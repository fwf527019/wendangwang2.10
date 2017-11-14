package hb.xnwdw.com.wendangwang.gui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 */

public class GuiViewPagerAdapter extends PagerAdapter {
    List<View> list;

    public GuiViewPagerAdapter(List<View> list) {
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;
    }

    @Override
    public void destroyItem(View view, int position, Object object) {
        ((ViewPager) view).removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(View view, int position) {
        ((ViewPager) view).addView(list.get(position), 0);

        return list.get(position);
    }
}
