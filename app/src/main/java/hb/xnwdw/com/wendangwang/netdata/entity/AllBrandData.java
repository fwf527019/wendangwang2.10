package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class AllBrandData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"eb5b28cef297432183890d3d79673969","ID":2,"BrandID":"00002","BrandName":"百事可乐","BrandPic":"/UploadImage/BrandPic/0c46b34a17f44c62bd842552a82ef29e.jpg","BrandStatus":1,"Memo":"B"},{"GUID":"f465a8bca932459c9fb167f566ea4795","ID":6,"BrandID":"00006","BrandName":"百事","BrandPic":"","BrandStatus":1,"Memo":"B"},{"GUID":"d5f9008cb60d40ae880f1eabac6d9193","ID":7,"BrandID":"00007","BrandName":"红牛","BrandPic":"","BrandStatus":1,"Memo":"H"},{"GUID":"e9a35e8feecb47ecb58200de2f631144","ID":1,"BrandID":"00001","BrandName":"康师傅","BrandPic":"/UploadImage/BrandPic/7aa4bb3ffab84324a5f8ad8fef6789cd.png","BrandStatus":1,"Memo":"K"},{"GUID":"dd7a2a89492e4264a998683f0f337ab0","ID":4,"BrandID":"00004","BrandName":"可口可乐","BrandPic":"/UploadImage/BrandPic/add3ea1719774fa7aa42270d6a687643.jpg","BrandStatus":1,"Memo":"K"},{"GUID":"5ed8ebd432fd4a47b16c3af8748b2471","ID":3,"BrandID":"00003","BrandName":"托牛所","BrandPic":"/UploadImage/BrandPic/7f8f378877ef493891c484bb6642bf09.jpg","BrandStatus":1,"Memo":"T"},{"GUID":"4daf422affa247558350f502cb9cea9a","ID":8,"BrandID":"00008","BrandName":"娃哈哈","BrandPic":"","BrandStatus":1,"Memo":"W"},{"GUID":"46861f5fb2e7493596bca953b3732706","ID":5,"BrandID":"00005","BrandName":"新希望","BrandPic":"/UploadImage/BrandPic/9edfe92968614cb08decd17734038485.jpg","BrandStatus":1,"Memo":"X"}]
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
         * GUID : eb5b28cef297432183890d3d79673969
         * ID : 2
         * BrandID : 00002
         * BrandName : 百事可乐
         * BrandPic : /UploadImage/BrandPic/0c46b34a17f44c62bd842552a82ef29e.jpg
         * BrandStatus : 1
         * Memo : B
         */

        private String GUID;
        private int ID;
        private String BrandID;
        private String BrandName;
        private String BrandPic;
        private int BrandStatus;
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

        public String getBrandID() {
            return BrandID;
        }

        public void setBrandID(String BrandID) {
            this.BrandID = BrandID;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getBrandPic() {
            return BrandPic;
        }

        public void setBrandPic(String BrandPic) {
            this.BrandPic = BrandPic;
        }

        public int getBrandStatus() {
            return BrandStatus;
        }

        public void setBrandStatus(int BrandStatus) {
            this.BrandStatus = BrandStatus;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }
    }
}
