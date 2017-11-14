package hb.xnwdw.com.wendangwang.gui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.andview.refreshview.XScrollView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.tencent.tauth.Tencent;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import hb.xnwdw.com.wendangwang.Zoomable.common.ZoomableActivity;
import hb.xnwdw.com.wendangwang.gui.activity.BrandDetails;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
import hb.xnwdw.com.wendangwang.gui.activity.OrderBalanceActivity;
import hb.xnwdw.com.wendangwang.gui.activity.ShopingCartActivity;
import hb.xnwdw.com.wendangwang.gui.activity.ShoppingCart1;
import hb.xnwdw.com.wendangwang.gui.adapter.GoodDetailHotSaleAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.GoodsDetailViewPagerAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.gui.widget.PagerDotIndicator;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandMaxData;
import hb.xnwdw.com.wendangwang.netdata.entity.CanBuyCountData;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodDetailCarDada;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodEvaluatData;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsDetailData;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsEvaluateListData;
import hb.xnwdw.com.wendangwang.netdata.entity.HotSaleData;
import hb.xnwdw.com.wendangwang.netdata.entity.MemoData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderGoodsInfo;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * 商品详情页
 * Created by Administrator on 2017/3/21.
 */

public class FragmentGoods extends FragmentBase implements HtttpRequest.LoginStatu {
    @BindView(R.id.goods_indicator_dot_ll)
    LinearLayout goodsIndicatorDotLl;
    @BindView(R.id.choose_goodsnum_ll)
    LinearLayout chooseGoodsnumLl;
    @BindView(R.id.goodsdetail_miaoshu)
    TextView goodsdetailMiaoshu;
    @BindView(R.id.goodsdetail_price)
    TextView goodsdetailPrice;
    @BindView(R.id.goodsdetail_oldprice)
    TextView goodsdetailOldprice;
    @BindView(R.id.goodsdetail_yishou)
    TextView goodsdetailYishou;
    @BindView(R.id.goodsdetail_shangjiabiaoqian1)
    TextView goodsdetailShangjiabiaoqian1;
    @BindView(R.id.goodsdetail_choosennum)
    TextView goodsdetailChoosennum;
    @BindView(R.id.goodsdetai_activity)
    TextView goodsdetaiActivity;
    @BindView(R.id.goodsdetai_pingjianum)
    TextView goodsdetaiPingjianum;
    @BindView(R.id.goodsdetai_pinpai_img)
    SimpleDraweeView goodsdetaiPinpaiImg;
    @BindView(R.id.goodsdetai_pinpai_name)
    TextView goodsdetaiPinpaiName;
    @BindView(R.id.goodsdetai_pinpai_zaishou)
    TextView goodsdetaiPinpaiZaishou;
    @BindView(R.id.goodsdetai_pinpai_xaioliang)
    TextView goodsdetaiPinpaiXaioliang;
    @BindView(R.id.goodsdetai_gotopingpai)
    TextView goodsdetaiGotopingpai;
    @BindView(R.id.goodsdetai_remaituijianlist)
    RecyclerView goodsdetaiRemaituijianlist;
    @BindView(R.id.goodsdetail_viewpager)
    ViewPager goodsdetailViewpager;
    @BindView(R.id.grament_goods)
    FrameLayout gramentGoods;
    @BindView(R.id.fgoodevaluat_userhead)
    SimpleDraweeView fgoodevaluatUserhead;
    @BindView(R.id.fgoodevaluat_userphone)
    TextView fgoodevaluatUserphone;
    @BindView(R.id.fgoodevaluat_content)
    TextView fgoodevaluatContent;
    @BindView(R.id.fgoodevaluat_ratingbar)
    RatingBar fgoodevaluatRatingbar;
    @BindView(R.id.fgoodevaluat_time)
    TextView fgoodevaluatTime;
    @BindView(R.id.fgoodevaluat_line)
    View fgoodevaluatLine;
    @BindView(R.id.goodsdetai_moreevaluate)
    TextView goodsdetaiMoreevaluate;
    @BindView(R.id.no_evaluate)
    TextView noEvaluate;
    @BindView(R.id.gooddetail_shoucang)
    LinearLayout gooddetailShoucang;
    @BindView(R.id.gooddetai_kefu)
    LinearLayout gooddetaiKefu;
    @BindView(R.id.gooddetai_goto_shopingcar)
    LinearLayout gooddetaiGotoShopingcar;
    @BindView(R.id.gooddetai_addcar)
    TextView gooddetaiAddcar;
    @BindView(R.id.gooddetai_buynow)
    TextView gooddetaiBuynow;
    @BindView(R.id.goodsdetai_activity_type)
    TextView goodsdetaiActivityType;
    @BindView(R.id.cosunit)
    LinearLayout cosunit;
    @BindView(R.id.gooddetail_picdetial)
    WebView gooddetailPicdetial;
    @BindView(R.id.fgoodevaluat_img_ll)
    LinearLayout fgoodevaluatImgLl;
    @BindView(R.id.eva_content_relay)
    RelativeLayout evaContentRelay;
    @BindView(R.id.gooddetias_hour)
    TextView gooddetailsHour;
    @BindView(R.id.gooddetail_minut)
    TextView gooddetailMinut;
    @BindView(R.id.gooddetail_second)
    TextView gooddetailSecond;
    @BindView(R.id.gooddetail_miaoshatital)
    LinearLayout gooddetailMiaoshatital;
    @BindView(R.id.gooddetail_shoucang_img)
    ImageView gooddetailShoucangImg;
    @BindView(R.id.gooddetai_goto_shopingcar_img)
    ImageView gooddetaiGotoShopingcarImg;
    @BindView(R.id.gooddetail_car_num)
    TextView gooddetailCarNum;
    @BindView(R.id.gooddetail_scrolle)
    XScrollView gooddetailScrolle;
    @BindView(R.id.goodsdetai_activity_type2)
    TextView goodsdetaiActivityType2;
    @BindView(R.id.goodsdetai_activity2)
    TextView goodsdetaiActivity2;
    @BindView(R.id.activity2)
    LinearLayout activity2;
    @BindView(R.id.good_detail_brand)
    LinearLayout goodDetailBrand;
    @BindView(R.id.good_detail_gotobrand)
    LinearLayout goodDetailGotobrand;
    @BindView(R.id.gooddetail_notread_num)
    TextView gooddetailNotreadNum;
    private GoodsDetailData data;
    private View view1, view2, view3, view4, view5;
    private List<View> viewList;//view数组
    private PagerDotIndicator pagerDotIndicator;
    private GoodsDetailViewPagerAdapter mviewpAdapter;
    private String itemId;
    private int goodNum;
    private int picNum = 0;
    private long time;
    private MyCount mc;
    private boolean isCollect;
    private MemoData md;
    private String ItemSize, UnitName;
    private int canBuyCout = Integer.MAX_VALUE;
    private int mostCount =Integer.MAX_VALUE;
    private Tencent mTencent;// 新建Tencent实例用于调用分享方法
    private ArrayList<String> mPaths;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_goods;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        data = (GoodsDetailData) bundle.getSerializable("Datas");
        itemId = bundle.getString("itemId");
        EventBus.getDefault().register(this);
        //网易七鱼的未读消息数量
        addUnreadCountChangeListener(true);

        int uniconNum = Unicorn.getUnreadCount();
        if (uniconNum > 0) {
            gooddetailNotreadNum.setVisibility(View.VISIBLE);
            gooddetailNotreadNum.setText(uniconNum + "");
        } else {
            gooddetailNotreadNum.setVisibility(View.GONE);
        }
        LogUtils.d("FragmentGoods", itemId);
        LogUtils.d("FragmentGoods", "data.getDataStatus():" + data.getDataStatus());
        if (data.getObj().getItemInfo().getMemo() != null && !data.getObj().getItemInfo().getMemo().equals("")) {
            String Memo = "{data:" + data.getObj().getItemInfo().getMemo() + "}";
            md = JSON.parseObject(Memo, MemoData.class);
            for (int i = 0; i < md.getData().size(); i++) {
                if ((md.getData().get(i).getID() + "").equals(itemId)) {
                    ItemSize = md.getData().get(i).getItemSize();
                    UnitName = md.getData().get(i).getUnitName();
                }
            }
        }
        goodsdetailChoosennum.setText(data.getObj().getItemInfo().getItemSize()+" "+UnitName);
        initView();

        //viewpager图片
        LayoutInflater inflater = getActivity().getLayoutInflater();
        getViews(inflater);
        mviewpAdapter = new GoodsDetailViewPagerAdapter(viewList);
        mviewpAdapter.setCallBack(new GoodsDetailViewPagerAdapter.CallBack() {
            @Override
            public void callBack(int postion) {
                ZoomableActivity.goToPage(getActivity(), mPaths, postion);
            }
        });
        goodsdetailViewpager.setAdapter(mviewpAdapter);
//        goodsdetailViewpager.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ZoomableActivity.goToPage(getActivity(),mPaths,0);
//            }
//        });
        //指示器
        pagerDotIndicator = new PagerDotIndicator(getActivity(), goodsIndicatorDotLl, goodsdetailViewpager);
        pagerDotIndicator.setDotNums(picNum);
        loadHotSaleData();
        loadMaxBrand(itemId);
        loadEvaluateData(itemId);
        loadEvaluateListData(itemId);
        if (WDWApp.getUserToken() != null) {
            loadIsCollect(itemId);

        }
        /***是否显示品牌***/
        if (MConstant.GoodsDetailBrandDispaly == 0) {
            goodDetailBrand.setVisibility(View.GONE);
            goodDetailGotobrand.setVisibility(View.GONE);
        }

        //图文详情
        //   gooddetailPicdetial
        //  LogUtils.d("FragmentGoods", data.getObj().getItemInfo().getItemPCDetail());

        WebSettings webSettings = gooddetailPicdetial.getSettings();
        //支持javascript
        webSettings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webSettings.setSupportZoom(true);
        // 设置出现缩放工具
//        webSettings.setBuiltInZoomControls(true);
        //扩大比例的缩放
        webSettings.setUseWideViewPort(true);
        //自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

        String picDetail;
        String picD;

        if (data.getObj().getItemInfo().getItemMobileDetail() == null) {
            picDetail = data.getObj().getItemInfo().getItemPCDetail();

        } else {
            picDetail = data.getObj().getItemInfo().getItemMobileDetail();

        }
        if (picDetail != null) {
            if (picDetail.contains("http")) {
                picD = "<style>img{width:100%;}</style>" + picDetail;
            } else {
                picD = "<style>img{width:100%;}</style>" + picDetail.replace("src=\"", "src=\"" + UrlApi.SERVER_IP);
            }
            LogUtils.d("FragmentGoods", picD);
            gooddetailPicdetial.loadDataWithBaseURL("about:blank", picD, "text/html", "utf-8", null);
        }
        gooddetailScrolle.setOnScrollListener(new XScrollView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(ScrollView view, int scrollState, boolean arriveBottom) {

            }

            @Override
            public void onScroll(int l, int t, int oldl, int oldt) {
                if (l >= dip2px(915)) {
                    EventBus.getDefault().post(2);
                } else {

                }
            }
        });


    }


//        if(data.getObj().getItemInfo().getItemMobileDetail()!=null){
//            gooddetailPicdetial.loadData(fmtString(data.getObj().getItemInfo().getItemPCDetail()), "text/html", "utf-8");
//        }else {
//            gooddetailPicdetial.loadData(fmtString(data.getObj().getItemInfo().getItemMobileDetail()), "text/html", "utf-8");
//       }


    private String fmtString(String str) {
        String notice = "";
        try {
            notice = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException ex) {

        }
        return notice;
    }

    private void initView() {
//
//        if (WDWApp.getUserToken() == null) {
//            gooddetailCarNum.setVisibility(View.GONE);
//        } else {
//            gooddetailCarNum.setVisibility(View.VISIBLE);
//        }

        ///库存。。
        if (data.getObj().getItemInfo().getItemStatus() != 2 && data.getObj().getItemInfo().getItemStatus() != 1) {
            gooddetaiBuynow.setText("已售罄");
            gooddetaiBuynow.setBackgroundColor(getResources().getColor(R.color.gry));
            gooddetaiBuynow.setClickable(false);
            gooddetaiBuynow.setTextColor(getResources().getColor(R.color.black));
            gooddetaiAddcar.setBackgroundColor(getResources().getColor(R.color.greys));
            gooddetaiAddcar.setTextColor(getResources().getColor(R.color.black));
            gooddetaiAddcar.setClickable(false);
        }
        if (data.getObj().getItemInfo().getItemStatus() == 1) {
            gooddetaiBuynow.setText("已下架");
            gooddetaiBuynow.setBackgroundColor(getResources().getColor(R.color.gry));
            gooddetaiBuynow.setClickable(false);
            gooddetaiBuynow.setTextColor(getResources().getColor(R.color.black));
            gooddetaiAddcar.setBackgroundColor(getResources().getColor(R.color.greys));
            gooddetaiAddcar.setTextColor(getResources().getColor(R.color.black));
            gooddetaiAddcar.setClickable(false);
        }

        goodsdetailMiaoshu.setText(data.getObj().getItemInfo().getItemName());
        if (data.getObj().getActInfo().getDiscountUnit() == data.getObj().getActInfo().getCostUnit()) {
            goodsdetailPrice.setText(data.getObj().getActInfo().getDiscountUnit() + "");
        } else {
            goodsdetailPrice.setText(data.getObj().getActInfo().getDiscountUnit() + "");
            cosunit.setVisibility(View.VISIBLE);
            goodsdetailOldprice.setText(data.getObj().getActInfo().getCostUnit() + "");
        }
        if (data.getObj().getItemInfo().getItemName_V9() != null && !data.getObj().getItemInfo().getItemName_V9().equals("")) {
            goodsdetailShangjiabiaoqian1.setText(data.getObj().getItemInfo().getItemName_V9());
        } else {
            goodsdetailShangjiabiaoqian1.setText("稳当自营    正品保障");
        }


        goodsdetailYishou.setText(data.getObj().getItemInfo().getSalesSum() + data.getObj().getItemInfo().getVirtualSaleSum() + "");
        /*************************活动的判断与描述************************************/

        GoodsDetailData.ObjBean.ActInfoBean act = data.getObj().getActInfo();
        if (data.getObj().getActInfo().getActivityStatus() != 2) {
            goodsdetaiActivityType.setVisibility(View.GONE);
            goodsdetaiActivity.setText("无");
        } else {
            goodsdetaiActivityType.setVisibility(View.VISIBLE);
            if (data.getObj().getActInfo().getLimitNewMember() > 0) {
                goodsdetaiActivity.setText("新用户专享");
                goodsdetaiActivityType.setText("新用户");
            }
            if (data.getObj().getActInfo().getActivityContent().equals("满减")) {
                goodsdetaiActivityType.setText("满减");
                goodsdetaiActivity.setText("活动期间满" + act.getLeastAmount() + "元立减" + act.getReduceMoney() + "元");
            }
            if (act.getActivityContent().equals("满赠")) {
                goodsdetaiActivityType.setText("满赠");
                if (data.getObj().getActInfo().getPagePresentItemList() != null && data.getObj().getActInfo().getPagePresentItemList().size() > 0) {
                    goodsdetaiActivity.setText("活动期间满" + act.getLeastAmount() + "赠送商品" + data.getObj().getActInfo().getPagePresentItemList().get(0).getItemName());
                    goodsdetaiActivity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                            intent.putExtra("itemId", data.getObj().getActInfo().getPagePresentItemList().get(0).getItemID());
                            startActivity(intent);
                        }
                    });
                }
            }
            if (act.getActivityContent().equals("买赠")) {
                goodsdetaiActivityType.setText("买赠");
                if (data.getObj().getActInfo().getPagePresentItemList() != null && data.getObj().getActInfo().getPagePresentItemList().size() > 0) {
                    goodsdetaiActivity.setText("活动期间购买商品赠送" + data.getObj().getActInfo().getPagePresentItemList().get(0).getItemName());
                    goodsdetaiActivity.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                            intent.putExtra("itemId", data.getObj().getActInfo().getPagePresentItemList().get(0).getItemID());
                            startActivity(intent);
                        }
                    });
                }
            }
            if (act.getActivityContent().equals("秒杀")) {
                goodsdetaiActivityType.setText("秒杀");
                goodsdetaiActivity.setText("离结束还有");
                gooddetailMiaoshatital.setVisibility(View.VISIBLE);
                initskillTime();
            }
            if (act.getActivityContent().equals("特价")) {
                goodsdetaiActivityType.setText("特价");
                goodsdetaiActivity.setText("活动期间商品" + act.getActivityContent() + "优惠");
            }
            if ((act.getActivityContent().contains("折"))) {
                goodsdetaiActivityType.setText("打折");
                goodsdetaiActivity.setText("活动期间商品" + act.getActivityContent() + "优惠");
            }
            if (data.getObj().getItemInfo().getMostCount() > 0) {
                activity2.setVisibility(View.VISIBLE);
                goodsdetaiActivityType2.setText("限购");
                goodsdetaiActivity2.setText("活动期间可享受活动价商品" + data.getObj().getItemInfo().getMostCount() + "件");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    /**
     * 选择规格
     *
     * @param
     */
    private void showPopupWindow() {
        goodNum = 1;
//        View contentView = LayoutInflater.from(getActivity()).inflate(
//                R.layout.popupwindow_choosenum, null);
//        mPopuwidow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
//        mPopuwidow.setContentView(contentView);
        /***********下面这段代码可以重用******************/
        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_choosenum, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;

        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        /***********上面这段代码可以重用******************/
        ImageView img_exit = (ImageView) contentView.findViewById(R.id.popuwid_exit);
        ImageView img_jia = (ImageView) contentView.findViewById(R.id.good_detail_jian);
//        LinearLayout clip_ll = (LinearLayout) contentView.findViewById(R.id.clip_ll);
//
//        clip_ll.setBackgroundColor(getResources().getColor(R.color.white));
//        clip_ll.setAlpha((float) 1);
        final ImageView img_add = (ImageView) contentView.findViewById(R.id.good_detail_add);
        final TextView goods_pric = (TextView) contentView.findViewById(R.id.popuwid_price);
        final TextView choose_num = (TextView) contentView.findViewById(R.id.popuwid_num_tv);
        final TextView goods_num = (TextView) contentView.findViewById(R.id.good_detail_num);
        final TextView tips = (TextView) contentView.findViewById(R.id.tips_goodschoose);
        TextView goods_sizi = (TextView) contentView.findViewById(R.id.choose_goods_sizi);
        TextView addtocar = (TextView) contentView.findViewById(R.id.choose_num_addtocart);
        TextView buynow = (TextView) contentView.findViewById(R.id.choose_num_buynow);
        goods_pric.setText("¥" + data.getObj().getActInfo().getDiscountUnit());

        TextPaint mTextPaint = goods_sizi.getPaint();
        float textWidth = mTextPaint.measureText(ItemSize);
        if (textWidth > dip2px(60)) {
            LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams((int) textWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
            goods_sizi.setLayoutParams(layoutParams1);
        }
        if (ItemSize != null) {
            goods_sizi.setText(ItemSize);
        }

        choose_num.setText("1" + UnitName);
        SimpleDraweeView img_goods = (SimpleDraweeView) contentView.findViewById(R.id.popgoods_img);
        img_goods.setImageURI(UrlApi.SERVER_IP + data.getObj().getItemInfo().getItemPic1());

        View.OnClickListener listner = new View.OnClickListener() {
            double allMony = 0;

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.popuwid_exit:
                        bottomDialog.dismiss();
                        break;
                    case R.id.good_detail_jian:
                        if (goodNum != 1) {
                            goodNum -= 1;

                            Log.d("FragmentGoods", "canBuyCout:" + canBuyCout);

                            choose_num.setText(goodNum + UnitName);
                            goods_num.setText(goodNum + "");
                            allMony = data.getObj().getActInfo().getDiscountUnit() * goodNum;
                            DecimalFormat df = new DecimalFormat("#####0.0");
                            String allMonys = df.format(allMony);
                            //加减时仍显示活动单价
                            //  goods_pric.setText("¥" + allMonys);

                            if (canBuyCout >= goodNum) {
                                tips.setVisibility(View.GONE);
                            } else {
                                tips.setVisibility(View.VISIBLE);
                                tips.setText("提醒:商品限购" + mostCount + "件,超出部分按原价计算");
                            }
                        }
                        break;
                    case R.id.good_detail_add:
                        goodNum += 1;
                        choose_num.setText(goodNum + UnitName);
                        goods_num.setText(goodNum + "");
                        allMony = data.getObj().getActInfo().getDiscountUnit() * goodNum;
                        DecimalFormat df = new DecimalFormat("#####0.0");
                        String allMonys = df.format(allMony);
                  //      goods_pric.setText("¥" + allMonys);
                        Log.d("FragmentGoods", "canBuyCout:" + canBuyCout);

                        if (canBuyCout >= goodNum) {
                            tips.setVisibility(View.GONE);
                        } else {
                            tips.setVisibility(View.VISIBLE);
                            tips.setText("提醒:商品限购" + mostCount + "件,超出部分按原价计算");
                        }

                        break;
                    case R.id.choose_num_addtocart:
                        if (WDWApp.getUserToken() != null) {
                            addGoodsToCar();
                            bottomDialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), LognActivity.class));
                        }
                        break;
                    /******************************************立即购买***************************************
                     * 传入下个页面的参数为：
                     *
                     */
                    case R.id.choose_num_buynow:
                        if (WDWApp.getUserToken() != null) {
                            //主商品的item信息
                            List<OrderGoodsInfo> orderGoodsInfos = new ArrayList<>();
                            OrderGoodsInfo orderData = new OrderGoodsInfo();
                            orderData.setAmount(goodNum);
                            orderData.setItemName(data.getObj().getItemInfo().getItemName());
                            orderData.setItemPic(data.getObj().getActInfo().getItemPic());
                            orderData.setItemSizi(data.getObj().getActInfo().getItemSize());
                            orderData.setItemPrice(data.getObj().getActInfo().getDiscountUnit());
                            orderData.setItemId(data.getObj().getItemInfo().getID() + "");
                            //赠品信息
                            List<OrderGoodsInfo.PresentItems> presentItemses = new ArrayList<>();
                            if (data.getObj().getActInfo().getPagePresentItemList() != null) {
                                for (int i = 0; i < data.getObj().getActInfo().getPagePresentItemList().size(); i++) {
                                    OrderGoodsInfo.PresentItems present = new OrderGoodsInfo.PresentItems();
                                    present.setPitemId(data.getObj().getActInfo().getPagePresentItemList().get(i).getItemID());
                                    present.setPitemPic(data.getObj().getActInfo().getPagePresentItemList().get(i).getItemPic());
                                    present.setPitemName(data.getObj().getActInfo().getPagePresentItemList().get(i).getItemName());
                                    present.setTag(data.getObj().getActInfo().getActiviName());
                                    presentItemses.add(present);
                                }
                            }

                            orderData.setPresentItems(presentItemses);
                            orderGoodsInfos.add(orderData);

                            Intent in = new Intent(getActivity(), OrderBalanceActivity.class);
                            in.putExtra("orderInfo", (Serializable) orderGoodsInfos);
                            startActivity(in);
                            bottomDialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), LognActivity.class));
                        }
                        break;
                }
            }
        };
        img_exit.setOnClickListener(listner);
        img_jia.setOnClickListener(listner);
        img_add.setOnClickListener(listner);
        addtocar.setOnClickListener(listner);
        buynow.setOnClickListener(listner);

        bottomDialog.show();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    /**
     * 初始化图片
     *
     * @param inflater
     */
    private void getViews(LayoutInflater inflater) {

        View[] views = {view1, view2, view3, view4, view5};
        List<String> picUrl = new ArrayList<>();
        if (data.getObj().getItemInfo().getItemPic1() != null) {
            picNum += 1;
            picUrl.add(data.getObj().getItemInfo().getItemPic1());
        }
        if (data.getObj().getItemInfo().getItemPic2() != null) {
            picNum += 1;
            picUrl.add(data.getObj().getItemInfo().getItemPic2());
        }
        if (data.getObj().getItemInfo().getItemPic3() != null) {
            picNum += 1;
            picUrl.add(data.getObj().getItemInfo().getItemPic3());
        }
        if (data.getObj().getItemInfo().getItemPic4() != null) {
            picNum += 1;
            picUrl.add(data.getObj().getItemInfo().getItemPic4());
        }
        if (data.getObj().getItemInfo().getItemPic5() != null) {
            picNum += 1;
            picUrl.add(data.getObj().getItemInfo().getItemPic5());
        }
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        mPaths = new ArrayList<>();
        for (int i = 0; i < picNum; i++) {
            views[i] = inflater.inflate(R.layout.goodsdetailimagpager, null);
            ((SimpleDraweeView) (views[i].findViewById(R.id.gooddetai_img))).setImageURI(UrlApi.SERVER_IP + picUrl.get(i));
            viewList.add(views[i]);
            mPaths.add(UrlApi.SERVER_IP + picUrl.get(i));
        }

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
                LogUtils.d("fdsgf", response);
                final HotSaleData datas = JSON.parseObject(response, HotSaleData.class);
                MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                goodsdetaiRemaituijianlist.setLayoutManager(linearLayoutManager);
                GoodDetailHotSaleAdapter adapter = new GoodDetailHotSaleAdapter(R.layout.item_goods, datas.getObj());
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent in = new Intent(getActivity(), GoodsDetails1.class);
                        in.putExtra("itemId", datas.getObj().get(position).getID() + "");
                        Log.d("FragmentGoods_intent", datas.getObj().get(position).getItemID());
                        startActivity(in);
                    }
                });
                goodsdetaiRemaituijianlist.setAdapter(adapter);
            }
        });
    }

    /**
     * 获取销量最高品牌信息
     */
    private void loadMaxBrand(String ITEMID) {

        Map<String, String> map = new HashMap<>();
        map.put("itemId", ITEMID);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETBRANDMAX, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentGoods", response);
                BrandMaxData data = JSON.parseObject(response, BrandMaxData.class);
                goodsdetaiPinpaiName.setText(data.getObj().getBrandName());
                goodsdetaiPinpaiZaishou.setText(data.getObj().getItemCount() + "件商品在售");
                goodsdetaiPinpaiXaioliang.setText("累计销量" + data.getObj().getSalesCount() + "件");
                goodsdetaiPinpaiImg.setImageURI(UrlApi.SERVER_IP + data.getObj().getBrandPic());
            }
        });

    }

    /**
     * 获取评价信息
     */

    private void loadEvaluateData(String itemId) {
        Map<String, String> map = new HashMap<>();
        map.put("itemId", itemId);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETEVALUATE, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentGoods____num", response);
                GoodEvaluatData data = JSON.parseObject(response, GoodEvaluatData.class);


            }
        });

    }

    /**
     * 获取评价列表数据
     *
     * @param itemId
     */
    private void loadEvaluateListData(String itemId) {
        Map<String, String> map = new HashMap<String, String>();

        map.put("itemId", itemId);
        map.put("pageIndex", "1");
        map.put("grade", "0");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETEVALUATELIST, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentGoods__list", response);
                GoodsEvaluateListData data = JSON.parseObject(response, GoodsEvaluateListData.class);
                if (data.getObj().size() == 0) {
                    goodsdetaiMoreevaluate.setVisibility(View.GONE);
                    evaContentRelay.setVisibility(View.GONE);
                    noEvaluate.setVisibility(View.VISIBLE);
                } else {
                    noEvaluate.setVisibility(View.GONE);
                    fgoodevaluatUserhead.setImageURI(UrlApi.SERVER_IP + (String) data.getObj().get(0).getHeadFace());

                    if (data.getObj() != null) {
                        goodsdetaiPingjianum.setText("(" + data.getObj().size() + ")");
                    } else {
                        goodsdetaiPingjianum.setText("(0)");
                    }

                    /******手机号码*****/
                    StringBuilder sb = new StringBuilder();
                    sb.append((String) data.getObj().get(0).getMemberPhone());
                    sb.replace(3, 7, "****");
                    fgoodevaluatUserphone.setText(sb.toString());
                    /*****评论内容******/
                    fgoodevaluatContent.setText(data.getObj().get(0).getCommContent());
                    //  fgoodevaluatImg1.setImageURI(UrlApi.SERVER_IP + data.getObj().get(0).getUpPics());
                    String pic = data.getObj().get(0).getUpPics();
                    if (pic == null || pic.equals("")) {
                        fgoodevaluatImgLl.setVisibility(View.GONE);
                    } else {
                        fgoodevaluatImgLl.setVisibility(View.VISIBLE);
                        String[] evaPic = new String[0];
                        if (pic != null) {
                            evaPic = pic.split(";");
                        }
                        //评价图片
                        for (int i = 0; i < evaPic.length; i++) {
                            SimpleDraweeView simgView = new SimpleDraweeView(getActivity());
                            LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(50), LinearLayout.LayoutParams.MATCH_PARENT);
                            pams.setMargins(dip2px(10), 0, 0, 0);
                            simgView.setLayoutParams(pams);
                            fgoodevaluatImgLl.addView(simgView);
                            simgView.setImageURI(UrlApi.SERVER_IP + evaPic[i]);
                        }
                    }

                    fgoodevaluatRatingbar.setRating(data.getObj().get(0).getSatisfaction());
                    fgoodevaluatTime.setText(data.getObj().get(0).getCommentTime());
                }
            }
        });

    }

    /*********
     * 点击事件监听
     *
     * @param view
     */
    @OnClick({R.id.goods_indicator_dot_ll, R.id.goodsdetai_moreevaluate, R.id.choose_goodsnum_ll, R.id.goodsdetai_gotopingpai, R.id.gooddetail_shoucang, R.id.gooddetai_kefu, R.id.gooddetai_goto_shopingcar, R.id.gooddetai_addcar, R.id.gooddetai_buynow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //收藏
            case R.id.gooddetail_shoucang:
                new HtttpRequest(FragmentGoods.this).isLogin("gooddetail_shoucang");


                break;
            ///客服
            case R.id.gooddetai_kefu:
                String title = "稳当生活";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，
                 * 用于客服了解用户是从什么页面进入三个参数分别为
                 * 来源页面的url，来源页面标题，来源页面额外信息（可自由定义）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
                ConsultSource source = new ConsultSource("url", "商品详情", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
                if (Unicorn.isServiceAvailable()) {
                    Log.d("GoodsDetails", "七鱼");
                    Unicorn.openServiceActivity(getActivity().getApplicationContext(), title, source);
                }


                break;
            ///购物车
            case R.id.gooddetai_goto_shopingcar:
//                Intent intent = new Intent(getActivity(), MainPagerActivity.class);
//                intent.putExtra("fr", 3);
//                startActivityForResult(intent, 6);
//                Intent intent = new Intent(getActivity(), ShopingCartActivity.class);
               Intent intent = new Intent(getActivity(), ShoppingCart1.class);
                startActivity(intent);

                break;
            /***加入购物车***/
            case R.id.gooddetai_addcar:
                new HtttpRequest(FragmentGoods.this).isLogin("gooddetai_addcar");
                break;
            /**立即购买**/
            case R.id.gooddetai_buynow:
                new HtttpRequest(FragmentGoods.this).isLogin("gooddetai_buynow");
                break;
            case R.id.goods_indicator_dot_ll:
                break;
            /**评价***/
            case R.id.goodsdetai_moreevaluate:
                EventBus.getDefault().post("gotoevaluat");
                break;
            /***选择商品规格***/
            //          case R.id.choose_goodsnum_ll:
//                if (WDWApp.getUserToken() != null) {
//                    loadNumOfCanBuy();
//
//                } else {
//                    Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getActivity(), LognActivity.class));
//                }
//                break;
            /****品牌**/
            case R.id.goodsdetai_gotopingpai:
                Intent intent1 = new Intent(getActivity(), BrandDetails.class);
                startActivity(intent1);
                break;
        }
    }



    @Override
    public void isLogin(String what) {
        switch (what){
            case "gooddetail_shoucang":
                if (isCollect) {
                    cancelCollect(itemId);
                } else {
                    addToCollect(itemId);
                }
                break;

            case "gooddetai_buynow":
                loadNumOfCanBuy();
                break;
            case "gooddetai_addcar":
                addGoodsToCar();
                break;

        }

    }




    /**
     * 添加进入购物车
     */
    private void addGoodsToCar() {
        JSONObject object = new JSONObject();
        object.put("iItemID", itemId);
        object.put("iCount", 1);
        object.put("sMemberID", WDWApp.getMenberId());
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_ADDSGOPINGCAR, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentGoods", response);
                if (response.endsWith("\"已加入\"}")) {
                    Toast.makeText(getActivity(), "已加入", Toast.LENGTH_SHORT).show();
                    gooddetailCarNum.setVisibility(View.VISIBLE);
                    carNum = carNum + 1;
                    gooddetailCarNum.setText(carNum + "");
                }
            }
        });
    }

    /**
     * 加入收藏
     */
    private void addToCollect(String itemId) {
        JSONObject object = new JSONObject();
        object.put("itemId", itemId);
        object.put("dataSource", "Mobile");
        object.put("memberId", WDWApp.getMenberId());
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_ADDTOCOLECT, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404)) {
                    if ((int) JSONObject.parseObject(response).get("dataStatus") > 0) {
                        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_SHORT).show();
                        gooddetailShoucangImg.setImageResource(R.drawable.ico_collected);
                        isCollect = true;
                    } else {
                        Toast.makeText(getActivity(), (JSONObject.parseObject(response).get("describe")).toString(), Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*******************************
     * 秒杀的倒计时
     *******************************************/
    private void initskillTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null;
        try {
            d1 = df.parse(data.getObj().getActInfo().getSeckillEndTime());
            long da1 = d1.getTime();
            long da2 = System.currentTimeMillis();
            long diff = da1 - da2;
            time = diff;
            if (time != 0) {
                mc = new MyCount(time, 1000);
                mc.start();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void noLofin() {
        if(WDWApp.getUserToken()!=null){
            Toast.makeText(getActivity(), "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
            WDWApp.setUserToken(null);
        }else {
            Toast.makeText(getActivity(), "请登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(),LognActivity.class));
        }
    }


    /*定义一个倒计时的内部类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            gooddetailMiaoshatital.setVisibility(View.GONE);
            goodsdetaiActivity.setText("活动结束");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            String formatLongToTimeStr = formatLongToTimeStr(millisUntilFinished);
            String[] split = formatLongToTimeStr.split(":");
            gooddetailsHour.setText(split[0] + "");
            gooddetailMinut.setText(split[1] + "");
            gooddetailSecond.setText(split[2] + "");
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

    /***
     * s是否收藏
     *
     * @param itemID
     */
    private void loadIsCollect(String itemID) {
        Map<String, String> map = new HashMap<>();
        map.put("itemId", itemID);
        HtttpRequest.CreatGetRequst(UrlApi.URL_CheckCollects, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("loadIsCollect", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("obj") != null && ((int) (JSONObject.parseObject(response).get("obj")) > 0)) {
                        isCollect = true;
                        gooddetailShoucangImg.setImageResource(R.drawable.ico_collected);
                    } else {
                        isCollect = false;
                        gooddetailShoucangImg.setImageResource(R.drawable.ico_collect);
                    }
                }
            }
        });
    }

    /**
     * 删除收藏
     */
    private void cancelCollect(String itemId) {
        JSONObject object = new JSONObject();
        object.put("memberId", "00");
        object.put("dataSource", "APP");
        object.put("itemId", itemId);
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_DELETECOLECTION, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("Fragds_cancelCollect", response);
                Toast.makeText(getActivity(), "移除成功", Toast.LENGTH_SHORT).show();
                isCollect = false;
                gooddetailShoucangImg.setImageResource(R.drawable.ico_collect);

            }
        });
    }

    private int carNum = 0;

    /**
     * 购物车数量
     */
    private void loadCarnum() {
        Map<String, String> map = new HashMap<>();
        map.put("sSource", "APP");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetShoppingCartItem, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentGoods", response);
                if (!response.contains(MConstant.HTTP404)) {
                    GoodDetailCarDada dada = JSON.parseObject(response, GoodDetailCarDada.class);
                    if (dada.getObj() != null) {

                        for (int i = 0; i < dada.getObj().size(); i++) {
                            carNum += dada.getObj().get(i).getBuyCounts();
                        }

                        if (carNum == 0) {
//                            gooddetailCarNum.setVisibility(View.GONE);
                        }else {
                            gooddetailCarNum.setVisibility(View.VISIBLE);
                        }
                        gooddetailCarNum.setText(carNum + "");
                    }
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//取消注册
        addUnreadCountChangeListener(false);
        if (gooddetailPicdetial != null) {
            gooddetailPicdetial.clearHistory();
            ((ViewGroup) gooddetailPicdetial.getParent()).removeView(gooddetailPicdetial);
            gooddetailPicdetial.loadUrl("about:blank");
            gooddetailPicdetial.stopLoading();
            gooddetailPicdetial.setWebChromeClient(null);
            gooddetailPicdetial.setWebViewClient(null);
            gooddetailPicdetial.destroy();
            gooddetailPicdetial = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GooddetailScroll(Integer messageEvent) {
        if (messageEvent == 1) {
            gooddetailScrolle.scrollTo(0, dip2px(915));
        } else {

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void GooddetailScrolltotop(Integer messageEvent) {
        if (messageEvent == 10086) {
            gooddetailScrolle.scrollTo(0, 0);
        } else {

        }
    }


    private CanBuyCountData canBuyCountData;

    /**
     * 获取限购数量
     */
    private void loadNumOfCanBuy() {
        startProgressDialog("加载中...");
        Map<String, String> map = new HashMap<>();
        map.put("iItemId", itemId);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetiCanBuyCount, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                Log.d("FragmentGoods", "e:" + e);
                showPopupWindow();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                Log.d("FragmentGoods", response);
                if (response != null && (!response.contains(MConstant.HTTP404))) {
                    canBuyCountData = JSON.parseObject(response, CanBuyCountData.class);
                    if (canBuyCountData.getObj() != null) {
                        canBuyCout = canBuyCountData.getObj().getCanBuyCout();
                        mostCount = canBuyCountData.getObj().getMostCount();
                        showPopupWindow();
                    } else {
                        //规格选择弹窗
                        showPopupWindow();
                    }

                } else {
                    return;
                }
            }
        });
    }

    //网易七鱼未读消息
    // 添加未读数变化监听，add 为 true 是添加，为 false 是撤销监听。
    // 退出界面时，必须撤销，以免造成资源泄露
    private UnreadCountChangeListener listener = new UnreadCountChangeListener() { // 声明一个成员变量
        @Override
        public void onUnreadCountChange(int count) {
            // 在此更新界面, count 为当前未读数，
            // 也可以用 Unicorn.getUnreadCount() 获取总的未读数
            Log.d("FragmentGoods", "count:" + count);
            if (count > 0) {
                gooddetailNotreadNum.setVisibility(View.VISIBLE);
                gooddetailNotreadNum.setText(count + "");
            } else {
                gooddetailNotreadNum.setVisibility(View.GONE);
            }

        }
    };


    private void addUnreadCountChangeListener(boolean add) {
        Unicorn.addUnreadCountChangeListener(listener, add);
    }

    @Override
    public void onResume() {
        super.onResume();
        carNum=0;
        loadCarnum();
        loadIsCollect(itemId);
    }
}
