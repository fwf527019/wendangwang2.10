package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.utils.LogUtils;

/**
 * Created by Administrator on 2017/3/21.
 */

public class FragmentGoodsPicDetails extends FragmentBase {
    @BindView(R.id.goodsdetai_frgment_fr)
    FrameLayout goodsdetaiFrgmentFr;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_goodsdetails;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        LogUtils.d("FragmentGoodsPicDetails", bundle.getString("itemId"));

    }



}
