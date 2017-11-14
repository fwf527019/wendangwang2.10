package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentYouHuiQuanLosed;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentYouHuiQuanNotUsed;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentYouHuiQuanUsed;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/22.
 */
public class MyYouHuiQuanActivity extends ActivityBase implements FragmentYouHuiQuanNotUsed.UseConpCallBack {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myyouhuiquan_fragment)
    FrameLayout myyouhuiquanFragment;

    FragmentYouHuiQuanNotUsed fragmentYouHuiQuanNotUsed;
    FragmentYouHuiQuanUsed fragmentYouHuiQuanUsed;
    FragmentYouHuiQuanLosed fragmentYouHuiQuanLosed;
    @BindView(R.id.youhuiquan_notuserd_tv)
    TextView youhuiquanNotuserdTv;
    @BindView(R.id.youhuiquan_userd_tv)
    TextView youhuiquanUserdTv;
    @BindView(R.id.youhuiquan_losed_tv)
    TextView youhuiquanLosedTv;
    @BindView(R.id.youhuiquan_view1)
    View youhuiquanView1;
    @BindView(R.id.youhuiquan_view2)
    View youhuiquanView2;
    @BindView(R.id.youhuiquan_view3)
    View youhuiquanView3;
    private CouponInfoData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myyouhuiquan;
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
        title.setText("我的优惠券");
        initfr1();
        initTextColor(1);
        loadDatas();
    }


    @OnClick({R.id.back, R.id.youhuiquan_notuserd_tv, R.id.youhuiquan_userd_tv, R.id.youhuiquan_losed_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.youhuiquan_notuserd_tv:
                initfr1();
                initTextColor(1);
                break;
            case R.id.youhuiquan_userd_tv:
                initfr2();
                initTextColor(2);
                break;
            case R.id.youhuiquan_losed_tv:
                initfr3();
                initTextColor(3);
                break;
        }
    }


    private void initfr1() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentYouHuiQuanNotUsed == null) {
            fragmentYouHuiQuanNotUsed = new FragmentYouHuiQuanNotUsed();
            transaction.add(R.id.myyouhuiquan_fragment, fragmentYouHuiQuanNotUsed);
        }
        hideFragment(transaction);
        transaction.show(fragmentYouHuiQuanNotUsed);
        transaction.commit();
    }

    private void initfr2() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentYouHuiQuanUsed == null) {
            fragmentYouHuiQuanUsed = new FragmentYouHuiQuanUsed();
            transaction.add(R.id.myyouhuiquan_fragment, fragmentYouHuiQuanUsed);
        }
        hideFragment(transaction);
        transaction.show(fragmentYouHuiQuanUsed);
        transaction.commit();
    }

    private void initfr3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentYouHuiQuanLosed == null) {
            fragmentYouHuiQuanLosed = new FragmentYouHuiQuanLosed();
            transaction.add(R.id.myyouhuiquan_fragment, fragmentYouHuiQuanLosed);
        }
        hideFragment(transaction);
        transaction.show(fragmentYouHuiQuanLosed);
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentYouHuiQuanNotUsed != null) {
            transaction.hide(fragmentYouHuiQuanNotUsed);
        }
        if (fragmentYouHuiQuanUsed != null) {
            transaction.hide(fragmentYouHuiQuanUsed);
        }
        if (fragmentYouHuiQuanLosed != null) {
            transaction.hide(fragmentYouHuiQuanLosed);
        }
    }

    private void loadDatas() {
        Map<String, String> map = new HashMap<>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("dataSource", "APP");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMYCOUPONINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("Aksjdkj", response);
                if (!response.startsWith(MConstant.HTTP404)) {
                    data = JSON.parseObject(response, CouponInfoData.class);
                    youhuiquanNotuserdTv.setText("未使用(" + data.getObj().get(0).size() + ")");
                    youhuiquanLosedTv.setText("已失效(" + data.getObj().get(2).size() + ")");
                    youhuiquanUserdTv.setText("已使用(" + data.getObj().get(1).size() + ")");
                }
            }
        });
    }


    private void initTextColor(int pos) {
        TextView[] views1 = {youhuiquanNotuserdTv, youhuiquanUserdTv, youhuiquanLosedTv};
        View[] views2 = {youhuiquanView1, youhuiquanView2, youhuiquanView3};
        for (int i = 0; i < 3; i++) {
            if (pos - 1 == i) {
                views1[i].setTextColor(getResources().getColor(R.color.maincolor));
                views2[i].setVisibility(View.VISIBLE);
            } else {
                views1[i].setTextColor(getResources().getColor(R.color.gry));
                views2[i].setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void sendMassege(boolean success) {
        if (success) {
            youhuiquanNotuserdTv.setText("未使用(" + (data.getObj().get(0).size() + 1) + ")");
        }
    }
}
