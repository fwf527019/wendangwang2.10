package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.MymessageAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MessageData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MyMassegeActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.my_massege_recycler)
    RecyclerView myMassegeRecycler;
    private MymessageAdapter adapter;
    private int pageIdex = 1;
    private MessageData MessageDatadata;
    private Handler mHandler;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mymassege;
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
        title.setText("系统消息");
        loadMessage(pageIdex);
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle b = msg.getData();
                MessageDatadata = (MessageData) b.getSerializable("data");
                myMassegeRecycler.setLayoutManager(new MyLinearLayoutManager(MyMassegeActivity.this));
                adapter = new MymessageAdapter(R.layout.item_message, MessageDatadata.getObj().getMsgList());
                myMassegeRecycler.setAdapter(adapter);
                adapter.addFooterView(MyMassegeActivity.this.getLayoutInflater().inflate(R.layout.sub_getmore, (ViewGroup) myMassegeRecycler.getParent(), false));
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (MessageDatadata.getObj().getMsgList().get(position).getOperationType() != null) {
                            switch (MessageDatadata.getObj().getMsgList().get(position).getOperationType()) {
                                case "1":
                                    Intent i = new Intent(MyMassegeActivity.this, OrderDetailsActivity.class);
                                    i.putExtra("orderNum", MessageDatadata.getObj().getMsgList().get(position).getParams());
                                    startActivity(i);
                                    break;
                                case "2":
                                    Intent i1 = new Intent(MyMassegeActivity.this, MySaleDetail.class);
                                    i1.putExtra("saleId", MessageDatadata.getObj().getMsgList().get(position).getParams());
                                    startActivity(i1);
                                    break;
                                case "3":
                                    Intent i2 = new Intent(MyMassegeActivity.this, UrlWebActivity.class);
                                    i2.putExtra("url", MessageDatadata.getObj().getMsgList().get(position).getParams());
                                    startActivity(i2);
                                    break;
                            }

                            if (MessageDatadata.getObj().getMsgList().get(position).getIsRead() == 0) {
                                changeMessageState(MessageDatadata.getObj().getMsgList().get(position).getID(), new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        Log.d("MyMassegeActivity", "e:" + e);
                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                    if(JSONObject.parseObject(response).get("dataStatus").toString().equals("1")){

                                        EventBus.getDefault().post(1);
                                    }

                                    }
                                });

                                MessageDatadata.getObj().getMsgList().get(position).setIsRead(1);
                                adapter.notifyItemChanged(position);
                            }

                        }
                    }
                });
                adapter.getFooterLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, String> map = new HashMap<>();
                        map.put("iPage", pageIdex + 1 + "");
                        map.put("iPageSize", "8");
                        HtttpRequest.CreatGetRequst(UrlApi.URL_GETMESSAGE, map, new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.d("MyMassegeActivity", response);
                                if (!response.contains(MConstant.HTTP404)) {
                                    MessageData dataw = JSON.parseObject(response, MessageData.class);
                                    if (dataw.getObj().getMsgList().size() == 0) {
                                        TextView tv = (TextView) adapter.getFooterLayout().findViewById(R.id.getmore_tv);
                                        tv.setText("没有更多");
                                    } else {
                                        adapter.addData(dataw.getObj().getMsgList());
                                    }

                                } else {
                                    Toast.makeText(MyMassegeActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                    }
                });

            }
        };


    }

    /**
     * 改变消息的已读未读状态
     * @param id
     * @param callback
     */
    private void changeMessageState(String id, StringCallback callback) {

        Map<String, String> map = new HashMap<>();
        map.put("imsgID", id);
        HtttpRequest.CreatGetRequst(UrlApi.URL_SetMsgRead, map, callback);

    }

    @OnClick({R.id.back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    /**
     * 或取消息数据
     * @param page
     */
    private void loadMessage(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("iPage", page + "");
        map.put("iPageSize", "8");
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETMESSAGE, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyMassegeActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    MessageData data = JSON.parseObject(response, MessageData.class);
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", data);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);
                } else {
                    Toast.makeText(MyMassegeActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


}
