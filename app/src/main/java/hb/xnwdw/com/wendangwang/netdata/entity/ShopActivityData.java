package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */

public class ShopActivityData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"c42276c42a45493e880570cc0ea01796","ID":2,"ActivityName":"左1212","ActPic":null,"StartTime":"2017-03-27 00:00:00","EndTime":"2017-05-05 00:00:00","ActivityDetail":"<p>111椅套大厦大厦大厦大厦大厦大厦大厦大厦大厦大白白白白白白的瞃<br/><\/p>","activeStatus":-1,"IssueTM":"2017-04-05 14:40:50","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null},{"GUID":"c2ee5588ce384b0da1c7004ca24881cd","ID":3,"ActivityName":"121211门店10周年大派送45","ActPic":null,"StartTime":"2017-04-05 00:00:00","EndTime":"2017-04-25 00:00:00","ActivityDetail":"<p>25222门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送5555555555门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送门店10周年大派送212门店10周年大派送门店10周年大派送<\/p>","activeStatus":-1,"IssueTM":"2017-04-05 15:44:18","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null},{"GUID":"9bd302627d7344a2b5a01fc2924f0a51","ID":4,"ActivityName":"asdlkj112寒冰..","ActPic":null,"StartTime":"2017-04-27 10:14:04","EndTime":"2017-05-05 00:00:00","ActivityDetail":"<p>啥地方多舒服撒范德萨的发<\/p>","activeStatus":-1,"IssueTM":"2017-04-27 10:14:52","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null},{"GUID":"9b8f657e22574cc9a4aaaefb629e1aee","ID":5,"ActivityName":"4月水果狂欢节","ActPic":null,"StartTime":"2017-04-27 10:25:38","EndTime":"2017-04-30 10:25:38","ActivityDetail":"<p>阿士大夫撒飞洒地方<br/><\/p>","activeStatus":-1,"IssueTM":"2017-04-27 10:26:27","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null},{"GUID":"ff8bde1af9b94e3e87db953d027b3134","ID":6,"ActivityName":"4月水果狂欢节","ActPic":null,"StartTime":"2017-04-27 10:26:29","EndTime":"2017-04-30 10:26:29","ActivityDetail":"<p>嗯嗯嗯嗯嗯嗯嗯嗯<br/><\/p>","activeStatus":-1,"IssueTM":"2017-04-27 10:26:45","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null},{"GUID":"76d6bb6f32d04c628dba06f0bdf32d97","ID":7,"ActivityName":"第一个活动","ActPic":"/UploadImage/ActPic/201705/07c134dc-6cfe-4246-a6d7-875ef25e3324.jpg","StartTime":"2017-05-05 11:17:27","EndTime":"2017-05-31 11:17:27","ActivityDetail":"<p>稳当稳当稳当稳当稳当稳当<\/p>","activeStatus":1,"IssueTM":"2017-05-05 11:18:06","IsDelete":0,"Memo":null,"StoreIdList":null,"StoreNameList":null,"storeId":null,"stroeName":null,"basic_StoreList":null}]
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
         * GUID : c42276c42a45493e880570cc0ea01796
         * ID : 2
         * ActivityName : 左1212
         * ActPic : null
         * StartTime : 2017-03-27 00:00:00
         * EndTime : 2017-05-05 00:00:00
         * ActivityDetail : <p>111椅套大厦大厦大厦大厦大厦大厦大厦大厦大厦大白白白白白白的瞃<br/></p>
         * activeStatus : -1
         * IssueTM : 2017-04-05 14:40:50
         * IsDelete : 0
         * Memo : null
         * StoreIdList : null
         * StoreNameList : null
         * storeId : null
         * stroeName : null
         * basic_StoreList : null
         */

        private String GUID;
        private int ID;
        private String ActivityName;
        private Object ActPic;
        private String StartTime;
        private String EndTime;
        private String ActivityDetail;
        private int activeStatus;
        private String IssueTM;
        private int IsDelete;
        private Object Memo;
        private Object StoreIdList;
        private Object StoreNameList;
        private Object storeId;
        private Object stroeName;
        private Object basic_StoreList;

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

        public String getActivityName() {
            return ActivityName;
        }

        public void setActivityName(String ActivityName) {
            this.ActivityName = ActivityName;
        }

        public Object getActPic() {
            return ActPic;
        }

        public void setActPic(Object ActPic) {
            this.ActPic = ActPic;
        }

        public String getStartTime() {
            return StartTime;
        }

        public void setStartTime(String StartTime) {
            this.StartTime = StartTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getActivityDetail() {
            return ActivityDetail;
        }

        public void setActivityDetail(String ActivityDetail) {
            this.ActivityDetail = ActivityDetail;
        }

        public int getActiveStatus() {
            return activeStatus;
        }

        public void setActiveStatus(int activeStatus) {
            this.activeStatus = activeStatus;
        }

        public String getIssueTM() {
            return IssueTM;
        }

        public void setIssueTM(String IssueTM) {
            this.IssueTM = IssueTM;
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

        public Object getStoreIdList() {
            return StoreIdList;
        }

        public void setStoreIdList(Object StoreIdList) {
            this.StoreIdList = StoreIdList;
        }

        public Object getStoreNameList() {
            return StoreNameList;
        }

        public void setStoreNameList(Object StoreNameList) {
            this.StoreNameList = StoreNameList;
        }

        public Object getStoreId() {
            return storeId;
        }

        public void setStoreId(Object storeId) {
            this.storeId = storeId;
        }

        public Object getStroeName() {
            return stroeName;
        }

        public void setStroeName(Object stroeName) {
            this.stroeName = stroeName;
        }

        public Object getBasic_StoreList() {
            return basic_StoreList;
        }

        public void setBasic_StoreList(Object basic_StoreList) {
            this.basic_StoreList = basic_StoreList;
        }
    }
}
