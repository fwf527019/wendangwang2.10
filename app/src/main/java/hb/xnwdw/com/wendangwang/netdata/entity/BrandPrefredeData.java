package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class BrandPrefredeData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"62dc8a1904e948c4884b87c601d6dd9b","ID":1,"BrandID":"1","BrandPic":"/UploadImage/BrandPic/7aa4bb3ffab84324a5f8ad8fef6789cd.png","BrandName":"康师傅","IsSelected":1,"EditTime":"2017-04-12 15:58:20","ArrivalsWeight":1,"ArrivalsStatus":1,"Memo":null,"Brand":null},{"GUID":"598bdf5971da443f8cb5523d7f2c78fa","ID":2,"BrandID":"2","BrandPic":"/UploadImage/BrandArrivals/d9e67809-0f5f-4c44-b474-acc599801a1d.jpg","BrandName":"百事可乐","IsSelected":1,"EditTime":"2017-04-12 16:00:55","ArrivalsWeight":2,"ArrivalsStatus":1,"Memo":null,"Brand":null},{"GUID":"3542714ba4114d7a8659afdf1cb8146e","ID":3,"BrandID":"3","BrandPic":"/UploadImage/BrandArrivals/ba470d6f-c39d-4dc9-907e-28d9d53dd1bb.jpg","BrandName":"托牛所","IsSelected":1,"EditTime":"2017-04-12 16:01:07","ArrivalsWeight":3,"ArrivalsStatus":1,"Memo":null,"Brand":null},{"GUID":"11dbb86d319a4891b071dd0818910494","ID":4,"BrandID":"1","BrandPic":"/UploadImage/BrandPic/7aa4bb3ffab84324a5f8ad8fef6789cd.png","BrandName":"康师傅","IsSelected":1,"EditTime":"2017-04-13 14:44:12","ArrivalsWeight":6,"ArrivalsStatus":1,"Memo":null,"Brand":null}]
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
         * GUID : 62dc8a1904e948c4884b87c601d6dd9b
         * ID : 1
         * BrandID : 1
         * BrandPic : /UploadImage/BrandPic/7aa4bb3ffab84324a5f8ad8fef6789cd.png
         * BrandName : 康师傅
         * IsSelected : 1
         * EditTime : 2017-04-12 15:58:20
         * ArrivalsWeight : 1
         * ArrivalsStatus : 1
         * Memo : null
         * Brand : null
         */

        private String GUID;
        private String ID;
        private String BrandID;
        private String BrandPic;
        private String BrandName;
        private String IsSelected;
        private String EditTime;
        private String ArrivalsWeight;
        private String ArrivalsStatus;
        private Object Memo;
        private Object Brand;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getBrandID() {
            return BrandID;
        }

        public void setBrandID(String BrandID) {
            this.BrandID = BrandID;
        }

        public String getBrandPic() {
            return BrandPic;
        }

        public void setBrandPic(String BrandPic) {
            this.BrandPic = BrandPic;
        }

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getIsSelected() {
            return IsSelected;
        }

        public void setIsSelected(String IsSelected) {
            this.IsSelected = IsSelected;
        }

        public String getEditTime() {
            return EditTime;
        }

        public void setEditTime(String EditTime) {
            this.EditTime = EditTime;
        }

        public String getArrivalsWeight() {
            return ArrivalsWeight;
        }

        public void setArrivalsWeight(String ArrivalsWeight) {
            this.ArrivalsWeight = ArrivalsWeight;
        }

        public String getArrivalsStatus() {
            return ArrivalsStatus;
        }

        public void setArrivalsStatus(String ArrivalsStatus) {
            this.ArrivalsStatus = ArrivalsStatus;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public Object getBrand() {
            return Brand;
        }

        public void setBrand(Object Brand) {
            this.Brand = Brand;
        }
    }
}
