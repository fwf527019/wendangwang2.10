package hb.xnwdw.com.wendangwang.gui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.callback.IHeaderCallBack;

import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RfreshHeaderView  extends LinearLayout implements IHeaderCallBack{

    private GifView gifView2;
    private TextView mHintTextView;

    public RfreshHeaderView(Context context) {
        super(context);
        setBackgroundColor(Color.parseColor("#f3f3f3"));
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public RfreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.gif_header, this);

        mHintTextView = (TextView) findViewById(R.id.gif_header_hint);
        gifView2 = (GifView) findViewById(R.id.gif2);
//        gifView2.setImageResource(R.drawable.refresh_arrow);
        gifView2.setMovieResource(R.raw.refresh);
        gifView2.setVisibility(View.GONE);
    }

    public void setRefreshTime(long lastRefreshTime) {
    }

    public void hide() {
        setVisibility(View.GONE);
    }

    public void show() {
        setVisibility(View.VISIBLE);
    }

    @Override
    public void onStateNormal() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_normal);

        gifView2.setVisibility(View.GONE);


    }

    @Override
    public void onStateReady() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_ready);
    }

    @Override
    public void onStateRefreshing() {
        mHintTextView.setText(R.string.xrefreshview_header_hint_refreshing);

        gifView2.setVisibility(View.VISIBLE);
        gifView2.setPaused(false);
        startRotate();
    }

    @Override
    public void onStateFinish(boolean success) {
        mHintTextView.setText(success ? R.string.xrefreshview_header_hint_loaded : R.string.xrefreshview_header_hint_loaded_fail);
//        gifView1.setVisibility(View.VISIBLE);
        gifView2.setVisibility(View.GONE);
        gifView2.setPaused(true);
        stopRotate();
    }

    @Override
    public void onHeaderMove(double headerMovePercent, int offsetY, int deltaY) {
        //
    }

    @Override
    public int getHeaderHeight() {
        return getMeasuredHeight();
    }
    public void startRotate(){

    }
    /**
     * 关闭动画
     */
    public void stopRotate(){

    }
}
