package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class ShearchResutData {

    /**
     * page : 1
     * pageSize : 20
     * sumSize : 1
     * count : 13
     * obj : [{"GUID":"3a3e0288696140178d793ebab2f47241","ID":1,"ItemID":"01020201","ItemBaecode":"01020201","ItemName_V9":"托牛所高档牛排","ItemName":"托牛所高档牛排1","ItemDesc":null,"ItemCate1":1,"ItemCate1Name":null,"ItemCate2":6,"ItemCate2Name":null,"ItemBrand":2,"ItemSize":"袋","PurchasePrice":0,"RetailPrice":56,"MobilePrice":50,"PCPrice":50,"MinRetailPrice":36,"ItemStock":999,"ItemPic1":"/UploadImage/ItemPic/201705/a61098e8b7ed485bbf5fad7003fe40fa.png","ItemPic2":"/UploadImage/ItemPic/201705/db6b3dbbf946456ead48f4f460023a28.png","ItemPic3":"/UploadImage/ItemPic/201705/c2beaccbe1054fd68d1a7faf38117574.png","ItemPic4":"/UploadImage/ItemPic/201705/eb3c515cab6247168258580667ec296a.png","ItemPic5":"/UploadImage/ItemPic/201705/065ecc27a8934a36bc4b9b57bbc0c98a.png","QRCodePic":null,"ItemPCDetail":"<p><img src=\"../../images/pc/sp_detail_01.jpg\"/><img src=\"../../images/pc/sp_detail_02.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":11,"ItemStatus":2,"IsDelete":1,"Memo":null,"ItemBrandName":null,"lockStock":0,"SalesSum":0,"SalesActivityNum":null}]
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
         * GUID : 3a3e0288696140178d793ebab2f47241
         * ID : 1
         * ItemID : 01020201
         * ItemBaecode : 01020201
         * ItemName_V9 : 托牛所高档牛排
         * ItemName : 托牛所高档牛排1
         * ItemDesc : null
         * ItemCate1 : 1
         * ItemCate1Name : null
         * ItemCate2 : 6
         * ItemCate2Name : null
         * ItemBrand : 2
         * ItemSize : 袋
         * PurchasePrice : 0
         * RetailPrice : 56
         * MobilePrice : 50
         * PCPrice : 50
         * MinRetailPrice : 36
         * ItemStock : 999
         * ItemPic1 : /UploadImage/ItemPic/201705/a61098e8b7ed485bbf5fad7003fe40fa.png
         * ItemPic2 : /UploadImage/ItemPic/201705/db6b3dbbf946456ead48f4f460023a28.png
         * ItemPic3 : /UploadImage/ItemPic/201705/c2beaccbe1054fd68d1a7faf38117574.png
         * ItemPic4 : /UploadImage/ItemPic/201705/eb3c515cab6247168258580667ec296a.png
         * ItemPic5 : /UploadImage/ItemPic/201705/065ecc27a8934a36bc4b9b57bbc0c98a.png
         * QRCodePic : null
         * ItemPCDetail : <p><img src="../../images/pc/sp_detail_01.jpg"/><img src="../../images/pc/sp_detail_02.jpg"/></p>
         * ItemMobileDetail : null
         * IsActivity : 11
         * ItemStatus : 2
         * IsDelete : 1
         * Memo : null
         * ItemBrandName : null
         * lockStock : 0
         * SalesSum : 0
         * SalesActivityNum : null
         */

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
        private int PurchasePrice;
        private int RetailPrice;
        private String MobilePrice;
        private int PCPrice;
        private int MinRetailPrice;
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
        private String Memo;
        private String ItemBrandName;
        private int lockStock;
        private int SalesSum;
        private Object SalesActivityNum;
        private String IsNewMember;
        private String ActFlag;


        public String getIsNewMember() {
            return IsNewMember;
        }

        public void setIsNewMember(String isNewMember) {
            IsNewMember = isNewMember;
        }

        public String getActFlag() {
            return ActFlag;
        }

        public void setActFlag(String actFlag) {
            ActFlag = actFlag;
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

        public int getPurchasePrice() {
            return PurchasePrice;
        }

        public void setPurchasePrice(int PurchasePrice) {
            this.PurchasePrice = PurchasePrice;
        }

        public int getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(int RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public String getMobilePrice() {
            return MobilePrice;
        }

        public void setMobilePrice(String MobilePrice) {
            this.MobilePrice = MobilePrice;
        }

        public int getPCPrice() {
            return PCPrice;
        }

        public void setPCPrice(int PCPrice) {
            this.PCPrice = PCPrice;
        }

        public int getMinRetailPrice() {
            return MinRetailPrice;
        }

        public void setMinRetailPrice(int MinRetailPrice) {
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

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getItemBrandName() {
            return ItemBrandName;
        }

        public void setItemBrandName(String ItemBrandName) {
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

        public Object getSalesActivityNum() {
            return SalesActivityNum;
        }

        public void setSalesActivityNum(Object SalesActivityNum) {
            this.SalesActivityNum = SalesActivityNum;
        }
    }
}
