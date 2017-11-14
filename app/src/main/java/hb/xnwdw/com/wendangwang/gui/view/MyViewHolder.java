package hb.xnwdw.com.wendangwang.gui.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import hb.xnwdw.com.wendangwang.R;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout my_goods_ll;
    public SimpleDraweeView img;
    public TextView name;
    public  TextView price;
    public  TextView sizi;
    public TextView num;
    public CheckBox checkBox;
    public ImageView add;
    public ImageView jian;
    public TextView miaoshall_h;
    public TextView miaoshall_m;
    public TextView miaoshall_s;
    public TextView biaoqian;
    public LinearLayout miaoshall;
    public int position;

    public MyViewHolder(View itemView) {
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
        miaoshall = (LinearLayout) itemView.findViewById(R.id.shop_cart_miaoshatital);
        miaoshall_h = (TextView) itemView.findViewById(R.id.shop_cart_hour);
        miaoshall_m = (TextView) itemView.findViewById(R.id.shop_cart_minut);
        miaoshall_s= (TextView) itemView.findViewById(R.id.shop_cart_second);

    }

    public void setDataPosition(int position) {
        this.position = position;
    }


}
