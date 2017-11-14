//package hb.xnwdw.com.wendangwang.gui.fragment;
//
//
//import android.content.Intent;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.LinearLayout;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.baoyz.swipemenulistview.SwipeMenu;
//import com.baoyz.swipemenulistview.SwipeMenuCreator;
//import com.baoyz.swipemenulistview.SwipeMenuItem;
//import com.baoyz.swipemenulistview.SwipeMenuListView;
//import com.zhy.http.okhttp.callback.StringCallback;
//
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import butterknife.Unbinder;
//import hb.xnwdw.com.wendangwang.R;
//import hb.xnwdw.com.wendangwang.WDWApp;
//import hb.xnwdw.com.wendangwang.gui.activity.OrderBalanceActivity;
//import hb.xnwdw.com.wendangwang.gui.adapter.GoodsCartListViewAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.ItemRemoveAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.ShopCartHistotryGridAdapter;
//import hb.xnwdw.com.wendangwang.gui.adapter.ShopcartLosedGoodsAdapter;
//import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
//import hb.xnwdw.com.wendangwang.netdata.UrlApi;
//import hb.xnwdw.com.wendangwang.netdata.entity.FooterHistData;
//import hb.xnwdw.com.wendangwang.netdata.entity.ShopingCartData;
//import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
//import hb.xnwdw.com.wendangwang.utils.LogUtils;
//import okhttp3.Call;
//
///**
// * Created by Administrator on 2017/2/20.
// */
//
//public class FragmentShoppingCart extends FragmentBase {
//
//    @BindView(R.id.is_carempty)
//    LinearLayout isCarempty;
//    //    @BindView(R.id.shopingcart_goods_recview)
////    ItemRemoveRecyclerView shopingcartGoodsRecview;
//
//    @BindView(R.id.shongpingcart_gridview)
//    RecyclerView shongpingcartGridview;
//    @BindView(R.id.tite_tv)
//    TextView titeTv;
//    @BindView(R.id.shopcart_quanxuan)
//    CheckBox shopcartQuanxuan;
//    @BindView(R.id.shopingcart_goodtotalsnum)
//    TextView shopingcartGoodtotalsnum;
//    @BindView(R.id.shongpingcart_totalmoney)
//    TextView shongpingcartTotalmoney;
//    Unbinder unbinder;
//    @BindView(R.id.shopingcart_jiesuan)
//    Button shopingcartJiesuan;
//    @BindView(R.id.shopingcart_goods_listview)
//    SwipeMenuListView shopingcartGoodsListview;
//
//    @BindView(R.id.shopingcart_losegoods_recview)
//    RecyclerView shopingcartLosegoodsRecview;
//
//
//    private MyThread timeThread;
//    private List<ShopingCartData.ObjBean> canBuy;
//    private List<ShopingCartData.ObjBean> canNotBuy;
//    private ShopcartLosedGoodsAdapter myShopcartLosAdapter;
//    private ItemRemoveAdapter myShopcartAdapter;
//    public static boolean ISAll = false;
//    ArrayList<GoodsData> list = new ArrayList<>();
//    private double totalMoney;
//    private Map<Integer, Double> map = new HashMap<>();
//    private List<FooterHistData.ObjBean.ContentBean> listHis;
//    private ShopCartHistotryGridAdapter adpter_his;
//
//
//    @Override
//    protected int getContentViewResId() {
//        return R.layout.fragment_shoppingcart;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        loadCarData();
//        loadFoots();
////        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getActivity());
////        shopingcartGoodsRecview.setLayoutManager(myLinearLayoutManager);
//
//        // 设置添加器监听器。
//
//        shopcartQuanxuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    ISAll = true;
//                    myShopcartAdapter.notifyDataSetChanged();
//
//                } else
//                    ISAll = false;
//                myShopcartAdapter.notifyDataSetChanged();
//            }
//        });
//
//
//        /*********
//         * 侧滑listview
//         ***********/
////测试数据集。
//        String[] data = new String[5];
//        for (int i = 0; i < data.length; i++) {
//            data[i] = "测试数据:" + i;
//        }
//
//       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, data);
//     //   shopingcartGoodsListview.setAdapter(adapter);
//
//        SwipeMenuCreator creator = new SwipeMenuCreator() {
//
//            @Override
//            public void create(SwipeMenu menu) {
//                // create "open" item
//                SwipeMenuItem openItem = new SwipeMenuItem(
//                        getActivity().getApplication());
//                // set item background
//                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
//                        0xCE)));
//                // set item width
//                openItem.setWidth(dip2px(90));
//                // set item title
//                openItem.setTitle("Open");
//                // set item title fontsize
//                openItem.setTitleSize(18);
//                // set item title font color
//                openItem.setTitleColor(Color.WHITE);
//                // add to menu
//                menu.addMenuItem(openItem);
//
//                // create "delete" item
//                SwipeMenuItem deleteItem = new SwipeMenuItem(
//                        getActivity());
//                // set item background
//                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
//                        0x3F, 0x25)));
//                // set item width
//                deleteItem.setWidth(dip2px(90));
//                // set a icon
//                //   deleteItem.setIcon(R.drawable.ic_delete);
//                // add to menu
//                menu.addMenuItem(deleteItem);
//            }
//        };
//        shopingcartGoodsListview.setMenuCreator(creator);
//
//
//        shopingcartGoodsListview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
//                switch (index) {
//                    case 0:
//                        // open
//                        break;
//                    case 1:
//                        // delete
//                        break;
//                }
//                // false : close the menu; true : not close the menu
//                return false;
//            }
//        });
//        shopingcartGoodsListview.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
//
//            @Override
//            public void onSwipeStart(int pos) {
//                Log.d("位置:" + pos, "开始侧滑...");
//            }
//
//            @Override
//            public void onSwipeEnd(int pos) {
//                Log.d("位置:" + pos, "侧滑结束.");
//            }
//        });
//
//
//    }
//
//
//    /**
//     * 加载购物车数据
//     */
//    private void loadCarData() {
//        Map<String, String> map1 = new HashMap<>();
//        map1.put("sSource", "APP");
//        map1.put("MemberID", WDWApp.getMenberId());
//        HtttpRequest.CreatGetRequst(UrlApi.URL_GETSHOPINGCARLIST, map1, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                LogUtils.d("FragmentShoppingCart", response);
//                if (response.startsWith("{")) {
//                    final ShopingCartData data = JSON.parseObject(response, ShopingCartData.class);
//                    if (data.getObj() != null && !data.getObj().toString().equals("null")) {
//                        if (isCarempty != null) {
//                            isCarempty.setVisibility(View.INVISIBLE);
//                        }
//
//                        // 将数据分割成能购买的和失效的
//                        canBuy = new ArrayList<ShopingCartData.ObjBean>();
//                        canNotBuy = new ArrayList<ShopingCartData.ObjBean>();
//                        for (int i = 0; i < data.getObj().size(); i++) {
//                            if (data.getObj().get(i).getState().equals("有货")) {
//                                canBuy.add(data.getObj().get(i));
//
//                            } else {
//                                canNotBuy.add(data.getObj().get(i));
//                            }
//                        }
//                    }
//
//                    //适配能购买的
//                    MyLinearLayoutManager linearLayoutManager = new MyLinearLayoutManager(getActivity());
////                    shopingcartGoodsRecview.setNestedScrollingEnabled(false);
////                    shopingcartGoodsRecview.setLayoutManager(linearLayoutManager);
////                    myShopcartAdapter = new ItemRemoveAdapter(getActivity(), canBuy);
////                    shopingcartGoodsRecview.setAdapter(myShopcartAdapter);
//                    final GoodsCartListViewAdapter adapter = new GoodsCartListViewAdapter(canBuy, getActivity());
//                      shopingcartGoodsListview.setAdapter(adapter);
////                    shopingcartGoodsRecview.setOnItemClickListener(new OnItemClickListener() {
////                        @Override
////                        public void onItemClick(View view, int position) {
////
////                        }
////
////                        @Override
////                        public void onDeleteClick(int position) {
////                            myShopcartAdapter.removeItem(position);
////                        }
////                    });
////                    for (int i = 0; i < canBuy.size(); i++) {
////                        long counttime = initskillTime(canBuy, i);
////                        canBuy.get(i).setCounttime(counttime);
////                    }
//                    /**单选按钮/******/
//                    adapter.setCheckInterface(new GoodsCartListViewAdapter.CheckInterface() {
//                        @Override
//                        public void checkGroup(int position, boolean isChecked) {
//                            canBuy.get(position).setCheached(isChecked);
//                            if (isAllCheck())
//                                shopcartQuanxuan.setChecked(true);
//                            else
//                                shopcartQuanxuan.setChecked(false);
//                            myShopcartAdapter.notifyDataSetChanged();
//                            statistics();
//                        }
//                    });
//
//
//                    /**加减******/
//                    adapter.setModifyCountInterface(new GoodsCartListViewAdapter.ModifyCountInterface() {
//                        @Override
//                        public void doIncrease(int position, View showCountView, boolean isChecked) {
//                            ShopingCartData.ObjBean shoppingCartBean = canBuy.get(position);
//                            int currentCount = shoppingCartBean.getCount();
//                            currentCount++;
//                            shoppingCartBean.setCount(currentCount);
//                            ((TextView) showCountView).setText(currentCount + "");
//                            adapter.notifyDataSetChanged();
//                            statistics();
//                        }
//
//                        @Override
//                        public void doDecrease(int position, View showCountView, boolean isChecked) {
//                            ShopingCartData.ObjBean shoppingCartBean = canBuy.get(position);
//                            int currentCount = shoppingCartBean.getCount();
//                            if (currentCount == 1) {
//                                return;
//                            }
//                            currentCount--;
//                            shoppingCartBean.setCount(currentCount);
//                            ((TextView) showCountView).setText(currentCount + "");
//                            adapter.notifyDataSetChanged();
//                            statistics();
//                        }
//
//                        @Override
//                        public void childDelete(int position) {
//
//                        }
//                    });
//
//
//                    //适配失效的
//                    MyLinearLayoutManager linearLayoutManager2 = new MyLinearLayoutManager(getActivity());
//                    shopingcartLosegoodsRecview.setNestedScrollingEnabled(false);
//                    shopingcartLosegoodsRecview.setLayoutManager(linearLayoutManager2);
//                    myShopcartLosAdapter = new ShopcartLosedGoodsAdapter(R.layout.item_losedgoods, canNotBuy);
//                    shopingcartLosegoodsRecview.setAdapter(myShopcartLosAdapter);
//                    if (canNotBuy != null) {
//                        myShopcartLosAdapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_clearlist, (ViewGroup) shopingcartLosegoodsRecview.getParent(), false));
//                        myShopcartLosAdapter.addHeaderView(getActivity().getLayoutInflater().inflate(R.layout.header_shixiaogoods, (ViewGroup) shopingcartLosegoodsRecview.getParent(), false));
//                    }
//
//                }
//            }
//        });
//
//    }
//
//
//    /**
//     * 统计操作
//     * 1.先清空全局计数器<br>
//     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作
//     * 3.给底部的textView进行数据填充
//     */
//    private int totalCount;
//    private double totalPrice;
//
//    public void statistics() {
//        totalCount = 0;
//        totalPrice = 0.00;
//        for (int i = 0; i < canBuy.size(); i++) {
//            ShopingCartData.ObjBean shoppingCartBean = canBuy.get(i);
//            if (shoppingCartBean.isCheached()) {
//                totalCount++;
//                totalPrice += shoppingCartBean.getUnit() * shoppingCartBean.getCount();
//            }
//        }
//        shopingcartGoodtotalsnum.setText(totalCount + "");
//        shongpingcartTotalmoney.setText(totalPrice + "");
//        shopingcartJiesuan.setText("结算(" + totalCount + ")");
//
//    }
//
//
//    /**
//     * 遍历list集合
//     *
//     * @return
//     */
//    private boolean isAllCheck() {
//
//        for (ShopingCartData.ObjBean group : canBuy) {
//            if (!group.isCheached())
//                return false;
//        }
//        return true;
//    }
//
//    private void commitData() {
//
//        if (myShopcartAdapter != null) {
//
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("dataSource", "APP");
//        map.put("memberId", WDWApp.getMenberId());
//        map.put("orderTypeId", "0");
//        map.put("itemList", "a");
//
//        final JSONObject object = new JSONObject();
//        object.put("dataSource", "APP");
//        object.put("memberId", WDWApp.getMenberId());
//        object.put("orderTypeId", "0");
//        object.put("itemList", list);
//        String paramsString = object.toJSONString();
//        HtttpRequest.CreatPostRequst(UrlApi.URL_SHOPINGCARSELEMENT, paramsString, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                LogUtils.d("FragmentShoppingCart_js", response);
//                if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
//                    Intent intent = new Intent(getActivity(), OrderBalanceActivity.class);
//                    String orderNum = JSONObject.parseObject(response).get("obj").toString();
//                    intent.putExtra("orderNum", orderNum);
//                    startActivity(intent);
//                }
//
//            }
//        });
//    }
//
//
//    class GoodsData {
//
//        int ItemID;
//        int Amount;
//
//        public int getItemID() {
//            return ItemID;
//        }
//
//        public void setItemID(int itemID) {
//            ItemID = itemID;
//        }
//
//        public int getAmount() {
//            return Amount;
//        }
//
//        public void setAmount(int amount) {
//            Amount = amount;
//        }
//
//
//    }
//
//    @OnClick(R.id.shopingcart_jiesuan)
//    public void onViewClicked() {
//        commitData();
////        Intent intent = new Intent(getActivity(), OrderBalanceActivity.class);
////        startActivity(intent);
//    }
//
//
//    /*******************************
//     * 秒杀的倒计时
//     *******************************************/
//    private long diff;
//
//    private long initskillTime(List<ShopingCartData.ObjBean> mList, int pos) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d1 = null;
//        try {
//            if (mList.get(pos).getActivityEndTime() == null || mList.get(pos).getActivityEndTime().equals("")) {
//                return 0;
//            } else {
//                d1 = df.parse(mList.get(pos).getActivityEndTime());
//                long da1 = d1.getTime();
//                long da2 = System.currentTimeMillis();
//                diff = da1 - da2;
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return diff;
//    }
//
//    /**
//     * 时间转化
//     *
//     * @param l
//     * @return
//     */
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
//        String strtime = hour + ":" + minute + ":" + second;
//        return strtime;
//
//    }
//
//    Handler handler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    //刷新适配器
//                    myShopcartAdapter.notifyDataSetChanged();
//                    //优化刷新adapter的方法
//                    //  myShopcartAdapter.notifyData();
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };
//
//    class MyThread implements Runnable {
//        //用来停止线程
//        boolean endThread;
//        List<ShopingCartData.ObjBean> list;
//
//        public MyThread(List<ShopingCartData.ObjBean> list) {
//            this.list = list;
//        }
//
//        @Override
//        public void run() {
//            while (!endThread) {
//                try {
//                    Thread.sleep(1000);
//                    for (int i = 0; i < list.size(); i++) {
//                        //拿到每件商品的时间差，转化为具体的多少天多少小时多少分多少秒
//                        //并保存在商品time这个属性内
//                        long counttime = list.get(i).getCounttime();
//                        int hours = (int) (counttime / (1000 * 60 * 60));
//                        int minutes = (int) ((counttime - hours * (1000 * 60 * 60)) / (1000 * 60));
//                        int second = (int) ((counttime - hours * (1000 * 60 * 60) - minutes * (1000 * 60)) / (1000));
//
//                        //并保存在商品time这个属性内
//                        //  String finaltime = days + "天" + hours + "时" + minutes + "分" + second + "秒";
//
//                        list.get(i).setTimeH(hours);
//                        list.get(i).setTimeM(minutes);
//                        list.get(i).setTimeS(second);
//                        //如果时间差大于1秒钟，将每件商品的时间差减去一秒钟，
//                        // 并保存在每件商品的counttime属性内
//                        if (counttime > 1000) {
//                            list.get(i).setCounttime(counttime - 1000);
//                        }
//                    }
//                    Message message = new Message();
//                    message.what = 1;
//                    //发送信息给handler
//                    handler.sendMessage(message);
//                } catch (Exception e) {
//
//                }
//            }
//        }
//    }
//
//    private void loadFoots() {
//        Map<String, String> map = new HashMap<>();
//        map.put("iPage", "1");
//        map.put("iPageSize", "10");
//        map.put("sDataSource", "APP");
//        map.put("sMemberID", WDWApp.getMenberId());
//
//        HtttpRequest.CreatGetRequst(UrlApi.URL_GETFOOTHOSTORY, map, new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                listHis = new ArrayList<FooterHistData.ObjBean.ContentBean>();
//                Log.d("shopingCat-his", response);
//                if (response.startsWith("{") && JSONObject.parseObject(response).get("obj") != null) {
//                    FooterHistData data = JSON.parseObject(response, FooterHistData.class);
//                    MyLinearLayoutManager linearLayoutManager3 = new MyLinearLayoutManager(getActivity());
//                    linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
//                    shongpingcartGridview.setNestedScrollingEnabled(false);
//                    shongpingcartGridview.setLayoutManager(linearLayoutManager3);
//                    if (data.getObj() != null && data.getObj().size() != 0) {
//                        for (int i = 0; i < data.getObj().size(); i++) {
//                            listHis.addAll(data.getObj().get(i).getContent());
//                        }
//                        adpter_his = new ShopCartHistotryGridAdapter(R.layout.item_goods, listHis);
//                        shongpingcartGridview.setAdapter(adpter_his);
//                    } else {
//
//                    }
//
//                }
//            }
//        });
//
//    }
//
//
//}
