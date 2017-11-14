//package hb.xnwdw.com.wendangwang.gui.adapter;
//
//import android.content.Context;
//import android.os.CountDownTimer;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import hb.xnwdw.com.wendangwang.R;
//import hb.xnwdw.com.wendangwang.gui.fragment.FragmentGoods;
//import hb.xnwdw.com.wendangwang.gui.view.MyViewHolder;
//import hb.xnwdw.com.wendangwang.netdata.UrlApi;
//import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;
//
//public class ItemRemoveAdapter extends RecyclerView.Adapter {
//    private Context mContext;
//    private LayoutInflater mInflater;
//    private List<ShopingCartData.ObjBean> mList;
//    private MyViewHolder viewHolder;
//    public List<MyViewHolder> myViewHolderList = new ArrayList<>();
//    private CheckInterface checkInterface;
//    private ModifyCountInterface modifyCountInterface;
//    public ItemRemoveAdapter(Context context, List<ShopingCartData.ObjBean> list) {
//        mContext = context;
//        mInflater = LayoutInflater.from(context);
//        mList = list;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new MyViewHolder(mInflater.inflate(R.layout.item_shopingcart_goods, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        viewHolder = (MyViewHolder) holder;
//        viewHolder.setDataPosition(position);
//        if(!(myViewHolderList.contains(viewHolder))){
//            myViewHolderList.add(viewHolder);
//        }
//        if(mList.get(position).getActivityFlag()!=null&&!mList.get(position).getActivityFlag().equals("")){
//            viewHolder.biaoqian.setVisibility(View.VISIBLE);
//        }else {
//            viewHolder.biaoqian.setVisibility(View.GONE);
//        }
//
//        /*******秒杀********/
//        if(mList.get(position).getActivityType()==3){
//            viewHolder.miaoshall.setVisibility(View.VISIBLE);
//
//        }else {
//            viewHolder.miaoshall.setVisibility(View.GONE);
//        }
//
//        viewHolder.img.setImageURI(UrlApi.SERVER_IP+mList.get(position).getItemPic());
//        viewHolder.name.setText(mList.get(position).getItemName());
//        viewHolder.price.setText(mList.get(position).getUnit()+"");
//        viewHolder.biaoqian.setText(mList.get(position).getActivityFlag());
//        //单选框
//       viewHolder.checkBox.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mList.get(position).setCheached(((CheckBox) v).isChecked());
//                        checkInterface.checkGroup(position, ((CheckBox) v).isChecked());//向外暴露接口
//                        if(mList.get(position).isCheached()){
//                            mList.get(position).setCheached(false);
//                            viewHolder.checkBox.setBackgroundResource(R.drawable.ico_choose);
//                        }else {
//                            mList.get(position).setCheached(true);
//                            viewHolder.checkBox.setBackgroundResource(R.drawable.ico_chosen);
//                        }
//                    }
//                }
//        );
//        //增加按钮
//        viewHolder.add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modifyCountInterface.doIncrease(position, viewHolder.num, mList.get(position).isCheached());//暴露增加接口
//            }
//        });
//        //删减按钮
//        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                modifyCountInterface.doDecrease(position, viewHolder.num, mList.get(position).isCheached());//暴露删减接口
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList != null ? mList.size() : 0;
//    }
//
//
//
//    public void removeItem(int position) {
//        mList.remove(position);
//        notifyDataSetChanged();
//    }
//    //遍历list，刷新相应holder的TextView
//    public void notifyData(){
//        for(int i = 0;i < myViewHolderList.size(); i++){
//            myViewHolderList.get(i).miaoshall_h
//                    .setText(mList.get(myViewHolderList.get(i).position).getTimeH());
//            myViewHolderList.get(i).miaoshall_m
//                    .setText(mList.get(myViewHolderList.get(i).position).getTimeM());
//            myViewHolderList.get(i).miaoshall_s
//                    .setText(mList.get(myViewHolderList.get(i).position).getTimeS());
//
//        }
//    }
//    /**
//     * 单选接口
//     *
//     * @param checkInterface
//     */
//    public void setCheckInterface(CheckInterface checkInterface) {
//        this.checkInterface = checkInterface;
//    }
//
//    /**
//     * 改变商品数量接口
//     *
//     * @param modifyCountInterface
//     */
//    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
//        this.modifyCountInterface = modifyCountInterface;
//    }
//
//    /**
//     * 复选框接口
//     */
//    public interface CheckInterface {
//        /**
//         * 组选框状态改变触发的事件
//         *
//         * @param position  元素位置
//         * @param isChecked 元素选中与否
//         */
//        void checkGroup(int position, boolean isChecked);
//    }
//
//
//    /**
//     * 改变数量的接口
//     */
//    public interface ModifyCountInterface {
//        /**
//         * 增加操作
//         *
//         * @param position      元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         */
//        void doIncrease(int position, View showCountView, boolean isChecked);
//
//        /**
//         * 删减操作
//         *
//         * @param position      元素位置
//         * @param showCountView 用于展示变化后数量的View
//         * @param isChecked     子元素选中与否
//         */
//        void doDecrease(int position, View showCountView, boolean isChecked);
//
//        /**
//         * 删除子item
//         *
//         * @param position
//         */
//        void childDelete(int position);
//    }
//
//}
