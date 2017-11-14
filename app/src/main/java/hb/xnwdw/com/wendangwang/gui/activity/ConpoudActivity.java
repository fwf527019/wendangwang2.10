package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/7/17.
 */
public class ConpoudActivity extends ActivityBase {

    @BindView(R.id.conpound_web)
    WebView conpoundWeb;

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_conpoud;
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
        title.setText("优惠券");
        WebViewSetings.synCookies(this, UrlApi.SERVER_IP + "/wdw/page/mb/coupon.html?app=app");
        WebViewSetings.setWebView(conpoundWeb, UrlApi.SERVER_IP + "/wdw/page/mb/coupon.html?app=app", this);

        conpoundWeb.setWebViewClient(new WebViewClient() {
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
                    String OrderNumber = Utils.cutString(url, "OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(ConpoudActivity.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                //首页
                if (url.contains("/wdw/page/mb/index.html")) {
                    Intent i = new Intent(ConpoudActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 0);
                    startActivity(i);
                    return true;
                }
                //分类
                if (url.contains("/wdw/page/mb/list.html")) {
                    Intent i = new Intent(ConpoudActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 1);
                    startActivity(i);
                    return true;
                }
                //购物车
                if (url.contains("/wdw/page/mb/shopping_car")) {
                    Intent i = new Intent(ConpoudActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 3);
                    startActivity(i);
                    return true;
                }
                //个人中心
                if (url.contains("/wdw/page/mb/user_center.html")) {
                    Intent i = new Intent(ConpoudActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 4);
                    startActivity(i);
                    return true;
                }
                //分类
                if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    String brandId = Utils.cutString(url, "brandId=");
                    Intent i = new Intent(ConpoudActivity.this, BrandDetails.class);
                    i.putExtra("brandId", brandId);
                    startActivity(i);
                    return true;
                }
                //登录
                if (url.contains("/wdw/page/mb/login.html")) {
                    Intent i = new Intent(ConpoudActivity.this, LognActivity.class);
                    startActivity(i);
                    return true;
                }
                //支付
                if (url.contains("/wdw/page/mb/pay.html")) {
                    String OrderNumber = Utils.cutString(url, "OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(ConpoudActivity.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
