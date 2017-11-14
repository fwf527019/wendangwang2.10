package hb.xnwdw.com.wendangwang.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.BindPhoneActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import okhttp3.Call;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册API
        api = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq resp) {

    }

    @Override
    public void onResp(BaseResp resp) {

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                finish();

                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                finish();

                break;
            default:
                finish();
                break;
        }

        if (resp instanceof SendAuth.Resp) {
            SendAuth.Resp newResp = (SendAuth.Resp) resp;
            //获取微信传回的code
            String code = newResp.code;
            LogUtils.d("WXEntryActivity_code", code);

            Map<String, String> map = new HashMap<>();
            map.put("appid", "wxa0b36e8699f972a9");
            map.put("secret", WXConstants.APP_Secret);
            map.put("code", code);
            //  map.put("grant_type","authorization_code");

            String result = null;

            HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_WXLOGIN, map, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    LogUtils.d("WXEntryActivity", "e:" + e);
                }

                @Override
                public void onResponse(String response, int id) {
                    LogUtils.d("WXEntryActivity", response);
                    if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                        WDWApp.setUserToken((JSONObject.parseObject(response).get("Data")).toString());
                        SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userToken", (JSONObject.parseObject(response).get("Data")).toString());
                        editor.commit();
                        Log.d("WXEntryActivity", WDWApp.getUserToken());
                        Toast.makeText(WXEntryActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(WXEntryActivity.this,MainPagerActivity.class).putExtra("fr",4));
                        MobclickAgent.onProfileSignIn("WX","wendangwang");
                        EventBus.getDefault().post(new Good_Login_event("1"));
                        finish();
                    }
                    if (JSONObject.parseObject(response).get("ErrMSG").toString().equals("未绑定")) {
                        Intent intent = new Intent(WXEntryActivity.this, BindPhoneActivity.class);
                        if (JSONObject.parseObject(response).get("Data") != null) {
                            intent.putExtra("ID", JSONObject.parseObject(response).get("Data").toString());
                            intent.putExtra("Mode", "WeChatID");
                            startActivity(intent);
                            finish();
                        }
                    }

                }
            });

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        //     setVisible(true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}