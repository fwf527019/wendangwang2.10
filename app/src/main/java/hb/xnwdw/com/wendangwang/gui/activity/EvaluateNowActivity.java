package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/6/29.
 */
public class EvaluateNowActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.evaluatenow_ll)
    LinearLayout evaluatenowLl;
    private String orderNumber;
   private WebView webView;
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_evaluatnow;
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
        final Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
         webView = new WebView(getApplicationContext());
        evaluatenowLl.addView(webView);
        title.setText("我的评价");
        WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/eva_list.html?orderNumber=" + orderNumber+"&app=app");
        WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/eva_list.html?orderNumber=" + orderNumber+"&app=app", getApplicationContext());
        webView.setWebViewClient(new WebViewClient() {
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
            if(url.contains("/wdw/page/mb/evaluate.html")){
                Intent intent1=new Intent(EvaluateNowActivity.this,PublishEvaluateActivity.class);
                intent1.putExtra("iItemID", Utils.cutString(url,"itemid="));
                intent1.putExtra("iOrderID",Utils.cutString(url,"orderID=","&itemid="));
                startActivity(intent1);
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
    protected void onResume() {
        super.onResume();
        WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + "/wdw/page/mb/eva_list.html?orderNumber=" + orderNumber+"&app=app");
        WebViewSetings.setWebView(webView, UrlApi.SERVER_IP + "/wdw/page/mb/eva_list.html?orderNumber=" + orderNumber+"&app=app", getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
