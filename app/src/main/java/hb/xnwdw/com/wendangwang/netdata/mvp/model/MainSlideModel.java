package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface MainSlideModel {
    void loadSlidPgerDatas(String url,Callback callback);

    public interface Callback {
        void loadDatasSuccess(List<MainPagerSlidResponse.DatasBean> dataBeen);

        void loadDatasFailed();
    }
}
