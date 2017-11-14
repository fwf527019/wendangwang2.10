package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface NewCommendeModel {
    void loadDatas(String url,CallBack callBack);


    interface  CallBack {
        void loadDataSuccess(List<NewRecommendeData.DatasBean> datas);

        void loadDatasFailed();

    }
}
