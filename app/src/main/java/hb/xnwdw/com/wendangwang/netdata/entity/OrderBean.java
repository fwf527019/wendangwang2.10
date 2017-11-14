package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */

public class OrderBean {
    public double OrderMoney;
    public double IntegralDeduction;
    public double DiscountCouponDeduction;
    public  int Postage;
    public double PracticalMoney;
    public String ShoppeId;
    public String ShoppeName;
    public String LogisticsTypeId;
    public  String UserMessage;
    public String RecName;
    public String PhoneNum;
    public String AddressDetail;





    public void setOrderMoney(double orderMoney) {
        OrderMoney = orderMoney;
    }

    public void setIntegralDeduction(double integralDeduction) {
        IntegralDeduction = integralDeduction;
    }

    public void setDiscountCouponDeduction(double discountCouponDeduction) {
        DiscountCouponDeduction = discountCouponDeduction;
    }

    public void setPostage(int postage) {
        Postage = postage;
    }

    public void setPracticalMoney(double practicalMoney) {
        PracticalMoney = practicalMoney;
    }

    public void setShoppeId(String shoppeId) {
        ShoppeId = shoppeId;
    }

    public void setShoppeName(String shoppeName) {
        ShoppeName = shoppeName;
    }

    public void setLogisticsTypeId(String logisticsTypeId) {
        LogisticsTypeId = logisticsTypeId;
    }

    public void setUserMessage(String userMessage) {
        UserMessage = userMessage;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public void setAddressDetail(String addressDetail) {
        AddressDetail = addressDetail;
    }


}
