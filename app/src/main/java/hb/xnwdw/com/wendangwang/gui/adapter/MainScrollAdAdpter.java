package hb.xnwdw.com.wendangwang.gui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.ShearchResutsActivity;
import hb.xnwdw.com.wendangwang.gui.activity.UrlWebActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
import hb.xnwdw.com.wendangwang.utils.Utils;

/**
 * Created by Administrator on 2017/6/27.
 */

public class MainScrollAdAdpter extends PagerAdapter {
    int num = 0;
    private List<MainPagerSlidResponse.DatasBean> list;
    private Context context;
    private List<SimpleDraweeView> imgList;

    public MainScrollAdAdpter(final List<MainPagerSlidResponse.DatasBean> list, final Context context) {
        imgList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SimpleDraweeView view = new SimpleDraweeView(context);
            view.setImageURI(UrlApi.SERVER_IP + list.get(i).getAdvertPic());
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imgList.add(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //链接为商品详情
                    String url = list.get(finalI).getContentUrl();
                    try {
                        url = URLEncoder.encode(url, "utf-8");

                        if (list.get(finalI).getContentUrl().contains("/wdw/page/mb/gs_detail.html")) {
                            Intent intent = new Intent(context, GoodsDetails1.class);
                            intent.putExtra("itemId", Utils.cutString(url, "(?i)itemId="));
                            context.startActivity(intent);
                        } else if (url.contains("/wdw/page/mb/search_result.html?oneId=")) {
                            //跳转到收索结果页面
                            //网页：
//                        Intent intent = new Intent(getActivity(), UrlWebActivity.class);
//                        intent.putExtra("url", data.get(finalI).getMbPicUrl());
//                        intent.putExtra("tag", "more");
                            //原生：
                            Intent intent = new Intent(context, ShearchResutsActivity.class);
                            intent.putExtra("TAG", "classfy");
                            String TwoCateID = Utils.cutString(url, "(?i)twoId=");
                            intent.putExtra("TwoCateID", TwoCateID);
                            String OneCateID = Utils.cutString(url, "(?i)oneId=", "&");
                            intent.putExtra("OneCateID", OneCateID);
                            context.startActivity(intent);

                        } else if (url.contains("/wdw/page/mb/search_result.html?kw=")) {
                            Intent intent = new Intent(context, ShearchResutsActivity.class);
                            intent.putExtra("TAG", "搜索");
                            intent.putExtra("keyword", Utils.cutString(url, "kw="));
                            context.startActivity(intent);
                        } else {
                            Intent intent = new Intent(context, UrlWebActivity.class);
                            intent.putExtra("url", list.get(finalI).getContentUrl());
                            intent.putExtra("tital", "");
                            context.startActivity(intent);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

//    @Override
//    public View getView(final ViewGroup container, final int position) {
//        Log.d("MainScrollAdAdpter", "position:" + position);
//
//        SimpleDraweeView view = new SimpleDraweeView(context);
//        num+=1;
//        Log.d("MainScrollAdAdpter", "num:" + num);
//        view.setImageURI(UrlApi.SERVER_IP+list.get(position).getAdvertPic());
//        view.setScaleType(ImageView.ScaleType.FIT_XY);
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //链接为商品详情
//                String url=list.get(position).getContentUrl();
//                if(list.get(position).getContentUrl().contains("/wdw/page/mb/gs_detail.html")){
//                    Intent intent=new Intent(context, GoodsDetails1.class);
//                    intent.putExtra("itemId", Utils.cutString(url,"(?i)itemId="));
//                    context.startActivity(intent);
//                }else {
//                    Intent intent = new Intent(context, UrlWebActivity.class);
//                    intent.putExtra("url", list.get(position).getContentUrl());
//                    intent.putExtra("tital", "");
//                    context.startActivity(intent);
//                }
//            }
//        });
//        return view;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(imgList.get(position));
        return imgList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(imgList.get(position));
    }


}
