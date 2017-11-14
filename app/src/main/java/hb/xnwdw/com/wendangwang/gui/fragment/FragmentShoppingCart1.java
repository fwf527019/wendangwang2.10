package hb.xnwdw.com.wendangwang.gui.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
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
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.gui.activity.OrderBalanceActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.GoodsCartListViewAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ShopCartHistotryGridAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ShopcartLosedGoodsAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.FullyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.gui.widget.SimpleDividerItemDecoration;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CanBuyCountData;
import hb.xnwdw.com.wendangwang.netdata.entity.FooterHistData;
import hb.xnwdw.com.wendangwang.netdata.entity.FullReduce;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderGoodsInfo;
import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * 原生购物车
 * Created by Administrator on 2017/2/20.
 */

public class FragmentShoppingCart1 extends FragmentBase implements GoodsCartListViewAdapter.CheckInterface, GoodsCartListViewAdapter.ModifyCountInterface {

    @BindView(R.id.is_carempty)
    LinearLayout isCarempty;
    @BindView(R.id.shopcart_quanxuan)
    CheckBox shopcartQuanxuan;
    @BindView(R.id.shopingcart_goodtotalsnum)
    TextView shopingcartGoodtotalsnum;
    @BindView(R.id.shongpingcart_totalmoney)
    TextView shongpingcartTotalmoney;
    Unbinder unbinder;
    @BindView(R.id.shopingcart_jiesuan)
    Button shopingcartJiesuan;
    @BindView(R.id.shopingcart_goods_listview)
    RecyclerView shopingcartGoodsListview;
    @BindView(R.id.shopingcart_losegoods_recview)
    RecyclerView shopingcartLosegoodsRecview;
    @BindView(R.id.cart_root_fr)
    FrameLayout cartRootFr;
    @BindView(R.id.shongpingcart_gridview)
    RecyclerView shongpingcartGridview;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.start_shoping)
    TextView startShoping;
    @BindView(R.id.cart_jieshuan)
    LinearLayout cartJieshuan;
    @BindView(R.id.fullreduce_tv)
    TextView fullreduceTv;
    @BindView(R.id.fullreduce_ll)
    LinearLayout fullreduceLl;
    @BindView(R.id.his_tv)
    LinearLayout hisTv;
    @BindView(R.id.ll_lose)
    LinearLayout llLose;
    @BindView(R.id.clear_cannotbuy)
    TextView clearCannotbuy;
    @BindView(R.id.ll_lose_footer)
    LinearLayout llLoseFooter;
    private List<ShopingCartData.ObjBean> canBuy;
    private List<ShopingCartData.ObjBean> canNotBuy;
    private ShopcartLosedGoodsAdapter myShopcartLosAdapter;
    private MyThread timeThread;
    public static boolean ISAll = false;
    private double totalMoney;
    private Map<Integer, Double> map = new HashMap<>();
    private List<FooterHistData.ObjBean.ContentBean> listHis;
    private ShopCartHistotryGridAdapter adpter_his;
    private List<Integer> listPos;

    private Thread thread;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCarData();
        loadFoots();
        listPos = new ArrayList<>();
        back.setVisibility(View.GONE);
        //   rightTv.setText("编辑");
        title.setText("购物车");
        cartRootFr.setPadding(0, getStatusHeight(), 0, 0);

    }

    private GoodsCartListViewAdapter adapter;

    /**
     * 加载购物车数据
     */
    private void loadCarData() {

        Map<String, String> map1 = new HashMap<>();
        map1.put("sSource", "APP");
        map1.put("MemberID", WDWApp.getMenberId());
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETSHOPINGCARLIST, map1, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

                if (response.length() > 3000) {
                    for (int i = 0; i < response.length(); i += 3000) {
                        if (i + 3000 < response.length())
                            Log.i("rescounter" + i, response.substring(i, i + 3000));
                        else
                            Log.i("rescounter" + i, response.substring(i, response.length()));
                    }
                } else {
                    Log.i("resinfo", response);
                }

                if (response.startsWith("{")) {
                    final ShopingCartData data = JSON.parseObject(response, ShopingCartData.class);

                    if (data.getObj() != null && !data.getObj().toString().equals("null") && data.getObj().size() != 0) {
                        if (isCarempty != null) {
                            isCarempty.setVisibility(View.GONE);
                        }

                        // 将数据分割成能购买的和失效的
                        canBuy = new ArrayList<ShopingCartData.ObjBean>();
                        canNotBuy = new ArrayList<ShopingCartData.ObjBean>();

                        for (int i = 0; i < data.getObj().size(); i++) {
                            if (data.getObj().get(i).getState().equals("已删除") || data.getObj().get(i).getState().equals("已售罄") ||
                                    data.getObj().get(i).getState().equals("已下架") || data.getObj().get(i).getState().equals("已禁用")
                                    ) {
                                canNotBuy.add(data.getObj().get(i));
                            } else {
                                canBuy.add(data.getObj().get(i));
                            }
                        }
                    } else {
                        isCarempty.setVisibility(View.VISIBLE);
                        cartJieshuan.setVisibility(View.GONE);
                        rightTv.setVisibility(View.GONE);
                    }


                    /***适配能购买的****/
                    if (shopingcartGoodsListview != null) {
                        ((DefaultItemAnimator) shopingcartGoodsListview.getItemAnimator()).setSupportsChangeAnimations(false);
                        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(getActivity());


                        shopingcartGoodsListview.setLayoutManager(linearLayoutManager);
                        shopingcartGoodsListview.setNestedScrollingEnabled(false);
                        adapter = new GoodsCartListViewAdapter(R.layout.item_shopingcart_goods, canBuy, getActivity());
                        shopingcartGoodsListview.setAdapter(adapter);

                        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.drawable.line_divider);
                        shopingcartGoodsListview.addItemDecoration(new SimpleDividerItemDecoration(getActivity(), drawable, 5));
                        shopingcartGoodsListview.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (canBuy != null && canBuy.size() != 0) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        });
                        adapter.setCheckInterface(FragmentShoppingCart1.this);
                        adapter.setModifyCountInterface(FragmentShoppingCart1.this);
                        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.item_addto_collecte:
                                        addToCollect(position);
                                        break;
                                    case R.id.item_delecte:
                                        delecteItem(position);
                                        break;
                                    case R.id.shopcart_imgview:
                                        Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                                        intent.putExtra("itemId", canBuy.get(position).getItemID() + "");
                                        startActivity(intent);
                                        break;
                                }
                                return false;
                            }
                        });
                        /****item的长点击事件弹出删除收藏提示框*****/
                        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                            @Override
                            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                                showPopwiond(position);
                                return false;
                            }
                        });
                        timeThread = new MyThread(canBuy);
                        thread = new Thread(timeThread);
                        thread.start();
                        /***********能购买的************/
                        if (canBuy != null && canBuy.size() != 0) {


                            for (int i = 0; i < canBuy.size(); i++) {
                                final int finalI = i;
                                /***
                                 * 写入限买数量
                                 */
                                loadNumOfCanBuy(i, canBuy.get(i).getItemID() + "", new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {

                                        Log.d("FragmentShoppingCart1" + finalI, response);
                                        if (response != null && (!response.contains(MConstant.HTTP404))) {
                                            canBuyCountData = JSON.parseObject(response, CanBuyCountData.class);
                                            if (canBuyCountData.getObj() != null) {
                                                canBuy.get(finalI).setCanBuyCout(canBuyCountData.getObj().getCanBuyCout());
                                                canBuy.get(finalI).setMostCount(canBuyCountData.getObj().getMostCount());
                                            } else {
                                                LogUtils.d("FragmentShoppingCart1", "错误：" + finalI);

                                            }


                                        }
                                        adapter.notifyItemChanged(finalI);
                                    }
                                });

                            }
                        }
                    }
                    Log.d("FragmentShoppingCart1", "完成");

                    //适配失效的
                    if (shopingcartLosegoodsRecview != null) {
                        if (canNotBuy != null && canNotBuy.size() != 0) {
                            llLose.setVisibility(View.VISIBLE);
                            llLoseFooter.setVisibility(View.VISIBLE);
                            MyLinearLayoutManager linearLayoutManager2 = new MyLinearLayoutManager(getActivity());
                            shopingcartLosegoodsRecview.setNestedScrollingEnabled(false);

                            shopingcartLosegoodsRecview.setLayoutManager(linearLayoutManager2);
                            myShopcartLosAdapter = new ShopcartLosedGoodsAdapter(R.layout.item_losedgoods, canNotBuy);
                            shopingcartLosegoodsRecview.setAdapter(myShopcartLosAdapter);


//                            myShopcartLosAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_clearlist, (ViewGroup) shopingcartLosegoodsRecview.getParent(), false));
//                            myShopcartLosAdapter.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.header_shixiaogoods, (ViewGroup) shopingcartLosegoodsRecview.getParent(), false));
//                              myShopcartLosAdapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        for (int i = canNotBuy.size() - 1; i >= 0; i--) {
//                                            delecteItemCanNotBuy(i);
//
//                                        }
//                                        canNotBuy.clear();
//                                        myShopcartLosAdapter.notifyDataSetChanged();
//                                    }
//                                });
                        }
                    }


                }
            }
        });

    }

    /**
     * 弹出收藏删除对话框
     *
     * @param pos
     */
    private void showPopwiond(final int pos) {
        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_cart, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(true);
        //  bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        TextView add = (TextView) contentView.findViewById(R.id.car_addtocloocte);
        TextView delecte = (TextView) contentView.findViewById(R.id.car_delecte);

        if (canBuy.get(pos).getIsCollect() == 1) {
            add.setText("已收藏");
        } else {
            add.setText("加入收藏");
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canBuy.get(pos).getIsCollect() == 1) {
                } else {
                    addToCollect(pos);
                }
                bottomDialog.dismiss();

            }
        });
        delecte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
                delecteItem(pos);
            }
        });

        bottomDialog.show();
    }

    /**
     * 清空失效数据
     */
    private void delecteItemCanNotBuy(final int pos) {
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("iItemId", canNotBuy.get(pos).getItemID());
        jsonobj.put("sMemberID", "asd");
        Log.d("FragmentShoppingCart1", jsonobj.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_RemoveShoppingCartItem, jsonobj.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("FragmentShoppingCart1", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {

                    } else {
                        Toast.makeText(getActivity(), JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


    /**
     * 移除购物车
     *
     * @param pos
     */
    private void delecteItem(final int pos) {
        JSONObject jsonobj = new JSONObject();
        jsonobj.put("iItemId", canBuy.get(pos).getItemID());
        jsonobj.put("sMemberID", "asd");
        Log.d("FragmentShoppingCart1", jsonobj.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_RemoveShoppingCartItem, jsonobj.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("FragmentShoppingCart1", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        canBuy.remove(pos);
                        adapter.notifyItemRemoved(pos);
                        statistics();
                    } else {
                        Toast.makeText(getActivity(), JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    /***
     * 加入收藏
     *
     * @param pos
     */
    private void addToCollect(final int pos) {
        JSONObject object = new JSONObject();
        object.put("itemId", canBuy.get(pos).getItemID());
        object.put("dataSource", "APP");
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
                        canBuy.get(pos).setIsCollect(1);
                    } else {
                        Toast.makeText(getActivity(), (JSONObject.parseObject(response).get("describe")).toString(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private long diff = 0;


    /***
     * 足迹数据
     */
    private FooterHistData footData;

    private void loadFoots() {
        Map<String, String> map = new HashMap<>();
        map.put("iPage", "1");
        map.put("iPageSize", "10");
        map.put("sDataSource", "APP");
        map.put("sMemberID", WDWApp.getMenberId());

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETFOOTHOSTORY, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                listHis = new ArrayList<FooterHistData.ObjBean.ContentBean>();
                Log.d("shopingCat-his", response);
                if (response.startsWith("{") && JSONObject.parseObject(response).get("obj") != null) {
                    footData = JSON.parseObject(response, FooterHistData.class);
                    MyLinearLayoutManager linearLayoutManager3 = new MyLinearLayoutManager(getActivity());
                    linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                    if (shongpingcartGridview != null) {
                        shongpingcartGridview.setNestedScrollingEnabled(false);
                        shongpingcartGridview.setLayoutManager(linearLayoutManager3);
                        if (footData.getObj() != null && footData.getObj().size() != 0) {
                            hisTv.setVisibility(View.VISIBLE);
                            for (int i = 0; i < footData.getObj().size(); i++) {
                                listHis.addAll(footData.getObj().get(i).getContent());
                            }

                            List<FooterHistData.ObjBean.ContentBean> HisList = new ArrayList<FooterHistData.ObjBean.ContentBean>();
                            if (listHis.size() > 10) {
                                HisList = listHis.subList(0, 10);
                            } else {
                                HisList = listHis;
                            }

                            adpter_his = new ShopCartHistotryGridAdapter(R.layout.item_goods, HisList);
                            shongpingcartGridview.setAdapter(adpter_his);
                            adpter_his.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent(getActivity(), GoodsDetails1.class);
                                    intent.putExtra("itemId", listHis.get(position).getItemID() + "");
                                    startActivity(intent);
                                }
                            });
                        } else {
                            hisTv.setVisibility(View.GONE);
                        }
                    }
                } else {
                    hisTv.setVisibility(View.GONE);
                }

            }
        });

    }


/*********************************************************************************************************************************/
    /**
     * 遍历list集合
     *
     * @return
     */
    private boolean isAllCheck() {

        for (ShopingCartData.ObjBean group : canBuy) {
            if (!group.isCheached())
                return false;
        }
        return true;
    }


    /**
     * 单选
     *
     * @param position  组元素位置
     * @param isChecked 组元素选中与否
     */
    @Override
    public void checkGroup(int position, boolean isChecked) {
        canBuy.get(position).setCheached(isChecked);
        if (isAllCheck())
            shopcartQuanxuan.setChecked(true);
        else
            shopcartQuanxuan.setChecked(false);
        adapter.notifyItemChanged(position);
        statistics();
    }

    /**
     * 增加
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        ShopingCartData.ObjBean shoppingCartBean = canBuy.get(position);
        int currentCount = shoppingCartBean.getBuyCounts();
        currentCount++;

        canBuy.get(position).setBuyCounts(currentCount);
        shoppingCartBean.setBuyCounts(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        addNum(canBuy.get(position).getItemID() + "", 1);
        adapter.notifyItemChanged(position);
        statistics();
    }

    /**
     * 删减
     *
     * @param position      组元素位置
     * @param showCountView 用于展示变化后数量的View
     * @param isChecked     子元素选中与否
     */
    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        ShopingCartData.ObjBean shoppingCartBean = canBuy.get(position);
        int currentCount = shoppingCartBean.getBuyCounts();
        if (currentCount == 1) {
            return;
        }
        currentCount--;
        canBuy.get(position).setBuyCounts(currentCount);
        addNum(canBuy.get(position).getItemID() + "", -1);
        shoppingCartBean.setBuyCounts(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        adapter.notifyItemChanged(position);
        statistics();
    }

    @Override
    public void childDelete(int position) {

    }


    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private List<OrderGoodsInfo> orderGoodsInfoList;
    private double fullsMoneys = 0;
    private String fullsMoney;
    private String sActivityNum;

    /**
     * 统计操作
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
     * 3.给底部的textView进行数据填充
     */
    public void statistics() {
        orderGoodsInfoList = new ArrayList<>();
        totalCount = 0;
        fullsMoneys = 0.00;
        totalPrice = 0.00;
        for (int i = 0; i < canBuy.size(); i++) {
            ShopingCartData.ObjBean shoppingCartBean = canBuy.get(i);
            if (shoppingCartBean.isCheached()) {
                //满减活动
                if (shoppingCartBean.getActivityType() == 4) {
                    sActivityNum = shoppingCartBean.getActivityNum();
                    fullsMoneys += (shoppingCartBean.getBuyCounts() * shoppingCartBean.getUnit());
                    DecimalFormat df = new DecimalFormat("#####0.0");
                    fullsMoney = df.format(fullsMoneys);
                }
                OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
                orderGoodsInfo.setAmount(shoppingCartBean.getBuyCounts());
                orderGoodsInfo.setItemId(shoppingCartBean.getItemID() + "");
                orderGoodsInfoList.add(orderGoodsInfo);
                totalCount += shoppingCartBean.getBuyCounts();
                if (canBuy.get(i).getBuyCounts() > canBuy.get(i).getCanBuyCout()) {
                    totalPrice += (shoppingCartBean.getUnit() * shoppingCartBean.getCanBuyCout()) + (shoppingCartBean.getCostUnit() * (shoppingCartBean.getBuyCounts() - shoppingCartBean.getCanBuyCout()));
                } else {
                    totalPrice += shoppingCartBean.getUnit() * shoppingCartBean.getBuyCounts();
                }
            }
        }

        /**
         * 满减信息
         */

        getFullReduceinfo(sActivityNum, fullsMoney, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("Frag", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("Frag", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("obj") != null) {
                        FullReduce data = JSON.parseObject(response, FullReduce.class);
                        fullreduceLl.setVisibility(View.VISIBLE);
                        fullreduceTv.setText("节省：¥" + data.getObj().getMoney());
                    } else {
                        fullreduceLl.setVisibility(View.GONE);
                    }
                }
            }
        });

        DecimalFormat df = new DecimalFormat("#####0.0");
        String totoalMoneys = df.format(totalPrice);
        shongpingcartTotalmoney.setText(totoalMoneys);
        shopingcartGoodtotalsnum.setText(totalCount + "");
    }

    /**
     * 按钮监听
     *
     * @param view
     */
    @OnClick({R.id.shopcart_quanxuan, R.id.shopingcart_jiesuan, R.id.right_tv, R.id.start_shoping,R.id.clear_cannotbuy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shopcart_quanxuan:
                if (canBuy.size() != 0) {
                    if (shopcartQuanxuan.isChecked()) {
                        for (int i = 0; i < canBuy.size(); i++) {
                            canBuy.get(i).setCheached(true);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        for (int i = 0; i < canBuy.size(); i++) {
                            canBuy.get(i).setCheached(false);
                        }

                        adapter.notifyDataSetChanged();
                    }
                    statistics();
                }
                break;
            case R.id.shopingcart_jiesuan:
                int cheackedNum = 0;
                for (int i = 0; i < canBuy.size(); i++) {
                    if (canBuy.get(i).isCheached()) {
                        cheackedNum += 1;
                    }
                }
                if (cheackedNum > 0) {
                    Intent intent = new Intent(getActivity(), OrderBalanceActivity.class);
                    intent.putExtra("orderInfo", (Serializable) orderGoodsInfoList);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "亲您没有选择商品", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.clear_cannotbuy:
                llLoseFooter.setVisibility(View.GONE);
                llLose.setVisibility(View.GONE);

                for (int i = canNotBuy.size() - 1; i >= 0; i--) {
                    delecteItemCanNotBuy(i);
                    canNotBuy.remove(i);
                }
                shopingcartLosegoodsRecview.setVisibility(View.GONE);
                break;
//            case R.id.right_tv:
//                if (GoodsCartListViewAdapter.isEdiText == 1) {
//                    GoodsCartListViewAdapter.isEdiText = 0;
//                    rightTv.setText("编辑");
//                    adapter.notifyDataSetChanged();
//                } else {
//                    GoodsCartListViewAdapter.isEdiText = 1;
//                    rightTv.setText("完成");
//                    adapter.notifyDataSetChanged();
//                }
//
//                break;
            case R.id.start_shoping:
                MainPagerActivity.GotoFragment(0);
                break;
        }
    }

    //    timeThread = new MyThread(mActivitiesList);
//    new Thread(timeThread).start();
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    //刷新适配器
                    //    mRecommendActivitiesAdapter.notifyDataSetChanged();
                    //优化刷新adapter的方法

//                    for (Integer pos : listPos) {
//                        adapter.notifyItemChanged(pos);
//                    }


                    for (int i = 0; i < listPos.size(); i++) {
                        adapter.notifyItemChanged(listPos.get(i));
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    boolean endThread = false;


    /**
     * 秒杀商品的倒计时类
     */
    class MyThread implements Runnable {
        //用来停止线程


        List<ShopingCartData.ObjBean> shopingcatData;

        public MyThread(List<ShopingCartData.ObjBean> shopingcatData) {
            this.shopingcatData = shopingcatData;
        }

        @Override
        public void run() {
            while (!endThread) {
                try {
                    Thread.sleep(1000);
                    for (int i = 0; i < shopingcatData.size(); i++) {
                        //拿到每件商品的时间差，转化为具体的多少天多少小时多少分多少秒
                        //并保存在商品time这个属性内
                        //并保存在商品time这个属性内
                        //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
                        // 并保存在每件商品的counttime属性内
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = null;
                        try {
                            if (canBuy.get(i).getActivityEndTime() == null || shopingcatData.get(i).getActivityEndTime().equals("") || (shopingcatData.get(i).getActivityType() != 3)) {
                                shopingcatData.get(i).setEndTime((long) 0);
                            } else {
                                listPos.add(i);
                                d1 = df.parse(shopingcatData.get(i).getActivityEndTime().replace("/", "-"));
                                LogUtils.d("MyThread", "d1:" + d1);
                                long da1 = d1.getTime();
                                long da2 = System.currentTimeMillis();
                                diff = da1 - da2;
                                shopingcatData.get(i).setEndTime(diff);
                                if (diff > 1000) {
                                    shopingcatData.get(i).setEndTime(diff - 1000);
                                }
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                    Message message = new Message();
                    message.what = 1;
                    //发送信息给handler
                    handler.sendMessage(message);
                } catch (Exception e) {

                }
            }
        }
    }

    /**
     * 获取限购数量
     */
    private CanBuyCountData canBuyCountData;

    private void loadNumOfCanBuy(final int pos, String itemId, StringCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("iItemId", itemId);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetiCanBuyCount, map, callback);


    }

    /***
     * 增加购物车数量
     */
    private void addNum(String itemId, final int num) {
        JSONObject object = new JSONObject();
        object.put("iItemID", itemId);
        object.put("iCount", num);
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

                }
            }
        });
    }

    protected boolean isCreate = false;


    @Override
    public void onResume() {
        super.onResume();
        if (canBuy != null&&adapter!=null) {
            canBuy.clear();
            adapter.notifyDataSetChanged();
        }
        if (canNotBuy != null&&myShopcartLosAdapter!=null) {
            canNotBuy.clear();
            myShopcartLosAdapter.notifyDataSetChanged();
        }
        if (footData != null && footData.getObj() != null && adpter_his != null) {
            footData.getObj().clear();
            adpter_his.notifyDataSetChanged();
        }
        Log.d("FragmentShoppingCart1", "onResume");
        loadCarData();
        if (adapter != null) {
            GoodsCartListViewAdapter.isEdiText = 0;
            shopcartQuanxuan.setChecked(false);
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取满减活动信息
     */
    private void getFullReduceinfo(String sActivityNum, String money, StringCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("sSource", "APP");
        map.put("sMemberID", "asd");
        map.put("sActivityNum", sActivityNum);
        map.put("iMoney", money);

        for (String value : map.values()) {
            Log.d("Frag", ("Value = " + value));
        }
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetFullReducMoney, map, callback);

    }

    /***
     * g
     * 获取满赠活动信息
     */
    private void getFullPresentInfo(String sActivityNum, String money) {
        Map<String, String> map = new HashMap<>();
        map.put("sSource", "APP");
        map.put("sActivityNum", sActivityNum);
        map.put("iMoney", money);
        map.put("sMemberID", "asd");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetFullPresentInfo, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }

    /**
     * 获取买赠活动信息
     */
    private void getBuyPresentInfo(String sActivityNum, String money) {
        Map<String, String> map = new HashMap<>();
        map.put("sSource", "APP");
        map.put("sActivityNum", sActivityNum);
        map.put("iMoney", money);
        map.put("sMemberID", "asd");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetBuyPresentInfo, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });

    }

    private boolean isclose = false;

    @Override
    public void onPause() {
        super.onPause();
        endThread = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        endThread = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        endThread = true;
    }
}