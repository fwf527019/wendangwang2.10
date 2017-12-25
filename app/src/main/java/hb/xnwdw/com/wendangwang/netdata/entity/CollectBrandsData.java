package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12.
 */
public class CollectBrandsData {


    /**
     * page : 1
     * pageSize : 8
     * sumSize : 2
     * count : 10
     * obj : [{"GUID":"06b651c6ed2d4fff80d42488ef78db05","ID":56,"BrandID":"1196","BrandName":"山姆大叔","BrandPic":"","BrandStatus":1,"Memo":null,"ItemCount":0,"SalesCount":0},{"GUID":"9c78967b3d974848b2b0df35a91dc7cd","ID":2,"BrandID":"QS","BrandName":"稳当生活","BrandPic":"/UploadImage/BrandPic/201712/compresse6ef2cd616d74ce98f0f4ed7d8efb900.png","BrandStatus":1,"Memo":null,"ItemCount":0,"SalesCount":0},{"GUID":"9c78967b3d974848b2b0df35a91dc7cd","ID":2,"BrandID":"QS","BrandName":"稳当生活","BrandPic":"/UploadImage/BrandPic/201712/compresse6ef2cd616d74ce98f0f4ed7d8efb900.png","BrandStatus":1,"Memo":null,"ItemCount":0,"SalesCount":0}]
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
         * GUID : 06b651c6ed2d4fff80d42488ef78db05
         * ID : 56
         * BrandID : 1196
         * BrandName : 山姆大叔
         * BrandPic :
         * BrandStatus : 1
         * Memo : null
         * ItemCount : 0
         * SalesCount : 0
         */

        private String GUID;
        private String ID;
        private String BrandID;
        private String BrandName;
        private String BrandPic;
        private int BrandStatus;
        private Object Memo;
        private int ItemCount;
        private int SalesCount;
        private boolean isCollect=false;


        public boolean isCollect() {
            return isCollect;
        }

        public void setCollect(boolean collect) {
            isCollect = collect;
        }

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
