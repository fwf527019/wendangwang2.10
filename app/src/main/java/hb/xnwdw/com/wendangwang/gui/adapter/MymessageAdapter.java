package hb.xnwdw.com.wendangwang.gui.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.MessageData;

/**
 * Created by Administrator on 2017/6/26.
 */
public class MymessageAdapter extends BaseQuickAdapter<MessageData.ObjBean.ListBean, BaseViewHolder> {
    public MymessageAdapter(int layoutResId, List<MessageData.ObjBean.ListBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder helper, MessageData.ObjBean.ListBean item) {
        helper.setText(R.id.message_count, item.getMessContent())
                .setText(R.id.message_time, item.getMessDate());


        //0----未读
        //1---已读
        if (item.getMessType().contains("通知")) {
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
        } else if (item.getMessType().contains("客服")) {
            if (item.getIsRead() == 1) {
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.GRAY);
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_kefu);
            } else {
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.BLACK);
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_kefu_red);
            }

        } else if (item.getMessType().contains("订单")) {
            if (item.getIsRead() == 1) {
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.GRAY);
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_dingdan);
            } else {
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.BLACK);
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_dingdan_red);
            }
        } else {
            if (item.getIsRead() == 1) {
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_dingdan);
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.GRAY);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.GRAY);
            } else {
                ((ImageView) (helper.getView(R.id.my_massege_img))).setImageResource(R.drawable.news_dingdan_red);
                ((TextView) (helper.getView(R.id.message_count))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_time))).setTextColor(Color.BLACK);
                ((TextView) (helper.getView(R.id.message_type))).setTextColor(Color.BLACK);
            }

        }
    }

}
