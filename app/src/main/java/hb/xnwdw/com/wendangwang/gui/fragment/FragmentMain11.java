package hb.xnwdw.com.wendangwang.gui.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.ConpoudActivity;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MiaoShaActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyMessageMianPage;
import hb.xnwdw.com.wendangwang.gui.activity.NearShopActivity;
import hb.xnwdw.com.wendangwang.gui.activity.PreferredBrandActivity;
import hb.xnwdw.com.wendangwang.gui.activity.QianDaoActivity;
import hb.xnwdw.com.wendangwang.gui.activity.ShearchResutsActivity;
import hb.xnwdw.com.wendangwang.gui.activity.SherchPageActivity;
import hb.xnwdw.com.wendangwang.gui.activity.TongGaoDetail;
import hb.xnwdw.com.wendangwang.gui.activity.UrlWebActivity;
import hb.xnwdw.com.wendangwang.gui.activity.ZiXunActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.MainScrollAdAdpter;
import hb.xnwdw.com.wendangwang.gui.adapter.RecommendeNewAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.RecommendeYouAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.SekKillAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.TongGaoAdapter;
import hb.xnwdw.com.wendangwang.gui.view.FloorView;
import hb.xnwdw.com.wendangwang.gui.view.MyScrollView;
import hb.xnwdw.com.wendangwang.gui.view.RollingView;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AdverNotice;
import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;
import hb.xnwdw.com.wendangwang.netdata.entity.NavData;
import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;
import hb.xnwdw.com.wendangwang.netdata.entity.WhatIsShow;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.FloorInfoPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainRecommendeYouPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainSlidpagerPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MianMiaoShaPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.NewCommendePresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.FloorInfoPresenterImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainMiaoShaPresenterImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainRecommendeYouPresenterImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainSlidPagerPresenterImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.NewCommendePresenterImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.FloorInfoView;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainMiaoShaView;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainRecommendeYouView;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainslidView;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.NewCommendeView;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/20.
 */

public class FragmentMain11 extends FragmentBase implements MainslidView, MainMiaoShaView, NewCommendeView, MainRecommendeYouView, FloorInfoView, HtttpRequest.LoginStatu {

    @BindView(R.id.main_scrollad)
    RollPagerView mainScrollad;
    @BindView(R.id.nav1_ll)
    LinearLayout nav1Ll;
    @BindView(R.id.nav2_ll)
    LinearLayout nav2Ll;
    @BindView(R.id.mad_view)
    RollingView madView;
    @BindView(R.id.mian_hour)
    TextView mianHour;
    @BindView(R.id.mian_minut)
    TextView mianMinut;
    @BindView(R.id.main_second)
    TextView mainSecond;
    @BindView(R.id.main_miaoshatital)
    LinearLayout mainMiaoshatital;
    @BindView(R.id.sekkill_recycler)
    RecyclerView sekkillRecycler;
    @BindView(R.id.main_newrecmend_tatil)
    LinearLayout mainNewrecmendTatil;
    @BindView(R.id.recomment_recycler)
    RecyclerView recommentRecycler;
    @BindView(R.id.floor_ll)
    LinearLayout floorLl;
    @BindView(R.id.main_recmendyou)
    LinearLayout mainRecmendyou;
    @BindView(R.id.youcomment_recyclerview)
    RecyclerView youcommentRecyclerview;
    @BindView(R.id.fab_fragment_main_list)
    FloatingActionButton fabFragmentMainList;
    @BindView(R.id.main_titel_fujin_img)
    ImageView mainTitelFujinImg;
    @BindView(R.id.main_titel_fujin_tv)
    TextView mainTitelFujinTv;
    @BindView(R.id.zx_img_ll)
    LinearLayout zxImgLl;
    @BindView(R.id.main_sherch_img)
    ImageView mainSherchImg;
    @BindView(R.id.main_sherch_tv)
    TextView mainSherchTv;
    @BindView(R.id.main_sherch_ll)
    LinearLayout mainSherchLl;
    @BindView(R.id.main_my_massege_img)
    ImageView mainMyMassegeImg;
    @BindView(R.id.main_my_massege_tv)
    TextView mainMyMassegeTv;
    @BindView(R.id.main_messege_ll)
    LinearLayout mainMessegeLl;
    @BindView(R.id.main_titel_ll)
    LinearLayout mainTitelLl;
    @BindView(R.id.scrollview)
    MyScrollView scrollview;
    @BindView(R.id.miaosha_type)
    TextView miaoshaType;
    @BindView(R.id.maintitail_pad)
    LinearLayout maintitailPad;
    @BindView(R.id.miaosha_list_ll)
    LinearLayout miaoshaListLl;
    private MainSlidpagerPresenter mpresenter;
    private int sheight;
    private int height;
    private int swidth;
    private MyCount mc;
    //稳当秒杀
    Handler handler = new Handler();
    private MianMiaoShaPresenter mianMiaoShaPresenter;
    private SekKillAdapter sekKillAdapter;
    //新品推荐
    private RecommendeNewAdapter recommendeNewAdapter;
    private NewCommendePresenter newCommendePresenter;

    //为你推荐
    private MainRecommendeYouPresenter mainRecommendeYouPresenter;
    private RecommendeYouAdapter recommendeYouAdapter;
    private List<MainRecommendeYouData.DatasBean> RecommendeYouData = new ArrayList<>();

    //楼层
    private FloorInfoPresenter floorInfoPresenter;
    private int pageIdex = 1;

    /**
     * 秒杀倒计时时间
     */
    private long time;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_main;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        maintitailPad.get
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusHeight());
        maintitailPad.setLayoutParams(params);
        mpresenter = new MainSlidPagerPresenterImpl(this);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        swidth = dm.widthPixels;
        sheight = dm.heightPixels;
        initNavData();
        initADdata();
        GetSearchTital();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mainScrollad.getLayoutParams();
        layoutParams.height = swidth / 3;


        //设置播放时间间隔
        mainScrollad.setPlayDelay(2500);
        //设置透明度
        mainScrollad.setAnimationDurtion(500);
        //设置适配器
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mainScrollad.setHintView(new ColorPointHintView(getActivity(), getResources().getColor(R.color.maincolor), Color.WHITE));
        //mRollViewPager.setHintView(new TextHintView(this));
        //mRollViewPager.setHintView(null);

        mpresenter.loadDatas();
        //设置搜索栏
        //FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //   layoutParams.setMargins(0, getStatusHeight(), 0, 0);
        //    mainTitelLl.setLayoutParams(layoutParams);
        //    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd);
        mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd1);
        // mainTitelLl.setAlpha((float) 0.9);
        //   mainSherchLl.getBackground().setAlpha(1);

        loadMassegeNum();
        //获取秒杀数据
        if (MConstant.seckillDisplay == 1) {
            mianMiaoShaPresenter = new MainMiaoShaPresenterImpl(this);
            mianMiaoShaPresenter.loadDatas();
        }


        //为你推荐数据
        mainRecommendeYouPresenter = new MainRecommendeYouPresenterImpl(this);
        mainRecommendeYouPresenter.loadDtas(5, 1);
        //楼层数据
        floorInfoPresenter = new FloorInfoPresenterImpl(this);
        floorInfoPresenter.loadDatas();

        initFab();
        scrollview.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int t) {
                LogUtils.d("FragmentMain11", "scrollY:" + t);

                // 设置搜索栏的状态
                if (t <= dip2px(100) && t > dip2px(5)) {
                    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd);
                    //    mainTitelLl.setAlpha((float) (t + dip2px(50)) / dip2px(100));
                    mainTitelLl.setAlpha((float) 1);
                }
                if (t > dip2px(20)) {
                    mainTitelFujinImg.setImageResource(R.drawable.fujin1);
                    mainTitelFujinTv.setTextColor(getResources().getColor(R.color.black));

                    mainMyMassegeImg.setImageResource(R.drawable.ic_messageno);
                    mainMyMassegeTv.setTextColor(getResources().getColor(R.color.black));

                    mainSherchLl.setBackgroundResource(R.drawable.sherchbg1);
                    mainSherchTv.setTextColor(getResources().getColor(R.color.white));
                    mainSherchImg.setImageResource(R.drawable.ic_search_white);
                    mainTitelLl.setAlpha((float) 1);
                }
                if (t < dip2px(5)) {
                    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd1);
                    mainTitelLl.setAlpha((float) 0.9);

                    mainTitelFujinImg.setImageResource(R.drawable.fujin);
                    mainTitelFujinTv.setTextColor(getResources().getColor(R.color.white));

                    mainMyMassegeImg.setImageResource(R.drawable.ic_message);
                    mainMyMassegeTv.setTextColor(getResources().getColor(R.color.white));

                    mainSherchLl.setBackgroundResource(R.drawable.sherch);
                    mainSherchTv.setTextColor(getResources().getColor(R.color.gry));
                    mainSherchImg.setImageResource(R.drawable.ic_search_grey);

                    //     mainTitelLl.setAlpha((float) 0);
                }
                if (t >= height / 3) {
                    fabFragmentMainList.setVisibility(View.VISIBLE);

                } else {
                    fabFragmentMainList.setVisibility(View.GONE);

                }
            }

        });


//        scrollview.setOnScrollListener(new XScrollView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {
//                LogUtils.d("FragmentMain11", "scrollState:" + scrollState);
//            }
//
//            @Override
//            public void onScroll(int l, int t, int oldl, int oldt) {
//
//                LogUtils.d("FragmentMain11", "t:" + t);
//
//                LogUtils.d("FragmentMain11", "oldt:" + oldt);
//
//        });
    }


    //广告滚动
    private void initADdata() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetNoticeList, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404)) {
                    final AdverNotice datas = JSON.parseObject(response, AdverNotice.class);
                    TongGaoAdapter adapter = new TongGaoAdapter(datas.getObj(), getActivity());
                    List<String> list = new ArrayList<String>();
                    for (int i = 0; i < datas.getObj().size(); i++) {
                        list.add(datas.getObj().get(i).getTitle());

                    }
                    madView.setRollingText(list);
                    madView.setPageSize(1);
                    madView.setOnItemClickListener(new RollingView.onItemClickListener() {
                        @Override
                        public void onItemClick(TextView v, int pos) {
                            Intent intent = new Intent(getActivity(), TongGaoDetail.class);
                            intent.putExtra("id", datas.getObj().get(pos).getID() + "");
                            intent.putExtra("titles", datas.getObj().get(pos).getTitle() + "");
                            Log.d("FragmentMain11", datas.getObj().get(pos).getID() + "");
                            startActivity(intent);
                        }
                    });
                    madView.resume();
                }
            }
        });
    }

    //广告轮播回调
    @Override
    public void showPagerSlidData(List<MainPagerSlidResponse.DatasBean> databean) {
        List<MainPagerSlidResponse.DatasBean> datas = databean;
        mainScrollad.setAdapter(new MainScrollAdAdpter(datas, getActivity()));
        mainScrollad.setPlayDelay(3000);
        mainScrollad.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    @Override
    public void showDatasError() {

    }

    //秒杀层数据回调
    @Override
    public void showMiaoShaDatas(final List<MainPageMiaoShaDate.ItemsBean> itemsBeen) {
        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        sekkillRecycler.setLayoutManager(linearLayoutManager);
        final List<MainPageMiaoShaDate.ItemsBean> data;
        if (itemsBeen.size() >= 5) {
            data = itemsBeen.subList(0, 5);
        } else {
            data = itemsBeen;
        }
        sekKillAdapter = new SekKillAdapter(R.layout.item_goods, data);
        sekKillAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) sekkillRecycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
        sekKillAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (miaoshaType.getText().equals("距离结束:")) {
                    Intent intent = new Intent(getActivity(), MiaoShaActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "活动还没开始，请赖心等待", Toast.LENGTH_SHORT).show();
                }

            }
        });
        sekkillRecycler.setAdapter(sekKillAdapter);
        sekKillAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                intent.putExtra("itemId", data.get(position).getItemID());
                startActivity(intent);
            }
        });

    }

    /**
     * 秒杀剩余时间传给time
     *
     * @param date
     */
    @Override
    public void shouMiaoshaTim(MainPageMiaoShaDate date) {

        if (date.getLimitNewMember() != null && date.getLimitNewMember().equals("1")) {
            SekKillAdapter.newUserLimit = "1";
        } else {
            SekKillAdapter.newUserLimit = "0";
        }

        if (date != null) {
            mainMiaoshatital.setVisibility(View.VISIBLE);
            miaoshaListLl.setVisibility(View.VISIBLE);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d1 = null;
            Date d2 = null;
            long da3 = 0;
            try {
                d1 = df.parse(date.getEndTm());
                if (date.getBegTm() != null) {
                    d2 = df.parse(date.getBegTm());
                    da3 = d2.getTime();

                }
                long da1 = d1.getTime();
                long da2 = System.currentTimeMillis();
                long diffStar = da2 - da3;
                long diffEnd = da1 - da2;
                /**秒杀开始倒计时****/
                if (diffStar <= 0) {
                    time = Math.abs(diffStar);
                    miaoshaType.setText("距离开始:");
                    if (time != 0) {
                        mc = new MyCount(time, 1000);
                        mc.start();
                    }
                } else {
                    /******秒杀结束倒计时*****/
                    time = diffEnd;
                    LogUtils.d("FragmentMain11", "time:" + time);
                    miaoshaType.setText("距离结束:");

                    if (time != 0) {
                        mc = new MyCount(time, 1000);
                        mc.start();
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private List<NewRecommendeData.DatasBean> NewRecommendeDatadata;

    //新品推荐数据回调
    @Override
    public void showNewCommende(final List<NewRecommendeData.DatasBean> itemsBeen) {
        NewRecommendeDatadata = new ArrayList<>();

        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recommentRecycler.setLayoutManager(linearLayoutManager);
        NewRecommendeDatadata = itemsBeen;
        recommendeNewAdapter = new RecommendeNewAdapter(R.layout.item_goods, NewRecommendeDatadata);
        recommentRecycler.setAdapter(recommendeNewAdapter);
        //     recommendeNewAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) sekkillRecycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
        recommendeNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                intent.putExtra("itemId", itemsBeen.get(position).getItemID());
                startActivity(intent);
            }
        });

    }


    //为你推荐数据回调

    @Override
    public void showRecommendeYouShaDatas(final List<MainRecommendeYouData.DatasBean> datasBeen) {
        if (datasBeen == null) {
            mainRecmendyou.setVisibility(View.GONE);
            youcommentRecyclerview.setVisibility(View.GONE);
        } else {
            MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
            youcommentRecyclerview.setLayoutManager(linearLayoutManager);
            youcommentRecyclerview.setNestedScrollingEnabled(false);
            recommendeYouAdapter = new RecommendeYouAdapter(R.layout.item_youcommend, datasBeen);
            youcommentRecyclerview.setAdapter(recommendeYouAdapter);
            recommendeYouAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_getmore, (ViewGroup) youcommentRecyclerview.getParent(), false));
            recommendeYouAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pageIdex += 1;
                    if (pageIdex <= 4) {
                        Map<String, String> map = new HashMap<>();
                        map.put("page", pageIdex + "");
                        map.put("pageSize", "5");
                        HtttpRequest.CreatGetRequst(UrlApi.URL_RCOMMENDEYOU, map, new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                if (!response.contains(MConstant.HTTP404)) {
                                    String ss = "{\"datas\":";
                                    String str = null;
                                    LogUtils.d("NewCommendeModelImpl", response);
                                    if (response.startsWith("[")) {
                                        str = ss + response + "}";
                                    }
                                    MainRecommendeYouData datas = JSON.parseObject(str, MainRecommendeYouData.class);
                                    recommendeYouAdapter.addData(datas.getDatas());
                                    if (datas.getDatas().size() == 0) {
                                        TextView tv = (TextView) recommendeYouAdapter.getFooterLayout().findViewById(R.id.getmore_tv);
                                        tv.setText("没有更多");
                                    }
                                }
                            }
                        });
                    } else {
                        TextView tv = (TextView) recommendeYouAdapter.getFooterLayout().findViewById(R.id.getmore_tv);
                        tv.setText("没有更多");
                    }
                }
            });

            recommendeYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.d("FragmentMain11", "position:" + position);
                    Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                    intent.putExtra("itemId", datasBeen.get(position).getItemID() + "");
                    startActivity(intent);
                }
            });
            recommendeYouAdapter.setOnItemChildClickListener(
                    new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                            intent.putExtra("itemId", datasBeen.get(position).getItemID() + "");
                            startActivity(intent);
                            return false;
                        }
                    });

        }
    }

    //楼层数据回调
    @Override
    public void showFloorInfoData(List<FloorInfoData.DatasBean> dataBeen) {
        final List<FloorInfoData.DatasBean> data = dataBeen;

        for (int i = 0; i < data.size(); i++) {

            FloorView view = new FloorView(getActivity(), data, i);
            view.setTitleText(data.get(i).getFloorName());
            final int finalI = i;
            try {
                final String url = URLDecoder.decode(data.get(finalI).getMbPicUrl(), "utf-8");

                view.setPicIntent(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (url.contains("/wdw/page/mb/gs_detail.html")) {
                            Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                            intent.putExtra("itemId", Utils.cutString((data.get(finalI).getMbPicUrl()), "(?i)itemId="));
                            startActivity(intent);
                        } else if (url.contains("/wdw/page/mb/search_result.html?oneId=")) {
                            //跳转到收索结果页面
                            //网页：
//                        Intent intent = new Intent(getActivity(), UrlWebActivity.class);
//                        intent.putExtra("url", data.get(finalI).getMbPicUrl());
//                        intent.putExtra("tag", "more");
                            //原生：
                            Intent intent = new Intent(getActivity(), ShearchResutsActivity.class);
                            intent.putExtra("TAG", "classfy");
                            String TwoCateID = Utils.cutString(data.get(finalI).getMbPicUrl(), "(?i)twoId=");
                            intent.putExtra("TwoCateID", TwoCateID);
                            String OneCateID = Utils.cutString(data.get(finalI).getMbPicUrl(), "(?i)oneId=", "&");
                            intent.putExtra("OneCateID", OneCateID);
                            startActivity(intent);

                        } else if (url.contains("/wdw/page/mb/search_result.html?kw=")) {
                            Intent intent = new Intent(getActivity(), ShearchResutsActivity.class);
                            intent.putExtra("TAG", "搜索");
                            intent.putExtra("keyword", Utils.cutString(url, "kw="));
                            startActivity(intent);
                        } else {
                            Intent inten1 = new Intent(getActivity(), UrlWebActivity.class);
                            inten1.putExtra("url", data.get(finalI).getMbPicUrl());
                            startActivity(inten1);
                        }

                    }
                });
                floorLl.addView(view);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    public void showDataError() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.zx_img_ll, R.id.main_sherch_ll,
            R.id.main_messege_ll,
            R.id.fab_fragment_main_list,})
    public void onClick(View view) {
        switch (view.getId()) {
            //附近门店
            case R.id.zx_img_ll:
//                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
//                } else {
//                    Intent intent = new Intent(getActivity(), ScanActivity.class);
//                    startActivityForResult(intent, 5);
//                }

                AndPermission.with(FragmentMain11.this)
                        .permission(Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        .requestCode(100)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                startActivity(new Intent(getActivity(), NearShopActivity.class));
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(getActivity(), "位置权限获取失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .start();

                break;
            //搜索
            case R.id.main_sherch_ll:
                startActivity(new Intent(getActivity(), SherchPageActivity.class).putExtra("keyword", keword));
                break;
            //消息
            case R.id.main_messege_ll:
                new HtttpRequest(this).isLogin("main_messege_ll");
                break;
            //上滚回退
            case R.id.fab_fragment_main_list:
                scrollview.smoothScrollTo(0, 0);
                break;
        }
    }

    private void initFab() {
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        height = wm.getDefaultDisplay().getHeight();
        scrollview.smoothScrollTo(0, 0);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 得到根Fragment
     *
     * @return
     */
    private Fragment getRootFragment() {
        Fragment fragment = getParentFragment();
        if (fragment != null) {
            while (fragment.getParentFragment() != null) {
                fragment = fragment.getParentFragment();
            }
        }
        return fragment;
    }

    private void loadMassegeNum() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMASSEGENUM, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.equals("0")) {
                    mainMyMassegeImg.setImageResource(R.drawable.ic_message_red);
                }

            }
        });
    }

    public int getStatusHeight() {
        final Rect rect = new Rect();
        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int n = rect.top;
        if (n != 0) {
            return n;
        }
        try {
            final Class<?> forName = Class.forName("com.android.internal.R$dimen");
            n = getResources().getDimensionPixelSize(Integer.parseInt(forName.getField("status_bar_height").get(forName.newInstance()).toString()));
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex2) {
            ex2.printStackTrace();
        } catch (java.lang.InstantiationException ex3) {
            ex3.printStackTrace();
        } catch (NumberFormatException ex4) {
            ex4.printStackTrace();
        } catch (IllegalArgumentException ex5) {
            ex5.printStackTrace();
        } catch (SecurityException ex6) {
            ex6.printStackTrace();
        } catch (NoSuchFieldException ex7) {
            ex7.printStackTrace();
        }
        return n;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onResume() {
        super.onResume();
        madView.resume();

        initData();

//        ImmersiveStatusBar.initStateInFragment(this, llBar4);
//        SystemBarTintManager tintManager = new SystemBarTintManager(getActivity());
//        tintManager.setStatusBarTintEnabled(true);
//       tintManager.setStatusBarTintResource(R.color.greys);//设置系统状态栏颜色
//     //  tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.mybgcolor_02));//设置系统状态栏背景图
        super.onResume();
    }

    private void initData() {
        loadMassegeNum();
        loadwhatcanShow();
    }

    /**
     * **********首页的导航标签
     */

    private void initNavData() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetNavData, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentMain", response);
                final NavData data = JSON.parseObject(response, NavData.class);
                if (data.getObj().size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        LinearLayout l = new LinearLayout(getContext());
                        l.setOrientation(LinearLayout.VERTICAL);
                        l.setGravity(Gravity.CENTER);
                        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                        nav1Ll.addView(l);
                        SimpleDraweeView img = new SimpleDraweeView(getContext());
                        // img.setImageResource(R.drawable.defalt_pic);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(45), dip2px(45));
                        layoutParams.setMargins(0, dip2px(15), 0, dip2px(5));
                        img.setLayoutParams(layoutParams);
                        img.setImageURI(UrlApi.SERVER_IP + data.getObj().get(i).getImgUrl());
                        l.addView(img);
                        TextView tv = new TextView(getContext());
                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        layoutParams1.setMargins(0, dip2px(5), 0, dip2px(5));
                        tv.setLayoutParams(layoutParams1);
                        tv.setText(data.getObj().get(i).getText());
                        tv.setTextSize(12f);
                        tv.setGravity(Gravity.CENTER);
                        l.addView(tv);
                        final int finalI = i;
                        final int finalI1 = i;
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (data.getObj().get(finalI).getText().contains("签到")) {
                                    new HtttpRequest(FragmentMain11.this).isLogin("签到");
                                } else if (data.getObj().get(finalI).getText().contains("资讯")) {
                                    startActivity(new Intent(getActivity(), ZiXunActivity.class));
                                } else if (data.getObj().get(finalI).getText().contains("品牌")) {
                                    startActivity(new Intent(getActivity(), PreferredBrandActivity.class));
                                } else if (data.getObj().get(finalI).getText().contains("门店")) {
                                    AndPermission.with(FragmentMain11.this)
                                            .permission(Manifest.permission.ACCESS_FINE_LOCATION,
                                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                            )
                                            .requestCode(100)
                                            .callback(new PermissionListener() {
                                                @Override
                                                public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                                    startActivity(new Intent(getActivity(), NearShopActivity.class));
                                                }

                                                @Override
                                                public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                                    Toast.makeText(getActivity(), "位置权限获取失败", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .start();
                                } else if (data.getObj().get(finalI).getText().contains("分类")) {
                                    MainPagerActivity.GotoFragment(1);
                                } else {
                                    Intent intent = new Intent(getActivity(), UrlWebActivity.class);
                                    intent.putExtra("url", data.getObj().get(finalI1).getUrl());
                                    intent.putExtra("tital", data.getObj().get(finalI).getText());
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                    if (data.getObj().size() > 4) {
                        for (int i = 4; i < 8; i++) {
                            LinearLayout l = new LinearLayout(getContext());
                            l.setOrientation(LinearLayout.VERTICAL);
                            l.setGravity(Gravity.CENTER);
                            l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
                            nav2Ll.addView(l);
                            SimpleDraweeView img = new SimpleDraweeView(getContext());
                            //   img.setImageResource(R.drawable.defalt_pic);
                            TextView tv = new TextView(getContext());
                            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                            layoutParams1.setMargins(0, dip2px(5), 0, dip2px(5));
                            tv.setLayoutParams(layoutParams1);
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(45), dip2px(45));
                            layoutParams.setMargins(0, dip2px(15), 0, dip2px(5));
                            img.setLayoutParams(layoutParams);
                            if (i < data.getObj().size()) {
                                img.setImageURI(UrlApi.SERVER_IP + data.getObj().get(i).getImgUrl());
                                tv.setText(data.getObj().get(i).getText());
                                tv.setTextSize(12f);
                                tv.setGravity(Gravity.CENTER);
                            }
                            l.addView(img);
                            l.addView(tv);
                            final int finalI = i;
                            img.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (data.getObj().get(finalI).getText().contains("签到")) {
//                                        if (WDWApp.getUserToken() != null) {
//
//                                        } else {
//                                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(getActivity(), LognActivity.class));
//                                        }

                                        new HtttpRequest(FragmentMain11.this).isLogin("签到");

                                    } else if (data.getObj().get(finalI).getUrl().contains("/wdw/page/mb/coupon.html")) {
                                        startActivity(new Intent(getActivity(), ConpoudActivity.class));

                                    } else if (data.getObj().get(finalI).getText().contains("品牌推荐")) {
                                        startActivity(new Intent(getActivity(), PreferredBrandActivity.class));
                                    } else if (data.getObj().get(finalI).getText().contains("附近门店")) {
                                        startActivity(new Intent(getActivity(), NearShopActivity.class));
                                    } else if (data.getObj().get(finalI).getText().contains("分类")) {
                                        MainPagerActivity.GotoFragment(1);
                                    } else {
                                        if (data.getObj().get(finalI).getUrl() != null) {
                                            Intent intent = new Intent(getActivity(), UrlWebActivity.class);
                                            intent.putExtra("url", data.getObj().get(finalI).getUrl());
                                            intent.putExtra("tital", data.getObj().get(finalI).getText());
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });
                        }
                    }

                }
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        madView.pause();

    }

    private String keword;

    private void GetSearchTital() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetSeasheText, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentMain11", response);
                if (!response.contains(MConstant.HTTP404)) {
                    keword = JSONObject.parseObject(response).get("obj").toString();
                    mainSherchTv.setText(JSONObject.parseObject(response).get("obj").toString());
                }
            }
        });
    }

    @Override
    public void isLogin(String what) {
        switch (what) {
            case "签到":
                startActivity(new Intent(getActivity(), QianDaoActivity.class));
                break;
            case "main_messege_ll":
                startActivityForResult(new Intent(getActivity(), MyMessageMianPage.class), 11);
                break;

        }
    }

    @Override
    public void noLofin() {
        if (WDWApp.getUserToken() == null) {
            Toast.makeText(getActivity(), "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(getActivity(), LognActivity.class));
    }


    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mainMiaoshatital.setVisibility(View.GONE);
            miaoshaListLl.setVisibility(View.GONE);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            String formatLongToTimeStr = formatLongToTimeStr(millisUntilFinished);
            String[] split = formatLongToTimeStr.split(":");
            if (split[0].length() == 1) {
                mianHour.setText("0" + split[0]);
            } else {
                mianHour.setText(split[0] + "");
            }
            if (split[1].length() == 1) {
                mianMinut.setText("0" + split[1]);
            } else {

                mianMinut.setText(split[1] + "");
            }

            if (split[2].length() == 1) {
                mainSecond.setText("0" + split[2]);
            } else {
                mainSecond.setText(split[2] + "");
            }


        }

    }

    /**
     * 时间转化
     *
     * @param l
     * @return
     */
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
        String strtime = hour + ":" + minute + ":" + second;
        return strtime;

    }

    /**
     * 某些楼层信息显示不显示
     */
    private void loadwhatcanShow() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetIndexFloorCfg, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MainPagerActivity", "e:" + e);
                mainNewrecmendTatil.setVisibility(View.GONE);
                recommentRecycler.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MainPagerActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    WhatIsShow data = JSON.parseObject(response, WhatIsShow.class);
                    MConstant.GoodsDetailBrandDispaly = data.getObj().getGoodsDetailBrandDispaly();
                    MConstant.newGoodsDisplay = data.getObj().getNewGoodsDisplay();
                    MConstant.seckillDisplay = data.getObj().getSeckillDisplay();
                    //新品推荐数据
                    if (MConstant.newGoodsDisplay == 1) {
                        newCommendePresenter = new NewCommendePresenterImpl(FragmentMain11.this);
                        newCommendePresenter.loadDatas();
                        mainNewrecmendTatil.setVisibility(View.VISIBLE);
                        recommentRecycler.setVisibility(View.VISIBLE);
                    }
                } else {
                    mainNewrecmendTatil.setVisibility(View.GONE);
                    recommentRecycler.setVisibility(View.GONE);
                }
            }
        });
    }


}