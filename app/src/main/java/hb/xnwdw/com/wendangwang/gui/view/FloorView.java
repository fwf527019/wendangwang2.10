package hb.xnwdw.com.wendangwang.gui.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.ShearchResutsActivity;
import hb.xnwdw.com.wendangwang.gui.activity.UrlWebActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.FloorViewAapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;
import hb.xnwdw.com.wendangwang.utils.Utils;

/**
 * Created by Administrator on 2017/7/14.
 */

public class FloorView extends LinearLayout {
    private TextView floorviewTital;
    private SimpleDraweeView flooviewImg;
    private RecyclerView floorviewList;

    public FloorView(final Context context, final List<FloorInfoData.DatasBean> data, final int pos) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.floorview, this);

        floorviewTital = (TextView) findViewById(R.id.floorview_tital);

        flooviewImg = (SimpleDraweeView) findViewById(R.id.flooview_img);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);
        int swidth = wm.getDefaultDisplay().getWidth();

        ViewGroup.LayoutParams params=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,swidth/2);
        flooviewImg.setLayoutParams(params);
        floorviewList = (RecyclerView) findViewById(R.id.floorview_list);

        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(context);
        flooviewImg.setImageURI(UrlApi.SERVER_IP+data.get(pos).getAppPic());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        floorviewList.setHasFixedSize(true);
        floorviewList.setNestedScrollingEnabled(false);

        floorviewList.setLayoutManager(layoutManager);
        FloorViewAapter adapter = new FloorViewAapter(R.layout.item_goods, data.get(pos).getItems());

        adapter.addFooterView( LayoutInflater.from(context).inflate(R.layout.sub_more, (ViewGroup) floorviewList.getParent(), false), -1, LinearLayout.HORIZONTAL);
//        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                Intent intent=new Intent(context, UrlWebActivity.class);
//                intent.putExtra("url",data.get(pos).getMbPicUrl());
//                context.startActivity(intent);
//            }
//        });
        adapter.getFooterLayout().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

               String url= data.get(pos).getTitleUrlMobile();

                try {
                    url= URLDecoder.decode(url,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                if(url.contains("/wdw/page/mb/gs_detail.html")) {
                    Intent intent = new Intent(context, GoodsDetails1.class);
                    intent.putExtra("itemId", Utils.cutString(url, "(?i)itemId="));
                    context.startActivity(intent);
//                }else if(url.contains("/wdw/page/mb/search_result.html")){
//                    Intent intent=new Intent(context,UrlWebActivity.class);
//                    intent.putExtra("url",url);
//                    intent.putExtra("tag","more");

                }else if (url.contains("/wdw/page/mb/search_result.html?oneId")) {
                    //跳转到收索结果页面
                    //原生：
                    Intent intent = new Intent(context, ShearchResutsActivity.class);
                    intent.putExtra("TAG", "classfy");
                    String TwoCateID=Utils.cutString(url,"(?i)twoId=");
                    intent.putExtra("TwoCateID",TwoCateID );
                    String OneCateID=Utils.cutString(url,"(?i)oneId=","&");
                    intent.putExtra("OneCateID",OneCateID);
                    context.startActivity(intent);
                }else if(url.contains("/wdw/page/mb/search_result.html?kw")){
                    Intent intent = new Intent(context, ShearchResutsActivity.class);
                    intent.putExtra("TAG", "搜索");
                    intent.putExtra("keyword",Utils.cutString(url,"kw="));
                    context.startActivity(intent);
                }


                else {
                    Intent intent=new Intent(context, UrlWebActivity.class);
                    intent.putExtra("url",url);
                    context.startActivity(intent);
                }


            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(context, GoodsDetails1.class);
                intent.putExtra("itemId",data.get(pos).getItems().get(position).getItemID());
                context.startActivity(intent);
                return false;
            }
        });
        floorviewList.setAdapter(adapter);

    }

    // 设置标题的方法
    public void setTitleText(String title) {
        floorviewTital.setText(title);
    }

    //图片点击链接
    public void setPicIntent(OnClickListener onClickListener) {
        flooviewImg.setOnClickListener(onClickListener);
    }

}
