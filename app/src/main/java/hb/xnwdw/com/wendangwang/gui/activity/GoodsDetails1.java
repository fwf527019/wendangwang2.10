package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.share.WbShareCallback;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.WeiBo.WConstants;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentGoods;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentGoodsPicDetails;
import hb.xnwdw.com.wendangwang.gui.fragment.GoodsEvaluateFragment;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */

public class GoodsDetails1 extends ActivityBase implements View.OnClickListener, WbShareCallback {
    private IWXAPI api;
    private Bitmap bitmap;
    private FragmentGoods fragmentGoods;
    private FragmentGoodsPicDetails fragmentGoodsDetails;
    private GoodsEvaluateFragment goodsEvaluateFragment;
    public GoodsDetailData data;
    private Handler handler;
    private String itemId;

    private TextView titalGood;
    private TextView titalGoodDetailPic;
    private TextView titalGoodEvaluat;
    private ImageView goback, shearBtn;
    private View view1, view2, view3;
    private Tencent mTencent;// 新建Tencent实例用于调用分享方法
    private Bundle params;
    private GoodsDetails1.MyIUiListener mIUiListener = new GoodsDetails1.MyIUiListener();
    private WbShareHandler shareHandler;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_goodsdetails;
    }

    @Override
    protected void initViews() {
        titalGood = (TextView) findViewById(R.id.goodsdetai_titel_goods_tv);
        titalGoodDetailPic = (TextView) findViewById(R.id.goodsdetai_titel_detail_tv);
        titalGoodEvaluat = (TextView) findViewById(R.id.goodsdetai_titel_evaluate_tv);
        goback = (ImageView) findViewById(R.id.gooddetai_back_img);
        shearBtn = (ImageView) findViewById(R.id.shear_btn);
        view1 = findViewById(R.id.good_detail_view1);
        view2 = findViewById(R.id.good_detail_view2);
        view3 = findViewById(R.id.good_detail_view3);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        loadGoodData(itemId);
        loading();
        mTencent = Tencent.createInstance("101423390", getApplicationContext());
        titalGood.setOnClickListener(this);
        titalGoodDetailPic.setOnClickListener(this);
        titalGoodEvaluat.setOnClickListener(this);
        goback.setOnClickListener(this);
        shearBtn.setOnClickListener(this);
        initTextColor(1);
        WbSdk.install(this, new AuthInfo(this, WConstants.APP_KEY, WConstants.REDIRECT_URL,
                WConstants.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();

    }

    /**
     * 获取商品详情的数据
     *
     * @param id
     */
    private void loadGoodData(String id) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataSource", "APP");
        map.put("itemId", id);
        map.put("memberId", WDWApp.getMenberId());
        Log.d("GoodsDetails1", itemId);
        Log.d("GoodsDetails1", UrlApi.URL_GETGOODSDETAIL);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETGOODSDETAIL, map, new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("GoodsDetails", "e:" + e);
                dismiss();
                Toast.makeText(GoodsDetails1.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                dismiss();
                LogUtils.d("GoodsDetails", response);
                data = JSON.parseObject(response, GoodsDetailData.class);
//                Message msg = new Message();
//                msg.what = 1;
//                msg.obj = data;//传对象，还有arg1、arg2……
//                handler.sendMessage(msg);

                if (data.getObj().getItemInfo().getItemPic1()!=null) {
                    OkHttpUtils
                            .get()
                            .url(UrlApi.SERVER_IP + data.getObj().getItemInfo().getItemPic1())
                            .build()
                            .execute(new BitmapCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(Bitmap response, int id) {
                                    bitmap = response;
                                }
                            });
                }
                    initfr1(data, itemId);


            }
        });

    }


    private void initfr1(GoodsDetailData data, String itemId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentGoods == null) {
            fragmentGoods = new FragmentGoods();
            Bundle bundle = new Bundle();
            bundle.putSerializable("Datas", data);
            bundle.putString("itemId", itemId);
            fragmentGoods.setArguments(bundle);
            transaction.add(R.id.goodsdetail_content_fr, fragmentGoods);
        }
        hideFragment(transaction);
        transaction.show(fragmentGoods);
        transaction.commit();
    }

    private void initfr2(GoodsDetailData data, String itemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentGoodsDetails == null) {
            fragmentGoodsDetails = new FragmentGoodsPicDetails();
            Bundle bundle = new Bundle();
            bundle.putString("itemId", itemId);
            bundle.putSerializable("Datas", data);
            fragmentGoodsDetails.setArguments(bundle);
            transaction.add(R.id.goodsdetail_content_fr, fragmentGoodsDetails);
        }
        hideFragment(transaction);
        transaction.show(fragmentGoodsDetails);
        transaction.commit();
    }

    private void initfr3(GoodsDetailData data, String itemId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (goodsEvaluateFragment == null) {
            goodsEvaluateFragment = new GoodsEvaluateFragment();
            Bundle bundle = new Bundle();
            bundle.putString("itemId", itemId);
            bundle.putSerializable("Datas", data);
            goodsEvaluateFragment.setArguments(bundle);
            transaction.add(R.id.goodsdetail_content_fr, goodsEvaluateFragment);
        }
        hideFragment(transaction);
        transaction.show(goodsEvaluateFragment);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentGoods != null) {
            transaction.hide(fragmentGoods);
        }
        if (fragmentGoodsDetails != null) {
            transaction.hide(fragmentGoodsDetails);
        }
        if (goodsEvaluateFragment != null) {
            transaction.hide(goodsEvaluateFragment);
        }

    }

    Fragment fragment;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goodsdetai_titel_goods_tv:
                initTextColor(1);
                FragmentManager fr = getSupportFragmentManager();
                fragment = fr.findFragmentById(R.id.goodsdetail_content_fr);
                if (fragment instanceof FragmentGoods) {
                    EventBus.getDefault().post(10086);
                } else {
                    initfr1(data, itemId);
                }
                break;
            case R.id.gooddetai_back_img:
                finish();
                break;
            case R.id.goodsdetai_titel_detail_tv:
                initTextColor(2);
                initfr1(data, itemId);
                EventBus.getDefault().post(1);
                break;
            case R.id.goodsdetai_titel_evaluate_tv:
                initTextColor(3);
                initfr3(data, itemId);
                break;
            case R.id.shear_btn:
                show1();
                break;
        }
    }

    private void initTextColor(int pos) {
        TextView[] views1 = {titalGood, titalGoodDetailPic, titalGoodEvaluat};
        View[] views2 = {view1, view2, view3};
        for (int i = 0; i < 3; i++) {
            if (pos - 1 == i) {
                views1[i].setTextColor(getResources().getColor(R.color.maincolor));
                views2[i].setVisibility(View.VISIBLE);
            } else {
                views1[i].setTextColor(getResources().getColor(R.color.gry));
                views2[i].setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GooddetailScr(Integer messageEvent) {
        if (messageEvent == 2) {
            initTextColor(2);
        } else {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Goodgotoevalu(String messageEvent) {
        if (messageEvent.equals("gotoevaluat")) {
            initTextColor(3);
            initfr3(data, itemId);
        }
    }

    /**
     *分享弹窗
     */
    private void show1() {
        WindowManager wm = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_shear, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        ImageView img1 = (ImageView) contentView.findViewById(R.id.good_detailweb_shear_img1);
        ImageView img3 = (ImageView) contentView.findViewById(R.id.good_detailweb_shear_img3);
        ImageView img4 = (ImageView) contentView.findViewById(R.id.good_detailweb_shear_img4);
        ImageView img2 = (ImageView) contentView.findViewById(R.id.good_detailweb_shear_img2);
        TextView tv1 = (TextView) contentView.findViewById(R.id.popshear_tv);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        LinearLayout left_ll = (LinearLayout) contentView.findViewById(R.id.popwindleft_ll);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shearToQQ();
                bottomDialog.dismiss();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shearToFrends(UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId, data.getObj().getItemInfo().getItemName(), data.getObj().getItemInfo().getItemName(), 1);
                bottomDialog.dismiss();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMultiMessage();
                bottomDialog.dismiss();
            }


        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shearToFrends(UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId, data.getObj().getItemInfo().getItemName(), data.getObj().getItemInfo().getItemName(), 0);
                bottomDialog.dismiss();
            }
        });

        bottomDialog.show();
    }


    private void shearToQQ() {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, data.getObj().getActInfo().getItemName());// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, data.getObj().getActInfo().getItemName());// 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId);// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, UrlApi.SERVER_IP + data.getObj().getItemInfo().getItemPic1());// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        // 分享操作要在主线程中完成
        mTencent.shareToQQ(GoodsDetails1.this, params, mIUiListener);
    }

    @Override
    public void onWbShareSuccess() {

    }

    @Override
    public void onWbShareCancel() {

    }

    @Override
    public void onWbShareFail() {

    }

    class MyIUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            // 操作成功
            Toast.makeText(GoodsDetails1.this, "完成分享", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onError(UiError uiError) {
            // 分享异常
            Toast.makeText(GoodsDetails1.this, "分享异常", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onCancel() {
            // 取消分享
        }
    }

    /**
     * 微信分享
     *
     * @param url
     * @param title
     * @param description
     * @param scene
     * @return
     */
    private boolean shearToFrends(String url, String title, String description, int scene) {
        api = WXAPIFactory.createWXAPI(GoodsDetails1.this, WXConstants.APP_ID, true);
        api.registerApp(WXConstants.APP_ID);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(GoodsDetails1.this, "您还未安装微信客户端",
                    Toast.LENGTH_SHORT).show();
            return false;

        } else {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            WXMediaMessage msg = new WXMediaMessage(webpage);
            if (title != null) {
                msg.title = title;
            }
            if (description != null) {
                msg.description = description;
            }
            if (bitmap != null) {
                Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
                msg.setThumbImage(thumbBmp);
            }
            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = String.valueOf(System.currentTimeMillis());
            req.message = msg;
            req.scene = scene;
            return api.sendReq(req);
        }
    }


    /**
     * 第三方应用发送请求消息到微博，唤起微博分享界面。
     */
    private void sendMultiMessage() {


        WeiboMultiMessage weiboMessage = new WeiboMultiMessage();
        weiboMessage.mediaObject = getWebpageObj();
        shareHandler.shareMessage(weiboMessage, false);

    }

    private WebpageObject getWebpageObj() {
        WebpageObject mediaObject = new WebpageObject();
        mediaObject.identify = Utility.generateGUID();
        mediaObject.title = data.getObj().getActInfo().getItemName();
        mediaObject.description = data.getObj().getActInfo().getItemName();
        // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = " UrlApi.SERVER_IP + \"/wdw/page/mb/gs_detail.html?itemID=\" + itemId";
        mediaObject.defaultText = "稳当生活";
        return mediaObject;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_QQ_SHARE || resultCode == Constants.REQUEST_QZONE_SHARE || resultCode == Constants.REQUEST_OLD_SHARE) {
                Tencent.handleResultData(data, mIUiListener);
            }
        }
    }


}
