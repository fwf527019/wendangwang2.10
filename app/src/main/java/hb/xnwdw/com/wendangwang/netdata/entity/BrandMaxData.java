package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/5/19.
 */

public class BrandMaxData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"eb5b28cef297432183890d3d79673969","ID":2,"BrandID":"00002","BrandName":"百事可乐","BrandPic":"/UploadImage/BrandPic/0c46b34a17f44c62bd842552a82ef29e.jpg","BrandStatus":1,"Memo":null,"ItemCount":2,"SalesCount":0}
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
         * GUID : eb5b28cef297432183890d3d79673969
         * ID : 2
         * BrandID : 00002
         * BrandName : 百事可乐
         * BrandPic : /UploadImage/BrandPic/0c46b34a17f44c62bd842552a82ef29e.jpg
         * BrandStatus : 1
         * Memo : null
         * ItemCount : 2
         * SalesCount : 0
         */

        private String GUID;
        private int ID;
        private String BrandID;
        private String BrandName;
        private String BrandPic;
        private int BrandStatus;
        private Object Memo;
        private int ItemCount;
        private int SalesCount;

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

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public int getItemCount() {
            return ItemCount;
        }

        public void setItemCount(int ItemCount) {
            this.ItemCount = ItemCount;
        }

        public int getSalesCount() {
            return SalesCount;
        }

        public void setSalesCount(int SalesCount) {
            this.SalesCount = SalesCount;
        }
    }
}
