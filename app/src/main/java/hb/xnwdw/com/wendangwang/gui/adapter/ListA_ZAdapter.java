package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by Administrator on 2017/3/14.
 */

public class ListA_ZAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int slectePos = -1;

    public int getSlectePos() {
        return slectePos;
    }

    public void setSlectePos(int slectePos) {
        this.slectePos = slectePos;
    }

    public ListA_ZAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {
        helper
                .setText(R.id.word_tv, item);
        Log.d(TAG, "slectePos:" + slectePos);
        if (slectePos == -1) {
            helper.getView(R.id.word_tv).setBackgroundColor(Color.WHITE);
        } else if (slectePos == helper.getPosition()) {
            helper.getView(R.id.word_tv).setBackgroundColor(Color.GRAY);
            slectePos=-1;
        }
//        helper.getView(R.id.word_tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (slectePos == helper.getPosition()) {
//                    slectePos = -1;
//                    notifyDataSetChanged();
//                } else {
//                    slectePos = helper.getPosition();
//                    notifyDataSetChanged();
//                }
//            }
//        });
    }


}
