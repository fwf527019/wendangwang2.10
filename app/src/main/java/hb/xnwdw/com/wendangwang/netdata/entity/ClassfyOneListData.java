package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/24.
 */

public class ClassfyOneListData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"b61b6d3620ae46c7880195c59d5c581d","ID":1,"CateNum":"010","CateGrade":"一级","ParentID":0,"CateName_V9":"精选肉品","CateName":"精选肉类","CateWeight":6,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/FirstCatePic/e410917fa35b4036815e3ffa0df598c3.jpg","IconFlag":"sp_fl01_icon.png","cate":null,"cateList":null},{"GUID":"3341a926af9945b6a7ad0856eab2bd46","ID":2,"CateNum":"020","CateGrade":"一级","ParentID":0,"CateName_V9":"进口水果","CateName":"新鲜水果","CateWeight":1,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/floor4_goods01.jpg","IconFlag":"sp_fl02_icon.png","cate":null,"cateList":null},{"GUID":"a9691513d6054123ab7108d4268a2a38","ID":3,"CateNum":"030","CateGrade":"一级","ParentID":0,"CateName_V9":"海鲜水产","CateName":"海鲜水产","CateWeight":1,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/sp_pg_001.jpg","IconFlag":"sp_fl03_icon.png","cate":null,"cateList":null},{"GUID":"b3ba7621730444879e715e4a10bb22df","ID":4,"CateNum":"040","CateGrade":"一级","ParentID":0,"CateName_V9":"饮品食品","CateName":"饮品食品","CateWeight":1,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/sp_pg_001.jpg","IconFlag":"sp_fl04_icon.png","cate":null,"cateList":null},{"GUID":"445342a6289245538be2f2ad4ec61281","ID":5,"CateNum":"050","CateGrade":"一级","ParentID":0,"CateName_V9":"地方特产","CateName":"地方特产","CateWeight":1,"CateStatus":1,"Memo":null,"CatePic":"/UploadImage/FirstCatePic/6b2b07c4d71a45008d1b666c121bf39b.jpg","IconFlag":"sp_fl05_icon.png","cate":null,"cateList":null}]
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
         * GUID : b61b6d3620ae46c7880195c59d5c581d
         * ID : 1
         * CateNum : 010
         * CateGrade : 一级
         * ParentID : 0
         * CateName_V9 : 精选肉品
         * CateName : 精选肉类
         * CateWeight : 6
         * CateStatus : 1
         * Memo : null
         * CatePic : /UploadImage/FirstCatePic/e410917fa35b4036815e3ffa0df598c3.jpg
         * IconFlag : sp_fl01_icon.png
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
        private String IconFlag;
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

        public String getIconFlag() {
            return IconFlag;
        }

        public void setIconFlag(String IconFlag) {
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
