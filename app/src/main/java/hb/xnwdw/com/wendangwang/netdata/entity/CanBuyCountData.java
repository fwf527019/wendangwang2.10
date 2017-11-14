package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/9/7.
 */

public class CanBuyCountData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"CanBuyCout":2,"MostCount":2}
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
         * CanBuyCout : 2
         * MostCount : 2
         */

        private int CanBuyCout;
        private int MostCount;

        public int getCanBuyCout() {
            return CanBuyCout;
        }

        public void setCanBuyCout(int CanBuyCout) {
            this.CanBuyCout = CanBuyCout;
        }

        public int getMostCount() {
            return MostCount;
        }

        public void setMostCount(int MostCount) {
            this.MostCount = MostCount;
        }
    }
}
