package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.adapter.MyCollectesAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyCollectionData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/12/11.
 */

public class FragmentGoodsCollecte extends FragmentBase implements MyCollectesAdapter.OnItemClickLitener, OnSwipeMenuItemClickListener, SwipeMenuCreator {
    @BindView(R.id.no_collecets)
    LinearLayout noCollecets;
    @BindView(R.id.my_collects_recycler)
    SwipeMenuRecyclerView myCollectsRecycler;
    @BindView(R.id.mycollects_delete)
    TextView mycollectsDelete;
    Unbinder unbinder;
    private MyCollectionData data;
    private List<MyCollectionData.ObjBean> objBeen = new ArrayList<>();
    private List<Boolean> selected = new ArrayList<>();
    private MyCollectesAdapter myCollectesAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_goods_collectes;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        myCollectsRecycler.setLayoutManager(myLinearLayoutManager);
        myCollectsRecycler.setItemViewSwipeEnabled(false); // 开启滑动删除。
        // 设置添加器监听器。
        myCollectsRecycler.setSwipeMenuCreator(this);
        myCollectsRecycler.setSwipeMenuItemClickListener(this);
        EventBus.getDefault().register(this);
        loadData();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.mycollects_delete)
    public void onViewClicked() {
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

        HtttpRequest.CheackIsLoginGet(getActivity(), UrlApi.URL_MYCOLECTION, map, new StringCallback() {
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
                        myCollectesAdapter = new MyCollectesAdapter(FragmentGoodsCollecte.this, objBeen, selected);
                        myCollectsRecycler.setAdapter(myCollectesAdapter);

                    } else {
                        noCollecets.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
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
        SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
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
            Intent intent = new Intent(getActivity(), GoodsDetails1.class);
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEdtInfo(String event) {
        String msg = "onEventMainThread收到了消息：" + event;
        Log.d("harvic", msg);
        if (event.equals("finish")) {
            mycollectsDelete.setVisibility(View.VISIBLE);
            myCollectesAdapter.setFlag(true);
            myCollectesAdapter.notifyDataSetChanged();
        } else if (event.equals("editor")) {
            mycollectsDelete.setVisibility(View.GONE);
            myCollectesAdapter.setFlag(false);
            myCollectesAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
