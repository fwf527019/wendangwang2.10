package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.entity.RegestConpondData;

/**
 * Created by Administrator on 2017/7/24.
 */
public class RegestConpoud extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.regest_conpoundlist)
    RecyclerView regestConpoundlist;
    private RegestConpondData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_regestconpound;
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
        title.setText("注册获得的优惠券");
        data = (RegestConpondData) intent.getSerializableExtra("regestData");
        if (data.getObj() != null&&data.getObj().size()!=0) {
            LinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(RegestConpoud.this);
            regestConpoundlist.setLayoutManager(linearLayoutManager);
            Adpter adpter = new Adpter(R.layout.item_youhuiquan_notused, data.getObj().subList(0,data.getObj().size()-1));
            regestConpoundlist.setAdapter(adpter);
            adpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    startActivity(new Intent(RegestConpoud.this,MainPagerActivity.class).putExtra("fr",0));
                }
            });
        }else {

        }


    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        startActivity(new Intent(RegestConpoud.this,MainPagerActivity.class).putExtra("fr",4));
        finish();
    }

    private class Adpter extends BaseQuickAdapter<RegestConpondData.ObjBean, BaseViewHolder> {
        public Adpter(int layoutResId, List<RegestConpondData.ObjBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, RegestConpondData.ObjBean item) {

                helper.setText(R.id.youhuiquannotused_item_price, item.getCouponMoney()+"")
                        .setText(R.id.youhuiquannotused_name, item.getCouponName())
                        .setText(R.id.youhuiquannotused_item_usetime, item.getValidStartTime().substring(0, 10) + "至" + item.getValidEndTime().substring(0, 10))
                       .setText(R.id.youhuiquannotused_item_condition, "满" + item.getUseCondition() + "元使用")

                        .addOnClickListener(R.id.uesit);
            if(item.getCouponLimit()!=null){
                helper.setText(R.id.youhuiquannotused_item_classfay,"限"+item.getCouponLimit());
            }else {
                helper.setText(R.id.youhuiquannotused_item_classfay,"全部类商品");
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK){
            startActivity(new Intent(RegestConpoud.this,MainPagerActivity.class).putExtra("fr",4));
        }
        return super.onKeyDown(keyCode, event);
    }
}
