package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.MyOrderListAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.OrderDetailListAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.MyOrderListData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderDetailData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/21.
 */
public class Offline_hisActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.offline_his_list1)
    RecyclerView offlineHisList1;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_offline_history;
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
        title.setText("历史订单");
        loadDatas();
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


    /**
     * 根据状态获取订单列表
     *
     * @param
     */
    private void loadDatas() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("state", "-10");
        map.put("page", "1");
        map.put("pageSize", "20");
        map.put("memberID", WDWApp.getMenberId());
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETODERLIST, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("MyOrder", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("Offline_hisActivity", response);
                MyLinearLayoutManager linearLayoutManager=new MyLinearLayoutManager(Offline_hisActivity.this);
                MyOrderListData data=JSON.parseObject(response,MyOrderListData.class);
                offlineHisList1.setLayoutManager(linearLayoutManager);
                Adapter adapter=new Adapter(R.layout.item_offline_his,data.getOrders());
                offlineHisList1.setAdapter(adapter);

            }
        });

    }
    class Adapter extends BaseQuickAdapter<MyOrderListData.OrdersBean,BaseViewHolder>{

        public Adapter(int layoutResId, List<MyOrderListData.OrdersBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MyOrderListData.OrdersBean item) {

          String ordernumber=item.getOrderNumber();
            helper
                    .setText(R.id.offline_his_time,item.getOrderDate())
                    .setText(R.id.offline_his_goods_pice,"¥"+item.getPracticalMoney())
                    .setText(R.id.offline_his_goods_num,"×"+item.getItems().size())
                    .setText(R.id.offline_his_goods_name,GetItemName(ordernumber));
            ((SimpleDraweeView)(helper.getView(R.id.offline_his_goods_img))).setImageURI(UrlApi.SERVER_IP+item.getItems().get(0).getItemPic());

        }
    }

    /**
     * 订单详情
     * @param orderNum
     */
   private String itemname;
    private String GetItemName(String orderNum) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("memberId", WDWApp.getMenberId());
        map.put("orderNum", orderNum);
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETODERDETAL, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("OrderDetailsActivity", response);
                OrderDetailData orderDetailData = JSON.parseObject(response, OrderDetailData.class);
                 itemname=orderDetailData.getObj().getOrderDetails().get(0).getItemName();


            }
        });

        return itemname;
    }


}
