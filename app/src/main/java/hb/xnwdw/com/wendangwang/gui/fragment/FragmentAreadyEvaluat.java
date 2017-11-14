package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/7/20.
 */

public class FragmentAreadyEvaluat extends FragmentBase {

    @BindView(R.id.aready_evaluate_list)
    RecyclerView areadyEvaluateList;
    Unbinder unbinder;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_areadyevaluat;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
