package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/12/18.
 */

public class MsgDetileData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"SaveType":0,"ComplaintTypeString":"延迟发货","MessageID":6228,"SuggestID":38,"Describe":"刚刚好","UpPics":"/UploadImage/MemberCard/201712/compress71278e722f874439a9bf8e27059f828b.jpeg;/UploadImage/MemberCard/201712/compress2b47188adcbb4ab09f6019a5614d3e49.jpeg","MessContent":"刚刚好<br /><span style='color:#3b72ca'>回复：好的，我们这些要开始处理了<\/span>"}
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
         * SaveType : 0
         * ComplaintTypeString : 延迟发货
         * MessageID : 6228
         * SuggestID : 38
         * Describe : 刚刚好
         * UpPics : /UploadImage/MemberCard/201712/compress71278e722f874439a9bf8e27059f828b.jpeg;/UploadImage/MemberCard/201712/compress2b47188adcbb4ab09f6019a5614d3e49.jpeg
         * MessContent : 刚刚好<br /><span style='color:#3b72ca'>回复：好的，我们这些要开始处理了</span>
         */

        private int SaveType;
        private String ComplaintTypeString;
        private int MessageID;
        private int SuggestID;
        private String Describe;
        private String UpPics;
        private String MessContent;

        public int getSaveType() {
            return SaveType;
        }

        public void setSaveType(int SaveType) {
            this.SaveType = SaveType;
        }

        public String getComplaintTypeString() {
            return ComplaintTypeString;
        }

        public void setComplaintTypeString(String ComplaintTypeString) {
            this.ComplaintTypeString = ComplaintTypeString;
        }

        public int getMessageID() {
            return MessageID;
        }

        public void setMessageID(int MessageID) {
            this.MessageID = MessageID;
        }

        public int getSuggestID() {
            return SuggestID;
        }

        public void setSuggestID(int SuggestID) {
            this.SuggestID = SuggestID;
        }

        public String getDescribe() {
            return Describe;
        }

        public void setDescribe(String Describe) {
            this.Describe = Describe;
        }

        public String getUpPics() {
            return UpPics;
        }

        public void setUpPics(String UpPics) {
            this.UpPics = UpPics;
        }

        public String getMessContent() {
            return MessContent;
        }

        public void setMessContent(String MessContent) {
            this.MessContent = MessContent;
        }
    }
}
