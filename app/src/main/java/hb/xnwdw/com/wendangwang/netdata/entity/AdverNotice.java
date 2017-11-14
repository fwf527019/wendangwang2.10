package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class AdverNotice {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"dab43bad136c4622ab92af42fd5db618","ID":4,"Title":"关于开展某大型活动的公告","ReleaseTime":"2017-04-24 10:54:58","OrderIndex":0,"RecToHome":0,"NoticeStatus":0,"NoticeDetail":null,"Synopsis":null,"Memo":null},{"GUID":"8628edb581e64bd198a561e44d33b8fe","ID":5,"Title":"关于习近平特朗普就朝鲜局势等问题通电话","ReleaseTime":"2017-04-24 13:54:04","OrderIndex":0,"RecToHome":0,"NoticeStatus":0,"NoticeDetail":null,"Synopsis":null,"Memo":null},{"GUID":"afeb39eceaf244e1b677e97429ba65f2","ID":1005,"Title":"消费者保障服务","ReleaseTime":"2017-05-03 09:48:33","OrderIndex":0,"RecToHome":0,"NoticeStatus":0,"NoticeDetail":null,"Synopsis":null,"Memo":null}]
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
         * GUID : dab43bad136c4622ab92af42fd5db618
         * ID : 4
         * Title : 关于开展某大型活动的公告
         * ReleaseTime : 2017-04-24 10:54:58
         * OrderIndex : 0
         * RecToHome : 0
         * NoticeStatus : 0
         * NoticeDetail : null
         * Synopsis : null
         * Memo : null
         */

        private String GUID;
        private int ID;
        private String Title;
        private String ReleaseTime;
        private int OrderIndex;
        private int RecToHome;
        private int NoticeStatus;
        private Object NoticeDetail;
        private Object Synopsis;
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

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getReleaseTime() {
            return ReleaseTime;
        }

        public void setReleaseTime(String ReleaseTime) {
            this.ReleaseTime = ReleaseTime;
        }

        public int getOrderIndex() {
            return OrderIndex;
        }

        public void setOrderIndex(int OrderIndex) {
            this.OrderIndex = OrderIndex;
        }

        public int getRecToHome() {
            return RecToHome;
        }

        public void setRecToHome(int RecToHome) {
            this.RecToHome = RecToHome;
        }

        public int getNoticeStatus() {
            return NoticeStatus;
        }

        public void setNoticeStatus(int NoticeStatus) {
            this.NoticeStatus = NoticeStatus;
        }

        public Object getNoticeDetail() {
            return NoticeDetail;
        }

        public void setNoticeDetail(Object NoticeDetail) {
            this.NoticeDetail = NoticeDetail;
        }

        public Object getSynopsis() {
            return Synopsis;
        }

        public void setSynopsis(Object Synopsis) {
            this.Synopsis = Synopsis;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }
    }
}
