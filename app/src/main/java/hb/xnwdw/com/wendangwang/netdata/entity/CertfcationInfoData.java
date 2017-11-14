package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/5/17.
 */

public class CertfcationInfoData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"MemberID":10,"AuthStatus":-1,"RealName":"真实姓名","CardNum":"511521201704123849","CardPic1":"/UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg","CardPic2":"/UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg"}
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
         * MemberID : 10
         * AuthStatus : -1
         * RealName : 真实姓名
         * CardNum : 511521201704123849
         * CardPic1 : /UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg
         * CardPic2 : /UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg
         */

        private int MemberID;
        private int AuthStatus;
        private String RealName;
        private String CardNum;
        private String CardPic1;
        private String CardPic2;

        public int getMemberID() {
            return MemberID;
        }

        public void setMemberID(int MemberID) {
            this.MemberID = MemberID;
        }

        public int getAuthStatus() {
            return AuthStatus;
        }

        public void setAuthStatus(int AuthStatus) {
            this.AuthStatus = AuthStatus;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public String getCardNum() {
            return CardNum;
        }

        public void setCardNum(String CardNum) {
            this.CardNum = CardNum;
        }

        public String getCardPic1() {
            return CardPic1;
        }

        public void setCardPic1(String CardPic1) {
            this.CardPic1 = CardPic1;
        }

        public String getCardPic2() {
            return CardPic2;
        }

        public void setCardPic2(String CardPic2) {
            this.CardPic2 = CardPic2;
        }
    }
}
