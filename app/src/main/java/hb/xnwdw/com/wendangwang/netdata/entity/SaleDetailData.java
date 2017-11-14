package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15.
 */

public class SaleDetailData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"AfterSaleId":1131,"AfterNum":"20170803012","ItemPic":"/UploadImage/ItemPic/201706/cbe77bc6bce34ad896e462436219d789.jpeg","Unit":"38.00","ItemID":1033,"ItemName":"巴沙鱼带皮","ItemSize":"1kg","Counts":1,"Operationdate":"2017/8/3 18:04:13","DisposeInfo":"审核已通过!","Type":"退款","Describe":"sdf","AddressDetail":"西宁市城北区朝阳西路52号3号楼一层商铺","RecName":"小李","PhoneNum":"18782070820","Status":5,"ShouldRefundMoney":38,"State":"已完成"},{"AfterSaleId":1131,"AfterNum":"20170803012","ItemPic":"/UploadImage/ItemPic/201706/cbe77bc6bce34ad896e462436219d789.jpeg","Unit":"38.00","ItemID":1033,"ItemName":"巴沙鱼带皮","ItemSize":"1kg","Counts":1,"Operationdate":"2017/8/3 18:04:20","DisposeInfo":"sdf","Type":"退款","Describe":"sdf","AddressDetail":"西宁市城北区朝阳西路52号3号楼一层商铺","RecName":"小李","PhoneNum":"18782070820","Status":5,"ShouldRefundMoney":38,"State":"已完成"}]
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
         * AfterSaleId : 1131
         * AfterNum : 20170803012
         * ItemPic : /UploadImage/ItemPic/201706/cbe77bc6bce34ad896e462436219d789.jpeg
         * Unit : 38.00
         * ItemID : 1033
         * ItemName : 巴沙鱼带皮
         * ItemSize : 1kg
         * Counts : 1
         * Operationdate : 2017/8/3 18:04:13
         * DisposeInfo : 审核已通过!
         * Type : 退款
         * Describe : sdf
         * AddressDetail : 西宁市城北区朝阳西路52号3号楼一层商铺
         * RecName : 小李
         * PhoneNum : 18782070820
         * Status : 5
         * ShouldRefundMoney : 38
         * State : 已完成
         */

        private int AfterSaleId;
        private String AfterNum;
        private String ItemPic;
        private String Unit;
        private int ItemID;
        private String ItemName;
        private String ItemSize;
        private int Counts;
        private String Operationdate;
        private String DisposeInfo;
        private String Type;
        private String Describe;
        private String AddressDetail;
        private String RecName;
        private String PhoneNum;
        private int Status;
        private int ShouldRefundMoney;
        private String State;

        public int getAfterSaleId() {
            return AfterSaleId;
        }

        public void setAfterSaleId(int AfterSaleId) {
            this.AfterSaleId = AfterSaleId;
        }

        public String getAfterNum() {
            return AfterNum;
        }

        public void setAfterNum(String AfterNum) {
            this.AfterNum = AfterNum;
        }

        public String getItemPic() {
            return ItemPic;
        }

        public void setItemPic(String ItemPic) {
            this.ItemPic = ItemPic;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String Unit) {
            this.Unit = Unit;
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

        public String getItemSize() {
            return ItemSize;
        }

        public void setItemSize(String ItemSize) {
            this.ItemSize = ItemSize;
        }

        public int getCounts() {
            return Counts;
        }

        public void setCounts(int Counts) {
            this.Counts = Counts;
        }

        public String getOperationdate() {
            return Operationdate;
        }

        public void setOperationdate(String Operationdate) {
            this.Operationdate = Operationdate;
        }

        public String getDisposeInfo() {
            return DisposeInfo;
        }

        public void setDisposeInfo(String DisposeInfo) {
            this.DisposeInfo = DisposeInfo;
        }

        public String getType() {
            return Type;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public String getDescribe() {
            return Describe;
        }

        public void setDescribe(String Describe) {
            this.Describe = Describe;
        }

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String AddressDetail) {
            this.AddressDetail = AddressDetail;
        }

        public String getRecName() {
            return RecName;
        }

        public void setRecName(String RecName) {
            this.RecName = RecName;
        }

        public String getPhoneNum() {
            return PhoneNum;
        }

        public void setPhoneNum(String PhoneNum) {
            this.PhoneNum = PhoneNum;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public int getShouldRefundMoney() {
            return ShouldRefundMoney;
        }

        public void setShouldRefundMoney(int ShouldRefundMoney) {
            this.ShouldRefundMoney = ShouldRefundMoney;
        }

        public String getState() {
            return State;
        }

        public void setState(String State) {
            this.State = State;
        }
    }
}
