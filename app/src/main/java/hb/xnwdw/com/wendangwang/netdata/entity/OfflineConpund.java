package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class OfflineConpund {
    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"feac505e0f544bdb8385a461d38f7853","ID":2395,"MemberID":7687,"MemberName":null,"CouponID":22,"CouponCode":"qwe20170724000000000040","RecTime":"2017-07-24 17:08:49","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":"注册赠送优惠券","basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0},"orders":null},{"GUID":"2db6fbf1a1b24d018b290779c6d30ab0","ID":2396,"MemberID":7687,"MemberName":null,"CouponID":23,"CouponCode":"qwe20170724000000000043","RecTime":"2017-07-24 17:08:50","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":"注册赠送优惠券","basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0},"orders":null},{"GUID":"c2fa9d1e293c4dd789a9056e165cca92","ID":2397,"MemberID":7687,"MemberName":null,"CouponID":24,"CouponCode":"qwe20170724000000000046","RecTime":"2017-07-24 17:08:50","CouponStatus":0,"StrStatus":null,"UseTime":"0001-01-01 00:00:00","Memo":"注册赠送优惠券","basic_Coupon":{"GUID":null,"ID":0,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0},"orders":null}]
     * dataStatus : 1
     * describe : null
     */
    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private int dataStatus;
    private Object describe;
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

    public Object getDescribe() {
        return describe;
    }

    public void setDescribe(Object describe) {
        this.describe = describe;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * GUID : feac505e0f544bdb8385a461d38f7853
         * ID : 2395
         * MemberID : 7687
         * MemberName : null
         * CouponID : 22
         * CouponCode : qwe20170724000000000040
         * RecTime : 2017-07-24 17:08:49
         * CouponStatus : 0
         * StrStatus : null
         * UseTime : 0001-01-01 00:00:00
         * Memo : 注册赠送优惠券
         * basic_Coupon : {"GUID":null,"ID":0,"PromotionMode":"注册发放","CouponName":"新人大礼包","CouponMoney":10,"UseCondition":50,"ValidStartTime":"2017-07-21 00:00:00","ValidEndTime":"2017-08-21 23:59:59","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[],"coponStatus":0}
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
        private String Memo;
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

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
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
             * PromotionMode : 注册发放
             * CouponName : 新人大礼包
             * CouponMoney : 10
             * UseCondition : 50
             * ValidStartTime : 2017-07-21 00:00:00
             * ValidEndTime : 2017-08-21 23:59:59
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
            private String CouponMoney;
            private int UseCondition;
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

            public String getCouponMoney() {
                return CouponMoney;
            }

            public void setCouponMoney(String CouponMoney) {
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

            public void setCouponLimit(List<CouponLimitBean> CouponLimit) {
                this.CouponLimit = CouponLimit;
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
