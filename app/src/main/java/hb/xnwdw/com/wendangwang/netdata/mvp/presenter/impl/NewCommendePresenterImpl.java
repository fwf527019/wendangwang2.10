package hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.NewCommendeModel;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.impl.NewCommendeModelImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.NewCommendePresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.NewCommendeView;

/**
 * Created by Administrator on 2017/4/18.
 */

public class NewCommendePresenterImpl implements NewCommendePresenter {
    private NewCommendeModel newCommendeModel;
    private NewCommendeView view;
   private String url= UrlApi.URL_NEWARRIVALS;

    public NewCommendePresenterImpl(NewCommendeView view) {
        this.view = view;
        this.newCommendeModel=new NewCommendeModelImpl();
    }

    @Override
    public void loadDatas() {
        newCommendeModel.loadDatas(url, new NewCommendeModel.CallBack() {


            @Override
            public void loadDataSuccess(List<NewRecommendeData.DatasBean> datas) {
                view.showNewCommende(datas);
            }

            @Override
            public void loadDatasFailed() {

            }
        });
    }

}
