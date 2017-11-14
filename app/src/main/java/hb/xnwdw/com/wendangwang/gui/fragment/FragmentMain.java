//package hb.xnwdw.com.wendangwang.gui.fragment;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.Rect;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.DisplayMetrics;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.alibaba.fastjson.JSON;
//import com.andview.refreshview.XRefreshView;
//import com.andview.refreshview.XScrollView;
//import com.chad.library.adapter.base.BaseQuickAdapter;
//import com.facebook.drawee.view.SimpleDraweeView;
//import com.jude.rollviewpager.RollPagerView;
//import com.jude.rollviewpager.hintview.ColorPointHintView;
//import com.yanzhenjie.permission.AndPermission;
//import com.yanzhenjie.permission.PermissionListener;
//import com.zhy.http.okhttp.callback.StringCallback;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import hb.xnwdw.com.wendangwang.R;
//import hb.xnwdw.com.wendangwang.WDWApp;
//import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails;
//import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.MiaoShaActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.MyMassegeActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.NearShopActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.PreferredBrandActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.QianDaoActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.SherchPageActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.UrlWebActivity;
//import hb.xnwdw.com.wendangwang.gui.activity.ZiXunActivity;
//import hb.xnwdw.com.wendangwang.gui.adapter.JDViewAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.MainScrollAdAdpter;
//import hb.xnwdw.com.wendangwang.gui.adapter.RecommendeNewAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.RecommendeYouAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.SekKillAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.TongGaoAdapter;
//import hb.xnwdw.com.wendangwang.gui.view.JDAdverView;
//import hb.xnwdw.com.wendangwang.gui.view.RfreshFooterView;
//import hb.xnwdw.com.wendangwang.gui.view.RfreshHeaderView;
//import hb.xnwdw.com.wendangwang.gui.view.RollingView;
//import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
//import hb.xnwdw.com.wendangwang.netdata.UrlApi;
//import hb.xnwdw.com.wendangwang.netdata.entity.AdverNotice;
//import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;
//import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
//import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
//import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;
//import hb.xnwdw.com.wendangwang.netdata.entity.NavData;
//import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.FloorInfoPresenter;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainRecommendeYouPresenter;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainSlidpagerPresenter;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MianMiaoShaPresenter;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.NewCommendePresenter;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.FloorInfoPresenterImpl;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainMiaoShaPresenterImpl;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainRecommendeYouPresenterImpl;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.MainSlidPagerPresenterImpl;
//import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl.NewCommendePresenterImpl;
//import hb.xnwdw.com.wendangwang.netdata.mvp.view.FloorInfoView;
//import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainMiaoShaView;
//import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainRecommendeYouView;
//import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainslidView;
//import hb.xnwdw.com.wendangwang.netdata.mvp.view.NewCommendeView;
//import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
//import hb.xnwdw.com.wendangwang.utils.LogUtils;
//import hb.xnwdw.com.wendangwang.utils.MConstant;
//import okhttp3.Call;
//
///**
// * Created by Administrator on 2017/2/20.
// */
//
//public class FragmentMain extends FragmentBase implements MainslidView, MainMiaoShaView, NewCommendeView, MainRecommendeYouView, FloorInfoView {
//    @BindView(R.id.zx_img_ll)
//    LinearLayout zxImgLl;
//    @BindView(R.id.main_sherch_ll)
//    LinearLayout mainSherchLl;
//    @BindView(R.id.main_messege_ll)
//    LinearLayout mainMessegeLl;
//    @BindView(R.id.sekkill_recycler)
//    RecyclerView sekkillRecycler;
//    @BindView(R.id.recomment_recycler)
//    RecyclerView recommentRecycler;
//    @BindView(R.id.youcomment_recyclerview)
//    RecyclerView youcommentRecyclerview;
//    @BindView(R.id.scrollview)
//    XScrollView xscrollview;
//    @BindView(R.id.xrefresh_view)
//    XRefreshView xrefreshView;
//    @BindView(R.id.main_titel_ll)
//    LinearLayout mainTitelLl;
//    @BindView(R.id.fab_fragment_main_list)
//    FloatingActionButton fabFragmentMainList;
//    @BindView(R.id.main_my_massege_tv)
//    TextView textView3;
//    @BindView(R.id.main_my_massege_img)
//    ImageView mainMyMassege;
//    @BindView(R.id.floor1_titel)
//    TextView floor1Titel;
////    @BindView(R.id.floor1_img)
////    SimpleDraweeView floor1Img;
////    @BindView(R.id.floor1_recycler)
////    RecyclerView floor1Recycler;
////    @BindView(R.id.floor2_titel)
////    TextView floor2Titel;
////    @BindView(R.id.floor2_recycler)
////    RecyclerView floor2Recycler;
////    @BindView(R.id.floor2_img)
//    SimpleDraweeView floor2Img;
//    @BindView(R.id.mian_hour)
//    TextView mianHour;
//    @BindView(R.id.mian_minut)
//    TextView mianMinut;
//    @BindView(R.id.main_second)
//    TextView mainSecond;
//    @BindView(R.id.main_miaoshatital)
//    LinearLayout mainMiaoshatital;
//    @BindView(R.id.floor3_titel)
//    TextView floor3Titel;
//    @BindView(R.id.floor3_img)
//    SimpleDraweeView floor3Img;
//    @BindView(R.id.floor3_recycler)
//    RecyclerView floor3Recycler;
//    @BindView(R.id.main_titel_fujin_img)
//    ImageView mainTitelFujinImg;
//    @BindView(R.id.main_titel_fujin_tv)
//    TextView mainTitelFujinTv;
//    @BindView(R.id.main_sherch_img)
//    ImageView mainSherchImg;
//    @BindView(R.id.main_sherch_tv)
//    TextView mainSherchTv;
//    @BindView(R.id.main_recmendyou)
//    LinearLayout mainRecmendyou;
//    @BindView(R.id.main_floor3_titel)
//    LinearLayout mainFloor3Titel;
//    @BindView(R.id.hotavtvity_main)
//    LinearLayout hotavtvityMain;
//    @BindView(R.id.mian_floor1_titel)
//    LinearLayout mianFloor1Titel;
//    @BindView(R.id.mian_floor2_titel)
//    LinearLayout mianFloor2Titel;
//    @BindView(R.id.main_newrecmend_tatil)
//    LinearLayout mainNewrecmendTatil;
//    @BindView(R.id.main_scrollad)
//    RollPagerView mainScrollad;
//    @BindView(R.id.nav1_ll)
//    LinearLayout nav1Ll;
//    @BindView(R.id.nav2_ll)
//    LinearLayout nav2Ll;
//    @BindView(R.id.mad_view)
//    JDAdverView madView;
//
//    private MainSlidpagerPresenter mpresenter;
//    private int sheight;
//    private int height;
//    private int swidth;
//
//    //稳当秒杀
//    Handler handler = new Handler();
//    private MianMiaoShaPresenter mianMiaoShaPresenter;
//    private SekKillAdapter sekKillAdapter;
//    /**
//     * 秒杀倒计时
//     */
//    Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            time -= 1000;
//            String formatLongToTimeStr = formatLongToTimeStr(time);
//            String[] split = formatLongToTimeStr.split("：");
//            for (int i = 0; i < split.length; i++) {
//                if (i == 0) {
//                    mianHour.setText(split[0] + "");
//                }
//                if (i == 1) {
//                    mianMinut.setText(split[1] + "");
//                }
//                if (i == 2) {
//                    mainSecond.setText(split[2] + "");
//                }
//
//            }
//            if (time > 0) {
//                handler.postDelayed(this, 1000);
//            }
//        }
//    };
//    //新品推荐
//    private RecommendeNewAdapter recommendeNewAdapter;
//    private NewCommendePresenter newCommendePresenter;
//
//    //为你推荐
//    private MainRecommendeYouPresenter mainRecommendeYouPresenter;
//    private RecommendeYouAdapter recommendeYouAdapter;
//
//
//    //楼层
//    private FloorInfoPresenter floorInfoPresenter;
//    private FloorInfo1Adapter floorInfoAdapter1;
//    private FloorInfo2Adapter floorInfo2Adapter;
//    private FloorInfo3Adapter floorInfo3Adapter;
//    private FloorInfo4Adapter floorInfo4Adapter;
//
//    @Override
//    protected int getContentViewResId() {
//        return R.layout.fragment_main;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mpresenter = new MainSlidPagerPresenterImpl(this);
//        DisplayMetrics dm = getResources().getDisplayMetrics();
//        swidth = dm.widthPixels;
//        sheight = dm.heightPixels;
//        initNavData();
//        initADdata();
//
//        //设置播放时间间隔
//        mainScrollad.setPlayDelay(2500);
//        //设置透明度
//        mainScrollad.setAnimationDurtion(500);
//        //设置适配器
//        //设置指示器（顺序依次）
//        //自定义指示器图片
//        //设置圆点指示器颜色
//        //设置文字指示器
//        //隐藏指示器
//        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
//        mainScrollad.setHintView(new ColorPointHintView(getActivity(), getResources().getColor(R.color.maincolor), Color.WHITE));
//        //mRollViewPager.setHintView(new TextHintView(this));
//        //mRollViewPager.setHintView(null);
//
//        mpresenter.loadDatas();
//        //设置搜索栏
//        //FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        //   layoutParams.setMargins(0, getStatusHeight(), 0, 0);
//        //    mainTitelLl.setLayoutParams(layoutParams);
//        //    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd);
//        mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd1);
//       // mainTitelLl.setAlpha((float) 0.9);
//        //   mainSherchLl.getBackground().setAlpha(1);
//
//        loadMassegeNum();
//        //获取秒杀数据
//        mianMiaoShaPresenter = new MainMiaoShaPresenterImpl(this);
//        mianMiaoShaPresenter.loadDatas();
//        handler.postDelayed(runnable, 1000);
//        //新品推荐数据
//        newCommendePresenter = new NewCommendePresenterImpl(this);
//        newCommendePresenter.loadDatas();
//        //为你推荐数据
//        mainRecommendeYouPresenter = new MainRecommendeYouPresenterImpl(this);
//        mainRecommendeYouPresenter.loadDtas();
//
//        //楼层数据
//        floorInfoPresenter = new FloorInfoPresenterImpl(this);
//        floorInfoPresenter.loadDatas();
//        xrefreshView.setCustomHeaderView(new RfreshHeaderView(getContext()));
//        xrefreshView.setCustomFooterView(new RfreshFooterView(getActivity()));
//        xrefreshView.setAutoLoadMore(true);
//        xrefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        xrefreshView.stopRefresh();
//                        mpresenter.loadDatas();
//                        mianMiaoShaPresenter.loadDatas();
//                        mainRecommendeYouPresenter.loadDtas();
//                        newCommendePresenter.loadDatas();
//                        floorInfoPresenter.loadDatas();
//                    }
//                }, 2000);
//
//            }
//
//            @Override
//            public void onRefresh(boolean isPullDown) {
//
//            }
//
//            @Override
//            public void onLoadMore(boolean isSilence) {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        xrefreshView.stopLoadMore();
//                    }
//                }, 2000);
//            }
//
//            @Override
//            public void onRelease(float direction) {
//
//            }
//
//            @Override
//            public void onHeaderMove(double headerMovePercent, int offsetY) {
//                if (headerMovePercent != 0) {
//                    mainTitelLl.setVisibility(View.INVISIBLE);
//                } else {
//                }
//                mainTitelLl.setVisibility(View.VISIBLE);
//            }
//        });
//        initFab();
//
//
//    }
//
//    private void initADdata() {
//        HtttpRequest.CreatGetRequst(UrlApi.URL_GetNoticeList, null, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                if (!response.contains(MConstant.HTTP404)) {
//                    AdverNotice datas = JSON.parseObject(response, AdverNotice.class);
//                    TongGaoAdapter adapter = new TongGaoAdapter(datas.getObj(), getActivity());
//                    List<String> list=new ArrayList<String>();
//                    for (int i = 0; i <datas.getObj().size() ; i++) {
//                        list.add(datas.getObj().get(i).getTitle());
//
//                    }
//                    JDViewAdapter adapter1 = new JDViewAdapter(datas.getObj(),getActivity());
//                    madView.setAdapter(adapter1);
//                    madView.start();
//                }
//            }
//        });
//    }
//
//    //广告轮播回调
//    @Override
//    public void showPagerSlidData(List<MainPagerSlidResponse.DatasBean> databean) {
//        List<MainPagerSlidResponse.DatasBean> datas = databean;
//        mainScrollad.setAdapter(new MainScrollAdAdpter(datas, getActivity()));
//    }
//
//    @Override
//    public void showDatasError() {
//
//    }
//
//    //秒杀层数据回调
//    @Override
//    public void showMiaoShaDatas(final List<MainPageMiaoShaDate.ItemsBean> itemsBeen) {
//        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        sekkillRecycler.setLayoutManager(linearLayoutManager);
//        final List<MainPageMiaoShaDate.ItemsBean> data = itemsBeen;
//        sekKillAdapter = new SekKillAdapter(R.layout.item_goods, data);
//        //   sekKillAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) sekkillRecycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
//        sekkillRecycler.setAdapter(sekKillAdapter);
//        sekKillAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(), MiaoShaActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private long time;
//
//    @Override
//    public void shouMiaoshaTim(MainPageMiaoShaDate date) {
//        if (date != null) {
//            mainMiaoshatital.setVisibility(View.VISIBLE);
//        }
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = null;
//        try {
//            d1 = df.parse(date.getEndTm());
//            long da1 = d1.getTime();
//            long da2 = System.currentTimeMillis();
//            long diff = da1 - da2;
//            long hours = diff / (1000 * 60 * 60);
//            long minutes = (diff - hours * (1000 * 60 * 60)) / (1000 * 60);
//            long second = (diff - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000 * 60);
//
////            mianHour.setText(hours+"");
////            mianMinut.setText(minutes+"");
////            mainSecond.setText(second+"");
//            time = diff;
//
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    //新品推荐数据回调
//    @Override
//    public void showNewCommende(final List<NewRecommendeData.DatasBean> itemsBeen) {
//        if (itemsBeen.size() == 0) {
//            mainNewrecmendTatil.setVisibility(View.GONE);
//            recommentRecycler.setVisibility(View.GONE);
//        } else {
//            MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
//            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//            recommentRecycler.setLayoutManager(linearLayoutManager);
//            List<NewRecommendeData.DatasBean> data = itemsBeen;
//            recommendeNewAdapter = new RecommendeNewAdapter(R.layout.item_goods, data);
//            recommentRecycler.setAdapter(recommendeNewAdapter);
//            //     recommendeNewAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) sekkillRecycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
//            recommendeNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent intent = new Intent(getActivity(),GoodsDetails1.class);
//                    intent.putExtra("itemId", itemsBeen.get(position).getItemID());
//                    startActivity(intent);
//                }
//            });
//        }
//    }
//
//    //为你推荐数据回调
//    @Override
//    public void showRecommendeYouShaDatas(List<MainRecommendeYouData.DatasBean> datasBeen) {
//        if (datasBeen == null) {
//            mainRecmendyou.setVisibility(View.GONE);
//            youcommentRecyclerview.setVisibility(View.GONE);
//        } else {
//            MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
//            youcommentRecyclerview.setLayoutManager(linearLayoutManager);
//            youcommentRecyclerview.setNestedScrollingEnabled(false);
//            final List<MainRecommendeYouData.DatasBean> data = datasBeen;
//            recommendeYouAdapter = new RecommendeYouAdapter(R.layout.item_youcommend, data);
//            recommendeYouAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent intent = new Intent(getActivity(),GoodsDetails1.class);
//                    intent.putExtra("itemId", data.get(position).getItemID() + "");
//                    startActivity(intent);
//                }
//            });
//            youcommentRecyclerview.setAdapter(recommendeYouAdapter);
//        }
//    }
//
//    //楼层数据回调
//    @Override
//    public void showFloorInfoData(List<FloorInfoData.DatasBean> dataBeen) {
//        final List<FloorInfoData.DatasBean> data = dataBeen;
//        if (data == null) {
//            mianFloor1Titel.setVisibility(View.GONE);
//            floor1Img.setVisibility(View.GONE);
//            mianFloor2Titel.setVisibility(View.GONE);
//            floor2Img.setVisibility(View.GONE);
//            mainFloor3Titel.setVisibility(View.GONE);
//            floor3Img.setVisibility(View.GONE);
//        } else {
//            switch (data.size()) {
//                case 1:
//                    mianFloor2Titel.setVisibility(View.GONE);
//                    floor2Img.setVisibility(View.GONE);
//                    mainFloor3Titel.setVisibility(View.GONE);
//                    floor3Img.setVisibility(View.GONE);
//                    initFloor1(data);
//                    break;
//                case 2:
//                    mainFloor3Titel.setVisibility(View.GONE);
//                    floor3Img.setVisibility(View.GONE);
//                    initFloor1(data);
//                    initFloor2(data);
//                    break;
//                default:
//                    initFloor1(data);
//                    initFloor2(data);
//                    initFloor3(data);
//                    break;
//            }
//
//
//        }
//
//
//    }
//
//
//    @Override
//    public void showDataError() {
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//
//    @OnClick({R.id.zx_img_ll, R.id.main_my_massege_img, R.id.main_sherch_ll,
//            R.id.main_messege_ll,
//            R.id.fab_fragment_main_list,})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            //附近门店
//            case R.id.zx_img_ll:
////                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
////                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
////                } else {
////                    Intent intent = new Intent(getActivity(), ScanActivity.class);
////                    startActivityForResult(intent, 5);
////                }
//
//                AndPermission.with(FragmentMain.this)
//                        .permission(Manifest.permission.ACCESS_FINE_LOCATION,
//                                Manifest.permission.ACCESS_COARSE_LOCATION
//                        )
//                        .requestCode(100)
//                        .callback(new PermissionListener() {
//                            @Override
//                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
//                                startActivity(new Intent(getActivity(), NearShopActivity.class));
//                            }
//
//                            @Override
//                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
//                                Toast.makeText(getActivity(), "位置权限获取失败", Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .start();
//
//                break;
//            //搜索
//            case R.id.main_sherch_ll:
//                startActivity(new Intent(getActivity(), SherchPageActivity.class));
//                break;
//            //消息
//            case R.id.main_messege_ll:
//                if (WDWApp.getUserToken() == null) {
//                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getActivity(), LognActivity.class));
//                }
//                startActivityForResult(new Intent(getActivity(), MyMassegeActivity.class), 11);
//                break;
//            //上滚回退
//            case R.id.fab_fragment_main_list:
//                xscrollview.smoothScrollTo(0, 0);
//                break;
//            //我的消息
//            case R.id.main_my_massege_img:
//                if (WDWApp.getUserToken() == null) {
//                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getActivity(), LognActivity.class));
//                }
//                startActivity(new Intent(getActivity(), MyMassegeActivity.class));
//                break;
//        }
//    }
//
//    private void initFab() {
//        WindowManager wm = (WindowManager) getActivity()
//                .getSystemService(Context.WINDOW_SERVICE);
//        height = wm.getDefaultDisplay().getHeight();
//        xscrollview.smoothScrollTo(0, 0);
//        xscrollview.setOnScrollListener(new XScrollView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {
//
//            }
//
//            @Override
//            public void onScroll(int l, int t, int oldl, int oldt) {
//                //设置搜索栏的状态
//                if (t <= dip2px(100) && t > 0) {
//                    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd);
//                    mainTitelLl.setAlpha((float) (t + dip2px(50)) / dip2px(100));
//
//                }
//                if (t > dip2px(20)) {
//                    mainTitelFujinImg.setImageResource(R.drawable.fujin1);
//                    mainTitelFujinTv.setTextColor(getResources().getColor(R.color.black));
//
//                    mainMyMassege.setImageResource(R.drawable.ic_message_red1);
//                    textView3.setTextColor(getResources().getColor(R.color.black));
//
//                    mainSherchLl.setBackgroundResource(R.drawable.sherchbg1);
//                    mainSherchTv.setTextColor(getResources().getColor(R.color.white));
//                    mainSherchImg.setImageResource(R.drawable.ic_search_white);
//
//                }
//                if (t == 0) {
//                    mainTitelLl.setBackgroundResource(R.drawable.main_tite_bacrd1);
//                    mainTitelLl.setAlpha((float) 0.9);
//
//                    mainTitelFujinImg.setImageResource(R.drawable.fujin);
//                    mainTitelFujinTv.setTextColor(getResources().getColor(R.color.white));
//
//                    mainMyMassege.setImageResource(R.drawable.ic_message);
//                    textView3.setTextColor(getResources().getColor(R.color.white));
//
//                    mainSherchLl.setBackgroundResource(R.drawable.sherch);
//                    mainSherchTv.setTextColor(getResources().getColor(R.color.gry));
//                    mainSherchImg.setImageResource(R.drawable.ic_search_grey);
//
//
//                    //     mainTitelLl.setAlpha((float) 0);
//                }
//                if (t >= height / 3) {
//                    fabFragmentMainList.setVisibility(View.VISIBLE);
//                } else {
//                    fabFragmentMainList.setVisibility(View.GONE);
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//
//    }
//
//    public String formatLongToTimeStr(Long l) {
//        int hour = 0;
//        int minute = 0;
//        int second = 0;
//        second = l.intValue() / 1000;
//        if (second > 60) {
//            minute = second / 60;         //取整
//            second = second % 60;         //取余
//        }
//
//        if (minute > 60) {
//            hour = minute / 60;
//            minute = minute % 60;
//        }
//        String strtime = hour + "：" + minute + "：" + second;
//        return strtime;
//
//    }
//
//
//    /**
//     * 得到根Fragment
//     *
//     * @return
//     */
//    private Fragment getRootFragment() {
//        Fragment fragment = getParentFragment();
//        if (fragment != null) {
//            while (fragment.getParentFragment() != null) {
//                fragment = fragment.getParentFragment();
//            }
//        }
//        return fragment;
//    }
//
//    private void loadMassegeNum() {
//        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMASSEGENUM, null, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                if (!response.equals("0")) {
//                    mainMyMassege.setImageResource(R.drawable.ic_message_red);
//                }
//
//            }
//        });
//    }
//
//    public int getStatusHeight() {
//        final Rect rect = new Rect();
//        getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
//        int n = rect.top;
//        if (n != 0) {
//            return n;
//        }
//        try {
//            final Class<?> forName = Class.forName("com.android.internal.R$dimen");
//            n = getResources().getDimensionPixelSize(Integer.parseInt(forName.getField("status_bar_height").get(forName.newInstance()).toString()));
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex2) {
//            ex2.printStackTrace();
//        } catch (java.lang.InstantiationException ex3) {
//            ex3.printStackTrace();
//        } catch (NumberFormatException ex4) {
//            ex4.printStackTrace();
//        } catch (IllegalArgumentException ex5) {
//            ex5.printStackTrace();
//        } catch (SecurityException ex6) {
//            ex6.printStackTrace();
//        } catch (NoSuchFieldException ex7) {
//            ex7.printStackTrace();
//        }
//        return n;
//    }
//
//    private void initFloor1(final List<FloorInfoData.DatasBean> data) {
//        //设置适配器
//        //楼层1
//        MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        floor1Recycler.setLayoutManager(linearLayoutManager);
//        floor1Titel.setText(data.get(0).getFloorName());
//        floor1Img.setImageURI(UrlApi.SERVER_IP + data.get(0).getFloorPic());
//        floorInfoAdapter1 = new FloorInfo1Adapter(R.layout.item_goods, data.get(0).getItems());
//        floorInfoAdapter1.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) floor1Recycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
//        floorInfoAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(),GoodsDetails1.class);
//                intent.putExtra("itemId", data.get(0).getItems().get(position).getItemID() + "");
//                startActivity(intent);
//            }
//        });
//        floor1Recycler.setAdapter(floorInfoAdapter1);
//    }
//
//    private void initFloor2(final List<FloorInfoData.DatasBean> data) {
//        //楼层2
//        MyLinearLayoutManager linearLayoutManager1 = new MyLinearLayoutManager(getActivity());
//        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
//        floor2Recycler.setLayoutManager(linearLayoutManager1);
//        floor2Img.setImageURI(UrlApi.SERVER_IP + data.get(1).getFloorPic());
//        floor2Titel.setText(data.get(1).getFloorName());
//        floorInfo2Adapter = new FloorInfo2Adapter(R.layout.item_goods, data.get(1).getItems());
//        floor2Recycler.setAdapter(floorInfo2Adapter);
//        floorInfo2Adapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) floor2Recycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
//        floorInfo2Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(),GoodsDetails1.class);
//                intent.putExtra("itemId", data.get(1).getItems().get(position).getItemID() + "");
//                startActivity(intent);
//            }
//        });
//    }
//
//    private void initFloor3(final List<FloorInfoData.DatasBean> data) {
//        //楼层3
//        MyLinearLayoutManager linearLayoutManager2 = new MyLinearLayoutManager(getActivity());
//        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        floor3Recycler.setLayoutManager(linearLayoutManager2);
//        floor3Img.setImageURI(UrlApi.SERVER_IP + data.get(2).getFloorPic());
//        floor3Titel.setText(data.get(2).getFloorName());
//        floorInfo3Adapter = new FloorInfo3Adapter(R.layout.item_goods, data.get(2).getItems());
//        floor3Recycler.setAdapter(floorInfo3Adapter);
//        floorInfo3Adapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_more, (ViewGroup) floor3Recycler.getParent().getParent(), false), -1, LinearLayout.HORIZONTAL);
//        floorInfo3Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(getActivity(),GoodsDetails1.class);
//                intent.putExtra("itemId", data.get(2).getItems().get(position).getItemID() + "");
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        initData();
//
//    }
//
//    private void initData() {
//        loadMassegeNum();
//    }
//
//    private void initNavData() {
//        HtttpRequest.CreatGetRequst(UrlApi.URL_GetNavData, null, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                LogUtils.d("FragmentMain", response);
//                final NavData data = JSON.parseObject(response, NavData.class);
//                if (data.getObj().size() >= 4) {
//                    for (int i = 0; i < 4; i++) {
//                        LinearLayout l = new LinearLayout(getContext());
//                        l.setOrientation(LinearLayout.VERTICAL);
//                        l.setGravity(Gravity.CENTER);
//                        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//                        nav1Ll.addView(l);
//                        SimpleDraweeView img = new SimpleDraweeView(getContext());
//                        // img.setImageResource(R.drawable.defalt_pic);
//                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(45), dip2px(45));
//                        layoutParams.setMargins(0, dip2px(15), 0, dip2px(5));
//                        img.setLayoutParams(layoutParams);
//                        img.setImageURI(UrlApi.SERVER_IP + data.getObj().get(i).getImgUrl());
//                        l.addView(img);
//                        TextView tv = new TextView(getContext());
//                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        layoutParams1.setMargins(0, dip2px(5), 0, dip2px(5));
//                        tv.setLayoutParams(layoutParams1);
//                        tv.setText(data.getObj().get(i).getText());
//                        tv.setTextSize(12f);
//                        tv.setGravity(Gravity.CENTER);
//                        l.addView(tv);
//                        final int finalI = i;
//                        final int finalI1 = i;
//                        img.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (data.getObj().get(finalI).getText().contains("签到")) {
//                                    startActivity(new Intent(getActivity(), QianDaoActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("资讯")) {
//                                    startActivity(new Intent(getActivity(), ZiXunActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("品牌推荐")) {
//                                    startActivity(new Intent(getActivity(), PreferredBrandActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("附近门店")) {
//                                    startActivity(new Intent(getActivity(), NearShopActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("分类")) {
//                                    MainPagerActivity.GotoFragment(1);
//                                } else {
//                                    Intent intent = new Intent(getActivity(), UrlWebActivity.class);
//                                    intent.putExtra("url", data.getObj().get(finalI1).getImgData());
//                                    intent.putExtra("tital", "");
//                                    startActivity(intent);
//                                }
//                            }
//                        });
//
//                    }
//                    for (int i = 4; i < 8; i++) {
//                        LinearLayout l = new LinearLayout(getContext());
//                        l.setOrientation(LinearLayout.VERTICAL);
//                        l.setGravity(Gravity.CENTER);
//                        l.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
//                        nav2Ll.addView(l);
//                        SimpleDraweeView img = new SimpleDraweeView(getContext());
//                        //   img.setImageResource(R.drawable.defalt_pic);
//                        TextView tv = new TextView(getContext());
//                        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        layoutParams1.setMargins(0, dip2px(5), 0, dip2px(5));
//                        tv.setLayoutParams(layoutParams1);
//                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(dip2px(45), dip2px(45));
//                        layoutParams.setMargins(0, dip2px(15), 0, dip2px(5));
//                        img.setLayoutParams(layoutParams);
//                        if (i < data.getObj().size()) {
//                            img.setImageURI(UrlApi.SERVER_IP + data.getObj().get(i).getImgUrl());
//                            tv.setText(data.getObj().get(i).getText());
//                            tv.setTextSize(12f);
//                            tv.setGravity(Gravity.CENTER);
//                        }
//                        l.addView(img);
//                        l.addView(tv);
//                        final int finalI = i;
//                        img.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (data.getObj().get(finalI).getText().contains("签到")) {
//                                    if (WDWApp.getUserToken() != null) {
//                                        startActivity(new Intent(getActivity(), QianDaoActivity.class));
//                                    } else {
//                                        Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                                        startActivity(new Intent(getActivity(), LognActivity.class));
//                                    }
//
//                                } else if (data.getObj().get(finalI).getText().contains("资讯")) {
//                                    startActivity(new Intent(getActivity(), ZiXunActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("品牌推荐")) {
//                                    startActivity(new Intent(getActivity(), PreferredBrandActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("附近门店")) {
//                                    startActivity(new Intent(getActivity(), NearShopActivity.class));
//                                } else if (data.getObj().get(finalI).getText().contains("分类")) {
//                                    MainPagerActivity.GotoFragment(1);
//                                } else {
//
//                                    if (data.getObj().get(finalI).getImgData() != null) {
//                                        Intent intent = new Intent(getActivity(), UrlWebActivity.class);
//                                        intent.putExtra("url", data.getObj().get(finalI).getImgData());
//                                        intent.putExtra("tital", "");
//                                        startActivity(intent);
//                                    }
//                                }
//                            }
//                        });
//                    }
//                }
//            }
//        });
//    }
//}