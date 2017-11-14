package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.MiaoShaAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/28.
 */

public class MiaoShaActivity extends ActivityBase {
    @BindView(R.id.miaoshalist_hour)
    TextView miaoshalistHour;
    @BindView(R.id.miaoshalist_minut)
    TextView miaoshalistMinut;
    @BindView(R.id.miaoshalist_second)
    TextView miaoshalistSecond;
    @BindView(R.id.miaohs_recycler)
    RecyclerView miaohsRecycler;
    @BindView(R.id.miaoshalist_back)
    LinearLayout miaoshalistBack;
    private MiaoShaAdapter adapter;
    private long time;
    private MainPageMiaoShaDate data;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time -= 1000;
            String formatLongToTimeStr = formatLongToTimeStr(time);
            String[] split = formatLongToTimeStr.split("：");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    miaoshalistHour.setText(split[0] + "");
                }
                if (i == 1) {
                    miaoshalistMinut.setText(split[1] + "");
                }
                if (i == 2) {
                    miaoshalistSecond.setText(split[2] + "");
                }

            }
            if (time > 0) {
                handler.postDelayed(this, 1000);
            }
        }
    };

    public String formatLongToTimeStr(Long l) {
        int hour = 0;
        int minute = 0;
        int second = 0;
        second = l.intValue() / 1000;
        if (second > 60) {
            minute = second / 60;         //取整
            second = second % 60;         //取余
        }

        if (minute > 60) {
            hour = minute / 60;
            minute = minute % 60;
        }
        String strtime = hour + "：" + minute + "：" + second;
        return strtime;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_miaosha;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setColor(MiaoShaActivity.this, getResources().getColor(R.color.maincolor), 0);
    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        LoadDta();
        handler.postDelayed(runnable, 1000);
    }

    private void LoadDta() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_MAINMIAOSHA, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MiaoShaActivity", response);

                data = JSON.parseObject(response, MainPageMiaoShaDate.class);

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d1 = null;
                try {
                    d1 = df.parse(data.getEndTm());
                    long da1 = d1.getTime();
                    long da2 = System.currentTimeMillis();
                    long diff = da1 - da2;
                    long hours = diff / (1000 * 60 * 60);
                    long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
                    long second = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000 * 60);
                    time = diff;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                miaohsRecycler.setLayoutManager(new MyLinearLayoutManager(getApplicationContext()));
                adapter = new MiaoShaAdapter(R.layout.item_miaosha, data.getItems());
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        return false;
                    }
                });
                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(MiaoShaActivity.this,GoodsDetails1.class);
                        intent.putExtra("itemId", data.getItems().get(position).getItemID());
                        startActivity(intent);
                        return false;
                    }
                });
                miaohsRecycler.setAdapter(adapter);


            }
        });
    }


    @OnClick(R.id.miaoshalist_back)
    public void onViewClicked() {
        finish();
    }
}
