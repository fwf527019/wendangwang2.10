package hb.xnwdw.com.wendangwang.netdata.entity;

/**
 * Created by Administrator on 2017/7/13.
 */

public class BindingInfoData {

    /**
     * page : 0
     * pageSize : 0
     * sumSize : 0
     * count : 0
     * obj : {"GUID":"45c72ab761a44993ac55c1704eed0739","ID":2,"MemberID":10,"MemberPhone":"18980542859","HeadFace":"/UploadImage/MemberHead/201707/22861000-5724-4bfc-aa9d-767884fbd6d3.png","Sex":"男","Birthday":"1987-08-07 00:00:00","QQID":"4CA9C3C7D80A74E7C39CF384276ADFA5","WeChatID":"olNKrsxfe_ceOV8LetNN2mV5OIWk","MicroblogID":null,"PurchaseNum":20,"SurplusIntegral":16112,"AuthStatus":-1,"RealName":"李小三","CardNum":"511521201704123849","CardPic1":"/UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg","CardPic2":"/UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg","Memo1":null,"Memo2":null,"Memo3":null}
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
         * GUID : 45c72ab761a44993ac55c1704eed0739
         * ID : 2
         * MemberID : 10
         * MemberPhone : 18980542859
         * HeadFace : /UploadImage/MemberHead/201707/22861000-5724-4bfc-aa9d-767884fbd6d3.png
         * Sex : 男
         * Birthday : 1987-08-07 00:00:00
         * QQID : 4CA9C3C7D80A74E7C39CF384276ADFA5
         * WeChatID : olNKrsxfe_ceOV8LetNN2mV5OIWk
         * MicroblogID : null
         * PurchaseNum : 20
         * SurplusIntegral : 16112
         * AuthStatus : -1
         * RealName : 李小三
         * CardNum : 511521201704123849
         * CardPic1 : /UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg
         * CardPic2 : /UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg
         * Memo1 : null
         * Memo2 : null
         * Memo3 : null
         */

        private String GUID;
        private int ID;
        private int MemberID;
        private String MemberPhone;
        private String HeadFace;
        private String Sex;
        private String Birthday;
        private String QQID;
        private String WeChatID;
        private String MicroblogID;
        private int PurchaseNum;
        private int SurplusIntegral;
        private int AuthStatus;
        private String RealName;
        private String CardNum;
        private String CardPic1;
        private String CardPic2;
        private Object Memo1;
        private Object Memo2;
        private Object Memo3;

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

        public int getMemberID() {
            return MemberID;
        }

        public void setMemberID(int MemberID) {
            this.MemberID = MemberID;
        }

        public String getMemberPhone() {
            return MemberPhone;
        }

        public void setMemberPhone(String MemberPhone) {
            this.MemberPhone = MemberPhone;
        }

        public String getHeadFace() {
            return HeadFace;
        }

        public void setHeadFace(String HeadFace) {
            this.HeadFace = HeadFace;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public String getQQID() {
            return QQID;
        }

        public void setQQID(String QQID) {
            this.QQID = QQID;
        }

        public String getWeChatID() {
            return WeChatID;
        }

        public void setWeChatID(String WeChatID) {
            this.WeChatID = WeChatID;
        }

        public String getMicroblogID() {
            return MicroblogID;
        }

        public void setMicroblogID(String MicroblogID) {
            this.MicroblogID = MicroblogID;
        }

        public int getPurchaseNum() {
            return PurchaseNum;
        }

        public void setPurchaseNum(int PurchaseNum) {
            this.PurchaseNum = PurchaseNum;
        }

        public int getSurplusIntegral() {
            return SurplusIntegral;
        }

        public void setSurplusIntegral(int SurplusIntegral) {
            this.SurplusIntegral = SurplusIntegral;
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

        public Object getMemo1() {
            return Memo1;
        }

        public void setMemo1(Object Memo1) {
            this.Memo1 = Memo1;
        }

        public Object getMemo2() {
            return Memo2;
        }

        public void setMemo2(Object Memo2) {
            this.Memo2 = Memo2;
        }

        public Object getMemo3() {
            return Memo3;
        }

        public void setMemo3(Object Memo3) {
            this.Memo3 = Memo3;
        }
    }
}
