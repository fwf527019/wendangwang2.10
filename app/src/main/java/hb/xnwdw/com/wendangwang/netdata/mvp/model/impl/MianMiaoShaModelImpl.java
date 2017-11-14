package hb.xnwdw.com.wendangwang.netdata.mvp.model.impl;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MianMiaoShaModel;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MianMiaoShaModelImpl implements MianMiaoShaModel {


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
                LogUtils.d("MianMiaoShaModelImpl", "加载数据失败");
                LogUtils.d("MianMiaoShaModelImpl", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MianMiaoShaModelImpl", response);
                if(!response.equals("null")) {
                    MainPageMiaoShaDate mainPageMiaoShaDate = JSON.parseObject(response, MainPageMiaoShaDate.class);
                    callBack.loadDataSuccess(mainPageMiaoShaDate.getItems());
                    callBack.getTim(mainPageMiaoShaDate);
                }
            }
        });
    }

}
