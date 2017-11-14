package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.HashMap;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FooterHistData;
import hb.xnwdw.com.wendangwang.netdata.entity.HistoryData;
import hb.xnwdw.com.wendangwang.netdata.entity.TestGoods;

/**
 * Created by Administrator on 2017/3/10.
 */

public class ShopCartHistotryGridAdapter extends BaseQuickAdapter<FooterHistData.ObjBean.ContentBean,BaseViewHolder> {


    public ShopCartHistotryGridAdapter(int layoutResId, List<FooterHistData.ObjBean.ContentBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FooterHistData.ObjBean.ContentBean item) {
        helper.setText(R.id.test_goods_name, item.getItemName())
                .setText(R.id.test_goods_price, item.getUnit()+"");
        ((SimpleDraweeView) (helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());

    }
}
