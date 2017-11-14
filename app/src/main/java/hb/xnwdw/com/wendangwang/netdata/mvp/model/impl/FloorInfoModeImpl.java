package hb.xnwdw.com.wendangwang.netdata.mvp.model.impl;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.FloorInfonModel;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/24.
 */

public class FloorInfoModeImpl implements FloorInfonModel {
    @Override
    public void loadFloorDatas(String url, final CallBack callBack) {
        HtttpRequest.CreatGetRequstNoToken(url, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("FloorInfoModeImpl", "e:" + e);
            }
            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FloorInfoModeImpl", response);
                String ss="{\"datas\":";
                String str=null;
                if(response.startsWith("[")){
                    str=ss+response+"}";
                    FloorInfoData floorInfoData=JSON.parseObject(str,FloorInfoData.class);
                    callBack.loadDatasSuccess(floorInfoData.getDatas());
                }
                //  LogUtils.d("FloorInfoModeImpl", str);
            }
        });
    }
}
