package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jiguang.net.HttpResponse;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MySetsActivity extends ActivityBase implements HtttpRequest.LoginStatu {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.set_accontinfo_ll)
    LinearLayout setAccontinfoLl;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.set_accontself_ll)
    LinearLayout setAccontselfLl;
    @BindView(R.id.set_bindaccont_ll)
    LinearLayout setBindaccontLl;
    @BindView(R.id.set_aboutus_ll)
    LinearLayout setAboutusLl;
    @BindView(R.id.safe_exit)
    TextView safeExit;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mysets;
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
        title.setText("设置");
    }


    @OnClick({R.id.set_accontinfo_ll, R.id.set_accontself_ll, R.id.set_bindaccont_ll, R.id.set_aboutus_ll, R.id.back, R.id.safe_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_accontinfo_ll:
                new HtttpRequest(this).isLogin("set_accontinfo_ll");
                break;
            case R.id.set_accontself_ll:
                new HtttpRequest(this).isLogin("set_accontself_ll");
                break;
            case R.id.set_bindaccont_ll:
                new HtttpRequest(this).isLogin("set_bindaccont_ll");
                break;
            case R.id.set_aboutus_ll:
                new HtttpRequest(this).isLogin("set_aboutus_ll");
                break;
            case R.id.safe_exit:
                WDWApp.setUserToken(null);
                SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.remove("userToken");
                editor.remove("userName");
                editor.remove("userPsw");
                editor.commit();
                Toast.makeText(this, "当前账号已退出", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.back:
                Intent intent = new Intent(MySetsActivity.this, MainPagerActivity.class);
                intent.putExtra("fr", 4);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    public void isLogin(String what) {
        switch (what){
            case "set_accontinfo_ll":
                startActivity(new Intent(this, AccontinfoActivity.class));
                break;
            case  "set_accontself_ll":
                startActivity(new Intent(this, AccontSafeActivity.class));
                break;
            case  "set_bindaccont_ll":
                startActivity(new Intent(this, AccontBindingActivity.class));
                break;
            case "set_aboutus_ll":
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
        }
    }

    @Override
    public void noLofin() {
        Toast.makeText(this, "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LognActivity.class));
    }
}
