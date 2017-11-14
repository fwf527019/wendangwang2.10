package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/6/16.
 */

public class AccontinInfoData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"c4faeb78ee6b467b9c79eb5f98041b20","ID":1052,"MemberID":1068,"HeadFace":"/UploadImage/HeadFace/201707\\e8c4dba8117249cb995a90d26a3cdc38.jpeg","MemberName":"明理楼","Sex":"男","Birthday":"2017-08-14 00:00:00"}
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
         * GUID : c4faeb78ee6b467b9c79eb5f98041b20
         * ID : 1052
         * MemberID : 1068
         * HeadFace : /UploadImage/HeadFace/201707\e8c4dba8117249cb995a90d26a3cdc38.jpeg
         * MemberName : 明理楼
         * Sex : 男
         * Birthday : 2017-08-14 00:00:00
         */

        private String GUID;
        private int ID;
        private int MemberID;
        private String HeadFace;
        private String MemberName;
        private String Sex;
        private String Birthday;

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

        public String getHeadFace() {
            return HeadFace;
        }

        public void setHeadFace(String HeadFace) {
            this.HeadFace = HeadFace;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String MemberName) {
            this.MemberName = MemberName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }
    }
}
