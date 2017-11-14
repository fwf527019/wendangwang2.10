package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */

public class MyAdrasseData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"4c9f35e1be584ace9d79e59caddeb873","ID":1,"MemberID":10,"RecName":"张三","PhoneNum":"15623124523","Province":"青海","City":"西宁市","County":"城西区","AddressDetail":"张三三","IsDefault":1,"Memo":null},{"GUID":"afb4533680294271acf3797b7b662b6a","ID":2,"MemberID":10,"RecName":"小马刺","PhoneNum":"13512894556","Province":"青海","City":"海北藏族自治州","County":"海晏县","AddressDetail":"海晏县老中区天华二街3号天天花园13栋1201","IsDefault":0,"Memo":null},{"GUID":"b247127761eb46f088753c33e11185f0","ID":3,"MemberID":10,"RecName":"张三","PhoneNum":"15956452210","Province":"四川","City":"成都市","County":"青羊区","AddressDetail":"青羊区小南街23号富丽花园12栋1109","IsDefault":0,"Memo":null}]
     * dataStatus : 1
     * describe : null
     */

    private int page;
    private int pageSize;
    private int sumSize;
    private int count;
    private int dataStatus;
    private Object describe;
    private List<ObjBean> obj;

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

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * GUID : 4c9f35e1be584ace9d79e59caddeb873
         * ID : 1
         * MemberID : 10
         * RecName : 张三
         * PhoneNum : 15623124523
         * Province : 青海
         * City : 西宁市
         * County : 城西区
         * AddressDetail : 张三三
         * IsDefault : 1
         * Memo : null
         */
        private boolean isSelected;
        private String name;
        private String GUID;
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

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


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

        public int getMemberID() {
            return MemberID;
        }

        public void setMemberID(int MemberID) {
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
