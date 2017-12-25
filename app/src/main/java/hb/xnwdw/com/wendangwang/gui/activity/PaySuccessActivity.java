package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PaySuccessActivity extends ActivityBase {

    @BindView(R.id.paysuccess_paymoney)
    TextView paysuccessPaymoney;
    @BindView(R.id.paysuccess_ordernum)
    TextView paysuccessOrdernum;
    @BindView(R.id.goto_draw)
    TextView gotoDraw;
    @BindView(R.id.draw_ll)
    LinearLayout drawLl;
    @BindView(R.id.paysuccess_title)
    TextView paysuccessTitle;
    @BindView(R.id.paysuccess_ok)
    TextView paysuccessOk;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    private String payOrderNum;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_paysuccess;
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
        paysuccessTitle.setText("支付完成");
        Intent intent = getIntent();
        int orderType = intent.getIntExtra("orderType", 0);
        if (orderType == 3) {
            paysuccessPaymoney.setText("支付金额：¥0");
            payOrderNum = intent.getStringExtra("orderNum");
            paysuccessOrdernum.setText("请等待收货，订单号：" + payOrderNum);

        }
        payOrderNum = WDWApp.payOrderNum;
        paysuccessPaymoney.setText("支付金额：¥ " + PayActivity.payMoney);
        paysuccessOrdernum.setText("请等待收货，订单号：" + WDWApp.payOrderNum);
        AppcanDraw();
    }

    @OnClick({R.id.goto_draw, R.id.paysuccess_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.paysuccess_ok:
                startActivity(new Intent(PaySuccessActivity.this, MainPagerActivity.class).putExtra("fr", 4));
                break;
            case R.id.goto_draw:
                Intent intent = new Intent(getApplicationContext(), PrizeDrawActivity.class);
                intent.putExtra("orderNumber", WDWApp.payOrderNum);
                startActivity(intent);
                finish();
                break;
        }
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
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("obj").toString().equals("true")) {
                        orderCanDraw();
                    } else {
                        drawLl.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    /**
     * 查看订单是否可以参与抽奖
     */
    private void orderCanDraw() {
        Map<String, String> map = new HashMap<>();
        Log.d("PaySuccessActivity_", payOrderNum);
        map.put("sOrderNum", payOrderNum);
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
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("obj") != null && JSONObject.parseObject(response).get("obj").toString().equals("1")) {
                        drawLl.setVisibility(View.VISIBLE);
                    } else {
                        drawLl.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(PaySuccessActivity.this, MainPagerActivity.class).putExtra("fr", 4));
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}