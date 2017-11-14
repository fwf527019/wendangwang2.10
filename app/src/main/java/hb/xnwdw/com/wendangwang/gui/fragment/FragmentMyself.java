package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.AccontinfoActivity;
import hb.xnwdw.com.wendangwang.gui.activity.LognActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyAdressActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyCollectActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyFootActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyMassegeActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyMessageMianPage;
import hb.xnwdw.com.wendangwang.gui.activity.MyOrder;
import hb.xnwdw.com.wendangwang.gui.activity.MySaleAfter;
import hb.xnwdw.com.wendangwang.gui.activity.MySetsActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyWenDangJiFenActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MyYouHuiQuanActivity;
import hb.xnwdw.com.wendangwang.gui.activity.My_sales;
import hb.xnwdw.com.wendangwang.gui.activity.OfflineCopuonActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MenberInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.ResContNumData;
import hb.xnwdw.com.wendangwang.netdata.entity.ServerContData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterInforData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterMenberInfoData;
import hb.xnwdw.com.wendangwang.netdata.entity.UserCenterOrderNumData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.StatusBarCompat;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/20.
 */

public class FragmentMyself extends FragmentBase implements HtttpRequest.LoginStatu {
    @BindView(R.id.username_tv)
    TextView usernameTv;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.jifen)
    LinearLayout jifen;
    @BindView(R.id.my_massege)
    LinearLayout myMassege;
    @BindView(R.id.my_shoucang)
    LinearLayout myShoucang;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.my_foot)
    LinearLayout myFoot;
    @BindView(R.id.my_adress)
    LinearLayout myAdress;
    @BindView(R.id.my_set)
    LinearLayout mySet;
    @BindView(R.id.myself_youhuiquan)
    LinearLayout myselfYouhuiquan;
    @BindView(R.id.myself_wendangjifen)
    LinearLayout myselfWendangjifen;
    @BindView(R.id.myself_photo_img)
    SimpleDraweeView myselfPhotoImg;
    @BindView(R.id.daizifu_notice)
    TextView daizifuNotice;
    @BindView(R.id.daishouhuo_notice)
    TextView daishouhuoNotice;
    @BindView(R.id.daipingjia_notice)
    TextView daipingjiaNotice;
    @BindView(R.id.center_sever_notice)
    TextView severnotice;
    @BindView(R.id.all_notice)
    TextView allNotice;

    @BindView(R.id.myself_jifen_num)
    TextView myselfJifenNum;
    @BindView(R.id.myself_youhuiquan_num)
    TextView myselfYouhuiquanNum;
    @BindView(R.id.daizifu)
    FrameLayout daizifu;
    @BindView(R.id.daishouhuo)
    FrameLayout daishouhuo;
    @BindView(R.id.daipingjia)
    FrameLayout daipingjia;
    @BindView(R.id.shouhou)
    FrameLayout shouhou;
    @BindView(R.id.allorders)
    FrameLayout allorders;
    @BindView(R.id.my_massege_notice)
    TextView myMassegeNotice;
    private UserCenterInforData data;

    protected boolean isCreate = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreate = true;
    }

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_myself;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        StatusBarCompat.compat(getActivity(), getResources().getColor(R.color.maincolor));
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StatusBarCompat.compat(getActivity(), getResources().getColor(R.color.white));
        jifen.getBackground().setAlpha(80);
        loadDats();
        loadMassegeNum();
    }

    /**
     * 获取个人中心的页面数据
     */
    private void loadDats() {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "APP");
        map.put("memberId", "0");

        HtttpRequest.CreatGetRequst(UrlApi.URL_USERCENTERINFO, map, new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("FragmentMyself", "e:" + e);
                        daizifuNotice.setVisibility(View.GONE);
                        daishouhuoNotice.setVisibility(View.GONE);
                        daipingjiaNotice.setVisibility(View.GONE);
                        myselfPhotoImg.setImageURI("");
                        myselfYouhuiquanNum.setText("0");
                        myselfJifenNum.setText("0");
                        usernameTv.setText("马上登录");
                        severnotice.setVisibility(View.GONE);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!response.contains(MConstant.HTTP404)) {
                            Log.d("FragmentMyself", response);
                            String str = "{\"data\":" + response + "}";
                            JSONObject jsonObj = JSON.parseObject(str);
                            List<JSONObject> list = (List<JSONObject>) jsonObj.get("data");
                            Log.d("FragmentMyself", list.toString());
                            JSONObject jsonObject1 = list.get(0);
                            JSONObject jsonObject2 = list.get(1);
                            JSONObject jsonObject3 = list.get(2);
                            JSONObject jsonObject4 = list.get(3);
                            JSONObject jsonObject1_1 = (JSONObject) jsonObject1.get("value");

                            MenberInfoData menberInfoData = JSON.parseObject(jsonObject3.toString(), MenberInfoData.class);

                            for (int i = 0; i < menberInfoData.getValue().size(); i++) {
                                if (menberInfoData.getValue().get(i).getOrderStatus().equals("1")) {
                                    daizifuNotice.setVisibility(View.VISIBLE);
                                    daizifuNotice.setText(menberInfoData.getValue().get(i).getCount());
                                    if (menberInfoData.getValue().get(i).getCount().equals("0")) {
                                        daizifuNotice.setVisibility(View.GONE);
                                    }
                                }
                                if (menberInfoData.getValue().get(i).getOrderStatus().equals("8")) {
                                    daishouhuoNotice.setVisibility(View.VISIBLE);
                                    daishouhuoNotice.setText(menberInfoData.getValue().get(i).getCount());
                                    if (menberInfoData.getValue().get(i).getCount().equals("0")) {
                                        daishouhuoNotice.setVisibility(View.GONE);
                                    }
                                }
                                if (menberInfoData.getValue().get(i).getOrderStatus().equals("9")) {
                                    daipingjiaNotice.setVisibility(View.VISIBLE);
                                    daipingjiaNotice.setText(menberInfoData.getValue().get(i).getCount());
                                    if (menberInfoData.getValue().get(i).getCount().equals("0")) {
                                        daipingjiaNotice.setVisibility(View.GONE);
                                    }
                                }
                            }
                            if (((JSONObject) (JSONObject) (((JSONObject) jsonObject1.get("value")).get("MemberInfo"))).get("SurplusIntegral").toString() != null) {
                                if (((JSONObject) (jsonObject1_1.get("MemberInfo"))).get("HeadFace") != null) {
                                    myselfPhotoImg.setImageURI(UrlApi.SERVER_IP + ((JSONObject) (jsonObject1_1.get("MemberInfo"))).get("HeadFace").toString() + "");
                                }
                            }
                            if (jsonObject1_1.get("MemberName") != null) {
                                usernameTv.setText(jsonObject1_1.get("MemberName").toString());
                            } else {
                                usernameTv.setText("");
                            }

                            myselfJifenNum.setText(((JSONObject) (JSONObject) (((JSONObject) jsonObject1.get("value")).get("MemberInfo"))).get("SurplusIntegral").toString());
                            myselfYouhuiquanNum.setText(jsonObject2.get("value").toString());
                            if (jsonObject4.get("value").toString().equals("0")) {
                                severnotice.setVisibility(View.GONE);
                            } else {
                                severnotice.setVisibility(View.VISIBLE);
                                severnotice.setText(jsonObject4.get("value").toString());
                            }
                        }
                    }
                }
        );

    }

    @OnClick({R.id.daizifu, R.id.daishouhuo, R.id.daipingjia, R.id.shouhou, R.id.allorders, R.id.myself_photo_img, R.id.myself_youhuiquan, R.id.myself_wendangjifen, R.id.username_tv, R.id.my_massege, R.id.my_shoucang, R.id.imageView3, R.id.my_foot, R.id.my_adress, R.id.my_set})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myself_photo_img:
                if (!usernameTv.getText().toString().equals("马上登录")) {
                    startActivity(new Intent(getActivity(), AccontinfoActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LognActivity.class));
                }
                break;
            case R.id.username_tv:
                if (!usernameTv.getText().toString().equals("马上登录")) {
                    startActivity(new Intent(getActivity(), AccontinfoActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LognActivity.class));
                }
                break;
            case R.id.my_massege:
                new HtttpRequest(this).isLogin("my_massege");
                break;
            case R.id.my_shoucang:
                new HtttpRequest(this).isLogin("my_shoucang");
                break;
            case R.id.my_foot:
                new HtttpRequest(this).isLogin("my_foot");
                break;
            case R.id.my_adress:

                new HtttpRequest(this).isLogin("my_adress");



                break;
            case R.id.my_set:
                new HtttpRequest(this).isLogin("my_set");


                break;
            //优惠券
            case R.id.myself_youhuiquan:
                new HtttpRequest(this).isLogin("myself_youhuiquan");


                break;
            //稳当积分
            case R.id.myself_wendangjifen:
                new HtttpRequest(this).isLogin("myself_wendangjifen");


                break;
            //待支付
            case R.id.daizifu:
                new HtttpRequest(this).isLogin("daizifu");



                break;
            //待收货
            case R.id.daishouhuo:
                new HtttpRequest(this).isLogin("daishouhuo");



                break;
            //待评价
            case R.id.daipingjia:
                new HtttpRequest(this).isLogin("daipingjia");

                break;

            //售后
            case R.id.shouhou:
                new HtttpRequest(this).isLogin("shouhou");

                break;

            //所有订单
            case R.id.allorders:
                new HtttpRequest(this).isLogin("allorders");

                break;
        }

    }
    @Override
    public void isLogin(String what) {
        switch (what) {
            case "my_massege":
                Log.d("FragmentMyself", "Utils.isLogining():" + Utils.isLogining());
                startActivity(new Intent(getActivity(), MyMessageMianPage.class));
                break;
            case "my_shoucang":
                startActivity(new Intent(getActivity(), MyCollectActivity.class));
                break;
            case "my_foot":
                startActivity(new Intent(getActivity(), MyFootActivity.class));
                break;
            case "my_adress":
                startActivity(new Intent(getActivity(), MyAdressActivity.class));
                break;
            case "my_set":
                startActivity(new Intent(getActivity(), MySetsActivity.class));
                break;
            case "myself_youhuiquan":
                Intent intent = new Intent(getActivity(), MyYouHuiQuanActivity.class);
                startActivity(intent);
                break;
            case "myself_wendangjifen":
                startActivity(new Intent(getActivity(), MyWenDangJiFenActivity.class));
                break;
            case "daizifu":
                Intent intent1 = new Intent(getActivity(), MyOrder.class);
                intent1.putExtra("titalTag", "1");
                startActivity(intent1);
                break;
            case "daishouhuo":
                Intent intent2 = new Intent(getActivity(), MyOrder.class);
                intent2.putExtra("titalTag", "2");
                startActivity(intent2);
                break;

            case "daipingjia":
                Intent intent3 = new Intent(getActivity(), MyOrder.class);
                intent3.putExtra("titalTag", "3");
                startActivity(intent3);
                break;
            case "shouhou":
                Intent intent4 = new Intent(getActivity(), MySaleAfter.class);
                intent4.putExtra("url", "/wdw/page/mb/my_sales1.html?d=salesAfter&app=app");
                startActivity(intent4);
                break;
            case "allorders":
                Intent intent5 = new Intent(getActivity(), MyOrder.class);
                intent5.putExtra("titalTag", "0");
                startActivity(intent5);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();


    }

    @Override
    public void onResume() {
        LogUtils.d("FragmentMyself", "onResume");
        super.onResume();
        loadDats();
        loadMassegeNum();
    }

    @Override
    public void onPause() {
        LogUtils.d("FragmentMyself", "onPause");
        super.onPause();
    }

    @Override
    public void onStart() {
        loadDats();
        LogUtils.d("FragmentMyself", "onStart");
        super.onStart();
    }


//    @Override
//    public void showUserCenterOrderNum(List<UserCenterOrderNumData.ValueBean> valueBeen) {
//        for (int i = 0; i < valueBeen.size(); i++) {
//            if (valueBeen.get(i).getOrderStatus().equals("1")) {
//                daizifuNotice.setVisibility(View.VISIBLE);
//                daizifuNotice.setText(valueBeen.get(i).getCount());
//                if (valueBeen.get(i).getCount().equals("0")) {
//                    daizifuNotice.setVisibility(View.GONE);
//                }
//            }
//            if (valueBeen.get(i).getOrderStatus().equals("8")) {
//                daishouhuoNotice.setVisibility(View.VISIBLE);
//                daishouhuoNotice.setText(valueBeen.get(i).getCount());
//                if (valueBeen.get(i).getCount().equals("0")) {
//                    daishouhuoNotice.setVisibility(View.GONE);
//                }
//            }
//            if (valueBeen.get(i).getOrderStatus().equals("9")) {
//                daipingjiaNotice.setVisibility(View.VISIBLE);
//                daipingjiaNotice.setText(valueBeen.get(i).getCount());
//                if (valueBeen.get(i).getCount().equals("0")) {
//                    daipingjiaNotice.setVisibility(View.GONE);
//                }
//            }
//        }
//
//    }


    /**
     * 获取消息条数
     */
    private void loadMassegeNum() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMASSEGENUM, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404) && !response.equals("0")) {
                    myMassegeNotice.setVisibility(View.VISIBLE);
                    myMassegeNotice.setText(response);
                } else {
                    myMassegeNotice.setVisibility(View.INVISIBLE);
                }

            }
        });
    }


    private void loadtOrderForSaleAfterData() {
        Map<String, String> map = new HashMap<>();
        map.put("page", "1");
        map.put("pagesize", "8");
        map.put("memberID", "sss");
        HtttpRequest.CreatGetRequst(UrlApi.URL_UDPAYDATA, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentMyself", response);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreate) {
            //相当于Fragment的onResume
            loadDats();
            loadMassegeNum();
        } else {
            //相当于Fragment的onPause
        }
    }


    @Override
    public void noLofin() {
        Log.d("FragmentMyself", "Utils.isLogining():" + Utils.isLogining());
        Toast.makeText(getActivity(), "登录信息失效,请重新登录", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LognActivity.class));
    }
}

