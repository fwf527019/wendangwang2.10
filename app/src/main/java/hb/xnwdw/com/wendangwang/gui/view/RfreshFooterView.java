package hb.xnwdw.com.wendangwang.gui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.callback.IFooterCallBack;

import com.andview.refreshview.utils.Utils;

import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RfreshFooterView extends LinearLayout implements IFooterCallBack{


    public RfreshFooterView(Context context) {
        super(context);
        initView(context);
    }

    public RfreshFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void callWhenNotAutoLoadMore(XRefreshView xRefreshView) {

    }

    @Override
    public void onStateReady() {
        startRotate();
    }

    @Override
    public void onStateRefreshing() {
        startRotate();
    }

    @Override
    public void onReleaseToLoadMore() {
        startRotate();
    }

    @Override
    public void onStateFinish(boolean hidefooter) {
        startRotate();
    }

    @Override
    public void onStateComplete() {
        stopRotate();
    }

    @Override
    public void show(boolean show) {

    }

    @Override
    public boolean isShowing() {
        return false;
    }

    @Override
    public int getFooterHeight() {
        return 0;
    }
    private GifView gifView2;
    public void startRotate(){
        gifView2.setPaused(false);
    }
    /**
     * 关闭动画
     */
    public void stopRotate(){
        gifView2.setPaused(true);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.gif_header, this);
        gifView2 = (GifView) findViewById(R.id.gif2);
        ((TextView) findViewById(R.id.gif_header_hint)).setVisibility(GONE);
        gifView2.setMovieResource(R.raw.refresh);
    }
}
