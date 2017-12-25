package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;

/**
 * Created by Administrator on 2017/12/12.
 */
public class MapActivity extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.to_shop_map)
    MapView toShopMap;
    @BindView(R.id.adrasse)
    TextView adrasse;
    @BindView(R.id.to_shop_navigation)
    Button toShopNavigation;
    private double lat, lot, mlat, mlot;
    private String shop_name, storeAddress;
    BaiduMap baiduMap;
    List<String> maps;
    public static final String ROUTE_PLAN_NODE = "routePlanNode";

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_map;
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
        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat", 0);
        lot = intent.getDoubleExtra("lot", 0);
        mlat = intent.getDoubleExtra("mlat", 0);
        mlot = intent.getDoubleExtra("mlot", 0);
        shop_name = intent.getStringExtra("name");
        title.setText(shop_name);
        storeAddress = intent.getStringExtra("storeAddress");
        adrasse.setText(storeAddress);
        baiduMap = toShopMap.getMap();
        maps = cheakMapOfPhone();
        initMap();
    }

    @OnClick({R.id.back, R.id.adrasse, R.id.to_shop_navigation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.adrasse:
                startNavigation(maps);
                break;
            case R.id.to_shop_navigation:
                startNavigation(maps);
                break;
        }
    }


    private void initMap() {
        //设定中心点坐标
        LatLng cenpt = new LatLng(lat, lot);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(18)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化

        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        toShopMap.getMap().setMapStatus(mMapStatusUpdate);


        //定义Maker坐标点
        LatLng point = new LatLng(lat, lot);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.red);
       Bitmap bm= setImgSize(bitmap.getBitmap(),dip2px(this,20),dip2px(this,25));
        BitmapDescriptor  newBm=  BitmapDescriptorFactory.fromBitmap(bm);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(newBm);
        //在地图上添加Marker，并显示
        toShopMap.getMap().addOverlay(option);


        //文字，在地图中也是一种覆盖物，开发者可利用相关的接口，快速实现在地图上书写文字的需求。实现方式如下：
        //定义文字所显示的坐标点

        LatLng llText = new LatLng(lat, lot);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .fontSize(14)
                .fontColor(0xFFFF6C00)
                .text(shop_name)
                .rotate(0)
                .position(llText);
        //在地图上添加该文字对象并显示
        toShopMap.getMap().addOverlay(textOption);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        toShopMap.onDestroy();
    }


    /**
     * 检测手机上的地图软件
     */
    private List<String> cheakMapOfPhone() {
        List<String> phoneMaps = new ArrayList<>();

        if (isAvilible(MapActivity.this, "com.baidu.BaiduMap")) {
            phoneMaps.add("百度地图导航");
        }
        if (isAvilible(MapActivity.this, "com.autonavi.minimap")) {
            phoneMaps.add("高德地图导航");

        }
        phoneMaps.add("使用网页地图导航");
        return phoneMaps;
    }

    /**
     * 点击开启导航
     */
    private PopupWindow popupWindow;

    private void startNavigation(final List<String> maps) {
        View contentView = LayoutInflater.from(this).inflate(R.layout.maps, null);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(contentView);
        popupWindow.setWidth(dip2px(this, 150));
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置背景透明才能显示
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);

        View perrent = LayoutInflater.from(this).inflate(R.layout.activit_map, null);
        RecyclerView recyclerview = (RecyclerView) contentView.findViewById(R.id.maplist);
        recyclerview.setLayoutManager(new MyLinearLayoutManager(this));
        MapNameAdapter adapter = new MapNameAdapter(R.layout.item_maplist, maps);
        recyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (maps.get(position).equals("百度地图导航")) {
                    // 调起自定义打点
                    Intent i1 = new Intent();
                    i1.setData(Uri.parse("baidumap://map/marker?location=" + lat + "," + lot + "&title=" + shop_name + "&content=" + storeAddress + "=on"));
                    startActivity(i1);
                    popupWindow.dismiss();

                } else if (maps.get(position).equals("高德地图导航")) {
                    String uri = getGdMapUri("wdw", mlat + "", mlot + "", "我的位置",   bd_decrypt(lot,lat)[1] + "", bd_decrypt(lot,lat)[0] + "", shop_name);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.autonavi.minimap");
                    intent.setData(Uri.parse(uri));
                    startActivity(intent); //启动调用
                    popupWindow.dismiss();

                } else {
                    Intent intent = new Intent(MapActivity.this, Nav_mapActivity.class);
                    intent.putExtra("lat", lat);
                    intent.putExtra("lot", lot);
                    intent.putExtra("mlat", mlat);
                    intent.putExtra("mlot", mlot);
                    intent.putExtra("name", shop_name);
                    intent.putExtra("storeAddress", storeAddress);
                    String url =
                            "http://api.map.baidu.com/marker?location=40.05740665572,116.2964407172&title=Marker&content=makeamarker&traffic=on";
                    intent.putExtra("url", url);
                    startActivity(intent);
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow.showAtLocation(perrent, Gravity.BOTTOM | Gravity.END, -10, -10);
    }

    private class MapNameAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


        public MapNameAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.tv1, item);
        }
    }

    public static String getGdMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname) {
        String newUri = "androidamap://navi?sourceApplication=%1$s&poiname=%2$s&lat=%3$s&lon=%4$s&dev=1&style=2";
        return String.format(newUri, appName, dname, dlat, dlon);
    }


    /**
     * 百度地图坐标转高德地图
     *
     * @param bd_lon 经度（值较大）
     * @param bd_lat 纬度（值较小）
     */

    private double[] bd_decrypt(double bd_lon, double bd_lat) {

        double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

        double x = bd_lon - 0.0065, y = bd_lat - 0.006;

        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);

        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);

        double[] bd_decrypt = new double[2];
        bd_decrypt[0] = z * Math.cos(theta);
        bd_decrypt[1] = z * Math.sin(theta);
        return bd_decrypt;

    }

    public Bitmap setImgSize(Bitmap bm, int newWidth ,int newHeight){
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }
}
