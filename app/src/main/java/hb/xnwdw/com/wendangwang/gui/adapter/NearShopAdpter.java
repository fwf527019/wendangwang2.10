package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URI;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.NearShopActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.NearShopData;
import hb.xnwdw.com.wendangwang.utils.Distance;
import hb.xnwdw.com.wendangwang.utils.Utils;

/**
 * Created by Administrator on 2017/5/12.
 */
public class NearShopAdpter extends BaseQuickAdapter<NearShopData.ObjBean, BaseViewHolder> {
    private double lot1;
    private double lat1;
    private String mome;
    public NearShopAdpter(int layoutResId, List<NearShopData.ObjBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearShopData.ObjBean item) {

        String s = item.getMemo();
        String[] ss = s.split("\\.");
        if (ss.length == 2 &&ss[1].length()>1) {
             mome = ss[0] + '.' + ss[1].substring(0, 2);
        }


        helper
                .setText(R.id.nearshop_list_shopename, item.getStoreName())
                .setText(R.id.nearshop_list_distance, mome + "km")
                .setText(R.id.nearsop_msg, (CharSequence) item.getActivity());
        ((SimpleDraweeView) (helper.getView(R.id.nearshop_list_img))).setImageURI(UrlApi.SERVER_IP + item.getStorePic());
        if(item.getActivity()!=null||item.getActNum()>0){
            ((LinearLayout) (helper.getView(R.id.shop_activity_pic))).setVisibility(View.VISIBLE);
//            ((ImageView) (helper.getView(R.id.xiaonaba))).setVisibility(View.VISIBLE);
//            ((TextView) (helper.getView(R.id.nearsop_msg))).setVisibility(View.VISIBLE);
        }else {
            ((LinearLayout) (helper.getView(R.id.shop_activity_pic))).setVisibility(View.INVISIBLE);
//            ((ImageView) (helper.getView(R.id.xiaonaba))).setVisibility(View.GONE);
//            ((TextView) (helper.getView(R.id.nearsop_msg))).setVisibility(View.GONE);
        }
    }

}
