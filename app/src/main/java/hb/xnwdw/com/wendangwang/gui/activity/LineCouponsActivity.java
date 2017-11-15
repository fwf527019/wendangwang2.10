package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

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

    @BindView(R.id.line_couponds)
    RecyclerView lineCouponds;
    @BindView(R.id.can_uesecounpons_tv)
    TextView canUesecounponsTv;
    @BindView(R.id.can_uesecounpons_ll)
    LinearLayout canUesecounponsLl;
    @BindView(R.id.cannot_uesecounpons_tv)
    TextView cannotUesecounponsTv;
    @BindView(R.id.cannot_uesecounpons_ll)
    LinearLayout cannotUesecounponsLl;
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

        title.setText("选择优惠券");
        showCanUseCounpoan();
        initTetxtColor(0);
        canUesecounponsTv.setText("可用优惠券(" + data.getDatas().size() + ")");
        cannotUesecounponsTv.setText("不可用优惠券(" + canNotUsedata.getDatas().size() + ")");
    }

    @OnClick({R.id.back, R.id.can_uesecounpons_ll, R.id.cannot_uesecounpons_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.can_uesecounpons_ll:
                showCanUseCounpoan();
                initTetxtColor(0);
                break;
            case R.id.cannot_uesecounpons_ll:
                showCanNotUseCounpoan();
                initTetxtColor(1);
                break;
        }
    }

    /**
     * 可使用优惠券
     */
    private void showCanUseCounpoan() {
        if (data.getDatas() != null) {

            lineCouponds.setLayoutManager(new LinearLayoutManager(LineCouponsActivity.this));
            mAdapter = new OnlineYouHuiQuanNotUsedAdapter2(R.layout.item_youhuiquan_notused, data.getDatas());
            lineCouponds.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent intent = new Intent();
                    intent.putExtra("couponCode", data.getDatas().get(position).getCouponCode());
                    intent.putExtra("coupId", data.getDatas().get(position).getID() + "");
                    intent.putExtra("coupName", data.getDatas().get(position).getBasic_Coupon().getCouponName());
                    intent.putExtra("coupMony", data.getDatas().get(position).getBasic_Coupon().getCouponMoney());
                    intent.putExtra("coupLimit", data.getDatas().get(position).getBasic_Coupon().getUseCondition());
                    setResult(1, intent);
                    finish();
                }
            });
        }


    }

    /**
     * 不可用优惠券
     */
    private void showCanNotUseCounpoan() {
        if (canNotUsedata.getDatas() != null) {

            lineCouponds.setLayoutManager(new LinearLayoutManager(LineCouponsActivity.this));
            mAdapter2 = new OnlineYouHuiQuanNotUsedAdapter3(R.layout.item_youhuiquan_notused1, canNotUsedata.getDatas());
            lineCouponds.setAdapter(mAdapter2);

        }

    }

    /**
     * 字体背景初始化 变化
     *
     * @param pos
     */

    private void initTetxtColor(int pos) {
        List<LinearLayout> linerLayouts = new ArrayList<>();
        linerLayouts.add(canUesecounponsLl);
        linerLayouts.add(cannotUesecounponsLl);

        List<TextView> textViews = new ArrayList<>();
        textViews.add(canUesecounponsTv);
        textViews.add(cannotUesecounponsTv);

        for (int i = 0; i < 2; i++) {
            if (pos == i) {
                linerLayouts.get(i).setBackgroundResource(R.drawable.kuang_main);
                textViews.get(i).setTextColor(getResources().getColor(R.color.white));
            } else {
                linerLayouts.get(i).setBackgroundResource(R.drawable.line_kuang);
                textViews.get(i).setTextColor(getResources().getColor(R.color.maincolor));

            }


        }


    }
}
