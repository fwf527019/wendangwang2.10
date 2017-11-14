package hb.xnwdw.com.wendangwang.netdata.mvp.model;

import java.util.List;

import hb.xnwdw.com.wendangwang.netdata.entity.MenberInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.ResContNumData;
import hb.xnwdw.com.wendangwang.netdata.entity.ServerContData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterInforData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterMenberInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterOrderNumData;

/**
 * Created by Administrator on 2017/4/26.
 */

public interface UserCenterInfoModel {
    void loadDatas(String url,CallBack callBack);


    interface  CallBack {
        void userInfodata(MenberInfoData data);
    }
}
