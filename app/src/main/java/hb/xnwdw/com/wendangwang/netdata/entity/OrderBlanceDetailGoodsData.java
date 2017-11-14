package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/6/6.
 */

public class OrderBlanceDetailGoodsData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * ItemCate2 : 15
         * Amount : 1
         * Subtotal : 60
         * OrderId : 1115
         * CostUnit : 60
         * ActivityStatus : 0
         * InventoryStatus : 有货
         * SeckillEndTime : 0001-01-01 00:00:00
         * DiscountUnit : 60
         * ItemSize : 袋
         * ItemName : 托牛所牛排12号
         * ID : 10
         * ItemPic : /UploadImage/ItemPic/201705/bf64bccb87854e0b84794c2c2fe4230b.png
         * ItemBrandName : 托牛所
         * ItemCate1 : 3
         * ActivityContent : 满0立减0
         */

        private int ItemCate2;
        private int Amount;
        private int Subtotal;
        private int OrderId;
        private int CostUnit;
        private int ActivityStatus;
        private String InventoryStatus;
        private String SeckillEndTime;
        private int DiscountUnit;
        private String ItemSize;
        private String ItemName;
        private int ID;
        private String ItemPic;
        private String ItemBrandName;
        private int ItemCate1;
        private String ActivityContent;

        public int getItemCate2() {
            return ItemCate2;
        }

        public void setItemCate2(int ItemCate2) {
            this.ItemCate2 = ItemCate2;
        }

        public int getAmount() {
            return Amount;
        }

        public void setAmount(int Amount) {
            this.Amount = Amount;
        }

        public int getSubtotal() {
            return Subtotal;
        }

        public void setSubtotal(int Subtotal) {
            this.Subtotal = Subtotal;
        }

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
        }

        public int getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(int CostUnit) {
            this.CostUnit = CostUnit;
        }

        public int getActivityStatus() {
            return ActivityStatus;
        }

        public void setActivityStatus(int ActivityStatus) {
            this.ActivityStatus = ActivityStatus;
        }

        public String getInventoryStatus() {
            return InventoryStatus;
        }

        public void setInventoryStatus(String InventoryStatus) {
            this.InventoryStatus = InventoryStatus;
        }

        public String getSeckillEndTime() {
            return SeckillEndTime;
        }

        public void setSeckillEndTime(String SeckillEndTime) {
            this.SeckillEndTime = SeckillEndTime;
        }

        public int getDiscountUnit() {
            return DiscountUnit;
        }

        public void setDiscountUnit(int DiscountUnit) {
            this.DiscountUnit = DiscountUnit;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
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

        public int getItemCate1() {
            return ItemCate1;
        }

        public void setItemCate1(int ItemCate1) {
            this.ItemCate1 = ItemCate1;
        }

        public String getActivityContent() {
            return ActivityContent;
        }

        public void setActivityContent(String ActivityContent) {
            this.ActivityContent = ActivityContent;
        }
    }
}
