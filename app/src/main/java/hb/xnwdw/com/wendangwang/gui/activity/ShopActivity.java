package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.ShopActivityListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopActivityData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ShopActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shopactivity_list)
    RecyclerView shopactivityList;
    private String storName, storId;
    private ShopActivityListAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_shopactivity;
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
        Intent intent = getIntent();
        storName = intent.getStringExtra("storeName");
        storId = intent.getStringExtra("storeId");
        title.setText(storName);
        loadShopActivityData(storId);
    }

    private void loadShopActivityData(String storId) {
        Map<String,String> map=new HashMap<>();
        map.put("storeId", storId);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETSHOPACTIVITYLIST, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("ShopActivity", response);
                final ShopActivityData data = JSON.parseObject(response, ShopActivityData.class);
                shopactivityList.setLayoutManager(new MyLinearLayoutManager(getApplicationContext()));
                adapter = new ShopActivityListAdapter(R.layout.item_shopactivity_list, data.getObj());
                adapter.addFooterView(ShopActivity.this.getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) shopactivityList.getParent(), false));
                shopactivityList.setAdapter(adapter);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent=new Intent(ShopActivity.this,ActivityDetail.class);
                        intent.putExtra("actId",data.getObj().get(position).getID()+"");
                        intent.putExtra("acTitail",data.getObj().get(position).getActivityName());

                        startActivity(intent);
                    }
                });

            }
        });
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
