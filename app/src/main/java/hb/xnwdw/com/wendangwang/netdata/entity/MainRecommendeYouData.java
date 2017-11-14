package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */

public class MainRecommendeYouData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * ItemID : 9
         * ItemName : 托牛所高档牛排9
         * BrandName : null
         * ItemPic : /UploadImage/ItemPic/201705/9ad697c11a6b4f5db81e70ce2204692c.png
         * ItemSize : null
         * RetailPrice : 62
         * MobilePrice : 52.5
         * PCPrice : 67
         * MinRetailPrice : 0
         * State : null
         * CostUnit : 0
         * Unit : 0
         * Color : null
         * Format : null
         * ActivityFlag :
         * ActivityEndTime : null
         * ActivityType : 0
         * ActivityNum : null
         * IsCollect : 0
         * Memo :
         */

        private int ItemID;
        private String ItemName;
        private Object BrandName;
        private String ItemPic;
        private Object ItemSize;
        private int RetailPrice;
        private double MobilePrice;
        private int PCPrice;
        private int MinRetailPrice;
        private Object State;
        private int CostUnit;
        private int Unit;
        private Object Color;
        private Object Format;
        private String ActivityFlag;
        private Object ActivityEndTime;
        private int ActivityType;
        private Object ActivityNum;
        private int IsCollect;
        private String Memo;
        private String LimitNewMember;

        public String getLimitNewMember() {
            return LimitNewMember;
        }

        public void setLimitNewMember(String limitNewMember) {
            LimitNewMember = limitNewMember;
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

        public Object getItemSize() {
            return ItemSize;
        }

        public void setItemSize(Object ItemSize) {
            this.ItemSize = ItemSize;
        }

        public int getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(int RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public double getMobilePrice() {
            return MobilePrice;
        }

        public void setMobilePrice(double MobilePrice) {
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

        public Object getState() {
            return State;
        }

        public void setState(Object State) {
            this.State = State;
        }

        public int getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(int CostUnit) {
            this.CostUnit = CostUnit;
        }

        public int getUnit() {
            return Unit;
        }

        public void setUnit(int Unit) {
            this.Unit = Unit;
        }

        public Object getColor() {
            return Color;
        }

        public void setColor(Object Color) {
            this.Color = Color;
        }

        public Object getFormat() {
            return Format;
        }

        public void setFormat(Object Format) {
            this.Format = Format;
        }

        public String getActivityFlag() {
            return ActivityFlag;
        }

        public void setActivityFlag(String ActivityFlag) {
            this.ActivityFlag = ActivityFlag;
        }

        public Object getActivityEndTime() {
            return ActivityEndTime;
        }

        public void setActivityEndTime(Object ActivityEndTime) {
            this.ActivityEndTime = ActivityEndTime;
        }

        public int getActivityType() {
            return ActivityType;
        }

        public void setActivityType(int ActivityType) {
            this.ActivityType = ActivityType;
        }

        public Object getActivityNum() {
            return ActivityNum;
        }

        public void setActivityNum(Object ActivityNum) {
            this.ActivityNum = ActivityNum;
        }

        public int getIsCollect() {
            return IsCollect;
        }

        public void setIsCollect(int IsCollect) {
            this.IsCollect = IsCollect;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }
    }
}
