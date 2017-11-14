package hb.xnwdw.com.wendangwang.CallBack;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/8/28.
 */

public abstract class MyStringCallback extends Callback<String> {

    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
            if(!response.body().string().contains(MConstant.HTTP404)){
                return response.body().string();
            }else {
                return null;
            }

    }
}
