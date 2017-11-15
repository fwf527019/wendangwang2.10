package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.http.okhttp.OkHttpUtils;
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
import hb.xnwdw.com.wendangwang.gui.adapter.FooterfisAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.FooterHistData;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;
import hb.xnwdw.com.wendangwang.netdata.entity.MyFootDeleteEntity;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MyFootActivity extends ActivityBase implements ExpandableListView.OnGroupClickListener, ExpandableListView.OnChildClickListener, FooterfisAdapter.OnItemClickLitener {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.editor)
    TextView editor;
    @BindView(R.id.elv)
    ExpandableListView elv;
    @BindView(R.id.mycollects_delete)
    TextView mycollectsDelete;
    @BindView(R.id.no_foots_ll)
    LinearLayout noFootsLl;
    @BindView(R.id.my_foot_goto)
    TextView myFootGoto;

    private List<FooterHistData.ObjBean> objBeen = new ArrayList<>();
    private List<MyFootDeleteEntity> selectedID;
    private FooterfisAdapter adapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myfoot;
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
        ButterKnife.bind(this);
        elv.setGroupIndicator(null);
        elv.setOnGroupClickListener(this);
        elv.setOnChildClickListener(this);
        title.setText("我的足迹");
        loadData();

    }


    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("iPage", "1");
        map.put("iPageSize", "100");
        map.put("sDataSource", "APP");
        map.put("sMemberID", WDWApp.getMenberId());

        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETFOOTHOSTORY, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyFootActivity", response);
                if (response.startsWith("{") && JSONObject.parseObject(response).get("obj") != null) {
                    final FooterHistData data = JSON.parseObject(response, FooterHistData.class);
                    if (data.getObj().size() != 0) {
                        noFootsLl.setVisibility(View.GONE);
                    }else {
                        noFootsLl.setVisibility(View.VISIBLE);
                    }
                    objBeen.clear();
                    objBeen.addAll(data.getObj());
                    adapter = new FooterfisAdapter(MyFootActivity.this, objBeen);
                    //adapter.addFooterView(getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) zujiList.getParent(), false), -1, LinearLayout.VERTICAL);
                    elv.setAdapter(adapter);
                    for (int i = 0; i < objBeen.size(); i++) {
                        elv.expandGroup(i);
                    }
                }
            }
        });
    }


    @Override
    public void onItemClick(int flag, int groupPosition, int childPosition) {
        if (flag == 0) {
            //表示点击了删除
            deleteItem(objBeen.get(groupPosition).getContent().get(childPosition).getID());
            objBeen.get(groupPosition).getContent().remove(childPosition);
            adapter.notifyDataSetChanged();
        } else if (flag == 2) {
            //item点击回调
            Intent intent = new Intent(getApplicationContext(),GoodsDetails1.class);
            intent.putExtra("itemId", objBeen.get(groupPosition).getContent().get(childPosition).getItemID() + "");
            startActivity(intent);
        }
    }

    @Override
    public void onCheckBox(List<MyFootDeleteEntity> selectedId) {
        selectedID = new ArrayList<>();
        selectedID.addAll(selectedId);

    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        return true;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }


    @OnClick({R.id.back, R.id.editor, R.id.my_foot_goto, R.id.mycollects_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.editor:
                if (editor.getText().equals("编辑") && adapter != null) {
                    editor.setText("完成");
                    mycollectsDelete.setVisibility(View.VISIBLE);
                    adapter.setFlag(true);
                    adapter.notifyDataSetChanged();
                } else if (editor.getText().equals("完成")) {
                    editor.setText("编辑");
                    mycollectsDelete.setVisibility(View.GONE);
                    adapter.setFlag(false);
                    adapter.notifyDataSetChanged();
                }

                break;
            case R.id.my_foot_goto:
                startActivity(new Intent(this, MainPagerActivity.class));
                break;
            case R.id.mycollects_delete:
                List<Integer> ids=new ArrayList<>();
                for (int i = selectedID.size()-1; i >=0; i--) {
                    ids.add(selectedID.get(i).getID());
                    objBeen.get(selectedID.get(i).getGroupPosition()).getContent().remove(selectedID.get(i).getChildPosition());
                }
                deletaItemsBatch(ids);
                adapter.setData();
                adapter.notifyDataSetChanged();
                break;
        }
    }


    /**
     * 删除足迹
     */
    private void deleteItem(int itemId) {
        JSONObject object = new JSONObject();
        object.put("iID", itemId);
        object.put("sMemberID", WDWApp.getMenberId());
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_DelFootprint, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }
    /**
     * 批量删除足迹
     */
    private  void deletaItemsBatch(List<Integer> ids){
        JSONObject object = new JSONObject();
        object.put("iIDs", ids);
        object.put("sMemberID", WDWApp.getMenberId());
        String paramsString = object.toJSONString();
        HtttpRequest.CreatPostRequst(UrlApi.URL_BRanchDelFootprint, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MyFootActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyFootActivity", response);
            }
        });


    }

}
