package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;

/**
 * Created by Administrator on 2017/3/24.
 */
public class YouHuiQuanUsedAdapter extends BaseQuickAdapter<CouponInfoData.ObjBean, BaseViewHolder> {
    public YouHuiQuanUsedAdapter(int layoutResId, List<CouponInfoData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfoData.ObjBean item) {
        if (item != null && item.getCouponStatus() == 0) {
            helper.setText(R.id.youhuiquannotused_item_price, "¥"+item.getBasic_Coupon().getCouponMoney())
                    .setText(R.id.youhuiquannotused_name, item.getBasic_Coupon().getCouponName())
                    .setText(R.id.youhuiquannotused_item_usetime, item.getBasic_Coupon().getValidStartTime().substring(0,10) + "至" + item.getBasic_Coupon().getValidEndTime().substring(0,10))
                    .setText(R.id.youhuiquannotused_item_condition, "满" + item.getBasic_Coupon().getUseCondition() + "元使用")
                    .addOnClickListener(R.id.uesit);
            }

        ((RelativeLayout)(helper.getView(R.id.item_youhuiquan_relayout))).setBackgroundResource(R.drawable.used);
        ((TextView)(helper.getView(R.id.uesit))).setVisibility(View.INVISIBLE);
        }
}


//    @Override
//    protected void convert(BaseViewHolder holder, YouHuiQuanBean item) {
//
//        holder.setText(R.id.youhuiquannotused_item_price,item.getPrice())
//                .setText(R.id.youhuiquannotused_name,item.getName())
//                .setText(R.id.youhuiquannotused_item_usetime,item.getTime())
//                .setText(R.id.youhuiquannotused_item_condition,item.getUseCont())
//             //  .setText(R.id.youhuiquannotused_item_uscont,item.getUseConten())
//                .addOnClickListener(R.id.uesit);
//
//        ((TextView)(holder.getView(R.id.youhuiquannotused_item_uscont))).setText(item.getUseConten());
//
//    }



