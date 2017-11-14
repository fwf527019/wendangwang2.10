package hb.xnwdw.com.wendangwang.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.alibaba.fastjson.JSONObject;
import com.zhy.http.okhttp.callback.StringCallback;

import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.JifenData;
import okhttp3.Call;

/**
 * Created by MJJ on 2015/7/25.
 */
public class Utils {


    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static int dp2px(Context context, float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, context.getResources().getDisplayMetrics());
    }

    /**
     * �屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * 截取String s=初始String  r=从开始的Strin到末尾
     *
     * @param s
     * @return
     */
    public static String cutString(String s, String r) {
        String t[] = s.split(r);
        int i = t.length;
        String e = t[i - 1];
        return e;
    }

    /**
     * 截取 从star到end
     *
     * @param s
     * @param star
     * @param end
     * @return
     */
    public static String cutString(String s, String star, String end) {
        String str[] = s.split(star);
        String sr[] = str[1].split(end);
        return sr[0];
    }

    /**
     * 截取 从star到后n
     *
     * @param s
     * @param star
     * @param n
     * @return
     */
    public static String cutString(String s, String star, int n) {
        String str[] = s.split(star);
        String s1 = str[1].substring(n);
        String s2=str[0]+"."+s1;
        return s2;
    }

    public static boolean isPhone(String mobiles) {
        /**
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1]\\d{10}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
    /**
     * 判断是否登录状态
     */
    static boolean isLogin=false;
    public  static  boolean isLogining(){

        HtttpRequest.CreatGetRequst(UrlApi.URL_CheckMemberLogin, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                if(JSONObject.parseObject(response).get("dataStatus").toString().equals("1")){
                    isLogin=true;
                }
            }
        });
        return isLogin;
    }
}
