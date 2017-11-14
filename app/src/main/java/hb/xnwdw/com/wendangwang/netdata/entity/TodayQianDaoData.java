package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/5/11.
 */

public class TodayQianDaoData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"Signed":1,"Continue":3,"Setting":{"GUID":"997e9461af4e4005b3c964859fd35b01","ID":4,"SignDay":4,"PrizeType":-1,"Integral":0,"CouponID":"","ItemID":"","Memo":"未设置奖励"}}
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
         * Signed : 1
         * Continue : 3
         * Setting : {"GUID":"997e9461af4e4005b3c964859fd35b01","ID":4,"SignDay":4,"PrizeType":-1,"Integral":0,"CouponID":"","ItemID":"","Memo":"未设置奖励"}
         */

        private int Signed;
        private int Continue;
        private SettingBean Setting;

        public int getSigned() {
            return Signed;
        }

        public void setSigned(int Signed) {
            this.Signed = Signed;
        }

        public int getContinue() {
            return Continue;
        }

        public void setContinue(int Continue) {
            this.Continue = Continue;
        }

        public SettingBean getSetting() {
            return Setting;
        }

        public void setSetting(SettingBean Setting) {
            this.Setting = Setting;
        }

        public static class SettingBean {
            /**
             * GUID : 997e9461af4e4005b3c964859fd35b01
             * ID : 4
             * SignDay : 4
             * PrizeType : -1
             * Integral : 0
             * CouponID :
             * ItemID :
             * Memo : 未设置奖励
             */

            private String GUID;
            private int ID;
            private int SignDay;
            private int PrizeType;
            private int Integral;
            private String CouponID;
            private String ItemID;
            private String Memo;

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

            public int getSignDay() {
                return SignDay;
            }

            public void setSignDay(int SignDay) {
                this.SignDay = SignDay;
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

            public String getCouponID() {
                return CouponID;
            }

            public void setCouponID(String CouponID) {
                this.CouponID = CouponID;
            }

            public String getItemID() {
                return ItemID;
            }

            public void setItemID(String ItemID) {
                this.ItemID = ItemID;
            }

            public String getMemo() {
                return Memo;
            }

            public void setMemo(String Memo) {
                this.Memo = Memo;
            }
        }
    }
}
