package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import hb.xnwdw.com.wendangwang.utils.TimeCount;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/7.
 */

public class BindPhoneActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.bind_phone_edt)
    EditText bindPhoneEdt;
    @BindView(R.id.bind_phonedelete)
    ImageView bindPhonedelete;
    @BindView(R.id.bind_code_edt)
    EditText bindCodeEdt;
    @BindView(R.id.bind_getcode_tv)
    TextView bindGetcodeTv;
    @BindView(R.id.bind_btn)
    Button bindBtn;
    private Map<String, String> map;
    private String phoneNum;
    private String code;
    private String ID;
    private String Mode;
    private TimeCount time;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_bindphone;
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
        title.setText("绑定手机");
        time = new TimeCount(bindGetcodeTv, 60000, 1000);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        Mode = intent.getStringExtra("Mode");

     //   EventBus.getDefault().register(this);

    }


    @OnClick({R.id.back, R.id.bind_phonedelete, R.id.bind_getcode_tv, R.id.bind_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.bind_phonedelete:
                break;
            case R.id.bind_getcode_tv:
                time.start();
                sendCode();
                break;
            case R.id.bind_btn:
                bindingPhoneAndLogin(Mode, ID);
                break;
        }
    }

    /**
     * 第三方绑定并登录
     */
    private void bindingPhoneAndLogin(final String Mode, final String openid) {
        startProgressDialog("绑定中...");
        final String Phone = bindPhoneEdt.getText().toString();
        String code = bindCodeEdt.getText().toString();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Mode", Mode);
        jsonObject.put("ID", openid);
        jsonObject.put("Phone", Phone);
        jsonObject.put("Code", code);
        String pamas = jsonObject.toJSONString();
        HtttpRequest.CreatPostRequstNoToken(UrlApi.URL_BINGANDLOGIG, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
               stopProgressDialog();
                Toast.makeText(BindPhoneActivity.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("BindPhoneActivity", response);
                if (JSONObject.parseObject(response).get("describe").toString().equals("未注册")) {
                    Intent intent = new Intent(BindPhoneActivity.this, RegisterActivity.class);
                    intent.putExtra("Mode", Mode);
                    intent.putExtra("ID", openid);
                    intent.putExtra("phone", Phone);
                    intent.putExtra("TAG", "third");
                    startActivity(intent);
                } else {
                    if(JSONObject.parseObject(response).get("dataStatus").toString().equals("1")){
                        Toast.makeText(BindPhoneActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                        WDWApp.setUserToken(JSONObject.parseObject(response).get("describe").toString());
                        SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userToken", JSONObject.parseObject(response).get("describe").toString());
                        editor.commit();
                      //  EventBus.getDefault().post(new MessageEvent("true"));
                        Intent intent=new Intent(BindPhoneActivity.this,MainPagerActivity.class);
                        intent.putExtra("fr",4);
                        startActivity(intent);
                        finish();
                        MobclickAgent.onProfileSignIn(Mode,"wendangwang");
                    }else {
                        Toast.makeText(BindPhoneActivity.this, "绑定失败"+JSONObject.parseObject(response).get("describe").toString(),Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }

    /***
     * 获取手机验证码
     */
    private void sendCode() {
        phoneNum = bindPhoneEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        String paramsString = object.toJSONString();
        Map<String, String> map = new HashMap<>();
        map.put("phone", phoneNum);
        HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_SENDCODETOPHONE, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(BindPhoneActivity.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("RsetPwdGetCOde", "getcode--------------" + response);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}