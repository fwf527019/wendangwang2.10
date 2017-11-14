package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopActivityData;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ShopActivityListAdapter extends BaseQuickAdapter<ShopActivityData.ObjBean, BaseViewHolder> {
    public ShopActivityListAdapter(int layoutResId, List<ShopActivityData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopActivityData.ObjBean item) {
        helper
                .setText(R.id.shopactivitylist_name, item.getActivityName())
                .setText(R.id.shopactivitylist_startime, item.getStartTime().substring(0,10))
                .setText(R.id.shopactivitylist_endtime, item.getEndTime().substring(0,10));
        ((SimpleDraweeView)(helper.getView(R.id.shopactivitylist_img))).setImageURI(UrlApi.SERVER_IP + item.getActPic());
        if(item.getActiveStatus()==-1){
            ((ImageView) (helper.getView(R.id.shopactivitylist_overimg))).setVisibility(View.VISIBLE);
        }
        else {
            ((ImageView) (helper.getView(R.id.shopactivitylist_overimg))).setVisibility(View.GONE);
        }


    }
}
