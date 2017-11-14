package hb.xnwdw.com.wendangwang.gui.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.activity.AccontinfoActivity;
import hb.xnwdw.com.wendangwang.gui.activity.MainPagerActivity;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.CertfcationInfoData;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;


/**
 * Created by Administrator on 2017/4/12.
 */

public class FragmentCertification extends FragmentBase {

    Unbinder unbinder;
    @BindView(R.id.certification_name)
    EditText certificationName;
    @BindView(R.id.certification_idnum_edt)
    EditText certificationIdnumEdt;
    @BindView(R.id.certification_add_zheng)
    SimpleDraweeView certificationAddZheng;
    @BindView(R.id.certification_add_fan)
    SimpleDraweeView certificationAddFan;
    @BindView(R.id.certification_btn)
    Button certificationBtn;

    private String CardPic1;
    private String CardPic2;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_certification;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCertifacationData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.certification_add_zheng, R.id.certification_add_fan, R.id.certification_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.certification_add_zheng:
                show1(1);

                break;
            case R.id.certification_add_fan:
                show1(3);
                break;
            case R.id.certification_btn:
                if (certificationName.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "请输入姓名", Toast.LENGTH_SHORT).show();
                } else if (certificationIdnumEdt.getText().toString().length() != 18) {
                    Toast.makeText(getActivity(), "请输入正确的身份证号码", Toast.LENGTH_SHORT).show();
                } else if (certificationAddZheng.getDrawable() == null) {
                    Toast.makeText(getActivity(), "选着身份证照片正面", Toast.LENGTH_SHORT).show();
                } else if (certificationAddFan.getDrawable() == null) {
                    Toast.makeText(getActivity(), "选着身份证照片反面", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "提交中", Toast.LENGTH_SHORT).show();
                    certificationBtn.setClickable(false);
                    certificationBtn.setBackgroundResource(R.drawable.btn_bg_gray);
                    commitData();
                }
                break;
        }
    }

    private void commitData() {
        startProgressDialog("提交中...");
        //从控件获取图片的bitmap转化为bas64
        Bitmap bm1 = getBimapByImagView(certificationAddZheng);
        Bitmap bm2 = getBimapByImagView(certificationAddFan);
        //压缩并base64编码
        Bitmap bit1 = compressImage(bm1);
        CardPic1 = HelpUtil.bitmapToBase64(bit1);
        bit1.recycle();
        Bitmap bit2 = compressImage(bm2);
        CardPic2 = HelpUtil.bitmapToBase64(bit2);
        bit2.recycle();

        AuthInfo authInfo = new AuthInfo();
        JSONObject jsonObject = new JSONObject();
        authInfo.setMemberID(WDWApp.getMenberId());
        authInfo.setCardNum(certificationIdnumEdt.getText().toString());
        authInfo.setRealName(certificationName.getText().toString());
        authInfo.setCardPic1("data:image/jpeg;base64," + CardPic1);
        authInfo.setCardPic2("data:image/jpeg;base64," + CardPic2);
        jsonObject.put("dataSource", "Mobile");
        jsonObject.put("authInfo", authInfo);
        String pamas = jsonObject.toJSONString();
        LogUtils.d("FragmentCertification", pamas);
        HtttpRequest.CreatPostRequst(UrlApi.URL_POSTRealAuth, pamas, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("FragmentCertification", "e:" + e);
                stopProgressDialog();
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("FragmentCertification", response);
                if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "提交失败，请重试", Toast.LENGTH_SHORT).show();
                    certificationBtn.setClickable(true);
                    certificationBtn.setBackgroundResource(R.drawable.btn_bg);
                }
            }
        });
    }


    class AuthInfo {
        public String MemberID;
        public String RealName;
        public String CardNum;
        public String CardPic1;
        public String CardPic2;

        public void setMemberID(String memberID) {
            MemberID = memberID;
        }

        public void setRealName(String realName) {
            RealName = realName;
        }

        public void setCardNum(String cardNum) {
            CardNum = cardNum;
        }

        public void setCardPic1(String cardPic1) {
            CardPic1 = cardPic1;
        }

        public void setCardPic2(String cardPic2) {
            CardPic2 = cardPic2;
        }
    }


    private void show1(final int tag) {
        final Dialog bottomDialog = new Dialog(getActivity(), R.style.BottomDialog);
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuwindow_chooseheader, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        LinearLayout chooseheadCamare = (LinearLayout) contentView.findViewById(R.id.choosehead_camare);
        LinearLayout choosheadPhoto = (LinearLayout) contentView.findViewById(R.id.chooshead_photo);
        LinearLayout chooseheadExit = (LinearLayout) contentView.findViewById(R.id.choosehead_exit);
        chooseheadCamare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndPermission.with(FragmentCertification.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(getActivity(), rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                                //  Intent 跳转相机
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, tag);
                                bottomDialog.dismiss();
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                                MainPagerActivity.GotoFragment(0);
                            }
                        })
                        .start();


            }
        });
        choosheadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndPermission.with(FragmentCertification.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(getActivity(), rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                                //  Intent 跳转相机
                                Intent intent = new Intent(Intent.ACTION_PICK,
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, tag + 1);
                                bottomDialog.dismiss();
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(getActivity(), "授权失败", Toast.LENGTH_SHORT).show();
                                MainPagerActivity.GotoFragment(0);
                            }
                        })
                        .start();

            }
        });
        chooseheadExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String url = "";
        Bitmap bitmap = null;
        if (requestCode == 1) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bit = (Bitmap) bundle.get("data");
//                Bitmap bit1 = compressImage(bit);
//                CardPic1 = HelpUtil.bitmapToBase64(bit1);
                certificationAddZheng.setImageBitmap(bit);
            }
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            //       Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ContentResolver cr = getActivity().getContentResolver();
            try {
                Bitmap bit = MediaStore.Images.Media.getBitmap(cr, selectedImage);
//                //图片压缩
//                Bitmap bit1 = compressImage(bit);
//                //bitmap转化64编码
//                CardPic1 = HelpUtil.bitmapToBase64(bit1);
//
                certificationAddZheng.setImageBitmap(bit);
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 3) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle != null) {
                    Bitmap bit = (Bitmap) bundle.get("data");
//                Bitmap bit1 = compressImage(bit);
//                CardPic2 = HelpUtil.bitmapToBase64(bit1);
                    certificationAddFan.setImageBitmap(bit);
                }
            }
        }
        if (requestCode == 4 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getActivity().getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            //       Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ContentResolver cr = getActivity().getContentResolver();
            try {
                Bitmap bit = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                //bitmap转化64编码
//                Bitmap bit1 = compressImage(bit);
//                CardPic2 = HelpUtil.bitmapToBase64(bit1);
                certificationAddFan.setImageBitmap(bit);
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 90;

        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * 获取先前实名信息显示
     */
    private void initCertifacationData() {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "Mobile");
        map.put("memberId", "0");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GetMemberRealAuth, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AccontSafeActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    CertfcationInfoData data = JSON.parseObject(response, CertfcationInfoData.class);
                    if (data.getObj() != null) {
                        certificationName.setText(data.getObj().getRealName());
                        certificationIdnumEdt.setText(data.getObj().getCardNum());
                        certificationAddZheng.setImageURI(UrlApi.SERVER_IP + data.getObj().getCardPic1());
                        certificationAddFan.setImageURI(UrlApi.SERVER_IP + data.getObj().getCardPic2());
                        if (data.getObj().getAuthStatus() == 1) {
                            certificationName.setFocusable(false);
                            certificationName.setEnabled(false);
                            certificationIdnumEdt.setFocusable(false);
                            certificationIdnumEdt.setEnabled(false);
                            certificationAddZheng.setClickable(false);
                            certificationAddFan.setClickable(false);
                        }
                    }

                }
            }
        });
    }

    /**
     * 从Imageview获取Bitmap
     *
     * @param img
     * @return
     */
    private Bitmap getBimapByImagView(ImageView img) {
        img.setDrawingCacheEnabled(true);//获取bm前执行，否则无法获取
        Bitmap bm = img.getDrawingCache();
        //  img.setDrawingCacheEnabled(false);
        return bm;
    }

}
