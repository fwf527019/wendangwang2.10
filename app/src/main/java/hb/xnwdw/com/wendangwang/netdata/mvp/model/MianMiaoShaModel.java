package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;

/**
 * Created by Administrator on 2017/4/17.
 */

public interface MianMiaoShaModel {
    void loadDatas(String url, CallBack callBack);

    public interface CallBack {
        void loadDataSuccess(List<MainPageMiaoShaDate.ItemsBean> itemsBeen);
        void getTim(MainPageMiaoShaDate date);
        void loadDatasFailed();
    }


}
