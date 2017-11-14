package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.MySaleFragmentPagerAdapter;
import hb.xnwdw.com.wendangwang.gui.fragment.MySaleAfterProcessFragment;
import hb.xnwdw.com.wendangwang.gui.fragment.MySaleApplyFragment;
import hb.xnwdw.com.wendangwang.gui.fragment.MySaleProcessFragment;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.SaleNum;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/8/1.
 */

public class MySaleAfter extends ActivityBase implements ViewPager.OnPageChangeListener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tx_applying)
    TextView txApplying;
    @BindView(R.id.sale_process)
    TextView saleProcess;
    @BindView(R.id.after_process)
    TextView afterProcess;
    @BindView(R.id.llt_title)
    LinearLayout lltTitle;
    @BindView(R.id.cursor)
    View cursor;
    @BindView(R.id.scroll_view)
    HorizontalScrollView scrollView;
    @BindView(R.id.fragment_view_pager)
    ViewPager fragmentViewPager;

    private MySaleFragmentPagerAdapter adapter;
    private int currentIndex;//当前页卡编号

    private int subTitleWidth;//子标题宽度，cursor平移距离
    private int titleBarHideOffset;//整个标题未显示的长度
    private int firstHideSubTitle;//第一个未显示全的标题（从0数起）
    private int hideTitleNum;//未显示标题的数目（从1数起）
    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mysaleafters;
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
        loadNum();
        title.setText("我的售后");
        EventBus.getDefault().register(this);
        //获取屏幕宽度、标题宽度
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int swidth = outMetrics.widthPixels;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) lltTitle.getLayoutParams();
        params.width = swidth;
        lltTitle.setLayoutParams(params);
        subTitleWidth = swidth / 3;//一个title的宽度
        int titleBarWidth = swidth;//整个title的宽度


        //标题栏最大移动距离与第一个需要移动的title
        titleBarHideOffset = titleBarWidth - swidth;//未显示出的部分
        if (titleBarHideOffset < 0) {
            titleBarHideOffset = 0;
            hideTitleNum = 0;
        } else {//这里逻辑开始略复杂，关键是获取第一个未显示全的title位置
            for (int i = 0; i < 3; i++) {
                if (swidth < subTitleWidth * (i + 1)) {
                    firstHideSubTitle = i;
                    break;
                }
            }
            hideTitleNum = 3 - firstHideSubTitle;
        }

        //view pager
        adapter = new MySaleFragmentPagerAdapter(
                getSupportFragmentManager(),
                getFragmentList()
        );
        fragmentViewPager.setAdapter(adapter);
        fragmentViewPager.addOnPageChangeListener(this);
    }


    @OnClick({R.id.tx_applying, R.id.sale_process, R.id.after_process, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tx_applying:
                fragmentViewPager.setCurrentItem(0);
                break;
            case R.id.sale_process:
                fragmentViewPager.setCurrentItem(1);
                break;
            case R.id.after_process:
                fragmentViewPager.setCurrentItem(2);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    public ArrayList<Fragment> getFragmentList() {
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MySaleApplyFragment());
        fragmentArrayList.add(new MySaleProcessFragment());
        fragmentArrayList.add(new MySaleAfterProcessFragment());
        return fragmentArrayList;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Animation animation = new TranslateAnimation(
                currentIndex * subTitleWidth,  //from X
                position * subTitleWidth,      //to X
                0, 0
        );

        //cursor滑动
        currentIndex = position;
        animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
        animation.setInterpolator(new DecelerateInterpolator());//减速动画
        animation.setDuration(300);//动画持续时间0.3秒
        cursor.startAnimation(animation);
        lltTitle.getChildAt(position).requestFocus();

        //整体滑动
        if (hideTitleNum > 0) {
            if (position < hideTitleNum) {//左侧
                if (scrollView.getScrollX() > position * subTitleWidth) {
                    scrollView.smoothScrollTo(position * subTitleWidth, 0);
                }
            }

            int num = position - firstHideSubTitle;
            if (num >= 0) {//右侧
                int scrollTo = titleBarHideOffset - (hideTitleNum - (num + 1)) * subTitleWidth;
                if (scrollView.getScrollX() < scrollTo) {
                    scrollView.smoothScrollTo(scrollTo, 0);
                }
            }
        }

        //title的text变色
        changeColor(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeColor(int position) {
        int[] color = new int[3];
        for (int i = 0; i < color.length; i++) {
            if (i == position) {
                color[i] = 0xffff9600;
            } else {
                color[i] = 0xff666666;
            }
        }

        txApplying.setTextColor(color[0]);
        saleProcess.setTextColor(color[1]);
        afterProcess.setTextColor(color[2]);
    }

   private SaleNum data;
    private void loadNum() {

        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetSaleStateNum, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MySaleAfter", response);
                 data = JSON.parseObject(response, SaleNum.class);
                saleProcess.setText("处理中(" + data.getObj().get(1).getCount() + ")");
                afterProcess.setText("已处理(" + data.getObj().get(0).getCount() + ")");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Integer event) {
        if (event==12) {
            loadNum();
        }else if(event==13){
            loadNum();
        }
    }

}
