package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SekKillAdapter1 extends RecyclerView.Adapter<SekKillAdapter1.MyViewHolder> {
    private List<Integer> mDatas;
    private List<String> mDatas1;
    private LayoutInflater minflater;

    public SekKillAdapter1(Context context, List<Integer> mDatas, List<String> mDatas1) {
        minflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
        this.mDatas1 = mDatas1;
    }

    @Override
    public SekKillAdapter1.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = minflater.inflate(R.layout.item_goods, viewGroup, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.mImg = (ImageView) view
                .findViewById(R.id.test_goods_img);
        viewHolder.goodsName = (TextView) view.findViewById(R.id.test_goods_name);
        viewHolder.goodsPrice = (TextView) view.findViewById(R.id.test_goods_price);
        viewHolder.goodswight = (TextView) view.findViewById(R.id.test_goods_wight_tv);
        viewHolder.cart= (ImageView) view.findViewById(R.id.cart_img);


        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mImg.setImageResource(mDatas.get(position));
        holder.goodsName.setText(mDatas1.get(position * 3));
        holder.goodswight.setText(mDatas1.get(position * 3 + 1));
        holder.goodsPrice.setText(mDatas1.get(position * 3 + 2));
        holder.cart.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //viewholder

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        ImageView mImg;
        TextView goodsName;
        TextView goodswight;
        TextView goodsPrice;
        ImageView cart;

    }



}
