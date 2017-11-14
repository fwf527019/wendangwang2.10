package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class OrderGoodsInfo implements Serializable {
    String itemId;
    String itemSizi;
    double itemPrice;
    String itemPic;
    String itemName;
    String itemAct;
    int Amount;
    List<PresentItems> presentItems;

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

    public String getItemId() {
        return itemId;
    }

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public List<PresentItems> getPresentItems() {
        return presentItems;
    }

    public void setPresentItems(List<PresentItems> presentItems) {
        this.presentItems = presentItems;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemSizi() {
        return itemSizi;
    }

    public void setItemSizi(String itemSizi) {
        this.itemSizi = itemSizi;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public static class PresentItems implements Serializable{
        String pitemId;
        String pitemSizi;
        double pitemPrice;
        String pitemPic;
        String tag;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getPitemName() {
            return pitemName;
        }

        public void setPitemName(String pitemName) {
            this.pitemName = pitemName;
        }

        String pitemName;

        public String getPitemId() {
            return pitemId;
        }

        public void setPitemId(String pitemId) {
            this.pitemId = pitemId;
        }

        public String getPitemSizi() {
            return pitemSizi;
        }

        public void setPitemSizi(String pitemSizi) {
            this.pitemSizi = pitemSizi;
        }

        public double getPitemPrice() {
            return pitemPrice;
        }

        public void setPitemPrice(double pitemPrice) {
            this.pitemPrice = pitemPrice;
        }

        public String getPitemPic() {
            return pitemPic;
        }

        public void setPitemPic(String pitemPic) {
            this.pitemPic = pitemPic;
        }
    }
}
