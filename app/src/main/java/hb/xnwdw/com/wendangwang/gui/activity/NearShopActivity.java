package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.NearShopAdpter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.NearShopData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.MyLocationListener;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/15.
 */
public class NearShopActivity extends ActivityBase {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.nearshop_ll)
    LinearLayout nearshopLl;
    @BindView(R.id.nearshop_adrasse_img)
    ImageView nearshopAdrasseImg;
    @BindView(R.id.nearshop_adrasse_tv)
    TextView nearshopAdrasseTv;
    @BindView(R.id.nearshop_refresh_img)
    ImageView nearshopRefreshImg;
    @BindView(R.id.nearshop_refresh_tv)
    TextView nearshopRefreshTv;
    @BindView(R.id.nearshop_recycler)
    RecyclerView nearshopRecycler;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.nearshop_refresh_ll)
    LinearLayout nearshopRefreshLl;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_nearshop;
    }

    private String describe;
    private double latitude;
    private double lontitude;
    private Handler handler;
    private String adrasse = "您所在的位置";
    private LocationClient locationClient = null;
    private LocationClientOption option;
    private NearShopAdpter adpter;
    private int pageIndex = 1;
    private NearShopData data;

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
        title.setText("附近门店");
        locationClient = new LocationClient(this);
        initOption();
        locationClient.setLocOption(option);

        //子线程将经纬度返回给主线并和获取门店信息
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        data=null;
                        pageIndex=1;
                        loadShopInfo(lontitude, latitude);
                        Toast.makeText(NearShopActivity.this, "定位成功！", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        locationClient.start();
                        locationClient.requestLocation();
                        break;
                }
            }
        };


        locationClient.registerLocationListener(new MyLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                super.onReceiveLocation(location);
                adrasse = location.getAddrStr();
                latitude = location.getLatitude();//纬度
                lontitude = location.getLongitude();//经度
                // 子线程跟新UI(textview)
                nearshopAdrasseTv.post(new Runnable() {
                    @Override
                    public void run() {
                        nearshopAdrasseTv.setText(adrasse);

                    }
                });
                //子线程向主线发送消息
                Message msg = new Message();
                msg.what = 1;
                msg.obj = latitude;//传对象，还有arg1、arg2……
                msg.obj = lontitude;//传对象，还有arg1、arg2……
                describe = location.getLocationDescribe();
                handler.sendMessage(msg);
            }
        });

        locationClient.start();//开启定位

    }

    /**
     * 附近门店详情
     *
     * @param lng
     * @param lat
     */
    private   NearShopData nearDatas;
    private void loadShopInfo(double lng, double lat) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("lng", String.valueOf(lng));
        map.put("lat", String.valueOf(lat));
        map.put("pageIndex", pageIndex + "");
        map.put("pageSize", "6");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETNEARSHOPBYLATLNTBYPOS, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("NearShopActivity", response);
                // LogUtils.d("SelfGetChooseShop", response);
                //  LogUtils.d("NearShopActivity", "lontitude:" + lontitude);
                //  LogUtils.d("NearShopActivity", "latitude:" + latitude);
                if (!response.contains(MConstant.HTTP404)) {
                    nearDatas = JSON.parseObject(response, NearShopData.class);
                    if (data == null) {
                        data = nearDatas;
                    } else {
                        data.getObj().addAll(nearDatas.getObj());
                    }
                    // adpter.addData(data.getObj());
                    adpter = new NearShopAdpter(R.layout.item_nearshop_list, data.getObj());
                    nearshopRecycler.setLayoutManager(new MyLinearLayoutManager(getApplicationContext()));
                    adpter.addFooterView(NearShopActivity.this.getLayoutInflater().inflate(R.layout.sub_getmore, (ViewGroup) nearshopRecycler.getParent(), false));
                    nearshopRecycler.setAdapter(adpter);
                    adpter.notifyDataSetChanged();
                    adpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Intent intent = new Intent(getApplicationContext(), ShopActivity.class);
                            intent.putExtra("storeId", data.getObj().get(position).getGUID());
                            intent.putExtra("storeName", data.getObj().get(position).getStoreName());
                            startActivity(intent);
                        }
                    });



                    adpter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pageIndex += 1;
                            Map<String, String> map1 = new HashMap<String, String>();
                            map1.put("lng", String.valueOf(lontitude));
                            map1.put("lat", String.valueOf(latitude));
                            map1.put("pageIndex", pageIndex + "");
                            map1.put("pageSize", "6");
                            HtttpRequest.CreatGetRequst(UrlApi.URL_GETNEARSHOPBYLATLNTBYPOS, map1, new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("NearShopActivity", response);
                                    if (!response.contains(MConstant.HTTP404)) {
                                        NearShopData datas = JSON.parseObject(response, NearShopData.class);
                                        adpter.addData(datas.getObj());
                                        if (datas.getObj().size() == 0) {
                                            TextView tv = (TextView) adpter.getFooterLayout().findViewById(R.id.getmore_tv);
                                            tv.setText("没有更多");
                                        }
                                    }
                                }
                            });
                        }
                    });

//                    adpter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//                        @Override
//                        public void onLoadMoreRequested() {
//                            pageIndex+=1;
//                            Map<String, String> map = new HashMap<String, String>();
//                            map.put("lng", String.valueOf(lontitude));
//                            map.put("lat", String.valueOf(latitude));
//                            map.put("pageIndex", pageIndex + "");
//                            HtttpRequest.CreatGetRequst(UrlApi.URL_GETNEARSHOPBYLATLNTBYPOS, map, new StringCallback() {
//                                        @Override
//                                        public void onError(Call call, Exception e, int id) {
//
//                                        }
//
//                                        @Override
//                                        public void onResponse(String response, int id) {
//                                    if (!response.contains(MConstant.HTTP404)) {
//                                        NearShopData datas = JSON.parseObject(response, NearShopData.class);
//                                        adpter.addData(datas.getObj());
//                                    }
//                                }
//                                    }
//
//                            );
//
//                        }
//                    });
                }

            }
        });
    }

    private boolean isOpenLocation;

    private LocationClientOption initOption() {
        option = new LocationClientOption();
        option.setOpenGps(true);        //是否打开GPS
        option.setIsNeedAddress(true);
        option.setCoorType("bd09ll");       //设置返回值的坐标类型。
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置定位优先级
        option.setProdName("LocationDemo"); //设置产品线名称。强烈建议您使用自定义的产品线名称，方便我们以后为您提供更高效准确的定位服务。
        //  option.setScanSpan(UPDATE_TIME);    //设置定时定位的时间间隔。单位毫秒
        return option;
    }

    @OnClick({R.id.nearshop_refresh_ll, R.id.nearshop_adrasse_img, R.id.nearshop_adrasse_tv, R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nearshop_adrasse_img:
                break;
            case R.id.nearshop_adrasse_tv:
                break;
            case R.id.nearshop_refresh_ll:
                Thread mytime = new Thread(new ThreadShow());
                Toast.makeText(this, "正在定位...", Toast.LENGTH_SHORT).show();

                if (isOpenLocation) {
                    isOpenLocation = false;
                    Log.d("NearShopActivity", "1111111");
                    locationClient.stop();

//                    locationClient.start();//开启定位
                    mytime.start();
                    Animation circle_anim = AnimationUtils.loadAnimation(NearShopActivity.this, R.anim.rounding);
                    LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                    circle_anim.setInterpolator(interpolator);
                    if (circle_anim != null) {
                        nearshopRefreshImg.startAnimation(circle_anim);  //开始动画
                    }
                } else {
                    isOpenLocation = true;
                    Log.d("NearShopActivity", "22222222");
                    Animation circle_anim = AnimationUtils.loadAnimation(NearShopActivity.this, R.anim.rounding);
                    LinearInterpolator interpolator = new LinearInterpolator();  //设置匀速旋转，在xml文件中设置会出现卡顿
                    circle_anim.setInterpolator(interpolator);
                    if (circle_anim != null) {
                        nearshopRefreshImg.startAnimation(circle_anim);  //开始动画
                    }
//                    locationClient.start();//开启定位
                    mytime.start();
                }
//                locationClient = new LocationClient(this);
//                initOption();
//                locationClient.setLocOption(option);
//                locationClient.registerLocationListener(new MyLocationListener() {
//                    @Override
//                    public void onReceiveLocation(BDLocation location) {
//                        super.onReceiveLocation(location);
//                        adrasse = location.getAddrStr();
//                        latitude = location.getLatitude();//纬度
//                        lontitude = location.getLongitude();//经度
//                        // 子线程跟新UI(textview)
//                        nearshopAdrasseTv.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                nearshopAdrasseTv.setText(adrasse);
//                            }
//                        });
//                        //子线程向主线发送消息
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.obj = latitude;//传对象，还有arg1、arg2……
//                        msg.obj = lontitude;//传对象，还有arg1、arg2……
//                        handler.sendMessage(msg);
//                    }
//                });
//                locationClient.start();//开启定位
//
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    // 线程类
    class ThreadShow implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                locationClient.stop();
                Thread.sleep(2000);
                Message msg = new Message();
                msg.what = 2;
                handler.sendMessage(msg);
                // System.out.println("send...");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("thread error...");
            }
        }

    }
}