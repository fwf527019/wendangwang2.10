package hb.xnwdw.com.wendangwang.netdata.mvp.presenter.impl;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.MianMiaoShaModel;
import hb.xnwdw.com.wendangwang.netdata.mvp.model.impl.MianMiaoShaModelImpl;
import hb.xnwdw.com.wendangwang.netdata.mvp.presenter.MianMiaoShaPresenter;
import hb.xnwdw.com.wendangwang.netdata.mvp.view.MainMiaoShaView;

/**
 * Created by Administrator on 2017/4/17.
 */

public class MainMiaoShaPresenterImpl implements MianMiaoShaPresenter {
    private MainMiaoShaView view;
    private MianMiaoShaModel mianMiaoShaModel;
    private String url= UrlApi.URL_MAINMIAOSHA;

    public MainMiaoShaPresenterImpl(MainMiaoShaView view) {
        this.view = view;
        this.mianMiaoShaModel=new MianMiaoShaModelImpl();
    }

    @Override
    public void loadDatas() {
        mianMiaoShaModel.loadDatas(url, new MianMiaoShaModel.CallBack() {
            @Override
            public void loadDataSuccess(List<MainPageMiaoShaDate.ItemsBean> itemsBeen) {
                view.showMiaoShaDatas(itemsBeen);


            }

            @Override
            public void getTim(MainPageMiaoShaDate date) {
                view.shouMiaoshaTim(date);
            }

            @Override
            public void loadDatasFailed() {

            }
        });
    }
}
