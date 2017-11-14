package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.WaitForEvaluateAdpter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.WaitForEvaluateData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FragmentWaitForEvaluat extends FragmentBase {
    @BindView(R.id.waitfor_evaluat_list)
    RecyclerView waitforEvaluatList;
    Unbinder unbinder;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_waitfor_evaluat;
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
        loadData(null);

    }

    private void loadData(String IDsOrderNum) {
        Map<String,String> map=new HashMap<>();
        map.put("iPage","1");
        map.put("iPageSize","8");
        map.put("sMemberID","0");
        map.put("IDsOrderNum",IDsOrderNum);

        HtttpRequest.CreatGetRequst(UrlApi.URL_GetNoEvaluatOrder, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentWaitForEvaluat", response);
                if(!response.contains(MConstant.HTTP404)){
                    WaitForEvaluateData data= JSON.parseObject(response,WaitForEvaluateData.class);
                    waitforEvaluatList.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                    WaitForEvaluateAdpter adapter=new WaitForEvaluateAdpter(R.layout.item_waitfor_evaluate,data.getObj().getOrders(),getActivity());
                    waitforEvaluatList.setAdapter(adapter);
                }

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }





}
