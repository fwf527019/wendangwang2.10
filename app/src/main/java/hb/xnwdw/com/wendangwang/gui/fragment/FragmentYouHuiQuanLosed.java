package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanNotUsedAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanOverTimeAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/24.
 */
public class FragmentYouHuiQuanLosed extends FragmentBase {
    @BindView(R.id.youhuiquan_overtime_recycler)
    RecyclerView youhuiquanOvertimeRecycler;
    Unbinder unbinder;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_youhuiquan_overtime;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDatas();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private YouHuiQuanOverTimeAdapter mAdapter;

    private CouponInfoData data;

    private void loadDatas() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("dataSource", "APP");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMYCOUPONINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                {
                    LogUtils.d("Aksjdkj", response);
                    if (response != null) {
                        data = JSON.parseObject(response, CouponInfoData.class);
                        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getContext());
                        youhuiquanOvertimeRecycler.setLayoutManager(myLinearLayoutManager);
                        mAdapter = new YouHuiQuanOverTimeAdapter(R.layout.item_youhuiquan_notused, data.getObj().get(2));
                        youhuiquanOvertimeRecycler.setAdapter(mAdapter);
                    }
                }
            }
        });
    }
}
