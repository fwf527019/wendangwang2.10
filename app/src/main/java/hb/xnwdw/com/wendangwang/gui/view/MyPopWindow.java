package hb.xnwdw.com.wendangwang.gui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/9/28.
 */

public class MyPopWindow {
    private Context context;
    private int themeResId;
    private MyPopWindow myPopWindow;
    private String left, right;
    private RightClick rightClick;

    public void setRightClick(RightClick rightClick) {
        this.rightClick = rightClick;
    }

    public MyPopWindow(Context context, int themeResId, String left, String right) {
        this.context = context;
        this.themeResId = themeResId;
        this.left = left;
        this.right = right;
        final Dialog bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(themeResId, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width =context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.CENTER);
        bottomDialog.setCanceledOnTouchOutside(true);
       // bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        TextView left_tv = (TextView) contentView.findViewById(R.id.car_addtocloocte);
        TextView right_tv = (TextView) contentView.findViewById(R.id.car_delecte);
        left_tv.setText(left);
        right_tv.setText(right);
        bottomDialog.show();
        left_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        right_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightClick.rightClickListerner();
                bottomDialog.dismiss();
            }
        });

    }

    public interface RightClick {
        void rightClickListerner();
    }
}
//    final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
//    View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindow_choosenum, null);
//bottomDialog.setContentView(contentView);
//        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
//        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
//
//        contentView.setLayoutParams(layoutParams);
//        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
//        bottomDialog.setCanceledOnTouchOutside(true);
//        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);