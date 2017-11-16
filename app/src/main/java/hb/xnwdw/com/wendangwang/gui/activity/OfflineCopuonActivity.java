package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanNotUsedAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OfflineConpund;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/29.
 */
public class OfflineCopuonActivity extends ActivityBase {
    @BindView(R.id.youhuiquanoffline_notused_recycler)
    RecyclerView youhuiquanofflineNotusedRecycler;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.img_nodata)
    LinearLayout imgNodata;
    private String itemId;
    private int Amount;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_offilinecopuon;
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
        itemId = intent.getStringExtra("itemId");
        Amount = intent.getIntExtra("Amount", 1);
        title.setText("选择优惠券");
        loadConpond(itemId, Amount);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    /***
     * 线下商品获取优惠券
     */
    private OfflineConpund data;
    private YouHuiQuanNotUsedAdapter mAdapter;

    private void loadConpond(String itemId, int Amount) {

      Map<String,String> map=new HashMap<>();
        map.put("itemId", itemId);
        map.put("amount", Amount+"");
        map.put("memberId", WDWApp.getMenberId());




        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetValidCoupon, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("OfflineCopuonActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OfflineCopuonActivity", response);
                if (response != null&!response.contains(MConstant.HTTP404)) {
                    data = JSON.parseObject(response, OfflineConpund.class);

                    MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(OfflineCopuonActivity.this);
                    youhuiquanofflineNotusedRecycler.setLayoutManager(myLinearLayoutManager);
                    if (data.getObj().size() != 0) {
                        imgNodata.setVisibility(View.GONE);
                        mAdapter = new YouHuiQuanNotUsedAdapter(R.layout.item_youhuiquan_notused, data.getObj());
                        youhuiquanofflineNotusedRecycler.setAdapter(mAdapter);
                        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                                Intent intent = new Intent();
                                intent.putExtra("couponCode", data.getObj().get(position).getCouponCode());
                                intent.putExtra("coupId", data.getObj().get(position).getID());
                                intent.putExtra("coupMony", data.getObj().get(position).getBasic_Coupon().getCouponMoney());
                                intent.putExtra("coupLimit", data.getObj().get(position).getBasic_Coupon().getUseCondition());

                                setResult(1, intent);
                                finish();

                            }
                        });


                    }else {
                        imgNodata.setVisibility(View.VISIBLE);
                    }


                }


//                mAdapter.addHeaderView(OfflineCopuonActivity.this.getLayoutInflater().inflate(R.layout.sub_youhui_header, (ViewGroup) youhuiquanofflineNotusedRecycler.getParent(), false));
//                mAdapter.addFooterView(OfflineCopuonActivity.this.getLayoutInflater().inflate(R.layout.sub_youhui_footer, (ViewGroup) youhuiquanofflineNotusedRecycler.getParent(), false));

            }
        });
    }
}
