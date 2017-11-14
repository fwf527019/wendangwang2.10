package hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl;

import android.util.Log;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MainSlideModel;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.impl.MainSlideModelImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainSlidpagerPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainslidView;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MainSlidPagerPresenterImpl implements MainSlidpagerPresenter {
    private MainSlideModel model;
    private MainslidView view;
    private String url = UrlApi.URL_MAINSLID;

    public MainSlidPagerPresenterImpl(MainslidView view) {
        this.model = new MainSlideModelImpl();
        this.view = view;
    }


    @Override
    public void loadDatas() {
        model.loadSlidPgerDatas(url, new MainSlideModel.Callback() {
            @Override
            public void loadDatasSuccess(List<MainPagerSlidResponse.DatasBean> dataBeen) {
              view.showPagerSlidData(dataBeen);
            }

            @Override
            public void loadDatasFailed() {
                view.showDatasError();
            }
        });
    }
}