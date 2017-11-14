package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class GoodEvaluatData {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"count":5,"info":[{"CommentDesc":"不错呀","CommentNum":4,"CommentPro":80},{"CommentDesc":"待提高","CommentNum":1,"CommentPro":20}]}
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
         * count : 5
         * info : [{"CommentDesc":"不错呀","CommentNum":4,"CommentPro":80},{"CommentDesc":"待提高","CommentNum":1,"CommentPro":20}]
         */

        private int count;
        private List<InfoBean> info;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * CommentDesc : 不错呀
             * CommentNum : 4
             * CommentPro : 80
             */

            private String CommentDesc;
            private int CommentNum;
            private int CommentPro;

            public String getCommentDesc() {
                return CommentDesc;
            }

            public void setCommentDesc(String CommentDesc) {
                this.CommentDesc = CommentDesc;
            }

            public int getCommentNum() {
                return CommentNum;
            }

            public void setCommentNum(int CommentNum) {
                this.CommentNum = CommentNum;
            }

            public int getCommentPro() {
                return CommentPro;
            }

            public void setCommentPro(int CommentPro) {
                this.CommentPro = CommentPro;
            }
        }
    }
}
