package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class HistoryData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"ID":39,"ItemID":12,"ItemName":"托牛所高档牛排12","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/19282ac644a149588107e8f81e9babae.png","SeeTm":"2017-05-23","ItemSize":"袋","CostUnit":0,"Unit":100,"stItemsCount":0,"totalCount":0,"State":""}]
     * dataStatus : -1
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
         * ID : 39
         * ItemID : 12
         * ItemName : 托牛所高档牛排12
         * BrandName : null
         * ItemPic : /UploadImage/ItemPic/201705/19282ac644a149588107e8f81e9babae.png
         * SeeTm : 2017-05-23
         * ItemSize : 袋
         * CostUnit : 0.0
         * Unit : 100.0
         * stItemsCount : 0
         * totalCount : 0
         * State :
         */

        private int ID;
        private int ItemID;
        private String ItemName;
        private Object BrandName;
        private String ItemPic;
        private String SeeTm;
        private String ItemSize;
        private double CostUnit;
        private double Unit;
        private int stItemsCount;
        private int totalCount;
        private String State;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getItemID() {
            return ItemID;
        }

        public void setItemID(int ItemID) {
            this.ItemID = ItemID;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public Object getBrandName() {
            return BrandName;
        }

        public void setBrandName(Object BrandName) {
            this.BrandName = BrandName;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }

        public String getSeeTm() {
            return SeeTm;
        }

        public void setSeeTm(String SeeTm) {
            this.SeeTm = SeeTm;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public double getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(double CostUnit) {
            this.CostUnit = CostUnit;
        }

        public double getUnit() {
            return Unit;
        }

        public void setUnit(double Unit) {
            this.Unit = Unit;
        }

        public int getStItemsCount() {
            return stItemsCount;
        }

        public void setStItemsCount(int stItemsCount) {
            this.stItemsCount = stItemsCount;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
