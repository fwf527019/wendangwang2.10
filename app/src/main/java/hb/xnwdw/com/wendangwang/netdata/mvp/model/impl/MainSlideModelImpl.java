package hb.xnwdw.com.wendangwang.netdata.mvp.model.impl;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MainSlideModel;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MainSlideModelImpl implements MainSlideModel {
    @Override
    public void loadSlidPgerDatas(String url, final Callback callback) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .addParams("dataSource","App")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("MainSlideModelImpl", "加载数据失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //fastJison 绑定实体类

                        String ss="{\"datas\":";
                        String str=null;
                        LogUtils.d("MainSlideModelImpl", response);
                        if(response.startsWith("[")){
                            str=ss+response+"}";
                        }
                        MainPagerSlidResponse mainPagerSlidResponse= JSON.parseObject(str,MainPagerSlidResponse.class);
                        LogUtils.d("MainSlideModelImpl", response);
                        callback.loadDatasSuccess(mainPagerSlidResponse.getDatas());
                    }
                });
    }

}
