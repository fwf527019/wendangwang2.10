package hb.xnwdw.com.wendangwang.gui.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.callback.StringCallback;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.AliPay.PayResult;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.UDPay.UDPAy;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderDetailData;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.MD5Utils;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderBean;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderMoney;
import hb.xnwdw.com.wendangwang.netdata.entity.WXPayData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PayActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.weichat_pay)
    LinearLayout weichatPay;
    @BindView(R.id.ali_pay)
    LinearLayout aliPay;
    @BindView(R.id.bank_pay)
    LinearLayout bankPay;
    @BindView(R.id.yi_pay)
    LinearLayout yiPay;
    @BindView(R.id.pay_ordernum)
    TextView payOrdernum;
    @BindView(R.id.pay_mony)
    TextView payMony;
    private int SDK_PAY_FLAG = 1;
    private OrderBean orderBean;
    public String key = null;
    // public static String payOrderNum;
    public static String payMoney;
    public static String payOrderType = "0";
    private boolean canPay;
    private String Tag;
    private String sourse;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("PayActivity", "onStart");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        //判断是否能支付

        title.setText("订单支付");
        Intent intent = getIntent();
        WDWApp.payOrderNum = intent.getStringExtra("orderNum");
        if (intent.getStringExtra("payOrderType") != null) {
            payOrderType = intent.getStringExtra("payOrderType");
        }

        String state = intent.getStringExtra("state");
        if (("offline").equals(state)) {
            canPay = true;
        } else {
            canPay(WDWApp.payOrderNum);
        }
        payMoney = intent.getStringExtra("payMoney");
        loadOrderMoney(WDWApp.payOrderNum);
        payOrdernum.setText(WDWApp.payOrderNum);
        if (intent.getStringExtra("sourse") != null) {
            sourse = intent.getStringExtra("sourse");
        }

    }

    private void canPay(String payOrderNum) {
        JSONObject job = new JSONObject();
        job.put("dataSource", "APP");
        job.put("memberId", "");
        job.put("orderNum", payOrderNum);
        String pamString = job.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_OrderPayDetection, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PayActivity", "e:" + e);
                Toast.makeText(PayActivity.this, "网络超时", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("PayActivity_c", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    canPay = true;
                } else {
                    canPay = false;
                    Toast.makeText(PayActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    /**
     * 获取微信支付的数据
     */
    private void GetOrderDataWx(String orderNumber) {

        startProgressDialog("启动微信中...");
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        HtttpRequest.CreatGetRequst(UrlApi.SERVER_IP + "/Api/OrderServiceInterface/getOrderPayByAPPWeChat", map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                LogUtils.d("PayActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("PayActivity_wx", response);

                LogUtils.d("PayActivity_wx", JSONObject.parseObject(response).get("obj").toString());
                if (JSONObject.parseObject(response).get("obj") != null) {
                    WXPayData data = JSON.parseObject(JSONObject.parseObject(response).get("obj").toString(), WXPayData.class);
                    weiChatPay(data);
                } else {
                    Toast.makeText(PayActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @OnClick({R.id.back, R.id.weichat_pay, R.id.ali_pay, R.id.bank_pay, R.id.yi_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
                builder.setMessage("确认放弃支付？");
                builder.setTitle("提示");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (sourse != null) {
                            if (sourse.equals("OrderBalance_car")) {
                                startActivity(new Intent(PayActivity.this, MainPagerActivity.class).putExtra("fr", 3));
                            } else if (sourse.equals("OrderBalance_good")) {
                                WDWApp.destoryActivity("Orderbalance");
                                finish();
                            } else {
                                finish();
                            }
                            dialog.dismiss();
                        }else {
                            finish();
                        }
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
            /****微信支付***/
            case R.id.weichat_pay:
                GetOrderDataWx(WDWApp.payOrderNum);
                break;
            /***支付宝支付****/
            case R.id.ali_pay:
                // 必须异步调用
                GetOrderInfo(WDWApp.payOrderNum);

                break;
            /****银联支付*****/
            case R.id.bank_pay:
                Intent intent = new Intent(this, UDPAy.class);
                intent.putExtra("orderNumber", WDWApp.payOrderNum);
                intent.putExtra("orderType", payOrderType);
                startActivity(intent);

                break;
            case R.id.yi_pay:
                break;
        }
    }

    //调用支付宝
    Runnable payRunnable = new Runnable() {
        @Override
        public void run() {
            PayTask payTask = new PayTask(PayActivity.this);
            //    String result = payTask.pay(orderBean.payUrl, true);
            Map<String, String> result = payTask.payV2(key, true);
            Log.i("msp", result.toString());
            Message msg = new Message();
            msg.what = 1;
            msg.obj = result;
            mHandler.sendMessage(msg);
        }
    };
    /**
     * 支付宝支付回调
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (!payOrderType.equals("1")) {
                            startActivity(new Intent(PayActivity.this, PaySuccessActivity.class));
                        } else if (payOrderType.equals("1")) {
                         //   startActivity(new Intent(PayActivity.this, OfflineAfterPay.class));
                           startActivity(new Intent(PayActivity.this, OfflineAfterPay.class).putExtra("memberId",WDWApp.payOrderNum));
                        }
                    } else {
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服<span style="white-space:pre">                </span>务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            Toast.makeText(PayActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "6002")) {
                            Toast.makeText(PayActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.equals(resultStatus, "5000")) {
                            Toast.makeText(PayActivity.this, "重复请求", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败
                            Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), PayFailedActivity.class));

                        }
                    }
                    break;
                }
            }
        }
    };

    /**
     * 支付宝支付数据
     *
     * @param orderNum
     * @return
     */
    private void GetOrderInfo(String orderNum) {
        startProgressDialog("正在启动支付宝...");
        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", orderNum);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETALIPAYDATA, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("PayActivity", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("PayActivity_response", response);
                String keys = response.replaceAll("&amp;", "&");
                String s = "\\{";
                String ss[] = keys.split(s);
                key = ss[0];
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }

    /**
     * get the sdk version. 获取支付宝SDK版本号
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * 掉起微信支付sdk
     */
    private void weiChatPay(WXPayData wxPayData) {
        IWXAPI iwxapi = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID);
        //将app注册到微信
        iwxapi.registerApp(WXConstants.APP_ID);

        if (iwxapi != null && iwxapi.isWXAppInstalled()) {
            PayReq req = new PayReq();
            req.appId = wxPayData.getAppid();
            req.partnerId = wxPayData.getMch_id();
            req.prepayId = wxPayData.getPrepay_id();
            req.nonceStr = wxPayData.getNonce_str();
            req.timeStamp = wxPayData.getTimestamp();
            req.packageValue = "Sign=WXPay";
            req.sign = wxPayData.getSign2();
            //  req.sign =signm;
            iwxapi.sendReq(req);
        } else {
            Toast.makeText(this, "请安装微信", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 获取交易金额
     */
    double payMoneys;
    private void loadOrderMoney(String orderNum) {
        startProgressDialog("加载中...");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP");
        jsonObject.put("memberId", "APP");
        jsonObject.put("orderNum", orderNum);
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this,UrlApi.URL_ORDERMONY, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PayActivity", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("PayActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    OrderMoney orderMoney = JSON.parseObject(response, OrderMoney.class);
                    payMoney = orderMoney.getObj().getPracticalMoney();
                    payMoneys=Double.parseDouble(payMoney);
                    payMony.setText(orderMoney.getObj().getPracticalMoney());
                    //   loadData(payOrderNum);

                    if ((payMoneys-0.0==0) && payOrderType.equals("0")) {
                        startActivity(new Intent(PayActivity.this, PaySuccessActivity.class));
                    }
                    if ((payMoneys-0.0==0)&& payOrderType.equals("1")) {
                        startActivity(new Intent(PayActivity.this, OfflineAfterPay.class));
                    }


                }

//                LogUtils.d("PayActivity_wx", UrlUtils.getTime().substring(0, 10));
            }
        });
    }


    /**
     * 订单详情
     *
     * @param orderNum
     */

    private void loadData(String orderNum) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("orderNum", orderNum);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETODERDETAL, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OrderDetailsActivity", response);
                OrderDetailData orderDetailData = JSON.parseObject(response, OrderDetailData.class);
                payOrderType = orderDetailData.getObj().getOrderSummary().getOrderTypeId();
                if (payMoney.equals("0") && !payOrderType.equals("1")) {
                    startActivity(new Intent(PayActivity.this, PaySuccessActivity.class));
                } else if (payMoney.equals("0") && payOrderType.equals("1")) {
                    startActivity(new Intent(PayActivity.this, OfflineAfterPay.class));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PayActivity.this);
            builder.setMessage("确认放弃支付？");
            builder.setTitle("提示");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (sourse != null) {
                        if (sourse.equals("OrderBalance_car")) {
                            startActivity(new Intent(PayActivity.this, MainPagerActivity.class).putExtra("fr", 3));
                        } else if (sourse.equals("OrderBalance_good")) {
                            WDWApp.destoryActivity("Orderbalance");
                            finish();
                        } else {
                            finish();
                        }

                    }else {
                        finish();
                    }
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }
}
