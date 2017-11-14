package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandPrefredeData;

/**
 * Created by Administrator on 2017/3/14.
 */

public class PreferredBrandAdapter extends BaseQuickAdapter<BrandPrefredeData.ObjBean,BaseViewHolder> {

    public PreferredBrandAdapter(int layoutResId, List<BrandPrefredeData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandPrefredeData.ObjBean item) {
        ((SimpleDraweeView)(helper.getView(R.id.brandrecomende_listimg))).setImageURI(UrlApi.SERVER_IP+item.getBrandPic());
    }
}
