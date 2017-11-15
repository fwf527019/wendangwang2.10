package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */

public class RegestConpondData implements Serializable{

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"da5b855f8fbe47cc96a8bf3acd47f81e","ID":17,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":30,"UseCondition":99,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":-1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"6aa8fa22b64c4143a88b414a972da80e","ID":18,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":50,"UseCondition":150,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"8573de3196d948618d6fba21048fcd39","ID":19,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":50,"UseCondition":150,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"34ffacda47a148bf88f4b7b0c8ebb936","ID":20,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":30,"UseCondition":99,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"700623aa1c054058bbfa1dbdb92624a1","ID":21,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":30,"UseCondition":99,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"0b1f136ed4104ea786b6c02f08f2e1aa","ID":22,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"218ede928e054d23a941ecaefd170500","ID":23,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"fce44d4d728f4136a0167e37030fe647","ID":24,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"2017-07-21 00:00:00","RecEndTime":"2017-08-21 23:59:59","CouponStatus":1,"IsDelete":1,"Memo":null,"CouponLimit":null,"coponStatus":0},{"GUID":"51aa7dae67b04b0e88d308ab8ab4cc0f","ID":0,"PromotionMode":null,"CouponName":null,"CouponMoney":0,"UseCondition":0,"ValidStartTime":"0001-01-01 00:00:00","ValidEndTime":"0001-01-01 00:00:00","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":null,"coponStatus":0}]
     * dataStatus : 1
     * describe : 注册成功
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private int dataStatus;
    private String describe;
    private List<ObjBean> obj;

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

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean implements Serializable{
        /**
         * GUID : da5b855f8fbe47cc96a8bf3acd47f81e
         * ID : 17
         * PromotionMode : 注册发放
         * CouponName : 新人大礼包
         * CouponMoney : 30.0
         * UseCondition : 99.0
         * ValidStartTime : 2017-07-21 00:00:00
         * ValidEndTime : 2017-08-21 23:59:59
         * CouNumber : 0
         * ReceiveMode : 0
         * RecStartTime : 2017-07-21 00:00:00
         * RecEndTime : 2017-08-21 23:59:59
         * CouponStatus : 1
         * IsDelete : -1
         * Memo : null
         * CouponLimit : null
         * coponStatus : 0
         */

        private String GUID;
        private int ID;
        private String PromotionMode;
        private String CouponName;
        private double CouponMoney;
        private double UseCondition;
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
        private int coponStatus;

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

        public double getCouponMoney() {
            return CouponMoney;
        }

        public void setCouponMoney(double CouponMoney) {
            this.CouponMoney = CouponMoney;
        }

        public double getUseCondition() {
            return UseCondition;
        }

        public void setUseCondition(double UseCondition) {
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



        public int getCoponStatus() {
            return coponStatus;
        }

        public void setCoponStatus(int coponStatus) {
            this.coponStatus = coponStatus;
        }

        public List<CouponLimitBean> getCouponLimit() {
            return CouponLimit;
        }

        public void setCouponLimit(List<CouponLimitBean> couponLimit) {
            CouponLimit = couponLimit;
        }

        public static class CouponLimitBean implements Serializable{
            private  String LimitCate1;
            private  String cateName;

            public String getLimitCate1() {
                return LimitCate1;
            }

            public void setLimitCate1(String limitCate1) {
                LimitCate1 = limitCate1;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }
        }

    }
}
