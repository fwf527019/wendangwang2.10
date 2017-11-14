package hb.xnwdw.com.wendangwang.netdata.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MainRecommendeYouModel;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MainRecommendeYouModelImpl implements MainRecommendeYouModel {
    @Override
    public void loadData(int pageSizi,int pager,String url, final CallBack callBack) {

        Map<String,String> map=new HashMap<>();
        map.put("page",pager+"");
        map.put("pageSize",pageSizi+"");

        HtttpRequest.CreatGetRequst(url, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                String ss = "{\"datas\":";
                String str = null;
                LogUtils.d("NewCommendeModelImpl", response);
                if (response.startsWith("[")) {
                    str = ss + response + "}";
                }
                LogUtils.d("MainRecommendeYouModelI", str);
                MainRecommendeYouData mainRecommendeYouData = JSON.parseObject(str, MainRecommendeYouData.class);
                callBack.loadDataSuccess(mainRecommendeYouData.getDatas());
            }
        });

    }
}
