package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;

/**
 * Created by Administrator on 2017/7/14.
 */

public class FloorViewAapter extends BaseQuickAdapter<FloorInfoData.DatasBean.ItemsBean, BaseViewHolder> {
    private int pos;

    public FloorViewAapter(int layoutResId, List<FloorInfoData.DatasBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FloorInfoData.DatasBean.ItemsBean item) {
        helper.setText(R.id.test_goods_name, item.getItemName())
                .addOnClickListener(R.id.test_goods_img_ll)
                .setText(R.id.test_goods_price, item.getMobilePrice());
        ((SimpleDraweeView) (helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP + item.getItemPic());
        if (!item.getActivityFlag().equals("")) {
            ((TextView) (helper.getView(R.id.goods_activitflag))).setVisibility(View.VISIBLE);
            ((TextView) (helper.getView(R.id.goods_activitflag))).setText(item.getActivityFlag());
        }


        if(item.getLimitNewMember()!=null&&item.getLimitNewMember().equals("1")){
           helper.getView(R.id.newuser_tag).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.newuser_tag).setVisibility(View.GONE);
        }
    }
}
