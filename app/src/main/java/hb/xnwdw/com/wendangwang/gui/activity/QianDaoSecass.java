package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.HotSaleAdapter;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.HotSaleData;
import hb.xnwdw.com.wendangwang.netdata.entity.OrderGoodsInfo;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/15.
 */
public class QianDaoSecass extends ActivityBase {


    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.qiaodao_secsass_num)
    TextView qiaodaoSecsassNum;
    @BindView(R.id.qiaodao_secsass_type)
    TextView qiaodaoSecsassType;
    @BindView(R.id.qiandao_jifen_ll)
    LinearLayout qiandaoJifenLl;
    @BindView(R.id.qiandao_conpoud_item_price)
    TextView qiandaoConpoudItemPrice;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.qiandao_conpoud_item_condition)
    TextView qiandaoConpoudItemCondition;
    @BindView(R.id.lll2)
    LinearLayout lll2;
    @BindView(R.id.qiandao_conpoud_item_youxiaoqi)
    TextView qiandaoConpoudItemYouxiaoqi;
    @BindView(R.id.qiandao_conpoud_item_usetime)
    TextView qiandaoConpoudItemUsetime;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.qiandao_conpoud_name)
    TextView qiandaoConpoudName;
    @BindView(R.id.qiandao_conpoud_item_limit)
    TextView qiandaoConpoudItemLimit;
    @BindView(R.id.qiandao_youhuiquan_relayout)
    RelativeLayout qiandaoYouhuiquanRelayout;
    @BindView(R.id.qiaodao3_name)
    TextView qiaodao3Name;
    @BindView(R.id.qiaodao3_pic)
    SimpleDraweeView qiaodao3Pic;
    @BindView(R.id.qiaodao3_btn)
    TextView qiaodao3Btn;
    @BindView(R.id.qiaodao3_ll)
    LinearLayout qiaodao3Ll;
    @BindView(R.id.qiaodao_secsass_l)
    FrameLayout qiaodaoSecsassL;
    @BindView(R.id.qiaodao_secsass_hotsale)
    RecyclerView qiaodaoSecsassHotsale;
    @BindView(R.id.qiaodao_head_fr)
    LinearLayout qiaodaoHeadFr;
    private HotSaleAdapter adapter;
    private String type = "", Integral = "", CouponMoney = "", ItemID = "", Start, End, Condition, CouponName;
    private String itemId, itemName, itemPic;
    List<OrderGoodsInfo> list = new ArrayList<>();

    @Override
    protected int getContentViewResId() {
        return R.layout.qiaodaosecass;
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
        loadHotSaleData();
        title.setText("签到成功");
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        Integral = intent.getStringExtra("Integral");
        CouponMoney = intent.getStringExtra("CouponMoney");
        ItemID = intent.getStringExtra("ItemID");
        Start = intent.getStringExtra("Start");
        End = intent.getStringExtra("End");
        Condition = intent.getStringExtra("Condition");
        CouponName = intent.getStringExtra("CouponName");
        itemId = intent.getStringExtra("ItemID");
        itemName = intent.getStringExtra("ItemName");
        itemPic = intent.getStringExtra("ItemPic");


//        intent.putExtra("ItemID",daoData.getObj().getItemPic()+"");
//        intent.putExtra("ItemName",daoData.getObj().getItemName()+"");
//        intent.putExtra("ItemPic",daoData.getObj().getItemPic()+"");
//        intent.putExtra("RecordID",daoData.getObj().getRecordID()+"");

        if (type.contains("0")) {
            qiandaoJifenLl.setVisibility(View.VISIBLE);
            qiaodaoSecsassNum.setText(Integral);
            qiaodaoSecsassType.setText("积分");
        }
        if (type.contains("1")) {
            qiandaoYouhuiquanRelayout.setVisibility(View.VISIBLE);
            qiandaoConpoudItemPrice.setText("¥" + CouponMoney);
            qiandaoConpoudItemCondition.setText("满" + CouponMoney + "使用");
            qiandaoConpoudName.setText("");
            qiandaoConpoudItemUsetime.setText(Start.substring(0, 10) + "至" + End.substring(0, 10));
            qiandaoConpoudName.setText(CouponName);
        }
        if (type.contains("2")) {
            qiaodao3Ll.setVisibility(View.VISIBLE);
            qiaodao3Name.setText("恭喜您获得实物奖品:" + itemName);
            qiaodao3Pic.setImageURI(UrlApi.SERVER_IP + itemPic);
            OrderGoodsInfo orderGoodsInfo = new OrderGoodsInfo();
            orderGoodsInfo.setItemName(itemName);
            orderGoodsInfo.setItemId(itemId);
            orderGoodsInfo.setItemPic(itemPic);
            orderGoodsInfo.setAmount(1);
            list.add(orderGoodsInfo);
        }

    }
    /**
     * 热卖推荐
     */
    private void loadHotSaleData() {
        HtttpRequest.CreatGetRequst(UrlApi.URL_GETHOTSALE, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("QianDaoActivity_Hot", response);
                final HotSaleData data = JSON.parseObject(response, HotSaleData.class);
                LinearLayoutManager myLinearLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                myLinearLayoutManager.setSmoothScrollbarEnabled(false);
                qiaodaoSecsassHotsale.setLayoutManager(myLinearLayoutManager);
                adapter = new HotSaleAdapter(R.layout.item_shopcat_historygoods, data.getObj());
                qiaodaoSecsassHotsale.setAdapter(adapter);
                qiaodaoSecsassHotsale.setNestedScrollingEnabled(false);
                adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(getApplicationContext(), GoodsDetails1.class);
                        intent.putExtra("itemId", data.getObj().get(position).getID()+"");
                        startActivity(intent);
                    }
                });
                adapter.addFooterView(QianDaoSecass.this.getLayoutInflater().inflate(R.layout.sub_nomore_item, (ViewGroup) qiaodaoSecsassHotsale.getParent(), false));
            }
        });
    }

    @OnClick({R.id.back, R.id.qiaodao3_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.qiaodao3_btn:
                Intent intent = new Intent(QianDaoSecass.this, OrderBalanceActivity.class);
                intent.putExtra("orderInfo", (Serializable) list);
                intent.putExtra("orderType", 3);
                startActivity(intent);
                break;
        }
    }
}
