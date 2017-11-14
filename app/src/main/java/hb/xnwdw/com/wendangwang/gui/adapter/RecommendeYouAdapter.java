package hb.xnwdw.com.wendangwang.gui.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.crypto.spec.IvParameterSpec;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;

/**
 * Created by Administrator on 2017/4/18.
 */
public class RecommendeYouAdapter extends BaseQuickAdapter<MainRecommendeYouData.DatasBean, BaseViewHolder> {
    public RecommendeYouAdapter(int layoutResId, List<MainRecommendeYouData.DatasBean> data) {
        super(layoutResId, data);
    }

    String Tag = null;

    @Override
    protected void convert(BaseViewHolder helper, MainRecommendeYouData.DatasBean item) {
        helper
                .addOnClickListener(R.id.commendeyou_btn)
                .setText(R.id.commendeyou_price, item.getMobilePrice() + "")
                .setText(R.id.commendeyou_titel_tv, item.getItemName())
                .setText(R.id.commendeyou_activitflag,Tag)
                .setText(R.id.content_tv, item.getMemo());
        ((SimpleDraweeView)(helper.getView(R.id.commendeyou_imgview))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
        if(!item.getActivityFlag().equals("")){
            ((TextView) (helper.getView(R.id.commendeyou_activitflag))).setVisibility(View.VISIBLE);
            ((TextView) (helper.getView(R.id.commendeyou_activitflag))).setText(item.getActivityFlag());
        }else {
            ((TextView) (helper.getView(R.id.commendeyou_activitflag))).setVisibility(View.INVISIBLE);
        }

        if(item.getLimitNewMember()!=null&&item.getLimitNewMember().equals("1")){
            helper.getView(R.id.commend_newuser).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.commend_newuser).setVisibility(View.GONE);
        }

    }


}
