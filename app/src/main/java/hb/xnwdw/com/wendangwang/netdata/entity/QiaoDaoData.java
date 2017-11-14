package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/5/9.
 */

public class QiaoDaoData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"PrizeType":"积分","PrizeText":"20积分","SignDescribe":"本次签到可获得20积分,下次签到可获得30积分."}
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
         * PrizeType : 积分
         * PrizeText : 20积分
         * SignDescribe : 本次签到可获得20积分,下次签到可获得30积分.
         */

        private String PrizeType;
        private String PrizeText;
        private String SignDescribe;

        public String getPrizeType() {
            return PrizeType;
        }

        public void setPrizeType(String PrizeType) {
            this.PrizeType = PrizeType;
        }

        public String getPrizeText() {
            return PrizeText;
        }

        public void setPrizeText(String PrizeText) {
            this.PrizeText = PrizeText;
        }

        public String getSignDescribe() {
            return SignDescribe;
        }

        public void setSignDescribe(String SignDescribe) {
            this.SignDescribe = SignDescribe;
        }
    }
}
