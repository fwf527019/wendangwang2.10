package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.SheachpopAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ShearchResutsAdapter;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsBrandsData;
import hb.xnwdw.com.wendangwang.netdata.entity.ShearchResutData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/27.
 */

public class ShearchResutsActivity extends ActivityBase {

    @BindView(R.id.sherch_edt)
    EditText sherchEdt;
    @BindView(R.id.sherch_list)
    RecyclerView sherchList;
    @BindView(R.id.shearch_back_img)
    ImageView shearchBackImg;
    @BindView(R.id.defalt_sort)
    LinearLayout defaltSort;
    @BindView(R.id.price_sort)
    LinearLayout priceSort;
    @BindView(R.id.sold_sort)
    LinearLayout soldSort;
    @BindView(R.id.brand_sort)
    LinearLayout brandSort;
    @BindView(R.id.shearchresut_nodata)
    ImageView shearchresutNodata;
    @BindView(R.id.shearch_no_data)
    LinearLayout shearchNoData;
    @BindView(R.id.shearch_goto_cart)
    ImageView shearchGotoCart;
    @BindView(R.id.price_sort_img)
    ImageView priceSortImg;
    @BindView(R.id.sold_sort_img)
    ImageView soldSortImg;
    @BindView(R.id.choose_ll)
    LinearLayout chooseLl;
    @BindView(R.id.brand_sort_img)
    ImageView brandSortImg;
    @BindView(R.id.defalt_sort_tv)
    TextView defaltSortTv;
    @BindView(R.id.price_sort_tv)
    TextView priceSortTv;
    @BindView(R.id.sold_sort_tv)
    TextView soldSortTv;
    @BindView(R.id.brand_sort_tv)
    TextView brandSortTv;
    private ShearchResutsAdapter adapter;
    private String TAG;
    private String TwoCateID;
    private String OneCateID;
    private String OrderRule = "0";
    private PopupWindow mPopuwidow;
    private int pageSize = 8;
    private ShearchResutData data;
    private List<String> list;
    private String keyword;
    private boolean isSearch;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_shearchresuts;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        soldSortImg.setImageResource(R.drawable.sort2);
        priceSortImg.setImageResource(R.drawable.sort2);
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
        TAG = intent.getStringExtra("TAG");
        TwoCateID = intent.getStringExtra("TwoCateID");
        OneCateID = intent.getStringExtra("OneCateID");
        if (("classfy").equals(TAG)) {
            loadGoodsData(null, OneCateID, TwoCateID, null, "0", 1, 8);
            isSearch = false;
        } else {
            isSearch = true;
            sherchEdt.setText(keyword);
            loadSheaechData(keyword);

        }

        sherchEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if ((actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_SEARCH) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (list != null) {
                        list.clear();
                    }
                    if (sherchEdt.getText() != null) {
                        isSearch = true;
                        // loadSheaechData(sherchpageEdt.getText().toString().trim());
                        if (sherchEdt.getText().toString().length() > 0) {
                            keyword = sherchEdt.getText().toString();
                            loadSheaechData(sherchEdt.getText().toString());

                        } else {
                            Toast.makeText(ShearchResutsActivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                return false;
            }


        });
    }

    /**
     * 条件查询数据,排序
     *
     * @param keyword
     * @param OneCateID
     * @param
     * @param BrandID
     * @param OrderRule
     * @param PageIndex
     * @param PageSize
     */
    private List<Integer> idsList;
    int pageIndex = 1;

    private void loadGoodsData(final String keyword, final String OneCateID, final String TwoCateId, final List<Integer> BrandID, final String OrderRule, final int PageIndex, final int PageSize) {

        JSONObject jsobj = new JSONObject();
        jsobj.put("Keyword", keyword);
        jsobj.put("OneCateID", OneCateID);
        jsobj.put("TwoCateID", TwoCateId);
        jsobj.put("BrandID", BrandID);
        jsobj.put("OrderRule", OrderRule);
        jsobj.put("PageIndex", PageIndex);
        jsobj.put("OrderFiled", "MobilePrice");
        jsobj.put("PageSize", PageSize);
        jsobj.put("DataSource", "APP");
        final String pams = jsobj.toJSONString();
        Log.d("ShearchResutsActivity_1", pams);
        HtttpRequest.CreatPostRequst(UrlApi.URL_SearchItem, pams, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("ShearchResutsActivity_1", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("ShearchResutsActivity_1", response);
                if (!response.contains(MConstant.HTTP404)) {
                    data = JSON.parseObject(response, ShearchResutData.class);
                    if (JSONObject.parseObject(response).get("obj") == null) {
                        shearchNoData.setVisibility(View.VISIBLE);
                    } else {
                        if (isSearch) {
                            //加载页面数据后再加载出页面商品的品牌信息
                            GetBrandbyKeyword(keyword);
                        } else {
                            GetBrand(TwoCateId);
                        }
                        shearchNoData.setVisibility(View.GONE);
                        sherchList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                        adapter = new ShearchResutsAdapter(R.layout.item_shopcat_historygoods, data.getObj());
                        sherchList.setAdapter(adapter);
                        adapter.addFooterView(ShearchResutsActivity.this.getLayoutInflater().inflate(R.layout.sub_getmore, (ViewGroup) sherchList.getParent(), false));
                        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Intent intent = new Intent(ShearchResutsActivity.this, GoodsDetails1.class);
                                intent.putExtra("itemId", data.getObj().get(position).getID() + "");
                                startActivity(intent);
                            }
                        });
//                        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                            @Override
//                            public void onLoadMoreRequested() {
//                               loadGoodsData(keyword, OneCateID, TwoCateID, idList, OrderRule, pageIndex, pageSize);
//                            }
//                        }, sherchList);
                        adapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pageIndex += 1;
                                JSONObject jsobj = new JSONObject();
                                jsobj.put("Keyword", keyword);
                                jsobj.put("OneCateID", OneCateID);
                                jsobj.put("TwoCateID", TwoCateId);
                                jsobj.put("BrandID", BrandID);
                                jsobj.put("OrderRule", OrderRule);
                                jsobj.put("PageIndex", pageIndex);
                                jsobj.put("OrderFiled", "MobilePrice");
                                jsobj.put("PageSize", PageSize);
                                jsobj.put("DataSource", "APP");
                                String pamst = jsobj.toJSONString();
                                Log.d("ShearchResutsActivity_1", pams);
                                HtttpRequest.CreatPostRequst(UrlApi.URL_SearchItem, pamst, new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.d("ShearchResutsActivity_1", "e:" + e);
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        if (!response.contains(MConstant.HTTP404)) {
                                            ShearchResutData datas = JSON.parseObject(response, ShearchResutData.class);
                                            adapter.addData(datas.getObj());
                                            if (datas.getObj().size() == 0) {
                                                TextView tv = (TextView) adapter.getFooterLayout().findViewById(R.id.getmore_tv);
                                                tv.setText("没有更多");
                                            }

                                        }
                                    }
                                });
                            }
                        });

                    }
                }

            }
        });

    }


    @OnClick(R.id.shearch_back_img)
    public void onViewClicked() {
        finish();
    }

    /**
     * 收索结果数据,只有关键字
     *
     * @param s
     */
    private HashSet<String> hsset;

    private void loadSheaechData(final String s) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", s);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETSHERACHINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, final int id) {
                LogUtils.d("ShearchResutsActivit_2", response);
                if (!response.contains(MConstant.HTTP404)) {
                    data = JSON.parseObject(response, ShearchResutData.class);
                    if (data.getObj().size() != 0) {
                        shearchNoData.setVisibility(View.GONE);
                    }

                    GetBrandbyKeyword(s);
                    sherchList.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    adapter = new ShearchResutsAdapter(R.layout.item_shopcat_historygoods, data.getObj());
                    sherchList.setAdapter(adapter);
                    adapter.addFooterView(ShearchResutsActivity.this.getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) sherchList.getParent(), false));
                    adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(ShearchResutsActivity.this, GoodsDetails1.class);
                            intent.putExtra("itemId", data.getObj().get(position).getID() + "");
                            startActivity(intent);
                        }
                    });
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private boolean pricehight = true;
    private boolean soldhight = true;

    @OnClick({R.id.defalt_sort, R.id.price_sort, R.id.sold_sort, R.id.brand_sort, R.id.shearch_goto_cart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //默认排序
            case R.id.defalt_sort:
                idList = null;
                initColor(0);
                OrderRule = "0";
                loadGoodsData(keyword, OneCateID, TwoCateID, null, OrderRule, 1, 8);
                break;
            //价格排序
            case R.id.price_sort:
                initColor(1);
                soldSortImg.setImageResource(R.drawable.sort2);
                if (pricehight) {
                    priceSortImg.setImageResource(R.drawable.sort2_a);
                    OrderRule = "1";
                    pricehight = false;
                } else {
                    priceSortImg.setImageResource(R.drawable.sort1_orange);
                    pricehight = true;
                    OrderRule = "2";
                }
                loadGoodsData(keyword, OneCateID, TwoCateID, idList, OrderRule, 1, 8);
                break;
            //销量排序
            case R.id.sold_sort:
                initColor(2);
                priceSortImg.setImageResource(R.drawable.sort2);
                soldSortImg.setImageResource(R.drawable.sort2);
                if (soldhight) {
                    OrderRule = "3";
                    soldhight = false;
                    soldSortImg.setImageResource(R.drawable.sort2_a);
                } else {
                    OrderRule = "4";
                    soldhight = true;
                    soldSortImg.setImageResource(R.drawable.sort1_orange);
                }
                loadGoodsData(keyword, OneCateID, TwoCateID, idList, OrderRule, 1, 8);
                break;
            //品牌
            case R.id.brand_sort:
                initColor(3);
                showpopuwindow();
                break;
            //进入购物车
            case R.id.shearch_goto_cart:
                Intent intent = new Intent(this, ShoppingCart1.class);
                startActivity(intent);
                break;
        }
    }

    List<Integer> idList;
    boolean isIndent;
    List<String> brandList;

    /***
     * 品牌弹出
     */
    private void showpopuwindow() {
        idList = new ArrayList<>();
        View contentView = LayoutInflater.from(ShearchResutsActivity.this).inflate(
                R.layout.shearch_brand_pop_window, null);
        mPopuwidow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        mPopuwidow.setContentView(contentView);
        View rootview = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activit_shearchresuts, null);
        // mPopuwidow.showAtLocation(rootview, Gravity.NO_GRAVITY, 0, 0);
        mPopuwidow.showAsDropDown(chooseLl);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        mPopuwidow.setBackgroundDrawable(dw);
        mPopuwidow.setOutsideTouchable(true);
        final RecyclerView mreycycler = (RecyclerView) contentView.findViewById(R.id.shearch_brand_pop);
        TextView exit_tv = (TextView) contentView.findViewById(R.id.shearpop_exit);
        TextView ok_tv = (TextView) contentView.findViewById(R.id.shearpop_ok);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        gridLayoutManager.setSmoothScrollbarEnabled(false);
        mreycycler.setNestedScrollingEnabled(false);
        mreycycler.setLayoutManager(gridLayoutManager);


        if (list.size() > 12) {
            isIndent = true;
            brandList = list.subList(0, 11);
            brandList.add(11, "...");

        } else {
            isIndent = false;
            brandList = list;

        }

        final SheachpopAdapter sheachpopAdapter = new SheachpopAdapter(R.layout.item_popshearch, brandList, list);
        mreycycler.setAdapter(sheachpopAdapter);
        sheachpopAdapter.notifyDataSetChanged();
        sheachpopAdapter.setClicListener(new SheachpopAdapter.ClicListener() {
            @Override
            public void onClick(int postion, boolean isCheackd, View view) {

                if (postion == 11 && isIndent == true) {

                    isIndent = false;
                    sheachpopAdapter.remove(11);
                    sheachpopAdapter.setNewData(list);


                } else {

                    if (isCheackd) {
                        idList.add(idsList.get(postion));
                        view.setBackgroundColor(getResources().getColor(R.color.orange));
                        ((TextView) (view.findViewById(R.id.shearchpop_brand_tv))).setTextColor(getResources().getColor(R.color.maincolor));

                    } else {
                        idList.remove(idsList.get(postion));
                        view.setBackgroundColor(getResources().getColor(R.color.f5));
                        ((TextView) (view.findViewById(R.id.shearchpop_brand_tv))).setTextColor(getResources().getColor(R.color.black));

                    }
                }



            }
        });


        exit_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idList = null;
                loadGoodsData(keyword, OneCateID, TwoCateID, idList, "0", 1, 8);
                mPopuwidow.dismiss();
            }
        });

        ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据品牌的ID获取数据
                if (isSearch) {
                    loadGoodsData(keyword, "0", "0", idList, "0", 1, 8);
                } else {
                    loadGoodsData(keyword, OneCateID, TwoCateID, idList, "0", 1, 8);
                }
                mPopuwidow.dismiss();
            }
        });

    }

    /**
     * 根据第二类目，请求所有品牌数据
     */
    private void GetBrand(String TwoCateID) {
        list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("cateTwoId", TwoCateID);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETBRANDBYCATATWO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PP_t", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                idsList = new ArrayList<Integer>();
                Log.d("PP_t", response);
                if (!response.contains(MConstant.HTTP404)) {
                    GoodsBrandsData data = JSON.parseObject(response, GoodsBrandsData.class);
                    for (int i = 0; i < data.getObj().size(); i++) {
                        list.add(data.getObj().get(i).getBrandName());
                        idsList.add(data.getObj().get(i).getID());

                    }
                }
            }
        });
    }

    int ai = 1;

    /**
     * 根据关键字， 请求所有品牌数据
     */
    private void GetBrandbyKeyword(String kewords) {
        ai += 1;
        Log.d("ShearchResutsActivity", "list:" + ai);
        list = new ArrayList<>();
        if (list != null) {
            list.clear();
        }
        Map<String, String> map = new HashMap<>();
        map.put("keyword", kewords);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyword", kewords);
        String psmStr = jsonObject.toJSONString();

        HtttpRequest.CreatGetRequst(UrlApi.URL_GTEBRANDBYKYWORD, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("PP_k", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                idsList = new ArrayList<Integer>();
                Log.d("PP_k", response);
                if (!response.contains(MConstant.HTTP404)) {
                    GoodsBrandsData data = JSON.parseObject(response, GoodsBrandsData.class);
                    if( data.getObj()!=null){
                    for (int i = 0; i < data.getObj().size(); i++) {
                        list.add(data.getObj().get(i).getBrandName());
                        idsList.add(data.getObj().get(i).getID());
                    }
                    Log.d("ShearchResutsActivity", "list:" + list);
                }}
            }
        });
    }

    /**
     * 改变table的颜色
     *
     * @param pos
     */
    private void initColor(int pos) {
        List<TextView> tvList = new ArrayList<>();
        tvList.add(defaltSortTv);
        tvList.add(priceSortTv);
        tvList.add(soldSortTv);
        tvList.add(brandSortTv);
        for (int i = 0; i < 4; i++) {
            if (i == pos) {
                tvList.get(i).setTextColor(getResources().getColor(R.color.maincolor));
            } else {
                tvList.get(i).setTextColor(getResources().getColor(R.color.gry));
            }
        }
        if (pos == 0 || pos == 3) {
            soldSortImg.setImageResource(R.drawable.sort2);
            priceSortImg.setImageResource(R.drawable.sort2);
        }
        if (pos == 3) {
            brandSortImg.setImageResource(R.drawable.sort1_orange);
        } else {
            brandSortImg.setImageResource(R.drawable.sort1);
        }
    }

}
