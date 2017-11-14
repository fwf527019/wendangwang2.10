package hb.xnwdw.com.wendangwang.gui.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.TongGaoDetail;
import hb.xnwdw.com.wendangwang.gui.view.JDAdverView;
import hb.xnwdw.com.wendangwang.netdata.entity.AdverNotice;
/**
 * Created by Administrator on 2017/7/10.
 */
public class TongGaoAdapter {
    private Context context;
    private List<AdverNotice.ObjBean> mDatas;

    public TongGaoAdapter(List<AdverNotice.ObjBean> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
        if (mDatas == null || mDatas.isEmpty()) {
            throw new RuntimeException("nothing to show");
        }
    }
    /**
     * 获取数据的条数
     * @return
     */
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }
    /**
     * 获取个数据
     * @param position
     * @return
     */
    public AdverNotice.ObjBean getItem(int position) {
        return mDatas.get(position);
    }
    /**
     * 获取条目布局
     * @param parent
     * @return
     */
    public View getView(JDAdverView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.mad_item, null);
    }

    /**
     * 条目数据适配
     * @param view
     * @param data
     */
    public void setItem(final View view, final AdverNotice.ObjBean data) {
        TextView tv1 = (TextView) view.findViewById(R.id.gonggao_tv1);
        TextView tv2 = (TextView) view.findViewById(R.id.gonggao_tv2);
        tv1.setText(data.getTitle());
        tv2.setText(data.getReleaseTime().substring(5,10));
        //你可以增加点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //比如打开url
                Intent intent=new Intent(context,TongGaoDetail.class);
                intent.putExtra("id",data.getID()+"");
                context.startActivity(intent);
            }
        });
    }
}
