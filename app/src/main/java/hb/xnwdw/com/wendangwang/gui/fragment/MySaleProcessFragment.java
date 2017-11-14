package hb.xnwdw.com.wendangwang.gui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MySaleDetail;
import hb.xnwdw.com.wendangwang.gui.activity.My_sales;
import hb.xnwdw.com.wendangwang.gui.adapter.ProcessAdapter;
import hb.xnwdw.com.wendangwang.gui.view.MyPopWindow;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ProcessData;
import hb.xnwdw.com.wendangwang.netdata.entity.SaleDetailData;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/14.
 */
public class MySaleProcessFragment extends FragmentBase {
    @BindView(R.id.sale_process)
    RecyclerView saleProcess;
    Unbinder unbinder;
    @BindView(R.id.myself_process_nodata)
    LinearLayout myselfProcessNodata;
    private int page = 1;
    private ProcessAdapter adapter;
    private ProcessData data;
    private Handler handler;
    private Bitmap bm;
    private String pic64;
    private int saleAfterid;
    private int logstType = 2;

    @Override
    protected int getContentViewResId() {
        return R.layout.mysele_fr_process;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loaddata();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void loaddata() {
        final Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("pagesize", 20 + "");
        map.put("memberID", WDWApp.getMenberId());
        // 1代表未处理完成，-1 查询全部，其他代表查询已处理完成的售后
        map.put("isExecute", 1 + "");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetSaleAfterRecords, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, final int id) {
                LogUtils.d("MySaleProcessFragment", response);
                if (!response.contains(MConstant.HTTP404)) {


                    saleProcess.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                    if (data != null) {
                        data = null;
                    }
                    data = JSON.parseObject(response, ProcessData.class);
                    if (data.getObj() != null && data.getObj().size() != 0) {
                        adapter = new ProcessAdapter(R.layout.item_process_list, data.getObj(), 0);
                        saleProcess.setAdapter(adapter);
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {

                                loadSaleContent(data.getObj().get(position).getID() + "", new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        SaleDetailData datass = JSON.parseObject(response, SaleDetailData.class);
                                        if (datass.getObj().size() == 0) {
                                            Toast.makeText(getActivity(), "暂无处理结果", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Intent intent = new Intent(getActivity(), MySaleDetail.class);
                                            intent.putExtra("saleId", data.getObj().get(position).getID() + "");
                                            intent.putExtra("saleAfterNum", data.getObj().get(position).getAfterNum());
                                            intent.putExtra("saleType", data.getObj().get(position).getSaleAfterType());
                                            intent.putExtra("saleName", data.getObj().get(position).getItemName());
                                            intent.putExtra("salePic", data.getObj().get(position).getItemPic());
                                            intent.putExtra("saleSizi", data.getObj().get(position).getItemSize());
                                            intent.putExtra("saleNum", data.getObj().get(position).getCounts() + "");
                                            intent.putExtra("saleStat", data.getObj().get(position).getState());
                                            startActivity(intent);
                                        }
                                    }
                                });

                            }
                        });
                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                                switch (view.getId()) {
                                    case R.id.item_process_btn1:
                                        //填写物流
                                        saleAfterid = data.getObj().get(position).getID();
                                        show1();
                                        break;
                                    case R.id.item_process_btn2:
                                        MyPopWindow myPopWindow = new MyPopWindow(getActivity(), R.layout.popupwindow_cart, "取消", "确认");
                                        myPopWindow.setRightClick(new MyPopWindow.RightClick() {
                                            @Override
                                            public void rightClickListerner() {
                                                //撤销申请
                                                CancelApply(data.getObj().get(position).getID(), position);
                                            }
                                        });
                                        break;
                                }
                                return false;
                            }
                        });
                        adapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) saleProcess.getParent(), false));
                    } else {
                        myselfProcessNodata.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    /**
     * 取消售后
     *
     * @param saleAfterID
     * @param pos
     */
    private void CancelApply(int saleAfterID, final int pos) {

        JSONObject jsobj = new JSONObject();
        jsobj.put("saleAfterID", saleAfterID);
        jsobj.put("memberID", "asd");
        Log.d("MySaleProcessFragment", jsobj.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_CancelApply, jsobj.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MySaleProcessFragment", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(getActivity(), "撤销成功", Toast.LENGTH_SHORT).show();
                    data.getObj().remove(pos);
                    adapter.notifyItemRemoved(pos);
                    EventBus.getDefault().post(12);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    /***
     * 弹出上传图片框
     */
    private void show1() {
        View.OnClickListener listener;

        Button btn_choosefile;
        final ImageView img;
        Button btn_exit;
        Button btn_commit;
        final RadioButton radioButton2;
        final TextView tv_nme;

        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_pushlogist, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        // layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        btn_choosefile = (Button) contentView.findViewById(R.id.pushlogist_choosefile);
        img = (ImageView) contentView.findViewById(R.id.pushlogist_img);
        btn_exit = (Button) contentView.findViewById(R.id.pushlogist_exit);
        btn_commit = (Button) contentView.findViewById(R.id.pushlogist_commit);
        tv_nme = (TextView) contentView.findViewById(R.id.pushlogist_picnme);
        radioButton2 = (RadioButton) contentView.findViewById(R.id.pushlogist_rb2);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        //  tv_nme.setText();
                        img.setVisibility(View.VISIBLE);
                        img.setImageBitmap(bm);
                        break;
                }

            }
        };
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.pushlogist_choosefile:
                        showHeader();
                        break;
                    case R.id.pushlogist_exit:
                        bottomDialog.dismiss();
                        break;
                    case R.id.pushlogist_commit:
                        if (radioButton2.isChecked()) {
                            logstType = 1;
                        } else {
                            logstType = 2;
                        }
                        commitLogistData(logstType, new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.d("MySaleDetail", response);
                                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                                    Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                                    bottomDialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(), JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                                    bottomDialog.dismiss();
                                }
                            }
                        });
                        break;
                }
            }


        };
        btn_choosefile.setOnClickListener(listener);
        img.setOnClickListener(listener);
        btn_exit.setOnClickListener(listener);
        btn_commit.setOnClickListener(listener);

        View rootview = LayoutInflater.from(getActivity()).inflate(R.layout.activity_accontinfo, null);

        bottomDialog.show();
    }


    /**
     * 选头像
     */
    private void showHeader() {
        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_chooseheader, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        LinearLayout chooseheadCamare = (LinearLayout) contentView.findViewById(R.id.choosehead_camare);
        LinearLayout choosheadPhoto = (LinearLayout) contentView.findViewById(R.id.chooshead_photo);
        LinearLayout chooseheadExit = (LinearLayout) contentView.findViewById(R.id.choosehead_exit);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        chooseheadCamare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //相机权限管理
                AndPermission.with(getActivity())
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(getActivity(), rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                                //  Intent 跳转相机
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                bottomDialog.dismiss();
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                                MainPagerActivity.GotoFragment(0);
                            }
                        })
                        .start();

            }
        });
        choosheadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
                bottomDialog.dismiss();
            }
        });
        chooseheadExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String url = "";
        Bitmap bitmap = null;
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                bm = (Bitmap) bundle.get("data");
                //      Bitmap bit = HelpUtil.makeRoundCorner(bm);
                pic64 = HelpUtil.bitmapToBase64(bm);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                // chooseheadImg.setImageBitmap(bit);
            }
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Toast.makeText(getActivity(), imagePath, Toast.LENGTH_SHORT).show();
            //       Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ContentResolver cr = getActivity().getContentResolver();
            try {
                bm = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                //bitimap 圆形化
                //       Bitmap bit = HelpUtil.makeRoundCorner(bm);
                //bitmap转化64编码
                pic64 = HelpUtil.bitmapToBase64(bm);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                //  chooseheadImg.setImageBitmap(bit);
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    /**
     * 上传图片
     */
    private void commitLogistData(int logisticsTypeID, StringCallback callback) {
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("memberI", "as");
        jsonobject.put("saleAfterID", saleAfterid);
        jsonobject.put("logisticsTypeID", logisticsTypeID);
        jsonobject.put("img", pic64);
        LogUtils.d("MySaleDetail", jsonobject.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_UpLoadLogisticsPic, jsonobject.toJSONString(), callback);

    }

    private void loadSaleContent(String id, StringCallback stringCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("iSaleAfterID", id);
        map.put("memberID", "hehhe");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetDetails, map, stringCallback);

    }


    @Override
    public void onResume() {
        super.onResume();
     //   loaddata();
        Log.d("MySaleProcessFragment", "onResume");
    }
}
