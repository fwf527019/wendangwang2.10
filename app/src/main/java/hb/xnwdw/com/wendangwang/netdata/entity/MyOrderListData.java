package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/5.
 */

public class MyOrderListData {


    private List<OrdersBean> orders;

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * ID : 411
         * OrderNumber : 201709200007
         * OrderDate : 2017-09-20 16:25:59
         * TakeTm : 2017-09-20 16:25:59
         * OrderMoney : 35
         * OrderTypeId : 0
         * PracticalMoney : 55
         * OrderCount : 6
         * OrderSource : 1
         * OrderStatus : 1
         * RecName : android
         * Postage : 20
         * CanReturn : 1
         * IsPrint : 0
         * Items : [{"ItemPic":"/UploadImage/ItemPic/201708/e9743072b03a467bb4036f54cc0547ec.jpeg","ItemID":"551","ItemName":"冰岛红鱼 500-700g","ItemSize":"1条","Amount":"1","Unit":"35.00"}]
         * CanCancel : 1
         * CanPay : 1
         * CanDel : 0
         * CanBuyAgain : 0
         * CanReturnMoney : 0
         * CanLogistics : 0
         * CanBeSure : 0
         * CanEvaluate : 0
         * CanAfterSale : 0
         */

        private int ID;
        private String OrderNumber;
        private String OrderDate;
        private String TakeTm;
        private String OrderMoney;
        private String OrderTypeId;
        private String PracticalMoney;
        private String OrderCount;
        private String OrderSource;
        private String OrderStatus;
        private String RecName;
        private String Postage;
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
        private List<ItemsBean> Items;

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

        public String getTakeTm() {
            return TakeTm;
        }

        public void setTakeTm(String TakeTm) {
            this.TakeTm = TakeTm;
        }

        public String getOrderMoney() {
            return OrderMoney;
        }

        public void setOrderMoney(String OrderMoney) {
            this.OrderMoney = OrderMoney;
        }

        public String getOrderTypeId() {
            return OrderTypeId;
        }

        public void setOrderTypeId(String OrderTypeId) {
            this.OrderTypeId = OrderTypeId;
        }

        public String getPracticalMoney() {
            return PracticalMoney;
        }

        public void setPracticalMoney(String PracticalMoney) {
            this.PracticalMoney = PracticalMoney;
        }

        public String getOrderCount() {
            return OrderCount;
        }

        public void setOrderCount(String OrderCount) {
            this.OrderCount = OrderCount;
        }

        public String getOrderSource() {
            return OrderSource;
        }

        public void setOrderSource(String OrderSource) {
            this.OrderSource = OrderSource;
        }

        public String getOrderStatus() {
            return OrderStatus;
        }

        public void setOrderStatus(String OrderStatus) {
            this.OrderStatus = OrderStatus;
        }

        public String getRecName() {
            return RecName;
        }

        public void setRecName(String RecName) {
            this.RecName = RecName;
        }

        public String getPostage() {
            return Postage;
        }

        public void setPostage(String Postage) {
            this.Postage = Postage;
        }

        public int getCanReturn() {
            return CanReturn;
        }

        public void setCanReturn(int CanReturn) {
            this.CanReturn = CanReturn;
        }

        public int getIsPrint() {
            return IsPrint;
        }

        public void setIsPrint(int IsPrint) {
            this.IsPrint = IsPrint;
        }

        public int getCanCancel() {
            return CanCancel;
        }

        public void setCanCancel(int CanCancel) {
            this.CanCancel = CanCancel;
        }

        public int getCanPay() {
            return CanPay;
        }

        public void setCanPay(int CanPay) {
            this.CanPay = CanPay;
        }

        public int getCanDel() {
            return CanDel;
        }

        public void setCanDel(int CanDel) {
            this.CanDel = CanDel;
        }

        public int getCanBuyAgain() {
            return CanBuyAgain;
        }

        public void setCanBuyAgain(int CanBuyAgain) {
            this.CanBuyAgain = CanBuyAgain;
        }

        public int getCanReturnMoney() {
            return CanReturnMoney;
        }

        public void setCanReturnMoney(int CanReturnMoney) {
            this.CanReturnMoney = CanReturnMoney;
        }

        public int getCanLogistics() {
            return CanLogistics;
        }

        public void setCanLogistics(int CanLogistics) {
            this.CanLogistics = CanLogistics;
        }

        public int getCanBeSure() {
            return CanBeSure;
        }

        public void setCanBeSure(int CanBeSure) {
            this.CanBeSure = CanBeSure;
        }

        public int getCanEvaluate() {
            return CanEvaluate;
        }

        public void setCanEvaluate(int CanEvaluate) {
            this.CanEvaluate = CanEvaluate;
        }

        public int getCanAfterSale() {
            return CanAfterSale;
        }

        public void setCanAfterSale(int CanAfterSale) {
            this.CanAfterSale = CanAfterSale;
        }

        public List<ItemsBean> getItems() {
            return Items;
        }

        public void setItems(List<ItemsBean> Items) {
            this.Items = Items;
        }

        public static class ItemsBean {
            /**
             * ItemPic : /UploadImage/ItemPic/201708/e9743072b03a467bb4036f54cc0547ec.jpeg
             * ItemID : 551
             * ItemName : 冰岛红鱼 500-700g
             * ItemSize : 1条
             * Amount : 1
             * Unit : 35.00
             */

            private String ItemPic;
            private String ItemID;
            private String ItemName;
            private String ItemSize;
            private String Amount;
            private String Unit;

            public String getItemPic() {
                return ItemPic;
            }

            public void setItemPic(String ItemPic) {
                this.ItemPic = ItemPic;
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

            public String getItemSize() {
                return ItemSize;
            }

            public void setItemSize(String ItemSize) {
                this.ItemSize = ItemSize;
            }

            public String getAmount() {
                return Amount;
            }

            public void setAmount(String Amount) {
                this.Amount = Amount;
            }

            public String getUnit() {
                return Unit;
            }

            public void setUnit(String Unit) {
                this.Unit = Unit;
            }
        }
    }
}
