package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class Cpda {
    /**
     * GUID : 22b620c9fa8643dabd957773c3ec681d
     * ID : 7
     * PromotionMode : 积分兑换
     * CouponName : 双11欢乐送不停
     * CouponMoney : 111
     * UseCondition : 1
     * ValidStartTime : 2017-04-19 17:27:00
     * ValidEndTime : 2017-06-01 17:11:58
     * CouNumber : 11
     * ReceiveMode : 0
     * RecStartTime : 2017-04-19 17:27:05
     * RecEndTime : 2017-06-01 17:11:58
     * CouponStatus : 1
     * IsDelete : 1
     * Memo : 1
     * CouponLimit : [{"GUID":"e07ae086ef4c43c59a23d521a512757e","ID":1,"CouponID":7,"LimitCate1":1,"cate1Name":null,"LimitCate2":6,"cateName":"肥牛肉","Memo":null},{"GUID":"bff4ace771104fff9ebe560a35e99f8b","ID":2,"CouponID":7,"LimitCate1":1,"cate1Name":null,"LimitCate2":7,"cateName":"猪肉","Memo":null}]
     */

    private String GUID;
    private int ID;
    private String PromotionMode;
    private String CouponName;
    private int CouponMoney;
    private int UseCondition;
    private String ValidStartTime;
    private String ValidEndTime;
    private int CouNumber;
    private int ReceiveMode;
    private String RecStartTime;
    private String RecEndTime;
    private int CouponStatus;
    private int IsDelete;
    private String Memo;
    private List<CouponLimitBean> CouponLimit;

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPromotionMode() {
        return PromotionMode;
    }

    public void setPromotionMode(String PromotionMode) {
        this.PromotionMode = PromotionMode;
    }

    public String getCouponName() {
        return CouponName;
    }

    public void setCouponName(String CouponName) {
        this.CouponName = CouponName;
    }

    public int getCouponMoney() {
        return CouponMoney;
    }

    public void setCouponMoney(int CouponMoney) {
        this.CouponMoney = CouponMoney;
    }

    public int getUseCondition() {
        return UseCondition;
    }

    public void setUseCondition(int UseCondition) {
        this.UseCondition = UseCondition;
    }

    public String getValidStartTime() {
        return ValidStartTime;
    }

    public void setValidStartTime(String ValidStartTime) {
        this.ValidStartTime = ValidStartTime;
    }

    public String getValidEndTime() {
        return ValidEndTime;
    }

    public void setValidEndTime(String ValidEndTime) {
        this.ValidEndTime = ValidEndTime;
    }

    public int getCouNumber() {
        return CouNumber;
    }

    public void setCouNumber(int CouNumber) {
        this.CouNumber = CouNumber;
    }

    public int getReceiveMode() {
        return ReceiveMode;
    }

    public void setReceiveMode(int ReceiveMode) {
        this.ReceiveMode = ReceiveMode;
    }

    public String getRecStartTime() {
        return RecStartTime;
    }

    public void setRecStartTime(String RecStartTime) {
        this.RecStartTime = RecStartTime;
    }

    public String getRecEndTime() {
        return RecEndTime;
    }

    public void setRecEndTime(String RecEndTime) {
        this.RecEndTime = RecEndTime;
    }

    public int getCouponStatus() {
        return CouponStatus;
    }

    public void setCouponStatus(int CouponStatus) {
        this.CouponStatus = CouponStatus;
    }

    public int getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(int IsDelete) {
        this.IsDelete = IsDelete;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String Memo) {
        this.Memo = Memo;
    }

    public List<CouponLimitBean> getCouponLimit() {
        return CouponLimit;
    }

    public void setCouponLimit(List<CouponLimitBean> CouponLimit) {
        this.CouponLimit = CouponLimit;
    }

    public static class CouponLimitBean {
        /**
         * GUID : e07ae086ef4c43c59a23d521a512757e
         * ID : 1
         * CouponID : 7
         * LimitCate1 : 1
         * cate1Name : null
         * LimitCate2 : 6
         * cateName : 肥牛肉
         * Memo : null
         */

        private String GUID;
        private int ID;
        private int CouponID;
        private int LimitCate1;
        private Object cate1Name;
        private int LimitCate2;
        private String cateName;
        private Object Memo;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getCouponID() {
            return CouponID;
        }

        public void setCouponID(int CouponID) {
            this.CouponID = CouponID;
        }

        public int getLimitCate1() {
            return LimitCate1;
        }

        public void setLimitCate1(int LimitCate1) {
            this.LimitCate1 = LimitCate1;
        }

        public Object getCate1Name() {
            return cate1Name;
        }

        public void setCate1Name(Object cate1Name) {
            this.cate1Name = cate1Name;
        }

        public int getLimitCate2() {
            return LimitCate2;
        }

        public void setLimitCate2(int LimitCate2) {
            this.LimitCate2 = LimitCate2;
        }

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }
    }
}
