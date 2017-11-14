package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ProcessData;

/**
 * Created by Administrator on 2017/8/14.
 */
public class ProcessAdapter extends BaseQuickAdapter<ProcessData.ObjBean, BaseViewHolder> {
  private   int state;
    public ProcessAdapter(int layoutResId, List<ProcessData.ObjBean> data,int state) {
        super(layoutResId, data);
        this.state=state;
    }

    @Override
    protected void convert(BaseViewHolder helper, ProcessData.ObjBean item) {
        if(item.getCounts()==0){
            helper.getView(R.id.item_process_num).setVisibility(View.GONE);
        }else {
        }

        helper
                .setText(R.id.item_process_ordernum,"售后单号:"+ item.getAfterNum())
                .setText(R.id.item_process_type, item.getSaleAfterType())
                .setText(R.id.item_process_time, "申请时间" + item.getApplyTime())
                .setText(R.id.item_process_name, item.getItemName())
                .setText(R.id.item_process_memo, item.getItemSize())
                .setText(R.id.item_process_num, "数量:"+item.getCounts())
                .setText(R.id.item_process_state, item.getState())
                .addOnClickListener(R.id.item_process_btn1)
                .addOnClickListener(R.id.item_process_btn2);


        ((SimpleDraweeView) (helper.getView(R.id.item_process_img))).setImageURI(UrlApi.SERVER_IP + item.getItemPic());

        if(state==1){
            helper.getView(R.id.item_process_btn1).setVisibility(View.GONE);
            helper.getView(R.id.item_process_btn2).setVisibility(View.GONE);
        } else {

            if ("待收货".equals(item.getState())) {
                helper.getView(R.id.item_process_btn1).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.item_process_btn1).setVisibility(View.GONE);
            }
        }
    }



}
