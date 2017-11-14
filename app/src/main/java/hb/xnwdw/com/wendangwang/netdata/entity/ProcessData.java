package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ProcessData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"ID":1141,"AfterNum":"20170810005","OrderNumber":"201708020015","ItemName":null,"ItemSize":null,"ItemPic":"/wdw/images/default2.png","ApplyTime":"2017/8/10 15:44:54","SaleAfterType":"整单退款","State":"待审核","RecordsCount":16,"Counts":0,"LogisticsTypeId":0},{"ID":1129,"AfterNum":"20170803010","OrderNumber":"201708030048","ItemName":"巴沙鱼带皮","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/cbe77bc6bce34ad896e462436219d789.jpeg","ApplyTime":"2017/8/3 17:36:05","SaleAfterType":"换货","State":"待退款","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":1118,"AfterNum":"20170801004","OrderNumber":"201707240025","ItemName":"RINDA椰子味糖","ItemSize":"60g","ItemPic":"/UploadImage/ItemPic/201707/e1bf46d787d24567b271ca734325edfa.jpeg","ApplyTime":"2017/8/1 16:14:05","SaleAfterType":"换货","State":"客服审核退款","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":104,"AfterNum":"20170725003","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/25 15:11:36","SaleAfterType":"换货","State":"待退款","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":99,"AfterNum":"20170724002","OrderNumber":"201707240019","ItemName":"龙虾400g到450g","ItemSize":"3只","ItemPic":"/UploadImage/ItemPic/201706/6daf60c15a4141bd95274477e55b9075.jpeg","ApplyTime":"2017/7/24 15:23:37","SaleAfterType":"退货","State":"待退款","RecordsCount":16,"Counts":2,"LogisticsTypeId":2},{"ID":95,"AfterNum":"20170721002","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/21 10:29:42","SaleAfterType":"换货","State":"待退款","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":94,"AfterNum":"20170720007","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/20 21:03:00","SaleAfterType":"退货","State":"客服审核退款","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":93,"AfterNum":"20170720006","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/20 21:02:43","SaleAfterType":"","State":"待审核","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":92,"AfterNum":"20170720005","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/20 21:00:15","SaleAfterType":"","State":"待审核","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":91,"AfterNum":"20170720004","OrderNumber":"201707070005","ItemName":"青口贝","ItemSize":"1kg","ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ApplyTime":"2017/7/20 20:58:59","SaleAfterType":"补发","State":"待审核","RecordsCount":16,"Counts":1,"LogisticsTypeId":0},{"ID":60,"AfterNum":"20170711007","OrderNumber":"201706280009","ItemName":"匡迪真空学士杯520ml黑紫","ItemSize":"520ml黑紫","ItemPic":"/UploadImage/ItemPic/201707/c0a8d452162d40308806af3bbe08b217.jpeg","ApplyTime":"2017/7/11 9:26:14","SaleAfterType":"退款","State":"待收货","RecordsCount":16,"Counts":1,"LogisticsTypeId":2},{"ID":59,"AfterNum":"20170711006","OrderNumber":"201706280009","ItemName":"匡迪真空学士杯520ml黑紫","ItemSize":"520ml黑紫","ItemPic":"/UploadImage/ItemPic/201707/c0a8d452162d40308806af3bbe08b217.jpeg","ApplyTime":"2017/7/11 9:25:57","SaleAfterType":"退款","State":"待收货","RecordsCount":16,"Counts":1,"LogisticsTypeId":1}]
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * ID : 1141
         * AfterNum : 20170810005
         * OrderNumber : 201708020015
         * ItemName : null
         * ItemSize : null
         * ItemPic : /wdw/images/default2.png
         * ApplyTime : 2017/8/10 15:44:54
         * SaleAfterType : 整单退款
         * State : 待审核
         * RecordsCount : 16
         * Counts : 0
         * LogisticsTypeId : 0
         */

        private int ID;
        private String AfterNum;
        private String OrderNumber;
        private String ItemName;
        private String ItemSize;
        private String ItemPic;
        private String ApplyTime;
        private String SaleAfterType;
        private String State;
        private int RecordsCount;
        private int Counts;
        private int LogisticsTypeId;

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getAfterNum() {
            return AfterNum;
        }

        public void setAfterNum(String AfterNum) {
            this.AfterNum = AfterNum;
        }

        public String getOrderNumber() {
            return OrderNumber;
        }

        public void setOrderNumber(String OrderNumber) {
            this.OrderNumber = OrderNumber;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }

        public String getApplyTime() {
            return ApplyTime;
        }

        public void setApplyTime(String ApplyTime) {
            this.ApplyTime = ApplyTime;
        }

        public String getSaleAfterType() {
            return SaleAfterType;
        }

        public void setSaleAfterType(String SaleAfterType) {
            this.SaleAfterType = SaleAfterType;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }

        public int getRecordsCount() {
            return RecordsCount;
        }

        public void setRecordsCount(int RecordsCount) {
            this.RecordsCount = RecordsCount;
        }

        public int getCounts() {
            return Counts;
        }

        public void setCounts(int Counts) {
            this.Counts = Counts;
        }

        public int getLogisticsTypeId() {
            return LogisticsTypeId;
        }

        public void setLogisticsTypeId(int LogisticsTypeId) {
            this.LogisticsTypeId = LogisticsTypeId;
        }
    }
}
