package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/18.
 */

public class MsgData implements Serializable{


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"noReadCount":3,"Count":4,"MsgList":[{"GUID":"d20fd88a613d4631b9ab7e704bc5a048","ID":6231,"MessType":"客户回复","MessContent":"喔喔哦<br /><span style='color:#3b72ca'>回复：爱九年级<\/span>","MessUrl":"","MessDate":"2017-12-18 15:25:14","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"35","UserPhone":null},{"GUID":"e6288243f7104c1086cf65986103ecda","ID":6230,"MessType":"客户回复","MessContent":"刚刚好<br /><span style='color:#3b72ca'>回复：啊<\/span>","MessUrl":"","MessDate":"2017-12-18 15:24:45","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"37","UserPhone":null},{"GUID":"ddf27074834043378e3d9c16256f4b93","ID":6228,"MessType":"客户回复","MessContent":"刚刚好<br /><span style='color:#3b72ca'>回复：好的，我们这些要开始处理了<\/span>","MessUrl":"","MessDate":"2017-12-18 14:59:56","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"38","UserPhone":null},{"GUID":"9704561a88e24694850032cdda1a6704","ID":6227,"MessType":"客户回复","MessContent":"莫非<br /><span style='color:#3b72ca'>回复：大大大<\/span>","MessUrl":"","MessDate":"2017-12-18 14:23:29","IsRead":1,"UserId":7687,"Memo":null,"OperationType":"4","Params":"30","UserPhone":null}]}
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

    public static class ObjBean implements Serializable{
        /**
         * noReadCount : 3
         * Count : 4
         * MsgList : [{"GUID":"d20fd88a613d4631b9ab7e704bc5a048","ID":6231,"MessType":"客户回复","MessContent":"喔喔哦<br /><span style='color:#3b72ca'>回复：爱九年级<\/span>","MessUrl":"","MessDate":"2017-12-18 15:25:14","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"35","UserPhone":null},{"GUID":"e6288243f7104c1086cf65986103ecda","ID":6230,"MessType":"客户回复","MessContent":"刚刚好<br /><span style='color:#3b72ca'>回复：啊<\/span>","MessUrl":"","MessDate":"2017-12-18 15:24:45","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"37","UserPhone":null},{"GUID":"ddf27074834043378e3d9c16256f4b93","ID":6228,"MessType":"客户回复","MessContent":"刚刚好<br /><span style='color:#3b72ca'>回复：好的，我们这些要开始处理了<\/span>","MessUrl":"","MessDate":"2017-12-18 14:59:56","IsRead":0,"UserId":7687,"Memo":null,"OperationType":"4","Params":"38","UserPhone":null},{"GUID":"9704561a88e24694850032cdda1a6704","ID":6227,"MessType":"客户回复","MessContent":"莫非<br /><span style='color:#3b72ca'>回复：大大大<\/span>","MessUrl":"","MessDate":"2017-12-18 14:23:29","IsRead":1,"UserId":7687,"Memo":null,"OperationType":"4","Params":"30","UserPhone":null}]
         */

        private int noReadCount;
        private int Count;
        private List<MsgListBean> MsgList;

        public int getNoReadCount() {
            return noReadCount;
        }

        public void setNoReadCount(int noReadCount) {
            this.noReadCount = noReadCount;
        }

        public int getCount() {
            return Count;
        }

        public void setCount(int Count) {
            this.Count = Count;
        }

        public List<MsgListBean> getMsgList() {
            return MsgList;
        }

        public void setMsgList(List<MsgListBean> MsgList) {
            this.MsgList = MsgList;
        }

        public static class MsgListBean implements Serializable{
            /**
             * GUID : d20fd88a613d4631b9ab7e704bc5a048
             * ID : 6231
             * MessType : 客户回复
             * MessContent : 喔喔哦<br /><span style='color:#3b72ca'>回复：爱九年级</span>
             * MessUrl :
             * MessDate : 2017-12-18 15:25:14
             * IsRead : 0
             * UserId : 7687
             * Memo : null
             * OperationType : 4
             * Params : 35
             * UserPhone : null
             */

            private String GUID;
            private String ID;
            private String MessType;
            private String MessContent;
            private String MessUrl;
            private String MessDate;
            private int IsRead;
            private int UserId;
            private Object Memo;
            private String OperationType;
            private String Params;
            private Object UserPhone;

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

            public String getMessType() {
                return MessType;
            }

            public void setMessType(String MessType) {
                this.MessType = MessType;
            }

            public String getMessContent() {
                return MessContent;
            }

            public void setMessContent(String MessContent) {
                this.MessContent = MessContent;
            }

            public String getMessUrl() {
                return MessUrl;
            }

            public void setMessUrl(String MessUrl) {
                this.MessUrl = MessUrl;
            }

            public String getMessDate() {
                return MessDate;
            }

            public void setMessDate(String MessDate) {
                this.MessDate = MessDate;
            }

            public int getIsRead() {
                return IsRead;
            }

            public void setIsRead(int IsRead) {
                this.IsRead = IsRead;
            }

            public int getUserId() {
                return UserId;
            }

            public void setUserId(int UserId) {
                this.UserId = UserId;
            }

            public Object getMemo() {
                return Memo;
            }

            public void setMemo(Object Memo) {
                this.Memo = Memo;
            }

            public String getOperationType() {
                return OperationType;
            }

            public void setOperationType(String OperationType) {
                this.OperationType = OperationType;
            }

            public String getParams() {
                return Params;
            }

            public void setParams(String Params) {
                this.Params = Params;
            }

            public Object getUserPhone() {
                return UserPhone;
            }

            public void setUserPhone(Object UserPhone) {
                this.UserPhone = UserPhone;
            }
        }
    }
}
