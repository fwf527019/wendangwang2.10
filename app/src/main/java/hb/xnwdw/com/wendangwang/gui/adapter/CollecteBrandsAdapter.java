package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CollectBrandsData;

/**
 * Created by Administrator on 2017/12/12.
 */

public class CollecteBrandsAdapter extends BaseQuickAdapter<CollectBrandsData.ObjBean,BaseViewHolder> {
    private  boolean isEdt=false;
    Context context;
    public void setEdt(boolean edt) {
        isEdt = edt;
    }

    public CollecteBrandsAdapter(int layoutResId, List<CollectBrandsData.ObjBean> data) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, CollectBrandsData.ObjBean item) {
        if(isEdt){
            helper.getView(R.id.my_collectslist_chosse).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.my_collectslist_chosse).setVisibility(View.GONE);
        }
        if(item.isCollect()){
            helper.getView(R.id.my_collectslist_chosse).setBackgroundResource(R.drawable.ico_chosen);
        }else {
            helper.getView(R.id.my_collectslist_chosse).setBackgroundResource(R.drawable.ico_choose);
        }
        ((SimpleDraweeView)(helper.getView(R.id.my_collects_brandlist_img))).setImageURI(UrlApi.SERVER_IP+item.getBrandPic());
    }




}
