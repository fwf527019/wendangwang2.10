package hb.xnwdw.com.wendangwang.gui.activity;

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
 * Created by Administrator on 2017/7/14.
 */

public class JifenRulesActivity extends ActivityBase {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.jifenruls_web)
    WebView jifenrulsWeb;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_jifenrules;
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
        title.setText("积分说明");
        WebViewSetings.setWebView(jifenrulsWeb, UrlApi.SERVER_IP + "/wdw/page/mb/integral_explain.html?noticeType=积分说明&app=app", JifenRulesActivity.this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
