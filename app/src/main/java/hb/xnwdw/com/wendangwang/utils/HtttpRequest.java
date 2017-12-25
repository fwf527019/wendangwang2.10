package hb.xnwdw.com.wendangwang.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/5/27.
 */

public class HtttpRequest {

    public HtttpRequest(LoginStatu loginStatu) {
        this.loginStatu = loginStatu;
    }

    /**
     * get 请求
     *
     * @param map
     * @param callback
     */
    public static void CreatGetRequst(String url, Map<String, String> map, StringCallback callback) {
        Map<String, String> map2 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map2.put("timestamp",time);
        map2.put("token", UrlUtils.getMd5(time));
        if (WDWApp.getUserToken() != null) {

            map2.put("userToken", WDWApp.getUserToken());
        }
        //遍历map中的值

        for (String value : map2.values()) {

            Log.d("HtttpRequest", ("Value = " + value));

        }
        OkHttpUtils
                .get()
                .headers(map2)
                .params(map)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * post 请求
     *
     * @param st
     * @param callback
     */
    public static void CreatPostRequstNoToken(String url, String st, StringCallback callback) {
        String time= UrlUtils.getTime();
        OkHttpUtils
                .postString()
                .addHeader("timestamp", time)
                .addHeader("token", UrlUtils.getMd5(time))
                .content(st)
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(callback);
    }

    /**
     * post 请求
     *
     * @param st
     * @param callback
     */
    public static void CreatPostRequst(String url, String st, StringCallback callback) {
        Map<String, String> map2 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map2.put("timestamp",time);
        map2.put("token", UrlUtils.getMd5(time));
        if (WDWApp.getUserToken() != null) {
            map2.put("userToken", WDWApp.getUserToken());
        }
        OkHttpUtils
                .postString()
                .headers(map2)
                .content(st)
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .build()
                .execute(callback);
    }

    /**
     * get 请求
     *
     * @param map
     * @param callback
     */
    public static void CreatGetRequstNoToken(String url, Map<String, String> map, StringCallback callback) {
        Map<String, String> map2 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map2.put("timestamp",time);
        map2.put("token", UrlUtils.getMd5(time));

        OkHttpUtils
                .get()
                .headers(map2)
                .params(map)
                .url(url)
                .build()
                .execute(callback);
    }

    /**
     * 验证usertoken是否失效Get请求
     */
    public static void CheackIsLoginGet(final Context context, final String url, final Map<String, String> map, final StringCallback callback) {
        Map<String, String> map3 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map3.put("timestamp",time);
        map3.put("token", UrlUtils.getMd5(time));
        if (WDWApp.getUserToken() != null) {
            map3.put("userToken", WDWApp.getUserToken());
        }
        OkHttpUtils
                .get()
                .headers(map3)
                .url(UrlApi.URL_CHEAKISLOGIN)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("HtttpRequest", "e:" + e);
                        Toast.makeText(context, "e", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && (!response.contains(MConstant.HTTP404))) {
                            if (JSONObject.parseObject(response).get("dataStatus") != null && Integer.parseInt(JSONObject.parseObject(response).get("dataStatus").toString()) > 0) {
                                //是登录状态

                                Map<String, String> map2 = new HashMap<String, String>();
                                map2.put("timestamp", UrlUtils.getTime());
                                map2.put("token", UrlUtils.getMd5(UrlUtils.getTime()));
                                if (WDWApp.getUserToken() != null) {

                                    map2.put("userToken", WDWApp.getUserToken());
                                }
                                //遍历map中的值

                                for (String value : map2.values()) {

                                    Log.d("HtttpRequest", ("Value = " + value));

                                }
                                OkHttpUtils
                                        .get()
                                        .headers(map2)
                                        .params(map)
                                        .url(url)
                                        .build()
                                        .execute(callback);

                            } else {
                                if(WDWApp.getUserToken()!=null){
                                    Toast.makeText(context, "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
                                    WDWApp.setUserToken(null);
                                }else {
                                    Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                                }
                                context.startActivity(new Intent(context, LognActivity.class));
                            }

                        }

                    }
                });

    }

    /**
     * 验证UseerToken是否失效 POST
     */
    public static void CheackIsLoginPOST(final Context context, final String url, final String st, final StringCallback callback) {

        Map<String, String> map3 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map3.put("timestamp",time);
        map3.put("token", UrlUtils.getMd5(time));
        if (WDWApp.getUserToken() != null) {
            map3.put("userToken", WDWApp.getUserToken());
        }
        OkHttpUtils
                .get()
                .headers(map3)
                .url(UrlApi.URL_CHEAKISLOGIN)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("HtttpRequest", "e:" + e);
                        Toast.makeText(context, "e", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && (!response.contains(MConstant.HTTP404))) {
                            if (JSONObject.parseObject(response).get("dataStatus") != null && Integer.parseInt(JSONObject.parseObject(response).get("dataStatus").toString()) > 0) {
                                CreatPostRequst(url, st, callback);

                            } else {
                                if(WDWApp.getUserToken()!=null){
                                    Toast.makeText(context, "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
                                    WDWApp.setUserToken(null);
                                }else {
                                    Toast.makeText(context, "请登录", Toast.LENGTH_SHORT).show();
                                }


                                context.startActivity(new Intent(context, LognActivity.class));
                            }

                        }
                    }
                });
    }

    /**
     * 验证是否登录状态提供回调接口
     * @param what
     */
    public void isLogin(final String what) {
        Map<String, String> map3 = new HashMap<String, String>();
        String time= UrlUtils.getTime();
        map3.put("timestamp",time);
        map3.put("token", UrlUtils.getMd5(time));
        if (WDWApp.getUserToken() != null) {
            map3.put("userToken", WDWApp.getUserToken());
        }

        OkHttpUtils
                .get()
                .headers(map3)
                .url(UrlApi.URL_CHEAKISLOGIN)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && (!response.toString().contains(MConstant.HTTP404))) {
                            if (Integer.parseInt(JSONObject.parseObject(response.toString()).get("dataStatus").toString()) <= 0) {
                                loginStatu.noLofin();
                            } else {
                                loginStatu.isLogin(what);
                            }

                        }
                    }
                });

    }


    private LoginStatu loginStatu;

    public LoginStatu getLoginStatu() {
        return loginStatu;
    }

    public void setLoginStatu(LoginStatu loginStatu) {
        this.loginStatu = loginStatu;
    }

    public interface LoginStatu {
        public void isLogin(String what);

        public void noLofin();
    }
}
