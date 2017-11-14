package hb.xnwdw.com.wendangwang.gui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.ProgressWebView;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/6/21.
 */

public class My_sales extends ActivityBase {
    @BindView(R.id.my_sale_webview)
    ProgressWebView mySaleWebview;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    private ProgressBar progressBar;
    private int screenWidth;
    private ImageView iv_finish;



    @Override
    protected int getContentViewResId() {
        return R.layout.activit_mysale;
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
        title.setText("我的售后");
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        WebViewSetings.synCookies(getApplicationContext(), UrlApi.SERVER_IP + url);
        WebViewSetings.setWebView(mySaleWebview, UrlApi.SERVER_IP + url, getApplicationContext());
        mySaleWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mySaleWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                if (mySaleWebview != null && url != null) {
//               //     mySaleWebview.loadUrl("javascript:hideNavi()");
//                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("/wdw/page/mb/apply_sales.html")) {
                    LogUtils.d("FragmentShoppingCartWeb", url);
                    String OrderNumber = Utils.cutString(url, "OrderNumber=", "&itemID=");
                    String itemID = Utils.cutString(url, "&itemID=");
                    Intent i = new Intent(My_sales.this, ApplyCustomerServiceActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    i.putExtra("itemID", itemID);
                    startActivity(i);
                    return true;
                }
                if (url.contains("/wdw/page/mb/sales_detail.html")) {
                    LogUtils.d("My_sales", url);
                    if (url.contains("?")) {
                        WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                        WebViewSetings.setWebView(mySaleWebview, url + "&app=app", getApplicationContext());
                    } else {
                        WebViewSetings.synCookies(getApplicationContext(), url + "?app=app");
                        WebViewSetings.setWebView(mySaleWebview, url + "?app=app", getApplicationContext());
                    }
                    title.setText("售后详情");
                    rightTv.setVisibility(View.VISIBLE);
                    rightTv.setText("联系客服");
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
                if (title.getText().equals("售后详情")) {
                    title.setText("我的售后");
                    rightTv.setVisibility(View.GONE);
                    mySaleWebview.loadUrl("javascript:history.go(-1)");
                } else {
                    finish();
                }
                break;
            case R.id.right_tv:
                String title = "稳当客服";
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
                    Log.d("GoodsDetails", "七鱼");
                    Unicorn.openServiceActivity(getApplicationContext(), title, source);
                }
                break;
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mySaleWebview.removeAllViews();
        mySaleWebview.destroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("activity_sale", "requestCode=" + requestCode);
        if (resultCode == RESULT_OK) {
            if (requestCode == ProgressWebView.TYPE_CAMERA) { // 相册选择
                mySaleWebview.onActivityCallBack(true, null);
            } else if (requestCode == ProgressWebView.TYPE_GALLERY) {// 相册选择
                if (data != null) {
                    Uri uri = data.getData();
                    Log.d("activity_sale", "uri=" + uri);
                    if (uri != null) {
                        mySaleWebview.onActivityCallBack(false, uri);
                    } else {
                        Toast.makeText(My_sales.this, "获取数据为空", Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
    }




    // 权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == ProgressWebView.TYPE_REQUEST_PERMISSION) {
            mySaleWebview.toCamera();// 到相机
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mySaleWebview.canGoBack()) {
                mySaleWebview.goBack();// 返回上一页面
                return true;
            } else {
                this.finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}




