package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.BrandDetails;
import hb.xnwdw.com.wendangwang.gui.activity.QianDaoActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.AllBrandAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ListA_ZAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AllBrandData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FragmentAllBrand extends FragmentBase {

    @BindView(R.id.allbrand_letter_list)
    RecyclerView allbrandLetterList;
    @BindView(R.id.allgrand_recycler)
    RecyclerView allgrandRecycler;
    private AllBrandAdapter madapter;
    private GridLayoutManager gridLayoutManager;
    private TreeSet<String> treeSet;
    private List<String> list;
    private ListA_ZAdapter madapter1;

    @Override

    protected int getContentViewResId() {
        return R.layout.fragment_alldbrand;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;


    }

    /**
     * 请求所有品牌数据
     */
    void initDatas() {
        HtttpRequest.CreatGetRequstNoToken(UrlApi.URL_GETALLBRANDINFO, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("FragmentAllBrand", response);
                final AllBrandData allBrandData = JSON.parseObject(response, AllBrandData.class);
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                allgrandRecycler.setLayoutManager(gridLayoutManager);
                madapter = new AllBrandAdapter(R.layout.item_allbrand, allBrandData.getObj());
                madapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) allgrandRecycler.getParent(), false));
                allgrandRecycler.setAdapter(madapter);
                //item点击跳转
                madapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getActivity(), BrandDetails.class);
                        intent.putExtra("brandId", allBrandData.getObj().get(position).getID() + "");
                        intent.putExtra("name", allBrandData.getObj().get(position).getBrandName());
                        intent.putExtra("pic", UrlApi.SERVER_IP + allBrandData.getObj().get(position).getBrandPic());

                        startActivity(intent);
                    }
                });
                //讲品牌的字母添加到list中
                treeSet = new TreeSet<String>();
                for (int i = 0; i < allBrandData.getObj().size(); i++) {
                    treeSet.add(allBrandData.getObj().get(i).getMemo());
                }
                Iterator it = treeSet.iterator();
                list = new ArrayList<String>();
                while (it.hasNext()) {
                    String fruit = (String) it.next();
                    list.add(fruit);
                }

                MyLinearLayoutManager mlmlg = new MyLinearLayoutManager(getActivity());
                allbrandLetterList.setLayoutManager(mlmlg);
                madapter1 = new ListA_ZAdapter(R.layout.item_a_z, list);
                allbrandLetterList.setAdapter(madapter1);
                madapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        int t = 0;
                        LogUtils.d("FragmentAllBrand", "position:" + position);
                        madapter1.setSlectePos(position);
                        for (int i = 0; i < allBrandData.getObj().size(); i++) {
                            if ((allBrandData.getObj().get(i).getMemo()).equals(list.get(position))) {
                                t = i;
                            }
                        }
                        madapter1.notifyDataSetChanged();
                        allgrandRecycler.scrollToPosition(t);
                    }

                });
//                madapter1.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
//                    @Override
//                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                        int t = 0;
//                        madapter1.setSlectePos(position);
//                        madapter1.notifyDataSetChanged();
//                        for (int i = 0; i < allBrandData.getObj().size(); i++) {
//                            if ((allBrandData.getObj().get(i).getMemo()).equals(list.get(position))) {
//                                t = i;
//                            }
//                        }
//                        allgrandRecycler.scrollToPosition(t);
//                        return true;
//                    }
//                });

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
