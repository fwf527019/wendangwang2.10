package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class SecondClassfyData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"e701049714ae44beae572e7ccca65381","ID":6,"CateNum":"010001","CateGrade":"二级","ParentID":1,"CateName_V9":"牛肉","CateName":"肥牛肉","CateWeight":1,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/floor4_goods01.jpg","IconFlag":null,"cate":null,"cateList":null},{"GUID":"65af550d81b44e51a146f1fce452f948","ID":7,"CateNum":"010002","CateGrade":"二级","ParentID":1,"CateName_V9":"猪肉","CateName":"猪肉","CateWeight":4,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/floor4_goods01.jpg","IconFlag":null,"cate":null,"cateList":null}]
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
         * GUID : e701049714ae44beae572e7ccca65381
         * ID : 6
         * CateNum : 010001
         * CateGrade : 二级
         * ParentID : 1
         * CateName_V9 : 牛肉
         * CateName : 肥牛肉
         * CateWeight : 1
         * CateStatus : 1
         * Memo : null
         * CatePic : /UploadImage/floor4_goods01.jpg
         * IconFlag : null
         * cate : null
         * cateList : null
         */

        private String GUID;
        private int ID;
        private String CateNum;
        private String CateGrade;
        private int ParentID;
        private String CateName_V9;
        private String CateName;
        private int CateWeight;
        private int CateStatus;
        private Object Memo;
        private String CatePic;
        private Object IconFlag;
        private Object cate;
        private Object cateList;

        public String getGUID() {
            return GUID;
        }

        public void setGUID(String GUID) {
            this.GUID = GUID;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getCateNum() {
            return CateNum;
        }

        public void setCateNum(String CateNum) {
            this.CateNum = CateNum;
        }

        public String getCateGrade() {
            return CateGrade;
        }

        public void setCateGrade(String CateGrade) {
            this.CateGrade = CateGrade;
        }

        public int getParentID() {
            return ParentID;
        }

        public void setParentID(int ParentID) {
            this.ParentID = ParentID;
        }

        public String getCateName_V9() {
            return CateName_V9;
        }

        public void setCateName_V9(String CateName_V9) {
            this.CateName_V9 = CateName_V9;
        }

        public String getCateName() {
            return CateName;
        }

        public void setCateName(String CateName) {
            this.CateName = CateName;
        }

        public int getCateWeight() {
            return CateWeight;
        }

        public void setCateWeight(int CateWeight) {
            this.CateWeight = CateWeight;
        }

        public int getCateStatus() {
            return CateStatus;
        }

        public void setCateStatus(int CateStatus) {
            this.CateStatus = CateStatus;
        }

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public String getCatePic() {
            return CatePic;
        }

        public void setCatePic(String CatePic) {
            this.CatePic = CatePic;
        }

        public Object getIconFlag() {
            return IconFlag;
        }

        public void setIconFlag(Object IconFlag) {
            this.IconFlag = IconFlag;
        }

        public Object getCate() {
            return cate;
        }

        public void setCate(Object cate) {
            this.cate = cate;
        }

        public Object getCateList() {
            return cateList;
        }

        public void setCateList(Object cateList) {
            this.cateList = cateList;
        }
    }
}
