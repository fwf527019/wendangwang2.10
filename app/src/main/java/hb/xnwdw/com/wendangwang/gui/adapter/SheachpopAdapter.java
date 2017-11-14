package hb.xnwdw.com.wendangwang.gui.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.AllBrandData;

/**
 * Created by Administrator on 2017/6/15.
 */
public class SheachpopAdapter  extends BaseQuickAdapter<String,BaseViewHolder>{
    List<String> data;

    public void setData(List<String> data) {
        if(this.data!=null){
            this.data.clear();
        }
        this.data = data;
        notifyDataSetChanged();
    }

    final boolean[] isChecked;
    public SheachpopAdapter(int layoutResId, List<String> data,List<String> list) {
        super(layoutResId, data);
        this.data=data;
        isChecked  = new boolean[list.size()];
    }

    @Override
    protected void convert(final BaseViewHolder helper, String item) {

        helper
                .setText(R.id.shearchpop_brand_tv,item)
                .addOnClickListener(R.id.shearchpop_brand_tv);

        ((TextView) (helper.getView(R.id.shearchpop_brand_tv))) .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( ! isChecked[helper.getPosition()]){
                    isChecked[helper.getPosition()] =true;
                      clicListener.onClick(helper.getPosition(),true,v);
                }else {
                    isChecked[helper.getPosition()] =false;
                    clicListener.onClick(helper.getPosition(),false,v);
                }


            }
        });

    }
    private ClicListener clicListener;

    public void setClicListener(ClicListener clicListener) {
        this.clicListener = clicListener;
    }

    public interface  ClicListener{
        void  onClick(int postion,boolean isCheackd,View view);
    }
}
