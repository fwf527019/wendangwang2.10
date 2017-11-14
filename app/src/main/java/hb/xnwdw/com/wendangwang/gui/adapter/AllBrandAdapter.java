package hb.xnwdw.com.wendangwang.gui.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AllBrandData;

/**
 * Created by Administrator on 2017/3/14.
 */

public class AllBrandAdapter extends BaseQuickAdapter<AllBrandData.ObjBean,BaseViewHolder> {

    public AllBrandAdapter(int layoutResId, List<AllBrandData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBrandData.ObjBean item) {
        ((SimpleDraweeView)(helper.getView(R.id.allbrand_listimg))).setImageURI(UrlApi.SERVER_IP+item.getBrandPic());
    }
}
