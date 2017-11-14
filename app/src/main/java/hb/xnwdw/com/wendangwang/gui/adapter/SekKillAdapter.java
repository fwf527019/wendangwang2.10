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
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SekKillAdapter extends BaseQuickAdapter<MainPageMiaoShaDate.ItemsBean,BaseViewHolder> {

    public static String newUserLimit="0";
    public SekKillAdapter(int layoutResId, List<MainPageMiaoShaDate.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainPageMiaoShaDate.ItemsBean item) {
        ((SimpleDraweeView)(helper.getView(R.id.test_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
        helper.setText(R.id.test_goods_name,item.getItemName())
                .setText(R.id.test_goods_price,item.getSeckillPrice());

            ((TextView) (helper.getView(R.id.goods_activitflag))).setVisibility(View.VISIBLE);
            ((TextView) (helper.getView(R.id.goods_activitflag))).setText("秒杀");

        if(newUserLimit.equals("1")){
                helper.getView(R.id.newuser_tag).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.newuser_tag).setVisibility(View.GONE);
        }
    }


}
