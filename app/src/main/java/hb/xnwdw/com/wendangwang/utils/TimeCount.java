package hb.xnwdw.com.wendangwang.utils;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/3/20.
 */

public class TimeCount extends CountDownTimer {
    private TextView textView;

    public TimeCount(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.textView=textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setTextColor(Color.parseColor("#666666"));
        textView.setClickable(false);
        textView.setText("(" + millisUntilFinished / 1000 + ") 秒后可重新发送");
    }

    @Override
    public void onFinish() {
        textView.setText("重新获取验证码");
        textView.setClickable(true);
        textView.setTextColor(Color.parseColor("#ff6c00"));

    }

}
