package hb.xnwdw.com.wendangwang.gui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.OrderBalanceListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.FullyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ItemListBean;
import hb.xnwdw.com.wendangwang.netdata.entity.JifenData;
import hb.xnwdw.com.wendangwang.netdata.entity.LineCanNotCouponsData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderAdreessData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderBean;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderConpData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderGoodsInfo;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderItemsData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderStoreData;
import hb.xnwdw.com.wendangwang.netdata.entity.StageData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/14.
 */
public class OrderBalanceActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.orderbalance_list)
    RecyclerView orderbalanceList;
    @BindView(R.id.suerorder_btn)
    TextView suerorderBtn;
    @BindView(R.id.orderbalance_ziti_name)
    TextView orderbalanceZitiName;
    @BindView(R.id.orderbalance_ziti_phone)
    TextView orderbalanceZitiPhone;
    @BindView(R.id.goto_choose_shop)
    RelativeLayout gotoChooseShop;
    @BindView(R.id.orderbalance_ziti_adrasse)
    TextView orderbalanceZitiAdrasse;
    @BindView(R.id.goto_choose_myadrasse)
    RelativeLayout gotoChooseMyadrasse;
    @BindView(R.id.shangmen_name)
    TextView shangmenName;
    @BindView(R.id.shangmen_phone)
    TextView shangmenPhone;
    @BindView(R.id.shnagmen_moren)
    TextView shnagmenMoren;
    @BindView(R.id.shangmen_adrasse)
    TextView shangmenAdrasse;
    @BindView(R.id.choose_copond)
    TextView chooseCopond;
    @BindView(R.id.checkbox_jiti)
    CheckBox checkboxJiti;
    @BindView(R.id.checkbox_shangmen)
    CheckBox checkboxShangmen;
    @BindView(R.id.top_postag)
    TextView topPostag;
    @BindView(R.id.orderbalance_remakers)
    EditText orderbalanceRemakers;
    @BindView(R.id.orderbalance_jifen)
    TextView orderbalanceJifen;
    @BindView(R.id.cheackbox_jifendikou)
    CheckBox cheackboxJifendikou;
    @BindView(R.id.orderbalance_totalmony)
    TextView orderbalanceTotalmony;
    @BindView(R.id.orderbalance_youhui)
    TextView orderbalanceYouhui;
    @BindView(R.id.orderbalance_jifendikou)
    TextView orderbalanceJifendikou;
    @BindView(R.id.orderbalance_postage)
    TextView orderbalancePostage;
    @BindView(R.id.orderbalance_totalnum)
    TextView orderbalanceTotalnum;
    @BindView(R.id.orderbalance_totalmony_all)
    TextView orderbalanceTotalmonyAll;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.orderbalance_actyouhui)
    TextView orderbalanceActyouhui;
    @BindView(R.id.orderbalance_present_list)
    RecyclerView orderbalancePresentList;
    @BindView(R.id.conpound_name)
    TextView conpoundName;
    @BindView(R.id.shangmen_info)
    LinearLayout shangmenInfo;
    @BindView(R.id.shangmen_adrasse_nodata)
    TextView shangmenAdrasseNodata;
    //   @BindView(R.id.shangmen_adress_detail)
    //   TextView shangmenAdressDetail;
    private OrderBalanceListAdapter adpter;
    private String shoppeId = "0", shoppeName = "";
    private String couponCode = "0", coupId = "0";
    public String provence;
    private OrderBean orderData;
    private String adrass = "", LogisticsType = "2", phonenum = "", name = "";
    private double jifenMony = 0, canUsejifenMony = 0, pricticMoney = 0, orderMoney = 0, toatalMonyNoPostage = 0;
    private String defaultName, defaultPhone;
    private int postage = 0;
    private double conpMony = 0;

    public StageData stageData;
    public double toatalMony = 0;
    private double fullCat;
    private OrderAdreessData adreesData;
    private List<OrderGoodsInfo> orderGoodsInfoslist;
    private OrderStoreData storeData;
    private double itemsPrice = 0;
    private OrderConpData conpData;
    private LineCanNotCouponsData lineCanNotCouponsData;

    private int orderGoodsnum, orderTypeId = 0;//0为线上订单 1为线下订单 2为换货订单 3为奖品订单 4为优惠券订单
    private ItemListBean itembean;
    private List<ItemListBean> items;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_orderbalance;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    public String orderNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title.setText("确认订单");
        WDWApp.addDestoryActivity(OrderBalanceActivity.this, "Orderbalance");
        Intent intent = getIntent();
        orderGoodsInfoslist = (List<OrderGoodsInfo>) intent.getSerializableExtra("orderInfo");
        orderTypeId = intent.getIntExtra("orderType", 0);
        Log.d("OrderBalanceActivity", "orderTypeId:" + orderTypeId);
        LogUtils.d("OrderBalanceActivity", "orderGoodsInfoslist.size():" + orderGoodsInfoslist.size());
        LogUtils.d("OrderBalanceActivity", orderGoodsInfoslist.get(0).getItemId());
        items = new ArrayList<>();

        loadOrderData();


        //   loadOrderListInfo(orderNum);
        /**
         * 自提
         */
        checkboxJiti.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    postage = 0;
                    checkboxShangmen.setChecked(false);
                    LogisticsType = "1";
                    topPostag.setVisibility(View.GONE);
                    phonenum = (String) orderbalanceZitiPhone.getText();
                    name = (String) orderbalanceZitiName.getText();
                    CountFilalyMony();
                }

            }
        });
        /**
         * 送货上门
         */
        checkboxShangmen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    topPostag.setVisibility(View.VISIBLE);
                    LogisticsType = "2";
                    checkboxJiti.setChecked(false);
                    phonenum = (String) shangmenPhone.getText();
                    name = (String) shangmenName.getText();
                    adrass = (String) shangmenAdrasse.getText();
                    CountFilalyMony();
                } else {
                    topPostag.setVisibility(View.GONE);
                }

            }
        });
        /***
         *积分抵扣
         */
        cheackboxJifendikou.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if ((orderMoney - conpMony) >= canUsejifenMony) {
                        orderbalanceJifendikou.setText("¥" + canUsejifenMony);
                        jifenMony = canUsejifenMony;

                        CountFilalyMony();
                    } else {
                        orderbalanceJifendikou.setText("¥" + (orderMoney - conpMony));
                        jifenMony = (orderMoney - conpMony);
                        CountFilalyMony();
                    }

                } else {
                    jifenMony = 0;
                    orderbalanceJifendikou.setText("-¥" + jifenMony);
                    CountFilalyMony();
                }

            }
        });

    }

    /***
     * 显示Item LIst
     ***/
    private void showPagedata() {
        orderbalanceList.setLayoutManager(new FullyLinearLayoutManager(getApplicationContext()));
        orderbalanceList.setNestedScrollingEnabled(false);
        adpter = new OrderBalanceListAdapter(R.layout.item_ordeerdetail, items, OrderBalanceActivity.this);
        orderbalanceList.setAdapter(adpter);
    }

    /**
     * 点击事件监听
     *
     * @param view
     */
    @OnClick({R.id.back, R.id.suerorder_btn, R.id.goto_choose_myadrasse, R.id.goto_choose_shop, R.id.choose_copond, R.id.shangmen_adrasse_nodata})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.shangmen_adrasse_nodata:
                Intent intent3 = new Intent(this, MyAdressActivity.class);
                intent3.putExtra("tag", "OrderBalanceActivity");
                startActivityForResult(intent3, 1);
                break;
            //选择收货地址
            case R.id.goto_choose_myadrasse:
                Intent intent1 = new Intent(this, MyAdressActivity.class);
                intent1.putExtra("tag", "OrderBalanceActivity");
                startActivityForResult(intent1, 1);
                break;
            //门店自提
            case R.id.goto_choose_shop:
                AndPermission.with(OrderBalanceActivity.this)
                        .permission(Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                        .requestCode(100)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                Intent intent = new Intent(OrderBalanceActivity.this, SelfGetChooseShop.class);
                                intent.putExtra("shoppeId", shoppeId);
                                intent.putExtra("shoppeName", shoppeName);
                                startActivityForResult(intent, 2);
                            }
                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(OrderBalanceActivity.this, "位置权限获取失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();
                break;
            //选择优惠券
            case R.id.choose_copond:
                if (chooseCopond.getText().toString().equals("请选择优惠券")) {
                    Intent intent2 = new Intent(this, LineCouponsActivity.class);
               //     Intent intent2 = new Intent(this, LineCopuonActivity.class);
                    intent2.putExtra("cdata", conpData);
                    intent2.putExtra("cdata2", lineCanNotCouponsData);
                    startActivityForResult(intent2, 4);
                } else if (chooseCopond.getText().toString().equals("取消使用优惠券")) {
                    chooseCopond.setText("请选择优惠券");
                    conpMony = 0;
                    if ((orderMoney - conpMony) >= allJifenMoney) {
                        orderbalanceJifen.setText("使用" + (int) allJifen + "稳当积分抵扣" + allJifenMoney + "元");
                        canUsejifenMony = allJifenMoney;
                    } else {
                        if ((int) ((orderMoney - conpMony) * jifenProportin) > 0) {

                            orderbalanceJifen.setText("使用" + (int) ((orderMoney - conpMony) * jifenProportin) + "稳当积分抵扣" + (orderMoney - conpMony) + "元");
                        } else {
                            orderbalanceJifen.setText("使用" + 1 + "稳当积分抵扣" + (orderMoney - conpMony) + "元");
                        }
                        canUsejifenMony = (orderMoney - conpMony);
                    }
                    cheackboxJifendikou.setChecked(false);
                    jifenMony = 0;
                    orderbalanceJifendikou.setText("-¥" + jifenMony);
                    orderbalanceYouhui.setText("-¥" + conpMony);
                    conpoundName.setText("");
                    coupId = "0";
                    couponCode = "0";
                    CountFilalyMony();
                }

                break;
            /***
             * 结算提交数据并跳转支付
             */
            case R.id.suerorder_btn:

                commitOrderData();


                break;
        }
    }

    /***
     * 提交订单数据
     */
    private void commitOrderData() {
        orderData = new OrderBean();
        //订单金额
        orderData.setOrderMoney(orderMoney);
        //地址
        adrass = shangmenAdrasse.getText().toString();
        orderData.setAddressDetail(adrass);
        //优惠券换多少钱,
        orderData.setDiscountCouponDeduction(conpMony);
        //积分数,
        orderData.setIntegralDeduction(jifenMony * jifenProportin);
        //物流方式
        orderData.setLogisticsTypeId(LogisticsType);
        //电话,
        // phonenum = shangmenPhone.getText().toString();
        orderData.setPhoneNum(phonenum);
        //邮费,
        orderData.setPostage(postage);
        //门店编号
        orderData.setShoppeId(shoppeId);
        //门店名
        orderData.setShoppeName(shoppeName);
        //实付金额
        orderData.setPracticalMoney(toatalMony);
        //收货人信息,
        orderData.setRecName(name);
        //备注
        orderData.setUserMessage(orderbalanceRemakers.getText().toString());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP");
        jsonObject.put("memberId", "10");
        jsonObject.put("orderData", orderData);
        jsonObject.put("couponCode", couponCode);
        jsonObject.put("orderTypeId", orderTypeId);
        Log.d("OrderBalanceActivity", "orderTypeId:" + orderTypeId);
        jsonObject.put("coupId", coupId);
        jsonObject.put("itemList", items);
        String pamase = jsonObject.toJSONString();
        LogUtils.d("OrderBalanceActivity", pamase);
        if (checkboxJiti.isChecked() || checkboxShangmen.isChecked()) {
            if (name.equals("")) {
                Toast.makeText(this, "请填写收货信息", Toast.LENGTH_SHORT).show();
            } else {
                suerorderBtn.setBackgroundColor(getResources().getColor(R.color.greys));
                suerorderBtn.setClickable(false);
                suerorderBtn.setText("订单提交中");
                HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_ORDERCOMMIT, pamase, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("OrderBalanceActivity_l", "e:" + e);
                        suerorderBtn.setBackgroundColor(getResources().getColor(R.color.maincolor));
                        suerorderBtn.setClickable(true);
                        suerorderBtn.setText("提交订单");
                        Toast.makeText(OrderBalanceActivity.this, "请求超时", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        suerorderBtn.setBackgroundColor(getResources().getColor(R.color.maincolor));
                        suerorderBtn.setClickable(true);
                        suerorderBtn.setText("提交订单");
                        LogUtils.d("OrderBalanceActivity_l", response);
                        if (!response.contains(MConstant.HTTP404)&&JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                            Intent intent = new Intent(OrderBalanceActivity.this, PayActivity.class);
                            if (JSONObject.parseObject(response).get("obj") != null) {
                                orderNum = JSONObject.parseObject(response).get("obj").toString();
                            }
                            intent.putExtra("orderNum", orderNum);
                            CountFilalyMony();
                            intent.putExtra("mony", String.valueOf(toatalMony));
                            if (orderGoodsInfoslist.size() != 1) {
                                intent.putExtra("sourse", "OrderBalance_car");
                            } else {
                                intent.putExtra("sourse", "OrderBalance_good");
                            }

                            startActivity(intent);

                            if (orderTypeId == 3) {
                                Intent intent1 = new Intent(OrderBalanceActivity.this, PaySuccessActivity.class);
                                orderNum = JSONObject.parseObject(response).get("obj").toString();
                                intent1.putExtra("orderNum", orderNum);
                                intent1.putExtra("orderType", 3);
                                startActivity(intent1);
                            }
                        } else {
                            //     Toast.makeText(OrderBalanceActivity.this, "提交订单失败," + JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                            showPopWindow("提交订单失败," + JSONObject.parseObject(response).get("describe").toString());
                            suerorderBtn.setBackgroundColor(getResources().getColor(R.color.maincolor));
                            suerorderBtn.setClickable(true);
                            suerorderBtn.setText("提交订单");
                        }
                    }
                });
            }
        } else {
            Toast.makeText(this, "请填写收货信息", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * activity 返回值
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    private String coupName;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //地址信息
        if (requestCode == 1) {
            {
                if (resultCode == 1) {
                    //获取返回信息
                    if (data == null) {
                        Toast.makeText(this, "返回信息=null", Toast.LENGTH_LONG);
                    } else {
//                        shangmenAdressDetail.setVisibility(View.VISIBLE);
                        shangmenInfo.setVisibility(View.VISIBLE);
                        name = data.getStringExtra("name");
                        String num = data.getExtras().getString("phone");
                        adrass = data.getExtras().getString("adrass");
                        shangmenName.setText(name);
                        shangmenPhone.setText(num);
                        shangmenAdrasse.setText(adrass);
                        phonenum = num;
                        provence = data.getStringExtra("provence");
                        shangmenAdrasseNodata.setVisibility(View.GONE);
                        if (data.getIntExtra("pos", 0) != 0) {
                            shnagmenMoren.setVisibility(View.GONE);
                        } else {
                            shnagmenMoren.setVisibility(View.VISIBLE);
                        }

                        if (stageData != null) {
                            for (int i = 0; i < stageData.getDatas().size(); i++) {
                                if (stageData.getDatas().get(i).getProvence().contains(provence)) {
                                    if (orderMoney >= stageData.getDatas().get(i).getLowLimit()) {
                                        postage = 0;
                                    } else {
                                        topPostag.setVisibility(View.VISIBLE);
                                        postage = stageData.getDatas().get(i).getFreight();
                                    }
                                }
                            }
                        }
                        if (orderTypeId == 3) {
                            orderbalancePostage.setText("¥" + 0);
                            postage = 0;
                        }
                        topPostag.setText(provence+"，运费¥" + postage);
                        orderbalancePostage.setText("¥" + postage);
                        CountFilalyMony();
                    }

                } else {

                }
            }
            //门店自提信息
        } else if (requestCode == 2) {
            if (resultCode == 1) {

                orderbalanceZitiName.setText(data.getStringExtra("RecName"));
                orderbalanceZitiPhone.setText(data.getStringExtra("PhoneNum"));
                //        orderbalanceZitiAdrasse.setText(data.getStringExtra("adrass"));
                //显示门店名称
                orderbalanceZitiAdrasse.setText(data.getStringExtra("ShoppeName"));
                shoppeId = data.getStringExtra("ShoppeId");
                shoppeName = data.getStringExtra("ShoppeName");
                name = data.getStringExtra("RecName");
                phonenum = data.getStringExtra("PhoneNum");
            }

            //优惠券信息
        } else if (requestCode == 4) {
            if (resultCode == 1) {
                couponCode = data.getStringExtra("couponCode");
                coupId = data.getStringExtra("coupId");
                conpMony = Double.parseDouble(data.getStringExtra("coupMony"));
                coupName = data.getStringExtra("coupName");
                orderbalanceYouhui.setText("-¥" + conpMony);
                if (orderTypeId == 3) {
                    orderbalanceYouhui.setText("-¥" + 0);
                }
                chooseCopond.setText("取消使用优惠券");
                conpoundName.setText(coupName);

                if ((orderMoney - conpMony) >= allJifenMoney) {
                    orderbalanceJifen.setText("使用" + (int) allJifen + "稳当积分抵扣" + allJifenMoney + "元");
                    canUsejifenMony = allJifenMoney;
                } else {
                    orderbalanceJifen.setText("使用" + (int) ((orderMoney - conpMony) * jifenProportin) + "稳当积分抵扣" + (orderMoney - conpMony) + "元");
                    canUsejifenMony = (orderMoney - conpMony);
                }

                CountFilalyMony();
            }
        }
    }

    /**
     * 获取到可用积分
     */
    private double allJifenMoney, jifenProportin, allJifen;


    private void loadJifenData() {
        Map<String, String> map = new HashMap<>();
        map.put("sMemberID", "null");
        HtttpRequest.CreatGetRequst(UrlApi.URL_QUERYNUM, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("MyWenDangJiFenActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyWenDangJiFenActivity", response);
                if (!response.startsWith("{") || JSONObject.parseObject(response).get("dataStatus").toString().equals("-1")) {
                    Toast.makeText(OrderBalanceActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                } else {
                    JifenData data = JSON.parseObject(response, JifenData.class);
                    allJifenMoney = data.getObj().getSurplusIntegral() / data.getObj().getProportion();
                    allJifen = data.getObj().getSurplusIntegral();
                    jifenProportin = data.getObj().getProportion();
                    if (orderMoney - conpMony >= (data.getObj().getSurplusIntegral() / data.getObj().getProportion())) {
                        orderbalanceJifen.setText("使用" + (int) data.getObj().getSurplusIntegral() + "稳当积分抵扣" + data.getObj().getSurplusIntegral() / data.getObj().getProportion() + "元");
                        canUsejifenMony = data.getObj().getSurplusIntegral() / data.getObj().getProportion();
                    } else {
                       if((int) (orderMoney * data.getObj().getProportion())>0){
                           orderbalanceJifen.setText("使用" + (int) (orderMoney * data.getObj().getProportion()) + "稳当积分抵扣" + orderMoney + "元");
                       }else {
                           orderbalanceJifen.setText("使用" + 1 + "稳当积分抵扣" + orderMoney + "元");
                       }

                        canUsejifenMony = data.getObj().getSurplusIntegral() / data.getObj().getProportion();
                    }


                }
            }
        });
    }


    /**
     * 计算页面数据,总钱数
     */
    private void CountFilalyMony() {
        if (checkboxShangmen.isChecked()) {
            if (stageData != null && provence != null) {
                for (int i = 0; i < stageData.getDatas().size(); i++) {
                    if (stageData.getDatas().get(i).getProvence().contains(provence)) {
                        if ((orderMoney - jifenMony - conpMony - fullCat) >= stageData.getDatas().get(i).getLowLimit()) {
                            postage = 0;
                        } else {
                            postage = stageData.getDatas().get(i).getFreight();
                            topPostag.setVisibility(View.VISIBLE);
                            if (orderTypeId == 3) {
                                postage = 0;
                            }
                        }
                    }
                }
            } else {
                LogUtils.d("OrderBalanceActivity", "null");
            }
        } else {
            postage = 0;
        }
        if (orderTypeId == 3) {
            orderbalanceTotalmonyAll.setText("¥" + 0);
        }
        if ((orderMoney - jifenMony - conpMony - fullCat) <= 0) {
            toatalMony = postage;
        } else {
            toatalMony = orderMoney + postage - jifenMony - conpMony - fullCat;
        }
        topPostag.setText(provence+"，运费¥" + postage);
        orderbalancePostage.setText("¥" + postage);
        DecimalFormat df = new DecimalFormat("#####0.00");
        String toatalMonys = df.format(toatalMony);
        orderbalanceTotalmonyAll.setText("¥" + toatalMonys);

    }

//    /**
//     * 计算邮费
//     */
//    private void countPostage(){
//        toatalMonyNoPostage=
//
//
//    }

    /**
     * 获取页面信息
     */
    private void loadOrderData() {
        List<JSONObject> list = new ArrayList<>();
        if (orderGoodsInfoslist != null) {
            for (int i = 0; i < orderGoodsInfoslist.size(); i++) {
                JSONObject jsonObject1 = new JSONObject();
                jsonObject1.put("ItemID", orderGoodsInfoslist.get(i).getItemId());
                jsonObject1.put("Amount", orderGoodsInfoslist.get(i).getAmount());
                list.add(jsonObject1);
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP");
        jsonObject.put("memberId", WDWApp.getMenberId());
        jsonObject.put("itemList", list);
        String pams = jsonObject.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_OrderInfoByShapping, pams, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                //  LogUtils.d("OrderBalanceActivity", response);
                if (response.length() > 3000) {
                    for (int i = 0; i < response.length(); i += 3000) {
                        if (i + 3000 < response.length())
                            Log.i("rescounter" + i, response.substring(i, i + 3000));
                        else
                            Log.i("rescounter" + i, response.substring(i, response.length()));
                    }
                } else {
                    Log.i("resinfo", response);
                }
                if (!response.contains(MConstant.HTTP404)) {
                    JSONObject object = JSONObject.parseObject(response);
                    if (object.get("obj") != null) {
                        JSONArray jsonArray = (JSONArray) object.get("obj");
                        /***用户信息******/
                        jsonArray.get(0);

                        /***3、门店信息******/

                        String store = jsonArray.get(2).toString();
                        String stores = "{\"datas\":" + store + "}";
                        LogUtils.d("OrderBalanceActivity", stores);

                        storeData = JSON.parseObject(stores, OrderStoreData.class);
                        if (storeData.getDatas() != null && storeData.getDatas().size() != 0) {
                            //    orderbalanceZitiAdrasse.setText(storeData.getDatas().get(0).getStoreAddress());
                            orderbalanceZitiAdrasse.setText(storeData.getDatas().get(0).getStoreName());
                            shoppeId = String.valueOf(storeData.getDatas().get(0).getID());
                            shoppeName = storeData.getDatas().get(0).getStoreName();
                        } else {

                        }

                        /***4、订单商品清单******/
//                    JSONArray jsonArrayItems = (JSONArray) jsonArray.get(3);
                        Log.d("OrderBalanceActy_item", ((JSONArray) jsonArray.get(3)).toJSONString());

                        String orderitems = jsonArray.get(3).toString();
                        String itemss = "{\"datas\":" + orderitems + "}";
                        LogUtils.d("OrderBalanceActivity", itemss);

                        OrderItemsData itemsDatas = JSON.parseObject(itemss, OrderItemsData.class);

                        for (int i = 0; i < itemsDatas.getDatas().size(); i++) {
                            itembean = new ItemListBean();
                            List<ItemListBean.PresentItemBean> list = new ArrayList<ItemListBean.PresentItemBean>();
                            orderMoney += (itemsDatas.getDatas().get(i).getSubtotal());
                            orderGoodsnum += itemsDatas.getDatas().get(i).getAmount();
                            itembean.setAmount(itemsDatas.getDatas().get(i).getAmount());
                            itembean.setItemID(itemsDatas.getDatas().get(i).getID() + "");
                            itembean.setItemAct(itemsDatas.getDatas().get(i).getActivityContent());
                            itembean.setItemName(itemsDatas.getDatas().get(i).getItemName());
                            itembean.setItemPic(itemsDatas.getDatas().get(i).getItemPic());
                            itembean.setItemPrice(itemsDatas.getDatas().get(i).getDiscountUnit() + "");
                            itembean.setItemSizi(itemsDatas.getDatas().get(i).getItemSize());

                            if (itemsDatas.getDatas().get(i).getPresentItemList() != null) {
                                for (int j = 0; j < itemsDatas.getDatas().get(i).getPresentItemList().size(); j++) {
                                    ItemListBean.PresentItemBean presentItemBean = new ItemListBean.PresentItemBean();
                                    presentItemBean.setItemName(itemsDatas.getDatas().get(i).getPresentItemList().get(0).getItemName());
                                    presentItemBean.setPresentNum(itemsDatas.getDatas().get(i).getPresentItemList().get(0).getPresentNum());
                                    list.add(presentItemBean);
                                }
                            }
                            itembean.setPresentItemList(list);
                            items.add(itembean);
                        }

                        showPagedata();


                        DecimalFormat df = new DecimalFormat("#####0.00");
                        String orderMoneys = df.format(orderMoney);
                        orderbalanceTotalmony.setText("¥" + orderMoneys);
                        orderbalanceTotalnum.setText(orderGoodsnum + "");
                        CountFilalyMony();

                        loadJifenData();


                        /***5、未使用的可用优惠券******/
                        JSONArray jsonArrayConpount = (JSONArray) jsonArray.get(4);
                        LogUtils.d("OrderBalanceActivity", "jsonArrayConpount.size():" + jsonArrayConpount.size());

                        String conp = jsonArray.get(4).toString();
                        String conps = "{\"datas\":" + conp + "}";
                        LogUtils.d("OrderBalanceActivity", conps);

                        conpData = JSON.parseObject(conps, OrderConpData.class);

                        /**********未使用的不可用优惠券***********/




                        String cannotUseCoupon = jsonArray.get(7).toString();
                        String cannotUseCoupons = "{\"datas\":" + cannotUseCoupon + "}";
                        LogUtils.d("OrderBalanceActivity", cannotUseCoupons);
                        lineCanNotCouponsData = JSON.parseObject(cannotUseCoupons, LineCanNotCouponsData.class);


                        /***6、运费数据列表******/
                        String stage = jsonArray.get(5).toString();
                        String stages = "{\"datas\":" + stage + "}";
                        LogUtils.d("OrderBalanceActivity", stages);
                        stageData = JSON.parseObject(stages, StageData.class);


                        /***7、返回满减要少多少钱******/
                        fullCat = Double.parseDouble(jsonArray.get(6).toString());
                        orderbalanceActyouhui.setText("-¥" + fullCat);
                        CountFilalyMony();

                        /***2、收货地址******/
                        JSONArray jsonArrayAdress = (JSONArray) jsonArray.get(1);
                        LogUtils.d("OrderBalanceActivity", "jsonArrayAdress.size():" + jsonArrayAdress.size());

                        String adreess = jsonArray.get(1).toString();

                        String adrees = "{\"datas\":" + adreess + "}";
                        Log.d("OrderBalanceActivity", adrees);
                        adreesData = JSON.parseObject(adrees, OrderAdreessData.class);
                        if (adreesData.getDatas() != null && adreesData.getDatas().size() != 0) {
                            defaultName = adreesData.getDatas().get(0).getRecName();
                            defaultPhone = adreesData.getDatas().get(0).getPhoneNum();
                            orderbalanceZitiName.setText(defaultName);
                            orderbalanceZitiPhone.setText(defaultPhone);
                            shangmenName.setText(adreesData.getDatas().get(0).getRecName());
                            shangmenPhone.setText(adreesData.getDatas().get(0).getPhoneNum());
                            phonenum = adreesData.getDatas().get(0).getPhoneNum();
                            name = adreesData.getDatas().get(0).getRecName();
                            shangmenAdrasse.setText(adreesData.getDatas().get(0).getProvince() + adreesData.getDatas().get(0).getCity() + adreesData.getDatas().get(0).getCounty() + adreesData.getDatas().get(0).getAddressDetail());
                            provence = adreesData.getDatas().get(0).getProvince();

                            CountFilalyMony();

                        } else {
                            shangmenAdrasseNodata.setVisibility(View.VISIBLE);
                            shangmenInfo.setVisibility(View.INVISIBLE);
//                        shangmenAdressDetail.setVisibility(View.INVISIBLE);
                        }

                    }
                }
            }
        });
    }


    /**
     * t弹出窗口提示进入购物车修改订单
     */
    private void showPopWindow(String content) {
        final Dialog bottomDialog = new Dialog(OrderBalanceActivity.this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(OrderBalanceActivity.this).inflate(R.layout.popupwindow_changecart, null);
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
                startActivity(new Intent(OrderBalanceActivity.this, MainPagerActivity.class).putExtra("fr", 3));
                finish();
            }
        });

        bottomDialog.show();
    }
}
