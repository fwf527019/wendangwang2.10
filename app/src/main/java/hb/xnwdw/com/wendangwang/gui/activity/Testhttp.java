package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.widget.Button;


import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;

import butterknife.BindView;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;


public class Testhttp extends ActivityBase {


    @BindView(R.id.button)
    Button button;

    @Override
    protected int getContentViewResId() {
        return R.layout.sec_titelsaf;
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

    }

    public Testhttp() {
    }

    @OnClick(R.id.button)
    public void onViewClicked() {

    }
}

