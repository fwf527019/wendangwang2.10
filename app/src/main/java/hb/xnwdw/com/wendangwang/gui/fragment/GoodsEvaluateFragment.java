package hb.xnwdw.com.wendangwang.gui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.Zoomable.common.ZoomableActivity;
import hb.xnwdw.com.wendangwang.gui.adapter.EvaluateListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodEvaluatData;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsDetailData;
import hb.xnwdw.com.wendangwang.netdata.entity.GoodsEvaluateListData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/5/22.
 */
public class GoodsEvaluateFragment extends FragmentBase {
    @BindView(R.id.goodsevaluate_all)
    TextView goodsevaluateAll;
    @BindView(R.id.goodsevaluate_well)
    TextView goodsevaluateWell;
    @BindView(R.id.goodsevaluate_just)
    TextView goodsevaluateJust;
    @BindView(R.id.goodevaluate_badly)
    TextView goodevaluateBadly;
    @BindView(R.id.goodevaluate_saidan)
    TextView goodevaluateSaidan;
    @BindView(R.id.goodsevaluate_list)
    RecyclerView goodsevaluateList;
    Unbinder unbinder;
    @BindView(R.id.goodevaluat_nodata)
    LinearLayout goodevaluatNodata;
    private String itemId;
    private GoodsDetailData data;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_goodevaluate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        itemId = bundle.getString("itemId");
        loadEvaluateData(itemId);
        loadEvaluateListData(itemId, 0);
        goodsevaluateAll.setBackgroundColor(getResources().getColor(R.color.maincolor));
        goodsevaluateAll.setTextColor(getResources().getColor(R.color.white));
        goodsevaluateAll.setTextSize(28);
        goodsevaluateAll.setText("全部(0)");
        goodsevaluateJust.setText("不错哟(0)");
        goodsevaluateJust.setText("待提高(0)");
        goodevaluateBadly.setText("小失落(0)");
        goodevaluateSaidan.setText("晒图(0)");
        initTextColor(1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    /***
     * 评价详情列表
     *
     * @param id
     * @param grade
     */
    private EvaluateListAdapter adapter;
   private ArrayList<String> mPaths;
    private void loadEvaluateListData(String id, int grade) {
        OkHttpUtils
                .get()
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .addParams("itemId", id)
                .addParams("pageIndex", "1")
                .addParams("grade", String.valueOf(grade))
                .url(UrlApi.URL_GETEVALUATELIST)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("GoodsEvaluateActivity", response);
                        if(!response.contains(MConstant.HTTP404)){
                            final GoodsEvaluateListData data = JSON.parseObject(response, GoodsEvaluateListData.class);
                            if(data.getObj()!=null&&data.getObj().size()!=0){
                                goodsevaluateList.setVisibility(View.VISIBLE);
                                goodevaluatNodata.setVisibility(View.GONE);
                                goodsevaluateList.setLayoutManager(new MyLinearLayoutManager(getActivity()));
                                adapter = new EvaluateListAdapter(R.layout.item_goodsevaluate_list, data.getObj(), getActivity());
                                goodsevaluateList.setAdapter(adapter);

                                adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                                    @Override
                                    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                        mPaths = new ArrayList<>();
                                        String str =  data.getObj().get(position).getUpPics();
                                        if(str!=null&&!str.equals("")){
                                            String[] split = str.split(";");
                                            for (int i = 0; i <split.length ; i++) {
                                                mPaths.add(UrlApi.SERVER_IP+split[i]);
                                            }
                                        }else {

                                        }
                                        ZoomableActivity.goToPage(getActivity(),mPaths,0);

                                        return false;

                                    }
                                });

                                adapter.notifyDataSetChanged();
                            }else {
                                goodsevaluateList.setVisibility(View.GONE);
                                goodevaluatNodata.setVisibility(View.VISIBLE);

                            }

                        }

                    }
                });

    }

    private void loadEvaluateData(String id) {

        Map<String, String> map = new HashMap<>();
        map.put("itemId", id);

        HtttpRequest.CreatGetRequst(UrlApi.URL_GETEVALUATE, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("GoodsEvaluateFragment_s", response);
                GoodEvaluatData data = JSON.parseObject(response, GoodEvaluatData.class);
                if (data.getObj().getInfo().size() != 0) {
                    goodsevaluateAll.setText("全部(" + data.getObj().getCount() + ")");
                    for (int i = 0; i < data.getObj().getInfo().size(); i++) {
                        if (data.getObj().getInfo().get(i).getCommentDesc().equals("不错呀")) {
                            goodsevaluateWell.setText("不错哟(" + data.getObj().getInfo().get(i).getCommentNum() + ")");
                        }
                        if (data.getObj().getInfo().get(i).getCommentDesc().equals("待提高")) {
                            goodsevaluateJust.setText("待提高(" + data.getObj().getInfo().get(i).getCommentNum() + ")");
                        }
                        if (data.getObj().getInfo().get(i).getCommentDesc().equals("小失落")) {
                            goodevaluateBadly.setText("小失落(" + data.getObj().getInfo().get(i).getCommentNum() + ")");
                        }
                        if (data.getObj().getInfo().get(i).getCommentDesc().equals("晒图")) {
                            goodevaluateSaidan.setText("晒图(" + data.getObj().getInfo().get(i).getCommentNum() + ")");
                        }
                    }
                }

            }
        });
    }


    @OnClick({R.id.goodevaluate_saidan, R.id.goodsevaluate_all, R.id.goodsevaluate_well, R.id.goodsevaluate_just, R.id.goodevaluate_badly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goodsevaluate_all:
                loadEvaluateListData(itemId, 0);
                initTextColor(1);
                break;
            case R.id.goodsevaluate_well:
                initTextColor(2);
                loadEvaluateListData(itemId, 1);
                break;
            case R.id.goodsevaluate_just:
                initTextColor(3);
                loadEvaluateListData(itemId, 2);
                break;
            case R.id.goodevaluate_badly:
                initTextColor(4);
                loadEvaluateListData(itemId, 3);
                break;
            case R.id.goodevaluate_saidan:
                initTextColor(5);
                loadEvaluateListData(itemId, 4);
                break;
        }
    }

    private void initTextColor(int pos) {
        TextView[] views1 = {goodsevaluateAll, goodsevaluateWell, goodsevaluateJust, goodevaluateBadly, goodevaluateSaidan};

        for (int i = 0; i < 5; i++) {
            if (pos - 1 == i) {
                views1[i].setTextSize(14);
                views1[i].setTextColor(getResources().getColor(R.color.white));
                views1[i].setBackgroundResource(R.drawable.kuang_ovals_mainc);
            } else {
                views1[i].setTextSize(13);
                views1[i].setTextColor(getResources().getColor(R.color.gry));
                views1[i].setBackgroundResource(R.drawable.kuang_oval);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
