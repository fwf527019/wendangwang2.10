package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/7.
 */

public class SetPaswActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.set_pasw_ok)
    Button login;
    @BindView(R.id.set_pasw_pas_old)
    EditText setPaswPasOld;
    @BindView(R.id.set_pasw_pas_new1)
    EditText setPaswPasNew1;
    @BindView(R.id.set_pasw_pas_new2)
    EditText setPaswPasNew2;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_setpasw;
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
        title.setText("修改登录密码");
    }


    @OnClick({R.id.back, R.id.set_pasw_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.set_pasw_ok:
                commitData();
                break;
        }
    }

    private void commitData() {
        JSONObject jsonObject = new JSONObject();
        JSONObject pwdInfo = new JSONObject();
        pwdInfo.put("OldPwd",setPaswPasOld.getText().toString() );
        pwdInfo.put("NewPwd",setPaswPasNew1.getText().toString() );
        pwdInfo.put("RePwd",setPaswPasNew2.getText().toString() );
        jsonObject.put("dataSource", "APP");
        jsonObject.put("pwdInfo", pwdInfo);
        Log.d("SetPaswActivity", jsonObject.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.SERVER_IP + "/api/Basic_Member/ChangePwd", jsonObject.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("SetPaswActivity", response);
                if(JSONObject.parseObject(response).get("Success").toString().equals("true")){
                    Toast.makeText(SetPaswActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                }   else {
                    Toast.makeText(SetPaswActivity.this, JSONObject.parseObject(response).get("ErrMSG").toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


}
