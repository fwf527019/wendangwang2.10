package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/6/26.
 */
public class ActivityDetail extends ActivityBase {


    @BindView(R.id.activitydetail_web)
    WebView activitydetailWeb;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_activitydatail;
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
        String titail = intent.getStringExtra("acTitail");
        title.setText(titail);
        String id = intent.getStringExtra("actId");
        WebViewSetings.synCookies(this, UrlApi.SERVER_IP + "/wdw/page/mb/activity_deatail.html?actId=" + id + "&app=app");
        WebViewSetings.setWebView(activitydetailWeb, UrlApi.SERVER_IP + "/wdw/page/mb/activity_deatail.html?actId=" + id + "&app=app", this);

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
