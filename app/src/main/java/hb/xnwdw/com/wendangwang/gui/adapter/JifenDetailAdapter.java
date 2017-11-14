package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.JifenDetailData;

/**
 * Created by Administrator on 2017/6/1.
 */

public class JifenDetailAdapter extends BaseQuickAdapter<JifenDetailData.ObjBean.DataBean,BaseViewHolder> {
    public JifenDetailAdapter(int layoutResId, List<JifenDetailData.ObjBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, JifenDetailData.ObjBean.DataBean item) {
        helper
                .setText(R.id.jifen_add,item.getIntegral()+"")
                .setText(R.id.jifen_source,item.getDescription())
                .setText(R.id.jifen_time,item.getHappenTime());
    }
}
