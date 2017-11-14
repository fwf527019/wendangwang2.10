package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.ShearchResutsActivity;
import hb.xnwdw.com.wendangwang.gui.activity.ShearchResutsActivityweb;
import hb.xnwdw.com.wendangwang.gui.adapter.GoodsClassfyGridViewAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.GoodsClassfyListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.ClassfyOneListData;
import hb.xnwdw.com.wendangwang.netdata.entity.SecondClassfyData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/2/20.
 */

public class FragmentClassfiy extends FragmentBase {
    @BindView(R.id.classfy_grid_titei_tv)
    TextView classfyGridTiteiTv;
    @BindView(R.id.classfy_gridview)
    RecyclerView classfyGridview;
    @BindView(R.id.classfy_list)
    RecyclerView classfyList;
    @BindView(R.id.gif_img)
    SimpleDraweeView gifImg;
    @BindView(R.id.classfy_rootview)
    LinearLayout classfyRootview;
    private GoodsClassfyListAdapter mAdapter;
    private GoodsClassfyGridViewAdapter mAdaptergrid;
    public static int select_item = 0;
    public static int towid = 0;

    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_classfiy;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        classfyRootview.setPadding(0, getStatusHeight(), 0, 0);
        loadGetItemCateOneList();
    }

    /**
     * 分类获取二级类目
     */
    private void loadGetItemCateSecondeList(final int ids) {

        OkHttpUtils
                .get()
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .addParams("oneId", String.valueOf(ids))
                .url(UrlApi.URL_GETCATETWOLIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "加载失败请重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        dismiss();
                        LogUtils.d("FragmentClassfiy_2", response);
                        if (response.startsWith("{")) {
                            final SecondClassfyData datas = JSON.parseObject(response, SecondClassfyData.class);
                            classfyGridview.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                            mAdaptergrid = new GoodsClassfyGridViewAdapter(R.layout.item_goodclassfy_gridv, datas.getObj());
//                            mAdaptergrid.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) classfyGridview.getParent(), false));

                            classfyGridview.setAdapter(mAdaptergrid);
                            mAdaptergrid.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    //原生的搜素结果
                                    Intent intent = new Intent(getActivity(), ShearchResutsActivity.class);
                                    intent.putExtra("TAG", "classfy");
                                    intent.putExtra("TwoCateID", datas.getObj().get(position).getID() + "");
                                    intent.putExtra("OneCateID", datas.getObj().get(position).getParentID() + "");
                                    //      Intent intent = new Intent(getActivity(), ShearchResutsActivityweb.class);
                                    //网页的收索结果
//                                    Intent intent = new Intent(getActivity(), ShearchResutsActivityweb.class);
//                                      intent.putExtra("keyword", "?oneId=" + datas.getObj().get(position).getParentID() + "&twoId=" + datas.getObj().get(position).getID() + "&app=app");
                                    startActivity(intent);
                                }
                            });

                        } else {
                            loadGetItemCateSecondeList(ids);
                        }

                    }
                });
    }

    /**
     * 分类获取一级类目
     */

    private void loadGetItemCateOneList() {
        OkHttpUtils
                .get()
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .url(UrlApi.URL_GETCATEONELIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.d("FragmentClassfiy", "e:" + e);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("FragmentClassfiy_1", response);
                        if (response.startsWith("{")) {
                            final ClassfyOneListData data = JSON.parseObject(response, ClassfyOneListData.class);
                            mAdapter = new GoodsClassfyListAdapter(R.layout.item_classfy, data.getObj());
                            classfyList.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                            classfyList.setAdapter(mAdapter);

                            if (towid == 0) {
                                classfyGridTiteiTv.setText(data.getObj().get(0).getCateName());
                                loadGetItemCateSecondeList(data.getObj().get(0).getID());
                            } else {
                                classfyGridTiteiTv.setText(data.getObj().get(select_item).getCateName());
                                loadGetItemCateSecondeList(towid);
                            }
                            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    select_item = position;
                                    int id = data.getObj().get(position).getID();
                                    towid = id;
                                    classfyGridTiteiTv.setText(data.getObj().get(position).getCateName());
                                    loadGetItemCateSecondeList(id);
                                    mAdapter.notifyDataSetChanged();
                                    view.findViewById(R.id.classfy_line_view).setVisibility(View.VISIBLE);

                                }
                            });
                        } else {
                            loadGetItemCateOneList();
                        }
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("FragmentClassfiy", "towid:" + towid);
        //loadGetItemCateSecondeList(towid);

    }
}
