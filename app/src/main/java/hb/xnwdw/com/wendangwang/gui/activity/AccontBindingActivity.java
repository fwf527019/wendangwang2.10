package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.TagAliasCallback;
import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.WeiBo.WConstants;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.BindingInfoData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/11.
 */
public class AccontBindingActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.binding_phone)
    LinearLayout bindingPhone;
    @BindView(R.id.binding_qq)
    LinearLayout bindingQq;
    @BindView(R.id.binding_weichat)
    LinearLayout bindingWeichat;
    @BindView(R.id.binding_sina)
    LinearLayout bindingSina;
    @BindView(R.id.phone_isbinding)
    TextView phoneIsbinding;
    @BindView(R.id.phone_more)
    ImageView phoneMore;
    @BindView(R.id.qq_isbinding)
    TextView qqIsbinding;
    @BindView(R.id.qq_more)
    ImageView qqMore;
    @BindView(R.id.weichat_isbinding)
    TextView weichatIsbinding;
    @BindView(R.id.weichat_more)
    ImageView weichatMore;
    @BindView(R.id.weibo_isbinding)
    TextView weiboIsbinding;
    @BindView(R.id.weibo_more)
    ImageView weiboMore;
    private Tencent mTencent;
    private SsoHandler mSsoHandler;
    private Oauth2AccessToken mAccessToken;
    private String scope;
    @Override
    protected int getContentViewResId() {
        return R.layout.activity_accontbinding;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        mTencent = Tencent.createInstance("101423390", this.getApplicationContext());
        title.setText("账号绑定");
        WbSdk.install(AccontBindingActivity.this, new AuthInfo(getApplicationContext(), WConstants.APP_KEY, WConstants.REDIRECT_URL, WConstants.SCOPE));
        mSsoHandler = new SsoHandler(AccontBindingActivity.this);
        loadDats();
    }

    @OnClick({R.id.back, R.id.binding_phone, R.id.binding_qq, R.id.binding_weichat, R.id.binding_sina})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.binding_phone:
                Intent intent=new Intent(AccontBindingActivity.this,ExchangePhoneActivity.class);
                intent.putExtra("phone",data.getObj().getMemberPhone());
                startActivity(intent);
                break;
            case R.id.binding_qq:
                if (!qqIsbinding.getText().toString().equals("已绑定")) {
                    loginQQ();
                }
                break;
            case R.id.binding_weichat:
                if (!weichatIsbinding.getText().toString().equals("已绑定")) {
                    loginWXchat();
                }

                break;
            case R.id.binding_sina:
                if (!weiboIsbinding.getText().toString().equals("已绑定")) {
                   loginWeiB();
                }
                break;
        }
    }
    BindingInfoData data;
    private void loadDats() {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "APP");
        map.put("memberId", "0");

        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GetMemberInfoEx, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404)) {
                    Log.d("AccontBindingActivity", response);
                     data = JSON.parseObject(response, BindingInfoData.class);
                    if (data.getObj() != null) {
                        if (data.getObj().getMemberPhone() != null) {
                            phoneIsbinding.setText(data.getObj().getMemberPhone());
                            phoneIsbinding.setVisibility(View.VISIBLE);

                        } else {
                            phoneIsbinding.setVisibility(View.GONE);
                            phoneMore.setVisibility(View.VISIBLE);
                        }
                        if (data.getObj().getQQID() != null) {
                            qqIsbinding.setText("已绑定");
                            qqIsbinding.setVisibility(View.VISIBLE);
                            qqMore.setVisibility(View.GONE);
                        } else {
                            qqIsbinding.setVisibility(View.GONE);
                            qqMore.setVisibility(View.VISIBLE);
                        }
                        if (data.getObj().getWeChatID() != null) {
                            weichatIsbinding.setText("已绑定");
                            weichatIsbinding.setVisibility(View.VISIBLE);
                            weichatMore.setVisibility(View.GONE);
                        } else {
                            weichatIsbinding.setVisibility(View.GONE);
                            weichatMore.setVisibility(View.VISIBLE);
                        }
                        if (data.getObj().getMicroblogID() != null) {
                            weiboIsbinding.setText("已绑定");
                            weiboIsbinding.setVisibility(View.VISIBLE);
                            weiboMore.setVisibility(View.GONE);
                        } else {
                            weiboIsbinding.setVisibility(View.GONE);
                            weiboMore.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        });
    }





    @Override
    protected void onResume() {
        super.onResume();
        loadDats();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }








    private String ID;

    /**
     * 第三方登录的回调都在OnActivityResult 这里
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }

        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                //            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                //QQ回调信息
                Tencent.handleResultData(data, new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        LogUtils.d("AccontBindingActivity", o.toString());
                        JSONObject jsonObject = JSON.parseObject(o.toString());
//                        ID = jsonObject.get("openid").toString();
                        TestQQ(jsonObject.get("access_token").toString());

                    }

                    @Override
                    public void onError(UiError uiError) {
                        LogUtils.d("AccontBindingActivity", "uiError:" + uiError);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        }

    }

    /**
     * 登录QQ
     */
    public void loginQQ() {
        if (!mTencent.isSessionValid()) {
            mTencent.login(this, scope, new IUiListener() {
                @Override
                public void onComplete(Object o) {
                }

                @Override
                public void onError(UiError uiError) {
                }

                @Override
                public void onCancel() {
                }
            });
        }
    }

    /**
     * 微信登录
     */
    private IWXAPI mApi;

    private void loginWXchat() {

        //api注册
        mApi = WXAPIFactory.createWXAPI(this, WXConstants.APP_ID, true);
        mApi.registerApp(WXConstants.APP_ID);

        if (mApi != null && mApi.isWXAppInstalled()) {
            final SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "diandi_wx_login";
            mApi.sendReq(req);
        } else
            Toast.makeText(this, "用户未安装微信", Toast.LENGTH_SHORT).show();
    }


    /**
     * 微博登录
     */
    private void loginWeiB() {
    //授权方式有三种，第一种对客户端授权 第二种对Web短授权，第三种结合前两中方式
        mSsoHandler.authorize(new SelfWbAuthListener());
    }

    /***
     * 验证QQ是否绑定
     */
    private void TestQQ(String access_token) {
        Map map = new HashMap();
        map.put("access_token", access_token);
        HtttpRequest.CreatGetRequst(UrlApi.URL_TESTQQ, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("AccontBindingActivity", "e:" + e);
                Toast.makeText(AccontBindingActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AccontBindingActivity_testQQ", response);
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.get("ErrMSG").toString().equals("未绑定")) {
                    Intent intent = new Intent(AccontBindingActivity.this, BindPhoneActivity.class);
                    if(jsonObject.get("Data")!=null){
                        ID = jsonObject.get("Data").toString();
                    }
                    if (ID != null) {
                        intent.putExtra("ID", ID);
                        intent.putExtra("Mode", "QQID");
                        startActivity(intent);
                    }

                } else {
                    String userToken = jsonObject.get("Data").toString();
                    WDWApp.setUserToken(userToken);
                    EventBus.getDefault().post(new Good_Login_event("1"));
                    SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userToken", userToken);
                    editor.commit();
                    MobclickAgent.onProfileSignIn("QQ","wendangwang");
                    Toast.makeText(AccontBindingActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }


    /***
     * 验证新浪微博是否绑定
     */
    private void TestWeiBo(String uid) {
        Map map = new HashMap();
        map.put("code", uid);
        map.put("isIOS","Android");
        HtttpRequest.CreatGetRequst(UrlApi.URL_TESTWEIBO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("AccontBindingActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AccontBindingActivity_testWEIbo", response);
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.get("ErrMSG").toString().equals("未绑定")) {
                    Intent intent = new Intent(AccontBindingActivity.this, BindPhoneActivity.class);
                    if(jsonObject.get("Data")!=null){
                        ID = jsonObject.get("Data").toString();
                    }
                    if (ID != null) {
                        intent.putExtra("ID", ID);
                        intent.putExtra("Mode", "MicroblogID");
                        startActivity(intent);
                    }

                } else {



                    EventBus.getDefault().post(new Good_Login_event("1"));
                    SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.commit();
                    Toast.makeText(AccontBindingActivity.this, "绑定成功", Toast.LENGTH_SHORT).show();
                    MobclickAgent.onProfileSignIn("WB","wendangwang");
                    weiboIsbinding.setText("已绑定");
                    finish();

                }
            }
        });
    }

    //微信事件处理
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void WXEvent(MessageEvent messageEvent) {
        if (messageEvent.getMessage().equals("true")) {
            finish();
        } else {
            Toast.makeText(this, "微信登陆失败请重试", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    private class SelfWbAuthListener implements WbAuthListener {
        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            AccontBindingActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
//                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
//                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                        Toast.makeText(AccontBindingActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                        //获取个人资料
                        //https://api.weibo.com/2/users/show.json
                        AccessTokenKeeper.writeAccessToken(AccontBindingActivity.this, mAccessToken);
                        LogUtils.d("SelfWbAuthListener", mAccessToken.getUid());
                        LogUtils.d("SelfWbAuthListener", mAccessToken.getPhoneNum());
                        LogUtils.d("SelfWbAuthListener", mAccessToken.getRefreshToken());
                        LogUtils.d("SelfWbAuthListener_to", mAccessToken.getToken());

                        TestWeiBo(mAccessToken.getUid());

//                        OkHttpUtils.get()
//                                .url("https://api.weibo.com/2/users/show.json")
//                                .addParams("access_token", mAccessToken.getToken())
//                                .addParams("uid", mAccessToken.getUid())
//                                .build()
//                                .execute(new StringCallback() {
//                                    @Override
//                                    public void onError(Call call, Exception e, int id) {
//                                        LogUtils.d("WEIBO", "获取失败：" + e.getMessage());
//                                        e.printStackTrace();
//                                    }
//
//                                    @Override
//                                    public void onResponse(String response, int id) {
//                                        LogUtils.d("WEIBO", "response:" + response);
//                                    }
//                                });

                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(AccontBindingActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(AccontBindingActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }
    }

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success极光推送别名设置成功";
                    Log.d("WDWApp_alias", alias);
                    LogUtils.d("MainPagerActivity", logs);
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    LogUtils.d("MainPagerActivity", logs);
                    break;
            }
        }
    };
    
    
}

