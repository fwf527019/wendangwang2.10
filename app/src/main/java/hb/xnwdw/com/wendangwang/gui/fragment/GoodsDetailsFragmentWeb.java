package hb.xnwdw.com.wendangwang.gui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentBase;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.LogUtils;

/**
 * Created by Administrator on 2017/3/16.
 */

public class GoodsDetailsFragmentWeb extends FragmentBase {

    Unbinder unbinder;
    String itemId;
    private WebView mywebGoodsdetail;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_goodsdetailsweb;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        itemId = bundle.getString("itemId");
        //设置webview
        WebSettings settings = mywebGoodsdetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(true);// 不支持缩放
        settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//支持缓存
        settings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        String url = UrlApi.SERVER_IP + "/wdw/page/mb/gs_detail.html?itemID=" + itemId;
        synCookies(getActivity(), url);
        mywebGoodsdetail.loadUrl(url);

    }


    /**
     * 同步一下cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        LogUtils.d("FragmentShoppingCartWeb", "userToken=" + WDWApp.getUserToken());
        cookieManager.setCookie(url, "userToken=" + WDWApp.getUserToken());//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
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
        if (mywebGoodsdetail != null) {
            mywebGoodsdetail.clearHistory();
            ((ViewGroup) mywebGoodsdetail.getParent()).removeView(mywebGoodsdetail);
            mywebGoodsdetail.loadUrl("about:blank");
            mywebGoodsdetail.stopLoading();
            mywebGoodsdetail.setWebChromeClient(null);
            mywebGoodsdetail.setWebViewClient(null);
            mywebGoodsdetail.destroy();
            mywebGoodsdetail = null;
        }
    }
}
