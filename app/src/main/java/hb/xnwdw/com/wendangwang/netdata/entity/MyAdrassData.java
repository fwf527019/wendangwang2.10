package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/9/15.
 */

public class MyAdrassData  {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"8f044209e59f4c329e2e912d88be8b74","ID":291,"MemberID":7687,"RecName":"你莫13688546666","PhoneNum":"13655852225","Province":"青海省","City":"西宁市","County":"城中区","AddressDetail":"哦P民","IsDefault":0,"Memo":null}
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
         * GUID : 8f044209e59f4c329e2e912d88be8b74
         * ID : 291
         * MemberID : 7687
         * RecName : 你莫13688546666
         * PhoneNum : 13655852225
         * Province : 青海省
         * City : 西宁市
         * County : 城中区
         * AddressDetail : 哦P民
         * IsDefault : 0
         * Memo : null
         */

        private String GUID;
        private String ID;
        private String MemberID;
        private String RecName;
        private String PhoneNum;
        private String Province;
        private String City;
        private String County;
        private String AddressDetail;
        private int IsDefault;
        private Object Memo;

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

        public String getMemberID() {
            return MemberID;
        }

        public void setMemberID(String MemberID) {
            this.MemberID = MemberID;
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

        public String getProvince() {
            return Province;
        }

        public void setProvince(String Province) {
            this.Province = Province;
        }

        public String getCity() {
            return City;
        }

        public void setCity(String City) {
            this.City = City;
        }

        public String getCounty() {
            return County;
        }

        public void setCounty(String County) {
            this.County = County;
        }

        public String getAddressDetail() {
            return AddressDetail;
        }

        public void setAddressDetail(String AddressDetail) {
            this.AddressDetail = AddressDetail;
        }

        public int getIsDefault() {
            return IsDefault;
        }

        public void setIsDefault(int IsDefault) {
            this.IsDefault = IsDefault;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }
    }
}
