package hb.xnwdw.com.wendangwang.gui.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.MessageData;
import hb.xnwdw.com.wendangwang.netdata.entity.MsgData;

/**
 * Created by Administrator on 2017/6/26.
 */
public class MymessageAdapter1 extends BaseQuickAdapter<MsgData.ObjBean.MsgListBean, BaseViewHolder> {


    public MymessageAdapter1(int layoutResId, List<MsgData.ObjBean.MsgListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgData.ObjBean.MsgListBean item) {
        helper.setText(R.id.message_count, item.getMessContent())
                .setText(R.id.message_time, item.getMessDate());

        if (item.getIsRead() == 1) {
            ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.GRAY);
            ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.GRAY);
            ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.GRAY);
            ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_tongzhi);
        } else {
            ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.BLACK);
            ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.BLACK);
            ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.BLACK);
            ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_tongzhi_red);
        }
    }
}
