package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/6/22.
 */

public class OfflineGoodData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"0c99906efb30415c82ede85234ee6875","ID":22,"ItemId":2,"ItemNum":null,"ItemName":"托牛所中档牛排1","RetailPrice":35,"OfflineQRPrice":25,"ItemPic":"/UploadImage/ItemPic/201705/c3b64e890bec460aa88681feebcfec61.png","ItemSize":"袋","Store_Id":4,"StoreName":"稳当生活第一家","OfflineQRCode":"/UploadImage/OfflineStoreQRPic/0c99906efb30415c82ede85234ee6875.jpg","SalesCount":0,"Memo":null}
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
         * GUID : 0c99906efb30415c82ede85234ee6875
         * ID : 22
         * ItemId : 2
         * ItemNum : null
         * ItemName : 托牛所中档牛排1
         * RetailPrice : 35
         * OfflineQRPrice : 25
         * ItemPic : /UploadImage/ItemPic/201705/c3b64e890bec460aa88681feebcfec61.png
         * ItemSize : 袋
         * Store_Id : 4
         * StoreName : 稳当生活第一家
         * OfflineQRCode : /UploadImage/OfflineStoreQRPic/0c99906efb30415c82ede85234ee6875.jpg
         * SalesCount : 0
         * Memo : null
         */

        private String GUID;
        private int ID;
        private int ItemId;
        private Object ItemNum;
        private String ItemName;
        private String RetailPrice;
        private double OfflineQRPrice;
        private String ItemPic;
        private String ItemSize;
        private int Store_Id;
        private String StoreName;
        private String OfflineQRCode;
        private int SalesCount;
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

        public int getItemId() {
            return ItemId;
        }

        public void setItemId(int ItemId) {
            this.ItemId = ItemId;
        }

        public Object getItemNum() {
            return ItemNum;
        }

        public void setItemNum(Object ItemNum) {
            this.ItemNum = ItemNum;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(String RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public double getOfflineQRPrice() {
            return OfflineQRPrice;
        }

        public void setOfflineQRPrice(double OfflineQRPrice) {
            this.OfflineQRPrice = OfflineQRPrice;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public int getStore_Id() {
            return Store_Id;
        }

        public void setStore_Id(int Store_Id) {
            this.Store_Id = Store_Id;
        }

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public String getOfflineQRCode() {
            return OfflineQRCode;
        }

        public void setOfflineQRCode(String OfflineQRCode) {
            this.OfflineQRCode = OfflineQRCode;
        }

        public int getSalesCount() {
            return SalesCount;
        }

        public void setSalesCount(int SalesCount) {
            this.SalesCount = SalesCount;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }
    }
}
