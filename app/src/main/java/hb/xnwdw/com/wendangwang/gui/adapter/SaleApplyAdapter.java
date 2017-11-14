package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.ApplyCustomerServiceActivity;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MysaleApplyData;

/**
 * Created by Administrator on 2017/8/14.
 */
public class SaleApplyAdapter extends BaseQuickAdapter<MysaleApplyData.ObjBean.OrdersBean,BaseViewHolder>{
private Context contxt;
    public SaleApplyAdapter(int layoutResId, List<MysaleApplyData.ObjBean.OrdersBean> data, Context contxt) {
        super(layoutResId, data);
        this.contxt=contxt;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MysaleApplyData.ObjBean.OrdersBean item) {
        helper
                .setText(R.id.item_saleapply_ordernum,"订单号:"+item.getOrderNumber())
                .setText(R.id.item_saleapply_time,"交易时间:"+item.getOrderDate());

        RecyclerView recyclerview= (RecyclerView)( helper.getView(R.id.item_apply_list));
        recyclerview.setLayoutManager(new MyLinearLayoutManager(contxt));
        SaleApplyListAdapter   adapter=new SaleApplyListAdapter(R.layout.item_apply_list,item.getItems());
        recyclerview.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent in=new Intent(contxt, ApplyCustomerServiceActivity.class);
                in.putExtra("itemID",item.getItems().get(position).getItemID());
                in.putExtra("orderNum",item.getOrderNumber());
                contxt.startActivity(in);
                return false;
            }
        });
    }

    private class SaleApplyListAdapter extends BaseQuickAdapter<MysaleApplyData.ObjBean.OrdersBean.ItemsBean,BaseViewHolder>{

        public SaleApplyListAdapter(int layoutResId, List<MysaleApplyData.ObjBean.OrdersBean.ItemsBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MysaleApplyData.ObjBean.OrdersBean.ItemsBean item) {
            helper
                    .setText(R.id.my_saleapply_name,item.getItemName())
                    .setText(R.id.my_saleapply_price,"¥"+item.getUnit())
                    .setText(R.id.my_saleapply_memo,item.getItemSize())
                    .addOnClickListener(R.id.apply_sale_btn)
                    .setText(R.id.my_saleapply_num,"×"+item.getAmount());
            ((SimpleDraweeView)(helper.getView(R.id.my_saleapply_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
        }
    }
}
