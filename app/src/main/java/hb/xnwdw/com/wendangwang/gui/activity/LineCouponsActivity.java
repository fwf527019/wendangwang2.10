package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.OnlineYouHuiQuanNotUsedAdapter2;
import hb.xnwdw.com.wendangwang.gui.adapter.OnlineYouHuiQuanNotUsedAdapter3;
import hb.xnwdw.com.wendangwang.netdata.entity.LineCanNotCouponsData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderConpData;

/**
 * Created by Administrator on 2017/11/14.
 */

public class LineCouponsActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.can_uesecounpons)
    LinearLayout canUesecounpons;
    @BindView(R.id.cannot_uesecounpons)
    LinearLayout cannotUesecounpons;
    @BindView(R.id.line_couponds)
    RecyclerView lineCouponds;
    private OrderConpData data;
    private LineCanNotCouponsData canNotUsedata;
    private OnlineYouHuiQuanNotUsedAdapter2 mAdapter;
    private OnlineYouHuiQuanNotUsedAdapter3 mAdapter2;
    @Override
    protected int getContentViewResId() {
        return R.layout.activit_linecounpons;
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
        data = (OrderConpData) intent.getSerializableExtra("cdata");
        canNotUsedata = (LineCanNotCouponsData) intent.getSerializableExtra("cdata2");

        showCanUseCounpoan();
    }

    @OnClick({R.id.back, R.id.can_uesecounpons, R.id.cannot_uesecounpons})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.can_uesecounpons:
                showCanUseCounpoan();
                break;
            case R.id.cannot_uesecounpons:
                showCanNotUseCounpoan();
                break;
        }
    }

    private void showCanUseCounpoan() {
        if (data.getDatas() != null) {
            if (data.getDatas().size() != 0) {
                lineCouponds.setLayoutManager(new LinearLayoutManager(LineCouponsActivity.this));
                mAdapter = new OnlineYouHuiQuanNotUsedAdapter2(R.layout.item_youhuiquan_notused, data.getDatas());
                lineCouponds.setAdapter(mAdapter);
                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.uesit:
                                Intent intent = new Intent();
                                intent.putExtra("couponCode", data.getDatas().get(position).getCouponCode());
                                intent.putExtra("coupId", data.getDatas().get(position).getID() + "");
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
            } else {

            }
        }

    }

    private void showCanNotUseCounpoan() {
        if (canNotUsedata.getDatas() != null) {
            if (canNotUsedata.getDatas().size() != 0) {
                lineCouponds.setLayoutManager(new LinearLayoutManager(LineCouponsActivity.this));
                mAdapter2 = new OnlineYouHuiQuanNotUsedAdapter3(R.layout.item_youhuiquan_notused1, canNotUsedata.getDatas());
                lineCouponds.setAdapter(mAdapter2);
//                mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                    @Override
//                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                        switch (view.getId()) {
//                            case R.id.uesit:
//                                Intent intent = new Intent();
//                                intent.putExtra("couponCode", canNotUsedata.getDatas().get(position).getCouponCode());
//                                intent.putExtra("coupId", canNotUsedata.getDatas().get(position).getID() + "");
//                                intent.putExtra("coupName", canNotUsedata.getDatas().get(position).getBasic_Coupon().getCouponName());
//                                intent.putExtra("coupMony", canNotUsedata.getDatas().get(position).getBasic_Coupon().getCouponMoney());
//                                intent.putExtra("coupLimit", canNotUsedata.getDatas().get(position).getBasic_Coupon().getUseCondition());
//                                setResult(1, intent);
//                                finish();
//                                break;
//                        }
//                        return false;
//                    }
//                });
            } else {

            }
        }
    }

}
