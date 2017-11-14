package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentClassfiy;
import hb.xnwdw.com.wendangwang.netdata.entity.ClassfyOneListData;

/**
 * Created by Administrator on 2017/3/8.
 */

public class GoodsClassfyListAdapter extends BaseQuickAdapter<ClassfyOneListData.ObjBean, BaseViewHolder> {

    private int select_item;

    public GoodsClassfyListAdapter(int layoutResId, List<ClassfyOneListData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassfyOneListData.ObjBean item) {
        helper
                .setText(R.id.classfy_tv, item.getCateName());
        this.select_item = FragmentClassfiy.select_item;
        if (this.select_item == helper.getPosition()) {
            ((TextView) helper.getView(R.id.classfy_tv)).setTextSize(17);
            ((TextView) helper.getView(R.id.classfy_tv)).setTextColor(helper.getConvertView().getResources().getColor(R.color.maincolor));
            ((View) helper.getView(R.id.classfy_line_view)).setVisibility(View.VISIBLE);

        } else {
            ((TextView) helper.getView(R.id.classfy_tv)).setTextSize(14);
            ((TextView) helper.getView(R.id.classfy_tv)).setTextColor(helper.getConvertView().getResources().getColor(R.color.black));
            ((View) helper.getView(R.id.classfy_line_view)).setVisibility(View.INVISIBLE);
        }
    }
}
