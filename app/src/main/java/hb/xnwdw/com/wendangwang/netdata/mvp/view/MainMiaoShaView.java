package hb.xnwdw.com.wendangwang.netdata.mvp.view;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MainPageMiaoShaDate;

/**
 * Created by Administrator on 2017/4/17.
 */

public interface MainMiaoShaView {
   void showMiaoShaDatas(List<MainPageMiaoShaDate.ItemsBean> itemsBeen);
    void  shouMiaoshaTim(MainPageMiaoShaDate date);
    void showDataError();

}
