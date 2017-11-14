package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/1.
 */

public class JifenDetailData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"data":[{"GUID":null,"ID":0,"MemberID":0,"Integral":30,"MemberName":null,"HappenTime":"2017-05-22 18:21:36","Description":"抽奖","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-30,"MemberName":null,"HappenTime":"2017-05-20 17:01:03","Description":"订单记录2017052016395900002678","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-400,"MemberName":null,"HappenTime":"2017-05-19 10:27:13","Description":"订单记录2017051910215400006113","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-1,"MemberName":null,"HappenTime":"2017-05-18 15:29:32","Description":"积分对换","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-12 10:46:19","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-11 09:36:19","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-10 14:47:21","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-09 17:36:08","Description":"签到领取","Memo":""}],"count":10}
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
         * data : [{"GUID":null,"ID":0,"MemberID":0,"Integral":30,"MemberName":null,"HappenTime":"2017-05-22 18:21:36","Description":"抽奖","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-30,"MemberName":null,"HappenTime":"2017-05-20 17:01:03","Description":"订单记录2017052016395900002678","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-400,"MemberName":null,"HappenTime":"2017-05-19 10:27:13","Description":"订单记录2017051910215400006113","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":-1,"MemberName":null,"HappenTime":"2017-05-18 15:29:32","Description":"积分对换","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-12 10:46:19","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-11 09:36:19","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-10 14:47:21","Description":"签到领取","Memo":""},{"GUID":null,"ID":0,"MemberID":0,"Integral":20,"MemberName":null,"HappenTime":"2017-05-09 17:36:08","Description":"签到领取","Memo":""}]
         * count : 10
         */

        private int count;
        private List<DataBean> data;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * GUID : null
             * ID : 0
             * MemberID : 0
             * Integral : 30
             * MemberName : null
             * HappenTime : 2017-05-22 18:21:36
             * Description : 抽奖
             * Memo :
             */

            private Object GUID;
            private int ID;
            private int MemberID;
            private int Integral;
            private Object MemberName;
            private String HappenTime;
            private String Description;
            private String Memo;

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

            public int getMemberID() {
                return MemberID;
            }

            public void setMemberID(int MemberID) {
                this.MemberID = MemberID;
            }

            public int getIntegral() {
                return Integral;
            }

            public void setIntegral(int Integral) {
                this.Integral = Integral;
            }

            public Object getMemberName() {
                return MemberName;
            }

            public void setMemberName(Object MemberName) {
                this.MemberName = MemberName;
            }

            public String getHappenTime() {
                return HappenTime;
            }

            public void setHappenTime(String HappenTime) {
                this.HappenTime = HappenTime;
            }

            public String getDescription() {
                return Description;
            }

            public void setDescription(String Description) {
                this.Description = Description;
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
