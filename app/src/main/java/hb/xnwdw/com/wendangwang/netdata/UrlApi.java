package hb.xnwdw.com.wendangwang.netdata;

/**
 * Created by Administrator on 2017/2/22.
 */

/**
 * URL管理
 */
public class UrlApi {
    //  public static String SERVER_IP = "http://wdw.cdhb.net";
    // public static String SERVER_IP = "http://wdw.cdhb.net";
    // public static String SERVER_IP = "http://192.168.1.137:8001";
    public static String SERVER_IP = "http://192.168.1.222:8002";
    // public static String SERVER_IP = "http://www.qhwendang.com";
    //正式地址
   // public static String SERVER_IP = "https://www.qhwendang.com";

    // public static String SERVER_IP = "http://125.72.17.131";
    public static String getServerIp() {
        return SERVER_IP;
    }

    public static void setServerIp(String serverIp) {
        SERVER_IP = serverIp;
    }

    public static String URL_MAINSLID = SERVER_IP + "/api/IndexDataService/GetIndexAdvert";//广告
    public static String URL_MAINMIAOSHA = SERVER_IP + "/api/IndexDataService/GetSekillInfo";//秒杀
    public static String URL_NEWARRIVALS = SERVER_IP + "/api/IndexDataService/GetNewArrivalsItems";//新品推荐
    public static String URL_RCOMMENDEYOU = SERVER_IP + "/api/IndexDataService/GetRecommendationItem";//为你推荐
    public static String UrL_USER_LOIN = SERVER_IP + "/api/Basic_Member/LoinUser";//密码登录
    public static String URL_USER_REGISTER = SERVER_IP + "/api/Basic_Member/RegUser";//
    public static String URL_ISEXITPHONE = SERVER_IP + "/api/Basic_Member/GetIsReg";//判断手机是否存在
    public static String URL_GETPHONECODE = SERVER_IP + "/api/Basic_Member/GetTelVerCode";//获取验证码
    public static String URL_LOGINBYPHONR = SERVER_IP + "/api/Basic_Member/LoginByPhone";//手机登录
    public static String URL_RESETPWD = SERVER_IP + "/api/Basic_Member/ResetMemberPwd";//重置密码
    public static String URL_FLOORINFO = SERVER_IP + "/api/IndexDataService/GetFloorInfo";//楼层信息
    public static String URL_USERCENTERINFO = SERVER_IP + "/api/Basic_Member/GetUserCenterInfo";//用户中心数据
    public static String URL_GETMYADRASS = SERVER_IP + "/api/Basic_Member/GetMyRecAddress";//获取地址
    public static String URL_ADDADRASS = SERVER_IP + "/api/Basic_Member/AddMyRecAddress";//添加地址
    public static String URL_DELETEADRASS = SERVER_IP + "/api/Basic_Member/DelAddressById";//删除地址
    public static String URL_MYCOLECTION = SERVER_IP + "/api/Basic_Member/MyCollection";//我的收藏
    public static String URL_DELETECOLECTION = SERVER_IP + "/api/Basic_Member/DelMyCollectionById";//删除收藏
    public static String URL_BRanchDelFootprint = SERVER_IP + "/api/Footprint/BatchDelFootprint";//批量删除足迹
    public static String URL_DelFootprint = SERVER_IP + "/api/Footprint/DelFootprint";//删除足迹
    public static String URL_GETMYCOUPONINFO = SERVER_IP + "/api/Basic_Member/GetMyCoupon";//我的优惠券
    public static String URL_GETODERLIST = SERVER_IP + "/api/OrderServiceInterface/GetOrderListByState";//获取订单列表
    public static String URL_GETODERDETAL = SERVER_IP + "/api/OrderServiceInterface/GetOrderDeltailsInfo";//获取订单详情
    public static String URL_GETQIANDAOINFO = SERVER_IP + "/api/Basic_Sign/GetSignDescribe";//获取会员当日签到奖励描述
    public static String URL_GETTODAYQIANDAOINFO = SERVER_IP + "/api/Basic_Sign/QueryTodayPrize";//获取当天签到的奖品信息
    public static String URL_POSTQIANDAOINFO = SERVER_IP + "/api/Basic_Sign/MemberSign";//会员签到提交数据
    public static String URL_ReceiveSignItem = SERVER_IP + "/api/Basic_Sign/ReceiveSignItem";//会员领取签到实物奖励
    public static String URL_GETBRANDRECOMENDEINFO = SERVER_IP + "/api/Basic_ItemBrand/GetBrandArrivals";//获取品牌推荐
    public static String URL_GETALLBRANDINFO = SERVER_IP + "/api/Basic_ItemBrand/GetItemBrand";//获取所有品牌
    public static String URL_GETBRANDDETAIL = SERVER_IP + "/api/Basic_ItemBrand/GetItemByBrand";//获取品牌详情
    public static String URL_GETBRANDFLOOR = SERVER_IP + "/api/Basic_ItemBrand/GetBrandArrivals";//获取品牌楼层信息
    public static String URL_GETHOTRECOMNED = SERVER_IP + "/api/Basic_ItemBrand/GetHotItemByBrand";//获取品牌热门推荐
    public static String URL_GETNEARESHOP = SERVER_IP + "/api/Basic_Store/GetStoreList";//获取附近门店
    public static String URL_GETNEARESHOPBYPAGE = SERVER_IP + "/api/Basic_Store/GetPagedStoreList";//获取分页附近门店
    public static String URL_GETSHOPACTIVITY = SERVER_IP + "/api/Basic_Store/GetPagedStoreList";//门店活动
    public static String URL_GETNEARSHOPBYXY = SERVER_IP + "/api/Basic_Store/GetNearbyStoreByPos";//根据经纬度获取附近门店列表
    public static String URL_GETSHOPACTIVITYLIST = SERVER_IP + "/api/Basic_Store/GetActivityByStore";//根据门店Id获取活动
    public static String URL_GETNEARSHOPBYLATLNTBYPOS = SERVER_IP + "/api/Basic_Store/GetPagedNearbyStore";//分页获取门店信息（按当前位置从近到远排序）get
    public static String URL_GETNEARSHOPBYLATLNT = SERVER_IP + "/api/Basic_Store/NearbyStoreByPos";//获取门店信息（按当前位置从近到远排序） post
    public static String URL_GETMENBERCERTEFCATION = SERVER_IP + "/api/Basic_MemberInfo/GetMemberRealAuth";//获取会员实名信息
    public static String URL_GETGOODSDETAIL = SERVER_IP + "/api/Basic_ItemDetail/GetItemDetail";//获取商品详情
    public static String URL_GETHOTSALE = SERVER_IP + "/api/Basic_ItemDetail/GetHotSaleItem";//获取商热卖列表
    public static String URL_GETSHERACHINFO = SERVER_IP + "/api/Basic_Item/SearchItemByKeyword";//根据关键字搜索商品
    public static String URL_GETBRANDMAX = SERVER_IP + "/api/Basic_ItemDetail/GetSalesMaxBrand";//获取销量最高的品牌信息
    public static String URL_GETEVALUATE = SERVER_IP + "/api/Basic_ItemDetail/GetCommentInfoByItem";//获取商品评价信息
    public static String URL_GETEVALUATELIST = SERVER_IP + "/api/Basic_ItemDetail/GetPagedItemComment";//分页获取商品评价列表信息
    public static String URL_GETSHOPINGCARLIST = SERVER_IP + "/api/ShoppingCartService/GetShoppingCartItem";//获取购物车列表
    public static String URL_ADDSGOPINGCAR = SERVER_IP + "/api/ShoppingCartService/AddShoppingCart";//加入购物车
    public static String URL_GETCATEONELIST = SERVER_IP + "/api/Basic_Item/GetItemCateOneList";//获取分类一级类目
    public static String URL_GETCATETWOLIST = SERVER_IP + "/api/Basic_Item/GetItemCateTwoByOne";//获取分类二级类目
    public static String URL_GETFOOTHOSTORY = SERVER_IP + "/api/Footprint/GetFootprint";//获取足迹
    public static String URL_GETFOOTNEARE = SERVER_IP + "/api/Footprint/GetRecentFootprint";//获取最近足迹
    public static String URL_SHOPINGCARSELEMENT = SERVER_IP + "/api/OrderServiceInterface/ShoppingCartSettlement";//购物车结算
    public static String URL_GTEORDERLISTINFO = SERVER_IP + "/api/OrderServiceInterface/OrderInfoByOrderNum";//根据订单编码返回订单结算清单信息post
    public static String URL_ORDERCOMMIT = SERVER_IP + "/api/OrderServiceInterface/OrderSettleAccount";//订单结算
    public static String URL_QUERYINTEGER = SERVER_IP + "/api/Basic_Member/QueryMemberIntegral";//积分明细
    public static String URL_QUERYNUM = SERVER_IP + "/api/Basic_Member/QueryMemberTotalIntegral";//剩余积分
    public static String URL_GETMENBERINFO = SERVER_IP + "/api/Basic_MemberInfo/GetMemberInfo";//获取会员信息
    public static String URL_PUTMENBERINFO = SERVER_IP + "/api/Basic_MemberInfo/AppSaveMemberInfo";//修改会员信息
    public static String URL_GETALIPAYDATA = SERVER_IP + "/api/OrderServiceInterface/getSearchOrderInfo";//支付宝数据
    public static String URL_UDPAYDATA = SERVER_IP + "/api/OrderServiceInterface/OrderAPPPayByUnPay";//银联数据
    public static String URL_TESTQQ = SERVER_IP + "/api/Basic_Member/QQAuthorized";//QQ登录验证
    public static String URL_TESTWXCHAT = SERVER_IP + "/api/Basic_Member/WXAuthorized";//微信登录验证
    public static String URL_TESTWEIBO = SERVER_IP + "/api/Basic_Member/WeiboAuthorized";//微博登录验证
    public static String URL_BINGANDLOGIG = SERVER_IP + "/api/Basic_Member/ThirdLogin";//第三方绑定并登陆
    public static String URL_REJESTANDBINDINGANDLOGIG = SERVER_IP + "/api/Basic_Member/ThirdRegLogin";//第三方注册绑定并登陆
    public static String URL_SENDCODETOPHONE = SERVER_IP + "/api/Basic_Member/SendCodeByPhone";//向手机发送验证码
    public static String URL_ADDTOCOLECT = SERVER_IP + "/api/Bus_MyCollects/UserAddCollects";//添加收藏
    public static String URL_CANCELORDER = SERVER_IP + "/api/OrderServiceInterface/CancelOrder";//取消订单
    public static String URL_DELETEORDER = SERVER_IP + "/api/OrderServiceInterface/OrderDelete";//删除订单
    public static String URL_SURETAKEOVER = SERVER_IP + "/api/OrderServiceInterface/Confirm";//确认收货
    public static String URL_SearchItem = SERVER_IP + "/api/Basic_Item/SearchItemEx";//根据条件搜索商品
    public static String URL_POSTRealAuth = SERVER_IP + "/api/Basic_MemberInfo/AppSubmitRealAuth";//提交会员实名信息
    public static String URL_GETMASSEGENUM = SERVER_IP + "/api/Basic_Member/GetNoReaMsgCount";//未读消息的数量
    public static String URL_ORDERMONY = SERVER_IP + "/api/OrderServiceInterface/ReceiveOrderInfo";//根据订单编码返回支付金额
    public static String URL_GetOfflineQRCodeItem = SERVER_IP + "/api/Basic_Item/GetOfflineQRCodeItem";//线下扫码购商品
    public static String URL_GetOfflineItemInfo = SERVER_IP + "/api/OrderServiceInterface/GetOfflineItemInfo";//获取线下扫码购商品详情
    public static String URL_GetValidCoupon = SERVER_IP + "/api/OrderServiceInterface/GetValidCoupon";//获取线下优惠券
    public static String URL_GainUseMyCoupon = SERVER_IP + "/api/OrderServiceInterface//GainUseMyCoupon";//获取线下优惠券
    public static String URL_OffLineOrderCrate = SERVER_IP + "/api/OrderServiceInterface/OffLineOrderCrate";//线下扫码购立即提交生成订单
    public static String URL_WXLOGIN = SERVER_IP + "/api/Basic_Member/WXAuthorized";//微信登录
    public static String URL_GETMESSAGE = SERVER_IP + "/api/Basic_Member/QueryMemberMsg";//消息
    public static String URL_GETIsOpen = SERVER_IP + "/api/LuckDrowService/IsOpen";//是否开放抽奖
    public static String URL_GETCanDraw = SERVER_IP + "/api/LuckDrowService/CanBeDraw";//查看订单是否可以参与抽奖
    public static String URL_GETDraws = SERVER_IP + "/api/LuckDrowService/GetPrize";//获取所有等级对应的奖品
    public static String URL_GetLuckGrade = SERVER_IP + "/api/LuckDrowService/GetLuckGrade";//获取中奖的等级
    public static String URL_GetNavData = SERVER_IP + "/api/IndexDataService/GetNavData";//获取动态图标
    public static String URL_GetNoticeList = SERVER_IP + "/api/Basic_Help/GetNoticeList";//获取首页公告列表
    public static String URL_OrderPayDetection = SERVER_IP + "/api/OrderServiceInterface/OrderPayDetection";//订单支付前检测该条订单数据里各条数据是否满足条件，如积分是否足够，商品是否已经下架
    public static String URL_GETBRANDBYCATATWO = SERVER_IP + "/api/Basic_ItemBrand/GetBrandByCateTwo"; //由第二类目获取品牌列表
    public static String URL_GTEBRANDBYKYWORD = SERVER_IP + "/api/Basic_ItemBrand/GetItemBrand";//由keyworld获取品牌列表
    public static String URL_GetMemberInfoEx = SERVER_IP + "/api/Basic_MemberInfo/GetMemberInfoEx";//获取会员的全部信息
    public static String URL_GetMemberRealAuth = SERVER_IP + "/api/Basic_MemberInfo/GetMemberRealAuth";//获取会员实名认证
    public static String URL_GetSeasheText = SERVER_IP + "/api/Basic_Item/GetSearchKeyWord?dataSource=移动端";//获取首页搜索默认字符
    public static String URL_BUYAGAIN = SERVER_IP + "/api/OrderServiceInterface/AddShoppingCart";//加入购物车 再次购买
    public static String URL_GETCOPUOND = SERVER_IP + "/api/Basic_Member/ActivateCoupon";//激活我的优惠券
    public static String URL_GetNoEvaluatOrder = SERVER_IP + "/api/EvaluateService/GetNoEvaluatOrder";//查询未评价订单
    public static String URL_Evaluate = SERVER_IP + "/api/EvaluateService/Evaluate";//提交评价数据
    public static String URL_ApplySaleAfter = SERVER_IP + "/api/SaleAfterService/ApplySaleAfter";//申请售后
    public static String URL_GetMaxApplySaleAfterNum = SERVER_IP + "/api/SaleAfterService/GetMaxApplySaleAfterNum";//获取最大申请数量
    public static String URL_GetItemInfoByItemID = SERVER_IP + "/api/Basic_Item/GetItemInfoByItemID";//获取Item名称
    public static String URL_ApplyRefund = SERVER_IP + "/Api/SaleAfterService/ApplyRefund";//申请退款
    public static String URL_GetMemberLockedIntegral = SERVER_IP + "/api/Basic_MemberInfo/GetMemberLockedIntegral";//获取会员锁定的积分
    public static String URL_CheckMemberLogin = SERVER_IP + "/api/Basic_Member/CheckMemberLogin";//判断是否登录
    public static String URL_GETQIAODAORULE = SERVER_IP + "/api/Basic_Help/GetEspecialNotice?noticeType=签到说明";//签到规则
    public static String URL_GetOrderForSaleAfter = SERVER_IP + "/api/SaleAfterService/GetOrderForSaleAfter";//可申请售后的定单
    public static String URL_GetSaleAfterRecords = SERVER_IP + "/api/SaleAfterService/GetSaleAfterRecords";//售后的记录
    public static String URL_CancelApply = SERVER_IP + "/api/SaleAfterService/CancelApply";//取消售后
    public static String URL_GetSaleStateNum = SERVER_IP + "/api/SaleAfterService/GetStateNum";//售后数量
    public static String URL_GetDetails = SERVER_IP + "/api/SaleAfterService/GetDetails";//售后详情
    public static String URL_UpLoadLogisticsPic = SERVER_IP + "/api/SaleAfterService/UpLoadLogisticsPic";//上传物流照片
    public static String URL_CheckCollects = SERVER_IP + "/api/Bus_MyCollects/CheckCollects";//商品是否收藏
    public static String URL_GetShoppingCartItem = SERVER_IP + "/api/ShoppingCartService/GetShoppingCartItem";//购物车商品数量
    public static String URL_OrderInfoByShapping = SERVER_IP + "/api/OrderServiceInterface/OrderInfoByShapping";//购物车到订单页面加载数据
    public static String URL_GetIndexFloorCfg = SERVER_IP + "/api/Config/GetIndexFloorCfg";//获取商品详情页面是否显示品牌
    public static String URL_GetStoreListByKeyword = SERVER_IP + "/api/Basic_Store/GetStoreListByKeyword";//获取门店信息By关键字
    public static String URL_GetAppVersion = SERVER_IP + "/api/AppVersion/CheckVersion";//app版本信息
    public static String URL_GetiCanBuyCount = SERVER_IP + "/api/ShoppingCartService/GetiCanBuyCount";//限购数量
    public static String URL_SendRebindCode = SERVER_IP + "/api/Basic_Member/SendRebindCode";//换手机号码发送验证码
    public static String URL_RebindPhone = SERVER_IP + "/api/Basic_Member/RebindPhone";//更换手机号码绑定
    public static String URL_SetMsgRead = SERVER_IP + "/api/Basic_Member/SetMsgRead";//消息设置已读
    public static String URL_GetMyAddressd = SERVER_IP + "/api/Basic_Member/GetMyAddress";//根据收货地址编号得到详细信息
    public static String URL_DelMyCollectionByIds = SERVER_IP + "/api/Basic_Member/DelMyCollectionByIds";//批量删除收藏
    public static String URL_UpdateLoseOrderByShoppCar = SERVER_IP + "/api/OrderServiceInterface/UpdateLoseOrderByShoppCar";//不能支付整单加入购物车
    public static String URL_RemoveShoppingCartItem = SERVER_IP + "/api/ShoppingCartService/RemoveShoppingCartItem";//移除购物车
    public static String URL_GetFullReducMoney = SERVER_IP + "/api/ShoppingCartService/GetFullReduceMoney";//满减
    public static String URL_GetFullPresentInfo = SERVER_IP + "/api/shoppingCartService/GetFullPresentInfo";//满赠
    public static String URL_GetBuyPresentInfo = SERVER_IP + "/api/shoppingCartService/GetBuyPresentInfo";//买赠
    public static String URL_CHEAKISLOGIN = SERVER_IP + "/api/Basic_Member/CheckMemberLogin";//验证是否登录


//  获取满减金额	/api/ShoppingCartService/GetFullReducMoney	get	sSource sActivityNum活动编号iMoney购买金额 sMemberID加密收的会员ID	ActivityNum活动编号Money要减少的金额ItemID赠送商品ID  Num数量
//  获取满赠信息	/api/shoppingCartService/GetFullPresentInfo	get	sSource sActivityNum活动编号iMoney购买金额 sMemberID加密收的会员ID	ActivityNum活动编号Money要减少的金额ItemID赠送商品ID  Num数量
//  获取买赠活动的赠品信息	/api/shoppingCartService/GetBuyPresentInfo	get	sSource sActivityNum活动编号iMoney购买金额 sMemberID加密收的会员ID	ActivityNum活动编号Money要减少的金额ItemID赠送商品ID  Num数量

}
