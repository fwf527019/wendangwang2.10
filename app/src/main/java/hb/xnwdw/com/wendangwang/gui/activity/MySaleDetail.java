package hb.xnwdw.com.wendangwang.gui.activity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.SaleDetailContentAdapter;
import hb.xnwdw.com.wendangwang.gui.view.MyPopWindow;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.SaleDetailData;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/14.
 */

public class MySaleDetail extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.saledetail_ordernum)
    TextView saledetailOrdernum;
    @BindView(R.id.saledetail_type)
    TextView saledetailType;
    @BindView(R.id.saledetail_img)
    SimpleDraweeView saledetailImg;
    @BindView(R.id.saledetail_name)
    TextView saledetailName;
    @BindView(R.id.saledetail_memo)
    TextView saledetailMemo;
    @BindView(R.id.saledetail_price)
    TextView saledetailPrice;
    @BindView(R.id.saledetail_num)
    TextView saledetailNum;
    @BindView(R.id.my_sale_)
    LinearLayout mySale;
    @BindView(R.id.saledetail_state)
    TextView saledetailState;
    @BindView(R.id.saledetail_btn1)
    TextView saledetailBtn1;
    @BindView(R.id.saledetail_btn2)
    TextView saledetailBtn2;
    @BindView(R.id.saledetail_content)
    RecyclerView saledetailContent;
    @BindView(R.id.sale_detail_ll)
    LinearLayout saleDetailLl;
    private String pic64;
    private Bitmap bm;
    private Handler handler;
    private String id,saleAfterNum;
    private int logstType = 2;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_mysaledetial;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title.setText("售后详情");
        rightTv.setText("联系客服");
        rightTv.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        id = intent.getStringExtra("saleId");
        saleAfterNum = intent.getStringExtra("saleAfterNum");
        Log.d("MySaleDetail", id);
        loadSaleContent(id);
        saledetailName.setText(intent.getStringExtra("saleName"));
        saledetailOrdernum.setText("售后单号:"+ saleAfterNum);
        saledetailImg.setImageURI(UrlApi.SERVER_IP + intent.getStringExtra("salePic"));
        saledetailMemo.setText(intent.getStringExtra("saleSizi"));
        if(intent.getStringExtra("saleNum")!=null&&!intent.getStringExtra("saleNum").equals("0")){
            saledetailNum.setText("×" + intent.getStringExtra("saleNum"));
        }else {
            saledetailNum.setVisibility(View.GONE);
        }

        saledetailType.setText(intent.getStringExtra("saleType"));
        if(intent.getStringExtra("saleStat")!=null){
        if (intent.getStringExtra("saleStat").equals("待收货")) {
            saledetailBtn1.setVisibility(View.VISIBLE);
        } else {
            saledetailBtn1.setVisibility(View.GONE);
        }
        }
    }


    private void loadSaleContent(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("iSaleAfterID", id);
        map.put("memberID", "hehhe");
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetDetails, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MySaleDetail", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MySaleDetail", response);
                final SaleDetailData data = JSON.parseObject(response, SaleDetailData.class);
                if (data.getObj().size() == 0) {
                    Toast.makeText(MySaleDetail.this, "暂无数据", Toast.LENGTH_SHORT).show();

                } else {
                    saleDetailLl.setVisibility(View.VISIBLE);
                    saledetailState.setText("应退金额:" + data.getObj().get(0).getShouldRefundMoney());
                    if(data.getObj().get(0).getUnit()!=null){
                        saledetailPrice.setText("¥" + data.getObj().get(0).getUnit());
                    }else {
                        saledetailPrice.setVisibility(View.GONE);
                    }

                    MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(MySaleDetail.this);
                    linearLayoutManager.setSmoothScrollbarEnabled(false);
                    saledetailContent.setLayoutManager(linearLayoutManager);
                    SaleDetailContentAdapter adapter = new SaleDetailContentAdapter(R.layout.item_salledetail, data.getObj());
                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            if(data.getObj().get(position).getDisposeInfo().contains("新订单已生成")){
                                String orderNum = Utils.cutString(data.getObj().get(position).getDisposeInfo(), ">", "<");
                                Intent intent=new Intent(MySaleDetail.this,OrderDetailsActivity.class);
                                intent.putExtra("orderNum",orderNum);
                                startActivity(intent);
                            }

                            return false;
                        }
                    });
                    saledetailContent.setAdapter(adapter);
                }
            }
        });

    }

    @OnClick({R.id.back, R.id.saledetail_btn1, R.id.saledetail_btn2,R.id.right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_tv:

                String title = "稳当生活";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，
                 * 用于客服了解用户是从什么页面进入三个参数分别为
                 * 来源页面的url，来源页面标题，来源页面额外信息（可自由定义）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
                ConsultSource source = new ConsultSource("url", "售后详情", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
                if (Unicorn.isServiceAvailable()) {
                    Log.d("GoodsDetails", "七鱼");
                    Unicorn.openServiceActivity(MySaleDetail.this, title, source);
                }

                break;
            case R.id.saledetail_btn1:
                //上传物流
                show1();
                break;
            case R.id.saledetail_btn2:
                //撤销售后
                MyPopWindow myPopWindow=new MyPopWindow(MySaleDetail.this,R.layout.popupwindow_cart,"取消","确认");
                myPopWindow.setRightClick(new MyPopWindow.RightClick() {
                    @Override
                    public void rightClickListerner() {
                        CancelApply(id);
                    }
                });
                break;
        }
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

        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_pushlogist, null);
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
                                    Toast.makeText(MySaleDetail.this, "上传成功", Toast.LENGTH_SHORT).show();
                                    bottomDialog.dismiss();
                                } else {
                                    Toast.makeText(MySaleDetail.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
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

        View rootview = LayoutInflater.from(MySaleDetail.this).inflate(R.layout.activity_accontinfo, null);

        bottomDialog.show();
    }


    /**
     * 选头像
     */
    private void showHeader() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindow_chooseheader, null);
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
                AndPermission.with(MySaleDetail.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(MySaleDetail.this, rationale)
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
                                Toast.makeText(MySaleDetail.this, "授权失败", Toast.LENGTH_SHORT).show();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Toast.makeText(this, imagePath, Toast.LENGTH_SHORT).show();
            //       Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ContentResolver cr = this.getContentResolver();
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
        jsonobject.put("saleAfterID", id);
        jsonobject.put("logisticsTypeID", logisticsTypeID);
        jsonobject.put("img", pic64);
        LogUtils.d("MySaleDetail", jsonobject.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_UpLoadLogisticsPic, jsonobject.toJSONString(), callback);

    }

    /**
     * 取消售后
     * @param saleAfterID
     * @param
     */
    private void CancelApply(String saleAfterID) {

        JSONObject jsobj = new JSONObject();
        jsobj.put("saleAfterID", saleAfterID);
        jsobj.put(",memberID", WDWApp.getMenberId());

        HtttpRequest.CreatPostRequst(UrlApi.URL_CancelApply, jsobj.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(MySaleDetail.this, "撤销成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MySaleDetail.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
