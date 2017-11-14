package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/12.
 */

public class GoodsBrandsData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"4a118723e4aa4528a1b9fba08e1f0fda","ID":129,"BrandID":"7coin","BrandName":"7coin","BrandPic":"/UploadImage/BrandPic/61f92a2dff924ed688f513997546b786.png","BrandStatus":1,"Memo":"7","ItemCount":0,"SalesCount":0},{"GUID":"750c002659d3453d93a2d10289a62a4f","ID":65,"BrandID":"艾米","BrandName":"艾米","BrandPic":"/UploadImage/BrandPic/416aba553cf64885ad5cddf97cabbb03.png","BrandStatus":1,"Memo":"A","ItemCount":0,"SalesCount":0}]
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
         * GUID : 4a118723e4aa4528a1b9fba08e1f0fda
         * ID : 129
         * BrandID : 7coin
         * BrandName : 7coin
         * BrandPic : /UploadImage/BrandPic/61f92a2dff924ed688f513997546b786.png
         * BrandStatus : 1
         * Memo : 7
         * ItemCount : 0
         * SalesCount : 0
         */

        private String GUID;
        private int ID;
        private String BrandID;
        private String BrandName;
        private String BrandPic;
        private int BrandStatus;
        private String Memo;
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

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
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
