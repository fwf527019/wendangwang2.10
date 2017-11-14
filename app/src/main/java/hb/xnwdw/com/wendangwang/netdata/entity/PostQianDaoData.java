package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/5/9.
 */
public class PostQianDaoData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"PrizeType":0,"Integral":20,"CouponID":null,"CouponName":null,"CouponMoney":0,"CouponCondition":0,"CouponStart":"0001-01-01 00:00:00","CouponEnd":"0001-01-01 00:00:00","ItemID":null,"ItemName":null,"ItemPic":null}
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
         * PrizeType : 0
         * Integral : 20
         * CouponID : null
         * CouponName : null
         * CouponMoney : 0
         * CouponCondition : 0
         * CouponStart : 0001-01-01 00:00:00
         * CouponEnd : 0001-01-01 00:00:00
         * ItemID : null
         * ItemName : null
         * ItemPic : null
         */

        private int PrizeType;
        private int Integral;
        private Object CouponID;
        private Object CouponName;
        private int CouponMoney;
        private int CouponCondition;
        private String CouponStart;
        private String CouponEnd;
        private String ItemID;
        private String ItemName;
        private String ItemPic;
        private String RecordID;

        public String getRecordID() {
            return RecordID;
        }

        public void setRecordID(String recordID) {
            RecordID = recordID;
        }

        public int getPrizeType() {
            return PrizeType;
        }

        public void setPrizeType(int PrizeType) {
            this.PrizeType = PrizeType;
        }

        public int getIntegral() {
            return Integral;
        }

        public void setIntegral(int Integral) {
            this.Integral = Integral;
        }

        public Object getCouponID() {
            return CouponID;
        }

        public void setCouponID(Object CouponID) {
            this.CouponID = CouponID;
        }

        public Object getCouponName() {
            return CouponName;
        }

        public void setCouponName(Object CouponName) {
            this.CouponName = CouponName;
        }

        public int getCouponMoney() {
            return CouponMoney;
        }

        public void setCouponMoney(int CouponMoney) {
            this.CouponMoney = CouponMoney;
        }

        public int getCouponCondition() {
            return CouponCondition;
        }

        public void setCouponCondition(int CouponCondition) {
            this.CouponCondition = CouponCondition;
        }

        public String getCouponStart() {
            return CouponStart;
        }

        public void setCouponStart(String CouponStart) {
            this.CouponStart = CouponStart;
        }

        public String getCouponEnd() {
            return CouponEnd;
        }

        public void setCouponEnd(String CouponEnd) {
            this.CouponEnd = CouponEnd;
        }

        public String getItemID() {
            return ItemID;
        }

        public void setItemID(String ItemID) {
            this.ItemID = ItemID;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }
    }
}
