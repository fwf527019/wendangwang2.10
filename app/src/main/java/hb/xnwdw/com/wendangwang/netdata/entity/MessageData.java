package hb.xnwdw.com.wendangwang.netdata.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/6/26.
 */
public class MessageData implements Serializable {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"noReadCount":0,"Count":0,"MsgList":[]}
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
         * noReadCount : 0
         * Count : 0
         * MsgList : []
         */

        private int noReadCount;
        private int Count;
        private List<ListBean> MsgList;

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

        public List<ListBean> getMsgList() {
            return MsgList;
        }

        public void setMsgList(List<ListBean> MsgList) {
            this.MsgList = MsgList;
        }

        public class ListBean implements Serializable{
            private  String MessContent;
            private  String MessDate;
            private  String MessType;
            private int  IsRead;//0未读1已读
            private String OperationType;
            private String Params;
            private String ID;

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public int getIsRead() {
                return IsRead;
            }

            public void setIsRead(int isRead) {
                IsRead = isRead;
            }

            public String getOperationType() {
                return OperationType;
            }

            public void setOperationType(String operationType) {
                OperationType = operationType;
            }

            public String getParams() {
                return Params;
            }

            public void setParams(String params) {
                Params = params;
            }




            public String getMessContent() {
                return MessContent;
            }

            public void setMessContent(String messContent) {
                MessContent = messContent;
            }

            public String getMessDate() {
                return MessDate;
            }

            public void setMessDate(String messDate) {
                MessDate = messDate;
            }

            public String getMessType() {
                return MessType;
            }

            public void setMessType(String messType) {
                MessType = messType;
            }
        }
    }


}
