package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;

/**
 * Created by Administrator on 2017/4/24.
 */
public class FloorInfo1Adapter extends BaseQuickAdapter<FloorInfoData.DatasBean.ItemsBean, BaseViewHolder> {
    public FloorInfo1Adapter(int layoutResId, List<FloorInfoData.DatasBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FloorInfoData.DatasBean.ItemsBean item) {
        helper.setText(R.id.test_goods_name, item.getItemName())
                .setText(R.id.test_goods_price, item.getMobilePrice());
        ((SimpleDraweeView) (helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
        if(!item.getActivityFlag().equals("")){
            ((TextView)(helper.getView(R.id.goods_activitflag))).setVisibility(View.VISIBLE);
            ((TextView)(helper.getView(R.id.goods_activitflag))).setText(item.getActivityFlag());
        }




    }



}
