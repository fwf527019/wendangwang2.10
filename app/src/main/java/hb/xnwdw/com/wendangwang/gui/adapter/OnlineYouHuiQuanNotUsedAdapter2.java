package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderConpData;

/**
 * Created by Administrator on 2017/3/24.
 */
public class OnlineYouHuiQuanNotUsedAdapter2 extends BaseQuickAdapter<OrderConpData.DatasBean, BaseViewHolder> {
    public OnlineYouHuiQuanNotUsedAdapter2(int layoutResId, List<OrderConpData.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderConpData.DatasBean item) {
//        if (item != null && item.getCouponStatus() == 0) {
//            helper.setText(R.id.youhuiquannotused_item_price, item.getBasic_Coupon().getCouponMoney()+"")
//                    .setText(R.id.youhuiquannotused_name, item.getBasic_Coupon().getCouponName())
//                    .setText(R.id.youhuiquannotused_item_usetime, item.getBasic_Coupon().getValidStartTime().substring(0,10) + "至" + item.getBasic_Coupon().getValidEndTime().substring(0,10))
//                    .setText(R.id.youhuiquannotused_item_condition, "满" + item.getBasic_Coupon().getUseCondition() + "元使用")
//                    .addOnClickListener(R.id.uesit);
//        }
//    }


    if (item != null && item.getCouponStatus() == 0) {
        helper.setText(R.id.youhuiquannotused_item_price, item.getBasic_Coupon().getCouponMoney()+"")
                .setText(R.id.youhuiquannotused_name, item.getBasic_Coupon().getCouponName())
                .setText(R.id.youhuiquannotused_item_usetime, item.getBasic_Coupon().getValidStartTime().substring(0,10) + "至" + item.getBasic_Coupon().getValidEndTime().substring(0,10))
                .setText(R.id.youhuiquannotused_item_condition, "满" + item.getBasic_Coupon().getUseCondition() + "元使用")
                .addOnClickListener(R.id.uesit);
        if(item.getBasic_Coupon().getCouponLimit().size()!=0){
            String str = "";
            for (int i = 0; i <item.getBasic_Coupon().getCouponLimit().size() ; i++) {
                str+=item.getBasic_Coupon().getCouponLimit().get(i).getCateName()+",";
            }
            int l=str.length();
            helper.setText(R.id.youhuiquannotused_item_limit, "限"+  str.substring(0,l-1)+"使用");
        }else {
            helper.setText(R.id.youhuiquannotused_item_limit, "全品类商品");
        }
    }
}
}






