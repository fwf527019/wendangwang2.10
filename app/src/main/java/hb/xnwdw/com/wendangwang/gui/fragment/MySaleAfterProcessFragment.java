package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.MySaleDetail;
import hb.xnwdw.com.wendangwang.gui.adapter.ProcessAdapter;
import hb.xnwdw.com.wendangwang.gui.view.MyPopWindow;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ProcessData;
import hb.xnwdw.com.wendangwang.netdata.entity.SaleDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/14.
 */
public class MySaleAfterProcessFragment extends FragmentBase {
    @BindView(R.id.after_process_recycler)
    RecyclerView afterProcessRecycler;
    Unbinder unbinder;
    @BindView(R.id.myself_afterprocess_nodata)
    LinearLayout myselfAfterprocessNodata;
    private int page = 1;
    private ProcessData data;
    private ProcessAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.mysele_fr_afterprocess;
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
        loaddata();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void loaddata() {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("pagesize", 20 + "");
        map.put("memberID", WDWApp.getMenberId());
        // 1代表未处理完成，-1 查询全部，其他代表查询已处理完成的售后
        map.put("isExecute", 0 + "");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetSaleAfterRecords, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MySaleAfterProcessFragm", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MySaleAfterProcessFragment", response);
                afterProcessRecycler.setLayoutManager(new MyLinearLayoutManager(getActivity()));

                data = JSON.parseObject(response, ProcessData.class);
                if (data.getObj() != null && data.getObj().size() != 0) {


                    adapter = new ProcessAdapter(R.layout.item_process_list, data.getObj(), 1);
                    afterProcessRecycler.setAdapter(adapter);


                    adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public boolean onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {

                            switch (view.getId()) {
                                case R.id.item_process_btn1:

                                    break;
                                case R.id.item_process_btn2:
                                   MyPopWindow myPopWindow=new MyPopWindow(getActivity(),R.layout.popupwindow_cart,"取消","确认");
                                    myPopWindow.setRightClick(new MyPopWindow.RightClick() {
                                        @Override
                                        public void rightClickListerner() {
                                            CancelApply(data.getObj().get(position).getID() + "", new StringCallback() {
                                                @Override
                                                public void onError(Call call, Exception e, int id) {

                                                }

                                                @Override
                                                public void onResponse(String response, int id) {
                                                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                                                        Toast.makeText(getActivity(), "撤销成功", Toast.LENGTH_SHORT).show();
                                                        adapter.notifyItemRemoved(position);
                                                    } else {
                                                        Toast.makeText(getActivity(), JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });

                                    break;
                            }
                            return false;

                        }
                    });


                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {
                            loadSaleContent(data.getObj().get(position).getID() + "", new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    SaleDetailData datas = JSON.parseObject(response, SaleDetailData.class);
                                    if (datas.getObj().size() == 0) {
                                        Toast.makeText(getActivity(), "暂无处理结果", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Intent intent = new Intent(getActivity(), MySaleDetail.class);
                                        intent.putExtra("saleId", data.getObj().get(position).getID() + "");
                                        intent.putExtra("saleAfterNum", data.getObj().get(position).getAfterNum());
                                        intent.putExtra("saleType", data.getObj().get(position).getSaleAfterType());
                                        intent.putExtra("saleName", data.getObj().get(position).getItemName());
                                        intent.putExtra("salePic", data.getObj().get(position).getItemPic());
                                        intent.putExtra("saleSizi", data.getObj().get(position).getItemSize());
                                        intent.putExtra("saleNum", data.getObj().get(position).getCounts() + "");
                                        intent.putExtra("saleStat", data.getObj().get(position).getState());
                                        startActivity(intent);
                                    }

                                }
                            });

                        }
                    });
                    adapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) afterProcessRecycler.getParent(), false));
                } else {
                    myselfAfterprocessNodata.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void loadSaleContent(String id, StringCallback stringCallback) {
        Map<String, String> map = new HashMap<>();
        map.put("iSaleAfterID", id);
        map.put("memberID", "hehhe");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetDetails, map, stringCallback);


    }

    /**
     * 取消售后
     *
     * @param saleAfterID
     * @param
     */
    private void CancelApply(String saleAfterID, StringCallback callback) {

        JSONObject jsobj = new JSONObject();
        jsobj.put("saleAfterID", saleAfterID);
        jsobj.put("memberID", WDWApp.getMenberId());

        HtttpRequest.CreatPostRequst(UrlApi.URL_CancelApply, jsobj.toJSONString(), callback);
    }


    @Override
    public void onResume() {
        super.onResume();
        if(data!=null&&data.getObj()!=null&&data.getObj().size()!=0){
            data.getObj().clear();
            adapter.notifyDataSetChanged();
        }
        loaddata();
    }
}
