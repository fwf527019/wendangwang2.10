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
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentClassfiy;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.SecondClassfyData;

/**
 * Created by Administrator on 2017/3/8.
 */

public class GoodsClassfyGridViewAdapter extends BaseQuickAdapter<SecondClassfyData.ObjBean,BaseViewHolder> {

    public GoodsClassfyGridViewAdapter(int layoutResId, List<SecondClassfyData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SecondClassfyData.ObjBean item) {
        helper
                .setText(R.id.calssfy_grid_tv,item.getCateName());
        ( (SimpleDraweeView)(helper.getView(R.id.classfy_grid_img))).setImageURI(UrlApi.SERVER_IP+item.getCatePic());
    }
}
