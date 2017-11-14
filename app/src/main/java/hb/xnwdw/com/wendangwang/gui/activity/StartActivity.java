package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.LoginData;
import hb.xnwdw.com.wendangwang.utils.AppVerison;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/20.
 */

public class StartActivity extends AppCompatActivity {
    private String pasw;
    private String phoneNum;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
         pasw = sp.getString("userPsw", null);
         phoneNum = sp.getString("userName", null);
        MobclickAgent.setDebugMode( true );
        setContentView(R.layout.activity_start);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gotoLogn();

            }


        }.start();

    }

    private void gotoMainPager() {
        startActivity(new Intent(this, MainPagerActivity.class));
    }

    private void gotoGuide() {
        startActivity(new Intent(this, GuideActivity.class));
    }


    /**
     * 判断当前版本是不是第一次进入应用
     *
     * @return
     */
    private boolean isVersionFirst() {
        SharedPreferences sp = getSharedPreferences("app_version", MODE_PRIVATE);
        String version = sp.getString("version", null);
        //获取到当前应用版本名字
        String versionName = AppVerison.getAppVersionName(this);
        if (versionName.equals(version)) {
            return false;
        }

        sp.edit().putString("version", versionName).commit();
        return true;

    }


    private boolean isExitByUser;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isExitByUser = true;
    }

    private void gotoLogn() {

        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("pwd", pasw);
        object.put("dataSource", "APP");
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequstNoToken(UrlApi.UrL_USER_LOIN, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("LognActivity", "e:" + e);

                if (isExitByUser) {
                    return;
                }
                if (isVersionFirst()) {
                    gotoGuide();
                    finish();
                } else {
                    gotoMainPager();
                    finish();
                }
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("StartActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    JSONObject jsonObject = JSON.parseObject(response);
                    if ((jsonObject.get("obj") != null) && (jsonObject.get("obj").toString() != null)) {
                        LogUtils.d("StartActivity", jsonObject.get("obj").toString());
                        LoginData data = JSON.parseObject(response, LoginData.class);
                        WDWApp.setUserToken(data.getObj());
                        SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userToken", data.getObj());
                        editor.commit();
                        MobclickAgent.onProfileSignIn(phoneNum);
                    } else {


                    }
                }
                if (isExitByUser) {
                    return;
                }
                if (isVersionFirst()) {
                    gotoGuide();
                    finish();
                } else {
                    gotoMainPager();
                    finish();
                }
            }
        });

    }


    /**
     * 判断是否有网络
     */
    private boolean isNetWork() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        return connectivityManager != null
                && connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isAvailable();
    }


}