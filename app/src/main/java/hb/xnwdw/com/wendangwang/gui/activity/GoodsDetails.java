package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
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
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.EventBus.Addaress_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */

public class GoodsDetails extends ActivityBase {

    @BindView(R.id.myweb_goodsdetail)
    WebView mywebGoodsdetail;

    @BindView(R.id.gooddetaiweb_back_img)
    ImageView gooddetaiwebBackImg;
    @BindView(R.id.goodsdetaiweb_titel_goods_tv)
    TextView goodsdetaiwebTitelGoodsTv;
    @BindView(R.id.goodsdetaiweb_titel_detail_tv)
    TextView goodsdetaiwebTitelDetailTv;
    @BindView(R.id.good_detailweb_view2)
    View goodDetailwebView2;
    @BindView(R.id.goodsdetaiweb_titel_evaluate_tv)
    TextView goodsdetaiwebTitelEvaluateTv;
    @BindView(R.id.good_detailweb_view3)
    View goodDetailwebView3;
    @BindView(R.id.good_detailweb_shear_btn)
    ImageView goodDetailwebShearBtn;
    @BindView(R.id.good_detailweb_view1)
    View goodDetailwebView1;
    @BindView(R.id.good_detailweb_tital)
    LinearLayout goodDetailwebTital;

    public GoodsDetailData datas;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.right_tv)
    TextView rightTv;
    private String itemId;
    private Tencent mTencent;// 新建Tencent实例用于调用分享方法
    private String name;
    private String pic;
    public static String CookieStr;
    private Bitmap bitmap;
    private int tag = 0;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_goodsdetailsweb;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // QQ分享对象
        mTencent = Tencent.createInstance("101423390", getApplicationContext());
        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        Intent intent = getIntent();
        itemId = intent.getStringExtra("itemId");
        initData(itemId);
        WebSettings settings = mywebGoodsdetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);// 支持缩放
        initTextColor(1);
        settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app");
        mywebGoodsdetail.loadUrl(UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app");
        mywebGoodsdetail.addJavascriptInterface(GoodsDetails.this, "android");
        mywebGoodsdetail.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("GoodsDetails", url);
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/wdw/page/mb/pay.html")) {
                    LogUtils.d("FragmentShoppingCartWeb", url);
                    String OrderNumber = Utils.cutString(url, "OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(GoodsDetails.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                //购物车
                if (url.contains("/wdw/page/mb/shopping_car")) {
                    rightTv.setVisibility(View.GONE);
                    Intent i = new Intent(GoodsDetails.this, MainPagerActivity.class);
                    CookieManager cookieManager = CookieManager.getInstance();
                    CookieStr = cookieManager.getCookie(url);
                    i.putExtra("fr", 3);
                    if (WDWApp.getUserToken() != null) {
                        startActivity(i);
                    } else {
                        Toast.makeText(GoodsDetails.this, "请登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(GoodsDetails.this, LognActivity.class));
                    }
                    return true;
                }
                //分类聚合
                if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    String brandId = Utils.cutString(url, "brandId=");
                    Intent i = new Intent(GoodsDetails.this, BrandDetails.class);
                    i.putExtra("brandId", brandId);
                    startActivity(i);
                    return true;
                }

                //登录
                if (url.contains("/wdw/page/mb/login.html")) {
                    startActivity(new Intent(GoodsDetails.this, LognActivity.class));
                    return true;
                }
                //商品详情
                if (url.contains("/wdw/page/mb/gs_detail.html")) {
                    WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + Utils.cutString(url, "(?i)itemId=") + "&app=app");
                    WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + Utils.cutString(url, "(?i)itemId=") + "&app=app", getApplicationContext());
                    return true;

                }

                //立即购买确认订单
                if (url.contains("/wdw/page/mb/order")) {

                    if (url.contains("?")) {
                        WebViewSetings.synCookies(GoodsDetails.this, url + "&app=app");
                        WebViewSetings.setWebView(mywebGoodsdetail, url + "&app=app", GoodsDetails.this);
                        goodDetailwebTital.setVisibility(View.GONE);
                        publicTital.setVisibility(View.VISIBLE);
                        title.setText("确认订单");
                        rightTv.setVisibility(View.GONE);
                    } else {
                        WebViewSetings.synCookies(GoodsDetails.this, url + "?app=app");
                        WebViewSetings.setWebView(mywebGoodsdetail, url + "?app=app", GoodsDetails.this);
                        goodDetailwebTital.setVisibility(View.GONE);
                        publicTital.setVisibility(View.VISIBLE);
                        title.setText("确认订单");
                        rightTv.setVisibility(View.GONE);
                    }
                    return true;
                }
                //优惠券
                if (url.contains("/wdw/page/mb/my_cpipon.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(mywebGoodsdetail, url + "&app=app", getApplicationContext());
                    goodDetailwebTital.setVisibility(View.GONE);
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("我的优惠券");
                    rightTv.setVisibility(View.GONE);
                    return true;
                }
                //收货地址
                if (url.contains("/wdw/page/mb/my_addr.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(mywebGoodsdetail, url + "&app=app", getApplicationContext());
                    goodDetailwebTital.setVisibility(View.GONE);
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("我的收货地址");
                    rightTv.setVisibility(View.VISIBLE);
                    rightTv.setText("新增收货地址");
                    return true;
                }
                //门店自提
                if (url.contains("/wdw/page/mb/sel_store.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(mywebGoodsdetail, url + "&app=app", getApplicationContext());
                    goodDetailwebTital.setVisibility(View.GONE);
                    publicTital.setVisibility(View.VISIBLE);
                    rightTv.setVisibility(View.GONE);
                    title.setText("门店自提");
                    return true;
                }
                //编辑
                if (url.contains("/wdw/page/mb/my_addr_edit.html")) {
                    //TODO:
                    return true;

                }
                //优惠券
                if (url.contains("/wdw/page/mb/coupon.html")) {
                    //TODO:
                    return true;

                }


                //客服
                if (url.contains("wdw/page/mb/wdw.html")) {
                    String title = "稳当网客服";
                    /**
                     * 设置访客来源，标识访客是从哪个页面发起咨询的，
                     * 用于客服了解用户是从什么页面进入三个参数分别为
                     * 来源页面的url，来源页面标题，来源页面额外信息（可自由定义）。
                     * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                     */
                    ConsultSource source = new ConsultSource("url", "商品详情", "custom information string");
                    /**
                     * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                     * 如果返回为false，该接口不会有任何动作
                     *
                     * @param context 上下文
                     * @param title   聊天窗口的标题
                     * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                     */
                    if (Unicorn.isServiceAvailable()) {
                        Unicorn.openServiceActivity(getApplicationContext(), title, source);
                    }
                    return true;
                }

                return false;
            }
        });
    }

    private void initTextColor(int pos) {
        TextView[] views1 = {goodsdetaiwebTitelGoodsTv, goodsdetaiwebTitelDetailTv, goodsdetaiwebTitelEvaluateTv};
        View[] views2 = {goodDetailwebView1, goodDetailwebView2, goodDetailwebView3};
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

    @OnClick({R.id.right_tv, R.id.back, R.id.gooddetaiweb_back_img, R.id.goodsdetaiweb_titel_goods_tv, R.id.goodsdetaiweb_titel_detail_tv, R.id.goodsdetaiweb_titel_evaluate_tv, R.id.good_detailweb_shear_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gooddetaiweb_back_img:
                finish();
                break;
            case R.id.goodsdetaiweb_titel_goods_tv:
                WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app");
                WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app", getApplicationContext());
                mywebGoodsdetail.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        mywebGoodsdetail.loadUrl("javascript:showItem()");
                    }
                });

                initTextColor(1);
                break;
            case R.id.goodsdetaiweb_titel_detail_tv:
                WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app");
                WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app", getApplicationContext());
                mywebGoodsdetail.setWebViewClient(new WebViewClient() {

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        mywebGoodsdetail.loadUrl("javascript:showDetail()");
                    }
                });
                initTextColor(2);
                break;
            case R.id.goodsdetaiweb_titel_evaluate_tv:
                publicTital.setVisibility(View.GONE);
                WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/eva_list2.html?itemId=" + itemId + "&app=app");
                WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/eva_list2.html?itemId=" + itemId + "&app=app", getApplicationContext());
                initTextColor(3);
                break;
            case R.id.good_detailweb_shear_btn:
                show1();
                break;
            case R.id.right_tv:
                startActivity(new Intent(GoodsDetails.this, AddAdrassActivity.class));
                break;
            case R.id.back:
                switch (title.getText().toString()) {
                    case "门店自提":
                        title.setText("确认订单");
                        rightTv.setVisibility(View.GONE);
                        mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                        break;
                    case "我的优惠券":
                        title.setText("确认订单");
                        rightTv.setVisibility(View.GONE);
                        mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                        break;
                    case "我的收货地址":
                        title.setText("确认订单");
                        rightTv.setVisibility(View.GONE);
                        mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                        break;
                    case "确认订单":
                        rightTv.setVisibility(View.GONE);
                        goodDetailwebTital.setVisibility(View.VISIBLE);
                        publicTital.setVisibility(View.GONE);
                        mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                        break;

                }


                break;
        }
    }


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
                shearToFrends(UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId, name, name, 1);
                bottomDialog.dismiss();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shearToSina();
                bottomDialog.dismiss();
            }


        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shearToFrends(UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId, name, name, 0);
                bottomDialog.dismiss();
            }
        });

        bottomDialog.show();

    }


    private IWXAPI api;


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
        api = WXAPIFactory.createWXAPI(GoodsDetails.this, WXConstants.APP_ID, true);
        api.registerApp(WXConstants.APP_ID);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(GoodsDetails.this, "您还未安装微信客户端",
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
     * 分享到新浪
     */
    private void shearToSina() {

    }


    private Bundle params;
    private MyIUiListener mIUiListener = new MyIUiListener();

    private void shearToQQ() {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, name);// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, name);// 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId);// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, UrlApi.SERVER_IP + pic);// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        // 分享操作要在主线程中完成
        mTencent.shareToQQ(GoodsDetails.this, params, mIUiListener);

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

    class MyIUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            // 操作成功
            Toast.makeText(GoodsDetails.this, "完成分享", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onError(UiError uiError) {
            // 分享异常
            Toast.makeText(GoodsDetails.this, "分享异常", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onCancel() {
            // 取消分享
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tag != 1) {
            LogUtils.d("GoodsDetails", "onResume");
            WebViewSetings.synCookies(GoodsDetails.this, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app");
            WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId + "&app=app", GoodsDetails.this);
            goodDetailwebTital.setVisibility(View.VISIBLE);
            publicTital.setVisibility(View.GONE);
        } else {
            WebViewSetings.synCookies(GoodsDetails.this, UrlApi.SERVER_IP + "/wdw/page/mb/my_addr.html?OrderNumber=10" + "&app=app");
            WebViewSetings.setWebView(mywebGoodsdetail, UrlApi.SERVER_IP + "/wdw/page/mb/my_addr.html?OrderNumber=10" + "&app=app", GoodsDetails.this);
            goodDetailwebTital.setVisibility(View.GONE);
            publicTital.setVisibility(View.VISIBLE);
            title.setText("我的收货地址");
            rightTv.setVisibility(View.VISIBLE);
            rightTv.setText("新增收货地址");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mywebGoodsdetail.destroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    private void initData(String itemId) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("itemId", itemId);
//        LogUtils.d("GoodsDetails", itemId);
        map.put("dataSource", "Mobile");
        map.put("memberId", WDWApp.getMenberId());

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETGOODSDETAIL, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("GoodsDetails", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("GoodsDetails", response);
                datas = JSON.parseObject(response, GoodsDetailData.class);
                name = datas.getObj().getItemInfo().getItemName();
                pic = datas.getObj().getItemInfo().getItemPic1();
                bitmap = ImageLoaderUtil.getBitmap(UrlApi.SERVER_IP + pic);
            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AddAdress(Addaress_event event) {
        if (event.getStatus().equals("1")) {
            tag = 1;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            switch (title.getText().toString()) {
                case "门店自提":
                    title.setText("确认订单");
                    rightTv.setVisibility(View.GONE);
                    mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                    break;
                case "我的优惠券":
                    title.setText("确认订单");
                    rightTv.setVisibility(View.GONE);
                    mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                    break;
                case "我的收货地址":
                    title.setText("确认订单");
                    rightTv.setVisibility(View.GONE);
                    mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                    break;
                case "确认订单":
                    rightTv.setVisibility(View.GONE);
                    goodDetailwebTital.setVisibility(View.VISIBLE);
                    publicTital.setVisibility(View.GONE);
                    mywebGoodsdetail.loadUrl("javascript:history.go(-1)");
                    break;

            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }

}