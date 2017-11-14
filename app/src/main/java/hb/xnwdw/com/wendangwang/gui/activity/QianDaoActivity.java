package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.HotSaleAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.HotSaleData;
import hb.xnwdw.com.wendangwang.netdata.entity.PostQianDaoData;
import hb.xnwdw.com.wendangwang.netdata.entity.QiaoDaoData;
import hb.xnwdw.com.wendangwang.netdata.entity.TodayQianDaoData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/3/22.
 */

public class QianDaoActivity extends ActivityBase {
    @BindView(R.id.qiandao_back)
    LinearLayout qiandaoBack;
    @BindView(R.id.qiandao_lianxu)
    TextView qiandaoLianxu;
    @BindView(R.id.qiaodao_rules)
    TextView qiaodaoRules;
    @BindView(R.id.qiandao_days)
    TextView qiandaoDays;
    @BindView(R.id.qiandao_run)
    ImageView qiandaoRun;
    @BindView(R.id.qiandao_btn)
    TextView qiandaoBtn;
    @BindView(R.id.qiandao_jinriqiandao)
    FrameLayout qiandaoJinriqiandao;
    @BindView(R.id.qiaodao_jifen)
    TextView qiaodaoJifen;
    @BindView(R.id.qiaodao_hotsale)
    RecyclerView qiaodaoHotsale;
    private Animation circle_anim;
    private HotSaleAdapter adapter;
    private boolean isQiandao = false;
    private String nextDay;
    private String qiaodao_rule;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_qiandao;
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setColor(QianDaoActivity.this, getResources().getColor(R.color.maincolor), 255);

    }

    @Override
    protected void initDatas() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        loadUserQianDaoData();
        loadHotSaleData();
        circle_anim = AnimationUtils.loadAnimation(this, R.anim.ddqb_anim_round_rotate);
        LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
        circle_anim.setInterpolator(interpolator);
    }

    /**
     * 热卖推荐
     */
    private void loadHotSaleData() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETHOTSALE, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity_Hot", response);
                final HotSaleData data = JSON.parseObject(response, HotSaleData.class);
                LinearLayoutManager myLinearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                myLinearLayoutManager.setSmoothScrollbarEnabled(false);
                qiaodaoHotsale.setLayoutManager(myLinearLayoutManager);
                adapter = new HotSaleAdapter(R.layout.item_shopcat_historygoods, data.getObj());
                qiaodaoHotsale.setAdapter(adapter);
                qiaodaoHotsale.setNestedScrollingEnabled(false);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), GoodsDetails1.class);
                        intent.putExtra("itemId", data.getObj().get(position).getID() + "");
                        startActivity(intent);
                    }
                });
                adapter.addFooterView(QianDaoActivity.this.getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) qiaodaoHotsale.getParent(), false));
            }
        });
    }

    /**
     * 获取当天签到的奖励和状态
     */
    private void loadUserQianDaoData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataSource", "Mobile");
        map.put("memberId", WDWApp.getMenberId());
        HtttpRequest.CheackIsLoginGet(this, UrlApi.URL_GETTODAYQIANDAOINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity_today", response);
                if (response.contains(MConstant.HTTP404)) {
                    return;
                } else {
                    TodayQianDaoData data = JSON.parseObject(response, TodayQianDaoData.class);
                    if (data.getDataStatus() == -1) {
                        Toast.makeText(QianDaoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    } else {
                        if (data.getObj().getSigned() == 1) {
                            qiandaoBtn.setText("已签到");
                            qiandaoBtn.setClickable(false);
                            qiandaoDays.setText(data.getObj().getContinue() + "");
                            isQiandao = true;
                            loadQinadaoDatas();
                        } else {
                            qiandaoDays.setText(data.getObj().getContinue() + "");
                            isQiandao = false;
                            loadQinadaoDatas();

                        }
                    }
                }
            }
        });
    }

    /**
     * 获取当日签到奖励数据
     */
    private String describ = "";
    private String dis = "";

    private void loadQinadaoDatas() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataSource", "Mobile");
        map.put("memberId", "10");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETQIANDAOINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity_a", response);
                if (!response.contains(MConstant.HTTP404)) {
                    JSONObject jsonObject = JSON.parseObject(response);
                    JSONObject object = (JSONObject) jsonObject.get("obj");

                    if (object != null) {
                        if (object.get("PrizeText") != null) {
                            dis = object.get("PrizeText").toString();
                        }
                        if (object.get("SignDescribe") != null) {
                            describ = (String) object.get("SignDescribe");

                        }


                        if (isQiandao) {
                            qiaodaoJifen.setText("明日签到可获得" + Utils.cutString(describ, "下次签到可获得").replace(".", ""));
                            nextDay = "明日签到可获得" + Utils.cutString(describ, "下次签到可获得").replace(".", "");

                        } else {
                            qiaodaoJifen.setText("今日签到可获得" + Utils.cutString(describ, "本次签到可获得", ","));


                        }
                    }
                }
            }
        });
    }

    @OnClick({R.id.qiandao_back, R.id.qiaodao_rules, R.id.qiandao_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.qiandao_back:
                finish();
                break;
            case R.id.qiaodao_rules:
                startActivity(new Intent(QianDaoActivity.this, QiandaoRules.class));


                break;
            case R.id.qiandao_btn:
                if (circle_anim != null) {
                    qiandaoRun.startAnimation(circle_anim);  //开始旋转动画
                }
                postData();
                break;
        }
    }

    /**
     * 向服务器提交签到数据
     */
    private void postData() {
        JSONObject object = new JSONObject();
        object.put("dataSource", "Mobile");
        object.put("memberId", "0");
        String paramsString = object.toJSONString();

        HtttpRequest.CreatPostRequst(UrlApi.URL_POSTQIANDAOINFO, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity_post", response);
                if (!response.contains(MConstant.HTTP404)) {
                    PostQianDaoData daoData = JSON.parseObject(response, PostQianDaoData.class);
                    if (daoData.getDataStatus() > 0 || daoData.getDescribe().equals("今日已经签到!")) {
                        isQiandao = true;
                        loadQinadaoDatas();
                        qiandaoBtn.setText(nextDay);
                        qiandaoRun.setVisibility(View.INVISIBLE);
                        qiandaoBtn.setClickable(false);
                        loadUserQianDaoData();

                        if (daoData.getObj().getPrizeType() == 2) {
                            Intent intent = new Intent(QianDaoActivity.this, QianDaoSecass.class);
                            intent.putExtra("ItemID", daoData.getObj().getItemID());
                            intent.putExtra("type", daoData.getObj().getPrizeType() + "");
                            intent.putExtra("ItemName", daoData.getObj().getItemName() + "");
                            intent.putExtra("ItemPic", daoData.getObj().getItemPic() + "");
                            intent.putExtra("RecordID", daoData.getObj().getRecordID() + "");
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(QianDaoActivity.this, QianDaoSecass.class);
                            intent.putExtra("type", daoData.getObj().getPrizeType() + "");
                            intent.putExtra("Integral", daoData.getObj().getIntegral() + "");
                            intent.putExtra("CouponMoney", daoData.getObj().getCouponMoney() + "");
                            intent.putExtra("Start", daoData.getObj().getCouponStart() + "");
                            intent.putExtra("End", daoData.getObj().getCouponEnd() + "");
                            intent.putExtra("Condition", daoData.getObj().getCouponCondition() + "");
                            intent.putExtra("CouponName", daoData.getObj().getCouponName() + "");
                            startActivity(intent);
                        }
                    }
                }
            }
        });

    }

    /**
     * 签到规则popuwindow
     */

    private void showHeader() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwidow_qiandaorules, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(true);
        //  bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        TextView tv_exit = (TextView) contentView.findViewById(R.id.btna_exit);
        WebView tv_contont = (WebView) contentView.findViewById(R.id.aa);
        WebViewSetings.setWebView(tv_contont, UrlApi.SERVER_IP + "/wdw/page/mb/integral_explain.html?noticeType=签到说明", getApplicationContext());
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.show();
    }


    /***
     * 获取会员月签到记录表
     */
    private void loadUserQiandaoIfon() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("dataSource", "Mobile");
        map.put("memberId", "10");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETQIANDAOINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity", response);
                QiaoDaoData qiaoDaoData = JSON.parseObject(response, QiaoDaoData.class);
                qiaodaoJifen.setText(qiaoDaoData.getObj().getSignDescribe());

            }
        });
    }


}
