package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandDeatailData;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandDetailHotRecomendData;

/**
 * Created by Administrator on 2017/5/10.
 */

public class BrandDetaiHotRecomendeAdapter extends BaseQuickAdapter<BrandDetailHotRecomendData.ObjBean,BaseViewHolder> {
    public BrandDetaiHotRecomendeAdapter(int layoutResId, List<BrandDetailHotRecomendData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandDetailHotRecomendData.ObjBean item) {
        helper.setText(R.id.test_goods_name,item.getItemName())
                .setText(R.id.test_goods_price,item.getMobilePrice());
        ((SimpleDraweeView)(helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic1());

    }
}
