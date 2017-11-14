package hb.xnwdw.com.wendangwang.gui.activity;

import android.graphics.Bitmap;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;
import okhttp3.Request;
/**
 * Created by Administrator on 2017/7/17.
 */
public class ImageLoaderUtil {
    /**
     * 获取bitmap
     * @param imageUrl
     * @return
     */
    static Bitmap bitmap;
    public static Bitmap getBitmap(String imageUrl) {

        OkHttpUtils
                .get()
                .url(imageUrl)
                .build()
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(Bitmap response, int id) {
                        bitmap=response;
                    }
                });
        return  bitmap;
    }

}
