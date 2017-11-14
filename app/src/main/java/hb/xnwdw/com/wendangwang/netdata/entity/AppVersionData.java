package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AppVersionData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"str","ID":1,"AppUrl":"/path/str.apk","upContent":"第一条&第二条&第三条","IsConstraint":true,"VersionNum":4,"APPType":0}
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
         * GUID : str
         * ID : 1
         * AppUrl : /path/str.apk
         * upContent : 第一条&第二条&第三条
         * IsConstraint : true
         * VersionNum : 4
         * APPType : 0
         */

        private String GUID;
        private int ID;
        private String AppUrl;
        private String upContent;
        private boolean IsConstraint;
        private int VersionNum;
        private int APPType;

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

        public String getAppUrl() {
            return AppUrl;
        }

        public void setAppUrl(String AppUrl) {
            this.AppUrl = AppUrl;
        }

        public String getUpContent() {
            return upContent;
        }

        public void setUpContent(String upContent) {
            this.upContent = upContent;
        }

        public boolean isIsConstraint() {
            return IsConstraint;
        }

        public void setIsConstraint(boolean IsConstraint) {
            this.IsConstraint = IsConstraint;
        }

        public int getVersionNum() {
            return VersionNum;
        }

        public void setVersionNum(int VersionNum) {
            this.VersionNum = VersionNum;
        }

        public int getAPPType() {
            return APPType;
        }

        public void setAPPType(int APPType) {
            this.APPType = APPType;
        }
    }
}
