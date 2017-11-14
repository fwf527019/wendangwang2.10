package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/4/13.
 */

public class SuerOrder extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.suerorder_tv1)
    TextView suerorderTv1;
    @BindView(R.id.suerorder_img1)
    ImageView suerorderImg1;
    @BindView(R.id.suerorder_name)
    TextView suerorderName;
    @BindView(R.id.suerorder_spec)
    TextView suerorderSpec;
    @BindView(R.id.suerorder_num)
    TextView suerorderNum;
    @BindView(R.id.suerorder_price)
    TextView suerorderPrice;
    @BindView(R.id.suerorder_btn)
    TextView suerorderBtn;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_suerorder;
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
        title.setText("确认订单");
    }


    @OnClick({R.id.back, R.id.suerorder_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.suerorder_btn:
                break;
        }
    }
}
