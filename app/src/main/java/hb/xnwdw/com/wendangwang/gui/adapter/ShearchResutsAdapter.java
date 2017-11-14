package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShearchResutData;

/**
 * Created by Administrator on 2017/5/18.
 */
public class ShearchResutsAdapter extends BaseQuickAdapter<ShearchResutData.ObjBean,BaseViewHolder> {

    public ShearchResutsAdapter(int layoutResId, List<ShearchResutData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShearchResutData.ObjBean item) {
        helper
                .setText(R.id.carthistory_goods_name,item.getItemName())
                .setText(R.id.carthistory_goods_wight,item.getItemSize())
                .setText(R.id.carthistory_goods_price,item.getMobilePrice()+"");
        ((SimpleDraweeView)(helper.getView(R.id.carthistory_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic1());
    }
}
