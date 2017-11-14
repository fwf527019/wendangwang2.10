package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;
import hb.xnwdw.com.wendangwang.utils.WebViewSetings;

/**
 * Created by Administrator on 2017/8/3.
 */

public class GetQiandao_Prize extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.qiandao_prize)
    WebView qiandaoPrize;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_qiandao_prize
                ;
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
        title.setText("签到成功");
        Intent intent = getIntent();
        String id = intent.getStringExtra("ItemID");
        String in = intent.getStringExtra("ItemName");
        String ip = intent.getStringExtra("ItemPic");
        String rid = intent.getStringExtra("RecordID");
        String url = UrlApi.SERVER_IP + "/wdw/page/mb/sign_success2.html?id=" + id + "&in" + in + "&ip" + ip + "&rid" + rid + "&app=app";
        WebViewSetings.synCookies(getApplicationContext(), url);
        WebViewSetings.setWebView(qiandaoPrize, url, getApplicationContext());
        qiandaoPrize.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtils.d("FragmentShoppingCartWeb", url);
                // 判断url链接中是否含有某个字段，如果有就执行指定的跳转（不执行跳转url链接），如果没有就加载url链接
                if (url.contains("/wdw/page/mb/pay.html")) {
                    LogUtils.d("FragmentShoppingCartWeb", url);
                    String OrderNumber = Utils.cutString(url, "(?i)OrderNumber=");
                    LogUtils.d("FragmentShoppingCartWeb", OrderNumber);
                    Intent i = new Intent(GetQiandao_Prize.this, PayActivity.class);
                    i.putExtra("orderNum", OrderNumber);
                    startActivity(i);
                    return true;
                }
                //首页
                else if (url.contains("/wdw/page/mb/index.html")) {
                    MainPagerActivity.GotoFragment(0);
                    return true;
                }
                //分类
                else if (url.contains("/wdw/page/mb/list.html")) {
                    MainPagerActivity.GotoFragment(1);
                    return true;
                }
                //购物车
                else if (url.contains("/wdw/page/mb/shopping_car")) {
                    Intent i = new Intent(GetQiandao_Prize.this, MainPagerActivity.class);
                    i.putExtra("fr", 3);
                    startActivity(i);
                }
                //个人中心
                else if (url.contains("/wdw/page/mb/user_center.html")) {
                    MainPagerActivity.GotoFragment(3);
                    return true;
                } else if (url.contains("/wdw/page/mb/recommend_detail.html")) {
                    String brandId = Utils.cutString(url, "(?i)brandId=");
                    Intent i = new Intent(GetQiandao_Prize.this, BrandDetails.class);
                    i.putExtra("brandId", brandId);
                    startActivity(i);
                    return true;
                }
                //商品详情
                else if (url.contains("/wdw/page/mb/gs_detail.html")) {
                    Intent i = new Intent(GetQiandao_Prize.this,GoodsDetails1.class);
                    String itemId = Utils.cutString(url, "(?i)itemID=");
                    i.putExtra("itemId", itemId);
                    startActivity(i);
                    return true;
                }

                //立即购买确认订单
                if (url.contains("/wdw/page/mb/order")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url);
                    WebViewSetings.setWebView(qiandaoPrize, url, getApplicationContext());
                    title.setText("确认订单");
                    return true;
                }

                //优惠券
                if (url.contains("/wdw/page/mb/my_cpipon.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(qiandaoPrize, url + "&app=app", getApplicationContext());
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("我的优惠券");
                    return true;
                }
                //收货地址
                if (url.contains("/wdw/page/mb/my_addr.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(qiandaoPrize, url + "&app=app", getApplicationContext());
                    title.setText("我的收货地址");
                    return true;
                }
                //门店自提
                if (url.contains("/wdw/page/mb/sel_store.html")) {
                    LogUtils.d("GoodsDetails", url);
                    WebViewSetings.synCookies(getApplicationContext(), url + "&app=app");
                    WebViewSetings.setWebView(qiandaoPrize, url + "&app=app", getApplicationContext());
                    publicTital.setVisibility(View.VISIBLE);
                    title.setText("门店自提");
                    return true;
                }





                return false;
            }
        });
    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
