package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */

public class BrandDetailHotRecomendData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"c4c50bf8bcc44e61af7f6f71c5a80b67","ID":8,"ItemID":"01020208","ItemBaecode":"01020208","ItemName_V9":"托牛所高档牛排","ItemName":"托牛所高档牛排8","ItemDesc":null,"ItemCate1":3,"ItemCate1Name":null,"ItemCate2":13,"ItemCate2Name":null,"ItemBrand":1,"ItemSize":"袋","PurchasePrice":0,"RetailPrice":263,"MobilePrice":260,"PCPrice":260,"MinRetailPrice":65,"ItemStock":1212,"ItemPic1":"/UploadImage/ItemPic/201704/68d9aa82f1d74dac88d83dc3569aa454.jpg","ItemPic2":null,"ItemPic3":null,"ItemPic4":null,"ItemPic5":null,"QRCodePic":null,"ItemPCDetail":null,"ItemMobileDetail":null,"IsActivity":0,"ItemStatus":2,"IsDelete":1,"Memo":null,"ItemBrandName":null,"lockStock":0,"SalesSum":0,"SalesActivityNum":"ACT20170426002"}]
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
         * GUID : c4c50bf8bcc44e61af7f6f71c5a80b67
         * ID : 8
         * ItemID : 01020208
         * ItemBaecode : 01020208
         * ItemName_V9 : 托牛所高档牛排
         * ItemName : 托牛所高档牛排8
         * ItemDesc : null
         * ItemCate1 : 3
         * ItemCate1Name : null
         * ItemCate2 : 13
         * ItemCate2Name : null
         * ItemBrand : 1
         * ItemSize : 袋
         * PurchasePrice : 0
         * RetailPrice : 263
         * MobilePrice : 260
         * PCPrice : 260
         * MinRetailPrice : 65
         * ItemStock : 1212
         * ItemPic1 : /UploadImage/ItemPic/201704/68d9aa82f1d74dac88d83dc3569aa454.jpg
         * ItemPic2 : null
         * ItemPic3 : null
         * ItemPic4 : null
         * ItemPic5 : null
         * QRCodePic : null
         * ItemPCDetail : null
         * ItemMobileDetail : null
         * IsActivity : 0
         * ItemStatus : 2
         * IsDelete : 1
         * Memo : null
         * ItemBrandName : null
         * lockStock : 0
         * SalesSum : 0
         * SalesActivityNum : ACT20170426002
         */

        private String GUID;
        private int ID;
        private String ItemID;
        private String ItemBaecode;
        private String ItemName_V9;
        private String ItemName;
        private Object ItemDesc;
        private String ItemCate1;
        private Object ItemCate1Name;
        private String ItemCate2;
        private Object ItemCate2Name;
        private String ItemBrand;
        private String ItemSize;
        private String PurchasePrice;
        private String RetailPrice;
        private String MobilePrice;
        private String PCPrice;
        private String MinRetailPrice;
        private String ItemStock;
        private String ItemPic1;
        private Object ItemPic2;
        private Object ItemPic3;
        private Object ItemPic4;
        private Object ItemPic5;
        private Object QRCodePic;
        private Object ItemPCDetail;
        private Object ItemMobileDetail;
        private String IsActivity;
        private String ItemStatus;
        private String IsDelete;
        private Object Memo;
        private Object ItemBrandName;
        private String lockStock;
        private String SalesSum;
        private String SalesActivityNum;

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

        public String getItemID() {
            return ItemID;
        }

        public void setItemID(String ItemID) {
            this.ItemID = ItemID;
        }

        public String getItemBaecode() {
            return ItemBaecode;
        }

        public void setItemBaecode(String ItemBaecode) {
            this.ItemBaecode = ItemBaecode;
        }

        public String getItemName_V9() {
            return ItemName_V9;
        }

        public void setItemName_V9(String ItemName_V9) {
            this.ItemName_V9 = ItemName_V9;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public Object getItemDesc() {
            return ItemDesc;
        }

        public void setItemDesc(Object ItemDesc) {
            this.ItemDesc = ItemDesc;
        }

        public String getItemCate1() {
            return ItemCate1;
        }

        public void setItemCate1(String ItemCate1) {
            this.ItemCate1 = ItemCate1;
        }

        public Object getItemCate1Name() {
            return ItemCate1Name;
        }

        public void setItemCate1Name(Object ItemCate1Name) {
            this.ItemCate1Name = ItemCate1Name;
        }

        public String getItemCate2() {
            return ItemCate2;
        }

        public void setItemCate2(String ItemCate2) {
            this.ItemCate2 = ItemCate2;
        }

        public Object getItemCate2Name() {
            return ItemCate2Name;
        }

        public void setItemCate2Name(Object ItemCate2Name) {
            this.ItemCate2Name = ItemCate2Name;
        }

        public String getItemBrand() {
            return ItemBrand;
        }

        public void setItemBrand(String ItemBrand) {
            this.ItemBrand = ItemBrand;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public String getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(String PurchasePrice) {
            this.PurchasePrice = PurchasePrice;
        }

        public String getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(String RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public String getMobilePrice() {
            return MobilePrice;
        }

        public void setMobilePrice(String MobilePrice) {
            this.MobilePrice = MobilePrice;
        }

        public String getPCPrice() {
            return PCPrice;
        }

        public void setPCPrice(String PCPrice) {
            this.PCPrice = PCPrice;
        }

        public String getMinRetailPrice() {
            return MinRetailPrice;
        }

        public void setMinRetailPrice(String MinRetailPrice) {
            this.MinRetailPrice = MinRetailPrice;
        }

        public String getItemStock() {
            return ItemStock;
        }

        public void setItemStock(String ItemStock) {
            this.ItemStock = ItemStock;
        }

        public String getItemPic1() {
            return ItemPic1;
        }

        public void setItemPic1(String ItemPic1) {
            this.ItemPic1 = ItemPic1;
        }

        public Object getItemPic2() {
            return ItemPic2;
        }

        public void setItemPic2(Object ItemPic2) {
            this.ItemPic2 = ItemPic2;
        }

        public Object getItemPic3() {
            return ItemPic3;
        }

        public void setItemPic3(Object ItemPic3) {
            this.ItemPic3 = ItemPic3;
        }

        public Object getItemPic4() {
            return ItemPic4;
        }

        public void setItemPic4(Object ItemPic4) {
            this.ItemPic4 = ItemPic4;
        }

        public Object getItemPic5() {
            return ItemPic5;
        }

        public void setItemPic5(Object ItemPic5) {
            this.ItemPic5 = ItemPic5;
        }

        public Object getQRCodePic() {
            return QRCodePic;
        }

        public void setQRCodePic(Object QRCodePic) {
            this.QRCodePic = QRCodePic;
        }

        public Object getItemPCDetail() {
            return ItemPCDetail;
        }

        public void setItemPCDetail(Object ItemPCDetail) {
            this.ItemPCDetail = ItemPCDetail;
        }

        public Object getItemMobileDetail() {
            return ItemMobileDetail;
        }

        public void setItemMobileDetail(Object ItemMobileDetail) {
            this.ItemMobileDetail = ItemMobileDetail;
        }

        public String getIsActivity() {
            return IsActivity;
        }

        public void setIsActivity(String IsActivity) {
            this.IsActivity = IsActivity;
        }

        public String getItemStatus() {
            return ItemStatus;
        }

        public void setItemStatus(String ItemStatus) {
            this.ItemStatus = ItemStatus;
        }

        public String getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(String IsDelete) {
            this.IsDelete = IsDelete;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public Object getItemBrandName() {
            return ItemBrandName;
        }

        public void setItemBrandName(Object ItemBrandName) {
            this.ItemBrandName = ItemBrandName;
        }

        public String getLockStock() {
            return lockStock;
        }

        public void setLockStock(String lockStock) {
            this.lockStock = lockStock;
        }

        public String getSalesSum() {
            return SalesSum;
        }

        public void setSalesSum(String SalesSum) {
            this.SalesSum = SalesSum;
        }

        public String getSalesActivityNum() {
            return SalesActivityNum;
        }

        public void setSalesActivityNum(String SalesActivityNum) {
            this.SalesActivityNum = SalesActivityNum;
        }
    }
}
