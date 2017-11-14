package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/3/7.
 */

public class FastLognActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fastlogn_phone_edt)
    EditText fastlognPhoneEdt;
    @BindView(R.id.fast_delete_img)
    ImageView fastDeleteImg;
    @BindView(R.id.fast_phonecode_edt)
    EditText fastPhonecodeEdt;
    @BindView(R.id.fast_sendcode_tv)
    TextView fastSendcodeTv;
    @BindView(R.id.fast_logn_btn)
    Button fastLognBtn;
    @BindView(R.id.fast_regest_tv)
    TextView fastRegestTv;
    @BindView(R.id.pasw_logn_tv)
    TextView paswLognTv;
    @BindView(R.id.fast_qqlogn_ll)
    LinearLayout fastQqlognLl;
    @BindView(R.id.fast_weichatlogn_ll)
    LinearLayout fastWeichatlognLl;
    @BindView(R.id.fast_weibologn_ll)
    LinearLayout fastWeibolognLl;
    private String phoneNum;
    private String phoneCode;


    @Override
    protected int getContentViewResId() {
        return R.layout.activity_fastlogn;
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
        title.setText("短信快捷登录");
        time = new TimeCount(60000, 1000);

    }


    @OnClick({R.id.back, R.id.fast_delete_img, R.id.fast_sendcode_tv, R.id.fast_logn_btn, R.id.fast_regest_tv, R.id.pasw_logn_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.fast_delete_img:

                break;
            case R.id.pasw_logn_tv:
                startActivity(new Intent(getApplicationContext(), LognActivity.class));
                finish();
                break;
            case R.id.fast_sendcode_tv:
                if (Utils.isPhone(fastlognPhoneEdt.getText().toString())) {
                    getPhoneCode();
                    time.start();
                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fast_logn_btn:
                if (Utils.isPhone(fastlognPhoneEdt.getText().toString())) {
                    gotToLogin();
                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.fast_regest_tv:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                finish();
                break;
        }
    }

    //获取短信验证码
    private void getPhoneCode() {
        phoneNum = fastlognPhoneEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("isReg", "1");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_GETPHONECODE, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("aa", "getcode--------------" + response);
            }
        });
    }

    //登录
    private void gotToLogin() {
        startProgressDialog("登录中...");
        phoneNum = fastlognPhoneEdt.getText().toString();
        phoneCode = fastPhonecodeEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("telverCode", phoneCode);
        object.put("dataSource", "APP");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_LOGINBYPHONR, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("FastLognActivity", response);
               if(JSONObject.parseObject(response).get("describe").toString().equals("登录成功")){
                   Toast.makeText(FastLognActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                   WDWApp.setUserToken(JSONObject.parseObject(response).get("obj").toString());
                   SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                   SharedPreferences.Editor editor = sp.edit();
                   editor.putString("userToken", JSONObject.parseObject(response).get("obj").toString());
                   editor.commit();
                   Intent intent=new Intent(FastLognActivity.this,MainPagerActivity.class);
                   intent.putExtra("fr",4);
                   startActivity(intent);
               }else {
                   Toast.makeText(FastLognActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
               }

            }
        });

    }

    private TimeCount time;

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            fastSendcodeTv.setTextColor(getResources().getColor(R.color.gry));
            fastSendcodeTv.setClickable(false);
            fastSendcodeTv.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            fastSendcodeTv.setText("重新获取验证码");
            fastSendcodeTv.setClickable(true);
            fastSendcodeTv.setTextColor(getResources().getColor(R.color.maincolor));
        }
    }
}
