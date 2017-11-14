package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;
import hb.xnwdw.com.wendangwang.netdata.entity.TestGoods;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ShopCartGoodsReclerAdapter extends SwipeMenuAdapter<ShopCartGoodsReclerAdapter.ViewHolders> {
    private List<ShopingCartData.ObjBean> objBeen;
    private List<Boolean> selected = new ArrayList<>();
    OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener {
        void onItemClick(View view, int flag, int position);
    }
    public ShopCartGoodsReclerAdapter(OnItemClickLitener mOnItemClickLitener, List<ShopingCartData.ObjBean> objBeen, List<Boolean> selected) {
        this.mOnItemClickLitener = mOnItemClickLitener;
        this.objBeen = objBeen;
        this.selected = selected;
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopingcart_goods, parent, false);
    }

    @Override
    public ViewHolders onCompatCreateViewHolder(View realContentView, int viewType) {
        ViewHolders viewHolder = new ViewHolders(realContentView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolders holder, int position) {
        holder.img.setImageURI(UrlApi.SERVER_IP+objBeen.get(position).getItemPic());
        holder.name.setText(objBeen.get(position).getItemName());
        holder.price.setText(objBeen.get(position).getUnit()+"");
        holder.biaoqian.setText(objBeen.get(position).getActivityFlag());

    }

    @Override
    public int getItemCount() {
        return objBeen == null ? 0 : objBeen.size();
    }

    static class ViewHolders extends RecyclerView.ViewHolder {
        LinearLayout my_goods_ll;
        SimpleDraweeView img;
        TextView name;
        TextView price;
        TextView sizi;
        TextView num;
        CheckBox checkBox;
        ImageView add;
        ImageView jian;
        TextView biaoqian;


        public ViewHolders(View itemView) {
            super(itemView);
            my_goods_ll = (LinearLayout) itemView.findViewById(R.id.shopcart_ll);
            img = (SimpleDraweeView) itemView.findViewById(R.id.shopcart_imgview);
            name = (TextView) itemView.findViewById(R.id.shopcart_titel_tv);
            price = (TextView) itemView.findViewById(R.id.shopcart_good_price);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox_chosen);
            sizi = (TextView) itemView.findViewById(R.id.shopcart_goods_sizi);
            num = (TextView) itemView.findViewById(R.id.goods_num_tv);
            jian = (ImageView) itemView.findViewById(R.id.shopcart_jian);
            add = (ImageView) itemView.findViewById(R.id.shopcart_add);
            biaoqian = (TextView) itemView.findViewById(R.id.shopcart_biaoqian);
        }

    }


//    public ShopCartGoodsReclerAdapter(int layoutResId, List<ShopingCartData.ObjBean> data) {
//        super(layoutResId, data);
//    }
//
//    @Override
//    protected void convert(final BaseViewHolder helper, final ShopingCartData.ObjBean item) {
//        helper.getAdapterPosition();
//        final int[] num = {1};
//
//        helper
//                .setText(R.id.shopcart_titel_tv, item.getItemName())
//                .setOnClickListener(R.id.jian, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        num[0] = num[0] - 1;
//                        ((TextView) (helper.getView(R.id.goods_num_tv))).setText(num[0] + "");
//                        mymunListener.OnJianClickListener(helper.getConvertView(),helper.getLayoutPosition(), num[0],item.getMobilePrice());
//                    }
//                })
//                .setText(R.id.shopcart_titel_tv, item.getItemName())
//                .setOnClickListener(R.id.add, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        num[0] = num[0] + 1;
//                        ((TextView) (helper.getView(R.id.goods_num_tv))).setText(num[0] + "");
//                        mymunListener.OnAddClickListener(helper.getConvertView(),helper.getLayoutPosition(), num[0],item.getMobilePrice());
//                    }
//                })
//
//                .addOnClickListener(R.id.checkbox_chosen)
//                .setText(R.id.shopcart_goods_sizi, item.getItemSize())
//                .setText(R.id.shopcart_good_price, item.getMobilePrice() + "")
//                //       .setText(R.id.shopcart_biaoqian,item.getActivityType())
//                .setText(R.id.shopcart_titel_tv, item.getItemName())
//                .setText(R.id.shopcart_titel_tv, item.getItemName());
//
//
//
//        //秒杀显示时间
//        if (item.getActivityType() == 3) {
//            ((LinearLayout) (helper.getView(R.id.shopcart_miaosha_time))).setVisibility(View.VISIBLE);
//        } else {
//            ((LinearLayout) (helper.getView(R.id.shopcart_miaosha_time))).setVisibility(View.INVISIBLE);
//        }
//
//
////        if (item.getActivityType() == 6) {
////            ((RelativeLayout) (helper.getView(R.id.shopcart_second_goods))).setVisibility(View.VISIBLE);
////        } else {
////            ((RelativeLayout) (helper.getView(R.id.shopcart_second_goods))).setVisibility(View.GONE);
////        }
//
//        ((SimpleDraweeView) (helper.getView(R.id.shopcart_imgview))).setImageURI(UrlApi.SERVER_IP + item.getItemPic());
//
//
////       if(FragmentShoppingCart.ISAll){
////           ((CheckBox) (helper.getView(R.id.checkbox_chosen))).setChecked(true);
////       }
////        else {
////           ((CheckBox) (helper.getView(R.id.checkbox_chosen))).setChecked(false);
////       }
//        ((CheckBox) (helper.getView(R.id.checkbox_chosen))).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(mymunListener!=null){
//                    mymunListener.OnCheakedBoxClickListener(buttonView,isChecked,helper.getLayoutPosition(), num[0],item.getMobilePrice());
//                }
//            }
//        });
//    }
//
//    private NumListener mymunListener;
//
//    public void setMymunListener(NumListener mymunListener) {
//        this.mymunListener = mymunListener;
//    }
//
//    public interface NumListener {
//        void OnCheakedBoxClickListener(View v, boolean isCheached, int pos,int num,double money);
//        void OnJianClickListener(View view,int pos,int num,double money);
//        void OnAddClickListener(View view,int pos,int num,double money);
//
//
//    }
}
