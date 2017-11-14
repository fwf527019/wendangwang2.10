package hb.xnwdw.com.wendangwang.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.OfflineAfterPay;
import hb.xnwdw.com.wendangwang.gui.activity.PayActivity;
import hb.xnwdw.com.wendangwang.gui.activity.PayFailedActivity;
import hb.xnwdw.com.wendangwang.gui.activity.PaySuccessActivity;
import hb.xnwdw.com.wendangwang.utils.LogUtils;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
    private String orderNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        api = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {

    }
    private int payState;
    @Override
    public void onResp(BaseResp resp) {
        LogUtils.d(TAG, "resp.errCode:" + resp.errCode);
        LogUtils.d(TAG, "resp.openId:" + resp.openId);
        LogUtils.d(TAG, "resp.transaction:" + resp.transaction);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle(R.string.app_tip);
//            builder.setMessage(getString(R.string.pay_result_callback_msg, String.valueOf(resp.errCode)));
//            builder.show();
            if (resp.errCode == 0 && !PayActivity.payOrderType.equals("1")) {
                payState=0;
                Intent intent = new Intent(this, PaySuccessActivity.class);
                intent.putExtra("orderNum", WDWApp.payOrderNum);
                intent.putExtra("payMoney", PayActivity.payMoney);
                startActivity(intent);
                finish();
            } else if (resp.errCode == 0 && PayActivity.payOrderType.equals("1")) {
                Intent intent = new Intent(this, OfflineAfterPay.class);
                payState=1;
                intent.putExtra("orderNum", WDWApp.payOrderNum);
                startActivity(intent);
                finish();
            } else if (resp.errCode != 0) {
                payState=2;
                Intent intent = new Intent(this, PayFailedActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
           switch (payState){
               case 0:
                   Intent intent = new Intent(this, PaySuccessActivity.class);
                   intent.putExtra("orderNum", WDWApp.payOrderNum);
                   intent.putExtra("payMoney", PayActivity.payMoney);
                   startActivity(intent);
                   finish();
                   break;
               case 1:
                   Intent intent1 = new Intent(this, OfflineAfterPay.class);
                   payState=1;
                   intent1.putExtra("orderNum", WDWApp.payOrderNum);
                   startActivity(intent1);
                   finish();
                   break;
               case 2:
                   Intent inten2 = new Intent(this, PayFailedActivity.class);
                   startActivity(inten2);
                   finish();
                   break;
           }


        }

        return super.onKeyDown(keyCode, event);
    }
}