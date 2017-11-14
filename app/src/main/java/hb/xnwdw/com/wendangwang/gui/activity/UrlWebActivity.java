package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.share.WbShareHandler;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WeiBo.WConstants;
import hb.xnwdw.com.wendangwang.gui.view.ScrollerWebview;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;

/**
 * Created by Administrator on 2017/6/26.
 */

public class UrlWebActivity extends ActivityBase {


    @BindView(R.id.urlactivty_back)
    ImageView urlactivtyBack;
    @BindView(R.id.urlactivty_title)
    TextView urlactivtyTitle;
    @BindView(R.id.urlactivty_shear)
    ImageView urlactivtyShear;
    @BindView(R.id.urlwebview)
    ScrollerWebview urlwebview;
    @BindView(R.id.url_web_tital)
    RelativeLayout urlWebTital;
    @BindView(R.id.urlweb_back)
    ImageView urlwebBack;
    @BindView(R.id.nonetwork)
    LinearLayout nonetwork;
    private Tencent mTencent;
    private String url1;
    private String tital;
    private String url;
    private WbShareHandler shareHandler;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_urlweb;
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
        //QQ
        mTencent = Tencent.createInstance("101423390", getApplicationContext());
        //微博
        WbSdk.install(this, new AuthInfo(this, WConstants.APP_KEY, WConstants.REDIRECT_URL,
                WConstants.SCOPE));
        shareHandler = new WbShareHandler(this);
        shareHandler.registerApp();
        Intent intent = getIntent();
        url1 = intent.getStringExtra("url");
        if(url1.contains("/pc/news_detail.html")){
            url1.replaceAll("pc","mb");
            String type= Utils.cutString(url1,"type=","&");
            String id= Utils.cutString(url1,"id=");

            if(type.equals("0")){
                url1= "/wdw/page/mb/news_detail.html?t=gg&id="+id;
            }else {
                url1= "/wdw/page/mb/news_detail.html?t=zx&id="+id;
            }

            //   Utils.cutString(url1,"")
        }
        if(url1.contains("/pc/")){
            Toast.makeText(this, "该消息请在PC端打开", Toast.LENGTH_SHORT).show();
        }

        if (intent.getStringExtra("tag") != null) {
            String tag = intent.getStringExtra("tag");
            if ("more".equals(tag)) {
                urlWebTital.setVisibility(View.GONE);
                urlwebBack.setVisibility(View.VISIBLE);
            }
        }

        if (!url1.contains("http")) {
            url1 = UrlApi.SERVER_IP + url1;
        }

        if (url1.contains("?")) {
            url = url1 + "&app=app";
        } else {
            url = url1 + "?app=app";
        }
        if (url1.contains("/wdw/page/mb/search_result.html")) {
            urlWebTital.setVisibility(View.GONE);
            urlwebBack.setVisibility(View.VISIBLE);
        }


        if (url1.contains("/wdw/page/mb/coupon.html")) {
            urlactivtyTitle.setText("优惠券");
        }

        Log.d("UrlWebActivity", url1);
        tital = intent.getStringExtra("tital");
        urlactivtyTitle.setText(tital);
        WebViewSetings.synCookies(this, url);
        WebViewSetings.setWebView(urlwebview, url, this);

        urlwebview.setOnScrollChangedCallback(new ScrollerWebview.OnScrollChangedCallback() {
            @Override
            public void onScroll(int left, int top, int oldLeft, int oldTop) {
                LogUtils.d("UrlWebActivity", "oldTop:" + oldTop);
                LogUtils.d("UrlWebActivity", "top:" + top);
                if (url1.contains("/wdw/page/mb/search_result.html")) {
                    if (top > dip2px(UrlWebActivity.this, 50)) {
                        urlwebBack.setVisibility(View.GONE);
                    } else {
                        urlwebBack.setVisibility(View.VISIBLE);
                    }
                }

            }
        });
        urlwebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                urlactivtyTitle.setText(title);
            }
        });

        urlwebview.setWebViewClient(new WebViewClient() {


            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                nonetwork.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("FragmentShoppingCartWeb", url);
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/wdw/page/mb/pay.html")) {
                    LogUtils.d("FragmentShoppingCartWeb", url);
                    String OrderNumber = Utils.cutString(url, "(?i)OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(UrlWebActivity.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                //首页
                else if (url.contains("/wdw/page/mb/index.html")) {
                    MainPagerActivity.GotoFragment(0);
                    return true;
                }
                //分类
                else if (url.contains("/wdw/page/mb/list.html")) {
                    MainPagerActivity.GotoFragment(1);
                    return true;
                }
                //购物车
                else if (url.contains("/wdw/page/mb/shopping_car")) {
                    Intent i = new Intent(UrlWebActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 3);
                    startActivity(i);
                    return true;
                }
                //个人中心
                else if (url.contains("/wdw/page/mb/user_center.html")) {
                    MainPagerActivity.GotoFragment(3);
                    return true;
                } else if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    String brandId = Utils.cutString(url, "(?i)brandId=");
                    Intent i = new Intent(UrlWebActivity.this, BrandDetails.class);
                    i.putExtra("brandId", brandId);
                    startActivity(i);
                    return true;
                }
                //商品详情
                else if (url.contains("/wdw/page/mb/gs_detail.html")) {
                    Intent i = new Intent(UrlWebActivity.this, GoodsDetails1.class);
                    String itemId = Utils.cutString(url, "(?i)itemID=");
                    i.putExtra("itemId", itemId);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (urlwebview != null) {
            urlwebview.clearHistory();
            ((ViewGroup) urlwebview.getParent()).removeView(urlwebview);
            urlwebview.loadUrl("about:blank");
            urlwebview.stopLoading();
            urlwebview.setWebChromeClient(null);
            urlwebview.setWebViewClient(null);
            urlwebview.destroy();
            urlwebview = null;
        }
    }

    @OnClick({R.id.urlactivty_back, R.id.urlactivty_shear, R.id.urlweb_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.urlactivty_back:
                finish();
                break;
            case R.id.urlactivty_shear:
                show1();
                break;
            case R.id.urlweb_back:
                finish();
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
                shearToFrends(url, "稳当网活动", "", 1);
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
                shearToFrends(url, "稳当网活动", "", 0);
                bottomDialog.dismiss();
            }
        });

        bottomDialog.show();

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
    private Bitmap bitmap;
    private IWXAPI api;

    private boolean shearToFrends(String url, String title, String description, int scene) {
        api = WXAPIFactory.createWXAPI(UrlWebActivity.this, WXConstants.APP_ID, true);
        api.registerApp(WXConstants.APP_ID);
        if (!api.isWXAppInstalled()) {
            Toast.makeText(UrlWebActivity.this, "您还未安装微信客户端",
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
        sendMultiMessage();
    }


    /**
     * 分享到QQ
     */
    private String name, pic, itemId;
    private Bundle params;
    private MyIUiListener mIUiListener = new MyIUiListener();

    private void shearToQQ() {
        params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "稳当网活动");// 标题
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "");// 摘要
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);// 内容地址
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, UrlApi.SERVER_IP + pic);// 网络图片地址　　params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "应用名称");// 应用名称
        params.putString(QQShare.SHARE_TO_QQ_EXT_INT, "其它附加功能");
        // 分享操作要在主线程中完成
        mTencent.shareToQQ(UrlWebActivity.this, params, mIUiListener);

    }


    class MyIUiListener implements IUiListener {
        @Override
        public void onComplete(Object o) {
            // 操作成功
            Toast.makeText(UrlWebActivity.this, "完成分享", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onError(UiError uiError) {
            // 分享异常
            Toast.makeText(UrlWebActivity.this, "分享异常", Toast.LENGTH_SHORT).show();
        }

        @Override

        public void onCancel() {
            // 取消分享
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
        mediaObject.title = tital;
        mediaObject.description = "稳当生活品质优先！";
        // 设置 Bitmap 类型的图片到视频对象里设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
        //  mediaObject.setThumbImage(bitmap);
        mediaObject.actionUrl = url;
        mediaObject.defaultText = "稳当生活";
        return mediaObject;
    }

}
