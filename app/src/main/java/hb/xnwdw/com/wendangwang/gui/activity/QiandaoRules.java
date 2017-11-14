package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/8/2.
 */

public class QiandaoRules extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.qiandao_rules)
    WebView qiandaoRules;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_qiandaorules;
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
        title.setText("签到规则");
       String url= UrlApi.SERVER_IP+"/wdw/page/mb/integral_explain.html?noticeType=签到说明&app=app";
        WebViewSetings.setWebView(qiandaoRules,url,getApplicationContext());

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
