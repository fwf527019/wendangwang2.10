package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jude.rollviewpager.Util;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import hb.xnwdw.com.wendangwang.EventBus.Good_Login_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.WeiBo.WConstants;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.wxapi.WXConstants;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.LoginData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/28.
 */

public class LognActivity extends ActivityBase {
    @BindView(R.id.logn_back)
    ImageView lognBack;
    @BindView(R.id.phone_edt)
    EditText phoneEdt;
    @BindView(R.id.pasw_edt)
    EditText paswEdt;
    @BindView(R.id.logn_bt)
    Button lognBt;
    @BindView(R.id.regest_tv)
    TextView regestTv;
    @BindView(R.id.fast_logn_tv)
    TextView fastLognTv;
    @BindView(R.id.fogetpasw_tv)
    TextView fogetpaswTv;
    @BindView(R.id.qq_logn)
    LinearLayout qqLogn;
    @BindView(R.id.weichat_logn)
    LinearLayout weichatLogn;
    @BindView(R.id.weobo_logn)
    LinearLayout weoboLogn;
    private Map<String, String> map;
    String phoneNum;
    String pasw;
    private Tencent mTencent;
    //授权登录监听器
    private IUiListener loginListener;
    //获取用户信息监听器
    //   private IUiListener userInfoListener;
    //获取信息的范围参数
    private String scope;
    //qq用户信息
    private UserInfo userInfo;
    private SsoHandler mssoHandler;
    private String IntentTag;


    /**
     * 新浪微博
     */
    private SsoHandler mSsoHandler;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    @Override
    protected int getContentViewResId() {
        return R.layout.logn_activity;
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
        Intent intent = getIntent();
        IntentTag = intent.getStringExtra("IntentTag");
        mTencent = Tencent.createInstance("101423390", LognActivity.this);

        WbSdk.install(this, new AuthInfo(this, WConstants.APP_KEY, WConstants.REDIRECT_URL,
                WConstants.SCOPE));
        mSsoHandler = new SsoHandler(LognActivity.this);

        //事件注册
        EventBus.getDefault().register(this);
        mAccessToken = AccessTokenKeeper.readAccessToken(this);
    }

    /**
     * 普通密码登录
     */
    public void gotoLogn() {
        startProgressDialog("登陆中...");
        phoneNum = phoneEdt.getText().toString();
        pasw = paswEdt.getText().toString();
        JSONObject object = new JSONObject();
        object.put("phone", phoneNum);
        object.put("pwd", pasw);
        object.put("dataSource", "APP");
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequstNoToken(UrlApi.UrL_USER_LOIN, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                Toast.makeText(LognActivity.this, "网络异常请检查您的网络连接", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("LognActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    JSONObject jsonObject = JSON.parseObject(response);
                    if ((jsonObject.get("obj") != null) && (jsonObject.get("obj").toString() != null)) {
                        LogUtils.d("LognActivity", jsonObject.get("obj").toString());
                        LoginData data = JSON.parseObject(response, LoginData.class);
                        WDWApp.setUserToken(data.getObj());
                        SharedPreferences sp = getSharedPreferences("MenberData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("userName", phoneNum);
                        editor.putString("userPsw", pasw);
                        editor.putString("userToken", data.getObj());
                        editor.commit();
                        MobclickAgent.onProfileSignIn(phoneNum);

                        EventBus.getDefault().post(new Good_Login_event("1"));
                        Toast.makeText(LognActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                        JPushInterface.setAliasAndTags(getApplicationContext(),
                                phoneNum, null, mAliasCallback);
                        //返回之前的页面
                        finish();
                    } else {
                        Toast.makeText(LognActivity.this, jsonObject.get("describe").toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


    @OnClick({R.id.logn_back, R.id.logn_bt, R.id.regest_tv, R.id.fast_logn_tv, R.id.fogetpasw_tv, R.id.qq_logn, R.id.weichat_logn, R.id.weobo_logn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.logn_back:
                finish();
                break;
            case R.id.logn_bt:
                if (Utils.isPhone(phoneEdt.getText().toString())) {
                    gotoLogn();
                } else {
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.regest_tv:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.fast_logn_tv:
                startActivity(new Intent(this, FastLognActivity.class));
                finish();
                break;
            case R.id.fogetpasw_tv:
                startActivity(new Intent(this, ForgetPaswActivity.class));
                finish();
                break;
            case R.id.qq_logn:
                loginQQ();
                break;
            case R.id.weichat_logn:
                loginWXchat();
                break;
            case R.id.weobo_logn:
                loginWeiB();
                break;
        }
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
        super.onActivityResult(requestCode, resultCode, data);
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
                        LogUtils.d("LognActivity", o.toString());
                        JSONObject jsonObject = JSON.parseObject(o.toString());
//                        ID = jsonObject.get("openid").toString();
                        TestQQ(jsonObject.get("access_token").toString());

                    }

                    @Override
                    public void onError(UiError uiError) {
                        LogUtils.d("LognActivity", "uiError:" + uiError);
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
        AccessTokenKeeper.clear(getApplicationContext());
        mAccessToken = new Oauth2AccessToken();
        //授权方式有三种，第一种对客户端授权 第二种对Web短授权，第三种结合前两中方式
          mSsoHandler.authorize(new SelfWbAuthListener());

    }

    /***
     * 验证QQ是否绑定
     */
    private void TestQQ(String access_token) {
        Map map = new HashMap();
        map.put("access_token", access_token);
        HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_TESTQQ, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("LognActivity", "e:" + e);
                Toast.makeText(LognActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("LognActivity_testQQ", response);
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.get("ErrMSG").toString().equals("未绑定")) {
                    Intent intent = new Intent(LognActivity.this, BindPhoneActivity.class);
                    if (jsonObject.get("Data") != null) {
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
                    MobclickAgent.onProfileSignIn("QQ", "wendangwang");
                    Toast.makeText(LognActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
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
        map.put("isIOS", "Android");
        HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_TESTWEIBO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("LognActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("LognActivity_testWEIbo", response);
                JSONObject jsonObject = JSON.parseObject(response);
                if (jsonObject.get("ErrMSG").toString().equals("未绑定")) {
                    Intent intent = new Intent(LognActivity.this, BindPhoneActivity.class);
                    if (jsonObject.get("Data") != null) {
                        ID = jsonObject.get("Data").toString();
                    }
                    if (ID != null) {
                        intent.putExtra("ID", ID);
                        intent.putExtra("Mode", "MicroblogID");
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
                    Toast.makeText(LognActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    MobclickAgent.onProfileSignIn("WB", "wendangwang");
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
            LognActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
//                        updateTokenView(false);
                        // 保存 Token 到 SharedPreferences
//                        AccessTokenKeeper.writeAccessToken(LoginActivity.this, mAccessToken);
                        Toast.makeText(LognActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                        //获取个人资料
                        //https://api.weibo.com/2/users/show.json
                        AccessTokenKeeper.writeAccessToken(LognActivity.this, mAccessToken);
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
            Toast.makeText(LognActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(LognActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
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