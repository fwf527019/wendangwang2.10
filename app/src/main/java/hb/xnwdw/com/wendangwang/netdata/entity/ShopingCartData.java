package hb.xnwdw.com.wendangwang.netdata.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ShopingCartData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"UserID":7687,"ItemID":319,"ItemName":"四川安岳柠檬 4个装","BrandName":"稳当生活","ItemPic":"/UploadImage/ItemPic/201708/18db66826788444e801701e911a0f46b.jpeg","BuyCounts":4,"ItemSize":"4个","RetailPrice":8,"MobilePrice":12,"PCPrice":12.5,"MinRetailPrice":8,"State":"仅剩3件","CostUnit":12,"Unit":12,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":144,"ItemName":"斑布软抽纸 450张*3包","BrandName":"斑布","ItemPic":"/UploadImage/ItemPic/201708/e3cbabe6dda54b20902fe54a8906607c.jpeg","BuyCounts":1,"ItemSize":"3包","RetailPrice":20.5,"MobilePrice":20.5,"PCPrice":20.5,"MinRetailPrice":17,"State":"有货","CostUnit":20.5,"Unit":20.5,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":551,"ItemName":"冰岛红鱼 500-700g","BrandName":"山姆大叔","ItemPic":"/UploadImage/ItemPic/201708/e9743072b03a467bb4036f54cc0547ec.jpeg","BuyCounts":2,"ItemSize":"1条","RetailPrice":25,"MobilePrice":35,"PCPrice":35.5,"MinRetailPrice":0,"State":"有货","CostUnit":35,"Unit":35,"Color":null,"Format":null,"ActivityFlag":"满减","ActivityEndTime":"2019/9/12 0:00:00","ActivityType":4,"ActivityNum":"ACT20170912004","IsCollect":0},{"UserID":7687,"ItemID":561,"ItemName":"加拿大章鱼足 2只","BrandName":"山姆大叔","ItemPic":"/UploadImage/ItemPic/201708/9955583e81e6430395efb14de556fcd4.jpeg","BuyCounts":3,"ItemSize":"240g","RetailPrice":12.8,"MobilePrice":25,"PCPrice":26,"MinRetailPrice":0,"State":"有货","CostUnit":25,"Unit":25,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":563,"ItemName":"新西兰青口贝 1000g","BrandName":"稳当生活","ItemPic":"/UploadImage/ItemPic/201708/930e5dd6d2d348539b8256511976f88a.jpeg","BuyCounts":1,"ItemSize":"1000g","RetailPrice":88,"MobilePrice":88,"PCPrice":88.5,"MinRetailPrice":10,"State":"有货","CostUnit":88,"Unit":88,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":1},{"UserID":7687,"ItemID":565,"ItemName":"越南巴沙鱼带皮 1000g","BrandName":"山姆大叔","ItemPic":"/UploadImage/ItemPic/201708/068d8ef0f6b643109b0c0d6816232e57.jpeg","BuyCounts":1,"ItemSize":"1kg","RetailPrice":40,"MobilePrice":40,"PCPrice":40.5,"MinRetailPrice":0,"State":"有货","CostUnit":40,"Unit":40,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":570,"ItemName":"智利三文鱼块 200g","BrandName":"稳当生活","ItemPic":"/UploadImage/ItemPic/201708/9fd84361bd3e414591b89bc3e88292b7.jpeg","BuyCounts":1,"ItemSize":"180-200G","RetailPrice":60,"MobilePrice":60,"PCPrice":60.5,"MinRetailPrice":35,"State":"有货","CostUnit":60,"Unit":60,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsColl ect":0},{"UserID":7687,"ItemID":739,"ItemName":"养养泰式青咖哩面 350g","BrandName":"养养牌","ItemPic":"/UploadImage/ItemPic/201708/a989d57b1eb64de49cedb3fca707bbef.jpeg","BuyCounts":1,"ItemSize":"350g","RetailPrice":15.4,"MobilePrice":15.4,"PCPrice":15.4,"MinRetailPrice":0,"State":"有货","CostUnit":15.4,"Unit":15.4,"Color":null,"Format":null,"ActivityFlag":"满减","ActivityEndTime":"2018/9/12 0:00:00","ActivityType":4,"ActivityNum":"ACT20170912003","IsCollect":1},{"UserID":7687,"ItemID":899,"ItemName":"乐事飘香麻辣锅味 70g","BrandName":"乐事","ItemPic":"/UploadImage/ItemPic/201708/86f9f6a272fe4593b3f2afcf622a5825.jpeg","BuyCounts":1,"ItemSize":"70g","RetailPrice":6.9,"MobilePrice":6.9,"PCPrice":7,"MinRetailPrice":0,"State":"有货","CostUnit":6.9,"Unit":6.9,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":139,"ItemName":"斑布700克实心卷纸 70g*10卷","BrandName":"斑布","ItemPic":"/UploadImage/ItemPic/201708/1583b27e7ab6435897998cb2faf86a82.jpeg","BuyCounts":1,"ItemSize":"70g*10卷","RetailPrice":16.5,"MobilePrice":16.5,"PCPrice":16.5,"MinRetailPrice":13.8,"State":"有货","CostUnit":16.5,"Unit":16.5,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":143,"ItemName":"斑布软抽纸 390张*3包","BrandName":"斑布","ItemPic":"/UploadImage/ItemPic/201708/06e201f56aaa4a55af213bd13d262032.jpeg","BuyCounts":5,"ItemSize":"3包","RetailPrice":16,"MobilePrice":16,"PCPrice":16,"MinRetailPrice":13.3,"State":"有货","CostUnit":16,"Unit":16,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":289,"ItemName":"雪芙兰保水网水漾化妆水","BrandName":"雪芙兰","ItemPic":"/UploadImage/ItemPic/201708/26c521e270714b0ab776bc89c419fba2.jpeg","BuyCounts":1,"ItemSize":"140ml","RetailPrice":76,"MobilePrice":76,"PCPrice":76.5,"MinRetailPrice":54.5,"State":"有货","CostUnit":76,"Unit":76,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0},{"UserID":7687,"ItemID":310,"ItemName":"山东烟台栖霞红富士苹果4个装   约2斤","BrandName":"稳当生活","ItemPic":"/UploadImage/ItemPic/201708/07d93902ee51486b801f41959b88ff29.jpeg","BuyCounts":1,"ItemSize":"4个","RetailPrice":10,"MobilePrice":12.5,"PCPrice":13.1,"MinRetailPrice":10,"State":"已下架","CostUnit":12.5,"Unit":12.5,"Color":null,"Format":null,"ActivityFlag":"","ActivityEndTime":"","ActivityType":0,"ActivityNum":"","IsCollect":0}]
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
         * UserID : 7687
         * ItemID : 319
         * ItemName : 四川安岳柠檬 4个装
         * BrandName : 稳当生活
         * ItemPic : /UploadImage/ItemPic/201708/18db66826788444e801701e911a0f46b.jpeg
         * BuyCounts : 4
         * ItemSize : 4个
         * RetailPrice : 8.0
         * MobilePrice : 12.0
         * PCPrice : 12.5
         * MinRetailPrice : 8.0
         * State : 仅剩3件
         * CostUnit : 12.0
         * Unit : 12.0
         * Color : null
         * Format : null
         * ActivityFlag :
         * ActivityEndTime :
         * ActivityType : 0
         * ActivityNum :
         * IsCollect : 0
         * IsColl ect : 0
         */
        /////////////////////////////////////
        private int canBuyCout=9999;
        private int mostCount;
        private Long EndTime;
        private boolean Cheached;
        ////////////////////////////////////
        private int UserID;
        private int ItemID;
        private String ItemName;
        private String BrandName;
        private String ItemPic;
        private int BuyCounts;
        private String ItemSize;
        private double RetailPrice;
        private double MobilePrice;
        private double PCPrice;
        private double MinRetailPrice;
        private String State;
        private double CostUnit;
        private double Unit;
        private Object Color;
        private Object Format;
        private String ActivityFlag;
        private String ActivityEndTime;
        private int ActivityType;
        private String ActivityNum;
        private int IsCollect;
        @SerializedName("IsColl ect")
        private int _$IsCollEct88; // FIXME check this code


        public int getCanBuyCout() {
            return canBuyCout;
        }

        public void setCanBuyCout(int canBuyCout) {
            this.canBuyCout = canBuyCout;
        }

        public int getMostCount() {
            return mostCount;
        }

        public void setMostCount(int mostCount) {
            this.mostCount = mostCount;
        }

        public Long getEndTime() {
            return EndTime;
        }

        public void setEndTime(Long endTime) {
            EndTime = endTime;
        }

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

        public double getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(double RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public double getMobilePrice() {
            return MobilePrice;
        }

        public void setMobilePrice(double MobilePrice) {
            this.MobilePrice = MobilePrice;
        }

        public double getPCPrice() {
            return PCPrice;
        }

        public void setPCPrice(double PCPrice) {
            this.PCPrice = PCPrice;
        }

        public double getMinRetailPrice() {
            return MinRetailPrice;
        }

        public void setMinRetailPrice(double MinRetailPrice) {
            this.MinRetailPrice = MinRetailPrice;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public double getCostUnit() {
            return CostUnit;
        }

        public void setCostUnit(double CostUnit) {
            this.CostUnit = CostUnit;
        }

        public double getUnit() {
            return Unit;
        }

        public void setUnit(double Unit) {
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

        public int get_$IsCollEct88() {
            return _$IsCollEct88;
        }

        public void set_$IsCollEct88(int _$IsCollEct88) {
            this._$IsCollEct88 = _$IsCollEct88;
        }

        public boolean isCheached() {
            return Cheached;
        }

        public void setCheached(boolean cheached) {
            Cheached = cheached;
        }
    }
}
