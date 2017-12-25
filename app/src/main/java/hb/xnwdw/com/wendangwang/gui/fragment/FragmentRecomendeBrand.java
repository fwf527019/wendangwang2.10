package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.BrandDetails;
import hb.xnwdw.com.wendangwang.gui.adapter.PreferredBrandAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.BrandPrefredeData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FragmentRecomendeBrand extends FragmentBase {


    @BindView(R.id.peferredgrand_recyclerview)
    RecyclerView peferredgrandRecyclerview;
    private PreferredBrandAdapter madapter;


    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_preferredbrand;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDatas();


    }

    /**
     * 网络加载品牌推荐数据
     */
    void initDatas() {
        OkHttpUtils
                .get()
                .addHeader("timestamp", UrlUtils.getTime())
                .addHeader("token", UrlUtils.getMd5(UrlUtils.getTime()))
                .url(UrlApi.URL_GETBRANDRECOMENDEINFO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.d("FragmentPreferredBrand", response);
                        if(!response.contains(MConstant.HTTP404)){
                            final BrandPrefredeData data= JSON.parseObject(response,BrandPrefredeData.class);
                            MyLinearLayoutManager myLinearLayoutManager=new MyLinearLayoutManager(getActivity());
                            peferredgrandRecyclerview.setLayoutManager(myLinearLayoutManager);
                            madapter=new PreferredBrandAdapter(R.layout.item_brandrecomende,data.getObj());
                            madapter.addFooterView(getActivity().getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) peferredgrandRecyclerview.getParent().getParent(), false), -1, LinearLayout.VERTICAL);
                            peferredgrandRecyclerview.setAdapter(madapter);
                            madapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent=new Intent(getActivity(), BrandDetails.class);
                                    intent.putExtra("brandId",data.getObj().get(position).getID() );
                                    intent.putExtra("name",data.getObj().get(position).getBrandName() );
                                    intent.putExtra("pic",UrlApi.SERVER_IP+data.getObj().get(position).getBrandPic() );
                                    startActivity(intent);
                                }
                            });
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
}
