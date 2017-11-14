package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/14.
 */

public class LineCanNotCouponsData  implements Serializable {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean  implements Serializable{
        /**
         * GUID : 84c87cddab6b4701a24de8baeea1808b
         * ID : 5269
         * MemberID : 8692
         * MemberName : null
         * CouponID : 23
         * CouponCode : 15053775499O953
         * RecTime : 2017-11-14 19:06:37
         * CouponStatus : 0
         * StrStatus : null
         * UseTime : 0001-01-01 00:00:00
         * Memo : null
         * basic_Coupon : {"GUID":null,"ID":0,"PromotionMode":"免费领取","CouponName":"肉满50减10块","CouponMoney":10,"UseCondition":150,"ValidStartTime":"2017-09-14 00:00:00","ValidEndTime":"2018-09-14 00:00:00","CouNumber":0,"ReceiveMode":0,"RecStartTime":"0001-01-01 00:00:00","RecEndTime":"0001-01-01 00:00:00","CouponStatus":0,"IsDelete":0,"Memo":null,"CouponLimit":[{"GUID":"2cc0aab6875d465194b103796a830ed8","ID":16,"CouponID":23,"LimitCate1":7,"cate1Name":null,"LimitCate2":41,"cateName":"红酒","Memo":null}],"coponStatus":0}
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

        public static class BasicCouponBean  implements Serializable{
            /**
             * GUID : null
             * ID : 0
             * PromotionMode : 免费领取
             * CouponName : 肉满50减10块
             * CouponMoney : 10
             * UseCondition : 150
             * ValidStartTime : 2017-09-14 00:00:00
             * ValidEndTime : 2018-09-14 00:00:00
             * CouNumber : 0
             * ReceiveMode : 0
             * RecStartTime : 0001-01-01 00:00:00
             * RecEndTime : 0001-01-01 00:00:00
             * CouponStatus : 0
             * IsDelete : 0
             * Memo : null
             * CouponLimit : [{"GUID":"2cc0aab6875d465194b103796a830ed8","ID":16,"CouponID":23,"LimitCate1":7,"cate1Name":null,"LimitCate2":41,"cateName":"红酒","Memo":null}]
             * coponStatus : 0
             */

            private Object GUID;
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

            public static class CouponLimitBean  implements Serializable{
                /**
                 * GUID : 2cc0aab6875d465194b103796a830ed8
                 * ID : 16
                 * CouponID : 23
                 * LimitCate1 : 7
                 * cate1Name : null
                 * LimitCate2 : 41
                 * cateName : 红酒
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
    }
}
