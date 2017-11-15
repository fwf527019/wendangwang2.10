package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import hb.xnwdw.com.wendangwang.gui.adapter.MyCollectesAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;
import hb.xnwdw.com.wendangwang.netdata.entity.TestGoods;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MyCollectActivity extends ActivityBase implements SwipeMenuCreator, OnSwipeMenuItemClickListener, MyCollectesAdapter.OnItemClickLitener {
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

    private List<MyCollectionData.ObjBean> objBeen = new ArrayList<>();
    private List<Boolean> selected = new ArrayList<>();
    private MyCollectesAdapter myCollectesAdapter;

    private MyCollectionData data;

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
        ButterKnife.bind(this);
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getApplicationContext());
        myCollectsRecycler.setLayoutManager(myLinearLayoutManager);
        myCollectsRecycler.setItemViewSwipeEnabled(false); // 开启滑动删除。
        // 设置添加器监听器。
        myCollectsRecycler.setSwipeMenuCreator(this);
        myCollectsRecycler.setSwipeMenuItemClickListener(this);
        title.setText("我的收藏");
        loadData();
    }

    /**
     * 获取收藏信息
     */
    private void loadData() {
        Map<String, String> map = new HashMap<>();
        map.put("pageIndex", "1");
        map.put("pageSize", "100");
        map.put("dataSource", "APP");
        map.put("memberId", WDWApp.getMenberId());

        HtttpRequest.CheackIsLoginGet(this, UrlApi.URL_MYCOLECTION, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyCollectActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    data = JSON.parseObject(response, MyCollectionData.class);
                    if (data.getObj().size() != 0) {
                        objBeen.clear();
                        objBeen.addAll(data.getObj());
                        for (int i = 0; i < objBeen.size(); i++) {
                            selected.add(false);
                        }
                        noCollecets.setVisibility(View.GONE);
                        myCollectesAdapter = new MyCollectesAdapter(MyCollectActivity.this, objBeen, selected);
                        myCollectsRecycler.setAdapter(myCollectesAdapter);

                    } else {
                        noCollecets.setVisibility(View.VISIBLE);
                    }
                }
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
                if (editor.getText().equals("编辑") && myCollectesAdapter != null) {
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
                List<Integer> ids = new ArrayList<>();

                for (int i = selected.size() - 1; i >= 0; i--) {
                    if (selected.get(i)) {
                        ids.add(objBeen.get(i).getItemID());
                        objBeen.remove(i);
                        selected.remove(i);
                    }
                }
                myCollectesAdapter.notifyDataSetChanged();
                deleteItemsByids(ids);
                break;
        }
    }

    @Override
    public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
        closeable.smoothCloseMenu();// 关闭被点击的菜单。
        if (menuPosition == 0) {// 删除按钮被点击。
            deleteItem(objBeen.get(adapterPosition).getItemID());
            objBeen.remove(adapterPosition);
            selected.remove(adapterPosition);
            myCollectesAdapter.notifyItemRemoved(adapterPosition);
        }
    }

    @Override
    public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
        SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext())
                .setBackgroundDrawable(R.drawable.selector_red)
                .setText("  删除  ") // 文字。
                .setTextColor(Color.WHITE) // 文字颜色。
                .setTextSize(16) // 文字大小。
                .setWidth(ViewGroup.LayoutParams.WRAP_CONTENT)
                .setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。.
    }

    //SwipeMenuRecyclerView item所有点击回调
    @Override
    public void onItemClick(View view, int flag, int position) {
        if (flag == 0) {
            selected.set(position, false);
        } else if (flag == 1) {
            selected.set(position, true);
        } else if (flag == 2) {
            //item点击回调
            Intent intent = new Intent(MyCollectActivity.this, GoodsDetails1.class);
            intent.putExtra("itemId", data.getObj().get(position).getItemID() + "");
            startActivity(intent);
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

    /***
     * 批量删除
     */
    private void deleteItemsByids(List<Integer> ids) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("dataSource", "APP");
        jsonObject.put("memberId", "APP");
        jsonObject.put("ids", ids);
        Log.d("MyCollectActivity", jsonObject.toJSONString());
        HtttpRequest.CreatPostRequst(UrlApi.URL_DelMyCollectionByIds, jsonObject.toJSONString(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("MyCollectActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("MyCollectActivity", response);
            }
        });

    }


}
