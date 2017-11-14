package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class FloorInfoData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * FloorName : 当季水果
         * FloorPic : http://localhost:3000/UploadImage/FloorBack/8c9080d0-30b5-4223-a2db-de99a6c7a2ae.jpg
         * FloorStyle : 4
         * TitlePic : http://localhost:3000/UploadImage/FloorBack/87501fcc-c1d0-4b29-af27-eb1e492fe851.png
         * TitleUrl : www.baidu.com
         * Items : [{"ItemID":1,"ItemName":"托牛所高档牛排1","ItemPic":"null","PcPrice":0,"RetaiPrice":56,"OrderIndex":1,"MobilePrice":"0.00","ItemSize":"袋"},{"ItemID":2,"ItemName":"托牛所高档牛排2","ItemPic":"null","PcPrice":0,"RetaiPrice":35,"OrderIndex":2,"MobilePrice":"0.00","ItemSize":"袋"},{"ItemID":3,"ItemName":"托牛所高档牛排3","ItemPic":"null","PcPrice":0,"RetaiPrice":15,"OrderIndex":3,"MobilePrice":"0.00","ItemSize":"袋"},{"ItemID":4,"ItemName":"托牛所高档牛排4","ItemPic":"null","PcPrice":40,"RetaiPrice":98,"OrderIndex":4,"MobilePrice":"38.00","ItemSize":"袋"},{"ItemID":5,"ItemName":"托牛所高档牛排5","ItemPic":"null","PcPrice":0,"RetaiPrice":23,"OrderIndex":5,"MobilePrice":"0.00","ItemSize":"袋"},{"ItemID":1,"ItemName":"托牛所高档牛排1","ItemPic":"null","PcPrice":0,"RetaiPrice":56,"OrderIndex":6,"MobilePrice":"0.00","ItemSize":"袋"},{"ItemID":5,"ItemName":"托牛所高档牛排5","ItemPic":"null","PcPrice":0,"RetaiPrice":23,"OrderIndex":7,"MobilePrice":"0.00","ItemSize":"袋"}]
         */

        private String FloorName;
        private String FloorPic;
        private String FloorStyle;
        private String TitlePic;
        private String TitleUrl;
        private List<ItemsBean> Items;
       private String TitleUrlMobile;
       private String MbPicUrl;
       private String Memo;
       private String PcPicUrl;
       private String AppPic;


        public String getAppPic() {
            return AppPic;
        }

        public void setAppPic(String appPic) {
            AppPic = appPic;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String memo) {
            Memo = memo;
        }

        public String getPcPicUrl() {
            return PcPicUrl;
        }

        public void setPcPicUrl(String pcPicUrl) {
            PcPicUrl = pcPicUrl;
        }

        public String getTitleUrlMobile() {
            return TitleUrlMobile;
        }

        public void setTitleUrlMobile(String titleUrlMobile) {
            TitleUrlMobile = titleUrlMobile;
        }

        public String getMbPicUrl() {
            return MbPicUrl;
        }

        public void setMbPicUrl(String mbPicUrl) {
            MbPicUrl = mbPicUrl;
        }

        public String getFloorName() {
            return FloorName;
        }

        public void setFloorName(String FloorName) {
            this.FloorName = FloorName;
        }

        public String getFloorPic() {
            return FloorPic;
        }

        public void setFloorPic(String FloorPic) {
            this.FloorPic = FloorPic;
        }

        public String getFloorStyle() {
            return FloorStyle;
        }

        public void setFloorStyle(String FloorStyle) {
            this.FloorStyle = FloorStyle;
        }

        public String getTitlePic() {
            return TitlePic;
        }

        public void setTitlePic(String TitlePic) {
            this.TitlePic = TitlePic;
        }

        public String getTitleUrl() {
            return TitleUrl;
        }

        public void setTitleUrl(String TitleUrl) {
            this.TitleUrl = TitleUrl;
        }

        public List<ItemsBean> getItems() {
            return Items;
        }

        public void setItems(List<ItemsBean> Items) {
            this.Items = Items;
        }

        public static class ItemsBean {
            /**
             * ItemID : 1
             * ItemName : 托牛所高档牛排1
             * ItemPic : null
             * PcPrice : 0
             * RetaiPrice : 56
             * OrderIndex : 1
             * MobilePrice : 0.00
             * ItemSize : 袋
             */

            private String ItemID;
            private String ItemName;
            private String ItemPic;
            private String PcPrice;
            private String RetaiPrice;
            private String OrderIndex;
            private String MobilePrice;
            private String ItemSize;
            private String ActivityFlag;
            private String LimitNewMember;

            public String getActivityFlag() {
                return ActivityFlag;
            }

            public void setActivityFlag(String activityFlag) {
                ActivityFlag = activityFlag;
            }

            public String getLimitNewMember() {
                return LimitNewMember;
            }

            public void setLimitNewMember(String limitNewMember) {
                LimitNewMember = limitNewMember;
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

            public String getItemPic() {
                return ItemPic;
            }

            public void setItemPic(String ItemPic) {
                this.ItemPic = ItemPic;
            }

            public String getPcPrice() {
                return PcPrice;
            }

            public void setPcPrice(String PcPrice) {
                this.PcPrice = PcPrice;
            }

            public String getRetaiPrice() {
                return RetaiPrice;
            }

            public void setRetaiPrice(String RetaiPrice) {
                this.RetaiPrice = RetaiPrice;
            }

            public String getOrderIndex() {
                return OrderIndex;
            }

            public void setOrderIndex(String OrderIndex) {
                this.OrderIndex = OrderIndex;
            }

            public String getMobilePrice() {
                return MobilePrice;
            }

            public void setMobilePrice(String MobilePrice) {
                this.MobilePrice = MobilePrice;
            }

            public String getItemSize() {
                return ItemSize;
            }

            public void setItemSize(String ItemSize) {
                this.ItemSize = ItemSize;
            }
        }
    }
}
