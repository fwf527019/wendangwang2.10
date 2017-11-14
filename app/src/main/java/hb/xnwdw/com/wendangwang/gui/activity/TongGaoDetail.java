package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/7/10.
 */
public class TongGaoDetail extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.ad_web)
    WebView adWeb;
    private String id;
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_adactivity;
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
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        String titles=intent.getStringExtra("titles");
        title.setText(titles);
        WebViewSetings.setWebView(adWeb, UrlApi.SERVER_IP+"/wdw/page/mb/news_detail.html?t=gg&id="+id+"&app=app",getApplicationContext());
    }
    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
