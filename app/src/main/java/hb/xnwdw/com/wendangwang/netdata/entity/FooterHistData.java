package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/25.
 */

public class FooterHistData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"key":{"ST":"2017-05-25","STCount":1},"Content":[{"ID":50,"ItemID":1020208,"ItemName":"托牛所高档牛排8","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/be0a7d7e5a4c4c6bacc20fa8ff98fc9a.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":260,"Unit":260,"stItemsCount":1,"totalCount":10,"State":""},{"ID":49,"ItemID":1020206,"ItemName":"托牛所高档牛排6","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/8a202d7e04634b4486fdbc9250635681.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":63,"Unit":63,"stItemsCount":1,"totalCount":10,"State":"已禁用"},{"ID":48,"ItemID":1020204,"ItemName":"托牛所高档牛排4","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201704/2294441ef5484c048eafbbcb6d8b919f.jpg","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":38,"Unit":38,"stItemsCount":1,"totalCount":10,"State":""},{"ID":47,"ItemID":1020210,"ItemName":"托牛所高档牛排10","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/bf64bccb87854e0b84794c2c2fe4230b.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":35,"Unit":35,"stItemsCount":1,"totalCount":10,"State":"已下架"},{"ID":45,"ItemID":1020212,"ItemName":"托牛所高档牛排12","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/19282ac644a149588107e8f81e9babae.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":100,"Unit":100,"stItemsCount":1,"totalCount":10,"State":""},{"ID":44,"ItemID":1020211,"ItemName":"托牛所高档牛排11","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/62fbbf17d46b439c906b44c54b0b4975.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":266,"Unit":266,"stItemsCount":1,"totalCount":10,"State":""},{"ID":43,"ItemID":1020201,"ItemName":"托牛所高档牛排1","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/a61098e8b7ed485bbf5fad7003fe40fa.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":50,"Unit":50,"stItemsCount":1,"totalCount":10,"State":""}]},{"key":{"ST":"2017-05-23","STCount":1},"Content":[{"ID":39,"ItemID":1020212,"ItemName":"托牛所高档牛排12","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/19282ac644a149588107e8f81e9babae.png","SeeTm":"2017-05-23","ItemSize":"袋","CostUnit":100,"Unit":100,"stItemsCount":1,"totalCount":10,"State":""},{"ID":37,"ItemID":1020201,"ItemName":"托牛所高档牛排1","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/a61098e8b7ed485bbf5fad7003fe40fa.png","SeeTm":"2017-05-23","ItemSize":"袋","CostUnit":50,"Unit":50,"stItemsCount":1,"totalCount":10,"State":""},{"ID":36,"ItemID":1020208,"ItemName":"托牛所高档牛排8","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/be0a7d7e5a4c4c6bacc20fa8ff98fc9a.png","SeeTm":"2017-05-23","ItemSize":"袋","CostUnit":260,"Unit":260,"stItemsCount":1,"totalCount":10,"State":""}]}]
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
         * key : {"ST":"2017-05-25","STCount":1}
         * Content : [{"ID":50,"ItemID":1020208,"ItemName":"托牛所高档牛排8","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/be0a7d7e5a4c4c6bacc20fa8ff98fc9a.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":260,"Unit":260,"stItemsCount":1,"totalCount":10,"State":""},{"ID":49,"ItemID":1020206,"ItemName":"托牛所高档牛排6","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/8a202d7e04634b4486fdbc9250635681.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":63,"Unit":63,"stItemsCount":1,"totalCount":10,"State":"已禁用"},{"ID":48,"ItemID":1020204,"ItemName":"托牛所高档牛排4","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201704/2294441ef5484c048eafbbcb6d8b919f.jpg","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":38,"Unit":38,"stItemsCount":1,"totalCount":10,"State":""},{"ID":47,"ItemID":1020210,"ItemName":"托牛所高档牛排10","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/bf64bccb87854e0b84794c2c2fe4230b.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":35,"Unit":35,"stItemsCount":1,"totalCount":10,"State":"已下架"},{"ID":45,"ItemID":1020212,"ItemName":"托牛所高档牛排12","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/19282ac644a149588107e8f81e9babae.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":100,"Unit":100,"stItemsCount":1,"totalCount":10,"State":""},{"ID":44,"ItemID":1020211,"ItemName":"托牛所高档牛排11","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/62fbbf17d46b439c906b44c54b0b4975.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":266,"Unit":266,"stItemsCount":1,"totalCount":10,"State":""},{"ID":43,"ItemID":1020201,"ItemName":"托牛所高档牛排1","BrandName":null,"ItemPic":"/UploadImage/ItemPic/201705/a61098e8b7ed485bbf5fad7003fe40fa.png","SeeTm":"2017-05-25","ItemSize":"袋","CostUnit":50,"Unit":50,"stItemsCount":1,"totalCount":10,"State":""}]
         */

        private KeyBean key;
        private List<ContentBean> Content;

        public KeyBean getKey() {
            return key;
        }

        public void setKey(KeyBean key) {
            this.key = key;
        }

        public List<ContentBean> getContent() {
            return Content;
        }

        public void setContent(List<ContentBean> Content) {
            this.Content = Content;
        }

        public static class KeyBean {
            /**
             * ST : 2017-05-25
             * STCount : 1
             */

            private String ST;
            private int STCount;

            public String getST() {
                return ST;
            }

            public void setST(String ST) {
                this.ST = ST;
            }

            public int getSTCount() {
                return STCount;
            }

            public void setSTCount(int STCount) {
                this.STCount = STCount;
            }
        }

        public static class ContentBean {
            /**
             * ID : 50
             * ItemID : 1020208
             * ItemName : 托牛所高档牛排8
             * BrandName : null
             * ItemPic : /UploadImage/ItemPic/201705/be0a7d7e5a4c4c6bacc20fa8ff98fc9a.png
             * SeeTm : 2017-05-25
             * ItemSize : 袋
             * CostUnit : 260
             * Unit : 260
             * stItemsCount : 1
             * totalCount : 10
             * State :
             */

            private int ID;
            private int ItemID;
            private String ItemName;
            private Object BrandName;
            private String ItemPic;
            private String SeeTm;
            private String ItemSize;
            private int CostUnit;
            private String Unit;
            private int stItemsCount;
            private int totalCount;
            private String State;
            public boolean isSelect;

            public int getID() {
                return ID;
            }
            public void setID(int ID) {
                this.ID = ID;
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

            public String getSeeTm() {
                return SeeTm;
            }

            public void setSeeTm(String SeeTm) {
                this.SeeTm = SeeTm;
            }

            public String getItemSize() {
                return ItemSize;
            }

            public void setItemSize(String ItemSize) {
                this.ItemSize = ItemSize;
            }

            public int getCostUnit() {
                return CostUnit;
            }

            public void setCostUnit(int CostUnit) {
                this.CostUnit = CostUnit;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public int getStItemsCount() {
                return stItemsCount;
            }

            public void setStItemsCount(int stItemsCount) {
                this.stItemsCount = stItemsCount;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public String getState() {
                return State;
            }

            public void setState(String State) {
                this.State = State;
            }
        }
    }
}
