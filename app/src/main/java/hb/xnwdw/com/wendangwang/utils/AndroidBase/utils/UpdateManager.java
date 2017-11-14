package hb.xnwdw.com.wendangwang.utils.AndroidBase.utils;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.maning.updatelibrary.InstallUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.List;


import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.NearShopActivity;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentMain11;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.base.BaseAndroid;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.widget.BaseDialog;

import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;
import okhttp3.Request;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 版本更新
 */
public class UpdateManager {
    private String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloads/";
    private int type = 0;//更新方式，0：引导更新，1：安装更新，2：强制更新
    private String url = "";//apk下载地址
    private String updateMessage = "";//更新内容
    private String fileName = null;//文件名
    private boolean isDownload = false;//是否下载
    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private BaseDialog dialog;
    private ProgressDialog progressDialog;

    public static UpdateManager updateManager;

    public static UpdateManager getInstance() {
        if (updateManager == null) {
            updateManager = new UpdateManager();
        }
        return updateManager;
    }

    private UpdateManager() {

    }

    /**
     * 弹出版本更新提示框
     */
    public void showDialog(final Context context) {

        final Dialog bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.updata_popwindow, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        // layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(false);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        LinearLayout not_updata = (LinearLayout) contentView.findViewById(R.id.not_updata);
        LinearLayout now_updata = (LinearLayout) contentView.findViewById(R.id.updata_now);
        TextView up_content = (TextView) contentView.findViewById(R.id.updata_content);
        String mupdateMessage = updateMessage.replaceAll("&", "\n");
        up_content.setText(mupdateMessage);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.not_updata:
                        bottomDialog.dismiss();
                        if (type == 2) {
                            System.exit(0);
                        }
                        break;
                    case R.id.updata_now:
                        /***
                         * 获取sd卡的读写权限
                         */
                        AndPermission.with(context)
                                .permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                                )
                                .requestCode(100)
                                .callback(new PermissionListener() {
                                    @Override
                                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                        if (isDownload) {
                                            installApk(context, new File(downLoadPath, fileName));
                                        } else {
                                            if (url != null && !TextUtils.isEmpty(url)) {
                                                if (type == 2) {
                                                    createProgress(context);
                                                } else {
                                                    createNotification(context);
                                                }
                                                downloadFile(context);
                                                bottomDialog.dismiss();
                                            } else {
                                                Toast.makeText(context, "下载地址错误", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                        Toast.makeText(context, "权限获取失败", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .start();

                        break;
                }


            }
        };

        not_updata.setOnClickListener(clickListener);
        now_updata.setOnClickListener(clickListener);


//        String title = "";
//        String left = "";
//        boolean cancelable = true;
//        if (type == 1 | isDownload) {
//            title = "安装新版本";
//            left = "立即安装";
//        } else {
//            title = "发现新版本";
//            left = "立即更新";
//        }
//        if (type == 2) {
//            cancelable = false;
//        }
//
//        dialog = new BaseDialog.Builder(context).setTitle(title).setMessage(updateMessage).setCancelable(cancelable)
//                .setLeftClick(left, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        if (type == 1 | isDownload) {
//                            installApk(context, new File(downLoadPath, fileName));
//                        } else {
//                            if (url != null && !TextUtils.isEmpty(url)) {
//                                if (type == 2) {
//                                    createProgress(context);
//                                } else {
//                                    createNotification(context);
//                                }
//                                downloadFile(context);
//                            } else {
//                                Toast.makeText(context, "下载地址错误", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                })
//                .setRightClick("取消", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        dialog.dismiss();
//                        if (type == 2) {
//                            System.exit(0);
//                        }
//                    }
//                })
//                .create();
//        dialog.show();
    }


    /**
     * 系统下载管理器
     */
    private DownloadManager dm;

    /**
     * 系统下载器分配的唯一下载任务id，可以通过这个id查询或者处理下载任务
     */
    private long enqueue;

    private String downloadUrl = "http://dakaapp.troila.com/download/daka.apk?v=3.0";

    /**
     * 下载apk
     */
    public void downloadFile(final Context context) {

        downLoad(context);
    }


    /**
     * 强制更新时显示在屏幕的进度条
     */
    private void createProgress(final Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在下载...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                progressDialog.dismiss();
                System.exit(0);
            }
        });
        progressDialog.show();
    }

    /**
     * 创建通知栏进度条
     */

    private Notification notification;

    private void createNotification(Context context) {
        mNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(BaseAndroid.getBaseConfig().getAppLogo());
        mBuilder.setContentTitle("版本更新");
        mBuilder.setContentText("正在下载...");
        mBuilder.setColor(context.getResources().getColor(R.color.maincolor));
        mBuilder.setProgress(100, 0, false);
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotifyManager.notify(122, notification);
    }


    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    APK文件
     */
    private void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /**
     * @return 当前应用的版本号
     */
    public int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断当前网络是否wifi
     */
    public boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public UpdateManager setUrl(String url) {
        this.url = url;
        return this;
    }

    public UpdateManager setType(int type) {
        this.type = type;
        return this;
    }

    public UpdateManager setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
        return this;
    }

    public UpdateManager setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public UpdateManager setIsDownload(boolean download) {
        isDownload = download;
        return this;
    }

//     最新APK的下载地址
    public static final String APK_URL = "https://www.qhwendang.com/AppDownload/DownloadAndroid";
    //下载后的APK的命名
    public static final String APK_NAME = "wdwup";

    private void downLoad(final Context context) {

        new InstallUtils(context, APK_URL, APK_NAME, new InstallUtils.DownloadCallBack() {
            @Override
            public void onStart() {
                Log.i("TAG", "InstallUtils---onStart");
                mBuilder.setContentTitle("安装更新");
//                    mBuilder.setContentText("点击安装");
               mBuilder.setProgress(100, 0, false);
             mNotifyManager.notify(122, mBuilder.build());
            }

            @Override
            public void onComplete(String path) {
                Log.i("TAG", "InstallUtils---onComplete:" + path);

                /**
                 * 安装APK工具类
                 * @param context       上下文
                 * @param filePath      文件路径
                 * @param authorities   ---------Manifest中配置provider的authorities字段---------
                 * @param callBack      安装界面成功调起的回调
                 */
                InstallUtils.installAPK(context, path, context.getPackageName() + ".fileProvider", new InstallUtils.InstallCallBack() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(context, "正在安装程序", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(context, "安装失败:" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                mBuilder.setContentTitle("下载完成");
               mBuilder.setContentText("");
                mBuilder.setProgress(100, 100, false);
                mNotifyManager.notify(122, mBuilder.build());
                
            }

            @Override
            public void onLoading(long total, long current) {
                Log.i("TAG", "InstallUtils----onLoading:-----total:" + total + ",current:" + current);
                //    tv_progress.setText((int) (current * 100 / total) + "%");

                if ((type == 0) || (type == 1)) {
                            if (((int)(current*100/total))%1== 0) {
                                Log.d("TAG", "((int)(current*100/total)):" + ((int) (total * 100 / current)));
                                mBuilder.setProgress(100, (int) ((int)(current*100/total)), false);
                                mNotifyManager.notify(122, mBuilder.build());
                            }
                        } else {
                            if ((int)(current*100/total)%1== 0) {
                                progressDialog.setProgress((int)(int) ((int)(current*100/total)));
                            }
                        }
                
            }

            @Override
            public void onFail(Exception e) {
                Log.i("TAG", "InstallUtils---onFail:" + e.getMessage());
            }

        }).downloadAPK();

    }
}
