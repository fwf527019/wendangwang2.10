package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/4/28.
 */

public class Basic_RecAddressBean {


    private String ID;
    private int MemberID;
    private String RecName;
    private String PhoneNum;
    private String Province;
    private String City;
    private String County;
    private String AddressDetail;
    private int IsDefault;
    private Object Memo;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }

    public String getRecName() {
        return RecName;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCounty() {
        return County;
    }

    public void setCounty(String county) {
        County = county;
    }

    public String getAddressDetail() {
        return AddressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        AddressDetail = addressDetail;
    }

    public int getIsDefault() {
        return IsDefault;
    }

    public void setIsDefault(int isDefault) {
        IsDefault = isDefault;
    }

    public Object getMemo() {
        return Memo;
    }

    public void setMemo(Object memo) {
        Memo = memo;
    }

}
