package hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FloorInfoData;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.FloorInfonModel;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.impl.FloorInfoModeImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.FloorInfoPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.FloorInfoView;

/**
 * Created by Administrator on 2017/4/24.
 */

public class FloorInfoPresenterImpl implements FloorInfoPresenter {

    private FloorInfonModel model;
    String url= UrlApi.URL_FLOORINFO;
    private FloorInfoView view;

    public FloorInfoPresenterImpl(FloorInfoView view) {
        this.model=new FloorInfoModeImpl();
        this.view = view;
    }

    @Override
    public void loadDatas() {
        model.loadFloorDatas(url, new FloorInfonModel.CallBack() {
            @Override
            public void loadDatasSuccess(List<FloorInfoData.DatasBean> dataBeen) {
                view.showFloorInfoData(dataBeen);
            }

            @Override
            public void loadDatasFaile() {

            }
        });
    }
}
