package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface MainRecommendeYouModel {
    void loadData(int pageSizi,int pager,String url,CallBack callBack);
    interface CallBack {
        void loadDataSuccess(List<MainRecommendeYouData.DatasBean> datasBeen);
        void loadDtaFaile();
    }
}
