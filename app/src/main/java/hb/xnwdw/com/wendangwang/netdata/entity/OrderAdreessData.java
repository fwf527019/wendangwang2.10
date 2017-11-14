package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public class OrderAdreessData {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * MemberID : 7687
         * PhoneNum : 13555555555
         * GUID : fd09b0eef098479d9a47f02d5e907a23
         * IsDefault : 1
         * County : 茅箭区
         * RecName : 1111
         * ID : 172
         * City : 十堰市
         * Province : 湖北省
         * AddressDetail : 可口可乐了路
         */

        private int MemberID;
        private String PhoneNum;
        private String GUID;
        private int IsDefault;
        private String County;
        private String RecName;
        private int ID;
        private String City;
        private String Province;
        private String AddressDetail;

        public int getMemberID() {
            return MemberID;
        }

        public void setMemberID(int MemberID) {
            this.MemberID = MemberID;
        }

        public String getPhoneNum() {
            return PhoneNum;
        }

        public void setPhoneNum(String PhoneNum) {
            this.PhoneNum = PhoneNum;
        }

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }

        public String getCounty() {
            return County;
        }

        public void setCounty(String County) {
            this.County = County;
        }

        public String getRecName() {
            return RecName;
        }

        public void setRecName(String RecName) {
            this.RecName = RecName;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String AddressDetail) {
            this.AddressDetail = AddressDetail;
        }
    }
}
