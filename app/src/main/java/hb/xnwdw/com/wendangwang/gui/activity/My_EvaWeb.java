package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/6/21.
 */

public class My_EvaWeb extends ActivityBase {

    @BindView(R.id.my_eva_web)
    WebView myEvaWeb;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myeva;
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

      //  WebViewSetings.synCookies(getApplicationContext(),);
    }


}
