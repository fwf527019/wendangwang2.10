package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.umeng.analytics.MobclickAgent;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.view.MyPopWindow;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.AppVerison;
import hb.xnwdw.com.wendangwang.utils.OnDoubleClickListener;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/11.
 */
public class AboutUsActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.set_severip)
    EditText setSeverip;
    @BindView(R.id.severip_ok)
    TextView severipOk;
    @BindView(R.id.set_severip_ll)
    LinearLayout setSeveripLl;
    @BindView(R.id.logo_ico)
    ImageView logoIco;
    @BindView(R.id.car_goods_list)
    SwipeMenuListView carGoodsList;


    private String severIp;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_aboutus;
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
        title.setText("关于我们");
        String versionName = AppVerison.getAppVersionName(this);
        version.setText(versionName);


              logoIco.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.DoubleClickCallback() {
            @Override
            public void onDoubleClick() {

            /***********************c测试代码***********************/
                Intent intent = new Intent(AboutUsActivity.this, PrizeDrawActivity.class);

                startActivity(intent);

            }
        }));



    }

    @OnClick({R.id.back, R.id.severip_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.severip_ok:
                UrlApi.setServerIp(setSeverip.getText().toString().trim());
                setSeveripLl.setVisibility(View.GONE);
                break;
        }
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
