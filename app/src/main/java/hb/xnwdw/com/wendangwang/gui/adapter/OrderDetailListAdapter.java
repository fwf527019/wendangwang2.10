package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderDetailData;

/**
 * Created by Administrator on 2017/5/9.
 */

public class OrderDetailListAdapter extends BaseQuickAdapter<OrderDetailData.ObjBean.OrderDetailsBean, BaseViewHolder> {
    public OrderDetailListAdapter(int layoutResId, List<OrderDetailData.ObjBean.OrderDetailsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailData.ObjBean.OrderDetailsBean item) {
        helper
                .setText(R.id.orderdetails_goods_name,item.getItemName())
                .setText(R.id.orderdetails_goods_spec,item.getNorms())
                .setText(R.id.orderdetails_goods_price,"¥"+item.getUnit())
                .setText(R.id.orderdetails_goods_num,"×"+item.getAmount());
        ((SimpleDraweeView)(helper.getView(R.id.orderdetails_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());

    }
}
