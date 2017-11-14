package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/9/26.
 */

public class FullReduce {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"ActivityNum":"ACT20170925008","Money":"10.00","ItemID":0,"ItemName":null,"Num":0}
     * dataStatus : 1
     * describe : null
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private ObjBean obj;
    private int dataStatus;
    private Object describe;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSumSize() {
        return sumSize;
    }

    public void setSumSize(int sumSize) {
        this.sumSize = sumSize;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Object getDescribe() {
        return describe;
    }

    public void setDescribe(Object describe) {
        this.describe = describe;
    }

    public static class ObjBean {
        /**
         * ActivityNum : ACT20170925008
         * Money : 10.00
         * ItemID : 0
         * ItemName : null
         * Num : 0
         */

        private String ActivityNum;
        private String Money;
        private int ItemID;
        private Object ItemName;
        private int Num;

        public String getActivityNum() {
            return ActivityNum;
        }

        public void setActivityNum(String ActivityNum) {
            this.ActivityNum = ActivityNum;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public int getItemID() {
            return ItemID;
        }

        public void setItemID(int ItemID) {
            this.ItemID = ItemID;
        }

        public Object getItemName() {
            return ItemName;
        }

        public void setItemName(Object ItemName) {
            this.ItemName = ItemName;
        }

        public int getNum() {
            return Num;
        }

        public void setNum(int Num) {
            this.Num = Num;
        }
    }
}
