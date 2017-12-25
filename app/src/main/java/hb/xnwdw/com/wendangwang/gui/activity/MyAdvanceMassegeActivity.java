package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.MymessageAdapter1;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MsgData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/12/15.
 */
public class MyAdvanceMassegeActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.advance_msg_list)
    RecyclerView advanceMsgList;
    MsgData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myadvancemassege;
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
        title.setText("投诉建议与举报消息");
        data = (MsgData) intent.getSerializableExtra("data");
        if(data!=null&&data.getObj()!=null){
            initList();
        }

    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    private void initList() {
        advanceMsgList.setLayoutManager(new MyLinearLayoutManager(this));
        MymessageAdapter1 adapter = new MymessageAdapter1(R.layout.item_message, data.getObj().getMsgList());
        advanceMsgList.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MyAdvanceMassegeActivity.this, MsgDetileActivity.class);
                intent.putExtra("id", data.getObj().getMsgList().get(position).getID() + "");
                startActivity(intent);

                if (data.getObj().getMsgList().get(position).getIsRead() == 0) {
                    changeMessageState(data.getObj().getMsgList().get(position).getID(), new StringCallback() {
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

                    data.getObj().getMsgList().get(position).setIsRead(1);
                    adapter.notifyItemChanged(position);
                }

            }
        });
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
}
