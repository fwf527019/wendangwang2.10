package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/17.
 */

public class NewRecommendeData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * ItemID : 1
         * ItemName : 托牛所高档牛排1
         * RetailPrice : 56.0
         * PCPrice : 0.0
         * ItemPic : 未设置图片
         */

        private String ItemID;
        private String ItemName;
        private String RetailPrice;
        private String PCPrice;
        private String ItemPic;

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

        public String getRetailPrice() {
            return RetailPrice;
        }

        public void setRetailPrice(String RetailPrice) {
            this.RetailPrice = RetailPrice;
        }

        public String getPCPrice() {
            return PCPrice;
        }

        public void setPCPrice(String PCPrice) {
            this.PCPrice = PCPrice;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }
    }
}
