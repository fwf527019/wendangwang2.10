package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.RegestConpondData;
import hb.xnwdw.com.wendangwang.netdata.entity.RegisterGetCodeData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/19.
 */

public class RegisterActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.register_phonenum_edt)
    EditText registerPhonenumEdt;
    @BindView(R.id.register_phoncode_edt)
    EditText registerPhoncodeEdt;
    @BindView(R.id.register_sendcode)
    TextView registerSendcode;
    @BindView(R.id.register_pasw_edt)
    EditText registerPaswEdt;
    @BindView(R.id.register_ok_btn)
    Button registerOkBtn;
    @BindView(R.id.register_getcode)
    LinearLayout registerGetcode;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.zhucexieyi_checkbox)
    CheckBox zhucexieyiCheckbox;
    @BindView(R.id.zhucexieyi)
    TextView zhucexieyi;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.therd_phone)
    TextView therdPhone;
    @BindView(R.id.phonenum_ll)
    LinearLayout phonenumLl;


    private String phoneNum;
    private String pasw;
    private String phoneCode;
    private String TAG;
    private String Mode;
    private String ID;
    private String phone;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_register;
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

        time = new TimeCount(60000, 1000);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID");
        Mode = intent.getStringExtra("Mode");
        TAG = intent.getStringExtra("TAG");
        phone = intent.getStringExtra("phone");
        if (("third").equals(TAG)) {
            registerGetcode.setVisibility(View.GONE);
            title.setText("设置密码");
            phonenumLl.setVisibility(View.GONE);
            therdPhone.setVisibility(View.VISIBLE);
            registerPhonenumEdt.setText(phone);
            therdPhone.setText(phone);
        } else {
            title.setText("注册账号");
            phonenumLl.setVisibility(View.VISIBLE);
            therdPhone.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.back, R.id.register_sendcode, R.id.register_ok_btn, R.id.zhucexieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register_sendcode:
                if (Utils.isPhone(registerPhonenumEdt.getText().toString())) {
                    getCode();
                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.register_ok_btn:

                if (registerPhonenumEdt.getText().toString().length()!= 11) {
                    Toast.makeText(this, "请输入11位的手机号码", Toast.LENGTH_SHORT).show();
                } else if (registerPaswEdt.getText().toString().length() < 6 || registerPaswEdt.getText().toString().length() > 20) {
                    Toast.makeText(this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
                } else if (zhucexieyiCheckbox.isChecked()) {
                    if (TAG != null && TAG.equals("third")) {
                        thirdRegister();
                    } else {
                        if (registerPhoncodeEdt.getText().toString() == null) {
                            Toast.makeText(this, "手机验证码不能为空", Toast.LENGTH_SHORT).show();
                        }
                        goToRegister();
                    }
                } else {
                    Toast.makeText(this, "注册前请仔细阅读《稳当网注册协议》", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.zhucexieyi:
                startActivity(new Intent(RegisterActivity.this, ZhuCeXieYiWeb.class));
        }
    }


    //获取短信验证码并判断手机是否注册过
    private void getCode() {
        phoneNum = registerPhonenumEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("isReg", "0");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequstNoToken(UrlApi.URL_GETPHONECODE, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("RegisterActivity", "e:" + e);
                Toast.makeText(RegisterActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("RegisterActivity", "getcode______________" + response);
                RegisterGetCodeData registerGetCodeData = JSON.parseObject(response, RegisterGetCodeData.class);
                if (registerGetCodeData.getDataStatus() == -1) {
                    Toast.makeText(RegisterActivity.this, "该手机号码已经被注册", Toast.LENGTH_SHORT).show();
                } else {
                    time.start();
                }
            }
        });
    }


    //注册
    private void goToRegister() {
        phoneNum = registerPhonenumEdt.getText().toString();
        pasw = registerPaswEdt.getText().toString();
        phoneCode = registerPhoncodeEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("pwd", pasw);
        object.put("telverCode", phoneCode);
        object.put("dataSource", "APP");
        object.put("verCode", "APP");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequstNoToken(UrlApi.URL_USER_REGISTER, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(RegisterActivity.this, "e:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("RegisterActivity", response);
                JSONObject jsonObject = JSON.parseObject(response);
                String s = (String) jsonObject.get("describe");
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                RegestConpondData data = JSON.parseObject(response, RegestConpondData.class);
                if (data.getDataStatus() == 1) {
                    Toast.makeText(RegisterActivity.this, "恭喜注册成功", Toast.LENGTH_SHORT).show();
                    WDWApp.setUserToken(data.getObj().get(data.getObj().size() - 1).getGUID());
                    SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userName", phoneNum);
                    editor.putString("userPsw", pasw);
                    editor.putString("userToken", data.getObj().get(data.getObj().size() - 1).getGUID());
                    editor.commit();
                    if (data.getObj() != null && data.getObj().size() != 1) {
                        Intent intent = new Intent(RegisterActivity.this, RegestConpoud.class);
                        intent.putExtra("regestData", data);
                        startActivity(intent);
                        finish();
                    } else {
                        startActivity(new Intent(RegisterActivity.this, MainPagerActivity.class).putExtra("fr", 4));
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, data.getDescribe(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    //倒计时类
    private TimeCount time;

    @OnClick(R.id.public_tital)
    public void onViewClicked() {
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            registerSendcode.setTextColor(getResources().getColor(R.color.gry));
            registerSendcode.setClickable(false);
            registerSendcode.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            registerSendcode.setText("重新获取验证码");
            registerSendcode.setClickable(true);
            registerSendcode.setTextColor(getResources().getColor(R.color.maincolor));

        }
    }

    /**
     * 第三方注册绑定并登
     */
    private void thirdRegister() {
        phoneNum = therdPhone.getText().toString();
        pasw = registerPaswEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("Phone", phoneNum);
        object.put("Pwd", pasw);
        object.put("Mode", Mode);
        object.put("ID", ID);
        String paramsString = object.toJSONString();
        LogUtils.d("RegisterActivity", paramsString);
        HtttpRequest.CreatPostRequstNoToken(UrlApi.URL_REJESTANDBINDINGANDLOGIG, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("RegisterActivity", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(RegisterActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    WDWApp.setUserToken(JSONObject.parseObject(response).get("describe").toString());
                    SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userToken", JSONObject.parseObject(response).get("describe").toString());
                    editor.commit();
                    Intent intent = new Intent(RegisterActivity.this, MainPagerActivity.class);
                    intent.putExtra("fr", 4);
                    startActivity(intent);
                    MobclickAgent.onProfileSignIn(Mode, "wendangwang");
                    finish();
                }else{
                    Toast.makeText(RegisterActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
