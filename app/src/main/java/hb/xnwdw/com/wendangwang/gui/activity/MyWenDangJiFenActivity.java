package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sina.weibo.sdk.api.CmdObject;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.JifenDetailAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.JifenData;
import hb.xnwdw.com.wendangwang.netdata.entity.JifenDetailData;
import hb.xnwdw.com.wendangwang.netdata.entity.NearShopData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/22.
 */
public class MyWenDangJiFenActivity extends ActivityBase {
    @BindView(R.id.jifen_back)
    ImageView jifenBack;
    @BindView(R.id.jifen_rules)
    TextView jifenRules;
    @BindView(R.id.can_uesejifen)
    TextView canUesejifen;
    @BindView(R.id.cannotuse_jifen)
    TextView cannotuseJifen;
    @BindView(R.id.jifen_list)
    RecyclerView jifenList;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_wendangjifen;
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
        loadJIfenNum();
        loadJifenIntegral();
        GetMemberLockedIntegral();
    }

    /**
     * 剩余积分
     */
    private void loadJIfenNum() {
        Map<String, String> map = new HashMap<>();
        map.put("sMemberID", "null");
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_QUERYNUM, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("MyWenDangJiFenActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyWenDangJiFenActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    JifenData data = JSON.parseObject(response, JifenData.class);
                    canUesejifen.setText((int)data.getObj().getSurplusIntegral() + "");
                }

            }
        });
    }

    private int pageIndex=1;
    /**
     * 积分明细
     */
    private void loadJifenIntegral() {
        Map<String, String> map = new HashMap<>();
        map.put("iPage", "1");
        map.put("iPageSize", "8");
        map.put("sMemberID", "null");
        HtttpRequest.CreatGetRequst(UrlApi.URL_QUERYINTEGER, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyWenDangJiFenActivity", response);
                JifenDetailData data = JSON.parseObject(response, JifenDetailData.class);
                if (("请登录").equals(data.getDescribe())) {
                    Toast.makeText(MyWenDangJiFenActivity.this, "请登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), LognActivity.class));
                } else {
                    jifenList.setLayoutManager(new MyLinearLayoutManager(getApplicationContext()));
                    final JifenDetailAdapter adpter = new JifenDetailAdapter(R.layout.item_jifendetail, data.getObj().getData());
                    jifenList.setAdapter(adpter);
                    adpter.addFooterView(MyWenDangJiFenActivity.this.getLayoutInflater().inflate(R.layout.sub_getmore, (ViewGroup) jifenList.getParent(), false));
                    adpter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pageIndex+=1;
                            Map<String, String> map = new HashMap<>();
                            map.put("iPage", pageIndex+"");
                            map.put("iPageSize", "8");
                            map.put("sMemberID", "null");
                            HtttpRequest.CreatGetRequst(UrlApi.URL_QUERYINTEGER, map, new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    LogUtils.d("MyWenDangJiFenActivity", "e:" + e);
                                }
                                @Override
                                public void onResponse(String response, int id) {
                                    Log.d("MyWenDangJiFenActivity", response);
                                    if (!response.contains(MConstant.HTTP404)) {
                                        JifenDetailData data2 = JSON.parseObject(response, JifenDetailData.class);
                                        adpter.addData(data2.getObj().getData());
                                        if(data2.getObj().getData().size()==0){
                                            TextView tv= (TextView) adpter.getFooterLayout().findViewById(R.id.getmore_tv);
                                            tv.setText("没有更多");
                                        }
                                    }
                                }
                            } );
                        }
                    });

                }
            }
        });
    }

    @OnClick({R.id.jifen_back, R.id.jifen_rules})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jifen_back:
                finish();
                break;
            case R.id.jifen_rules:
                startActivity(new Intent(MyWenDangJiFenActivity.this, JifenRulesActivity.class));
                break;
        }
    }

    /**
     * 锁定积分
     */
    private void GetMemberLockedIntegral() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GetMemberLockedIntegral, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MyWenDangJiFenActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404)) {
                    cannotuseJifen.setText(JSONObject.parseObject(response).get("obj").toString());
                }
            }
        });


    }

}
