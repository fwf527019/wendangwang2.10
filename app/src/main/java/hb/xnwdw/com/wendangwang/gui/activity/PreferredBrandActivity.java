package hb.xnwdw.com.wendangwang.gui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentAllBrand;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentRecomendeBrand;


/**
 * Created by Administrator on 2017/3/13.
 */

public class PreferredBrandActivity extends ActivityBase {

    private TextView preferredbrand;

    private TextView allbrand;
    private FragmentManager fragmentManager;
    ImageView img_bk;
    FragmentAllBrand fragmentAllBrand;
    FragmentRecomendeBrand fragmentPreferredBrand;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferredbrand = (TextView) findViewById(R.id.preferredbrand);
        allbrand = (TextView) findViewById(R.id.allbrand);
        img_bk = (ImageView) findViewById(R.id.brand_back);


        preferredbrand.setTextColor(getResources().getColor(R.color.white));
        allbrand.setTextColor(getResources().getColor(R.color.black));
        preferredbrand.setBackgroundResource(R.drawable.brand_left);
        allbrand.setBackgroundResource(R.drawable.brand_right_nor);

        preferredbrand.setOnClickListener(itemClick);
        allbrand.setOnClickListener(itemClick);
        img_bk.setOnClickListener(itemClick);


        fragmentManager = getSupportFragmentManager();
        initfr1();
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.preferredbrand:
                    initfr1();
                    preferredbrand.setTextColor(getResources().getColor(R.color.white));
                    allbrand.setTextColor(getResources().getColor(R.color.black));
                    preferredbrand.setBackgroundResource(R.drawable.brand_left);
                    allbrand.setBackgroundResource(R.drawable.brand_right_nor);

                    break;
                case R.id.allbrand:
                    initfr2();
                    allbrand.setTextColor(getResources().getColor(R.color.white));
                    preferredbrand.setTextColor(getResources().getColor(R.color.black));
                    preferredbrand.setBackgroundResource(R.drawable.brand_left_nor);
                    allbrand.setBackgroundResource(R.drawable.brand_right);
                    break;
                case R.id.brand_back:
                    finish();
                    break;
            }

        }
    };



    @Override
    protected int getContentViewResId() {
        return R.layout.activity_preferredbrand;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (fragmentPreferredBrand != null) {
            transaction.hide(fragmentPreferredBrand);
        }
        if (fragmentAllBrand != null) {
            transaction.hide(fragmentAllBrand);
        }
    }

    private void initfr1() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentPreferredBrand == null) {
            fragmentPreferredBrand = new FragmentRecomendeBrand();
            transaction.add(R.id.lay, fragmentPreferredBrand);
        }
        hideFragment(transaction);
        transaction.show(fragmentPreferredBrand);
        transaction.commit();
    }

    private void initfr2() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragmentAllBrand == null) {
            fragmentAllBrand = new FragmentAllBrand();
            transaction.add(R.id.lay, fragmentAllBrand);
        }
        hideFragment(transaction);
        transaction.show(fragmentAllBrand);
        transaction.commit();
    }

}
