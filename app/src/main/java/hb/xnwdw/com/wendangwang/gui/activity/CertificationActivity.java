package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentAfterCertification;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentCertification;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentCertificationIsGoing;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CertfcationInfoData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.Base64;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.StringUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/11.
 */

public class CertificationActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.certification_fr)
    FrameLayout certificationFr;
    @BindView(R.id.title)
    TextView title;
    private FragmentAfterCertification fragmentAfterCertification;
    private FragmentCertification fragmentCertification;
    private FragmentCertificationIsGoing fragmentCertificationIsGoing;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_cetif;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        title.setText("实名认证");
        Intent intent=getIntent();
        String fr=intent.getStringExtra("fr");
        initfr1();
//        if(fr.equals("未认证")){
//            initfr1();
//        }else {
//            initfr2();
//        }


    }



    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentAfterCertification != null) {
            transaction.hide(fragmentAfterCertification);
        }
        if (fragmentCertificationIsGoing != null) {
            transaction.hide(fragmentCertificationIsGoing);
        }
        if (fragmentCertification != null) {
            transaction.hide(fragmentCertification);
        }
    }


    private void initfr3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentAfterCertification == null) {
            fragmentAfterCertification = new FragmentAfterCertification();
            transaction.add(R.id.certification_fr, fragmentAfterCertification);
        }
        hideFragment(transaction);
        transaction.show(fragmentAfterCertification);
        transaction.commit();
    }

    private void initfr1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentCertification == null) {
            fragmentCertification = new FragmentCertification();
            transaction.add(R.id.certification_fr, fragmentCertification);
        }
        hideFragment(transaction);
        transaction.show(fragmentCertification);
        transaction.commit();
    }

    private void initfr2() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentCertificationIsGoing == null) {
            fragmentCertificationIsGoing = new FragmentCertificationIsGoing();
            transaction.add(R.id.certification_fr, fragmentCertificationIsGoing);
        }
        hideFragment(transaction);
        transaction.show(fragmentCertificationIsGoing);
        transaction.commit();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }



}
