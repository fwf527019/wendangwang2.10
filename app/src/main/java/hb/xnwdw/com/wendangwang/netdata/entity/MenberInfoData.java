package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class MenberInfoData {


    /**
     * name : orderCount
     * value : [{"OrderStatus":"0","count":"17"},{"OrderStatus":"1","count":"13"},{"OrderStatus":"8","count":"3"},{"OrderStatus":"9","count":"1"},{"OrderStatus":"99","count":"0"}]
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
         * count : 17
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
