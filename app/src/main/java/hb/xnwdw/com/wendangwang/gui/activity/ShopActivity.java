package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ShopActivity extends ActivityBase {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.shopactivity_list)
    RecyclerView shopactivityList;

    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.right_img)
    ImageView rightImg;
    private String storName, storId,storeAddress;
    private ShopActivityListAdapter adapter;
    private double lat,lot,mlat,mlot;
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
        storeAddress = intent.getStringExtra("storeAddress");
        storId = intent.getStringExtra("storeId");
        lat = intent.getDoubleExtra("lat", 0);
        lot = intent.getDoubleExtra("lot", 0);
        mlat = intent.getDoubleExtra("mlat", 0);
        mlot = intent.getDoubleExtra("mlot", 0);


        title.setText(storName);
        loadShopActivityData(storId);
    }

    private void loadShopActivityData(String storId) {
        Map<String, String> map = new HashMap<>();
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
                        Intent intent = new Intent(ShopActivity.this, ActivityDetail.class);
                        intent.putExtra("actId", data.getObj().get(position).getID() + "");
                        intent.putExtra("acTitail", data.getObj().get(position).getActivityName());

                        startActivity(intent);
                    }
                });

            }
        });
    }


    @OnClick({R.id.back, R.id.right_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.right_img:
                Intent intent=new Intent(this,MapActivity.class);
                intent.putExtra("lat",lat);
                intent.putExtra("lot",lot);
                intent.putExtra("mlat",mlat);
                intent.putExtra("mlot",mlot);
                intent.putExtra("name",storName);
                intent.putExtra("storeAddress",storeAddress);
                startActivity(intent);
                break;
        }
    }
}
