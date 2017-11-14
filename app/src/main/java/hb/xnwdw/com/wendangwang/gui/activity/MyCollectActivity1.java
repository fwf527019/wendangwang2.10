package hb.xnwdw.com.wendangwang.gui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.MyCollectesAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MyCollectActivity1 extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.editor)
    TextView editor;
    @BindView(R.id.no_collecets)
    LinearLayout noCollecets;
    @BindView(R.id.my_collects_recycler)
    SwipeMenuRecyclerView myCollectsRecycler;
    @BindView(R.id.mycollects_delete)
    TextView mycollectsDelete;
    List<Integer> itemIds=null;
    List<Integer> pois=null;
    private MyCollectesAdapter myCollectesAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_mycollects;
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
        title.setText("我的收藏");
        loadData();

    }

    /**
     * 获取收藏信息
     */
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "8");
        map.put("dataSource", "APP");
        map.put("memberId", WDWApp.getMenberId());

        HtttpRequest.CreatGetRequst(UrlApi.URL_MYCOLECTION, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("MyCollectActivity", response);
                final MyCollectionData data = JSON.parseObject(response, MyCollectionData.class);

            }
        });
    }



    @OnClick({R.id.back, R.id.editor, R.id.mycollects_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.editor:
                if (editor.getText().equals("编辑")) {
                    editor.setText("完成");
                    mycollectsDelete.setVisibility(View.VISIBLE);
                    myCollectesAdapter.setFlag(true);
                    myCollectesAdapter.notifyDataSetChanged();
                } else if (editor.getText().equals("完成")) {
                    editor.setText("编辑");
                    mycollectsDelete.setVisibility(View.GONE);
                   myCollectesAdapter.setFlag(false);
                    myCollectesAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.mycollects_delete:
                if(itemIds!=null&&pois!=null){
                    for (int i = 0; i <itemIds.size() ; i++) {
                        deleteItem(itemIds.get(i));
                        myCollectesAdapter.notifyItemRemoved(pois.get(i));
                    }
                }
                break;
        }
    }



    /**
     * 删除收藏
     */
    private void deleteItem(int itemId) {
        JSONObject object = new JSONObject();
        object.put("memberId", "10");
        object.put("dataSource", "APP");
        object.put("itemId", itemId);
        String paramsString = object.toJSONString();


        HtttpRequest.CreatPostRequst(UrlApi.URL_DELETECOLECTION, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }

}
