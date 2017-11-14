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
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.OnlineYouHuiQuanNotUsedAdapter2;
import hb.xnwdw.com.wendangwang.gui.adapter.YouHuiQuanNotUsedAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OfflineConpund;
import hb.xnwdw.com.wendangwang.netdata.entity.OnlineConpund;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderConpData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/29.
 */
public class LineCopuonActivity extends ActivityBase {
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
    private OrderConpData data;
    private OnlineYouHuiQuanNotUsedAdapter2 mAdapter;

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
       data= (OrderConpData) intent.getSerializableExtra("cdata");
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(LineCopuonActivity.this);
        youhuiquanofflineNotusedRecycler.setLayoutManager(myLinearLayoutManager);
        if(data.getDatas()!=null){
            if (data.getDatas().size()!= 0) {
                imgNodata.setVisibility(View.GONE);
                mAdapter = new OnlineYouHuiQuanNotUsedAdapter2(R.layout.item_youhuiquan_notused, data.getDatas());
                youhuiquanofflineNotusedRecycler.setAdapter(mAdapter);
                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.uesit:
                                Intent intent = new Intent();
                                intent.putExtra("couponCode",data.getDatas().get(position).getCouponCode());
                                intent.putExtra("coupId", data.getDatas().get(position).getID()+"");
                                intent.putExtra("coupName", data.getDatas().get(position).getBasic_Coupon().getCouponName());
                                intent.putExtra("coupMony", data.getDatas().get(position).getBasic_Coupon().getCouponMoney());
                                intent.putExtra("coupLimit", data.getDatas().get(position).getBasic_Coupon().getUseCondition());
                                setResult(1, intent);
                                finish();
                                break;
                        }
                        return false;
                    }
                });
            }else {
                imgNodata.setVisibility(View.VISIBLE);
            }

        }

        title.setText("选择优惠券");

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

//                mAdapter.addHeaderView(OfflineCopuonActivity.this.getLayoutInflater().inflate(R.layout.sub_youhui_header, (ViewGroup) youhuiquanofflineNotusedRecycler.getParent(), false));
//                mAdapter.addFooterView(OfflineCopuonActivity.this.getLayoutInflater().inflate(R.layout.sub_youhui_footer, (ViewGroup) youhuiquanofflineNotusedRecycler.getParent(), false));




}
