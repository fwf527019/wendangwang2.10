package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.AllBrandAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.BrandDetaiAllGoodsAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.BrandDetaiHotRecomendeAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ListA_ZAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AllBrandData;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandDeatailData;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandDetailHotRecomendData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/21.
 */

public class BrandDetails extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.brandetail_adimg)
    SimpleDraweeView brandetailAdimg;
    @BindView(R.id.brandetail_recomend_recycler)
    RecyclerView brandetailRecomendRecycler;
    @BindView(R.id.brandetail_allgoods_recycler)
    RecyclerView brandetailAllgoodsRecycler;
    private String brandId, brandName, picUrl;
    private BrandDetaiAllGoodsAdapter brandDetaiAllGoodsAdapter;
    private BrandDetaiHotRecomendeAdapter brandDetaiHotRecomendeAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_branddetails;
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
        brandId = intent.getStringExtra("brandId");
        brandName = intent.getStringExtra("name");
        picUrl = intent.getStringExtra("pic");
       title.setText(brandName);
        LoadBrandGoodsData(brandId);
       brandetailAdimg.setImageURI(picUrl);
        LoadHotRecomendData(brandId);
        initAllDatas(brandId);
    }

    /**
     * 热卖商品
     *
     * @param brandId
     */
    private void LoadHotRecomendData(String brandId) {

        OkHttpUtils
                .get()
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .addParams("brandId", brandId)
                .url(UrlApi.URL_GETHOTRECOMNED)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("BrandDetails__hot", response);
                        if (!response.equals("null") && response.startsWith("{")) {
                            final BrandDetailHotRecomendData data = JSON.parseObject(response, BrandDetailHotRecomendData.class);
                            MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getApplicationContext());
                            myLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            brandetailRecomendRecycler.setLayoutManager(myLinearLayoutManager);
                            brandetailRecomendRecycler.setNestedScrollingEnabled(false);
                            brandDetaiHotRecomendeAdapter = new BrandDetaiHotRecomendeAdapter(R.layout.item_goods, data.getObj());
                            brandetailRecomendRecycler.setAdapter(brandDetaiHotRecomendeAdapter);
                            brandDetaiHotRecomendeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getApplicationContext(),GoodsDetails1.class);
                                    intent.putExtra("itemId", data.getObj().get(position).getID() + "");
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                });

    }

    /**
     * 根据brandId
     *
     * @param brandId 获取品牌详情
     */
    private void LoadBrandGoodsData(String brandId) {
        Map<String, String> map = new HashMap<>();
        map.put("brandId", brandId);

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETBRANDDETAIL, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("BrandDetails", response);
                if(response.contains(MConstant.HTTP404)){
                    Toast.makeText(BrandDetails.this, "网络错误", Toast.LENGTH_SHORT).show();
                }else {
                    final BrandDeatailData data = JSON.parseObject(response, BrandDeatailData.class);
                    GridLayoutManager linearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    brandetailAllgoodsRecycler.setLayoutManager(linearLayoutManager);
                    brandDetaiAllGoodsAdapter = new BrandDetaiAllGoodsAdapter(R.layout.item_shopcat_historygoods, data.getObj());
                    brandetailAllgoodsRecycler.setNestedScrollingEnabled(false);
                    brandetailAllgoodsRecycler.setAdapter(brandDetaiAllGoodsAdapter);
                    brandDetaiAllGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getApplicationContext(),GoodsDetails1.class);
                            intent.putExtra("itemId", data.getObj().get(position).getID() + "");
                            startActivity(intent);
                        }
                    });
                }
            }
        });

    }


    @OnClick({R.id.back, R.id.title})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
                break;
        }
    }

    /**
     * 请求所有品牌数据
     */
    private void initAllDatas(final String brandId) {
        HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_GETALLBRANDINFO, null, new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("BrandDetails", response);
                AllBrandData allBrandData = JSON.parseObject(response, AllBrandData.class);
                for (int i = 0; i <allBrandData.getObj().size() ; i++) {
                    if((allBrandData.getObj().get(i).getID()+"").equals(brandId)){
                        title.setText(allBrandData.getObj().get(i).getBrandName());
                        brandetailAdimg.setImageURI(UrlApi.SERVER_IP+allBrandData.getObj().get(i).getBrandPic());
                    }
                }

            }
        });
    }
}