package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public class MainPageMiaoShaDate implements Serializable{


    /**
     * EndTm : 2017-04-19 00:00:00
     * ActivityName : 秒杀
     * ActivityNum : ACT20170406003
     *
     * Items : [{"ItemID":8,"ItemName":"托牛所高档牛排8","SeckillPrice":260,"RealityPrice":260,"RetailPrice":263,"LeastCount":4,"MostCount":9,"ParticipationCount":125,"RemainderCount":23,"RemainderPercent":"18.40"},{"ItemID":7,"ItemName":"托牛所高档牛排7","SeckillPrice":10,"RealityPrice":10,"RetailPrice":15,"LeastCount":3,"MostCount":77,"ParticipationCount":125,"RemainderCount":23,"RemainderPercent":"18.40"}]
     */

    private String EndTm;
    private String BegTm;
    private String sBegTm;
    private String ActivityName;
    private String ActivityNum;
    private String LimitNewMember;

    public String getLimitNewMember() {
        return LimitNewMember;
    }

    public void setLimitNewMember(String limitNewMember) {
        LimitNewMember = limitNewMember;
    }

    private List<ItemsBean> Items;

    public String getBegTm() {
        return BegTm;
    }

    public void setBegTm(String begTm) {
        BegTm = begTm;
    }

    public String getsBegTm() {
        return sBegTm;
    }

    public void setsBegTm(String sBegTm) {
        this.sBegTm = sBegTm;
    }

    public String getEndTm() {
        return EndTm;
    }

    public void setEndTm(String EndTm) {
        this.EndTm = EndTm;
    }

    public String getActivityName() {
        return ActivityName;
    }

    public void setActivityName(String ActivityName) {
        this.ActivityName = ActivityName;
    }

    public String getActivityNum() {
        return ActivityNum;
    }

    public void setActivityNum(String ActivityNum) {
        this.ActivityNum = ActivityNum;
    }

    public List<ItemsBean> getItems() {
        return Items;
    }

    public void setItems(List<ItemsBean> Items) {
        this.Items = Items;
    }

    public static class ItemsBean  implements   Serializable{
        /**
         * ItemID : 8
         * ItemName : 托牛所高档牛排8
         * SeckillPrice : 260
         * RealityPrice : 260
         * RetailPrice : 263
         * LeastCount : 4
         * MostCount : 9
         * ParticipationCount : 125
         * RemainderCount : 23
         * RemainderPercent : 18.40
         */

        private String ItemID;
        private String ItemName;
        private String ItemPic;
        private String SeckillPrice;
        private String RealityPrice;
        private String RetailPrice;
        private int LeastCount;
        private int MostCount;
        private String ParticipationCount;
        private String RemainderCount;
        private double RemainderPercent;
        private String LimitNewMember;

        public String getLimitNewMember() {
            return LimitNewMember;
        }

        public void setLimitNewMember(String limitNewMember) {
            LimitNewMember = limitNewMember;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String itemPic) {
            ItemPic = itemPic;
        }

        public String getItemID() {
            return ItemID;
        }

        public void setItemID(String ItemID) {
            this.ItemID = ItemID;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getSeckillPrice() {
            return SeckillPrice;
        }

        public void setSeckillPrice(String SeckillPrice) {
            this.SeckillPrice = SeckillPrice;
        }

        public String getRealityPrice() {
            return RealityPrice;
        }

        public void setRealityPrice(String RealityPrice) {
            this.RealityPrice = RealityPrice;
        }

        public String getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(String RetailPrice) {
            this.RetailPrice = RetailPrice;
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

        public String getParticipationCount() {
            return ParticipationCount;
        }

        public void setParticipationCount(String ParticipationCount) {
            this.ParticipationCount = ParticipationCount;
        }

        public String getRemainderCount() {
            return RemainderCount;
        }

        public void setRemainderCount(String RemainderCount) {
            this.RemainderCount = RemainderCount;
        }

        public double getRemainderPercent() {
            return RemainderPercent;
        }

        public void setRemainderPercent(double RemainderPercent) {
            this.RemainderPercent = RemainderPercent;
        }
    }
}
