package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.SaleApplyAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MysaleApplyData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/14.
 */
public class MySaleApplyFragment extends FragmentBase {
    @BindView(R.id.apply_sale_list)
    RecyclerView applySaleList;
    Unbinder unbinder;
    @BindView(R.id.myself_fr_nodata)
    LinearLayout myselfFrNodata;
    private int page = 1;

    @Override
    protected int getContentViewResId() {
        return R.layout.mysele_fr_appply;
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


        loadData();
    }


/***获取可申请售后的商品***/
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("pagesize", "20");
        map.put("memberID", WDWApp.getMenberId());
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetOrderForSaleAfter, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MySaleApplyFragment", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MySaleApplyFragment", response);
                if (!response.contains(MConstant.HTTP404)) {
                    MysaleApplyData data = JSON.parseObject(response, MysaleApplyData.class);
                    if (data.getObj() != null) {
                        if (data.getObj().getOrders() != null && data.getObj().getOrders().size() != 0) {
                            applySaleList.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                            SaleApplyAdapter adpter = new SaleApplyAdapter(R.layout.item_saleapply, data.getObj().getOrders(), getActivity());
                            applySaleList.setAdapter(adpter);
                            adpter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) applySaleList.getParent(), false));
                        }else {
                            myselfFrNodata.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void saleAfter(Integer messageEvent){
//        if (messageEvent == 11) {
//            applySaleList.removeAllViews();
//            applySaleList.clearFocus();
//          loadData();
//        }
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
