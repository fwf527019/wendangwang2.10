package hb.xnwdw.com.wendangwang.netdata.mvp.view;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface FloorInfoView {

    void showFloorInfoData(List<FloorInfoData.DatasBean> dataBeen);
    void showDataError();
}
