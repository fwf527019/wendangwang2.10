package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qiyukf.unicorn.api.ConsultSource;
import com.qiyukf.unicorn.api.Unicorn;
import com.qiyukf.unicorn.api.UnreadCountChangeListener;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/13.
 */

public class MyMessageMianPage extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.my_massege_img_sys)
    ImageView myMassegeImgSys;
    @BindView(R.id.message_type_sys)
    TextView messageTypeSys;
    @BindView(R.id.message_time_sys)
    TextView messageTimeSys;
    @BindView(R.id.message_count_sys)
    TextView messageCountSys;
    @BindView(R.id.my_massege_img_notify)
    ImageView myMassegeImgNotify;
    @BindView(R.id.message_type_notify)
    TextView messageTypeNotify;
    @BindView(R.id.message_time_notify)
    TextView messageTimeNotify;
    @BindView(R.id.message_count_notify)
    TextView messageCountNotify;
    @BindView(R.id.message_sys_ll)
    LinearLayout messageSysLl;
    @BindView(R.id.message_fefu_ll)
    LinearLayout messageFefuLl;
    @BindView(R.id.message_sys_notice)
    TextView messageSysNotice;
    @BindView(R.id.message_kefu_notice)
    TextView messageKefuNotice;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mymessagemainpage;
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
        addUnreadCountChangeListener(true);
        EventBus.getDefault().register(this);
        title.setText("消息中心");
        loadMassegeNum();
      int uniconNum=  Unicorn.getUnreadCount();
        if(uniconNum>0){
            messageKefuNotice.setVisibility(View.VISIBLE);
            messageKefuNotice.setText(uniconNum + "");
        }else {
            messageKefuNotice.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.back, R.id.message_sys_ll, R.id.message_fefu_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_sys_ll:
                startActivity(new Intent(MyMessageMianPage.this,MyMassegeActivity.class));
                break;
            case R.id.message_fefu_ll:
                String title = "稳当生活";
                /**
                 * 设置访客来源，标识访客是从哪个页面发起咨询的，
                 * 用于客服了解用户是从什么页面进入三个参数分别为
                 * 来源页面的url，来源页面标题，来源页面额外信息（可自由定义）。
                 * 设置来源后，在客服会话界面的"用户资料"栏的页面项，可以看到这里设置的值。
                 */
                ConsultSource source = new ConsultSource("url", "消息中心", "custom information string");
                /**
                 * 请注意： 调用该接口前，应先检查Unicorn.isServiceAvailable()，
                 * 如果返回为false，该接口不会有任何动作
                 *
                 * @param context 上下文
                 * @param title   聊天窗口的标题
                 * @param source  咨询的发起来源，包括发起咨询的url，title，描述信息等
                 */
                if (Unicorn.isServiceAvailable()) {
                    Log.d("GoodsDetails", "七鱼");
                    Unicorn.openServiceActivity(MyMessageMianPage.this, title, source);
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    //网易七鱼未读消息
    // 添加未读数变化监听，add 为 true 是添加，为 false 是撤销监听。
    // 退出界面时，必须撤销，以免造成资源泄露
    private UnreadCountChangeListener listener = new UnreadCountChangeListener() { // 声明一个成员变量
        @Override
        public void onUnreadCountChange(int count) {
            // 在此更新界面, count 为当前未读数，
            // 也可以用 Unicorn.getUnreadCount() 获取总的未读数
            Log.d("MyMessageMianPage", "count:" + count);
            if (count > 0) {
                messageKefuNotice.setVisibility(View.VISIBLE);
                messageKefuNotice.setText(count + "");
                messageCountNotify.setText("您有"+count+"条新消息未读");
            } else {
                messageKefuNotice.setVisibility(View.GONE);
                messageCountNotify.setText("您有"+0+"条新消息未读");
            }

        }
    };

    private void addUnreadCountChangeListener(boolean add) {
        Unicorn.addUnreadCountChangeListener(listener, add);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        addUnreadCountChangeListener(false);
    }

    /**
     * 获取未读消息条数
     */
    private int megNum;
    private void loadMassegeNum() {
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETMASSEGENUM, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if (!response.contains(MConstant.HTTP404) && !response.equals("0")) {
                    messageSysNotice.setVisibility(View.VISIBLE);
                    messageSysNotice.setText(response);
                    megNum= Integer.parseInt(response);
                    messageCountSys.setText("您有"+megNum+"条新消息未读");
                } else {
                    messageSysNotice.setVisibility(View.INVISIBLE);
                    messageCountSys.setText("您有"+0+"条新消息未读");
                }

            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(Integer event) {
        loadMassegeNum();
    }
}
