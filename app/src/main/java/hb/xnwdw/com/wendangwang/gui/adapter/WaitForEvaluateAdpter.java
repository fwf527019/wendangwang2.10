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
import hb.xnwdw.com.wendangwang.gui.activity.PublishEvaluateActivity;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.WaitForEvaluateData;

/**
 * Created by Administrator on 2017/7/20.
 */

public class WaitForEvaluateAdpter extends BaseQuickAdapter<WaitForEvaluateData.ObjBean.OrdersBean, BaseViewHolder> {
    private Context context;
    public WaitForEvaluateAdpter(int layoutResId, List<WaitForEvaluateData.ObjBean.OrdersBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final WaitForEvaluateData.ObjBean.OrdersBean item) {
        helper
              .setText(R.id.waitfor_evaluat_ordernum,"订单号："+item.getOrderNumber())
                .setText(R.id.waitfor_evaluat_time,"交易时间："+item.getOrderDate());
        RecyclerView itemlist=  ((RecyclerView) (helper.getView(R.id.waitfor_evaluat_itemlist)));
        itemlist.setLayoutManager(new MyLinearLayoutManager(context));
        EvaluatItemAdapter adapter=   new EvaluatItemAdapter(R.layout.item_goods_evaluate,item.getItems());
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(context, PublishEvaluateActivity.class);
                intent.putExtra("iItemID",item.getItems().get(position).getItemID());
                intent.putExtra("iOrderID",item.getOrderNumber());
                context.startActivity(intent);
                return false;
            }
        });
        itemlist.setAdapter(adapter);
    }


    private class EvaluatItemAdapter extends BaseQuickAdapter<WaitForEvaluateData.ObjBean.OrdersBean.ItemsBean,BaseViewHolder> {


        public EvaluatItemAdapter(int layoutResId, List<WaitForEvaluateData.ObjBean.OrdersBean.ItemsBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WaitForEvaluateData.ObjBean.OrdersBean.ItemsBean item) {
            helper
                    .addOnClickListener(R.id.goods_evaluat_btn)
                    .setText(R.id.goods_evaluat_titel_tv,item.getItemName());

            ((SimpleDraweeView)(helper.getView(R.id.goods_evaluat_imgview))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());

        }
    }
}
