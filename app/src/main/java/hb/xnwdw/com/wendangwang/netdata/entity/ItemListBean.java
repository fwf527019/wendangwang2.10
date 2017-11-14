package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/20.
 */

public class ItemListBean {
        public String ItemID;
        public int Amount;
        public String itemAct;
        public String itemName;
        public String itemSizi;
        public String itemPrice;
        public String itemPic;
        public List<PresentItemBean> presentItemList;

    public List<PresentItemBean> getPresentItemList() {
        return presentItemList;
    }

    public void setPresentItemList(List<PresentItemBean> presentItemList) {
        this.presentItemList = presentItemList;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getItemAct() {
        return itemAct;
    }

    public void setItemAct(String itemAct) {
        this.itemAct = itemAct;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSizi() {
        return itemSizi;
    }

    public void setItemSizi(String itemSizi) {
        this.itemSizi = itemSizi;
    }

    public static  class PresentItemBean {
        String ItemName;
        int PresentNum;

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String itemName) {
            ItemName = itemName;
        }

        public int getPresentNum() {
            return PresentNum;
        }

        public void setPresentNum(int presentNum) {
            PresentNum = presentNum;
        }
    }
}
