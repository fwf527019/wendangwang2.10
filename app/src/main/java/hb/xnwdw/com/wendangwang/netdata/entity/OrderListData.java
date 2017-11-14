package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/23.
 */

public class OrderListData {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * DiscountUnit : 37
         * activiName : 无
         * campaignCount : 1
         * pagePresentItemList : [{"RemainderCount":50,"ID":48,"ItemName":"宜朵毛巾长巾混装","ItemID":421,"PresentNum":1,"BuyOrFullPresentId":22,"GUID":"b39b3978d6ca44a0b432e5e748416f88","PresentTypeId":2,"PresentSum":50,"DeleteLevel":1}]
         * ReduceMoney : 0
         * ActivityContent : 满赠
         * ActivityStatus : 2
         * ItemBrandName : 缺省品牌
         * ItemCate2 : 5
         * ID : 10
         * ItemName : 斑布1600克空心卷纸
         * ActivityNum : ACT20170823004
         * SeckillEndTime : 2017-09-07 00:00:00
         * CostUnit : 37
         * OrderId : 0
         * ItemCate1 : 1
         * Subtotal : 37
         * LeastAmount : 50
         * InventoryStatus : 有货
         * Amount : 1
         * ItemSize : 包
         * LimitNewMember : 0
         */

        private int DiscountUnit;
        private String activiName;
        private String ItemPic;
        private int campaignCount;
        private int ReduceMoney;
        private String ActivityContent;
        private int ActivityStatus;
        private String ItemBrandName;
        private int ItemCate2;
        private int ID;
        private String ItemName;
        private String ActivityNum;
        private String SeckillEndTime;
        private int CostUnit;
        private int OrderId;
        private int ItemCate1;
        private int Subtotal;
        private int LeastAmount;
        private String InventoryStatus;
        private int Amount;
        private String ItemSize;
        private int LimitNewMember;
        private List<PagePresentItemListBean> pagePresentItemList;


        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String itemPic) {
            ItemPic = itemPic;
        }

        public int getDiscountUnit() {
            return DiscountUnit;
        }

        public void setDiscountUnit(int DiscountUnit) {
            this.DiscountUnit = DiscountUnit;
        }

        public String getActiviName() {
            return activiName;
        }

        public void setActiviName(String activiName) {
            this.activiName = activiName;
        }

        public int getCampaignCount() {
            return campaignCount;
        }

        public void setCampaignCount(int campaignCount) {
            this.campaignCount = campaignCount;
        }

        public int getReduceMoney() {
            return ReduceMoney;
        }

        public void setReduceMoney(int ReduceMoney) {
            this.ReduceMoney = ReduceMoney;
        }

        public String getActivityContent() {
            return ActivityContent;
        }

        public void setActivityContent(String ActivityContent) {
            this.ActivityContent = ActivityContent;
        }

        public int getActivityStatus() {
            return ActivityStatus;
        }

        public void setActivityStatus(int ActivityStatus) {
            this.ActivityStatus = ActivityStatus;
        }

        public String getItemBrandName() {
            return ItemBrandName;
        }

        public void setItemBrandName(String ItemBrandName) {
            this.ItemBrandName = ItemBrandName;
        }

        public int getItemCate2() {
            return ItemCate2;
        }

        public void setItemCate2(int ItemCate2) {
            this.ItemCate2 = ItemCate2;
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

        public String getActivityNum() {
            return ActivityNum;
        }

        public void setActivityNum(String ActivityNum) {
            this.ActivityNum = ActivityNum;
        }

        public String getSeckillEndTime() {
            return SeckillEndTime;
        }

        public void setSeckillEndTime(String SeckillEndTime) {
            this.SeckillEndTime = SeckillEndTime;
        }

        public int getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(int CostUnit) {
            this.CostUnit = CostUnit;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }

        public int getItemCate1() {
            return ItemCate1;
        }

        public void setItemCate1(int ItemCate1) {
            this.ItemCate1 = ItemCate1;
        }

        public int getSubtotal() {
            return Subtotal;
        }

        public void setSubtotal(int Subtotal) {
            this.Subtotal = Subtotal;
        }

        public int getLeastAmount() {
            return LeastAmount;
        }

        public void setLeastAmount(int LeastAmount) {
            this.LeastAmount = LeastAmount;
        }

        public String getInventoryStatus() {
            return InventoryStatus;
        }

        public void setInventoryStatus(String InventoryStatus) {
            this.InventoryStatus = InventoryStatus;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public int getLimitNewMember() {
            return LimitNewMember;
        }

        public void setLimitNewMember(int LimitNewMember) {
            this.LimitNewMember = LimitNewMember;
        }

        public List<PagePresentItemListBean> getPagePresentItemList() {
            return pagePresentItemList;
        }

        public void setPagePresentItemList(List<PagePresentItemListBean> pagePresentItemList) {
            this.pagePresentItemList = pagePresentItemList;
        }

        public static class PagePresentItemListBean {
            /**
             * RemainderCount : 50
             * ID : 48
             * ItemName : 宜朵毛巾长巾混装
             * ItemID : 421
             * PresentNum : 1
             * BuyOrFullPresentId : 22
             * GUID : b39b3978d6ca44a0b432e5e748416f88
             * PresentTypeId : 2
             * PresentSum : 50
             * DeleteLevel : 1
             */

            private int RemainderCount;
            private int ID;
            private String ItemName;
            private int ItemID;
            private int PresentNum;
            private int BuyOrFullPresentId;
            private String GUID;
            private int PresentTypeId;
            private int PresentSum;
            private int DeleteLevel;

            public int getRemainderCount() {
                return RemainderCount;
            }

            public void setRemainderCount(int RemainderCount) {
                this.RemainderCount = RemainderCount;
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

            public int getItemID() {
                return ItemID;
            }

            public void setItemID(int ItemID) {
                this.ItemID = ItemID;
            }

            public int getPresentNum() {
                return PresentNum;
            }

            public void setPresentNum(int PresentNum) {
                this.PresentNum = PresentNum;
            }

            public int getBuyOrFullPresentId() {
                return BuyOrFullPresentId;
            }

            public void setBuyOrFullPresentId(int BuyOrFullPresentId) {
                this.BuyOrFullPresentId = BuyOrFullPresentId;
            }

            public String getGUID() {
                return GUID;
            }

            public void setGUID(String GUID) {
                this.GUID = GUID;
            }

            public int getPresentTypeId() {
                return PresentTypeId;
            }

            public void setPresentTypeId(int PresentTypeId) {
                this.PresentTypeId = PresentTypeId;
            }

            public int getPresentSum() {
                return PresentSum;
            }

            public void setPresentSum(int PresentSum) {
                this.PresentSum = PresentSum;
            }

            public int getDeleteLevel() {
                return DeleteLevel;
            }

            public void setDeleteLevel(int DeleteLevel) {
                this.DeleteLevel = DeleteLevel;
            }
        }
    }
}
