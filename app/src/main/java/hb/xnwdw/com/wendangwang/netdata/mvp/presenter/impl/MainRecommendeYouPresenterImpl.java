package hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MainRecommendeYouModel;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.impl.MainRecommendeYouModelImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MainRecommendeYouPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainRecommendeYouView;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MainRecommendeYouPresenterImpl implements MainRecommendeYouPresenter {
    private MainRecommendeYouModel model;
    private MainRecommendeYouView view;
    private String url= UrlApi.URL_RCOMMENDEYOU;

    public MainRecommendeYouPresenterImpl(MainRecommendeYouView view) {
        this.view = view;
        this.model=new MainRecommendeYouModelImpl();
    }

    @Override
    public void loadDtas(int pageSizi,int pager ) {
        model.loadData(pageSizi,pager,url, new MainRecommendeYouModel.CallBack() {
            @Override
            public void loadDataSuccess(List<MainRecommendeYouData.DatasBean> datasBeen) {
                view.showRecommendeYouShaDatas(datasBeen);
            }
            @Override
            public void loadDtaFaile() {

            }
        });
    }
}
