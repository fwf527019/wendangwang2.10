package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/7/24.
 */
public class ZhuCeXieYiWeb extends ActivityBase {
    @BindView(R.id.zhucexieyi_web)
    WebView zhucexieyiWeb;
    @BindView(R.id.zhucexieyi_back)
    ImageView zhucexieyiBack;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_zhucexieyi;
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

        WebViewSetings.synCookies( ZhuCeXieYiWeb.this, UrlApi.SERVER_IP+"/wdw/page/mb/protocol.html"+"&app=app");
        WebViewSetings.setWebView(zhucexieyiWeb,UrlApi.SERVER_IP+"/wdw/page/mb/protocol.html",ZhuCeXieYiWeb.this);
    }

    @OnClick(R.id.zhucexieyi_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (zhucexieyiWeb != null) {
            zhucexieyiWeb.clearHistory();
            ((ViewGroup) zhucexieyiWeb.getParent()).removeView(zhucexieyiWeb);
            zhucexieyiWeb.loadUrl("about:blank");
            zhucexieyiWeb.stopLoading();
            zhucexieyiWeb.setWebChromeClient(null);
            zhucexieyiWeb.setWebViewClient(null);
            zhucexieyiWeb.destroy();
            zhucexieyiWeb = null;
        }
    }
}
