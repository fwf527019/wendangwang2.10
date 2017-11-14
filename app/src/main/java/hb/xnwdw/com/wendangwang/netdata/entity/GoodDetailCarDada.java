package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class GoodDetailCarDada {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"UserID":27,"ItemID":1030,"ItemName":"200300黄花鱼(缅甸)5条","BrandName":"山姆大叔","ItemPic":"/UploadImage/ItemPic/201706/92db59ce5d0d4f0b872a14608a8032f6.jpeg","BuyCounts":1,"ItemSize":"5条","RetailPrice":55,"MobilePrice":55,"PCPrice":55,"MinRetailPrice":0,"State":"有货","CostUnit":55,"Unit":55,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":1},{"UserID":27,"ItemID":1032,"ItemName":"龙虾400g到450g","BrandName":"山姆大叔","ItemPic":"/UploadImage/ItemPic/201706/6daf60c15a4141bd95274477e55b9075.jpeg","BuyCounts":1,"ItemSize":"3只","RetailPrice":252,"MobilePrice":252,"PCPrice":252,"MinRetailPrice":0,"State":"有货","CostUnit":252,"Unit":252,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":1}]
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
         * UserID : 27
         * ItemID : 1030
         * ItemName : 200300黄花鱼(缅甸)5条
         * BrandName : 山姆大叔
         * ItemPic : /UploadImage/ItemPic/201706/92db59ce5d0d4f0b872a14608a8032f6.jpeg
         * BuyCounts : 1
         * ItemSize : 5条
         * RetailPrice : 55
         * MobilePrice : 55
         * PCPrice : 55
         * MinRetailPrice : 0
         * State : 有货
         * CostUnit : 55
         * Unit : 55
         * Color : null
         * Format : null
         * ActivityFlag :
         * ActivityEndTime :
         * ActivityType : 0
         * ActivityNum :
         * IsCollect : 1
         */

        private int UserID;
        private int ItemID;
        private String ItemName;
        private String BrandName;
        private String ItemPic;
        private int BuyCounts;
        private String ItemSize;
        private int RetailPrice;
        private int MobilePrice;
        private int PCPrice;
        private int MinRetailPrice;
        private String State;
        private int CostUnit;
        private int Unit;
        private Object Color;
        private Object Format;
        private String ActivityFlag;
        private String ActivityEndTime;
        private int ActivityType;
        private String ActivityNum;
        private int IsCollect;

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int UserID) {
            this.UserID = UserID;
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

        public String getBrandName() {
            return BrandName;
        }

        public void setBrandName(String BrandName) {
            this.BrandName = BrandName;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }

        public int getBuyCounts() {
            return BuyCounts;
        }

        public void setBuyCounts(int BuyCounts) {
            this.BuyCounts = BuyCounts;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public int getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(int RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public int getMobilePrice() {
            return MobilePrice;
        }

        public void setMobilePrice(int MobilePrice) {
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

        public String getState() {
            return State;
        }

        public void setState(String State) {
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

        public String getActivityEndTime() {
            return ActivityEndTime;
        }

        public void setActivityEndTime(String ActivityEndTime) {
            this.ActivityEndTime = ActivityEndTime;
        }

        public int getActivityType() {
            return ActivityType;
        }

        public void setActivityType(int ActivityType) {
            this.ActivityType = ActivityType;
        }

        public String getActivityNum() {
            return ActivityNum;
        }

        public void setActivityNum(String ActivityNum) {
            this.ActivityNum = ActivityNum;
        }

        public int getIsCollect() {
            return IsCollect;
        }

        public void setIsCollect(int IsCollect) {
            this.IsCollect = IsCollect;
        }
    }
}
