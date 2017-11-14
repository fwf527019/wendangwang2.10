package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.view.ScrollerWebview;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ShearchResutsActivityweb extends ActivityBase {

    @BindView(R.id.search_web)
    ScrollerWebview searchWeb;
    @BindView(R.id.resutweb_back)
    ImageView resutwebBack;
    @BindView(R.id.nonetwork)
    LinearLayout nonetwork;
    private String url;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_searchresut_web;
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
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("keyword");
        url = UrlApi.SERVER_IP + "/wdw/page/mb/search_result.html" + keyword + "&app=app";
        WebViewSetings.synCookies(ShearchResutsActivityweb.this, url);
        WebViewSetings.setWebView(searchWeb, url, ShearchResutsActivityweb.this);

        searchWeb.setOnScrollChangedCallback(new ScrollerWebview.OnScrollChangedCallback() {
            @Override
            public void onScroll(int left, int top, int oldLeft, int oldTop) {
                if (top > dip2px(ShearchResutsActivityweb.this, 50)) {
                    resutwebBack.setVisibility(View.GONE);
                } else {
                    resutwebBack.setVisibility(View.VISIBLE);
                }
            }
        });



        searchWeb.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                nonetwork.setVisibility(View.VISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("/wdw/page/mb/gs_detail.html")) {

                    Intent intent = new Intent(ShearchResutsActivityweb.this, GoodsDetails1.class);
                    intent.putExtra("itemId", Utils.cutString(url, "itemId="));
                }

                //购物车
                if (url.contains("/wdw/page/mb/shopping_car")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, MainPagerActivity.class);
                    i.putExtra("fr", 3);
                    startActivity(i);
                    return true;
                }
                //分类聚合
                if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, MainPagerActivity.class);
                    i.putExtra("fr", 1);
                    startActivity(i);
                    return true;

                }
                //首页
                if (url.contains("/wdw/page/mb/index.html")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, MainPagerActivity.class);
                    i.putExtra("fr", 0);
                    startActivity(i);
                    return true;

                }

                //个人中心
                if (url.contains("/wdw/page/mb/user_center.html")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, MainPagerActivity.class);
                    i.putExtra("fr", 4);
                    startActivity(i);
                    return true;
                }

                //商品详情
                if (url.contains("/wdw/page/mb/gs_detail.html")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, GoodsDetails1.class);
                    //GoodsDetails1.class
                    String itemId = Utils.cutString(url, "itemId=");
                    i.putExtra("itemId", itemId);
                    startActivity(i);
                    return true;
                }
                //登录
                if (url.contains("/wdw/page/mb/login.html")) {
                    Intent i = new Intent(ShearchResutsActivityweb.this, LognActivity.class);
                    startActivity(i);
                    return true;
                }
                return false;


            }
        });
    }


    @OnClick(R.id.resutweb_back)
    public void onViewClicked() {
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (searchWeb != null) {
            searchWeb.clearHistory();
            ((ViewGroup) searchWeb.getParent()).removeView(searchWeb);
            searchWeb.loadUrl("about:blank");
            searchWeb.stopLoading();
            searchWeb.setWebChromeClient(null);
            searchWeb.setWebViewClient(null);
            searchWeb.destroy();
            searchWeb = null;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ShearchResutsActivitywe", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("ShearchResutsActivitywe", "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("ShearchResutsActivitywe", "onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("ShearchResutsActivitywe", "onPause");
    }
}
