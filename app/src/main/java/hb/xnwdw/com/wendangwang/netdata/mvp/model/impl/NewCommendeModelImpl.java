package hb.xnwdw.com.wendangwang.netdata.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.NewCommendeModel;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/18.
 */

public class NewCommendeModelImpl implements NewCommendeModel {
    @Override
    public void loadDatas(String url, final CallBack callBack) {






        OkHttpUtils
                .get()
                .url(url)
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("NewCommendeModelImpl", "获取数据异常");
            }


            @Override
            public void onResponse(String response, int id) {
                String ss="{\"datas\":";
                String str="";
                LogUtils.d("NewCommendeModelImpl", response);
                if(response.startsWith("[")){
                    str=ss+response+"}";
                    LogUtils.d("NewCommendeModelImpl", str);
                }
                if(!response.equals("null")) {
                    NewRecommendeData newRecommendeData = JSON.parseObject(str, NewRecommendeData.class);
                    callBack.loadDataSuccess(newRecommendeData.getDatas());
                }
            }
        });
    }
}
