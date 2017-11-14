package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.QianDaoActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsEvaluateListData;

/**
 * Created by Administrator on 2017/5/22.
 */

public class EvaluateListAdapter extends BaseQuickAdapter<GoodsEvaluateListData.ObjBean, BaseViewHolder> {
    private  Context context;
    public EvaluateListAdapter(int layoutResId, List<GoodsEvaluateListData.ObjBean> data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsEvaluateListData.ObjBean item) {

        LinearLayout linearLayout=helper.getView(R.id.goodevaluat_img_ll);
        SimpleDraweeView hearderimg=helper.getView(R.id.goodevaluat_userhead);

        StringBuilder sb = new StringBuilder();
        sb.append((String) item.getMemberPhone());
        sb.replace(3, 7, "****");
        helper
             .setText(R.id.goodevaluat_userphone,sb.toString())
                .setText(R.id.goodevaluat_content, item.getCommContent())
                .addOnClickListener(R.id.goodevaluat_img_ll)
                .setText(R.id.goodevaluat_time, item.getCommentTime());

        String str = item.getUpPics();


        if(linearLayout.getChildAt(0)!=null){
            linearLayout.removeAllViews();
        }else {
        }

        if(str!=null&&!str.equals("")){
            //拆分图片地址
            linearLayout.setVisibility(View.VISIBLE);
            String[] split = str.split(";");
            for (int i = 0; i <split.length ; i++) {
                SimpleDraweeView imgview=new SimpleDraweeView(context);
                LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(50), LinearLayout.LayoutParams.MATCH_PARENT);
                pams.setMargins(dip2px(10), 0, 0, 0);
                imgview.setLayoutParams(pams);
                linearLayout.addView(imgview);
                imgview.setImageURI(UrlApi.SERVER_IP + split[i]);
            }
        }else {
            linearLayout.setVisibility(View.GONE);
        }


        ((RatingBar)(helper.getView(R.id.goodevaluat_ratingbar))).setRating(item.getSatisfaction());
        hearderimg.setImageURI(UrlApi.SERVER_IP+item.getHeadFace());
    }



    /**
     * dip转像素
     */
    public  int dip2px(float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
