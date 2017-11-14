package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;

/**
 * Created by Administrator on 2017/4/24.
 */

public interface FloorInfonModel {
    void loadFloorDatas(String url,CallBack callBack);

    interface CallBack {
        void  loadDatasSuccess(List<FloorInfoData.DatasBean> dataBeen);
        void  loadDatasFaile();
    }
}
