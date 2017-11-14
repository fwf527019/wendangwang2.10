package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/9/7.
 */

public class ShopingCartActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.shopcart_ll)
    LinearLayout shopcartLl;
    @BindView(R.id.nonetwork)
    LinearLayout nonetwork;
    private WebView webView;
    long time1,time2;
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_shopingcart;
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
        EventBus.getDefault().register(this);
        title.setText("购物车");
        webView = new WebView(ShopingCartActivity.this);
        shopcartLl.addView(webView);

        WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app", ShopingCartActivity.this);
        WebViewSetings.synCookies(ShopingCartActivity.this, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app");


        webView.setWebChromeClient(new WebChromeClient() {


            //=========HTML5定位==========================================================
            //需要先加入权限
            //<uses-permission android:name="android.permission.INTERNET"/>
            //<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
            //<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }
            //=========HTML5定位==========================================================

        });


        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onLoadResource(WebView view, String url) {
                if (url.contains("/wdw/page/mb/index.html")) {
                    LogUtils.d("FragmentShoppingCartWeb____", url);
                    title.setText("确认订单");
                }

                super.onLoadResource(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //开始时间
                time1 = System.currentTimeMillis();


            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //      webView.loadUrl("javascript:hideNavi()");
                // webView.loadUrl("javascript:sessionStorage.setItem(\"firstvisit\",1)");
                //   webView.loadUrl("javascript:setSessionStorage()");
                time2 = System.currentTimeMillis();
                Log.d("ShopingCartActivity", "(time1-time2):" + (time1 - time2));
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                nonetwork.setVisibility(View.VISIBLE);
            }

            @Override
            // 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("FragmentShoppingCartWeb", url);
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                //支付
                if (url.contains("/wdw/page/mb/pay.html")) {
                    LogUtils.d("FragmentShoppingCartWeb", url);
                    String OrderNumber = Utils.cutString(url, "OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(ShopingCartActivity.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                //分类
                else if (url.contains("/wdw/page/mb/list.html")) {
                    MainPagerActivity.GotoFragment(1);
                    return true;
                }

                //首页
                else if (url.contains("/wdw/page/mb/index.html")) {
                    Intent intent = new Intent(ShopingCartActivity.this, MainPagerActivity.class);
                    startActivity(intent);
                    return true;
                }
                //购物车
                else if (url.contains("/wdw/page/mb/shopping_car")) {
                    title.setText("购物车");
                    MainPagerActivity.GotoFragment(3);
                }
                //个人中心
                else if (url.contains("/wdw/page/mb/user_center.html")) {
                    MainPagerActivity.GotoFragment(4);
                    return true;
                    //商品详情
                } else if (url.contains("/wdw/page/mb/gs_detail.html")) {
                    Intent i = new Intent(ShopingCartActivity.this, GoodsDetails1.class);
                    String itemId = Utils.cutString(url, "(?i)itemid=");
                    i.putExtra("itemId", itemId);
                    startActivity(i);
                    return true;
                }
                //登录
                else if (url.contains("/wdw/page/mb/login.html")) {
                    Intent i = new Intent(ShopingCartActivity.this, LognActivity.class);
                    startActivity(i);
                    return true;
                }

                //确认订单
                else if (url.contains("wdw/page/mb/order_js")) {
                    if (url.contains("?")) {
                        WebViewSetings.synCookies(ShopingCartActivity.this, url + "&app=app");
                        WebViewSetings.setWebView(webView, url + "&app=app", ShopingCartActivity.this);
                        title.setText("确认订单");
                        back.setVisibility(View.VISIBLE);
                    } else {
                        WebViewSetings.synCookies(ShopingCartActivity.this, url + "?app=app");
                        WebViewSetings.setWebView(webView, url + "?app=app", ShopingCartActivity.this);
                        title.setText("确认定单");
                        back.setVisibility(View.VISIBLE);
                    }
                }
                //优惠券
                else if (url.contains("/wdw/page/mb/my_cpipon.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(ShopingCartActivity.this, url + "&app=app");
                    WebViewSetings.setWebView(webView, url + "&app=app", ShopingCartActivity.this);
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("我的优惠券");
                    back.setVisibility(View.VISIBLE);
                    return true;
                }
                //领取优惠券
                if (url.contains("/wdw/page/mb/coupon.html")) {
                    LogUtils.d("GoodsDetails", url);
                    rightTv.setVisibility(View.GONE);
                    startActivity(new Intent(ShopingCartActivity.this, ConpoudActivity.class));
                    return true;
                }
                //收货地址
                else if (url.contains("/wdw/page/mb/my_addr.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(ShopingCartActivity.this, url + "&app=app");
                    WebViewSetings.setWebView(webView, url + "&app=app", ShopingCartActivity.this);
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("我的收货地址");
                    back.setVisibility(View.VISIBLE);
                    rightTv.setVisibility(View.VISIBLE);
                    rightTv.setText("新增收货地址");
                    return true;
                }

                //编辑
                if (url.contains("/wdw/page/mb/my_addr_edit.html")) {
                    //TODO:
                    Intent intent = new Intent(ShopingCartActivity.this, EdtAdrassActivity.class);
                    intent.putExtra("id", Utils.cutString(url, "id=", "&OrderNumber"));
                    Log.d("FragmentShoppingCartWeb", Utils.cutString(url, "id=", "&OrderNumber"));
                    startActivity(intent);
                    return true;
                }
                //门店自提
                else if (url.contains("/wdw/page/mb/sel_store.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(ShopingCartActivity.this, url + "&app=app");
                    WebViewSetings.setWebView(webView, url + "&app=app", ShopingCartActivity.this);
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("门店自提");
                    back.setVisibility(View.VISIBLE);
                    rightTv.setVisibility(View.GONE);
                    return true;
                }
                //新增收货地址
                else if (url.contains("/wdw/page/mb/my_addr_edit.html?id=0")) {
                    LogUtils.d("GoodsDetails", url);
                    startActivity(new Intent(ShopingCartActivity.this, AddAdrassActivity.class));
                    return true;
                }


                return false;
            }
        });
    }


    @OnClick({R.id.back, R.id.right_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (title.getText().toString().equals("确认订单")) {
                    title.setText("购物车");
                    rightTv.setVisibility(View.GONE);
//            WebViewSetings.synCookies(ShopingCartActivity.this, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app");
//            WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app", ShopingCartActivity.this);
                    webView.loadUrl("javascript:history.go(-1)");
                } else if (title.getText().toString().equals("我的优惠券")) {
                    title.setText("确认订单");
                    rightTv.setVisibility(View.GONE);
                    back.setVisibility(View.VISIBLE);
                    webView.loadUrl("javascript:history.go(-1)");
                } else if (title.getText().toString().equals("我的收货地址")) {
                    title.setText("确认订单");
                    back.setVisibility(View.VISIBLE);
                    rightTv.setVisibility(View.GONE);
                    webView.loadUrl("javascript:history.go(-1)");
                } else if (title.getText().toString().equals("门店自提")) {
                    title.setText("确认订单");
                    rightTv.setVisibility(View.GONE);
                    back.setVisibility(View.VISIBLE);
                    webView.loadUrl("javascript:history.go(-1)");
                } else if (title.getText().toString().equals("购物车")) {
                    finish();
                }
                break;
            case R.id.right_tv:
                startActivity(new Intent(ShopingCartActivity.this, AddAdrassActivity.class));
                break;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AddAda(Integer event) {
        Log.d("ShopingCartActivity", "event:" + event);
        if (event == 5) {
            WebViewSetings.synCookies(ShopingCartActivity.this, UrlApi.SERVER_IP + "/wdw/page/mb/my_addr.html?OrderNumber=10" + "&app=app");
            WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/my_addr.html?OrderNumber=10" + "&app=app", ShopingCartActivity.this);
            publicTital.setVisibility(View.VISIBLE);
            title.setText("我的收货地址");
            rightTv.setVisibility(View.VISIBLE);
            rightTv.setText("新增收货地址");

        }
    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (webView != null) {
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView = null;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (title.getText().toString().equals("确认订单")) {
                title.setText("购物车");
                rightTv.setVisibility(View.GONE);
//            WebViewSetings.synCookies(ShopingCartActivity.this, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app");
//            WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/shopping_car.html?app=app", ShopingCartActivity.this);
                webView.loadUrl("javascript:history.go(-1)");
            } else if (title.getText().toString().equals("我的优惠券")) {
                title.setText("确认订单");
                rightTv.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                webView.loadUrl("javascript:history.go(-1)");
            } else if (title.getText().toString().equals("我的收货地址")) {
                title.setText("确认订单");
                back.setVisibility(View.VISIBLE);
                rightTv.setVisibility(View.GONE);
                webView.loadUrl("javascript:history.go(-1)");
            } else if (title.getText().toString().equals("门店自提")) {
                title.setText("确认订单");
                rightTv.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                webView.loadUrl("javascript:history.go(-1)");
            } else if (title.getText().toString().equals("购物车")) {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
