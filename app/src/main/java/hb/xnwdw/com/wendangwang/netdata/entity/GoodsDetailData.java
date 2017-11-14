package hb.xnwdw.com.wendangwang.netdata.entity;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public class GoodsDetailData implements Serializable {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"ItemInfo":{"GUID":"4f5b8841c7e2406fabff12287af55512","ID":1064,"ItemID":"1108050090","ItemBaecode":"4714113087143","ItemName_V9":null,"ItemName":"鲜果","ItemDesc":"奇异果","ItemCate1":3,"ItemCate1Name":"进口食品","ItemCate2":12,"ItemCate2Name":"葡萄提子","ItemBrand":135,"ItemSize":"60g","PurchasePrice":0,"RetailPrice":20,"MobilePrice":20,"PCPrice":20,"MinRetailPrice":0,"ItemStock":994,"ItemPic1":"/UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg","ItemPic2":"/UploadImage/ItemPic/201707/03800523096b46198a286b1f1dfe3b4e.jpeg","ItemPic3":"/UploadImage/ItemPic/201707/e959ada3ce3f4d73b5aa9a09ca1d6ff4.jpeg","ItemPic4":"/UploadImage/ItemPic/201707/ddf7749de22d4996aa5c71e907e53a0f.jpeg","ItemPic5":null,"QRCodePic":"/UploadImage/ItemQRPic/201707/4f5b8841c7e2406fabff12287af55512.png","ItemPCDetail":"<p><img src=\"/Content/ueditor/net/upload/image/20170719/6363606829817180266217490.jpg\" title=\"库尔勒香梨.jpg\" alt=\"库尔勒香梨.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":0,"ItemStatus":2,"IsDelete":0,"Memo":"[{\"ID\":1064,\"ItemSize\":\"60g\",\"UnitName\":\"袋\"}]","ItemBrandName":"3颗豆","lockStock":0,"SalesSum":5,"SalesActivityNum":null,"OfflineQRPrice":null,"LeastCount":0,"MostCount":0,"virtualSaleSum":45,"unitName":null,"TaxRate":null,"ActFlag":null,"IsNewMember":0},"ActInfo":{"OrderId":0,"ID":1064,"ItemName":"鲜果","ItemCate1":3,"ItemCate2":12,"ItemPic":"/UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg","ItemBrandName":"3颗豆","ItemSize":"袋","CostUnit":20,"DiscountUnit":20,"Amount":1,"campaignCount":0,"Subtotal":20,"InventoryStatus":"有货","ActivityStatus":0,"ActivityNum":"","ActivityContent":"无","activiName":"无","presentItemList":null,"pagePresentItemList":null,"SeckillEndTime":"0001-01-01 00:00:00","LimitNewMember":0,"LeastAmount":0,"ReduceMoney":0}}
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

    public static class ObjBean implements Serializable {
        /**
         * ItemInfo : {"GUID":"4f5b8841c7e2406fabff12287af55512","ID":1064,"ItemID":"1108050090","ItemBaecode":"4714113087143","ItemName_V9":null,"ItemName":"鲜果","ItemDesc":"奇异果","ItemCate1":3,"ItemCate1Name":"进口食品","ItemCate2":12,"ItemCate2Name":"葡萄提子","ItemBrand":135,"ItemSize":"60g","PurchasePrice":0,"RetailPrice":20,"MobilePrice":20,"PCPrice":20,"MinRetailPrice":0,"ItemStock":994,"ItemPic1":"/UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg","ItemPic2":"/UploadImage/ItemPic/201707/03800523096b46198a286b1f1dfe3b4e.jpeg","ItemPic3":"/UploadImage/ItemPic/201707/e959ada3ce3f4d73b5aa9a09ca1d6ff4.jpeg","ItemPic4":"/UploadImage/ItemPic/201707/ddf7749de22d4996aa5c71e907e53a0f.jpeg","ItemPic5":null,"QRCodePic":"/UploadImage/ItemQRPic/201707/4f5b8841c7e2406fabff12287af55512.png","ItemPCDetail":"<p><img src=\"/Content/ueditor/net/upload/image/20170719/6363606829817180266217490.jpg\" title=\"库尔勒香梨.jpg\" alt=\"库尔勒香梨.jpg\"/><\/p>","ItemMobileDetail":null,"IsActivity":0,"ItemStatus":2,"IsDelete":0,"Memo":"[{\"ID\":1064,\"ItemSize\":\"60g\",\"UnitName\":\"袋\"}]","ItemBrandName":"3颗豆","lockStock":0,"SalesSum":5,"SalesActivityNum":null,"OfflineQRPrice":null,"LeastCount":0,"MostCount":0,"virtualSaleSum":45,"unitName":null,"TaxRate":null,"ActFlag":null,"IsNewMember":0}
         * ActInfo : {"OrderId":0,"ID":1064,"ItemName":"鲜果","ItemCate1":3,"ItemCate2":12,"ItemPic":"/UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg","ItemBrandName":"3颗豆","ItemSize":"袋","CostUnit":20,"DiscountUnit":20,"Amount":1,"campaignCount":0,"Subtotal":20,"InventoryStatus":"有货","ActivityStatus":0,"ActivityNum":"","ActivityContent":"无","activiName":"无","presentItemList":null,"pagePresentItemList":null,"SeckillEndTime":"0001-01-01 00:00:00","LimitNewMember":0,"LeastAmount":0,"ReduceMoney":0}
         */

        private ItemInfoBean ItemInfo;
        private ActInfoBean ActInfo;

        public ItemInfoBean getItemInfo() {
            return ItemInfo;
        }

        public void setItemInfo(ItemInfoBean ItemInfo) {
            this.ItemInfo = ItemInfo;
        }

        public ActInfoBean getActInfo() {
            return ActInfo;
        }

        public void setActInfo(ActInfoBean ActInfo) {
            this.ActInfo = ActInfo;
        }

        public static class ItemInfoBean implements Serializable {
            /**
             * GUID : 4f5b8841c7e2406fabff12287af55512
             * ID : 1064
             * ItemID : 1108050090
             * ItemBaecode : 4714113087143
             * ItemName_V9 : null
             * ItemName : 鲜果
             * ItemDesc : 奇异果
             * ItemCate1 : 3
             * ItemCate1Name : 进口食品
             * ItemCate2 : 12
             * ItemCate2Name : 葡萄提子
             * ItemBrand : 135
             * ItemSize : 60g
             * PurchasePrice : 0
             * RetailPrice : 20
             * MobilePrice : 20
             * PCPrice : 20
             * MinRetailPrice : 0
             * ItemStock : 994
             * ItemPic1 : /UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg
             * ItemPic2 : /UploadImage/ItemPic/201707/03800523096b46198a286b1f1dfe3b4e.jpeg
             * ItemPic3 : /UploadImage/ItemPic/201707/e959ada3ce3f4d73b5aa9a09ca1d6ff4.jpeg
             * ItemPic4 : /UploadImage/ItemPic/201707/ddf7749de22d4996aa5c71e907e53a0f.jpeg
             * ItemPic5 : null
             * QRCodePic : /UploadImage/ItemQRPic/201707/4f5b8841c7e2406fabff12287af55512.png
             * ItemPCDetail : <p><img src="/Content/ueditor/net/upload/image/20170719/6363606829817180266217490.jpg" title="库尔勒香梨.jpg" alt="库尔勒香梨.jpg"/></p>
             * ItemMobileDetail : null
             * IsActivity : 0
             * ItemStatus : 2可购买，1已下架，else售罄
             * IsDelete : 0
             * Memo : [{"ID":1064,"ItemSize":"60g","UnitName":"袋"}]
             * ItemBrandName : 3颗豆
             * lockStock : 0
             * SalesSum : 5
             * SalesActivityNum : null
             * OfflineQRPrice : null
             * LeastCount : 0
             * MostCount : 0
             * virtualSaleSum : 45
             * unitName : null
             * TaxRate : null
             * ActFlag : null
             * IsNewMember : 0
             */

            private String GUID;
            private int ID;
            private String ItemID;
            private String ItemBaecode;
            private String ItemName_V9;
            private String ItemName;
            private String ItemDesc;
            private int ItemCate1;
            private String ItemCate1Name;
            private int ItemCate2;
            private String ItemCate2Name;
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
            private String QRCodePic;
            private String ItemPCDetail;
            private String ItemMobileDetail;
            private int IsActivity;
            private int ItemStatus;
            private int IsDelete;
            private String Memo;
            private String ItemBrandName;
            private int lockStock;
            private int SalesSum;
            private Object SalesActivityNum;
            private Object OfflineQRPrice;
            private int LeastCount;
            private int MostCount;
            private int virtualSaleSum;
            private Object unitName;
            private Object TaxRate;
            private Object ActFlag;
            private int IsNewMember;

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

            public String getItemDesc() {
                return ItemDesc;
            }

            public void setItemDesc(String ItemDesc) {
                this.ItemDesc = ItemDesc;
            }

            public int getItemCate1() {
                return ItemCate1;
            }

            public void setItemCate1(int ItemCate1) {
                this.ItemCate1 = ItemCate1;
            }

            public String getItemCate1Name() {
                return ItemCate1Name;
            }

            public void setItemCate1Name(String ItemCate1Name) {
                this.ItemCate1Name = ItemCate1Name;
            }

            public int getItemCate2() {
                return ItemCate2;
            }

            public void setItemCate2(int ItemCate2) {
                this.ItemCate2 = ItemCate2;
            }

            public String getItemCate2Name() {
                return ItemCate2Name;
            }

            public void setItemCate2Name(String ItemCate2Name) {
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

            public String getQRCodePic() {
                return QRCodePic;
            }

            public void setQRCodePic(String QRCodePic) {
                this.QRCodePic = QRCodePic;
            }

            public String getItemPCDetail() {
                return ItemPCDetail;
            }

            public void setItemPCDetail(String ItemPCDetail) {
                this.ItemPCDetail = ItemPCDetail;
            }

            public String getItemMobileDetail() {
                return ItemMobileDetail;
            }

            public void setItemMobileDetail(String ItemMobileDetail) {
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

            public Object getOfflineQRPrice() {
                return OfflineQRPrice;
            }

            public void setOfflineQRPrice(Object OfflineQRPrice) {
                this.OfflineQRPrice = OfflineQRPrice;
            }

            public int getLeastCount() {
                return LeastCount;
            }

            public void setLeastCount(int LeastCount) {
                this.LeastCount = LeastCount;
            }

            public int getMostCount() {
                return MostCount;
            }

            public void setMostCount(int MostCount) {
                this.MostCount = MostCount;
            }

            public int getVirtualSaleSum() {
                return virtualSaleSum;
            }

            public void setVirtualSaleSum(int virtualSaleSum) {
                this.virtualSaleSum = virtualSaleSum;
            }

            public Object getUnitName() {
                return unitName;
            }

            public void setUnitName(Object unitName) {
                this.unitName = unitName;
            }

            public Object getTaxRate() {
                return TaxRate;
            }

            public void setTaxRate(Object TaxRate) {
                this.TaxRate = TaxRate;
            }

            public Object getActFlag() {
                return ActFlag;
            }

            public void setActFlag(Object ActFlag) {
                this.ActFlag = ActFlag;
            }

            public int getIsNewMember() {
                return IsNewMember;
            }

            public void setIsNewMember(int IsNewMember) {
                this.IsNewMember = IsNewMember;
            }
        }

        public static class ActInfoBean implements Serializable {
            /**
             * OrderId : 0
             * ID : 1064
             * ItemName : 鲜果
             * ItemCate1 : 3
             * ItemCate2 : 12
             * ItemPic : /UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg
             * ItemBrandName : 3颗豆
             * ItemSize : 袋
             * CostUnit : 20
             * DiscountUnit : 20
             * Amount : 1
             * campaignCount : 0
             * Subtotal : 20
             * InventoryStatus : 有货
             * ActivityStatus : 0
             * ActivityNum :
             * ActivityContent : 无
             * activiName : 无
             * presentItemList : null
             * pagePresentItemList : null
             * SeckillEndTime : 0001-01-01 00:00:00
             * LimitNewMember : 0
             * LeastAmount : 0
             * ReduceMoney : 0
             */

            private int OrderId;
            private int ID;
            private String ItemName;
            private int ItemCate1;
            private int ItemCate2;
            private String ItemPic;
            private String ItemBrandName;
            private String ItemSize;
            private double CostUnit;
            private double DiscountUnit;
            private int Amount;
            private int campaignCount;
            private int Subtotal;
            private String InventoryStatus;
            private int ActivityStatus;
            private String ActivityNum;
            private String ActivityContent;
            private String activiName;
            private Object presentItemList;
            private List<PagePresentItems> pagePresentItemList;
            private String SeckillEndTime;
            private int LimitNewMember;
            private int LeastAmount;
            private int ReduceMoney;


            public List<PagePresentItems> getPagePresentItemList() {
                return pagePresentItemList;
            }

            public void setPagePresentItemList(List<PagePresentItems> pagePresentItemList) {
                this.pagePresentItemList = pagePresentItemList;
            }

            public int getOrderId() {
                return OrderId;
            }

            public void setOrderId(int OrderId) {
                this.OrderId = OrderId;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getItemName() {
                return ItemName;
            }

            public void setItemName(String ItemName) {
                this.ItemName = ItemName;
            }

            public int getItemCate1() {
                return ItemCate1;
            }

            public void setItemCate1(int ItemCate1) {
                this.ItemCate1 = ItemCate1;
            }

            public int getItemCate2() {
                return ItemCate2;
            }

            public void setItemCate2(int ItemCate2) {
                this.ItemCate2 = ItemCate2;
            }

            public String getItemPic() {
                return ItemPic;
            }

            public void setItemPic(String ItemPic) {
                this.ItemPic = ItemPic;
            }

            public String getItemBrandName() {
                return ItemBrandName;
            }

            public void setItemBrandName(String ItemBrandName) {
                this.ItemBrandName = ItemBrandName;
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

            public double getDiscountUnit() {
                return DiscountUnit;
            }

            public void setDiscountUnit(double DiscountUnit) {
                this.DiscountUnit = DiscountUnit;
            }

            public int getAmount() {
                return Amount;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public int getCampaignCount() {
                return campaignCount;
            }

            public void setCampaignCount(int campaignCount) {
                this.campaignCount = campaignCount;
            }

            public int getSubtotal() {
                return Subtotal;
            }

            public void setSubtotal(int Subtotal) {
                this.Subtotal = Subtotal;
            }

            public String getInventoryStatus() {
                return InventoryStatus;
            }

            public void setInventoryStatus(String InventoryStatus) {
                this.InventoryStatus = InventoryStatus;
            }

            public int getActivityStatus() {
                return ActivityStatus;
            }

            public void setActivityStatus(int ActivityStatus) {
                this.ActivityStatus = ActivityStatus;
            }

            public String getActivityNum() {
                return ActivityNum;
            }

            public void setActivityNum(String ActivityNum) {
                this.ActivityNum = ActivityNum;
            }

            public String getActivityContent() {
                return ActivityContent;
            }

            public void setActivityContent(String ActivityContent) {
                this.ActivityContent = ActivityContent;
            }

            public String getActiviName() {
                return activiName;
            }

            public void setActiviName(String activiName) {
                this.activiName = activiName;
            }

            public Object getPresentItemList() {
                return presentItemList;
            }

            public void setPresentItemList(Object presentItemList) {
                this.presentItemList = presentItemList;
            }


            public String getSeckillEndTime() {
                return SeckillEndTime;
            }

            public void setSeckillEndTime(String SeckillEndTime) {
                this.SeckillEndTime = SeckillEndTime;
            }

            public int getLimitNewMember() {
                return LimitNewMember;
            }

            public void setLimitNewMember(int LimitNewMember) {
                this.LimitNewMember = LimitNewMember;
            }

            public int getLeastAmount() {
                return LeastAmount;
            }

            public void setLeastAmount(int LeastAmount) {
                this.LeastAmount = LeastAmount;
            }

            public int getReduceMoney() {
                return ReduceMoney;
            }

            public void setReduceMoney(int ReduceMoney) {
                this.ReduceMoney = ReduceMoney;
            }

            public static class PagePresentItems implements Serializable{
                String ItemID;
                String ItemPic;
                String ItemName;

                public String getItemID() {
                    return ItemID;
                }

                public void setItemID(String itemID) {
                    ItemID = itemID;
                }

                public String getItemPic() {
                    return ItemPic;
                }

                public void setItemPic(String itemPic) {
                    ItemPic = itemPic;
                }

                public String getItemName() {
                    return ItemName;
                }

                public void setItemName(String itemName) {
                    ItemName = itemName;
                }
            }
        }
    }
}
