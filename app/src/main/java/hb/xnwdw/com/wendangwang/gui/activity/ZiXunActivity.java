package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/3/15.
 */

public class ZiXunActivity extends ActivityBase {

    @BindView(R.id.zixun_web)
    WebView zixunWeb;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.zixun_ll)
    LinearLayout zixunLl;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_zixun;
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
        title.setText("资讯中心");


        WebViewSetings.synCookies(ZiXunActivity.this, UrlApi.SERVER_IP + "/wdw/page/mb/news_list.html?app=app");
        WebViewSetings.setWebView(zixunWeb, UrlApi.SERVER_IP + "/wdw/page/mb/news_list.html?app=app", ZiXunActivity.this);


        zixunWeb.setWebViewClient(new WebViewClient() {
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
                    Intent i = new Intent(ZiXunActivity.this, PayActivity.class);
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
                    Intent i = new Intent(ZiXunActivity.this, MainPagerActivity.class);
                    i.putExtra("fr", 3);
                    startActivity(i);
                }
                //个人中心
                else if (url.contains("/wdw/page/mb/user_center.html")) {
                    MainPagerActivity.GotoFragment(3);
                    return true;
                } else if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    String brandId = Utils.cutString(url, "brandId=");
                    Intent i = new Intent(ZiXunActivity.this, BrandDetails.class);
                    i.putExtra("brandId", brandId);
                    startActivity(i);
                    return true;
                }
                //资讯中心
                else if (url.contains("/wdw/page/mb/news_detail.html?")) {
                    Intent intent = new Intent(getApplicationContext(),UrlWebActivity.class);
                    intent.putExtra("tital","资讯详情");
                    intent.putExtra("url", url);
                    startActivity(intent);
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


    @Override
    protected void onPause(){
        super.onPause();

        zixunWeb.pauseTimers();
        if(isFinishing()){
            zixunWeb.loadUrl(UrlApi.SERVER_IP + "/wdw/page/mb/news_list.html?app=app");
            setContentView(new FrameLayout(this));
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        zixunWeb.resumeTimers();
    }

}
