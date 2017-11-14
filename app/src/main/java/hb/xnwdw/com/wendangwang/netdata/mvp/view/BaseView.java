package hb.xnwdw.com.wendangwang.netdata.mvp.view;

/**
 * Created by Administrator on 2017/4/17.
 */

public interface BaseView<T> {
    void showView(T t);
    void loadfailed();
}
