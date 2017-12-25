package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MsgDetileData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/12/18.
 */
public class MsgDetileActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.imgs_ll)
    LinearLayout imgsLl;
    @BindView(R.id.replay)
    TextView replay;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_msgdetile;
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
        String msgId = intent.getStringExtra("id");
        getMsgDetile(msgId);
        title.setText("消息详情");
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    private void getMsgDetile(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("iMessageID", id);
        HtttpRequest.CheackIsLoginGet(this, UrlApi.URL_GetQueryMessageDetail, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MsgDetileActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MsgDetileActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    MsgDetileData data = JSON.parseObject(response, MsgDetileData.class);
                    type.setText(data.getObj().getComplaintTypeString());
                    content.setText(data.getObj().getDescribe());
                    replay.setText(Utils.cutString(data.getObj().getMessContent(),"回复").substring(1));
                    String[] picList = data.getObj().getUpPics().split(";");

                    for (int i = 0; i <picList.length ; i++) {
                        SimpleDraweeView imgview=new SimpleDraweeView(MsgDetileActivity.this);
                        LinearLayout.LayoutParams pams = new LinearLayout.LayoutParams(dip2px(MsgDetileActivity.this,70), LinearLayout.LayoutParams.MATCH_PARENT);
                        pams.setMargins(dip2px(MsgDetileActivity.this,10), 0, 0, 0);
                        imgview.setLayoutParams(pams);
                        imgsLl.addView(imgview);
                        imgview.setImageURI(UrlApi.SERVER_IP + picList[i]);
                    }
                }

            }
        });

    }

}
