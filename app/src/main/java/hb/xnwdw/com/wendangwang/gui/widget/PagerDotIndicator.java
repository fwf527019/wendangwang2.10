package hb.xnwdw.com.wendangwang.gui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import hb.xnwdw.com.wendangwang.R;



/**
 * Created by Administrator on 2017/2/20.
 * Tips:指示器管理类
 */

public class PagerDotIndicator {


    private  ViewPager viewPager;
    private  LayoutInflater inflater;
    private  LinearLayout indicatorContainer;

    private int normalDotRes;
    private int selectedDotRes;

    public PagerDotIndicator(Context context, final LinearLayout indicatorContainer, ViewPager viewPager){
        this.indicatorContainer = indicatorContainer;
        this.viewPager = viewPager;
        this.inflater  = LayoutInflater.from(context);

        //设置指示器的图标样式
        normalDotRes = R.drawable.point_grey;
        selectedDotRes = R.drawable.point_click;

        //添加viewpager 监听
     viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
         @Override
         public void onPageSelected(int position) {
             super.onPageSelected(position);
             for (int i = 0; i <indicatorContainer.getChildCount() ; i++) {
                 ImageView imageview=
                         (ImageView) indicatorContainer.getChildAt(i).findViewById(R.id.imageView_indicator_dot);
                 if(position % indicatorContainer.getChildCount() == i){
                     imageview.setImageResource(selectedDotRes);
                 }else{
                     imageview.setImageResource(normalDotRes);
                 }
             }
         }
     });
    }

    /**
     * 外部可以设置指示器的图片资源（如果不设置就使用本类中默认的指示器图片）
     * @param normalDotRes
     * @param selectedDotRes
     */
    public void setDotRes(int normalDotRes,int selectedDotRes){
        this.normalDotRes = normalDotRes;
        this.selectedDotRes = selectedDotRes;
    }
    /**
     * 设置指示器的个数
     * @param size
     */
    public void setDotNums(int size) {
        //添加指示器 （有几页就加几个）
        for (int i = 0; i < size; i++) {
            View view = inflater.inflate(R.layout.subview_indicator_dots,null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView_indicator_dot);
            if(i == 0){
                imageView.setImageResource(selectedDotRes);
            }else{
                imageView.setImageResource(normalDotRes);
            }
            indicatorContainer.addView(view);
        }
    }
}