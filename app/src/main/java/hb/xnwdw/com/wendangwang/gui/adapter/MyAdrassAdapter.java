package hb.xnwdw.com.wendangwang.gui.adapter;


import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.MyAdrasseData;
import hb.xnwdw.com.wendangwang.utils.LogUtils;

/**
 * Created by Administrator on 2017/4/26.
 */
public class MyAdrassAdapter extends BaseQuickAdapter<MyAdrasseData.ObjBean, BaseViewHolder> {
    private int mSelectedPos = -1;//实现单选  方法二，变量保存当前选中的position

    public MyAdrassAdapter(int layoutResId, List<MyAdrasseData.ObjBean> data) {
        //实现单选方法二： 设置数据集时，找到默认选中的pos

        super(layoutResId, data);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MyAdrasseData.ObjBean item) {
        helper.setText(R.id.item_adrass_name, item.getRecName())
                .addOnClickListener(R.id.item_adrass_delete)
                .addOnClickListener(R.id.item_adrass_edt)
                .addOnClickListener(R.id.item_adrass_chekdbox)
                .setText(R.id.item_adrass_phone, item.getPhoneNum())
                .setText(R.id.item_adrass_adrass, item.getProvince() + " " + item.getCity() + " " + item.getCounty() + " " + item.getAddressDetail());

//        if (item.getIsDefault() == 1) {
//            ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio_selected);
//        } else {
//            ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio);
//        }
        if (item.getIsDefault() == 1){
            mSelectedPos = helper.getPosition();
        }else {
            mSelectedPos = -1;
        }

        if (mSelectedPos == helper.getPosition()) {
            ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio_selected);
        } else {
            ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio);
        }

        ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedPos == helper.getPosition()) {

                 clickLstner.Oncliclstner(true, helper.getPosition());
             //       ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio);
                    item.setIsDefault(0);
                    notifyDataSetChanged();
                } else {
                clickLstner.Oncliclstner(false, helper.getPosition());
           //         ((ImageView) (helper.getView(R.id.item_adrass_chekdbox))).setImageResource(R.drawable.radio_selected);
                    item.setIsDefault(1);
                    notifyDataSetChanged();
                }

            }
        });
    }


    private ClickLstner clickLstner;

    public void setClickLstner(ClickLstner clickLstner) {
        this.clickLstner = clickLstner;
    }

    public interface ClickLstner {
        void Oncliclstner(boolean isCheacked, int pos);
    }
}
