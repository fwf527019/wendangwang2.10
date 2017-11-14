package hb.xnwdw.com.wendangwang.gui.adapter;

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
 * Created by Administrator on 2017/6/15.
 */
public class FloorInfo4Adapter  extends BaseQuickAdapter<FloorInfoData.DatasBean, BaseViewHolder> {
    public FloorInfo4Adapter(int layoutResId, List<FloorInfoData.DatasBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FloorInfoData.DatasBean item) {
        helper.setText(R.id.test_goods_name, item.getItems().get(3).getItemName())
                .setText(R.id.test_goods_price, item.getItems().get(3).getMobilePrice());
        ((SimpleDraweeView) (helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItems().get(0).getItemPic());
        if(!item.getItems().get(3).getActivityFlag().equals("")){
            ((TextView)(helper.getView(R.id.goods_activitflag))).setVisibility(View.VISIBLE);
            ((TextView)(helper.getView(R.id.goods_activitflag))).setText(item.getItems().get(3).getActivityFlag());
        }
    }
}
