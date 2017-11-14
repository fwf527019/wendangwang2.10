package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class OrderStoreData {

    private List<DatasBean> datas;

    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * GUID : c5deedc46ce1451bb1215432d99ec9dd
         * ID : 14
         * StoreName : 朝阳店
         * PhoneNumber : 4000971309
         * Linkman : 店长
         * StoreAddress : 西宁市城北区朝阳西路52号3号楼一层商铺
         * StorePic : /UploadImage/StorePic/201707/9777e696-e478-4d3c-b807-d55909423f97.png
         * MapX : 101.770696
         * MapY : 36.674045
         * StoreStatus : 1
         * IsDelete : 0
         * Memo : null
         * depotId : 1003
         * ActNum : 0
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
        private Object Memo;
        private String depotId;
        private int ActNum;
        private Object Activity;

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

        public Object getMemo() {
            return Memo;
        }

        public void setMemo(Object Memo) {
            this.Memo = Memo;
        }

        public String getDepotId() {
            return depotId;
        }

        public void setDepotId(String depotId) {
            this.depotId = depotId;
        }

        public int getActNum() {
            return ActNum;
        }

        public void setActNum(int ActNum) {
            this.ActNum = ActNum;
        }

        public Object getActivity() {
            return Activity;
        }

        public void setActivity(Object Activity) {
            this.Activity = Activity;
        }
    }
}
