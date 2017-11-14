package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.HotSaleData;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodDetailHotSaleAdapter extends BaseQuickAdapter<HotSaleData.ObjBean, BaseViewHolder> {
    public GoodDetailHotSaleAdapter(int layoutResId, List<HotSaleData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSaleData.ObjBean item) {
        helper
                .setText(R.id.test_goods_name, item.getItemName())
                .setText(R.id.test_goods_wight_tv, item.getItemSize())
                .setText(R.id.test_goods_price, item.getMobilePrice() + "");
        ((SimpleDraweeView) (helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP + item.getItemPic1());

        if(item.getLimitNewMember()!=null&&item.getLimitNewMember().equals("1")){
        helper.getView(R.id.newuser_tag).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.newuser_tag).setVisibility(View.GONE);
        }
    }


}
