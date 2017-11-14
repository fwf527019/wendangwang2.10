package hb.xnwdw.com.wendangwang.gui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.SaleDetailData;
import hb.xnwdw.com.wendangwang.utils.Utils;

/**
 * Created by Administrator on 2017/8/15.
 */

public class SaleDetailContentAdapter extends BaseQuickAdapter<SaleDetailData.ObjBean, BaseViewHolder> {
    public SaleDetailContentAdapter(int layoutResId, List<SaleDetailData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleDetailData.ObjBean item) {

        if (item.getDisposeInfo().contains("/NoSendOrder/ShowDetails/")) {
            String orderNum = Utils.cutString(item.getDisposeInfo(), ">", "<");
            helper.addOnClickListener(R.id.saledetail_content)
                    .setText(R.id.saledetail_content,"新的订单已生成!"+orderNum);

        } else {
            helper.setText(R.id.saledetail_content,item.getDisposeInfo());
        }

        helper
                .setText(R.id.saledetail_time, item.getOperationdate());

//
    }


}
