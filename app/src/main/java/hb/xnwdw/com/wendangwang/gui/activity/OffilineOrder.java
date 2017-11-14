package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
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
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OfflineGoodData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/11.
 */

public class OffilineOrder extends ActivityBase {
    @BindView(R.id.offlin_img)
    SimpleDraweeView offlinImg;
    @BindView(R.id.offline_miaoshu)
    TextView offlineMiaoshu;
    @BindView(R.id.offline_price)
    TextView offlinePrice;
    @BindView(R.id.offline_oldprice)
    TextView offlineOldprice;
    @BindView(R.id.offline_copuon)
    TextView offlineCopuon;
    @BindView(R.id.offline_getcopoun)
    TextView offlineGetcopoun;
    @BindView(R.id.offlin_jian)
    ImageView offlinJian;
    @BindView(R.id.offlin_num)
    TextView offlinNum;
    @BindView(R.id.add)
    ImageView add;
    @BindView(R.id.offlin_totalmony_all)
    TextView offlinTotalmonyAll;
    @BindView(R.id.offlin_suerorder_btn)
    TextView offlinSuerorderBtn;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    private String itemId;
    private String storeId;
    private int num = 1;
    private int coupId = 0;
    private String couponCode = "''";
    private double totoalMoney = 0,goodPrice=0,totoalMoneyRely=0;
    private double DiscountCouponDeduction = 0;
    public static String storeName;
    private int coupLimit = 0;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_offilineorder;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        offlinNum.setText(num + "");
        offlineCopuon.setText(DiscountCouponDeduction + "元");
        itemId = intent.getStringExtra("itemId");
        storeId = intent.getStringExtra("storeId");
        loadOrderData(itemId, storeId);


    }

    /**
     * 获取商品详情
     *
     * @param itemId
     * @param storeId
     */
    private OfflineGoodData data;

    private void loadOrderData(String itemId, final String storeId) {
        Map<String, String> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("storeId", storeId);
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetOfflineItemInfo, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OffilineOrder", response);
                data = JSON.parseObject(response, OfflineGoodData.class);
                if (data.getObj() != null) {
                    offlinImg.setImageURI(UrlApi.SERVER_IP + data.getObj().getItemPic());
                    offlineMiaoshu.setText(data.getObj().getItemName());
                    offlineOldprice.setText(data.getObj().getRetailPrice() + "");
                    offlinePrice.setText(data.getObj().getOfflineQRPrice() + "");
                    goodPrice=data.getObj().getOfflineQRPrice();
                    totoalMoney = num * data.getObj().getOfflineQRPrice() - DiscountCouponDeduction;

                    DecimalFormat df = new DecimalFormat("#####0.00");
                    String totoalMoneys = df.format(totoalMoney);

                    offlinTotalmonyAll.setText(totoalMoneys + "");
                    storeName = data.getObj().getStoreName();
                    title.setText(data.getObj().getStoreName());
                }

            }
        });
    }


    @OnClick({R.id.offline_getcopoun, R.id.offlin_jian, R.id.add, R.id.offlin_suerorder_btn, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            ///优惠券
            case R.id.offline_getcopoun:
                if (offlineGetcopoun.getText().toString().equals("选取优惠券")) {
                    if (WDWApp.getUserToken() != null) {
                        Intent intent = new Intent(OffilineOrder.this, OfflineCopuonActivity.class);
                        intent.putExtra("itemId", itemId);
                        intent.putExtra("Amount", num);
                        startActivityForResult(intent, 1);
                    } else {
                        Toast.makeText(this, "请登录", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OffilineOrder.this, LognActivity.class);
                        startActivity(intent);
                    }
                } else {
                    if (offlineGetcopoun.getText().toString().equals("取消使用优惠券")) {
                        totoalMoney = num*goodPrice;
                        DiscountCouponDeduction = 0;
                        coupId = 0;
                        couponCode = "";
                        DecimalFormat df = new DecimalFormat("#####0.00");
                        String totoalMoneys = df.format(totoalMoney);
                        offlinTotalmonyAll.setText(totoalMoneys + "");

                        offlineCopuon.setText(DiscountCouponDeduction + "元");
                        offlineGetcopoun.setText("选取优惠券");
                    }
                }


                break;
            case R.id.back:
                finish();
                break;
            case R.id.offlin_jian:
                if (num > 1) {
                    num -= 1;
                    offlinNum.setText(num + "");
                    totoalMoneyRely = num * data.getObj().getOfflineQRPrice();
                    DecimalFormat df = new DecimalFormat("#####0.00");
                    String totoalMoneys = df.format(totoalMoney);

                    offlinTotalmonyAll.setText(totoalMoneys + "");
                    if(totoalMoneyRely<coupLimit){
                        DiscountCouponDeduction=0;
                        totoalMoney = num * data.getObj().getOfflineQRPrice();
                        offlineGetcopoun.setText("选取优惠券");
                        offlinTotalmonyAll.setText(totoalMoney + "");
                        offlineCopuon.setText(DiscountCouponDeduction + "元");
                    }
                    double money=num * data.getObj().getOfflineQRPrice() - DiscountCouponDeduction;
                    String moneys = df.format(money);
                    offlinTotalmonyAll.setText(moneys);
                }
                break;
            case R.id.add:
                num += 1;
                offlinNum.setText(num + "");
                offlinNum.setText(num + "");
                totoalMoneyRely = num * data.getObj().getOfflineQRPrice();
//                if(totoalMoneyRely<coupLimit){
//                    DiscountCouponDeduction=0;
//                    totoalMoney = num * data.getObj().getOfflineQRPrice();
//                    offlineGetcopoun.setText("选取优惠券");
//                    offlinTotalmonyAll.setText(totoalMoney + "");
//                    offlineCopuon.setText(DiscountCouponDeduction + "元");
//                }
                double money=num * data.getObj().getOfflineQRPrice() - DiscountCouponDeduction;
                DecimalFormat df = new DecimalFormat("#####0.00");
                String moneys = df.format(money);
                offlinTotalmonyAll.setText(moneys);
                break;
            case R.id.offlin_suerorder_btn:
                if (WDWApp.getUserToken() == null) {
                    startActivity(new Intent(OffilineOrder.this, LognActivity.class));
                } else {
                    commitData();
                }
                break;
        }
    }


    /**
     * 结算生成订单
     */
    private void commitData() {
        startProgressDialog("下单中...");
        OrderData orderData = new OrderData();
        orderData.setAddressDetail("''");
        orderData.setDiscountCouponDeduction(DiscountCouponDeduction);
        orderData.setIntegralDeduction(0);
        //  orderData.setItemList("{\"ItemID\":" + itemId + ",\"Amount\":" + num + "}");
//        ItemList list = new ItemList();
//        list.setItemID(Integer.parseInt(itemId));
//        list.setAmount(num);
//        orderData.setItemList(list);

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("ItemID", Integer.parseInt(itemId));
        jsonObject1.put("Amount", num);

        List<JSONObject> jsonlist = new ArrayList<>();
        jsonlist.add(jsonObject1);
        orderData.setLogisticsTypeId("3");
        orderData.setOrderMoney(num * data.getObj().getOfflineQRPrice());
        orderData.setPostage(0);
        orderData.setPracticalMoney(num * data.getObj().getOfflineQRPrice());
        orderData.setShoppeName(data.getObj().getStoreName());
        orderData.setShoppeId(storeId);
        orderData.setUserMessage("");

        orderData.setRecName("''");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP线下");
        jsonObject.put("memberId", "0");
        jsonObject.put("uuid", "''");
        jsonObject.put("coupId", coupId);
        jsonObject.put("couponCode", couponCode);
        jsonObject.put("orderTypeId", "1");
        jsonObject.put("orderData", orderData);
        jsonObject.put("itemList", jsonlist);

        String pams = jsonObject.toJSONString();
        LogUtils.d("OffilineOrder", pams);
        String ss = pams.replace("\\", "");
        LogUtils.d("OffilineOrder", ss);
        HtttpRequest.CreatPostRequst(UrlApi.URL_OffLineOrderCrate, pams, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("OffilineOrder", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("OffilineOrder", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {

                String orderNum = JSONObject.parseObject(response).get("obj").toString();
                Intent intent = new Intent(OffilineOrder.this, PayActivity.class);
                intent.putExtra("payOrderType", "1");
                intent.putExtra("orderNum", orderNum);
                intent.putExtra("state", "offline");
                startActivity(intent);
                finish();
                }else {
                    Toast.makeText(OffilineOrder.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class OrderData {
        public double OrderMoney;
        public int IntegralDeduction;
        public double DiscountCouponDeduction;
        public int Postage;
        public double PracticalMoney;
        public String ShoppeId;
        public String ShoppeName;
        public String LogisticsTypeId;
        public String UserMessage;
        public String RecName;
        public String PhoneNum;
        public String AddressDetail;
        //{ItemID:1121,Amount:23}
        public String itemList;

        public void setOrderMoney(double orderMoney) {
            OrderMoney = orderMoney;
        }

        public void setIntegralDeduction(int integralDeduction) {
            IntegralDeduction = integralDeduction;
        }

        public void setDiscountCouponDeduction(double discountCouponDeduction) {
            DiscountCouponDeduction = discountCouponDeduction;
        }

        public void setPostage(int postage) {
            Postage = postage;
        }

        public void setPracticalMoney(double practicalMoney) {
            PracticalMoney = practicalMoney;
        }

        public void setShoppeId(String shoppeId) {
            ShoppeId = shoppeId;
        }

        public void setShoppeName(String shoppeName) {
            ShoppeName = shoppeName;
        }

        public void setLogisticsTypeId(String logisticsTypeId) {
            LogisticsTypeId = logisticsTypeId;
        }

        public void setUserMessage(String userMessage) {
            UserMessage = userMessage;
        }

        public void setRecName(String recName) {
            RecName = recName;
        }

        public void setPhoneNum(String phoneNum) {
            PhoneNum = phoneNum;
        }

        public void setAddressDetail(String addressDetail) {
            AddressDetail = addressDetail;
        }

        public void setItemList(String itemList) {
            this.itemList = itemList;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 1:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                couponCode = b.getString("couponCode");
                coupId = b.getInt("coupId");
                DiscountCouponDeduction = Double.parseDouble(b.getString("coupMony"));//str即为回传的值
                coupLimit = b.getInt("coupLimit");
                offlineCopuon.setText(DiscountCouponDeduction + "元");
                totoalMoney = totoalMoney - DiscountCouponDeduction;
                offlinTotalmonyAll.setText(totoalMoney + "");
                offlineGetcopoun.setText("取消使用优惠券");
                break;
            default:
                break;
        }
    }

    class ItemList {
        private int ItemID;
        private int Amount;

        public int getItemID() {
            return ItemID;
        }

        public void setItemID(int itemID) {
            ItemID = itemID;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int amount) {
            Amount = amount;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadOrderData(itemId, storeId);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(OffilineOrder.this, MainPagerActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
