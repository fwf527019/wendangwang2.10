package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.OrderDetailListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;
/**
 * Created by Administrator on 2017/7/21.
 */

public class OfflineAfterPay extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.offline_list_img)
    SimpleDraweeView offlineListImg;
    @BindView(R.id.offline_list_num)
    TextView offlineListNum;
    @BindView(R.id.offline_list_pric)
    TextView offlineListPric;
    @BindView(R.id.offline_list_name)
    TextView offlineListName;
    @BindView(R.id.offline_list_ordernum)
    TextView offlineListOrdernum;
    @BindView(R.id.offline_list_time)
    TextView offlineListTime;
    @BindView(R.id.offline_list_conpound)
    TextView offlineListConpound;
    @BindView(R.id.offline_list_toatolmony)
    TextView offlineListToatolmony;
    @BindView(R.id.offline_list_allnum)
    TextView offlineListAllnum;
    @BindView(R.id.offline_goto_draw)
    TextView offlineGotoDraw;
    @BindView(R.id.draw_ll)
    LinearLayout drawLl;
    @BindView(R.id.offline_seehistory)
    TextView offlineSeehistory;
   private String orderNum;
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_offlineafterpay;
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
        Intent intent = getIntent();
        orderNum = intent.getStringExtra("orderNum");
        offlineListOrdernum.setText(orderNum);
        loadData(orderNum);
        title.setText(OffilineOrder.storeName);
        AppcanDraw();
    }

    @OnClick({R.id.back, R.id.offline_goto_draw, R.id.offline_seehistory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                startActivity(new Intent(OfflineAfterPay.this,MainPagerActivity.class).putExtra("fr",4));
                break;
            case R.id.offline_goto_draw:
                Intent intent = new Intent(getApplicationContext(), PrizeDrawActivity.class);
                intent.putExtra("orderNumber", WDWApp.payOrderNum);
                startActivity(intent);
                finish();
                break;
            case R.id.offline_seehistory:
                startActivity(new Intent(this, Offline_hisActivity.class));
                break;
        }
    }


    private void loadData(final String orderNum) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("orderNum", orderNum);
       HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETODERDETAL, map, new StringCallback() {
      //  HtttpRequest.CheackIsLoginGet(this,"http://192.168.1.123:803/api/OrderServiceInterface/GetOrderDeltailsInfo", map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("OfflineAfterPay", "e:" + e);
            }
            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OrderDetailsActivity", response);
                OrderDetailData orderDetailData = JSON.parseObject(response, OrderDetailData.class);
                offlineListImg.setImageURI(UrlApi.SERVER_IP + orderDetailData.getObj().getOrderDetails().get(0).getItemPic());
                offlineListConpound.setText(orderDetailData.getObj().getOrderSummary().getDiscountCouponDeduction());
                offlineListAllnum.setText(orderDetailData.getObj().getOrderDetails().get(0).getAmount());
                offlineListName.setText(orderDetailData.getObj().getOrderDetails().get(0).getItemName());
                offlineListTime.setText(orderDetailData.getObj().getOrderSummary().getOrderDate());
                offlineListNum.setText("×"+orderDetailData.getObj().getOrderDetails().get(0).getAmount());
                offlineListPric.setText("¥" + orderDetailData.getObj().getOrderDetails().get(0).getUnit());
                offlineListToatolmony.setText(orderDetailData.getObj().getOrderSummary().getPracticalMoney());
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
           startActivity(new Intent(OfflineAfterPay.this,MainPagerActivity.class).putExtra("fr",4));
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


    /**
     * 判断手机是否开启抽奖
     */

    private void AppcanDraw() {

        Map<String, String> map = new HashMap<>();
        map.put("source", "APP");
        HtttpRequest.CheackIsLoginGet(this, UrlApi.URL_GETIsOpen, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("PaySuccessActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("PaySuccessActivity", response);
                if (JSONObject.parseObject(response).get("obj").toString().equals("true")) {
                    orderCanDraw();
                } else {
                    drawLl.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    /**
     * 查看订单是否可以参与抽奖
     */
    private void orderCanDraw() {
        Map<String, String> map = new HashMap<>();
        map.put("sOrderNum", orderNum);
        map.put("sMemberID", "");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETCanDraw, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("PaySuccessActivity_", "e:" + e);
                orderCanDraw();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("PaySuccessActivity_", response);

                if (JSONObject.parseObject(response).get("obj") != null && JSONObject.parseObject(response).get("obj").toString().equals("1")) {
                    drawLl.setVisibility(View.VISIBLE);
                } else {
                    drawLl.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
