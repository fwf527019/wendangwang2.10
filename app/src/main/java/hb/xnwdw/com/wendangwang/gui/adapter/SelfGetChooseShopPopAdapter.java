package hb.xnwdw.com.wendangwang.gui.adapter;

import android.os.Handler;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.entity.NearShopData;

/**
 * Created by Administrator on 2017/5/15.
 */
public class SelfGetChooseShopPopAdapter extends BaseQuickAdapter<NearShopData.ObjBean, BaseViewHolder> {
    private int slectepos = -1;
    private List<NearShopData.ObjBean> datas;

    public SelfGetChooseShopPopAdapter(int layoutResId, List<NearShopData.ObjBean> data) {
        super(layoutResId, data);
        this.datas = data;
    }

    @Override
    protected void convert(final BaseViewHolder helper, NearShopData.ObjBean item) {
        helper
                .setText(R.id.selfget_list_name, item.getStoreName())
                .setText(R.id.selfget_list_phone, "联系电话：" + item.getPhoneNumber())

                .setText(R.id.selfget_list_adrasse, "地址" + item.getStoreAddress());

        if (item.isSlecte()) {
            ((CheckBox) (helper.getView(R.id.selfget_list_cheacbox))).setChecked(true);
        } else {
            ((CheckBox) (helper.getView(R.id.selfget_list_cheacbox))).setChecked(false);
        }
        ((CheckBox) (helper.getView(R.id.selfget_list_cheacbox))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //单选：每次点击前将数据重置
                for (int i = 0; i < datas.size(); i++) {
                    datas.get(i).setSlecte(false);
                }
                datas.get(helper.getLayoutPosition()).setSlecte(true);
                myListerner.OnCheakedBoxClickListener(v, true, helper.getLayoutPosition());
                notifyDataSetChanged();
            }
        });
    }

    private MyListerner myListerner;

    public void setMyListerner(MyListerner myListerner) {
        this.myListerner = myListerner;
    }

    public interface MyListerner {
        void OnCheakedBoxClickListener(View v, boolean isCheached, int pos);
    }


    private void specialUpdate() {
        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                notifyItemChanged(getItemCount() - 1);
            }
        };
        handler.post(r);
    }
}
