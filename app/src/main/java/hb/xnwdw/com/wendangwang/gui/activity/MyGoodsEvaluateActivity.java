package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.FragAdapter;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentAreadyEvaluat;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentWaitForEvaluat;

/**
 * Created by Administrator on 2017/4/1.
 */

public class MyGoodsEvaluateActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.goodsevaluate_daipingjia)
    TextView goodsevaluateDaipingjia;
    @BindView(R.id.goodsevaluate_yipingjia)
    TextView goodsevaluateYipingjia;
    private FragmentWaitForEvaluat fragmentWaitForEvaluat;
    private FragmentAreadyEvaluat fragmentAlreadyEvaluate;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_goodsevaluate;
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
        title.setText("商品评价");

        //构造适配器
        List<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new FragmentWaitForEvaluat());
        fragments.add(new FragmentAreadyEvaluat());
        FragAdapter adapter = new FragAdapter(getSupportFragmentManager(), fragments);

        //设定适配器
        ViewPager vp = (ViewPager) findViewById(R.id.evaluate_viewpager);
        vp.setAdapter(adapter);

        vp.setCurrentItem(0); //设置当前页是第一页
    }


    @OnClick({R.id.back, R.id.goodsevaluate_daipingjia, R.id.goodsevaluate_yipingjia})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.goodsevaluate_daipingjia:

                break;
            case R.id.goodsevaluate_yipingjia:

                break;
        }
    }
}
