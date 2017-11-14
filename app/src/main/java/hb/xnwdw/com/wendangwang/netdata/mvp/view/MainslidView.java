package hb.xnwdw.com.wendangwang.netdata.mvp.view;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPagerSlidResponse;

/**
 * Created by Administrator on 2017/2/22.
 */

public interface MainslidView {
    void showPagerSlidData(List<MainPagerSlidResponse.DatasBean> databean);
    void showDatasError();
}
