package hb.xnwdw.com.wendangwang.UDPay;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.alibaba.fastjson.JSONObject;
import com.unionpay.UPPayAssistEx;
import com.unionpay.uppay.PayActivity;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.HashMap;
import java.util.Map;
import hb.xnwdw.com.wendangwang.gui.activity.OfflineAfterPay;
import hb.xnwdw.com.wendangwang.gui.activity.PayFailedActivity;
import hb.xnwdw.com.wendangwang.gui.activity.PaySuccessActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/6/7.
 */

public class UDPAy extends AppCompatActivity {
    // 2017060700000000000005
    private String mMode = "00";//设置测试模式:01为测试 00为正式环境
    private String orderNumber;
    private String orderType;
    /**
     * 启动支付界面
     */
    public void doStartUnionPayPlugin(Activity activity, String tn, String mode) {
        UPPayAssistEx.startPayByJAR(activity, PayActivity.class, null, null,
                tn, mode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        orderType=intent.getStringExtra("orderType");
        LogUtils.d("UDPAy", orderNumber);
        loadOrderData(orderNumber);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        Log.v("zftphone", "2 " + data.getExtras().getString("merchantOrderId"));
        if (str.equalsIgnoreCase("success")) {
            msg = "支付成功！";

            if (!orderType.equals("1")) {
                Intent intent = new Intent(UDPAy.this, PaySuccessActivity.class);
                intent.putExtra("orderNum", orderNumber);
                startActivity(intent);

            }else if (orderType.equals("1")){
                Intent intent = new Intent(UDPAy.this, OfflineAfterPay.class);
                intent.putExtra("orderNum", orderNumber);
                startActivity(intent);
            }

        } else if (str.equalsIgnoreCase("fail")) {
            Intent intent = new Intent(UDPAy.this, PayFailedActivity.class);
            intent.putExtra("orderNumber", orderNumber);
            startActivity(intent);
            msg = "支付失败！";
            startActivity(new Intent(UDPAy.this,PayFailedActivity.class));
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
            startActivity(new Intent(UDPAy.this, hb.xnwdw.com.wendangwang.gui.activity.PayActivity.class));
        }
        //支付完成,处理自己的业务逻辑!
    }

    private void loadOrderData(String orderNumber) {
        final JSONObject jsonObject = new JSONObject();

        jsonObject.put("orderNumber", orderNumber);
        jsonObject.put("dataSource", "APP");
        String pram = jsonObject.toJSONString();

        Map<String, String> map = new HashMap<>();
        map.put("orderNumber", orderNumber);
        map.put("dataSource", "APP");
        HtttpRequest.CreatPostRequst(UrlApi.URL_UDPAYDATA, pram, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("UDPAy", response);
                if (response.contains(MConstant.HTTP404)) {
                    LogUtils.d("UDPAy", "网络错误");
                    return;
                } else {
                    String key = JSONObject.parseObject(response).get("describe").toString();
                    LogUtils.d("UDPAy", key);
                    doStartUnionPayPlugin(UDPAy.this, key, mMode);
                }
            }
        });
    }
}
