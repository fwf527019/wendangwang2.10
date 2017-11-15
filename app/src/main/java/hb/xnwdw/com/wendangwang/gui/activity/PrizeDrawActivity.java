package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.DrawLuck;
import hb.xnwdw.com.wendangwang.netdata.entity.DrawsData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/30.
 */

public class PrizeDrawActivity extends ActivityBase {
    private List<TextView> views = new LinkedList<>();//所有的视图
    private int timeC = 100;//变色时间间隔
    private int lightPosition = 0;//当前亮灯位置,从0开始
    private int runCount = 5;//需要转多少圈
    private int lunckyPosition;//中奖的幸运位置,从0开始
    TextView btnLuck;
    ImageView img;
    List<String> drawList;
    private int[] random = {5, 1, 2, 7, 4, 3, 6, 0};

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_prizedraw;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDraws();
    }


    private void init() {
        TextView tv1 = (TextView) findViewById(R.id.tvv1);
        tv1.setText(drawList.get(0));

        TextView tv2 = (TextView) findViewById(R.id.tvv2);
        tv2.setText(drawList.get(1));

        TextView tv3 = (TextView) findViewById(R.id.tvv3);
        tv3.setText(drawList.get(2));

        TextView tv4 = (TextView) findViewById(R.id.tvv4);
        tv4.setText(drawList.get(3));

        TextView tv5 = (TextView) findViewById(R.id.tvv5);
        tv5.setText(drawList.get(4));

        TextView tv6 = (TextView) findViewById(R.id.tvv6);
        tv6.setText(drawList.get(5));

        TextView tv7 = (TextView) findViewById(R.id.tvv7);
        tv7.setText(drawList.get(6));

        TextView tv8 = (TextView) findViewById(R.id.tvv8);
        tv8.setText(drawList.get(7));
        img = (ImageView) findViewById(R.id.luckdrawkuang_img);
        btnLuck = (TextView) findViewById(R.id.btn_luck);

        views.add(tv1);
        views.add(tv2);
        views.add(tv3);
        views.add(tv4);
        views.add(tv5);
        views.add(tv6);
        views.add(tv7);
        views.add(tv8);

        try {
            btnLuck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnLuck.setClickable(false);
                    btnLuck.setEnabled(false);
                    runCount = 5;
                    timeC = 100;
                    views.get(lunckyPosition).setBackgroundResource(R.drawable.lattice);
                    new TimeCount(timeC * 9, timeC).start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int randomNum(int minNum, int maxNum) {
        int max = maxNum;
        int min = minNum;
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }


    private class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            lightPosition = 0;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i(">>>", "---" + lightPosition);
            if (runCount > 0) {
                if (lightPosition > 0) {
                    views.get(lightPosition - 1).setBackgroundResource(R.drawable.lattice);
                    if (lightPosition % 2 == 0) {
                        img.setBackgroundResource(R.drawable.bg1);

                    } else {
                        img.setBackgroundResource(R.drawable.bg2);
                    }

                }
                if (lightPosition < 8) {
                    views.get(lightPosition).setBackgroundResource(R.drawable.lattice_h);
                    if (lightPosition % 2 == 0) {
                        img.setBackgroundResource(R.drawable.bg1);

                    } else {
                        img.setBackgroundResource(R.drawable.bg2);
                    }
                }

            } else if (runCount == 0) {

                if (lightPosition <= lunckyPosition) {
                    if (lightPosition > 0) {
                        views.get(lightPosition - 1).setBackgroundResource(R.drawable.lattice);
                        if (lightPosition % 2 == 0) {
                            img.setBackgroundResource(R.drawable.bg1);

                        } else {
                            img.setBackgroundResource(R.drawable.bg2);
                        }
                    }
                    if (lightPosition < 8) {
                        views.get(lightPosition).setBackgroundResource(R.drawable.lattice_h);
                        if (lightPosition % 2 == 0) {
                            img.setBackgroundResource(R.drawable.bg1);

                        } else {
                            img.setBackgroundResource(R.drawable.bg2);
                        }
                    }
                }
            }
            lightPosition++;
        }


        @Override
        public void onFinish() {
            Log.i(">>>", "onFinish==" + runCount);
            //如果不是最后一圈，需要还原最后一块的颜色
            TextView tvLast = views.get(7);
            if (runCount != 0) {
                tvLast.setBackgroundResource(R.drawable.lattice);
                //最后几转速度变慢
                if (runCount < 3) timeC += 200;
                new TimeCount(timeC * 9, timeC).start();
                runCount--;
            }

            //如果是最后一圈且计时也已经结束
            if (runCount == 0 && lightPosition == 8) {
                btnLuck.setClickable(false);
                btnLuck.setEnabled(false);
                //     Toast.makeText(PrizeDrawActivity.this, "恭喜你抽中: " + views.get(lunckyPosition).getText().toString(), Toast.LENGTH_SHORT).show();
                showPopupWindow();
                if (lunckyPosition != views.size())
                    tvLast.setBackgroundResource(R.drawable.lattice);
            }
        }
    }

    private PopupWindow mPopuwidow;

    private void showPopupWindow() {

        View contentView = LayoutInflater.from(this).inflate(
                R.layout.popupwindow_luckdraw, null);

        mPopuwidow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopuwidow.setContentView(contentView);

        ImageView img_exit = (ImageView) contentView.findViewById(R.id.popuwid_draw_exit);
        TextView jifen_tv = (TextView) contentView.findViewById(R.id.popuwid_draw_jifentishi);
        TextView jifen_tv1 = (TextView) contentView.findViewById(R.id.popuwid_draw_jifen);
        if (drawList.get(lunckyPosition).equals("谢谢惠顾")) {
            jifen_tv.setText("您抽中0积分");
            jifen_tv1.setText("0");
        } else {
            jifen_tv.setText("恭喜您抽中" + drawList.get(lunckyPosition));
            jifen_tv1.setText(data.getObj().get(random[lunckyPosition]).getValue() + "");
        }


        Button gotojifen = (Button) contentView.findViewById(R.id.popuwid_draw_seejifen);


        gotojifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyWenDangJiFenActivity.class));
                finish();
            }
        });
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopuwidow.dismiss();
                startActivity(new Intent(PrizeDrawActivity.this, MainPagerActivity.class).putExtra("fr", 4));
            }
        });
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_prizedraw, null);
        mPopuwidow.showAtLocation(rootview, Gravity.TOP, 0, 0);

    }

    /**
     * 获取奖品
     */
    private DrawsData data;

    private void getDraws() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETDraws, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("PrizeDrawActivity", response);
                data = JSON.parseObject(response, DrawsData.class);
                drawList = new ArrayList<String>();


                for (int i = 0; i < data.getObj().size(); i++) {
                    drawList.add(data.getObj().get(random[i]).getDisplay());
                }
                GetLuckGrade();
            }
        });

    }

    /**
     * 获取中奖的等级
     */

    private void GetLuckGrade() {
        Map<String, String> map = new HashMap<>();
        map.put("sOrderNumber", WDWApp.payOrderNum);
        // map.put("sOrderNumber", "201709070022");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GetLuckGrade, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(PrizeDrawActivity.this, "网络连接超时", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("PrizeDrawActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    DrawLuck dataw = JSON.parseObject(response, DrawLuck.class);

                    for (int i = 0; i < drawList.size(); i++) {
                        if (dataw.getObj()!=null) {
                            if (dataw.getObj().getIGrade() == data.getObj().get(random[i]).getGrade()) {
                                lunckyPosition = i;
                                LogUtils.d("PrizeDrawActivity", "lunckyPosition:" + lunckyPosition);
                            }
                        }
                    }
                    init();
                }

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(new Intent(PrizeDrawActivity.this, MainPagerActivity.class).putExtra("fr", 4));
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }


}

