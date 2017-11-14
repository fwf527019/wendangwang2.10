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
 * Created by Administrator on 2017/4/14.
 */

public class SureLogistics extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.surelogistics_tv1)
    TextView surelogisticsTv1;
    @BindView(R.id.surelogistics_img1)
    ImageView surelogisticsImg1;
    @BindView(R.id.surelogistics_name)
    TextView surelogisticsName;
    @BindView(R.id.surelogistics_spec)
    TextView surelogisticsSpec;
    @BindView(R.id.surelogistics_num)
    TextView surelogisticsNum;
    @BindView(R.id.surelogistics_price)
    TextView surelogisticsPrice;
    @BindView(R.id.suerorder_btn)
    TextView suerorderBtn;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_surelogistics;
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
        title.setText("确认物流");
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
