package hb.xnwdw.com.wendangwang.netdata.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */

public class UserCenterInforData {

    private List<DatasBean> datas;
    public List<DatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<DatasBean> datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * name : member
         * value : {"GUID":"14c226d45a9744dfb6f94f49e627a781","ID":10,"PhoneNum":"189****2859","MemberName":"小三子","Pwd":"6DB503B3B8561008","Salt":"J8V606","RegSource":"pc","RegTime":"2017-04-13 09:36:49","LoginTimes":0,"LastLoinDate":"2017-04-13 09:36:49","LastLoginIP":null,"AccountStatus":1,"IsDelete":1,"Memo":null,"AppMsgRecord":null,"MemberIntegral":null,"RecCoupon":null,"SignRecord":null,"PrizeRecord":null,"MemberInfo":{"GUID":"45c72ab761a44993ac55c1704eed0739","ID":2,"MemberID":10,"MemberPhone":"18980542859","HeadFace":"/UploadImage/MemberHead/029e33f3-825c-4948-8194-0dd82f15565a.jpg","Sex":"男","Birthday":"2001-08-07 00:00:00","QQID":null,"WeChatID":null,"MicroblogID":null,"PurchaseNum":0,"SurplusIntegral":359,"AuthStatus":-1,"RealName":"真实姓名","CardNum":"511521201704123849","CardPic1":"/UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg","CardPic2":"/UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg","Memo1":null,"Memo2":null,"Memo3":null},"RecAddress":null}
         */

        private String name;
        private ValueBean value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ValueBean getValue() {
            return value;
        }

        public void setValue(ValueBean value) {
            this.value = value;
        }

        public static class ValueBean {
            /**
             * GUID : 14c226d45a9744dfb6f94f49e627a781
             * ID : 10
             * PhoneNum : 189****2859
             * MemberName : 小三子
             * Pwd : 6DB503B3B8561008
             * Salt : J8V606
             * RegSource : pc
             * RegTime : 2017-04-13 09:36:49
             * LoginTimes : 0
             * LastLoinDate : 2017-04-13 09:36:49
             * LastLoginIP : null
             * AccountStatus : 1
             * IsDelete : 1
             * Memo : null
             * AppMsgRecord : null
             * MemberIntegral : null
             * RecCoupon : null
             * SignRecord : null
             * PrizeRecord : null
             * MemberInfo : {"GUID":"45c72ab761a44993ac55c1704eed0739","ID":2,"MemberID":10,"MemberPhone":"18980542859","HeadFace":"/UploadImage/MemberHead/029e33f3-825c-4948-8194-0dd82f15565a.jpg","Sex":"男","Birthday":"2001-08-07 00:00:00","QQID":null,"WeChatID":null,"MicroblogID":null,"PurchaseNum":0,"SurplusIntegral":359,"AuthStatus":-1,"RealName":"真实姓名","CardNum":"511521201704123849","CardPic1":"/UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg","CardPic2":"/UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg","Memo1":null,"Memo2":null,"Memo3":null}
             * RecAddress : null
             */

            private String GUID;
            private String ID;
            private String PhoneNum;
            private String MemberName;
            private String Pwd;
            private String Salt;
            private String RegSource;
            private String RegTime;
            private String LoginTimes;
            private String LastLoinDate;
            private Object LastLoginIP;
            private String AccountStatus;
            private String IsDelete;
            private Object Memo;
            private Object AppMsgRecord;
            private Object MemberIntegral;
            private Object RecCoupon;
            private Object SignRecord;
            private Object PrizeRecord;
            private MemberInfoBean MemberInfo;
            private Object RecAddress;

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

            public String getPhoneNum() {
                return PhoneNum;
            }

            public void setPhoneNum(String PhoneNum) {
                this.PhoneNum = PhoneNum;
            }

            public String getMemberName() {
                return MemberName;
            }

            public void setMemberName(String MemberName) {
                this.MemberName = MemberName;
            }

            public String getPwd() {
                return Pwd;
            }

            public void setPwd(String Pwd) {
                this.Pwd = Pwd;
            }

            public String getSalt() {
                return Salt;
            }

            public void setSalt(String Salt) {
                this.Salt = Salt;
            }

            public String getRegSource() {
                return RegSource;
            }

            public void setRegSource(String RegSource) {
                this.RegSource = RegSource;
            }

            public String getRegTime() {
                return RegTime;
            }

            public void setRegTime(String RegTime) {
                this.RegTime = RegTime;
            }

            public String getLoginTimes() {
                return LoginTimes;
            }

            public void setLoginTimes(String LoginTimes) {
                this.LoginTimes = LoginTimes;
            }

            public String getLastLoinDate() {
                return LastLoinDate;
            }

            public void setLastLoinDate(String LastLoinDate) {
                this.LastLoinDate = LastLoinDate;
            }

            public Object getLastLoginIP() {
                return LastLoginIP;
            }

            public void setLastLoginIP(Object LastLoginIP) {
                this.LastLoginIP = LastLoginIP;
            }

            public String getAccountStatus() {
                return AccountStatus;
            }

            public void setAccountStatus(String AccountStatus) {
                this.AccountStatus = AccountStatus;
            }

            public String getIsDelete() {
                return IsDelete;
            }

            public void setIsDelete(String IsDelete) {
                this.IsDelete = IsDelete;
            }

            public Object getMemo() {
                return Memo;
            }

            public void setMemo(Object Memo) {
                this.Memo = Memo;
            }

            public Object getAppMsgRecord() {
                return AppMsgRecord;
            }

            public void setAppMsgRecord(Object AppMsgRecord) {
                this.AppMsgRecord = AppMsgRecord;
            }

            public Object getMemberIntegral() {
                return MemberIntegral;
            }

            public void setMemberIntegral(Object MemberIntegral) {
                this.MemberIntegral = MemberIntegral;
            }

            public Object getRecCoupon() {
                return RecCoupon;
            }

            public void setRecCoupon(Object RecCoupon) {
                this.RecCoupon = RecCoupon;
            }

            public Object getSignRecord() {
                return SignRecord;
            }

            public void setSignRecord(Object SignRecord) {
                this.SignRecord = SignRecord;
            }

            public Object getPrizeRecord() {
                return PrizeRecord;
            }

            public void setPrizeRecord(Object PrizeRecord) {
                this.PrizeRecord = PrizeRecord;
            }

            public MemberInfoBean getMemberInfo() {
                return MemberInfo;
            }

            public void setMemberInfo(MemberInfoBean MemberInfo) {
                this.MemberInfo = MemberInfo;
            }

            public Object getRecAddress() {
                return RecAddress;
            }

            public void setRecAddress(Object RecAddress) {
                this.RecAddress = RecAddress;
            }

            public static class MemberInfoBean {
                /**
                 * GUID : 45c72ab761a44993ac55c1704eed0739
                 * ID : 2
                 * MemberID : 10
                 * MemberPhone : 18980542859
                 * HeadFace : /UploadImage/MemberHead/029e33f3-825c-4948-8194-0dd82f15565a.jpg
                 * Sex : 男
                 * Birthday : 2001-08-07 00:00:00
                 * QQID : null
                 * WeChatID : null
                 * MicroblogID : null
                 * PurchaseNum : 0
                 * SurplusIntegral : 359
                 * AuthStatus : -1
                 * RealName : 真实姓名
                 * CardNum : 511521201704123849
                 * CardPic1 : /UploadImage/MemberCard/d308628c-b894-43d1-a48c-03cae9a9ddc8.jpg
                 * CardPic2 : /UploadImage/MemberCard/7d087e6d-59fd-4914-b97d-6d60ffcf6ac1.jpg
                 * Memo1 : null
                 * Memo2 : null
                 * Memo3 : null
                 */

                private String GUID;
                private String ID;
                private String MemberID;
                private String MemberPhone;
                private String HeadFace;
                private String Sex;
                private String Birthday;
                private Object QQID;
                private Object WeChatID;
                private Object MicroblogID;
                private String PurchaseNum;
                private String SurplusIntegral;
                private String AuthStatus;
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

                public String getID() {
                    return ID;
                }

                public void setID(String ID) {
                    this.ID = ID;
                }

                public String getMemberID() {
                    return MemberID;
                }

                public void setMemberID(String MemberID) {
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

                public Object getQQID() {
                    return QQID;
                }

                public void setQQID(Object QQID) {
                    this.QQID = QQID;
                }

                public Object getWeChatID() {
                    return WeChatID;
                }

                public void setWeChatID(Object WeChatID) {
                    this.WeChatID = WeChatID;
                }

                public Object getMicroblogID() {
                    return MicroblogID;
                }

                public void setMicroblogID(Object MicroblogID) {
                    this.MicroblogID = MicroblogID;
                }

                public String getPurchaseNum() {
                    return PurchaseNum;
                }

                public void setPurchaseNum(String PurchaseNum) {
                    this.PurchaseNum = PurchaseNum;
                }

                public String getSurplusIntegral() {
                    return SurplusIntegral;
                }

                public void setSurplusIntegral(String SurplusIntegral) {
                    this.SurplusIntegral = SurplusIntegral;
                }

                public String getAuthStatus() {
                    return AuthStatus;
                }

                public void setAuthStatus(String AuthStatus) {
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
    }
}
