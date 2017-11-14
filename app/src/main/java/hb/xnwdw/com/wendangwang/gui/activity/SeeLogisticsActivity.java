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
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/6/14.
 */

public class SeeLogisticsActivity extends ActivityBase {
    @BindView(R.id.seelogist_webview)
    WebView seelogistWebview;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    private String orderNumber;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_seelogistics;
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
        title.setText("物流信息");
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/log_info.html?orderNumber=" + orderNumber + "&app=app");
        WebViewSetings.setWebView(seelogistWebview, UrlApi.SERVER_IP + "/wdw/page/mb/log_info.html?orderNumber=" + orderNumber + "&app=app", getApplicationContext());
        seelogistWebview.setWebViewClient(new WebViewClient() {
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
                return true;
            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
