package hb.xnwdw.com.wendangwang.netdata.mvp.view;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.NewRecommendeData;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface NewCommendeView {
    void showNewCommende(List<NewRecommendeData.DatasBean> itemsBeen);
    void showDataError();
}
