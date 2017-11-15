package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.widget.ItemSlideHelper;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;

/**
 * Created by Administrator on 2017/8/24.
 */

public class GoodsCartListViewAdapter extends BaseQuickAdapter<ShopingCartData.ObjBean,BaseViewHolder> {
//public class GoodsCartListViewAdapter extends BaseQuickAdapter<ShopingCartData.ObjBean,BaseViewHolder>implements ItemSlideHelper.Callback {

    private RecyclerView mRecyclerView;
    private boolean isShow = true;//是否显示编辑/完成
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    private List<ShopingCartData.ObjBean> shoppingCartBeanList;
//    private CheckInterface checkInterface;
//    private ModifyCountInterface modifyCountInterface;
    private Context context;
    public static int isEdiText=0;//0完成状态1：编辑状态
   private ItemSlideHelper itemSlideHelper;
        private  int Tag=0;
    public GoodsCartListViewAdapter(int layoutResId, List<ShopingCartData.ObjBean> data,Context context) {
        super(layoutResId, data);
        this.context=context;
    }



    @Override
    protected void convert(final BaseViewHolder helper, final ShopingCartData.ObjBean item) {

        if(isEdiText==1){
//            itemSlideHelper.horizontalDragItem(dip2px(120),helper.getView(R.id.item_car));
            helper.itemView.scrollTo(dip2px(120),0);
        }else {
            helper.itemView.scrollTo(0,0);
        }

        helper.
                setText(R.id.shopcart_titel_tv,item.getItemName())
                .setText(R.id.shopcart_goods_sizi,item.getItemSize())
                .setText(R.id.goods_num_tv,item.getBuyCounts()+"")
                .setText(R.id.shopcart_good_price,item.getUnit()+"")
                .addOnClickListener(R.id.item_addto_collecte)
                .addOnClickListener(R.id.item_delecte)
                .setText(R.id.shopcart_biaoqian,item.getActivityFlag());
        //活动标签
        if(item.getActivityFlag()!=null&&!item.getActivityFlag().equals("")){
            (helper.getView(R.id.shopcart_biaoqian)).setVisibility(View.VISIBLE);
        }else {
            (helper.getView(R.id.shopcart_biaoqian)).setVisibility(View.GONE);
        }
        //超出限购数量

        if (item.getCanBuyCout()<item.getBuyCounts()) {
            (helper.getView(R.id.tips_limt)).setVisibility(View.VISIBLE);
            ((TextView)( helper.getView(R.id.tips_limtnum_tv))).setText("该商品限购"+item.getMostCount()+"件，超出部分按原价计算");
        }else{
            (helper.getView(R.id.tips_limt)).setVisibility(View.GONE);
        }

        String tag= (String) helper.getView(R.id.shopcart_imgview).getTag();

        if ((UrlApi.SERVER_IP+item.getItemPic()).equals(tag)){
            helper.getView(R.id.shopcart_imgview).setTag(tag);
            //设置图片
        }else {
            ((SimpleDraweeView)( helper.getView(R.id.shopcart_imgview))).setImageURI(UrlApi.SERVER_IP+item.getItemPic());
        }



        ((CheckBox)( helper.getView(R.id.checkbox_chosen))).setOnCheckedChangeListener(null);
        ((CheckBox)( helper.getView(R.id.checkbox_chosen))).setChecked(item.isCheached());
//        ((CheckBox)( helper.getView(R.id.checkbox_chosen))).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//            }
//        });

        //单选框按钮
        helper.getView(R.id.checkbox_chosen).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    item.setCheached(((CheckBox) v).isChecked());
                        checkInterface.checkGroup(helper.getLayoutPosition(), ((CheckBox) v).isChecked());//向外暴露接口
                    }
                }
        );


        //增加按钮
        helper.getView(R.id.shopcart_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doIncrease(helper.getLayoutPosition(), helper.getView(R.id.goods_num_tv),  ((CheckBox)(helper.getView(R.id.checkbox_chosen))).isChecked());//暴露增加接口
            }
        });
        //删减按钮
        helper.getView(R.id.shopcart_jian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifyCountInterface.doDecrease(helper.getLayoutPosition(),helper.getView(R.id.goods_num_tv),  ((CheckBox)(helper.getView(R.id.checkbox_chosen))).isChecked());//暴露删减接口
            }
        });
        if(item.getEndTime()!=null&&item.getEndTime()>=1000){
            /**
             * 时间转化
             *
             * @param l
             * @return
             */
                int hour = 0;
                int minute = 0;
                int second = 0;
                second = item.getEndTime().intValue() / 1000;
                if (second > 60) {
                    minute = second / 60;         //取整
                    second = second % 60;         //取余
                }

                if (minute > 60) {
                    hour = minute / 60;
                    minute = minute % 60;
                }

           helper.getView(R.id.shop_cart_miaoshatital).setVisibility(View.VISIBLE);
            helper
                    .setText(R.id.shop_cart_hour,hour+"")
                    .setText(R.id.shop_cart_minut,minute+"")
                    .setText(R.id.shop_cart_second,second+"");
        }else {
            helper.getView(R.id.shop_cart_miaoshatital).setVisibility(View.GONE);
        }

    }

    /**
     * 单选接口
     *
     * @param checkInterface
     */
    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    /**
     * 改变商品数量接口
     *
     * @param modifyCountInterface
     */
    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }




    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param position  元素位置
         * @param isChecked 元素选中与否
         */
        void checkGroup(int position, boolean isChecked);
    }


    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int position, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param position      元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int position, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param position
         */
        void childDelete(int position);
    }
//
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        super.onAttachedToRecyclerView(recyclerView);
//        mRecyclerView = recyclerView;
//        itemSlideHelper=new ItemSlideHelper(mRecyclerView.getContext(), this);
//        mRecyclerView.addOnItemTouchListener(itemSlideHelper);
//    }
//
//    @Override
//    public int getHorizontalRange(RecyclerView.ViewHolder holder) {
//        if(holder.itemView instanceof LinearLayout){
//            ViewGroup viewGroup = (ViewGroup) holder.itemView;
//            if(viewGroup.getChildCount() == 3){
//                return viewGroup.getChildAt(2).getLayoutParams().width*2;
//            }
//        }
//
//        return 0;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder getChildViewHolder(View childView) {
//
//        return mRecyclerView.getChildViewHolder(childView);
//    }
//
//    @Override
//    public View findTargetView(float x, float y) {
//        return mRecyclerView.findChildViewUnder(x, y);
//    }
//


    /**
     * dip转像素
     */
    private   int dip2px(float dpValue){
        float scale = context.getApplicationContext().getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

}
