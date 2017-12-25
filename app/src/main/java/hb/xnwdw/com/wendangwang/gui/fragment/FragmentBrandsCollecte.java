package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.BrandDetails;
import hb.xnwdw.com.wendangwang.gui.adapter.CollecteBrandsAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.JDViewAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CollectBrandsData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/12/11.
 */

public class FragmentBrandsCollecte extends FragmentBase {
    @BindView(R.id.no_collecets)
    LinearLayout noCollecets;
    @BindView(R.id.my_brands_recycler)
    RecyclerView myBrandsRecycler;
    @BindView(R.id.mycollects_delete)
    TextView mycollectsDelete;
    Unbinder unbinder;
    private CollecteBrandsAdapter adapter;

    private boolean idEditor = false;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_brands_collectes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        loadData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.mycollects_delete)
    public void onViewClicked() {
        delecteBrands();
    }


    /***
     * 获取收藏品牌列表
     */
    int pageIndex = 1;
    CollectBrandsData collectData;

    private void loadData() {
//        {iPageIndex:1,iPageSize:5}
        Map<String, String> map = new HashMap<>();
        map.put("iPageIndex", pageIndex + "");
        map.put("iPageSize", "8");
        HtttpRequest.CheackIsLoginGet(getActivity(), UrlApi.URL_GetMyCollectBrand, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("FragmentBrandsCollecte", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("FragmentBrandsCollecte", response);
                collectData = JSON.parseObject(response, CollectBrandsData.class);
                myBrandsRecycler.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                adapter = new CollecteBrandsAdapter(R.layout.item_collect_brand, collectData.getObj());
                myBrandsRecycler.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (idEditor) {
                            if (collectData.getObj().get(position).isCollect()) {
                                collectData.getObj().get(position).setCollect(false);
                            } else {
                                collectData.getObj().get(position).setCollect(true);
                            }
                            adapter.notifyItemChanged(position);
                        } else {
                            Intent intent = new Intent(getActivity(), BrandDetails.class);
                            intent.putExtra("brandId", collectData.getObj().get(position).getID());
                            intent.putExtra("name", collectData.getObj().get(position).getBrandName());
                            intent.putExtra("pic", collectData.getObj().get(position).getBrandPic());
                            startActivity(intent);
                        }
                    }
                });
            }
        });


    }


    /**
     * 删除品牌
     */
    private void delecteBrands() {
        startProgressDialog("正在删除...");
        String str;
        final JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < collectData.getObj().size(); i++) {
            if (collectData.getObj().get(i).isCollect()) {
                jsonArray.add(collectData.getObj().get(i).getID());
            }
        }
        str = jsonArray.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this.getActivity(), UrlApi.URL_BtachCancelCollectBrand, str, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("FragmentBrandsCollecte", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                Log.d("FragmentBrandsCollecte", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                    for (int i = collectData.getObj().size() - 1; i >= 0; i--) {
                        if (collectData.getObj().get(i).isCollect()) {
                            collectData.getObj().remove(i);
                        }
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), "删除失败"+JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEdtInfo(String event) {
        String msg = "onEventMainThread收到了消息：" + event;
        Log.d("harvic", msg);
        if (event.equals("finish")) {
            mycollectsDelete.setVisibility(View.VISIBLE);
            adapter.setEdt(true);
            adapter.notifyDataSetChanged();
            idEditor = true;
        } else if (event.equals("editor")) {
            mycollectsDelete.setVisibility(View.GONE);
            adapter.setEdt(false);
            idEditor = false;
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
