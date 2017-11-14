package hb.xnwdw.com.wendangwang.utils;

import android.content.Context;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;

/**
 * Created by Administrator on 2017/6/14.
 */

public class WebViewSetings  {
    static Context context;
    public static void setWebView(WebView webView,String url,Context context){
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setDomStorageEnabled(true);

        settings.setSupportZoom(true);// 支持缩放
       settings.setLoadWithOverviewMode(true);// 缩放至屏幕的大小
//        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//支持缓存
          settings.setLoadsImagesAutomatically(true);//支持自动加载图片
        synCookies(context,url);
        webView.loadUrl(url);
    }
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        LogUtils.d("FragmentShoppingCartWeb", "userToken=" + WDWApp.getUserToken());
        cookieManager.setCookie(url, "userToken=" + WDWApp.getUserToken());//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }
}
