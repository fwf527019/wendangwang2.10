package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */


public class NearShopData  {


    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : [{"GUID":"f559aae0f4f649ad96270d260a0c576e","ID":4,"StoreName":"稳当第一家","PhoneNumber":"13745698546","Linkman":"张三","StoreAddress":"成都市高新区","StorePic":"/UploadImage/StorePic/201705/7e2f3ac9-5a1b-49a8-9d82-7ab547e6f26a.jpg","MapX":104.073588,"MapY":30.600153,"StoreStatus":1,"IsDelete":0,"Memo":"104.073588 , 30.600153","Activity":null},{"GUID":"b449ec727b5f41baa07f6245dbe16f4b","ID":8,"StoreName":"稳当生活第二家","PhoneNumber":"15612452356","Linkman":"张四","StoreAddress":"电力小区旁边","StorePic":"/UploadImage/StorePic/201705/08803caa-e048-4c3f-a10d-c41889b3e3d0.jpg","MapX":101.756534,"MapY":36.641702,"StoreStatus":1,"IsDelete":0,"Memo":"101.756534,36.641702","Activity":null},{"GUID":"83f98b185c474887ae98e9df9fe0ac73","ID":9,"StoreName":"稳当生活第三家","PhoneNumber":"18956451223","Linkman":"李四","StoreAddress":"国电公司一楼","StorePic":"/UploadImage/StorePic/201705/535aee23-29bf-4639-a201-2ee86aedb66f.jpg","MapX":104.073264,"MapY":30.600965,"StoreStatus":1,"IsDelete":0,"Memo":"104.073264,30.600965","Activity":null}]
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
         * GUID : f559aae0f4f649ad96270d260a0c576e
         * ID : 4
         * StoreName : 稳当第一家
         * PhoneNumber : 13745698546
         * Linkman : 张三
         * StoreAddress : 成都市高新区
         * StorePic : /UploadImage/StorePic/201705/7e2f3ac9-5a1b-49a8-9d82-7ab547e6f26a.jpg
         * MapX : 104.073588
         * MapY : 30.600153
         * StoreStatus : 1
         * IsDelete : 0
         * Memo : 104.073588 , 30.600153
         * Activity : null
         */

        private String GUID;
        private int ID;
        private String StoreName;
        private String PhoneNumber;
        private String Linkman;
        private String StoreAddress;
        private String StorePic;
        private double MapX;
        private double MapY;
        private int StoreStatus;
        private int IsDelete;
        private String Memo;
        private String Activity;
        private boolean isSlecte;
        private int ActNum;

        public int getActNum() {
            return ActNum;
        }

        public void setActNum(int actNum) {
            ActNum = actNum;
        }

        public boolean isSlecte() {
            return isSlecte;
        }

        public void setSlecte(boolean slecte) {
            isSlecte = slecte;
        }

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

        public String getStoreName() {
            return StoreName;
        }

        public void setStoreName(String StoreName) {
            this.StoreName = StoreName;
        }

        public String getPhoneNumber() {
            return PhoneNumber;
        }

        public void setPhoneNumber(String PhoneNumber) {
            this.PhoneNumber = PhoneNumber;
        }

        public String getLinkman() {
            return Linkman;
        }

        public void setLinkman(String Linkman) {
            this.Linkman = Linkman;
        }

        public String getStoreAddress() {
            return StoreAddress;
        }

        public void setStoreAddress(String StoreAddress) {
            this.StoreAddress = StoreAddress;
        }

        public String getStorePic() {
            return StorePic;
        }

        public void setStorePic(String StorePic) {
            this.StorePic = StorePic;
        }

        public double getMapX() {
            return MapX;
        }

        public void setMapX(double MapX) {
            this.MapX = MapX;
        }

        public double getMapY() {
            return MapY;
        }

        public void setMapY(double MapY) {
            this.MapY = MapY;
        }

        public int getStoreStatus() {
            return StoreStatus;
        }

        public void setStoreStatus(int StoreStatus) {
            this.StoreStatus = StoreStatus;
        }

        public int getIsDelete() {
            return IsDelete;
        }

        public void setIsDelete(int IsDelete) {
            this.IsDelete = IsDelete;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getActivity() {
            return Activity;
        }

        public void setActivity(String Activity) {
            this.Activity = Activity;
        }
    }
}
