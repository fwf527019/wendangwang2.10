package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsDetailData;
import hb.xnwdw.com.wendangwang.netdata.entity.ItemListBean;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderBlanceDetailGoodsData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderGoodsInfo;

/**
 * Created by Administrator on 2017/6/1.
 */
public class OrderBalanceListAdapter extends BaseQuickAdapter<ItemListBean,BaseViewHolder>{
    private Context context;

    public OrderBalanceListAdapter(int layoutResId, List<ItemListBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper,ItemListBean item) {

        if(item.getItemAct()!=null&&(!item.getItemAct().equals("无"))){
            (helper.getView(R.id.orderdetails_goods_act)).setVisibility(View.VISIBLE);
        }else {
            (helper.getView(R.id.orderdetails_goods_act)).setVisibility(View.GONE);
        }
        helper
                .setText(R.id.orderdetails_goods_name,item.getItemName())

                .setText(R.id.orderdetails_goods_spec,item.getItemSizi())
                .setText(R.id.orderdetails_goods_num,"×"+item.getAmount())
                .setText(R.id.orderdetails_goods_act,item.getItemAct())

                .setText(R.id.orderdetails_goods_price,"¥"+item.getItemPrice());
        if(item.getPresentItemList()!=null&&item.getPresentItemList().size()!=0){
            ((TextView)(helper.getView(R.id.present_tv))).setText(":"+item.getPresentItemList().get(0).getItemName());
            ((TextView)(helper.getView(R.id.presentnum_tv))).setText("×"+item.getPresentItemList().get(0).getPresentNum()+"");
        }
        ((SimpleDraweeView)(helper.getView(R.id.orderdetails_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());

//        RecyclerView recyclerView=helper.getView(R.id.item_orderdetail_present_items);
//        recyclerView.setLayoutManager(new MyLinearLayoutManager(context));
//        PresentItemsAdpter adpter=new PresentItemsAdpter(R.layout.item_present,item.getPresentItems());
//        recyclerView.setAdapter(adpter);
    }

    private class PresentItemsAdpter extends  BaseQuickAdapter<OrderGoodsInfo.PresentItems,BaseViewHolder>{


        public PresentItemsAdpter(int layoutResId, List<OrderGoodsInfo.PresentItems> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, OrderGoodsInfo.PresentItems item) {
            helper

                    .setText(R.id.present_goods_spec,item.getPitemSizi())
                    .setText(R.id.present_goods_pice,item.getPitemPrice()+"")
                    .setText(R.id.present_goods_num,"×1")
                    .setText(R.id.prensent_tag,item.getTag());
        }
    }

}
