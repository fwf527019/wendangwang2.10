package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CouponInfoData implements Serializable {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [[{"GUID":"d2ee9bc76c0644be8f31547214df53f3","ID":5154,"MemberID":7687,"MemberName":null,"CouponID":22,"CouponCode":"15052993241F147","RecTime":"2017-09-18 13:40:08","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":null,"basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"免费领取","CouponName":"测试免费领取","CouponMoney":10,"UseCondition":0.01,"ValidStartTime":"2017-09-13 00:00:00","ValidEndTime":"2017-12-31 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0},"orders":null},{"GUID":"68beb5214ae846639b6e324fe3f91882","ID":5263,"MemberID":7687,"MemberName":null,"CouponID":23,"CouponCode":"1505377549678E8","RecTime":"2017-09-18 13:40:11","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":null,"basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"免费领取","CouponName":"肉满50减10块","CouponMoney":10,"UseCondition":150,"ValidStartTime":"2017-09-14 00:00:00","ValidEndTime":"2018-09-14 00:00:00","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[{"GUID":"2cc0aab6875d465194b103796a830ed8","ID":16,"CouponID":23,"LimitCate1":7,"cate1Name":null,"LimitCate2":41,"cateName":"红酒","Memo":null}],"coponStatus":0},"orders":null},{"GUID":"e976285de9a548d18e3957c18e0fe775","ID":7261,"MemberID":7687,"MemberName":null,"CouponID":25,"CouponCode":"150537774151J18","RecTime":"2017-09-18 13:40:10","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":null,"basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"免费领取","CouponName":"test3","CouponMoney":20,"UseCondition":200,"ValidStartTime":"2017-09-14 00:00:00","ValidEndTime":"2018-09-14 00:00:00","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[{"GUID":"ac2e2b4f8f2d4180a778bea200c77998","ID":17,"CouponID":25,"LimitCate1":1,"cate1Name":null,"LimitCate2":11,"cateName":"浆果类","Memo":null},{"GUID":"a1716cc92cbe4b5687a7931d783d6ae1","ID":18,"CouponID":25,"LimitCate1":1,"cate1Name":null,"LimitCate2":12,"cateName":"柑橘类","Memo":null},{"GUID":"3a6e4b3aa0004ea6828cc43135c1c8d7","ID":19,"CouponID":25,"LimitCate1":1,"cate1Name":null,"LimitCate2":13,"cateName":"核果类","Memo":null}],"coponStatus":0},"orders":null}],[],[]]
     * dataStatus : -1
     * describe : null
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private int dataStatus;
    private Object describe;
    private List<List<ObjBean>> obj;

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

    public Object getDescribe() {
        return describe;
    }

    public void setDescribe(Object describe) {
        this.describe = describe;
    }

    public List<List<ObjBean>> getObj() {
        return obj;
    }

    public void setObj(List<List<ObjBean>> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * GUID : d2ee9bc76c0644be8f31547214df53f3
         * ID : 5154
         * MemberID : 7687
         * MemberName : null
         * CouponID : 22
         * CouponCode : 15052993241F147
         * RecTime : 2017-09-18 13:40:08
         * CouponStatus : 0
         * StrStatus : null
         * UseTime : 0001-01-01 00:00:00
         * Memo : null
         * basic_Coupon : {"GUID":null,"ID":0,"PromotionMode":"免费领取","CouponName":"测试免费领取","CouponMoney":10,"UseCondition":0.01,"ValidStartTime":"2017-09-13 00:00:00","ValidEndTime":"2017-12-31 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0}
         * orders : null
         */

        private String GUID;
        private int ID;
        private int MemberID;
        private Object MemberName;
        private int CouponID;
        private String CouponCode;
        private String RecTime;
        private int CouponStatus;
        private Object StrStatus;
        private String UseTime;
        private Object Memo;
        private BasicCouponBean basic_Coupon;
        private Object orders;

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

        public int getMemberID() {
            return MemberID;
        }

        public void setMemberID(int MemberID) {
            this.MemberID = MemberID;
        }

        public Object getMemberName() {
            return MemberName;
        }

        public void setMemberName(Object MemberName) {
            this.MemberName = MemberName;
        }

        public int getCouponID() {
            return CouponID;
        }

        public void setCouponID(int CouponID) {
            this.CouponID = CouponID;
        }

        public String getCouponCode() {
            return CouponCode;
        }

        public void setCouponCode(String CouponCode) {
            this.CouponCode = CouponCode;
        }

        public String getRecTime() {
            return RecTime;
        }

        public void setRecTime(String RecTime) {
            this.RecTime = RecTime;
        }

        public int getCouponStatus() {
            return CouponStatus;
        }

        public void setCouponStatus(int CouponStatus) {
            this.CouponStatus = CouponStatus;
        }

        public Object getStrStatus() {
            return StrStatus;
        }

        public void setStrStatus(Object StrStatus) {
            this.StrStatus = StrStatus;
        }

        public String getUseTime() {
            return UseTime;
        }

        public void setUseTime(String UseTime) {
            this.UseTime = UseTime;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public BasicCouponBean getBasic_Coupon() {
            return basic_Coupon;
        }

        public void setBasic_Coupon(BasicCouponBean basic_Coupon) {
            this.basic_Coupon = basic_Coupon;
        }

        public Object getOrders() {
            return orders;
        }

        public void setOrders(Object orders) {
            this.orders = orders;
        }

        public static class BasicCouponBean {
            /**
             * GUID : null
             * ID : 0
             * PromotionMode : 免费领取
             * CouponName : 测试免费领取
             * CouponMoney : 10
             * UseCondition : 0.01
             * ValidStartTime : 2017-09-13 00:00:00
             * ValidEndTime : 2017-12-31 23:59:59
             * CouNumber : 0
             * ReceiveMode : 0
             * RecStartTime : 0001-01-01 00:00:00
             * RecEndTime : 0001-01-01 00:00:00
             * CouponStatus : 0
             * IsDelete : 0
             * Memo : null
             * CouponLimit : []
             * coponStatus : 0
             */

            private Object GUID;
            private int ID;
            private String PromotionMode;
            private String CouponName;
            private int CouponMoney;
            private double UseCondition;
            private String ValidStartTime;
            private String ValidEndTime;
            private int CouNumber;
            private int ReceiveMode;
            private String RecStartTime;
            private String RecEndTime;
            private int CouponStatus;
            private int IsDelete;
            private Object Memo;
            private int coponStatus;
            private List<CouponLimitBean> CouponLimit;

            public Object getGUID() {
                return GUID;
            }

            public void setGUID(Object GUID) {
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

            public Object getMemo() {
                return Memo;
            }

            public void setMemo(Object Memo) {
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
            public static class CouponLimitBean {
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


}
