package hb.xnwdw.com.wendangwang.gui.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.LoadingFragment;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.base.BaseAndroid;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.widget.LoadingDialog;

/**
 * Created by Administrator on 2017/2/20.
 */
public abstract class ActivityBase extends FragmentActivity {
    private WDWApp mApliction;
    LoadingFragment loadingFragment;
    private static final String TAG = "ActivityBase";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
         //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
         //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }

        setContentView(getContentViewResId());
        //状态栏高度
      // getRootView(this).setPadding(0,getStatusHeight(),0,0);
        StatusBarUtil.setColor(this,getResources().getColor(R.color.white),255);
        initViews(); //初始化控件
        initDatas();//初始化数据

    }

    protected abstract int getContentViewResId();

    /**
     * 初始化控件
     */
    protected abstract void initViews();

    protected abstract void initDatas();




    /**
     * 重写Back键
     *
     * @param keyCode
     * @param event
     * @return
     */

    /**
     * 获取状态栏高度
     */
    public  int getStatusHeight() {
        final Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int n = rect.top;
        if (n != 0) {
            return n;
        }
        try {
            final Class<?> forName = Class.forName("com.android.internal.R$dimen");
            n = getResources().getDimensionPixelSize(Integer.parseInt(forName.getField("status_bar_height").get(forName.newInstance()).toString()));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        } catch (InstantiationException ex3) {
            ex3.printStackTrace();
        } catch (NumberFormatException ex4) {
            ex4.printStackTrace();
        } catch (IllegalArgumentException ex5) {
            ex5.printStackTrace();
        } catch (SecurityException ex6) {
            ex6.printStackTrace();
        } catch (NoSuchFieldException ex7) {
            ex7.printStackTrace();
        }
        return n;
    }


    /**
     * 设置全屏
     */
    protected void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }




    /**
     * 判断是否有网络
     */
    public boolean isNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable();
    }





    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * dip转像素
     */
    public static int dip2px(Context context,float dpValue){
        final float scale =context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }


//    /**
//     * 递归调用，对所有子Fragement生效
//     *
//     * @param frag
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//
//    private void handleResult(Fragment frag, int requestCode, int resultCode,
//                              Intent data) {
//        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
//        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
//        if (frags != null) {
//            for (Fragment f : frags) {
//                if (f != null)
//                    handleResult(f, requestCode, resultCode, data);
//            }
//        }
//    }



    /**
     * 获取根布局
     * @param context
     * @return
     */
    public View getRootView(Activity context)
    {
        return ((ViewGroup)context.findViewById(android.R.id.content)).getChildAt(0);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        FragmentManager fm = getSupportFragmentManager();
//        int index = requestCode >> 16;
//        if (index != 0) {
//            index--;
//            if (fm.getFragments() == null || index < 0
//                    || index >= fm.getFragments().size()) {
//                Log.w(TAG, "Activity result fragment index out of range: 0x"
//                        + Integer.toHexString(requestCode));
//                return;
//            }
//            Fragment frag = fm.getFragments().get(index);
//            if (frag == null) {
//                Log.w(TAG, "Activity result no fragment exists for index: 0x"
//                        + Integer.toHexString(requestCode));
//            } else {
//                handleResult(frag, requestCode, resultCode, data);
//            }
//            return;
//        }
//
//    }


    // 显示进度条,flag==ture表示点击dialog外部，dialog不会消失。flag==false表示点击dialog外部，dialog会消失
    public void loading() {
        if (loadingFragment == null) {
            loadingFragment = new LoadingFragment();
        }
        //这句代码用于解决频繁点击时发生的崩溃现象，未测试
        getSupportFragmentManager().executePendingTransactions();
        if (!loadingFragment.isAdded()) {
            loadingFragment.show(getSupportFragmentManager(), "loadingFragment");
        }
    }

    // 隐藏进度条
    public void dismiss() {
        Fragment prev = getSupportFragmentManager().findFragmentByTag("loadingFragment");
        if (prev != null) {
            loadingFragment.dismiss();
            return;
        }
        prev = getSupportFragmentManager().findFragmentByTag("loadingNoPromptFragment");
        if (prev != null) {
            loadingFragment.dismiss();
            return;
        }
    }


    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 设置标题栏信息
     */
    public void setTitleText(String string) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.lay_bg);
        relativeLayout.setBackgroundResource(BaseAndroid.getBaseConfig().getAppColor());
        LinearLayout backTv = (LinearLayout) findViewById(R.id.ly_base_back);
        backTv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
        TextView titleTv = (TextView) findViewById(R.id.tv_base_titleText);
        titleTv.setText(string);
    }



}

