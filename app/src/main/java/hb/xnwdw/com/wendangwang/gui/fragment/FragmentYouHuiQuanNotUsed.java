package hb.xnwdw.com.wendangwang.gui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.ConpoudActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanNotUsedAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanNotUsedAdapter1;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/24.
 */
public class FragmentYouHuiQuanNotUsed extends FragmentBase {
    @BindView(R.id.youhuiquan_notused_recycler)
    RecyclerView youhuiquanNotusedRecycler;
    YouHuiQuanNotUsedAdapter1 mAdapter;
    private CouponInfoData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_youhuiquan_notused;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        loadDatas();
//
//    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    //或取
    private void loadDatas() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("dataSource", "APP");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMYCOUPONINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("Aksjdkj", response);
                if (response.startsWith("{")) {
                    data = JSON.parseObject(response, CouponInfoData.class);
                    MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getContext());
                    youhuiquanNotusedRecycler.setLayoutManager(myLinearLayoutManager);
                    mAdapter = new YouHuiQuanNotUsedAdapter1(R.layout.item_youhuiquan_notused, data.getObj().get(0));
                    youhuiquanNotusedRecycler.setAdapter(mAdapter);
                    mAdapter.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.sub_youhui_header, (ViewGroup) youhuiquanNotusedRecycler.getParent(), false));
                    mAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_youhui_footer, (ViewGroup) youhuiquanNotusedRecycler.getParent(), false));
                    final EditText edt = (EditText) mAdapter.getHeaderLayout().findViewById(R.id.getyouhuiquan_ed);
                    TextView tvbtn = (TextView) mAdapter.getHeaderLayout().findViewById(R.id.youhuiquan_ok);
                    tvbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            getConpuond(edt.getText().toString());
                        }
                    });
                    mAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(getActivity(), ConpoudActivity.class));
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "未找到页面，请登录", Toast.LENGTH_SHORT).show();
                }
                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                        Intent intent=new Intent(getActivity(),MainPagerActivity.class);
                        intent.putExtra("fr",0);
                        startActivity(intent);
                        return false;
                    }
                });

            }
        });
    }

    private UseConpCallBack useConpCallBack;

    public interface UseConpCallBack {
        public void sendMassege(boolean success);
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        //当前fragment从activity重写了回调接口  得到接口的实例化对象
        useConpCallBack = (UseConpCallBack) getActivity();
    }

    private void getConpuond(String num) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP");
        jsonObject.put("memberId", WDWApp.getMenberId());
        jsonObject.put("couponCode", num);
        String pamString = jsonObject.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_GETCOPUOND, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentYouHuiQuanNotUs", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        Toast.makeText(getActivity(), "激活成功", Toast.LENGTH_SHORT).show();
                        useConpCallBack.sendMassege(true);
                    } else {
                        Toast.makeText(getActivity(), "激活失败", Toast.LENGTH_SHORT).show();
                        useConpCallBack.sendMassege(false);
                    }
                    loadDatas();
                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        data=null;
        loadDatas();
    }
}
