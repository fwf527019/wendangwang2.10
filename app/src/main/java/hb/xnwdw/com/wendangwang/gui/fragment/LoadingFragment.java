package hb.xnwdw.com.wendangwang.gui.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.view.BounceLoadingView;

/**
 * Created by Administrator on 2017/6/9.
 */
public class LoadingFragment extends DialogFragment {
    private View view;
    private BounceLoadingView loadingView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(true);
//        Window window = getDialog().getWindow();
//        WindowManager.LayoutParams windowParams = window.getAttributes();
//        windowParams.dimAmount = 0.0f;
//        window.setAttributes(windowParams);
        getDialog().getWindow().setBackgroundDrawable(new
                ColorDrawable(Color.TRANSPARENT));
        view = inflater.inflate(R.layout.fragment_loading, container);
        loadingView = (BounceLoadingView) view.findViewById(R.id.loadingView);
        loadingView.addBitmap(R.drawable.v1);
        loadingView.addBitmap(R.drawable.v2);
        loadingView.addBitmap(R.drawable.v3);
        loadingView.setShadowColor(getResources().getColor(R.color.greys));
        loadingView.setDuration(1000);
        loadingView.start();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loadingView.stop();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_NoTitleBar_Fullscreen);

    }
}
