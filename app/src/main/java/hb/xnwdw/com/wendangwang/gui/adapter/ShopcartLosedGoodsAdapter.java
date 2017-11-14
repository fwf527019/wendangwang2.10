package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;
import hb.xnwdw.com.wendangwang.netdata.entity.TestGoods;

import static hb.xnwdw.com.wendangwang.R.id.view;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ShopcartLosedGoodsAdapter extends BaseQuickAdapter<ShopingCartData.ObjBean, BaseViewHolder> {


    public ShopcartLosedGoodsAdapter(int layoutResId, List<ShopingCartData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopingCartData.ObjBean item) {
        helper
                .setText(R.id.cart_losed_name, item.getItemName())
                .setText(R.id.num_losegood, item.getBuyCounts()+"")
                .setText(R.id.shixiao_state, item.getState())
                .setText(R.id.cart_losed_num, item.getItemSize());
        ((SimpleDraweeView) (helper.getView(R.id.cart_losed_img))).setImageURI(UrlApi.SERVER_IP + item.getItemPic());
        ((TextView) (helper.getView(R.id.shixiao_bg))).getBackground().setAlpha(200);

    }

}
