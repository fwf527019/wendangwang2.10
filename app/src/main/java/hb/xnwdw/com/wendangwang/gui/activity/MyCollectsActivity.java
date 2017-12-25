package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.MyNewCollectesAdapter;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentBrandsCollecte;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentGoodsCollecte;

/**
 * Created by Administrator o
 *
 */

public class MyCollectsActivity extends ActivityBase {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.editor)
    TextView editor;
    @BindView(R.id.brand_back)
    ImageView brandBack;
    @BindView(R.id.my_collects_goods)
    TextView myCollectsGoods;
    @BindView(R.id.my_collects_brands)
    TextView myCollectsBrands;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.viewpage)
    ViewPager viewpage;
    @BindView(R.id.grand)
    LinearLayout grand;
    private FragmentBrandsCollecte fragmentBrandsCollecte;
    private FragmentGoodsCollecte fragmentGoodsCollecte;
    private MyNewCollectesAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mycollect;

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
        initFragment();
        title.setText("我的收藏");
    }

    @OnClick({R.id.back, R.id.editor, R.id.my_collects_goods, R.id.my_collects_brands})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.editor:
                if (editor.getText().equals("编辑")) {
                    editor.setText("完成");
                    EventBus.getDefault().post("finish");
                } else if (editor.getText().equals("完成")) {
                    editor.setText("编辑");
                    EventBus.getDefault().post("editor");
                }

                break;
            case R.id.my_collects_goods:
                viewpage.setCurrentItem(0);
                initColor(0);
                break;
            case R.id.my_collects_brands:
                viewpage.setCurrentItem(1);
                initColor(1);
                break;
        }
    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentGoodsCollecte());
        list.add(new FragmentBrandsCollecte());
        String[] titles = {"商品", "品牌"};
        adapter = new MyNewCollectesAdapter(getSupportFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(list);
        viewpage.setAdapter(adapter);
        initColor(0);

        viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                initColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void initColor(int i) {
        if (i == 0) {
            myCollectsGoods.setTextColor(getResources().getColor(R.color.white));
            myCollectsBrands.setTextColor(getResources().getColor(R.color.black));
            myCollectsGoods.setBackgroundResource(R.drawable.brand_left);
            myCollectsBrands.setBackgroundResource(R.drawable.brand_right_nor);
        } else {
            myCollectsBrands.setTextColor(getResources().getColor(R.color.white));
            myCollectsGoods.setTextColor(getResources().getColor(R.color.black));
            myCollectsGoods.setBackgroundResource(R.drawable.brand_left_nor);
            myCollectsBrands.setBackgroundResource(R.drawable.brand_right);
        }

    }




}
