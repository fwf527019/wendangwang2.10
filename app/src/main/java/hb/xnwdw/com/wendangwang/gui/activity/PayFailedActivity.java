package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;

/**
 * Created by Administrator on 2017/3/27.
 */

public class PayFailedActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.payagin)
    TextView payagin;
    @BindView(R.id.cheack_order)
    TextView cheackOrder;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_payfailed;
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
        title.setText("支付失败");
    }

    @OnClick({R.id.back, R.id.payagin, R.id.cheack_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.payagin:
               finish();
                break;
            case R.id.cheack_order:
                List<Integer> list=new ArrayList<>();
                list.add(2);
                list.add(3);
                Intent intent1=new Intent(this,OrderDetailsActivity.class);
                intent1.putExtra("orderNum", WDWApp.payOrderNum);
                intent1.putExtra("payMoney",WDWApp.payOrderNum);
                intent1.putExtra("btnNum", (Serializable) list);
                startActivity(intent1);
                break;
        }
    }
}
