package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/12/18.
 */

public class Nav_mapActivity extends Activity {
    @BindView(R.id.web_nav)
    WebView webNav;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        setContentView(R.layout.navmap);
        ButterKnife.bind(this);
        mContext=this;
        Intent intent = getIntent();
        double lat = intent.getDoubleExtra("lat", 0);
        double lot = intent.getDoubleExtra("lot", 0);
        double mlat = intent.getDoubleExtra("mlat", 0);
        double mlot = intent.getDoubleExtra("mlot", 0);

        String name = intent.getStringExtra("name");
        String storeAddress = intent.getStringExtra("storeAddress");
        String url = "http://api.map.baidu.com/direction?origin=latlng:" + mlat + "," + mlot + "|name:我的位置" + "&destination=latlng:" + lat + "," + lot + "|name:" + name + "&mode=driving&region=西宁&output=html&src=wdw";

        WebViewSetings.setWebView(webNav, url, this);
        webNav.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边

                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                super.onPageStarted(view, url, favicon);
                showDialog(1);

            }

            @Override
            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);
                dismissDialog(1);

            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webNav.destroy();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return ProgressDialog.show(mContext, null,
                    mContext.getString(R.string.loading));
        }
        return super.onCreateDialog(id);
    }
}
