package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class UserCenterOrderNumData {


    /**
     * name : orderCount
     * value : [{"OrderStatus":"0","count":"2"},{"OrderStatus":"1","count":"0"},{"OrderStatus":"8","count":"2"},{"OrderStatus":"9","count":"0"},{"OrderStatus":"99","count":"0"}]
     */

    private String name;
    private List<ValueBean> value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * OrderStatus : 0
         * count : 2
         */

        private String OrderStatus;
        private String count;

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
