package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class GoodsEvaluateListData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"8891B1641A9947DABC49374CA9020076","ID":5,"OrderId":12346,"ItemID":"25c454cd769841458815a7418a43b202","ItemName":"苹果","Satisfaction":5,"CommContent":"口感很好","UpPics":"/UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg","ReplyContent":null,"ReplyId":null,"DeleteLevel":1,"CommentTime":"2017-03-26 00:00:00","MemberName":"李老板","Memo":"不错呀","HeadFace":null,"MemberPhone":null},{"GUID":"6111D381695B4F708D189E9546B02630","ID":6,"OrderId":12346,"ItemID":"25c454cd769841458815a7418a43b202","ItemName":"苹果","Satisfaction":5,"CommContent":"口感很好","UpPics":"/UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg","ReplyContent":null,"ReplyId":null,"DeleteLevel":1,"CommentTime":"2017-03-26 00:00:00","MemberName":"李老板","Memo":"不错呀","HeadFace":null,"MemberPhone":null},{"GUID":"C0B8A5902DE24ED096167B84ABC2CCB7","ID":7,"OrderId":12346,"ItemID":"25c454cd769841458815a7418a43b202","ItemName":"苹果","Satisfaction":5,"CommContent":"口感很好","UpPics":"/UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg","ReplyContent":null,"ReplyId":null,"DeleteLevel":1,"CommentTime":"2017-03-26 00:00:00","MemberName":"李老板","Memo":"不错呀","HeadFace":null,"MemberPhone":null},{"GUID":"4AD909E2CA0B45649E6F7FB3D111D7A8","ID":4,"OrderId":12346,"ItemID":"25c454cd769841458815a7418a43b202","ItemName":"苹果","Satisfaction":4,"CommContent":"口感很好","UpPics":"/UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg","ReplyContent":null,"ReplyId":null,"DeleteLevel":1,"CommentTime":"2017-03-26 00:00:00","MemberName":"李老板","Memo":"不错呀","HeadFace":null,"MemberPhone":null},{"GUID":"0A81CF1EC15540C8AC4CCD4D7B1C3E60","ID":3,"OrderId":12346,"ItemID":"25c454cd769841458815a7418a43b202","ItemName":"苹果","Satisfaction":3,"CommContent":"口感很好","UpPics":"/UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg","ReplyContent":"123","ReplyId":2,"DeleteLevel":1,"CommentTime":"2017-03-26 00:00:00","MemberName":"李老板","Memo":"待提高","HeadFace":null,"MemberPhone":null}]
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
         * GUID : 8891B1641A9947DABC49374CA9020076
         * ID : 5
         * OrderId : 12346
         * ItemID : 25c454cd769841458815a7418a43b202
         * ItemName : 苹果
         * Satisfaction : 5
         * CommContent : 口感很好
         * UpPics : /UploadImage/069dde36-302f-432f-a6f4-7e4b709206be.jpg;/UploadImage/sp_pg_001.jpg
         * ReplyContent : null
         * ReplyId : null
         * DeleteLevel : 1
         * CommentTime : 2017-03-26 00:00:00
         * MemberName : 李老板
         * Memo : 不错呀
         * HeadFace : null
         * MemberPhone : null
         */

        private String GUID;
        private int ID;
        private int OrderId;
        private String ItemID;
        private String ItemName;
        private int Satisfaction;
        private String CommContent;
        private String UpPics;
        private Object ReplyContent;
        private Object ReplyId;
        private int DeleteLevel;
        private String CommentTime;
        private String MemberName;
        private String Memo;
        private String HeadFace;
        private String MemberPhone;

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

        public int getOrderId() {
            return OrderId;
        }

        public void setOrderId(int OrderId) {
            this.OrderId = OrderId;
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

        public int getSatisfaction() {
            return Satisfaction;
        }

        public void setSatisfaction(int Satisfaction) {
            this.Satisfaction = Satisfaction;
        }

        public String getCommContent() {
            return CommContent;
        }

        public void setCommContent(String CommContent) {
            this.CommContent = CommContent;
        }

        public String getUpPics() {
            return UpPics;
        }

        public void setUpPics(String UpPics) {
            this.UpPics = UpPics;
        }

        public Object getReplyContent() {
            return ReplyContent;
        }

        public void setReplyContent(Object ReplyContent) {
            this.ReplyContent = ReplyContent;
        }

        public Object getReplyId() {
            return ReplyId;
        }

        public void setReplyId(Object ReplyId) {
            this.ReplyId = ReplyId;
        }

        public int getDeleteLevel() {
            return DeleteLevel;
        }

        public void setDeleteLevel(int DeleteLevel) {
            this.DeleteLevel = DeleteLevel;
        }

        public String getCommentTime() {
            return CommentTime;
        }

        public void setCommentTime(String CommentTime) {
            this.CommentTime = CommentTime;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String MemberName) {
            this.MemberName = MemberName;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getHeadFace() {
            return HeadFace;
        }

        public void setHeadFace(String HeadFace) {
            this.HeadFace = HeadFace;
        }

        public String getMemberPhone() {
            return MemberPhone;
        }

        public void setMemberPhone(String MemberPhone) {
            this.MemberPhone = MemberPhone;
        }
    }
}
