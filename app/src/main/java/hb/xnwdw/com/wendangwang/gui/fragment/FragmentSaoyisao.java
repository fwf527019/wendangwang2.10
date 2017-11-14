package hb.xnwdw.com.wendangwang.gui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails;
import hb.xnwdw.com.wendangwang.gui.activity.GoodsDetails1;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.gui.activity.OffilineOrder;
import hb.xnwdw.com.wendangwang.gui.activity.ScanActivity;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.Utils;

/**
 * Created by Administrator on 2017/6/23.
 */
public class FragmentSaoyisao extends FragmentBase {
    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_saoma;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //相机权限管理
        AndPermission.with(FragmentSaoyisao.this)
                .requestCode(100)
                .permission(
                        // Multiple permissions, array form.
                        Manifest.permission.CAMERA
                )
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(getContext(), rationale)
                                .show();
                    }
                })
                .callback(new PermissionListener() {
                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        Intent intent = new Intent(getActivity(), ScanActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                        MainPagerActivity.GotoFragment(0);
                    }
                })
                .start();
    }

    static final int REQUEST_CODE = 5;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
        }
        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    LogUtils.d("FragmentMain", result);
                    if (result.contains("/wdw/page/mb/gs_detail.html")) {
                        Intent intent = new Intent(getActivity(),GoodsDetails1.class);
                        intent.putExtra("itemId", Utils.cutString(result, "itemID="));
                        startActivity(intent);
                    } else if (result.contains("/wdw/page/mb/WeChartItem.html")) {
                        Intent intent1 = new Intent(getActivity(), OffilineOrder.class);
                        intent1.putExtra("itemId", Utils.cutString(result, "itemID=", "&storeId"));
                        intent1.putExtra("storeId", Utils.cutString(result, "&storeId="));
                        startActivity(intent1);
                    } else {
                        Toast.makeText(getContext(), "此二维码暂不支持", Toast.LENGTH_SHORT).show();
                        gotoMianPage();
                    }

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                    gotoMianPage();
                }
            }
        }
    }

    private void gotoMianPage(){
        MainPagerActivity.GotoFragment(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        MainPagerActivity.GotoFragment(0);
    }

}
