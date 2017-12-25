package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.OrderDetailListAdapter;
import hb.xnwdw.com.wendangwang.gui.view.MyPopWindow;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * 订单详情
 * Created by Administrator on 2017/3/29.
 */


public class OrderDetailsActivity extends ActivityBase {
    String orderNum;
    @BindView(R.id.orderdetail_ordernum)
    TextView orderdetailOrdernum;
    @BindView(R.id.orderdetail_orderstatu)
    TextView orderdetailOrderstatu;
    @BindView(R.id.orderdetail_distribute_name)
    TextView orderdetailDistributeName;
    @BindView(R.id.orderdetail_distribute_phone)
    TextView orderdetailDistributePhone;
    @BindView(R.id.logistical_time)
    TextView logisticalTime;
    @BindView(R.id.orderdetail_logisticalinfo)
    LinearLayout orderdetailLogisticalinfo;
    @BindView(R.id.orderdetail_username)
    TextView orderdetailUsername;
    @BindView(R.id.orderdetail_phone)
    TextView orderdetailPhone;
    @BindView(R.id.orderdetail_adrasse)
    TextView orderdetailAdrasse;
    @BindView(R.id.orderdetail_remark)
    TextView orderdetailRemark;
    @BindView(R.id.orderdetail_goodsprice)
    TextView orderdetailGoodsprice;
    @BindView(R.id.orderdetail_youhui)
    TextView orderdetailYouhui;
    @BindView(R.id.orderdetail_jifen)
    TextView orderdetailJifen;
    @BindView(R.id.orderdetail_yunfei)
    TextView orderdetailYunfei;
    @BindView(R.id.orderdetail_total)
    TextView orderdetailTotal;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.orderdetail_orderdetailslist)
    RecyclerView orderdetailOrderdetailslist;
    @BindView(R.id.orderdetail_btn_1)
    Button orderdetailBtn1;
    @BindView(R.id.orderdetail_btn_2)
    Button orderdetailBtn2;
    @BindView(R.id.orderdetail_btn_3)
    Button orderdetailBtn3;
    @BindView(R.id.orderdetail_btn_4)
    Button orderdetailBtn4;
    @BindView(R.id.orderdetail_btn_5)
    Button orderdetailBtn5;
    @BindView(R.id.orderdetail_btn_6)
    Button orderdetailBtn6;
    @BindView(R.id.orderdetail_btn_7)
    Button orderdetailBtn7;
    @BindView(R.id.orderdetail_btn_8)
    Button orderdetailBtn8;
    @BindView(R.id.orderdetail_btn_9)
    Button orderdetailBtn9;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.orderdetail_name_ll)
    LinearLayout orderdetailNameLl;
    @BindView(R.id.orderdetail_address_ll)
    LinearLayout orderdetailAddressLl;
    private List<Integer> list;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_orderdetails;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title.setText("订单详情");
        Intent intent = getIntent();
        orderNum = intent.getStringExtra("orderNum");
        list = intent.getIntegerArrayListExtra("btnNum");

        loadData(orderNum);
    }

    private OrderDetailListAdapter adapter;
    private String str;

    /**
     * 订单详情
     *
     * @param orderNum
     */
    private void loadData(String orderNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("orderNum", orderNum);
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETODERDETAL, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OrderDetailsActivity", response);
                OrderDetailData orderDetailData = JSON.parseObject(response, OrderDetailData.class);
                orderdetailOrdernum.setText(orderDetailData.getObj().getOrderSummary().getOrderNumber());

                switch (orderDetailData.getObj().getOrderSummary().getOrderStatus()) {
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
                orderdetailOrderstatu.setText(str);
              String orderState=  orderDetailData.getObj().getOrderSummary().getOrderStatus();
                String orderType=orderDetailData.getObj().getOrderSummary().getOrderTypeId();
                //取消订单
                if (orderDetailData.getObj().getOrderOption().getCanCancel()==1) {
                    orderdetailBtn1.setVisibility(View.VISIBLE);
                }else {
                    orderdetailBtn1.setVisibility(View.GONE);
                }
                //立即支付
                if (orderDetailData.getObj().getOrderOption().getCanPay()==1){
                    orderdetailBtn2.setVisibility(View.VISIBLE);
                }else {
                    orderdetailBtn2.setVisibility(View.GONE);
                }
                //删除订单
                if (orderDetailData.getObj().getOrderOption().getCanDel()==1) {
                    orderdetailBtn3.setVisibility(View.VISIBLE);

                }else {
                    orderdetailBtn3.setVisibility(View.GONE);
                }
                //再次购买
                if (orderDetailData.getObj().getOrderOption().getCanBuyAgain()==1) {
                    orderdetailBtn4.setVisibility(View.VISIBLE);
                }else {
                    orderdetailBtn4.setVisibility(View.GONE);
                }
                //>申请退款
                if (orderDetailData.getObj().getOrderOption().getCanReturnMoney()==1) {
                    orderdetailBtn5.setVisibility(View.VISIBLE);

                }else {
                    orderdetailBtn5.setVisibility(View.GONE);
                }
                //查看物流
                if (orderDetailData.getObj().getOrderOption().getCanLogistics()==1) {
                    orderdetailBtn6.setVisibility(View.VISIBLE);
                }else {
                    orderdetailBtn6.setVisibility(View.GONE);
                }
                //确认收货
                if (orderDetailData.getObj().getOrderOption().getCanBeSure()==1) {
                    orderdetailBtn7.setVisibility(View.VISIBLE);

                }else {
                    orderdetailBtn7.setVisibility(View.GONE);
                }
                //去评价
                if (orderDetailData.getObj().getOrderOption().getCanEvaluate()==1) {
                    orderdetailBtn8.setVisibility(View.VISIBLE);

                }else {
                    orderdetailBtn8.setVisibility(View.GONE);
                }
                //售后
                if (orderDetailData.getObj().getOrderOption().getCanAfterSale()==1) {
                    orderdetailBtn9.setVisibility(View.VISIBLE);

                }else {
                    orderdetailBtn9.setVisibility(View.GONE);
                }

               if(orderType.equals("1")||orderType.equals("4")){
                   orderdetailAddressLl.setVisibility(View.GONE);
               }else {
                   orderdetailAddressLl.setVisibility(View.VISIBLE);
               }
                //收货地址 收货门店
                if(orderDetailData.getObj().getOrderSummary().getLogisticsTypeId().equals("2")){
                    orderdetailAdrasse.setText(orderDetailData.getObj().getOrderSummary().getAddressDetail());
                }else {
                    orderdetailAdrasse.setText(orderDetailData.getObj().getOrderSummary().getShoppeName());
                }

                orderdetailRemark.setText(orderDetailData.getObj().getOrderSummary().getUserMessage());
                orderdetailPhone.setText(orderDetailData.getObj().getOrderSummary().getPhoneNum());
                orderdetailUsername.setText(orderDetailData.getObj().getOrderSummary().getRecName());
                orderdetailGoodsprice.setText(orderDetailData.getObj().getOrderSummary().getOrderMoney());
                orderdetailYouhui.setText(orderDetailData.getObj().getOrderSummary().getDiscountCouponDeduction());
                orderdetailJifen.setText(orderDetailData.getObj().getOrderSummary().getIntegralDeduction());
                orderdetailYunfei.setText(orderDetailData.getObj().getOrderSummary().getPostage());
                orderdetailTotal.setText(orderDetailData.getObj().getOrderSummary().getPracticalMoney());
                MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(OrderDetailsActivity.this);
                orderdetailOrderdetailslist.setNestedScrollingEnabled(false);
                orderdetailOrderdetailslist.setLayoutManager(myLinearLayoutManager);
                adapter = new OrderDetailListAdapter(R.layout.item_ordeerdetail, orderDetailData.getObj().getOrderDetails());

                orderdetailOrderdetailslist.setAdapter(adapter);


            }
        });
    }

    /**
     * "取消订单":
     */
    private void cancelOrder(String orderNumber) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("memberID", WDWApp.getMenberId());
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_CANCELORDER, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder", response);
                String describe = JSONObject.parseObject(response).get("describe").toString();
                Toast.makeText(OrderDetailsActivity.this, describe, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * "删除订单":
     */
    private void deleteOrder(String orderNumber) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("memberID", WDWApp.getMenberId());
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_DELETEORDER, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder", response);
                String describe = JSONObject.parseObject(response).get("describe").toString();
                Toast.makeText(OrderDetailsActivity.this, describe, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * "立即评价":
     */
    private void evluatNow(String orderNumber) {
        Intent intent = new Intent(OrderDetailsActivity.this, EvaluateNowActivity.class);
        intent.putExtra("orderNumber", orderNumber);
        startActivity(intent);
    }

    /**
     * 立即支付
     */
    private void payNow(String orderNumber) {
        canPay(orderNumber,"0");

    }

    /**
     * 查看订单
     */
    private void seeOrder(String orderNumber) {
        Intent intent = new Intent(getApplicationContext(), OrderDetailsActivity.class);
        intent.putExtra("orderNum", orderNumber);
        startActivity(intent);
    }

    /**
     * 确认收货":
     */
    private void sureTakeOver(String orderNumber) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("memberID", WDWApp.getMenberId());
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_SURETAKEOVER, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder_s", response);
                String describe = JSONObject.parseObject(response).get("describe").toString();
                Toast.makeText(OrderDetailsActivity.this, describe, Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 查看物流":
     */
    private void seeLogistics(String orderNumber) {
        Intent intent = new Intent(this, SeeLogisticsActivity.class);
        intent.putExtra("orderNumber", orderNumber);
        startActivity(intent);
    }


    /**
     * 再次购买
     */
    private void show1(final String ordernum) {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.buyagain, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        LinearLayout buyit_no;
        LinearLayout buyit_ok;
        buyit_no = (LinearLayout) contentView.findViewById(R.id.byagain_no);
        buyit_ok = (LinearLayout) contentView.findViewById(R.id.byagain_ok);
        buyit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });

        buyit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsobj = new JSONObject();
                jsobj.put("num", ordernum);
                jsobj.put("type", 1);
                jsobj.put("memberID", WDWApp.getMenberId());
                String pamString = jsobj.toJSONString();
                HtttpRequest.CreatPostRequst(UrlApi.URL_BUYAGAIN, pamString, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("MyOrder", response);
                        Intent intent = new Intent(OrderDetailsActivity.this, MainPagerActivity.class);
                        intent.putExtra("fr", 3);
                        startActivity(intent);
                    }
                });
            }
        });

    }


    /**
     * 售后
     *
     * @param orderNumber
     */
    private void afterServer(String orderNumber) {
        Intent intent = new Intent(OrderDetailsActivity.this, My_sales.class);
        intent.putExtra("url", "/wdw/page/mb/my_sales1.html?orderNumber=" + orderNumber + "&app=app");
        startActivity(intent);
    }

    private void initBtn(List<Integer> btnNum) {
        for (int i = 0; i < btnNum.size(); i++) {
            switch (btnNum.get(1)) {
                case 1:
                    orderdetailBtn1.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    orderdetailBtn2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    orderdetailBtn3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    orderdetailBtn4.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    orderdetailBtn5.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    orderdetailBtn6.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    orderdetailBtn7.setVisibility(View.VISIBLE);
                    break;
                case 8:
                    orderdetailBtn8.setVisibility(View.VISIBLE);
                    break;
                case 9:
                    orderdetailBtn9.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @OnClick({R.id.back, R.id.orderdetail_btn_1, R.id.orderdetail_btn_2, R.id.orderdetail_btn_3, R.id.orderdetail_btn_4, R.id.orderdetail_btn_5, R.id.orderdetail_btn_6, R.id.orderdetail_btn_7, R.id.orderdetail_btn_8, R.id.orderdetail_btn_9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.orderdetail_btn_1:
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);
                builder.setMessage("确定取消订单？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelOrder(orderNum);
                        dialog.dismiss();
                        startActivity(new Intent(OrderDetailsActivity.this,MainPagerActivity.class).putExtra("fr",4));
                        finish();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.orderdetail_btn_2:
                payNow(orderNum);
                break;
            case R.id.orderdetail_btn_3:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(OrderDetailsActivity.this);
                builder1.setMessage("确定删除订单？");
                builder1.setTitle("提示");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteOrder(orderNum);
                        dialog.dismiss();
                        startActivity(new Intent(OrderDetailsActivity.this,MainPagerActivity.class).putExtra("fr",4));
                        finish();
                    }
                });

                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.create().show();
                break;
            case R.id.orderdetail_btn_4:
                show1(orderNum);
                break;
            case R.id.orderdetail_btn_5:
                showDialog(orderNum);
                break;
            case R.id.orderdetail_btn_6:
                seeLogistics(orderNum);
                break;
            case R.id.orderdetail_btn_7:
                MyPopWindow popWindow=new MyPopWindow(OrderDetailsActivity.this,R.layout.popupwindow_cart,"取消","确认收货");
                popWindow.setRightClick(new MyPopWindow.RightClick() {
                    @Override
                    public void rightClickListerner() {
                        sureTakeOver(orderNum);
                    }
                });
                break;
            case R.id.orderdetail_btn_8:
                evluatNow(orderNum);
                break;
            case R.id.orderdetail_btn_9:
                afterServer(orderNum);
                break;
        }
    }

    /**
     * 申请退款":
     */


    private void showDialog(final String orderNumber) {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.buyagain, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        LinearLayout buyit_no;
        LinearLayout buyit_ok;
        TextView tv;
        buyit_no = (LinearLayout) contentView.findViewById(R.id.byagain_no);
        buyit_ok = (LinearLayout) contentView.findViewById(R.id.byagain_ok);
        tv = (TextView) contentView.findViewById(R.id.buyagain_tv);
        tv.setText("确认申请退款吗？");

        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        buyit_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });

        buyit_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyRefund(orderNumber);
                bottomDialog.dismiss();
                startActivity(new Intent(OrderDetailsActivity.this,MainPagerActivity.class).putExtra("fr",4));
                finish();
            }
        });
    }

    private void applyRefund(String orderNumber) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        String pamString = jsonObject.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_ApplyRefund, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(OrderDetailsActivity.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(OrderDetailsActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(OrderDetailsActivity.this, "操作失败"+JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void canPay(final String payOrderNum, final String type) {
        JSONObject job = new JSONObject();
        job.put("dataSource", "APP");
        job.put("memberId", "");
        job.put("orderNum", payOrderNum);
        String pamString = job.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_OrderPayDetection, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PayActivity", "e:" + e);
                Toast.makeText(OrderDetailsActivity.this, "网络超时", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("PayActivity_c", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    //TODO:
                    Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                    intent.putExtra("orderNum", payOrderNum);
                    startActivity(intent);

                } else {
                    showPopWindow(JSONObject.parseObject(response).get("describe").toString(), payOrderNum);
                }
            }
        });

    }


    /**
     * t弹出窗口提示进入购物车修改订单
     */
    private void showPopWindow(String content, final String orderNum) {
        final Dialog bottomDialog = new Dialog(OrderDetailsActivity.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(OrderDetailsActivity.this).inflate(R.layout.popupwindow_changecart, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        //   layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        TextView tv1 = (TextView) contentView.findViewById(R.id.content);
        TextView tv2 = (TextView) contentView.findViewById(R.id.goto_cart);

        tv1.setText(content);
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                UpdateLoseOrderByShoppCar(orderNum);
            }
        });

        bottomDialog.show();
    }


    /**
     * 整单加入购物车修改
     */

    private void UpdateLoseOrderByShoppCar(String ordernum) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("", ordernum);
        String pams = jsonObject.toJSONString();
        Log.d("MyOrder", pams);
        HtttpRequest.CreatPostRequst(UrlApi.URL_UpdateLoseOrderByShoppCar, ordernum, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MyOrder_ShoppCar", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyOrder_ShoppCar", response);
                if(!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        Intent intent = new Intent(OrderDetailsActivity.this, MainPagerActivity.class);
                        intent.putExtra("fr", 3);
                        startActivity(intent);
                        finish();
//                    EventBus.getDefault().post("shopingcar");
//                    finish();
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}