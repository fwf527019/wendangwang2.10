package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/8.
 */

public class OrderDetailData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"orderSummary":{"GUID":"03b50571de45430f96e64072c8e8090f","ID":1018,"OrderNumber":"2017042814082000007886","OrderDate":"2017-04-28 14:08:20","OrderMoney":164.43,"IntegralDeduction":0,"DiscountCouponDeduction":111,"Postage":18,"PracticalMoney":71.43,"UserId":10,"UserAddId":0,"OrderStatus":1,"OrderSource":2,"PayMode":0,"OrderTypeId":0,"ShoppeId":0,"ShoppeName":null,"LogisticsTypeId":2,"LogisticsCompany":null,"FreightNumber":null,"UserMessage":"速度发货","PayDate":"2017-04-28 14:08:21","OderOutDate":"2017-04-28 14:08:21","IsSend":0,"DeleteLevel":1,"SellWay":"A","VoucherId":null,"TakeTm":"2017-04-28 14:08:21","AddressDetail":"青海海北藏族自治州海晏县海晏县老中区天华二街3号天天花园13栋1201","PhoneNum":"13512894556","RecName":"小马刺","FetchStoreDate":"2017-04-28 14:08:21","inventList":null,"memberInfo":null,"Address":null,"UsedIntegral":0,"CanReturn":1},"orderDetails":[{"OrderId":1018,"ItemID":1,"ItemName":"托牛所高档牛排1","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/2983f611cfe640a5afd28ce4cd5c939b.jpg","Amount":2,"CouponID":7,"CouponCode":"1492594045293L3","Unit":40.5,"CostUnit":50,"Subtotal":81},{"OrderId":1018,"ItemID":4,"ItemName":"托牛所高档牛排4","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/2294441ef5484c048eafbbcb6d8b919f.jpg","Amount":2,"CouponID":7,"CouponCode":"1492594045293L3","Unit":32.4,"CostUnit":40,"Subtotal":64.8},{"OrderId":1018,"ItemID":5,"ItemName":"托牛所高档牛排5","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/3aaf5e96768b499fa0d9acc25ee3d658.jpg","Amount":1,"CouponID":7,"CouponCode":"1492594045293L3","Unit":18.63,"CostUnit":23,"Subtotal":18.63}],"logisticsInfo":[]}
     * dataStatus : 1
     * describe : null
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private ObjBean obj;
    private int dataStatus;
    private Object describe;

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

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
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

    public static class ObjBean {
        /**
         * orderSummary : {"GUID":"03b50571de45430f96e64072c8e8090f","ID":1018,"OrderNumber":"2017042814082000007886","OrderDate":"2017-04-28 14:08:20","OrderMoney":164.43,"IntegralDeduction":0,"DiscountCouponDeduction":111,"Postage":18,"PracticalMoney":71.43,"UserId":10,"UserAddId":0,"OrderStatus":1,"OrderSource":2,"PayMode":0,"OrderTypeId":0,"ShoppeId":0,"ShoppeName":null,"LogisticsTypeId":2,"LogisticsCompany":null,"FreightNumber":null,"UserMessage":"速度发货","PayDate":"2017-04-28 14:08:21","OderOutDate":"2017-04-28 14:08:21","IsSend":0,"DeleteLevel":1,"SellWay":"A","VoucherId":null,"TakeTm":"2017-04-28 14:08:21","AddressDetail":"青海海北藏族自治州海晏县海晏县老中区天华二街3号天天花园13栋1201","PhoneNum":"13512894556","RecName":"小马刺","FetchStoreDate":"2017-04-28 14:08:21","inventList":null,"memberInfo":null,"Address":null,"UsedIntegral":0,"CanReturn":1}
         * orderDetails : [{"OrderId":1018,"ItemID":1,"ItemName":"托牛所高档牛排1","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/2983f611cfe640a5afd28ce4cd5c939b.jpg","Amount":2,"CouponID":7,"CouponCode":"1492594045293L3","Unit":40.5,"CostUnit":50,"Subtotal":81},{"OrderId":1018,"ItemID":4,"ItemName":"托牛所高档牛排4","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/2294441ef5484c048eafbbcb6d8b919f.jpg","Amount":2,"CouponID":7,"CouponCode":"1492594045293L3","Unit":32.4,"CostUnit":40,"Subtotal":64.8},{"OrderId":1018,"ItemID":5,"ItemName":"托牛所高档牛排5","Norms":"袋","ItemPic":"/UploadImage/ItemPic/201704/3aaf5e96768b499fa0d9acc25ee3d658.jpg","Amount":1,"CouponID":7,"CouponCode":"1492594045293L3","Unit":18.63,"CostUnit":23,"Subtotal":18.63}]
         * logisticsInfo : []
         */

        private OrderSummaryBean orderSummary;
        private List<OrderDetailsBean> orderDetails;
        private List<?> logisticsInfo;
        private OrderOptionBean orderOption;

        public OrderOptionBean getOrderOption() {
            return orderOption;
        }

        public void setOrderOption(OrderOptionBean orderOption) {
            this.orderOption = orderOption;
        }

        public OrderSummaryBean getOrderSummary() {
            return orderSummary;
        }

        public void setOrderSummary(OrderSummaryBean orderSummary) {
            this.orderSummary = orderSummary;
        }

        public List<OrderDetailsBean> getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(List<OrderDetailsBean> orderDetails) {
            this.orderDetails = orderDetails;
        }

        public List<?> getLogisticsInfo() {
            return logisticsInfo;
        }

        public void setLogisticsInfo(List<?> logisticsInfo) {
            this.logisticsInfo = logisticsInfo;
        }

        public static class OrderSummaryBean {
            /**
             * GUID : 03b50571de45430f96e64072c8e8090f
             * ID : 1018
             * OrderNumber : 2017042814082000007886
             * OrderDate : 2017-04-28 14:08:20
             * OrderMoney : 164.43
             * IntegralDeduction : 0
             * DiscountCouponDeduction : 111
             * Postage : 18
             * PracticalMoney : 71.43
             * UserId : 10
             * UserAddId : 0
             * OrderStatus : 1
             * OrderSource : 2
             * PayMode : 0
             * OrderTypeId : 0
             * ShoppeId : 0
             * ShoppeName : null
             * LogisticsTypeId : 2
             * LogisticsCompany : null
             * FreightNumber : null
             * UserMessage : 速度发货
             * PayDate : 2017-04-28 14:08:21
             * OderOutDate : 2017-04-28 14:08:21
             * IsSend : 0
             * DeleteLevel : 1
             * SellWay : A
             * VoucherId : null
             * TakeTm : 2017-04-28 14:08:21
             * AddressDetail : 青海海北藏族自治州海晏县海晏县老中区天华二街3号天天花园13栋1201
             * PhoneNum : 13512894556
             * RecName : 小马刺
             * FetchStoreDate : 2017-04-28 14:08:21
             * inventList : null
             * memberInfo : null
             * Address : null
             * UsedIntegral : 0
             * CanReturn : 1
             */

            private String GUID;
            private String ID;
            private String OrderNumber;
            private String OrderDate;
            private String OrderMoney;
            private String IntegralDeduction;
            private String DiscountCouponDeduction;
            private String Postage;
            private String PracticalMoney;
            private String UserId;
            private String UserAddId;
            private String OrderStatus;
            private String OrderSource;
            private String PayMode;
            private String OrderTypeId;
            private String ShoppeId;
            private String ShoppeName;
            private String LogisticsTypeId;
            private Object LogisticsCompany;
            private Object FreightNumber;
            private String UserMessage;
            private String PayDate;
            private String OderOutDate;
            private String IsSend;
            private String DeleteLevel;
            private String SellWay;
            private Object VoucherId;
            private String TakeTm;
            private String AddressDetail;
            private String PhoneNum;
            private String RecName;
            private String FetchStoreDate;
            private Object inventList;
            private Object memberInfo;
            private Object Address;
            private String UsedIntegral;
            private String CanReturn;

            public String getGUID() {
                return GUID;
            }

            public void setGUID(String GUID) {
                this.GUID = GUID;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getOrderNumber() {
                return OrderNumber;
            }

            public void setOrderNumber(String OrderNumber) {
                this.OrderNumber = OrderNumber;
            }

            public String getOrderDate() {
                return OrderDate;
            }

            public void setOrderDate(String OrderDate) {
                this.OrderDate = OrderDate;
            }

            public String getOrderMoney() {
                return OrderMoney;
            }

            public void setOrderMoney(String OrderMoney) {
                this.OrderMoney = OrderMoney;
            }

            public String getIntegralDeduction() {
                return IntegralDeduction;
            }

            public void setIntegralDeduction(String IntegralDeduction) {
                this.IntegralDeduction = IntegralDeduction;
            }

            public String getDiscountCouponDeduction() {
                return DiscountCouponDeduction;
            }

            public void setDiscountCouponDeduction(String DiscountCouponDeduction) {
                this.DiscountCouponDeduction = DiscountCouponDeduction;
            }

            public String getPostage() {
                return Postage;
            }

            public void setPostage(String Postage) {
                this.Postage = Postage;
            }

            public String getPracticalMoney() {
                return PracticalMoney;
            }

            public void setPracticalMoney(String PracticalMoney) {
                this.PracticalMoney = PracticalMoney;
            }

            public String getUserId() {
                return UserId;
            }

            public void setUserId(String UserId) {
                this.UserId = UserId;
            }

            public String getUserAddId() {
                return UserAddId;
            }

            public void setUserAddId(String UserAddId) {
                this.UserAddId = UserAddId;
            }

            public String getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(String OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public String getOrderSource() {
                return OrderSource;
            }

            public void setOrderSource(String OrderSource) {
                this.OrderSource = OrderSource;
            }

            public String getPayMode() {
                return PayMode;
            }

            public void setPayMode(String PayMode) {
                this.PayMode = PayMode;
            }

            public String getOrderTypeId() {
                return OrderTypeId;
            }

            public void setOrderTypeId(String OrderTypeId) {
                this.OrderTypeId = OrderTypeId;
            }

            public String getShoppeId() {
                return ShoppeId;
            }

            public void setShoppeId(String ShoppeId) {
                this.ShoppeId = ShoppeId;
            }

            public String  getShoppeName() {
                return ShoppeName;
            }

            public void setShoppeName(String ShoppeName) {
                this.ShoppeName = ShoppeName;
            }

            public String getLogisticsTypeId() {
                return LogisticsTypeId;
            }

            public void setLogisticsTypeId(String LogisticsTypeId) {
                this.LogisticsTypeId = LogisticsTypeId;
            }

            public Object getLogisticsCompany() {
                return LogisticsCompany;
            }

            public void setLogisticsCompany(Object LogisticsCompany) {
                this.LogisticsCompany = LogisticsCompany;
            }

            public Object getFreightNumber() {
                return FreightNumber;
            }

            public void setFreightNumber(Object FreightNumber) {
                this.FreightNumber = FreightNumber;
            }

            public String getUserMessage() {
                return UserMessage;
            }

            public void setUserMessage(String UserMessage) {
                this.UserMessage = UserMessage;
            }

            public String getPayDate() {
                return PayDate;
            }

            public void setPayDate(String PayDate) {
                this.PayDate = PayDate;
            }

            public String getOderOutDate() {
                return OderOutDate;
            }

            public void setOderOutDate(String OderOutDate) {
                this.OderOutDate = OderOutDate;
            }

            public String getIsSend() {
                return IsSend;
            }

            public void setIsSend(String IsSend) {
                this.IsSend = IsSend;
            }

            public String getDeleteLevel() {
                return DeleteLevel;
            }

            public void setDeleteLevel(String DeleteLevel) {
                this.DeleteLevel = DeleteLevel;
            }

            public String getSellWay() {
                return SellWay;
            }

            public void setSellWay(String SellWay) {
                this.SellWay = SellWay;
            }

            public Object getVoucherId() {
                return VoucherId;
            }

            public void setVoucherId(Object VoucherId) {
                this.VoucherId = VoucherId;
            }

            public String getTakeTm() {
                return TakeTm;
            }

            public void setTakeTm(String TakeTm) {
                this.TakeTm = TakeTm;
            }

            public String getAddressDetail() {
                return AddressDetail;
            }

            public void setAddressDetail(String AddressDetail) {
                this.AddressDetail = AddressDetail;
            }

            public String getPhoneNum() {
                return PhoneNum;
            }

            public void setPhoneNum(String PhoneNum) {
                this.PhoneNum = PhoneNum;
            }

            public String getRecName() {
                return RecName;
            }

            public void setRecName(String RecName) {
                this.RecName = RecName;
            }

            public String getFetchStoreDate() {
                return FetchStoreDate;
            }

            public void setFetchStoreDate(String FetchStoreDate) {
                this.FetchStoreDate = FetchStoreDate;
            }

            public Object getInventList() {
                return inventList;
            }

            public void setInventList(Object inventList) {
                this.inventList = inventList;
            }

            public Object getMemberInfo() {
                return memberInfo;
            }

            public void setMemberInfo(Object memberInfo) {
                this.memberInfo = memberInfo;
            }

            public Object getAddress() {
                return Address;
            }

            public void setAddress(Object Address) {
                this.Address = Address;
            }

            public String getUsedIntegral() {
                return UsedIntegral;
            }

            public void setUsedIntegral(String UsedIntegral) {
                this.UsedIntegral = UsedIntegral;
            }

            public String getCanReturn() {
                return CanReturn;
            }

            public void setCanReturn(String CanReturn) {
                this.CanReturn = CanReturn;
            }
        }

        public static class OrderDetailsBean {
            /**
             * OrderId : 1018
             * ItemID : 1
             * ItemName : 托牛所高档牛排1
             * Norms : 袋
             * ItemPic : /UploadImage/ItemPic/201704/2983f611cfe640a5afd28ce4cd5c939b.jpg
             * Amount : 2
             * CouponID : 7
             * CouponCode : 1492594045293L3
             * Unit : 40.5
             * CostUnit : 50
             * Subtotal : 81
             */

            private String OrderId;
            private String ItemID;
            private String ItemName;
            private String Norms;
            private String ItemPic;
            private String Amount;
            private String CouponID;
            private String CouponCode;
            private String Unit;
            private String CostUnit;
            private String Subtotal;

            public String getOrderId() {
                return OrderId;
            }

            public void setOrderId(String OrderId) {
                this.OrderId = OrderId;
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

            public String getNorms() {
                return Norms;
            }

            public void setNorms(String Norms) {
                this.Norms = Norms;
            }

            public String getItemPic() {
                return ItemPic;
            }

            public void setItemPic(String ItemPic) {
                this.ItemPic = ItemPic;
            }

            public String getAmount() {
                return Amount;
            }

            public void setAmount(String Amount) {
                this.Amount = Amount;
            }

            public String getCouponID() {
                return CouponID;
            }

            public void setCouponID(String CouponID) {
                this.CouponID = CouponID;
            }

            public String getCouponCode() {
                return CouponCode;
            }

            public void setCouponCode(String CouponCode) {
                this.CouponCode = CouponCode;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }

            public String getCostUnit() {
                return CostUnit;
            }

            public void setCostUnit(String CostUnit) {
                this.CostUnit = CostUnit;
            }

            public String getSubtotal() {
                return Subtotal;
            }

            public void setSubtotal(String Subtotal) {
                this.Subtotal = Subtotal;
            }
        }

        public class OrderOptionBean {
            private int CanReturn;
            private int IsPrint;
            private int CanCancel;
            private int CanPay;
            private int CanDel;
            private int CanBuyAgain;
            private int CanReturnMoney;
            private int CanLogistics;
            private int CanBeSure;
            private int CanEvaluate;
            private int CanAfterSale;

            public int getCanReturn() {
                return CanReturn;
            }

            public void setCanReturn(int canReturn) {
                CanReturn = canReturn;
            }

            public int getIsPrint() {
                return IsPrint;
            }

            public void setIsPrint(int isPrint) {
                IsPrint = isPrint;
            }

            public int getCanCancel() {
                return CanCancel;
            }

            public void setCanCancel(int canCancel) {
                CanCancel = canCancel;
            }

            public int getCanPay() {
                return CanPay;
            }

            public void setCanPay(int canPay) {
                CanPay = canPay;
            }

            public int getCanDel() {
                return CanDel;
            }

            public void setCanDel(int canDel) {
                CanDel = canDel;
            }

            public int getCanBuyAgain() {
                return CanBuyAgain;
            }

            public void setCanBuyAgain(int canBuyAgain) {
                CanBuyAgain = canBuyAgain;
            }

            public int getCanReturnMoney() {
                return CanReturnMoney;
            }

            public void setCanReturnMoney(int canReturnMoney) {
                CanReturnMoney = canReturnMoney;
            }

            public int getCanLogistics() {
                return CanLogistics;
            }

            public void setCanLogistics(int canLogistics) {
                CanLogistics = canLogistics;
            }

            public int getCanBeSure() {
                return CanBeSure;
            }

            public void setCanBeSure(int canBeSure) {
                CanBeSure = canBeSure;
            }

            public int getCanEvaluate() {
                return CanEvaluate;
            }

            public void setCanEvaluate(int canEvaluate) {
                CanEvaluate = canEvaluate;
            }

            public int getCanAfterSale() {
                return CanAfterSale;
            }

            public void setCanAfterSale(int canAfterSale) {
                CanAfterSale = canAfterSale;
            }
        }
    }
}
