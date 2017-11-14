package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/7/3.
 */

public class NavData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"Guid":"d35e2599cea54ae18da4159144ceb91a","ID":1,"IndexNo":1,"Text":"每日签到","Url":"sign.html","ImgUrl":"/UploadImage/indexNavImg/201706\\6dcab7bdbb864b6f9c8484834b4587e1.png","State":1,"ImgData":null},{"Guid":"f6ccc9817bfb4a138a6e3280f893ba81","ID":2,"IndexNo":2,"Text":"品牌推荐","Url":"recommend.html","ImgUrl":"/UploadImage/indexNavImg/201706\\be81fa39513c4cafbebf6dfb11186b44.png","State":1,"ImgData":null},{"Guid":"11ec23c5af834552b9173e807b2d6c6c","ID":4,"IndexNo":3,"Text":"资讯","Url":"news_list.html","ImgUrl":"/UploadImage/indexNavImg/201706\\013f424dd97a4c5e9d46c9f8fe9b29ac.png","State":1,"ImgData":null},{"Guid":"26f5813cc737417893a7c08f9edfc47f","ID":5,"IndexNo":4,"Text":"附近门店","Url":"nearby_store.html","ImgUrl":"/UploadImage/indexNavImg/201706\\90eca2ce494f45e8835cf658f705f595.png","State":1,"ImgData":null},{"Guid":"a446c95256714344a4e35abd62d0222a","ID":6,"IndexNo":5,"Text":"分类","Url":"list.html","ImgUrl":"/UploadImage/indexNavImg/201706\\a7b20e22b49c41589646f8d081db6d13.png","State":1,"ImgData":null}]
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
         * Guid : d35e2599cea54ae18da4159144ceb91a
         * ID : 1
         * IndexNo : 1
         * Text : 每日签到
         * Url : sign.html
         * ImgUrl : /UploadImage/indexNavImg/201706\6dcab7bdbb864b6f9c8484834b4587e1.png
         * State : 1
         * ImgData : null
         */

        private String Guid;
        private int ID;
        private int IndexNo;
        private String Text;
        private String Url;
        private String ImgUrl;
        private int State;
        private String ImgData;

        public String getGuid() {
            return Guid;
        }

        public void setGuid(String Guid) {
            this.Guid = Guid;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public int getIndexNo() {
            return IndexNo;
        }

        public void setIndexNo(int IndexNo) {
            this.IndexNo = IndexNo;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String ImgUrl) {
            this.ImgUrl = ImgUrl;
        }

        public int getState() {
            return State;
        }

        public void setState(int State) {
            this.State = State;
        }

        public String getImgData() {
            return ImgData;
        }

        public void setImgData(String ImgData) {
            this.ImgData = ImgData;
        }
    }
}
