package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderItemsData {

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * DiscountUnit : 39
         * activiName : 秒杀
         * campaignCount : 2
         * ReduceMoney : 0
         * ActivityContent : 秒杀
         * ActivityStatus : 2
         * ItemBrandName : 山姆大叔
         * ItemPic : /UploadImage/ItemPic/201708/121e4de648224249997f41c5a6ed1754.jpeg
         * ItemCate2 : 27
         * ID : 571
         * ItemName : 爱尔兰面包蟹(黄道蟹) 1只
         * ActivityNum : ACT20170831003
         * SeckillEndTime : 2017-09-01 14:00:00
         * CostUnit : 59
         * OrderId : 0
         * ItemCate1 : 4
         * Subtotal : 196
         * LeastAmount : 0
         * InventoryStatus : 有货
         * Amount : 4
         * ItemSize : 只
         * LimitNewMember : 0
         */

        private double DiscountUnit;
        private String activiName;
        private int campaignCount;
        private int ReduceMoney;
        private String ActivityContent;
        private int ActivityStatus;
        private String ItemBrandName;
        private String ItemPic;
        private int ItemCate2;
        private int ID;
        private String ItemName;
        private String ActivityNum;
        private String SeckillEndTime;
        private double CostUnit;
        private int OrderId;
        private int ItemCate1;
        private double Subtotal;
        private int LeastAmount;
        private String InventoryStatus;
        private int Amount;
        private String ItemSize;
        private int LimitNewMember;
        private List<PresentItemBean> presentItemList;

        public List<PresentItemBean> getPresentItemList() {
            return presentItemList;
        }

        public void setPresentItemList(List<PresentItemBean> presentItemList) {
            this.presentItemList = presentItemList;
        }

        public double getDiscountUnit() {
            return DiscountUnit;
        }

        public void setDiscountUnit(double DiscountUnit) {
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

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
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

        public double getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(double CostUnit) {
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

        public double getSubtotal() {
            return Subtotal;
        }

        public void setSubtotal(double Subtotal) {
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

        public class PresentItemBean {

            private String ItemName;
            private int PresentNum;

            public String getItemName() {
                return ItemName;
            }

            public void setItemName(String itemName) {
                ItemName = itemName;
            }

            public int getPresentNum() {
                return PresentNum;
            }

            public void setPresentNum(int presentNum) {
                PresentNum = presentNum;
            }
        }
    }
}
