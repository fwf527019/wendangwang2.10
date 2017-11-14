package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShearchResutData;

/**
 * Created by Administrator on 2017/5/18.
 */
public class ShearchResutsAdapter extends BaseQuickAdapter<ShearchResutData.ObjBean,BaseViewHolder> {

    public ShearchResutsAdapter(int layoutResId, List<ShearchResutData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShearchResutData.ObjBean item) {
        helper
                .setText(R.id.carthistory_goods_name,item.getItemName())
                .setText(R.id.carthistory_goods_wight,item.getItemSize())
                .setText(R.id.carthistory_goods_price,item.getMobilePrice()+"");
        ((SimpleDraweeView)(helper.getView(R.id.carthistory_goods_img1))).setImageURI(UrlApi.SERVER_IP+item.getItemPic1());
        if(item.getActFlag()!=null){
            ((TextView)(helper.getView(R.id.goods_activitflag))).setVisibility(View.VISIBLE);
            ((TextView)(helper.getView(R.id.goods_activitflag))).setText(item.getActFlag());
        }else {
            ((TextView)(helper.getView(R.id.goods_activitflag))).setVisibility(View.GONE);
        }

        if(item.getIsNewMember().equals("0")){
            helper.getView(R.id.newuser_tag).setVisibility(View.GONE);
        }else {
            helper.getView(R.id.newuser_tag).setVisibility(View.VISIBLE);
        }
    }

}
