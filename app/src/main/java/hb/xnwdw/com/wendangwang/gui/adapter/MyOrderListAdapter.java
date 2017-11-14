package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.OrderDetailsActivity;
import hb.xnwdw.com.wendangwang.gui.view.WarpLinearLayout;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyOrderListData;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MyOrderListAdapter extends BaseQuickAdapter<MyOrderListData.OrdersBean, BaseViewHolder> {
    private String str;
    private List<Integer> list;
    private Handler handler;
    private Context context;
    public MyOrderListAdapter(int layoutResId, List<MyOrderListData.OrdersBean> data, Handler handler, Context context) {
        super(layoutResId, data);
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyOrderListData.OrdersBean item) {

//        int Ids[] = {R.id.myorder_img1, R.id.myorder_img2, R.id.myorder_img3, R.id.myorder_img4, R.id.myorder_img5};
//        if (item.getItems().size() <= 5) {
//            for (int i = 0; i < item.getItems().size(); i++) {
//                ((SimpleDraweeView) (helper.getView(Ids[i]))).setImageURI(UrlApi.SERVER_IP + item.getItems().get(i).getItemPic());
//            }
//        }



        WarpLinearLayout img_ll=helper.getView(R.id.myorder_img_ll);
        //为了防止recycleview重复加载自动生成的图片
        if(img_ll.getChildAt(0)!=null){
            img_ll.removeAllViews();
        }else {
        }
        for (int i = 0; i <item.getItems().size() ; i++) {
            SimpleDraweeView img=new SimpleDraweeView(context);
            LinearLayout.LayoutParams parmas=new LinearLayout.LayoutParams(dip2px(45),dip2px(45));
            parmas.setMargins(dip2px(10), 0, 0, 0);
            img.setLayoutParams(parmas);
            img.setImageURI(UrlApi.SERVER_IP + item.getItems().get(i).getItemPic());
            img_ll.addView(img);

    }



        ((TextView) (helper.getView(R.id.orderlist_state))).setText(str);
        Button btn1 = ((Button) (helper.getView(R.id.myorder_all_btn_1)));
        Button btn2 = ((Button) (helper.getView(R.id.myorder_all_btn_2)));
        Button btn3 = ((Button) (helper.getView(R.id.myorder_all_btn_3)));
        Button btn4 = ((Button) (helper.getView(R.id.myorder_all_btn_4)));
        Button btn5 = ((Button) (helper.getView(R.id.myorder_all_btn_5)));
        Button btn6 = ((Button) (helper.getView(R.id.myorder_all_btn_6)));
        Button btn7 = ((Button) (helper.getView(R.id.myorder_all_btn_7)));
        Button btn8 = ((Button) (helper.getView(R.id.myorder_all_btn_8)));
        Button btn9 = ((Button) (helper.getView(R.id.myorder_all_btn_9)));


        //取消订单
        if (item.getCanCancel()==1) {
            btn1.setVisibility(View.VISIBLE);
        }else {
            btn1.setVisibility(View.GONE);
        }
        //立即支付
        if (item.getCanPay()==1){
            btn2.setVisibility(View.VISIBLE);
        }else {
            btn2.setVisibility(View.GONE);
        }
        //删除订单
        if (item.getCanDel()==1) {
            btn3.setVisibility(View.VISIBLE);

        }else {
            btn3.setVisibility(View.GONE);
        }
        //再次购买
        if (item.getCanBuyAgain()==1) {
            btn4.setVisibility(View.VISIBLE);
        }else {
            btn4.setVisibility(View.GONE);
        }
        //>申请退款
        if (item.getCanReturnMoney()==1 ) {
            btn5.setVisibility(View.VISIBLE);

        }else {
            btn5.setVisibility(View.GONE);
        }
        //查看物流
        if (item.getCanLogistics()==1) {
            btn6.setVisibility(View.VISIBLE);
        }else {
            btn6.setVisibility(View.GONE);
        }
        //确认收货
        if (item.getCanBeSure()==1) {
            btn7.setVisibility(View.VISIBLE);

        }else {
            btn7.setVisibility(View.GONE);
        }
        //去评价
        if (item.getCanEvaluate()==1) {
            btn8.setVisibility(View.VISIBLE);

        }else {
            btn8.setVisibility(View.GONE);
        }
        //售后
        if (item.getCanAfterSale()==1) {
            btn9.setVisibility(View.VISIBLE);

        }else {
            btn9.setVisibility(View.GONE);
        }

        switch (item.getOrderStatus()) {
            case "1":
                str = "待支付";
                break;
            case "2":
                str = "订单超时";
                break;
            case "3":
                str = "已取消";
                break;
            case "4":
                str = "支付失败";
                break;
            case "5":
                str = "待确认";
                break;
            case "6":
                str = "待发货";
                break;
            case "7":
                str = "待到店";
                break;
            case "8":
                str = "待收货";
                break;
            case "9":
                str = "待评价";
                break;
            case "10":
                str = "已完成";
                break;
        }

        helper.setText(R.id.orderlist_num, item.getOrderNumber())

                .addOnClickListener(R.id.myorder_all_btn_1)
                .addOnClickListener(R.id.myorder_all_btn_2)
                .addOnClickListener(R.id.myorder_all_btn_3)
                .addOnClickListener(R.id.myorder_all_btn_4)
                .addOnClickListener(R.id.myorder_all_btn_5)
                .addOnClickListener(R.id.myorder_all_btn_6)
                .addOnClickListener(R.id.myorder_all_btn_7)
                .addOnClickListener(R.id.myorder_all_btn_8)
                .addOnClickListener(R.id.myorder_all_btn_9)
                .setText(R.id.order_num, "共" + item.getItems().size() + "件商品  合计：¥" + item.getPracticalMoney() + "(含运费¥" + item.getPostage() + ")")
               .setText(R.id.orderlist_state,str)
                .setText(R.id.orderlist_time, item.getOrderDate());

        ((LinearLayout) (helper.getView(R.id.item_order))).setOnClickListener(new View.OnClickListener() {
                                                                                  @Override
                                                                                  public void onClick(View v) {
                                                                                      list = new ArrayList<>();
                                                                                      if (((item.getOrderStatus().toString().equals("1")) ||( item.getOrderStatus().equals("4")))&& (!item.getOrderTypeId().equals("3"))&& (!item.getOrderTypeId().equals("2"))&& (!item.getOrderTypeId().equals("4"))) {
                                                                                          list.add(1);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("1")) || (item.getOrderStatus().equals("4"))){
                                                                                          list.add(2);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("2") || (item.getOrderStatus().equals("3") || (item.getOrderStatus().equals("10"))))) {
                                                                                          list.add(3);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("2") || (item.getOrderStatus().equals("3") || (item.getOrderStatus().equals("10"))))&&(item.getOrderTypeId().equals("0")||item.getOrderTypeId().equals("2"))) {
                                                                                          list.add(4);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("5") || (item.getOrderStatus().equals("6")) && item.getIsPrint() == 0 && !item.getOrderTypeId().equals("3") && !item.getOrderTypeId().equals("4"))) {
                                                                                          list.add(5);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("5") || item.getOrderStatus().equals("6") || item.getOrderStatus().equals("7") || item.getOrderStatus().equals("8") || item.getOrderStatus().equals("9"))&&(item.getOrderTypeId().equals("0")||item.getOrderTypeId().equals("2")||item.getOrderTypeId().equals("3"))) {
                                                                                          list.add(6);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("8") || item.getOrderStatus().equals("7"))&&(item.getOrderTypeId().equals("0")||item.getOrderTypeId().equals("2")||item.getOrderTypeId().equals("3"))) {
                                                                                          list.add(7);
                                                                                      }
                                                                                      if (item.getOrderStatus().toString().equals("9")&&(item.getOrderTypeId().equals("0")||item.getOrderTypeId().equals("2")||item.getOrderTypeId().equals("3"))) {
                                                                                          list.add(8);
                                                                                      }
                                                                                      if ((item.getOrderStatus().toString().equals("9")||item.getOrderStatus().toString().equals("10") )&& !item.getOrderTypeId().equals("4") && !item.getOrderTypeId().equals("1")) {
                                                                                          list.add(9);
                                                                                      }

                                                                                      Intent intent = new Intent(context, OrderDetailsActivity.class);
                                                                                      intent.putExtra("orderNum", item.getOrderNumber());
                                                                                      intent.putExtra("btnNum", (Serializable) list);
                                                                                      context.startActivity(intent);
                                                                                  }
                                                                              }

        );

//        Message message = new Message();
//        Bundle bundle = new Bundle();
//        bundle.putIntegerArrayList("btnList", (ArrayList<Integer>) list);
//        message.setData(bundle);//bundle传值，耗时，效率低
//        handler.sendMessage(message);//发送message信息
//        message.what = 0;//标志是哪个线程传数据

    }


    /**
     * dip转像素
     */
    public  int dip2px(float dpValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }
}
