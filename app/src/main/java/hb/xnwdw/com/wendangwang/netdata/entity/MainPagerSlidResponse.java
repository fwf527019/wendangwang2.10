package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MainPagerSlidResponse {


    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * ContentUrl : www.baidu.com
         * AdvertPic : http://localhost:3000/UploadImage/b8cdff04-8214-499a-b2a3-01648282bb99.jpg
         */

        private String ContentUrl;
        private String AdvertPic;

        public String getContentUrl() {
            return ContentUrl;
        }

        public void setContentUrl(String ContentUrl) {
            this.ContentUrl = ContentUrl;
        }

        public String getAdvertPic() {
            return AdvertPic;
        }

        public void setAdvertPic(String AdvertPic) {
            this.AdvertPic = AdvertPic;
        }
    }
}
