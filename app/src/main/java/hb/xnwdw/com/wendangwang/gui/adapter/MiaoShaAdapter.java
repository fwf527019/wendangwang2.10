package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Intent;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;

/**
 * Created by Administrator on 2017/5/23.
 */
public class MiaoShaAdapter  extends BaseQuickAdapter<MainPageMiaoShaDate.ItemsBean,BaseViewHolder>{
    public MiaoShaAdapter(int layoutResId, List<MainPageMiaoShaDate.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainPageMiaoShaDate.ItemsBean item) {
        helper
                .addOnClickListener(R.id.miaosha_btn)
                .setText(R.id.miaosha_titel_tv,item.getItemName())
                .setText(R.id.textView2,"¥"+item.getSeckillPrice())
                .setText(R.id.ralprice,"¥"+item.getRetailPrice())
                .setText(R.id.chushou_num_tv,(item.getRemainderPercent())+"%");
        ((ProgressBar)(helper.getView(R.id.firstBar))).setProgress((int)item.getRemainderPercent());
        ((SimpleDraweeView)(helper.getView(R.id.miaosha_imgview))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());

    }

}
