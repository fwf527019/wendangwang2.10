package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.SelfGetChooseShopPopAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.FullyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.NearShopData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.MyLocationListener;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/6.
 */

public class SelfGetChooseShop extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.selfget_mapview)
    MapView selfgetMapview;
    @BindView(R.id.selget_inputadrass)
    TextView selgetInputadrass;
    @BindView(R.id.selget_shop_name)
    TextView selgetShopName;
    @BindView(R.id.selget_shop_adrass)
    TextView selgetShopAdrass;
    @BindView(R.id.selget_shop_phone)
    TextView selgetShopPhone;
    @BindView(R.id.selfgetchoose_nema)
    EditText selfgetchooseNema;
    @BindView(R.id.selfgetchoose_phone)
    EditText selfgetchoosePhone;
    @BindView(R.id.selfgetchoose_btn)
    TextView selfgetchooseBtn;
    @BindView(R.id.selfget_scroll)
    ScrollView selfgetScroll;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    private LocationClient locationClient = null;
    private double latitude;
    private double lontitude;
    private String adrasse = "您所在的位置";
    private Handler handler;
    private LatLng latlng;

    private SelfGetChooseShopPopAdapter adpter;
    private NearShopData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_selfgetchooseshop;
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
        title.setText("自提门店选择");
        Intent intent=getIntent();
        shoppeId= intent.getStringExtra("shoppeId");
        shoppeId= intent.getStringExtra("shoppeId");
        locationClient = new LocationClient(this);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        loadNearStorDatas(lontitude, latitude);
                }
            }
        };

        //设置定位条件
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);        //是否打开GPS
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");       //设置返回值的坐标类型。
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置定位优先级
        option.setProdName("LocationDemo"); //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        //  option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔。单位毫秒
        locationClient.setLocOption(option);
        locationClient.registerLocationListener(new MyLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                super.onReceiveLocation(location);
                adrasse = location.getAddrStr();
                latitude = location.getLatitude();
                lontitude = location.getLongitude();
                Message message = new Message();
                message.what = 1;
                message.obj = latitude;
                message.obj = lontitude;
                handler.sendMessage(message);

                // 子线程跟新UI(mapview)
                selfgetMapview.post(new Runnable() {
                    @Override
                    public void run() {
                        LatLng cenpt = new LatLng(latitude, lontitude);
                        upDataMap(cenpt);
                    }
                });
            }
        });
        locationClient.start();

    }

    /**
     * 获取门店默认列表
     *
     * @param lng
     * @param lat
     */
    private void loadNearStorDatas(final double lng, final double lat) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lng", String.valueOf(lng));
        jsonObject.put("lat", String.valueOf(lat));
        //   map.put("pageIndex", String.valueOf(1));
        jsonObject.put("Range", String.valueOf(2500));
        jsonObject.put("MaxCount", String.valueOf(50));
        HtttpRequest.CreatPostRequst(UrlApi.URL_GETNEARSHOPBYLATLNT, jsonObject.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("SelfGetChooseShop", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("SelfGetChooseShop", response);

                //定义Maker坐标点
                LatLng point = new LatLng(lat, lng);
                //构建Marker图标
                BitmapDescriptor bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_marka);
                //构建MarkerOption，用于在地图上添加Marker
                OverlayOptions option = new MarkerOptions()
                        .position(point)
                        .icon(bitmap);
                //在地图上添加Marker，并显示
                selfgetMapview.getMap().addOverlay(option);
                if(!response.contains(MConstant.HTTP404)){
                data = JSON.parseObject(response, NearShopData.class);
                if (data != null && data.getObj().size() != 0) {
                    selgetShopName.setText(data.getObj().get(0).getStoreName());
                    selgetShopPhone.setText(data.getObj().get(0).getPhoneNumber());
                    selgetShopAdrass.setText(data.getObj().get(0).getStoreAddress());
                }

                int siz = data.getObj().size();
                for (int i = 0; i < siz; i++) {
                    addMarker(data.getObj().get(i).getMapX(), data.getObj().get(i).getMapY());
                }
            }}
        });

    }

    private void addMarker(double lng, double lat) {

        //定义Maker坐标点
        LatLng point = new LatLng(lat, lng);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_marka);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        selfgetMapview.getMap().addOverlay(option);

    }

    /**
     * 按钮监听
     *
     * @param view
     */
    @OnClick({R.id.back, R.id.selget_inputadrass, R.id.selfgetchoose_btn, R.id.selfgetchoose_nema})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.selget_inputadrass:


               showPopwindow();

                break;
            case R.id.selfgetchoose_btn:
                if (shoppeId == null || shoppeName == null) {
                    Toast.makeText(this, "请选择自提门店", Toast.LENGTH_SHORT).show();
                } else if (selfgetchooseNema.getText().toString() == null || selfgetchooseNema.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入收货人姓名", Toast.LENGTH_SHORT).show();
                } else if (!Utils.isPhone(selfgetchoosePhone.getText().toString())) {
                    Toast.makeText(this, "请输入收货人电话", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("ShoppeId", shoppeId);
                    intent.putExtra("ShoppeName", shoppeName);
                    intent.putExtra("RecName", selfgetchooseNema.getText().toString());
                    intent.putExtra("PhoneNum", selfgetchoosePhone.getText().toString());
                    intent.putExtra("adrass", selgetShopAdrass.getText());
                    setResult(1, intent);
                    finish();
                }

                break;
        }
    }


    //   private PopupWindow mPopuwidow;
    private String shoppeId;
    private String shoppeName, RecName, PhoneNum;

    /**
     * 弹出popwindow
     */

    private void showPopwindow() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindowchooseadrasss, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        // layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);

        final RecyclerView mrecycler = (RecyclerView) contentView.findViewById(R.id.choosestor_recycler);
        Button btn = (Button) contentView.findViewById(R.id.btn_choose_shop);


        final EditText shearchEdit = (EditText) contentView.findViewById(R.id.selgetpopu_inputadrass);

        shearchEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_SEARCH) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                    if (mPoiSearch != null) {
//                        mPoiSearch.destroy();
//                    }
                    //                  shearch(shearchEdit.getText().toString());
                    shearchStor(shearchEdit.getText().toString(), new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            LogUtils.d("SelfGetChooseShop", "e:" + e);
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            LogUtils.d("SelfGetChooseShop", response);
                            data = JSON.parseObject(response, NearShopData.class);
                            if (data != null) {
                                adpter = new SelfGetChooseShopPopAdapter(R.layout.item_selfchoosestorlist, data.getObj());
                                mrecycler.setAdapter(adpter);

                                adpter.setMyListerner(new SelfGetChooseShopPopAdapter.MyListerner() {
                                    //回调radiobutton 状态
                                    @Override
                                    public void OnCheakedBoxClickListener(View v, boolean isCheached, int pos) {
                                        if (isCheached) {
                                            selgetShopName.setText(data.getObj().get(pos).getStoreName());
                                            selgetShopPhone.setText(data.getObj().get(pos).getPhoneNumber());
                                            selgetShopAdrass.setText(data.getObj().get(pos).getStoreAddress());
                                            shoppeId = data.getObj().get(pos).getID() + "";
                                            shoppeName = data.getObj().get(pos).getStoreName();
                                        }
                                    }
                                });
                            }
                        }
                    });
                }

                return false;
            }


        });


        mrecycler.setLayoutManager(new FullyLinearLayoutManager(getApplicationContext()));
        final List<NearShopData.ObjBean> mList = new ArrayList<>();
        if (data != null) {
            adpter = new SelfGetChooseShopPopAdapter(R.layout.item_selfchoosestorlist, data.getObj());
            mrecycler.setAdapter(adpter);
            adpter.notifyDataSetChanged();
            adpter.setMyListerner(new SelfGetChooseShopPopAdapter.MyListerner() {
                //回调radiobutton 状态
                @Override
                public void OnCheakedBoxClickListener(View v, boolean isCheached, int pos) {
                    if (isCheached) {

                        for (int i = 0; i <data.getObj().size() ; i++) {
                            if(i==pos){
                                data.getObj().get(i).setSlecte(true);
                            }else {
                                data.getObj().get(i).setSlecte(false);
                            }
                        }
                        selgetShopName.setText(data.getObj().get(pos).getStoreName());
                        selgetShopPhone.setText(data.getObj().get(pos).getPhoneNumber());
                        selgetShopAdrass.setText(data.getObj().get(pos).getStoreAddress());
                        shoppeId = data.getObj().get(pos).getID() + "";
                        shoppeName = data.getObj().get(pos).getStoreName();
                        if (mrecycler.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (mrecycler.isComputingLayout() == false)) {
                    //        adpter.notifyDataSetChanged();
                        }

                    }
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });


        bottomDialog.show();
    }

    /**
     * 根据门店关键字收索
     *
     * @param keyword
     * @param callback
     */
    private void shearchStor(String keyword, StringCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetStoreListByKeyword, map, callback);

    }


    @Override
    protected void onPause() {
        super.onPause();
        selfgetMapview.onPause();
    }

    @Override
    protected void onResume() {
        super.onRestart();
        selfgetMapview.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selfgetMapview.onDestroy();

    }


    //   PoiSearch mPoiSearch = PoiSearch.newInstance();

//    /**
//     * 百度地图POI检索获取地理经纬度
//     * @param s
//     */
//    private void shearch(String s) {
//
//        OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
//            public void onGetPoiResult(PoiResult poiResult) {
//                //获取POI检索结果
//                LogUtils.d("SelfGetChooseShop", "result.getTotalPoiNum():" + poiResult.getTotalPoiNum());
//                LogUtils.d("SelfGetChooseShop", "result.describeContents():" + poiResult.describeContents());
//                if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {// 没有找到检索结果
//                    Toast.makeText(getApplicationContext(), "未找到结果",
//                            Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {// 检索结果正常返回
//                    int totalPage = poiResult.getTotalPageNum();// 获取总分页数
//                    Toast.makeText(
//                            getApplicationContext(),
//                            "总共查到" + poiResult.getTotalPoiNum() + "个兴趣点, 分为"
//                                    + totalPage + "页", Toast.LENGTH_SHORT).show();
//
//                    if (totalPage != 0) {
//                        latlng = poiResult.getAllPoi().get(0).location;
//                        LogUtils.d("SelfGetChooseShop", "latlng:" + latlng);
//                        upDataMap(latlng);
//                        loadNearStorDatas(latlng.longitude, latlng.latitude);
//                        adpter.notifyDataSetChanged();
//
//                    }
//                }
//
//
//            }
//
//            public void onGetPoiDetailResult(PoiDetailResult result) {
//                //获取Place详情页检索结果
//                LogUtils.d("SelfGetChooseShop", result.getAddress());
//
//            }
//
//            @Override
//            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
//
//            }
//        };
//        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//
//        mPoiSearch.searchInCity((new PoiCitySearchOption())
//                .city("西宁市")
//                .keyword(s)
//                .pageNum(2));
//    }

    private void upDataMap(LatLng cenpt) {
        BaiduMap mBaidumap = selfgetMapview.getMap();
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();

    }
}

