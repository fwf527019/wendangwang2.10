package hb.xnwdw.com.wendangwang.netdata.mvp.view;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainRecommendeYouData;

/**
 * Created by Administrator on 2017/4/18.
 */

public interface MainRecommendeYouView  {
    void showRecommendeYouShaDatas(List<MainRecommendeYouData.DatasBean> datasBeen);
    void showDataError();

}
