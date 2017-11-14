package hb.xnwdw.com.wendangwang.EventBus;

/**
 * Created by Administrator on 2017/7/26.
 */

public class Good_Login_event {
    String Status;

    public Good_Login_event(String status) {
        Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
