package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class MysaleApplyData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"orders":[{"ID":690,"OrderNumber":"201707250014","OrderDate":"2017-07-25 15:24:46","OrderMoney":0,"OrderTypeId":2,"PracticalMoney":0,"OrderCount":3,"OrderSource":"2","OrderStatus":9,"RecName":"陈冠希","Postage":0,"IsPrint":0,"Items":[{"ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ItemID":"1024","ItemName":"青口贝","ItemSize":"1kg","Amount":"1","Unit":"0.00"}]},{"ID":689,"OrderNumber":"201707250013","OrderDate":"2017-07-25 15:15:13","OrderMoney":0,"OrderTypeId":2,"PracticalMoney":0,"OrderCount":3,"OrderSource":"2","OrderStatus":9,"RecName":"陈冠希","Postage":0,"IsPrint":0,"Items":[{"ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ItemID":"1024","ItemName":"青口贝","ItemSize":"1kg","Amount":"1","Unit":"0.00"}]},{"ID":669,"OrderNumber":"201707240014","OrderDate":"2017-07-24 14:18:21","OrderMoney":20,"OrderTypeId":0,"PracticalMoney":20,"OrderCount":3,"OrderSource":"3","OrderStatus":9,"RecName":"木子李","Postage":0,"IsPrint":0,"Items":[{"ItemPic":"/UploadImage/ItemPic/201707/c3b00a8652264062ae0eba6de9824e1c.jpeg","ItemID":"1064","ItemName":"鲜果","ItemSize":"60g","Amount":"1","Unit":"20.00"}]}]}
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
        private List<OrdersBean> orders;

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * ID : 690
             * OrderNumber : 201707250014
             * OrderDate : 2017-07-25 15:24:46
             * OrderMoney : 0
             * OrderTypeId : 2
             * PracticalMoney : 0
             * OrderCount : 3
             * OrderSource : 2
             * OrderStatus : 9
             * RecName : 陈冠希
             * Postage : 0
             * IsPrint : 0
             * Items : [{"ItemPic":"/UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg","ItemID":"1024","ItemName":"青口贝","ItemSize":"1kg","Amount":"1","Unit":"0.00"}]
             */

            private int ID;
            private String OrderNumber;
            private String OrderDate;
            private int OrderMoney;
            private int OrderTypeId;
            private int PracticalMoney;
            private int OrderCount;
            private String OrderSource;
            private int OrderStatus;
            private String RecName;
            private int Postage;
            private int IsPrint;
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

            public int getOrderMoney() {
                return OrderMoney;
            }

            public void setOrderMoney(int OrderMoney) {
                this.OrderMoney = OrderMoney;
            }

            public int getOrderTypeId() {
                return OrderTypeId;
            }

            public void setOrderTypeId(int OrderTypeId) {
                this.OrderTypeId = OrderTypeId;
            }

            public int getPracticalMoney() {
                return PracticalMoney;
            }

            public void setPracticalMoney(int PracticalMoney) {
                this.PracticalMoney = PracticalMoney;
            }

            public int getOrderCount() {
                return OrderCount;
            }

            public void setOrderCount(int OrderCount) {
                this.OrderCount = OrderCount;
            }

            public String getOrderSource() {
                return OrderSource;
            }

            public void setOrderSource(String OrderSource) {
                this.OrderSource = OrderSource;
            }

            public int getOrderStatus() {
                return OrderStatus;
            }

            public void setOrderStatus(int OrderStatus) {
                this.OrderStatus = OrderStatus;
            }

            public String getRecName() {
                return RecName;
            }

            public void setRecName(String RecName) {
                this.RecName = RecName;
            }

            public int getPostage() {
                return Postage;
            }

            public void setPostage(int Postage) {
                this.Postage = Postage;
            }

            public int getIsPrint() {
                return IsPrint;
            }

            public void setIsPrint(int IsPrint) {
                this.IsPrint = IsPrint;
            }

            public List<ItemsBean> getItems() {
                return Items;
            }

            public void setItems(List<ItemsBean> Items) {
                this.Items = Items;
            }

            public static class ItemsBean {
                /**
                 * ItemPic : /UploadImage/ItemPic/201706/c6a88b9e01664b8dab8e7fb27e6bf24a.jpeg
                 * ItemID : 1024
                 * ItemName : 青口贝
                 * ItemSize : 1kg
                 * Amount : 1
                 * Unit : 0.00
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
}
