package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import hb.xnwdw.com.wendangwang.EventBus.SerchadapterEvent;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.CouponInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.ShearchHistoryData;
import hb.xnwdw.com.wendangwang.netdata.entity.TestGoods;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;

/**
 * Created by Administrator on 2017/5/18.
 */

public class ShearchListAdapter extends RecyclerView.Adapter<ShearchListAdapter.MyViewHoder> implements View.OnClickListener {

    private List<String> list;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener = null;

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    public ShearchListAdapter(Context context, List<String> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_serchhistory, parent, false);
        MyViewHoder viewHolder = new MyViewHoder(view);
        view.setOnClickListener(this);
        viewHolder.content = (TextView) view.findViewById(R.id.shearch_his_content);
        viewHolder.delete = (ImageView) view.findViewById(R.id.shearch_his_delete);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHoder holder, final int position) {
        holder.content.setText(list.get(position));
        holder.itemView.setTag(position);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
               notifyDataSetChanged();
                EventBus.getDefault().post(new SerchadapterEvent(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHoder extends RecyclerView.ViewHolder {
        public MyViewHoder(View itemView) {
            super(itemView);
        }

        TextView content;
        ImageView delete;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


}
