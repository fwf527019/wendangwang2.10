package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.RandomCodeUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/12.
 */
public class ExchangePhoneActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.exchangephone_phonedelete)
    ImageView exchangephonePhonedelete;
    @BindView(R.id.exchangephone_piccode_edt)
    EditText exchangephonePiccodeEdt;
    @BindView(R.id.exchangephone_piccode)
    ImageView exchangephonePiccode;
    @BindView(R.id.exchangephone_code_edt)
    EditText exchangephoneCodeEdt;
    @BindView(R.id.exchangephone_getcode_tv)
    TextView exchangephoneGetcodeTv;
    @BindView(R.id.exchangephone_btn)
    Button exchangephoneBtn;
    @BindView(R.id.exchangephone_paw_edt)
    EditText exchangephonePawEdt;
    @BindView(R.id.exchangephone_newphone_edt)
    EditText exchangephoneNewphoneEdt;
    @BindView(R.id.exchangephone_newphonedelete)
    ImageView exchangephoneNewphonedelete;
    @BindView(R.id.exchangephone_oldphone)
    TextView exchangephoneOldphone;
    private String picCode;
    private Bitmap codeBitmap;
    private RandomCodeUtils randomCodeUtils;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_exchangephone;
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
        title.setText("更换手机号码");
        Intent intent = getIntent();
        String oldPhone = intent.getStringExtra("phone");
        exchangephoneOldphone.setText("手机号码:" + oldPhone);
        randomCodeUtils = RandomCodeUtils.getInstance();
        picCode = randomCodeUtils.createCode();
        Log.d("ExchangePhoneActivity", picCode);
        codeBitmap = randomCodeUtils.createBitmap(picCode);
        exchangephonePiccode.setImageBitmap(codeBitmap);
        time = new TimeCount(60000, 1000);
    }


    @OnClick({R.id.back, R.id.exchangephone_getcode_tv, R.id.exchangephone_btn, R.id.exchangephone_piccode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.exchangephone_piccode:
                if (codeBitmap != null) {
                    codeBitmap.recycle();
                    codeBitmap = null;
                }
                picCode = randomCodeUtils.createCode();
                Log.d("ExchangePhoneActivity", picCode);
                codeBitmap = randomCodeUtils.createBitmap(picCode);
                exchangephonePiccode.setImageBitmap(codeBitmap);
                break;
            case R.id.exchangephone_btn:
                if (exchangephonePawEdt.getText().toString().length() == 0) {
                    Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
                } else if (exchangephoneNewphoneEdt.getText().length() == 0) {
                    Toast.makeText(this, "请输入新的绑定账号", Toast.LENGTH_SHORT).show();
                } else if (exchangephoneCodeEdt.getText().length() == 0) {
                    Toast.makeText(this, "请输入短信验证码", Toast.LENGTH_SHORT).show();
                } else {
                    commitData();
                }
                break;
            case R.id.exchangephone_getcode_tv:
                if (Utils.isPhone(exchangephoneNewphoneEdt.getText().toString())) {
                    Log.d("ExchangePhoneActivity_1", picCode.toUpperCase());
                   String str1= picCode.toUpperCase();

                   String str2= exchangephonePiccodeEdt.getText().toString().toUpperCase();

                    Log.d("ExchangePhoneActivity_2", exchangephonePiccodeEdt.getText().toString().toUpperCase());
                    if (str1.equals(str2)) {
                        getPhoneCode(exchangephoneNewphoneEdt.getText().toString());
                    } else {
                        Toast.makeText(this, "图片验证码不正确", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


    /**
     * 获取短信验证码
     */
    private void getPhoneCode(String newPhone) {
        startProgressDialog("发送中...");
        Map<String, String> map = new HashMap<>();
        map.put("phone", newPhone);
        HtttpRequest.CreatGetRequst(UrlApi.URL_SendRebindCode, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                Toast.makeText(ExchangePhoneActivity.this, "网络错误:" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                Log.d("ExchangePhoneActivity", response);
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    time.start();
                }else {
                    Toast.makeText(ExchangePhoneActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    /**
     * 提交数据
     */
    private void commitData() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Password", exchangephonePawEdt.getText().toString());
        jsonObject.put("NewPhone", exchangephoneNewphoneEdt.getText().toString());
        jsonObject.put("PhoneCode", exchangephoneCodeEdt.getText().toString());
        String pams = jsonObject.toJSONString();


        HtttpRequest.CreatPostRequst(UrlApi.URL_RebindPhone, pams, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(ExchangePhoneActivity.this, "网络错误" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                    Toast.makeText(ExchangePhoneActivity.this, "修改绑定手机成功", Toast.LENGTH_SHORT).show();
                   finish();
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
            exchangephoneGetcodeTv.setTextColor(getResources().getColor(R.color.gry));
            exchangephoneGetcodeTv.setClickable(false);
            exchangephoneGetcodeTv.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            exchangephoneGetcodeTv.setText("重新获取验证码");
            exchangephoneGetcodeTv.setClickable(true);
            exchangephoneGetcodeTv.setTextColor(getResources().getColor(R.color.maincolor));

        }
    }


}
