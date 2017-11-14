package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CertfcationInfoData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/11.
 */
public class AccontSafeActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accontsafe_tv)
    TextView accontsafeTv;
    @BindView(R.id.accontsafe_certification)
    LinearLayout accontsafeCertification;
    @BindView(R.id.accontsafe_changepasw)
    LinearLayout accontsafeChangepasw;
    @BindView(R.id.certifacation_statua)
    TextView certifacationStatua;
   private String fr="";
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_accontsafe;
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
        title.setText("账户安全");
        initCertifacationData();
    }

    @OnClick({R.id.back, R.id.accontsafe_certification, R.id.accontsafe_changepasw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.accontsafe_certification:
                Intent intent=new Intent(AccontSafeActivity.this,CertificationActivity.class);
                LogUtils.d("AccontSafeActivity", fr);
               intent.putExtra("fr",fr);
                startActivity(intent);
                break;
            case R.id.accontsafe_changepasw:
                startActivity(new Intent(this, SetPaswActivity.class));
                break;
        }
    }



    /**
     * 获取用户的实名认证状态
     */
    private void initCertifacationData() {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "Mobile");
        map.put("memberId", "0");

        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetMemberRealAuth, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AccontSafeActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    CertfcationInfoData data = JSON.parseObject(response, CertfcationInfoData.class);
                    if(data.getObj()!=null){
                        if (data.getObj().getAuthStatus() == 1) {
                            certifacationStatua.setText("已认证");
                            //   accontsafeCertification.setClickable(false);

                        }else if(data.getObj().getAuthStatus() == 0){
                            certifacationStatua.setText("未认证");
                            fr="未认证";
                            accontsafeCertification.setClickable(true);
                        }else if(data.getObj().getAuthStatus() == -1){
                            certifacationStatua.setText("审核中");
                            fr="审核中";
                            accontsafeCertification.setClickable(true);
                        }
                    }
                }
            }
        });
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
