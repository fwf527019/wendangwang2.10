package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.MyOrderListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyOrderListData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/12.
 */

public class MyOrder extends ActivityBase {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.myorder_all)
    TextView myorderAll;
    @BindView(R.id.myorder_daizhifu)
    TextView myorderDaizhifu;
    @BindView(R.id.myorder_daishouhuo)
    TextView myorderDaishouhuo;
    @BindView(R.id.myorder_daipingjia)
    TextView myorderDaipingjia;
    @BindView(R.id.myorder_xianxiadingdan)
    TextView myorderXianxiadingdan;
    @BindView(R.id.myorder_recyvler)
    RecyclerView myorderRecyvler;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.no_data_page)
    LinearLayout noDataPage;
    private MyOrderListAdapter adapter1;
    private MyOrderListData data;
    private Handler mhandler;
    private String titalTag;
    public static String statue;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myorder;
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
        title.setText("我的订单");
        Intent intent = getIntent();
        titalTag = intent.getStringExtra("titalTag");
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        btnNum = (List<Integer>) msg.getData().get("btnList");
                        Log.d("MyOrder", "btnNum:" + btnNum);
                        break;

                }
            }
        };


        switch (titalTag) {
            case "0":
                loadDatas("-1");
                statue = "-1";
                initTextColor(1);
                break;
            case "1":
                initTextColor(2);
                statue = "1";
                loadDatas("1");
                break;
            case "2":
                initTextColor(3);
                statue = null;
                loadDatass();
                break;
            case "3":
                statue = "9";
                initTextColor(4);
                loadDatas("9");
                break;
        }

    }

    private String seskey;
    private List<Integer> btnNum;

    /**
     * 根据状态获取订单列表
     *
     * @param state
     */
    private void loadDatas(String state) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("state", state);
        map.put("page", "1");
        map.put("pageSize", "50");
        map.put("memberID", WDWApp.getMenberId());
        HtttpRequest.CheackIsLoginGet(this, UrlApi.URL_GETODERLIST, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("MyOrder", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder", response);
                if (!(response.contains(MConstant.HTTP404))) {
                    data = JSON.parseObject(response, MyOrderListData.class);
                    if (data.getOrders().size() == 0 || data.getOrders() == null) {
                        noDataPage.setVisibility(View.VISIBLE);
                    } else {
                        noDataPage.setVisibility(View.GONE);
                    }
                    MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getApplicationContext());
                    myorderRecyvler.setLayoutManager(myLinearLayoutManager);
                    adapter1 = new MyOrderListAdapter(R.layout.item_myorder, data.getOrders(), mhandler, MyOrder.this);
                    myorderRecyvler.setAdapter(adapter1);
//                    adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                        @Override
//                        public void onLoadMoreRequested() {
//
//                        }
//                    },myorderRecyvler);
                    adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public boolean onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                            final String orderNumber = data.getOrders().get(position).getOrderNumber();
                            switch (view.getId()) {
                                //取消订单
                                case R.id.myorder_all_btn_1:
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MyOrder.this);
                                    builder.setMessage("确定取消订单？");
                                    builder.setTitle("提示");
                                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelOrder(orderNumber, position);
//                                            data.getOrders().remove(position);
//                                            adapter1.notifyItemRemoved(position);
                                            dialog.dismiss();
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
                                //立即支付
                                case R.id.myorder_all_btn_2:
                                    payNow(orderNumber, data.getOrders().get(position).getOrderTypeId());
                                    break;
                                //删除订单
                                case R.id.myorder_all_btn_3:
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(MyOrder.this);
                                    builder1.setMessage("确定删除订单？");
                                    builder1.setTitle("提示");
                                    builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
//                                            data.getOrders().remove(position);
//                                            adapter1.notifyItemRemoved(position);
                                            deleteOrder(orderNumber, position);
                                            dialog.dismiss();
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
                                //再次购买
                                case R.id.myorder_all_btn_4:
                                    show1(orderNumber);
                                    break;
                                //申请退款
                                case R.id.myorder_all_btn_5:
                                    showDialog(orderNumber, position);
                                    break;
                                //查看物流
                                case R.id.myorder_all_btn_6:
                                    seeLogistics(orderNumber);
                                    break;
                                //确认收货
                                case R.id.myorder_all_btn_7:
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MyOrder.this);
                                    builder2.setMessage("是否确定收货？");
                                    builder2.setTitle("提示");
                                    builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            sureTakeOver(orderNumber);
                                            dialog.dismiss();
                                        }
                                    });
                                    builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    builder2.create().show();


                                    break;
                                //去评价
                                case R.id.myorder_all_btn_8:
                                    evluatNow(orderNumber);
                                    break;
                                //售后
                                case R.id.myorder_all_btn_9:
                                    afterServer(orderNumber);
                                    break;

                            }
                            return false;
                        }
                    });

                    adapter1.notifyDataSetChanged();
                } else {
                    Toast.makeText(MyOrder.this, "没有数据", Toast.LENGTH_SHORT).show();
                    if (data != null) {
                        myorderRecyvler.removeAllViews();
                        data.getOrders().clear();
                        if (list != null) {
                            list.clear();
                        }
                        adapter1.notifyDataSetChanged();
                    } else if (adapter1 != null) {
                        list.clear();
                        myorderRecyvler.removeAllViews();
                        adapter1.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    /**
     * 售后
     *
     * @param orderNumber
     */
    private void afterServer(String orderNumber) {
        Intent intent = new Intent(MyOrder.this, MySaleAfter.class);
//        intent.putExtra("url", "/wdw/page/mb/my_sales1.html?orderNumber=" + orderNumber + "&app=app");
        startActivity(intent);
    }


    @OnClick({R.id.back, R.id.myorder_all, R.id.myorder_daizhifu, R.id.myorder_daishouhuo, R.id.myorder_daipingjia, R.id.myorder_xianxiadingdan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            //全部订单
            case R.id.myorder_all:
                loadDatas("-1");
                statue = "-1";
                initTextColor(1);
                break;
            //待支付
            case R.id.myorder_daizhifu:
                initTextColor(2);
                statue = "1";
                loadDatas("1");
                break;
            //待收货 5-8
            case R.id.myorder_daishouhuo:
                initTextColor(3);
                loadDatass();
                break;
            //待评价
            case R.id.myorder_daipingjia:
                initTextColor(4);
                statue = null;
                loadDatas("9");
                break;
            //线下订单
            case R.id.myorder_xianxiadingdan:
                loadDatas("-10");
                statue = "-10";
                initTextColor(5);
                break;
        }
    }

    private void initTextColor(int pos) {
        TextView[] views1 = {myorderAll, myorderDaizhifu, myorderDaishouhuo, myorderDaipingjia, myorderXianxiadingdan};
        View[] views2 = {view1, view2, view3, view4, view5};

        for (int i = 0; i < 5; i++) {
            if (pos - 1 == i) {
                views1[i].setTextColor(getResources().getColor(R.color.maincolor));
                views2[i].setVisibility(View.VISIBLE);
            } else {
                views1[i].setTextColor(getResources().getColor(R.color.gry));
                views2[i].setVisibility(View.INVISIBLE);
            }
        }
    }


    /**
     * "取消订单":
     */
    private void cancelOrder(String orderNumber, final int pos) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("memberID", WDWApp.getMenberId());
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_CANCELORDER, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MyOrder.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder", response);
                String describe = JSONObject.parseObject(response).get("describe").toString();
                Toast.makeText(MyOrder.this, describe, Toast.LENGTH_SHORT).show();
                loadNewData();
            }
        });
    }

    /**
     * "删除订单":
     */
    private void deleteOrder(String orderNumber, final int pos) {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("memberID", WDWApp.getMenberId());
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_DELETEORDER, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyOrder", response);
                String describe = JSONObject.parseObject(response).get("describe").toString();
                Toast.makeText(MyOrder.this, describe, Toast.LENGTH_SHORT).show();
                loadNewData();


            }
        });
    }

    /**
     * "立即评价":
     */
    private void evluatNow(String orderNumber) {
        //    Intent intent = new Intent(MyOrder.this, MyGoodsEvaluateActivity.class);
        Intent intent = new Intent(MyOrder.this, EvaluateNowActivity.class);
        intent.putExtra("orderNumber", orderNumber);
        startActivity(intent);
    }

    /**
     * 立即支付
     */
    private void payNow(String orderNumber, String type) {
        canPay(orderNumber, type);
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
                Toast.makeText(MyOrder.this, describe, Toast.LENGTH_SHORT).show();
                loadNewData();

            }
        });
    }

    /**
     * 查看物流":
     */
    private void seeLogistics(String orderNumber) {
        Intent intent = new Intent(MyOrder.this, SeeLogisticsActivity.class);
        intent.putExtra("orderNumber", orderNumber);
        startActivity(intent);
    }

    /**
     * 申请退款":
     */

    private void showDialog(final String orderNumber, final int pos) {
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
                applyRefund(orderNumber, pos);
                bottomDialog.dismiss();

            }
        });
    }


    private void applyRefund(String orderNumber, final int pos) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderNumber", orderNumber);
        String pamString = jsonObject.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_ApplyRefund, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(MyOrder.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(MyOrder.this, "操作成功", Toast.LENGTH_SHORT).show();
                    loadNewData();


                } else {
                    Toast.makeText(MyOrder.this, "操作失败" + JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
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
                        bottomDialog.dismiss();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("MyOrder", response);
                        bottomDialog.dismiss();
                        Intent intent = new Intent(MyOrder.this, ShoppingCart1.class);
                        startActivity(intent);

                    }
                });
            }
        });

        bottomDialog.show();

    }

    private List<MyOrderListData.OrdersBean> list;
    private MyOrderListData myorderdatas;

    /**
     * 待收货状态
     */
    private void loadDatass() {
        list = new ArrayList<>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("state", "-1");
        map.put("page", "1");
        map.put("pageSize", "100");
        map.put("memberID", WDWApp.getMenberId());
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETODERLIST, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyOrder", response);
                myorderdatas = JSON.parseObject(response, MyOrderListData.class);
                if (myorderdatas.getOrders() == null) {

                } else {
                    for (int i = 0; i < myorderdatas.getOrders().size(); i++) {
                        if (myorderdatas.getOrders().get(i).getOrderStatus().equals("5") || myorderdatas.getOrders().get(i).getOrderStatus().equals("6") || myorderdatas.getOrders().get(i).getOrderStatus().equals("7") || myorderdatas.getOrders().get(i).getOrderStatus().equals("8")) {
                            list.add(myorderdatas.getOrders().get(i));
                        }
                        myorderRecyvler.setLayoutManager(new MyLinearLayoutManager(getApplicationContext()));
                        if (list.size() == 0 || list == null) {
                            noDataPage.setVisibility(View.VISIBLE);
                        } else {
                            noDataPage.setVisibility(View.GONE);
                        }
                        adapter1 = new MyOrderListAdapter(R.layout.item_myorder, list, mhandler, MyOrder.this);
                        myorderRecyvler.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();

                        adapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public boolean onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                final String orderNumber = list.get(position).getOrderNumber();
                                switch (view.getId()) {

                                    case R.id.myorder_all_btn_1:
                                        AlertDialog.Builder builder = new AlertDialog.Builder(MyOrder.this);
                                        builder.setMessage("确定取消订单？");
                                        builder.setTitle("提示");
                                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                cancelOrder(orderNumber, position);
                                                dialog.dismiss();
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
                                    case R.id.myorder_all_btn_2:
                                        payNow(orderNumber, data.getOrders().get(position).getOrderTypeId());
                                        break;
                                    case R.id.myorder_all_btn_3:
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MyOrder.this);
                                        builder1.setMessage("确定删除订单？");
                                        builder1.setTitle("提示");
                                        builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                deleteOrder(orderNumber, position);
                                                dialog.dismiss();
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
                                    case R.id.myorder_all_btn_4:
                                        show1(orderNumber);
                                        break;
                                    //申请退款
                                    case R.id.myorder_all_btn_5:
                                        showDialog(orderNumber, position);
                                        break;
                                    case R.id.myorder_all_btn_6:
                                        seeLogistics(orderNumber);
                                        break;
                                    case R.id.myorder_all_btn_7:
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MyOrder.this);
                                        builder2.setMessage("是否确定收货？");
                                        builder2.setTitle("提示");
                                        builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                sureTakeOver(orderNumber);
//                                                data.getOrders().remove(position);
//                                                adapter1.notifyItemRemoved(position);
                                                dialog.dismiss();
                                            }
                                        });
                                        builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        builder2.create().show();

                                        break;
                                    case R.id.myorder_all_btn_8:
                                        evluatNow(orderNumber);
                                        break;
                                    case R.id.myorder_all_btn_9:
                                        afterServer(orderNumber);
                                        break;

                                }
                                return false;
                            }
                        });

                    }
                }
            }
        });
    }


    /**
     * 判断是否能够支付
     *
     * @param payOrderNum
     * @param type
     */
    private void canPay(final String payOrderNum, final String type) {
        startProgressDialog("加载中...");
        JSONObject job = new JSONObject();
        job.put("dataSource", "APP");
        job.put("memberId", "");
        job.put("orderNum", payOrderNum);
        String pamString = job.toJSONString();
        Log.d("MyOrder", pamString);
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_OrderPayDetection, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PayActivity", "e:" + e);
                stopProgressDialog();
                Toast.makeText(MyOrder.this, "网络超时", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("PayActivity_c", response);
                stopProgressDialog();
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        //TODO:
                        Intent intent = new Intent(getApplicationContext(), PayActivity.class);
                        intent.putExtra("orderNum", payOrderNum);
                        if ("1".equals(type)) {
                            intent.putExtra("state", "offline");
                        }
                        startActivity(intent);

                    } else {
                        showPopWindow(JSONObject.parseObject(response).get("describe").toString(), payOrderNum);

                    }
                } else {
                    Toast.makeText(MyOrder.this, "请重试", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    /**
     * t弹出窗口提示进入购物车修改订单
     */
    private void showPopWindow(String content, final String orderNum) {
        final Dialog bottomDialog = new Dialog(MyOrder.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(MyOrder.this).inflate(R.layout.popupwindow_changecart, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
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
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_UpdateLoseOrderByShoppCar, ordernum, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MyOrder_ShoppCar", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyOrder_ShoppCar", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Intent intent = new Intent(MyOrder.this, ShoppingCart1.class);
                    startActivity(intent);
                    finish();
//                    EventBus.getDefault().post("shopingcar");
//                    finish();
                } else {
                    Toast.makeText(MyOrder.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    /**
     * 刷新数据
     */
    private void loadNewData() {
        if (statue != null) {
            loadDatas(statue);
        } else {
            loadDatass();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  Log.d("MyOrder_onDestroy", statue);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //  Log.d("MyOrder_onRestart", statue);
    }




}
