package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.TimeCount;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/3/8.
 */
public class ForgetPaswActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fogetpaw_phonenum_edt)
    EditText fogetpawPhonenumEdt;
    @BindView(R.id.forgetpaw_phoncode_edt)
    EditText forgetpawPhoncodeEdt;
    @BindView(R.id.forget_sendcode)
    TextView forgetSendcode;
    @BindView(R.id.fogetpaw_newpasw_edt)
    EditText fogetpawNewpaswEdt;
    @BindView(R.id.forget_ok_btn)
    Button forgetOkBtn;
    @BindView(R.id.forget_rememb_password_tv)
    TextView forgetRemembPasswordTv;

    private TimeCount time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title.setText("重置密码");
        time = new TimeCount(forgetSendcode, 60000, 1000);
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_forgetpasw;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @OnClick({R.id.back, R.id.forget_sendcode, R.id.forget_ok_btn, R.id.forget_rememb_password_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.forget_sendcode:
                if(Utils.isPhone(fogetpawPhonenumEdt.getText().toString())){
                    sendCode();
                    time.start();
                }

                break;
            case R.id.forget_ok_btn:
                ResetPwd();
                break;
            case R.id.forget_rememb_password_tv:
                startActivity(new Intent(this,LognActivity.class));
                finish();
                break;
        }
    }

    private String phoneCode;
    private String newPwd;

    /***
     * 进行密码重置
     */
    private void ResetPwd() {
        startProgressDialog("提交中...");
        phoneNum = fogetpawPhonenumEdt.getText().toString();
        phoneCode = forgetpawPhoncodeEdt.getText().toString();
        newPwd = fogetpawNewpaswEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("pwd", newPwd);
        object.put("telVerCode", phoneCode);
        object.put("dataSource", "APP");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_RESETPWD, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                LogUtils.d("ForgetPaswActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("ResetPwd", "getcode--------------" + response);
                if(JSONObject.parseObject(response).get("Success").toString().equals("true")){
                    Toast.makeText(ForgetPaswActivity.this, "设置密码成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    private String phoneNum;

    /***
     * 获取手机验证码
     */
    private void sendCode() {
        phoneNum = fogetpawPhonenumEdt.getText().toString();
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
                LogUtils.d("RsetPwdGetCOde", "getcode--------------" + response);
            }
        });

    }


}
