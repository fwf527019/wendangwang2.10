package hb.xnwdw.com.wendangwang;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.qiyukf.unicorn.api.SavePowerConfig;
import com.qiyukf.unicorn.api.StatusBarNotificationConfig;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.YSFOptions;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.WeiBo.WConstants;
import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.LoginData;
import hb.xnwdw.com.wendangwang.utils.CrashHandler;
import hb.xnwdw.com.wendangwang.utils.FrescoImageLoader;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.ImageLoaderConfig;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;

import hb.xnwdw.com.wendangwang.utils.Base64;

import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.StringUtils;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.OkHttpClient;


/**
 * Created by Administrator on 2017/2/20.
 */

public class WDWApp extends MultiDexApplication {
    private final static float HEAP_UTILIZATION = 0.75f;
    private final static int MIN_HEAP_SIZE = 6 * 1024 * 1024;
    public static String MenberId = "0";
    public static String userToken = null;
    private List<Activity> oList;//用于存放所有启动的Activity的集合
    private String phoneNum;
    private String pasw;
    private SharedPreferences sp;
    private boolean isDebug = false;
    public static WDWApp instance;

    public static String getMenberId() {
        return MenberId;
    }

    public static IWXAPI mApi;

    public static String getUserToken() {
        return userToken;
    }

    private static final String IMAGE_PIPELINE_CACHE_DIR = "image_cache";
    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "image_small_cache";

    public static void setUserToken(String userToken) {
        WDWApp.userToken = userToken;
    }

    private static final int MAX_DISK_SMALL_CACHE_SIZE = 10 * ByteConstants.MB;
    private static final int MAX_DISK_SMALL_ONLOWDISKSPACE_CACHE_SIZE = 5 * ByteConstants.MB;
    public static Context context;

    public static String payOrderNum = "";

    public static void setMenberId(String menberId) {
        try {
            WDWApp.MenberId = Base64.encode(StringUtils.encrypt(menberId, "wdw2017&"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WDWApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        //      initWebSDK();

        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(context, "0173422bf6", isDebug, strategy);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);
        sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
        ImagePipelineConfig config = ImageLoaderConfig.getImagePipelineConfig(this);
        userToken = sp.getString("userToken", null);
        phoneNum = sp.getString("userName", null);
        //初始化极光推送
        JPushInterface.setDebugMode(false);//如果时正式版就改成false
        JPushInterface.init(this);
        JPushInterface.setAliasAndTags(getApplicationContext(),
                phoneNum, null, mAliasCallback);
        oList = new ArrayList<Activity>();
        instance = this;
        //初始化图片加载框架
        Fresco.initialize(this, config);
        //  FreelineCore.init(this,this);

        //网络框架
        initHttp();
        //初始化百度地图
        SDKInitializer.initialize(this);
        //微信
        //   registToWX();
        //网易七鱼
        Unicorn.init(this, "467d5c76573eb31f20da0dba3af25876 ", options(), new FrescoImageLoader(getApplicationContext()));
        // 如果返回值为null，则全部使用默认参数。
    }

    /***
     * 初始化网络框架
     */
    private void initHttp() {
        try {
            InputStream in = getAssets().open("qhwendangwang.com.crt");
          HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, in, null);
         //  HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .connectTimeout(20000L, TimeUnit.MILLISECONDS)
                    .cache(new Cache(instance.getExternalCacheDir(), 1024 * 1024 * 10))
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void registToWX() {
        //AppConst.WEIXIN.APP_ID是指你应用在微信开放平台上的AppID，记得替换。
        mApi = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID, false);
        // 将该app注册到微信
        mApi.registerApp(WXConstants.APP_ID);
    }


    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success极光推送别名设置成功";
                    Log.d("WDWApp_alias", alias);
                    LogUtils.d("MainPagerActivity", logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    LogUtils.d("MainPagerActivity", logs);
                    break;
            }
        }
    };


    private YSFOptions options() {
        YSFOptions options = new YSFOptions();
        options.statusBarNotificationConfig = new StatusBarNotificationConfig();
        options.savePowerConfig = new SavePowerConfig();
        options.statusBarNotificationConfig.notificationEntrance = MainPagerActivity.class;

        return options;
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    //新浪微博初始化，对应的参数分别是app_key,回调地址，和权限
    private void initWebSDK() {

    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */
    private static Map<String, Activity> destoryMap = new HashMap<>();

    public static void addDestoryActivity(Activity activity, String activityName) {
        destoryMap.put(activityName, activity);
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            destoryMap.get(key).finish();
        }
    }
}
