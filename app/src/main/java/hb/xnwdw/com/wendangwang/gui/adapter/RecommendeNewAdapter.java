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
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;

/**
 * Created by Administrator on 2017/2/24.
 */

public class RecommendeNewAdapter extends BaseQuickAdapter<NewRecommendeData.DatasBean,BaseViewHolder> {


    public RecommendeNewAdapter(int layoutResId, List<NewRecommendeData.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewRecommendeData.DatasBean item) {
                helper.setText(R.id.test_goods_name,item.getItemName())
                        .setText(R.id.test_goods_price,item.getRetailPrice());
                ((SimpleDraweeView)(helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
    }

}
