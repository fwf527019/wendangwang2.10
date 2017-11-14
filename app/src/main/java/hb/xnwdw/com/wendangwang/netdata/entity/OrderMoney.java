package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/6/19.
 */

public class OrderMoney {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":null,"ID":1221,"OrderNumber":"201706150002","OrderDate":"0001-01-01 00:00:00","OrderMoney":0,"IntegralDeduction":0,"DiscountCouponDeduction":0,"Postage":0,"PracticalMoney":118,"UserId":0,"UserAddId":0,"OrderStatus":0,"OrderSource":0,"PayMode":0,"OrderTypeId":0,"ShoppeId":0,"ShoppeName":null,"LogisticsTypeId":0,"LogisticsCompany":null,"FreightNumber":null,"UserMessage":null,"PayDate":null,"OderOutDate":null,"IsSend":0,"DeleteLevel":null,"SellWay":null,"VoucherId":null,"TakeTm":null,"AddressDetail":null,"PhoneNum":null,"RecName":null,"FetchStoreDate":null,"inventList":null,"memberInfo":null,"Address":null,"UsedIntegral":0,"CanReturn":0,"buyId":null,"IsPrint":0,"IsDraw":0}
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
         * GUID : null
         * ID : 1221
         * OrderNumber : 201706150002
         * OrderDate : 0001-01-01 00:00:00
         * OrderMoney : 0
         * IntegralDeduction : 0
         * DiscountCouponDeduction : 0
         * Postage : 0
         * PracticalMoney : 118
         * UserId : 0
         * UserAddId : 0
         * OrderStatus : 0
         * OrderSource : 0
         * PayMode : 0
         * OrderTypeId : 0
         * ShoppeId : 0
         * ShoppeName : null
         * LogisticsTypeId : 0
         * LogisticsCompany : null
         * FreightNumber : null
         * UserMessage : null
         * PayDate : null
         * OderOutDate : null
         * IsSend : 0
         * DeleteLevel : null
         * SellWay : null
         * VoucherId : null
         * TakeTm : null
         * AddressDetail : null
         * PhoneNum : null
         * RecName : null
         * FetchStoreDate : null
         * inventList : null
         * memberInfo : null
         * Address : null
         * UsedIntegral : 0
         * CanReturn : 0
         * buyId : null
         * IsPrint : 0
         * IsDraw : 0
         */

        private Object GUID;
        private int ID;
        private String OrderNumber;
        private String OrderDate;
        private int OrderMoney;
        private int IntegralDeduction;
        private int DiscountCouponDeduction;
        private int Postage;
        private String PracticalMoney;
        private int UserId;
        private int UserAddId;
        private int OrderStatus;
        private int OrderSource;
        private int PayMode;
        private int OrderTypeId;
        private int ShoppeId;
        private Object ShoppeName;
        private int LogisticsTypeId;
        private Object LogisticsCompany;
        private Object FreightNumber;
        private Object UserMessage;
        private Object PayDate;
        private Object OderOutDate;
        private int IsSend;
        private Object DeleteLevel;
        private Object SellWay;
        private Object VoucherId;
        private Object TakeTm;
        private Object AddressDetail;
        private Object PhoneNum;
        private Object RecName;
        private Object FetchStoreDate;
        private Object inventList;
        private Object memberInfo;
        private Object Address;
        private int UsedIntegral;
        private int CanReturn;
        private Object buyId;
        private int IsPrint;
        private int IsDraw;

        public Object getGUID() {
            return GUID;
        }

        public void setGUID(Object GUID) {
            this.GUID = GUID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
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

        public int getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(int OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public int getIntegralDeduction() {
            return IntegralDeduction;
        }

        public void setIntegralDeduction(int IntegralDeduction) {
            this.IntegralDeduction = IntegralDeduction;
        }

        public int getDiscountCouponDeduction() {
            return DiscountCouponDeduction;
        }

        public void setDiscountCouponDeduction(int DiscountCouponDeduction) {
            this.DiscountCouponDeduction = DiscountCouponDeduction;
        }

        public int getPostage() {
            return Postage;
        }

        public void setPostage(int Postage) {
            this.Postage = Postage;
        }

        public String getPracticalMoney() {
            return PracticalMoney;
        }

        public void setPracticalMoney(String PracticalMoney) {
            this.PracticalMoney = PracticalMoney;
        }

        public int getUserId() {
            return UserId;
        }

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public int getUserAddId() {
            return UserAddId;
        }

        public void setUserAddId(int UserAddId) {
            this.UserAddId = UserAddId;
        }

        public int getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(int OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public int getOrderSource() {
            return OrderSource;
        }

        public void setOrderSource(int OrderSource) {
            this.OrderSource = OrderSource;
        }

        public int getPayMode() {
            return PayMode;
        }

        public void setPayMode(int PayMode) {
            this.PayMode = PayMode;
        }

        public int getOrderTypeId() {
            return OrderTypeId;
        }

        public void setOrderTypeId(int OrderTypeId) {
            this.OrderTypeId = OrderTypeId;
        }

        public int getShoppeId() {
            return ShoppeId;
        }

        public void setShoppeId(int ShoppeId) {
            this.ShoppeId = ShoppeId;
        }

        public Object getShoppeName() {
            return ShoppeName;
        }

        public void setShoppeName(Object ShoppeName) {
            this.ShoppeName = ShoppeName;
        }

        public int getLogisticsTypeId() {
            return LogisticsTypeId;
        }

        public void setLogisticsTypeId(int LogisticsTypeId) {
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

        public Object getUserMessage() {
            return UserMessage;
        }

        public void setUserMessage(Object UserMessage) {
            this.UserMessage = UserMessage;
        }

        public Object getPayDate() {
            return PayDate;
        }

        public void setPayDate(Object PayDate) {
            this.PayDate = PayDate;
        }

        public Object getOderOutDate() {
            return OderOutDate;
        }

        public void setOderOutDate(Object OderOutDate) {
            this.OderOutDate = OderOutDate;
        }

        public int getIsSend() {
            return IsSend;
        }

        public void setIsSend(int IsSend) {
            this.IsSend = IsSend;
        }

        public Object getDeleteLevel() {
            return DeleteLevel;
        }

        public void setDeleteLevel(Object DeleteLevel) {
            this.DeleteLevel = DeleteLevel;
        }

        public Object getSellWay() {
            return SellWay;
        }

        public void setSellWay(Object SellWay) {
            this.SellWay = SellWay;
        }

        public Object getVoucherId() {
            return VoucherId;
        }

        public void setVoucherId(Object VoucherId) {
            this.VoucherId = VoucherId;
        }

        public Object getTakeTm() {
            return TakeTm;
        }

        public void setTakeTm(Object TakeTm) {
            this.TakeTm = TakeTm;
        }

        public Object getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(Object AddressDetail) {
            this.AddressDetail = AddressDetail;
        }

        public Object getPhoneNum() {
            return PhoneNum;
        }

        public void setPhoneNum(Object PhoneNum) {
            this.PhoneNum = PhoneNum;
        }

        public Object getRecName() {
            return RecName;
        }

        public void setRecName(Object RecName) {
            this.RecName = RecName;
        }

        public Object getFetchStoreDate() {
            return FetchStoreDate;
        }

        public void setFetchStoreDate(Object FetchStoreDate) {
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

        public int getUsedIntegral() {
            return UsedIntegral;
        }

        public void setUsedIntegral(int UsedIntegral) {
            this.UsedIntegral = UsedIntegral;
        }

        public int getCanReturn() {
            return CanReturn;
        }

        public void setCanReturn(int CanReturn) {
            this.CanReturn = CanReturn;
        }

        public Object getBuyId() {
            return buyId;
        }

        public void setBuyId(Object buyId) {
            this.buyId = buyId;
        }

        public int getIsPrint() {
            return IsPrint;
        }

        public void setIsPrint(int IsPrint) {
            this.IsPrint = IsPrint;
        }

        public int getIsDraw() {
            return IsDraw;
        }

        public void setIsDraw(int IsDraw) {
            this.IsDraw = IsDraw;
        }
    }
}
