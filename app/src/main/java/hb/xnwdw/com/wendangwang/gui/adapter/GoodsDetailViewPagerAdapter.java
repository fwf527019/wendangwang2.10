package hb.xnwdw.com.wendangwang.gui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class GoodsDetailViewPagerAdapter extends PagerAdapter {
    List<View> list;
    private  CallBack callBack;
    public GoodsDetailViewPagerAdapter(List<View> list) {
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(View view, final int position) {

        View v = list.get(position);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.callBack(position);
            }
        });

        ((ViewPager) view).addView(list.get(position), 0);
        return list.get(position);
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        void callBack(int postion);

    }

}
