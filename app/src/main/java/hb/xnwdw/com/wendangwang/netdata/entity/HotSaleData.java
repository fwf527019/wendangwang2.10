package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class HotSaleData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"52e5959fc7e54bada69759c8272f40f6","ID":11,"ItemID":"01020211","ItemBaecode":"010202011","ItemName_V9":"托牛所高档牛排","ItemName":"托牛所高档牛排11","ItemDesc":null,"ItemCate1":3,"ItemCate1Name":null,"ItemCate2":16,"ItemCate2Name":null,"ItemBrand":4,"ItemSize":"袋","PurchasePrice":0,"RetailPrice":263,"MobilePrice":266,"PCPrice":266,"MinRetailPrice":15,"ItemStock":32,"ItemPic1":"/UploadImage/ItemPic/201705/62fbbf17d46b439c906b44c54b0b4975.png","ItemPic2":"/UploadImage/ItemPic/201705/1b42b54e5da54f5196a7a945bd307525.png","ItemPic3":"/UploadImage/ItemPic/201705/f507f98a544f4a34ade3f7a9980dd271.png","ItemPic4":"/UploadImage/ItemPic/201705/588edf0ee83c4fcdade5e083bd7b87fe.png","ItemPic5":"/UploadImage/ItemPic/201705/5471c69588444aeea8da7cdbce60ef9a.png","QRCodePic":null,"ItemPCDetail":"<p><img src=\"../../images/pc/sp_detail_01.jpg\"/><img src=\"../../images/pc/sp_detail_02.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":0,"ItemStatus":2,"IsDelete":2,"Memo":null,"ItemBrandName":null,"lockStock":0,"SalesSum":0,"SalesActivityNum":"ACT20170426002"},{"GUID":"ef25950ab44642f892c78d8d39d889e8","ID":10,"ItemID":"01020210","ItemBaecode":"010202010","ItemName_V9":"托牛所高档牛排","ItemName":"托牛所高档牛排10","ItemDesc":null,"ItemCate1":3,"ItemCate1Name":null,"ItemCate2":15,"ItemCate2Name":null,"ItemBrand":3,"ItemSize":"袋","PurchasePrice":0,"RetailPrice":36,"MobilePrice":35,"PCPrice":35,"MinRetailPrice":23,"ItemStock":6565,"ItemPic1":"/UploadImage/ItemPic/201705/bf64bccb87854e0b84794c2c2fe4230b.png","ItemPic2":"/UploadImage/ItemPic/201705/ad6b4c5cf99847818387c4d5eb726593.png","ItemPic3":"/UploadImage/ItemPic/201705/c0191251a4c54947a4e074281793a3fa.png","ItemPic4":"/UploadImage/ItemPic/201705/95e04511b50d4c4fb678132a88b7c285.png","ItemPic5":"/UploadImage/ItemPic/201705/fea2a4ca5b73482eb1810529d0de58b0.png","QRCodePic":null,"ItemPCDetail":"<p><img src=\"../../images/pc/sp_detail_01.jpg\"/><img src=\"../../images/pc/sp_detail_02.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":14,"ItemStatus":2,"IsDelete":1,"Memo":null,"ItemBrandName":null,"lockStock":0,"SalesSum":0,"SalesActivityNum":"ACT20170426002"},{"GUID":"3ba93ddd16244dac8e36e40443971bfb","ID":9,"ItemID":"01020209","ItemBaecode":"01020209","ItemName_V9":"托牛所高档牛排","ItemName":"托牛所高档牛排9","ItemDesc":null,"ItemCate1":3,"ItemCate1Name":null,"ItemCate2":14,"ItemCate2Name":null,"ItemBrand":2,"ItemSize":"袋","PurchasePrice":0,"RetailPrice":62,"MobilePrice":58.5,"PCPrice":60,"MinRetailPrice":56,"ItemStock":64555,"ItemPic1":"/UploadImage/ItemPic/201705/9ad697c11a6b4f5db81e70ce2204692c.png","ItemPic2":"/UploadImage/ItemPic/201705/467bcaecf8234d0ea9fab3cc1733498a.png","ItemPic3":"/UploadImage/ItemPic/201705/d5aa3e93e44540beb6b2f7730d2a24ca.png","ItemPic4":"/UploadImage/ItemPic/201705/a1e358ec99004b31be725052da0c31e0.png","ItemPic5":"/UploadImage/ItemPic/201705/d405dcfe5c774a4e992523a64cabc4e2.png","QRCodePic":null,"ItemPCDetail":"<p><img src=\"../../images/pc/sp_detail_01.jpg\"/><img src=\"../../images/pc/sp_detail_02.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":0,"ItemStatus":2,"IsDelete":1,"Memo":null,"ItemBrandName":null,"lockStock":0,"SalesSum":0,"SalesActivityNum":"ACT20170426002"}]
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * GUID : 52e5959fc7e54bada69759c8272f40f6
         * ID : 11
         * ItemID : 01020211
         * ItemBaecode : 010202011
         * ItemName_V9 : 托牛所高档牛排
         * ItemName : 托牛所高档牛排11
         * ItemDesc : null
         * ItemCate1 : 3
         * ItemCate1Name : null
         * ItemCate2 : 16
         * ItemCate2Name : null
         * ItemBrand : 4
         * ItemSize : 袋
         * PurchasePrice : 0
         * RetailPrice : 263
         * MobilePrice : 266
         * PCPrice : 266
         * MinRetailPrice : 15
         * ItemStock : 32
         * ItemPic1 : /UploadImage/ItemPic/201705/62fbbf17d46b439c906b44c54b0b4975.png
         * ItemPic2 : /UploadImage/ItemPic/201705/1b42b54e5da54f5196a7a945bd307525.png
         * ItemPic3 : /UploadImage/ItemPic/201705/f507f98a544f4a34ade3f7a9980dd271.png
         * ItemPic4 : /UploadImage/ItemPic/201705/588edf0ee83c4fcdade5e083bd7b87fe.png
         * ItemPic5 : /UploadImage/ItemPic/201705/5471c69588444aeea8da7cdbce60ef9a.png
         * QRCodePic : null
         * ItemPCDetail : <p><img src="../../images/pc/sp_detail_01.jpg"/><img src="../../images/pc/sp_detail_02.jpg"/></p>
         * ItemMobileDetail : null
         * IsActivity : 0
         * ItemStatus : 2
         * IsDelete : 2
         * Memo : null
         * ItemBrandName : null
         * lockStock : 0
         * SalesSum : 0
         * SalesActivityNum : ACT20170426002
         */
        private String LimitNewMember;
        private String GUID;
        private int ID;
        private String ItemID;
        private String ItemBaecode;
        private String ItemName_V9;
        private String ItemName;
        private Object ItemDesc;
        private int ItemCate1;
        private Object ItemCate1Name;
        private int ItemCate2;
        private Object ItemCate2Name;
        private int ItemBrand;
        private String ItemSize;
        private String PurchasePrice;
        private String RetailPrice;
        private String MobilePrice;
        private String PCPrice;
        private String MinRetailPrice;
        private int ItemStock;
        private String ItemPic1;
        private String ItemPic2;
        private String ItemPic3;
        private String ItemPic4;
        private String ItemPic5;
        private Object QRCodePic;
        private String ItemPCDetail;
        private Object ItemMobileDetail;
        private int IsActivity;
        private int ItemStatus;
        private int IsDelete;
        private Object Memo;
        private Object ItemBrandName;
        private int lockStock;
        private int SalesSum;
        private String SalesActivityNum;
        private String ActFlag;
        private String IsNewMember;

        public String getActFlag() {
            return ActFlag;
        }

        public void setActFlag(String actFlag) {
            ActFlag = actFlag;
        }

        public String getIsNewMember() {
            return IsNewMember;
        }

        public void setIsNewMember(String isNewMember) {
            IsNewMember = isNewMember;
        }

        public String getLimitNewMember() {
            return LimitNewMember;
        }

        public void setLimitNewMember(String limitNewMember) {
            LimitNewMember = limitNewMember;
        }

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

        public int getItemCate1() {
            return ItemCate1;
        }

        public void setItemCate1(int ItemCate1) {
            this.ItemCate1 = ItemCate1;
        }

        public Object getItemCate1Name() {
            return ItemCate1Name;
        }

        public void setItemCate1Name(Object ItemCate1Name) {
            this.ItemCate1Name = ItemCate1Name;
        }

        public int getItemCate2() {
            return ItemCate2;
        }

        public void setItemCate2(int ItemCate2) {
            this.ItemCate2 = ItemCate2;
        }

        public Object getItemCate2Name() {
            return ItemCate2Name;
        }

        public void setItemCate2Name(Object ItemCate2Name) {
            this.ItemCate2Name = ItemCate2Name;
        }

        public int getItemBrand() {
            return ItemBrand;
        }

        public void setItemBrand(int ItemBrand) {
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

        public int getItemStock() {
            return ItemStock;
        }

        public void setItemStock(int ItemStock) {
            this.ItemStock = ItemStock;
        }

        public String getItemPic1() {
            return ItemPic1;
        }

        public void setItemPic1(String ItemPic1) {
            this.ItemPic1 = ItemPic1;
        }

        public String getItemPic2() {
            return ItemPic2;
        }

        public void setItemPic2(String ItemPic2) {
            this.ItemPic2 = ItemPic2;
        }

        public String getItemPic3() {
            return ItemPic3;
        }

        public void setItemPic3(String ItemPic3) {
            this.ItemPic3 = ItemPic3;
        }

        public String getItemPic4() {
            return ItemPic4;
        }

        public void setItemPic4(String ItemPic4) {
            this.ItemPic4 = ItemPic4;
        }

        public String getItemPic5() {
            return ItemPic5;
        }

        public void setItemPic5(String ItemPic5) {
            this.ItemPic5 = ItemPic5;
        }

        public Object getQRCodePic() {
            return QRCodePic;
        }

        public void setQRCodePic(Object QRCodePic) {
            this.QRCodePic = QRCodePic;
        }

        public String getItemPCDetail() {
            return ItemPCDetail;
        }

        public void setItemPCDetail(String ItemPCDetail) {
            this.ItemPCDetail = ItemPCDetail;
        }

        public Object getItemMobileDetail() {
            return ItemMobileDetail;
        }

        public void setItemMobileDetail(Object ItemMobileDetail) {
            this.ItemMobileDetail = ItemMobileDetail;
        }

        public int getIsActivity() {
            return IsActivity;
        }

        public void setIsActivity(int IsActivity) {
            this.IsActivity = IsActivity;
        }

        public int getItemStatus() {
            return ItemStatus;
        }

        public void setItemStatus(int ItemStatus) {
            this.ItemStatus = ItemStatus;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
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

        public int getLockStock() {
            return lockStock;
        }

        public void setLockStock(int lockStock) {
            this.lockStock = lockStock;
        }

        public int getSalesSum() {
            return SalesSum;
        }

        public void setSalesSum(int SalesSum) {
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
