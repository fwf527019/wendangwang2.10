package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jaeger.library.StatusBarUtil;
import com.qiyukf.nimlib.sdk.NimIntent;
import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentClassfiy;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentMain11;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentMyself;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentSaoyisao;

import hb.xnwdw.com.wendangwang.gui.fragment.FragmentShoppingCart1;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentShoppingCartWeb;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AppVersionData;
import hb.xnwdw.com.wendangwang.netdata.entity.LoginData;
import hb.xnwdw.com.wendangwang.netdata.entity.WhatIsShow;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.base.BaseAndroid;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.base.BaseConfig;
import hb.xnwdw.com.wendangwang.utils.AndroidBase.utils.UpdateManager;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.StatusBarCompat;
import okhttp3.Call;

public class MainPagerActivity extends ActivityBase {
    LinearLayout activityMainll;
    private static FragmentTabHost fragmentTabHost;
    private String texts[] = {"首页", "分类", "", "购物车", "我的稳当"};
    private int imageButton[] = {R.drawable.tab_home_slecter, R.drawable.tab_classfy_slecter, R.drawable.sao1,
            R.drawable.tab_shopcar_slecter, R.drawable.tab_my_slecter};
    private Class fragmentArray[] = {FragmentMain11.class, FragmentClassfiy.class, FragmentSaoyisao.class, FragmentShoppingCart1.class, FragmentMyself.class};
    public static boolean isForeground = false;
    private int fr = -1;
    public static int startNum = 0;
    private Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadwhatcanShow();
        EventBus.getDefault().register(this);
        startNum += 1;
        Log.d("MainPagerActivity", "startNum:" + startNum);
        /**
         * 版本检测
         */
        if (startNum == 1) {
            cheakAppVersion(new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.d("MainPagerActivity", "e:" + e);

                }

                @Override
                public void onResponse(String response, int id) {
                    Log.d("MainPagerActivity", response);
                    if (response != null && !response.contains(MConstant.HTTP404)) {
                        AppVersionData appversion = JSON.parseObject(response, AppVersionData.class);
                        if (appversion.getObj() != null) {
                            int VersionCode = appversion.getObj().getVersionNum();
                            String appUrl = UrlApi.SERVER_IP + "/AppDownload/DownloadAndroid";
                            String upContent = appversion.getObj().getUpContent();
                            boolean IsConstraint = appversion.getObj().isIsConstraint();
                            BaseAndroid.init(new BaseConfig());
                            BaseAndroid.checkUpdate(MainPagerActivity.this, VersionCode, appUrl, upContent, IsConstraint);
                        }

                    }
                }
            });
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        }
        fragmentTabHost = (FragmentTabHost) findViewById(R.id.mian_fragmttabhost);
        fragmentTabHost.setVisibility(View.VISIBLE);
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.reltabacontent);
        for (int i = 0; i < texts.length; i++) {
            TabHost.TabSpec tabspec = fragmentTabHost.newTabSpec(texts[i]).setIndicator(getView(i));
            fragmentTabHost.addTab(tabspec, fragmentArray[i], null);
            //fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.tab_home_slecter);
        }
        //跳转
        intent  = getIntent();
        fr = intent.getIntExtra("fr", -1);
        if (fr == 3) {
            fragmentTabHost.setCurrentTab(3);
        }
        if (fr == 4) {
            fragmentTabHost.setCurrentTab(4);
        }
        if (fr == 0) {
            fragmentTabHost.setCurrentTab(0);
        }
        if (fr == 1) {
            fragmentTabHost.setCurrentTab(1);
        }
        if (intent.hasExtra(NimIntent.EXTRA_NOTIFY_CONTENT)) {
            // 打开客服窗口
            ConsultSource source = new ConsultSource("url", "消息推送", "custom information string");
            Unicorn.openServiceActivity(MainPagerActivity.this, "",source);
            // 最好将intent清掉，以免从堆栈恢复时又打开客服窗口
            setIntent(new Intent());
        }

    }

    private void cheakAppVersion(StringCallback callback) {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "Android");
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetAppVersion, map, callback);

    }

    private View getView(int i) {
        View view = View.inflate(MainPagerActivity.this, R.layout.maintabcontent, null);
        //取得布局对象
        ImageView imageView = (ImageView) view.findViewById(R.id.maintab_image);
        TextView textView = (TextView) view.findViewById(R.id.maintab_text);
        //设置图标
        imageView.setImageResource(imageButton[i]);
        //设置标题
        textView.setText(texts[i]);
        return view;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        //去除状态栏高度
        activityMainll = (LinearLayout) findViewById(R.id.activity_main);
//        if (Build.VERSION.SDK_INT >= 19) {
//            activityMainll.setPadding(0, getStatusHeight(), 0, 0);
//        }

//        Random random = new Random();
//        int color = 0xff000000 | random.nextInt(0xffffff);
//        StatusBarUtil.setTranslucentForImageViewInFragment(MainPagerActivity.this, null);
    }

    @Override
    protected void initDatas() {

    }

    private long exitTime = 0;
    private Fragment carw;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            int fg = fragmentTabHost.getCurrentTab();
            Fragment from = getSupportFragmentManager().findFragmentById(R.id.reltabacontent);
//            Log.d("MainPagerActivity", "fragmentTabHost.getAccessibilityClassName():" + fragmentTabHost.getAccessibilityClassName());

            if (fg == 8) {
              //  ((FragmentShoppingCartWeb) from).onKeyDown(keyCode, event);

            } else {
                if ((System.currentTimeMillis() - exitTime) > 2000)  //System.currentTimeMillis()无论何时调用，肯定大于2000
                {
                    Object mHelperUtils;
                    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    exitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        isForeground = true;
        loadwhatcanShow();
        intent=getIntent();
        int id = intent.getIntExtra("fr", 5);
       Log.d("MainPagerActivity", "id:" + id);
        if(intent!=null){
            if (id == 3) {
                fragmentTabHost.setCurrentTab(3);
            }
            if (id == 4) {
                fragmentTabHost.setCurrentTab(4);
            }
            if (id == 1) {
                fragmentTabHost.setCurrentTab(1);
            }
            if (id == 0) {
                fragmentTabHost.setCurrentTab(0);
            }
      setIntent(new Intent());
        }

        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    //隐藏tab
    public static void hidTabHost() {
        fragmentTabHost.setVisibility(View.GONE);
    }

    //显示tab
    public static void seeTabHost() {
        fragmentTabHost.setVisibility(View.VISIBLE);
    }

    public static void GotoFragment(int fr) {
        fragmentTabHost.setCurrentTab(fr);
    }

    /**
     * 某些楼层信息显示不显示
     */
    private void loadwhatcanShow() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetIndexFloorCfg, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MainPagerActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MainPagerActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    WhatIsShow data = JSON.parseObject(response, WhatIsShow.class);
                    MConstant.GoodsDetailBrandDispaly = data.getObj().getGoodsDetailBrandDispaly();
                    MConstant.newGoodsDisplay = data.getObj().getNewGoodsDisplay();
                    MConstant.seckillDisplay=data.getObj().getSeckillDisplay();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe (threadMode= ThreadMode.MAIN)
    public void getFr(String fr){
        if(fr.equals("shopingcar")){
            fragmentTabHost.setCurrentTab(3);
        }
    }

    /**
     *
     * 重写此方法，加上setIntent(intent);否则在onResume里面得不到intent
     * @param intent intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

}
