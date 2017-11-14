package hb.xnwdw.com.wendangwang.gui.fragment;


import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hb.xnwdw.com.wendangwang.utils.AndroidBase.widget.LoadingDialog;


/**
 * Created by Administrator on 2017/2/20.
 */

public abstract class FragmentBase extends Fragment {
    LoadingFragment loadingFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getContentViewResId(),null);
    }

    protected abstract int getContentViewResId();


    // 显示进度条,flag==ture表示点击dialog外部，dialog不会消失。flag==false表示点击dialog外部，dialog会消失
    public void loading() {
        if (loadingFragment == null) {
            loadingFragment = new LoadingFragment();
        }
        //这句代码用于解决频繁点击时发生的崩溃现象，未测试
     //   getActivity().getSupportFragmentManager().executePendingTransactions();
        if (!loadingFragment.isAdded()) {
            loadingFragment.show(getActivity().getSupportFragmentManager(), "loadingFragment");
        }
    }

    // 隐藏进度条
    public void dismiss() {
        Fragment prev = getActivity().getSupportFragmentManager().findFragmentByTag("loadingFragment");
        if (prev != null) {
            loadingFragment.dismiss();
            return;
        }
        prev = getActivity().getSupportFragmentManager().findFragmentByTag("loadingNoPromptFragment");
        if (prev != null) {
            loadingFragment.dismiss();
            return;
        }
    }


    /**
     * dip转像素
     */
    public  int dip2px(float dpValue){
        float scale = this.getResources().getDisplayMetrics().density;
        return (int)(dpValue*scale+0.5f);
    }

    /**
     * 像素转dip
     */
    public  int px2dip(float pxValue){
        float scale = this.getResources().getDisplayMetrics().density;
        return (int)(pxValue/scale+0.5f);
    }
    /**
     * 获取状态栏高度
     */
    public int getStatusHeight() {
        final Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int n = rect.top;
        if (n != 0) {
            return n;
        }
        try {
            final Class<?> forName = Class.forName("com.android.internal.R$dimen");
            n = getResources().getDimensionPixelSize(Integer.parseInt(forName.getField("status_bar_height").get(forName.newInstance()).toString()));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        } catch (java.lang.InstantiationException ex3) {
            ex3.printStackTrace();
        } catch (NumberFormatException ex4) {
            ex4.printStackTrace();
        } catch (IllegalArgumentException ex5) {
            ex5.printStackTrace();
        } catch (SecurityException ex6) {
            ex6.printStackTrace();
        } catch (NoSuchFieldException ex7) {
            ex7.printStackTrace();
        }
        return n;
    }
//
//    /**
//     * 获取根布局
//     * @param
//     * @return
//     */
//    public View getRootView(Fragment fragment)
//    {
//        return ((ViewGroup)fragment.findViewById(android.R.id.content)).getChildAt(0);
//
//    }


    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(getActivity(), msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 设置标题栏信息
     */

}
