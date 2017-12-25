package hb.xnwdw.com.wendangwang.gui.activity;

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
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.fragment.FragmentSaoyisao;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/1.
 */

public class PublishEvaluateActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.push_eva_star)
    RatingBar pushEvaStar;
    @BindView(R.id.push_eva_tv1)
    TextView pushEvaTv1;
    @BindView(R.id.push_eva_img_ll)
    LinearLayout pushEvaImgLl;
    @BindView(R.id.push_eva_btn)
    Button pushEvaBtn;
    @BindView(R.id.push_eva_edt)
    EditText pushEvaEdt;
    @BindView(R.id.push_eva_add_img)
    ImageView pushEvaAddImg;
    private String iItemID, iOrderID;
    private List<String> piclist;
    private Bitmap bitmap;
    private String pic64;
    private int imgNum = 0, starnum = 5;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_publishevaluate;
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
        title.setText("发表评价");
        Intent intent = getIntent();
        iItemID = intent.getStringExtra("iItemID");
        Log.d("PublishEvaluateActivity", iItemID);

        iOrderID = intent.getStringExtra("iOrderID");

        Log.d("PublishEvaluateActivity", iOrderID);

        piclist = new ArrayList<>();
        pushEvaStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int) rating) {
                    case 0:
                        pushEvaTv1.setText("小失落");
                        starnum = 0;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                    case 1:
                        pushEvaTv1.setText("小失落");
                        starnum = 1;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                    case 2:
                        pushEvaTv1.setText("小失落");

                        starnum = 2;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                    case 3:
                        pushEvaTv1.setText("待提高");
                        starnum = 3;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                    case 4:
                        pushEvaTv1.setText("不错呀");
                        starnum = 4;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                    case 5:
                        pushEvaTv1.setText("不错呀");
                        starnum = 5;
                        Log.d("PublishEvaluateActivity", "starnum:" + starnum);
                        break;
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.push_eva_btn, R.id.push_eva_add_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.push_eva_add_img:
                if (imgNum < 4) {
                    showHeader();
                } else {
                    Toast.makeText(this, "最多只能上传4张图片", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.push_eva_btn:
                if (pushEvaEdt.getText().toString() != null && !pushEvaEdt.getText().toString().equals("")) {
                    commitData();
                } else {
                    Toast.makeText(this, "请输入评价内容", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    private void commitData() {
        startProgressDialog("提交中...");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sMemberID", WDWApp.getMenberId());
        jsonObject.put("iItemID", iItemID);
        jsonObject.put("iOrderID", iOrderID);
        jsonObject.put("iSatisfaction", starnum);
        jsonObject.put("img", piclist);
        jsonObject.put("sCommContent", pushEvaEdt.getText().toString());
        String pamString = jsonObject.toJSONString();
        Log.d("PublishEvaluateActivity", pamString);
        HtttpRequest.CheackIsLoginPOST(this,UrlApi.URL_Evaluate, pamString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                Toast.makeText(PublishEvaluateActivity.this, "网络错误，请重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("PublishEvaluateActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        Toast.makeText(PublishEvaluateActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(PublishEvaluateActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(PublishEvaluateActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    /**
     * 选图片
     */
    long start_time, end_time;

    private void showHeader() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindow_chooseheader, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        LinearLayout chooseheadCamare = (LinearLayout) contentView.findViewById(R.id.choosehead_camare);
        LinearLayout choosheadPhoto = (LinearLayout) contentView.findViewById(R.id.chooshead_photo);
        LinearLayout chooseheadExit = (LinearLayout) contentView.findViewById(R.id.choosehead_exit);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        chooseheadCamare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent 跳转相机

                AndPermission.with(PublishEvaluateActivity.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(PublishEvaluateActivity.this, rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                bottomDialog.dismiss();
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(PublishEvaluateActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                                MainPagerActivity.GotoFragment(0);
                            }
                        })
                        .start();


            }
        });
        ///系统相册
        choosheadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                startActivityForResult(intent, 2);

                bottomDialog.dismiss();
            }
        });
        chooseheadExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        start_time = System.currentTimeMillis();
        String picurl = "";
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bit = (Bitmap) bundle.get("data");
                bitmap = compressImage(bit);
                pic64 = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bitmap);
                piclist.add(pic64);
                Log.d("PublishEvaluateActivity", "piclist:" + piclist);
                ImageView img = new ImageView(PublishEvaluateActivity.this);
                img.setLayoutParams(new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
                img.setImageBitmap(bitmap);
                final FrameLayout frameLayout = new FrameLayout(PublishEvaluateActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
                frameLayout.addView(img);
                TextView textView = new TextView(PublishEvaluateActivity.this);
                textView.setText("点击取消");
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTextSize(8);
                textView.setGravity(Gravity.CENTER);
                frameLayout.addView(textView);
                pushEvaImgLl.addView(frameLayout);
                imgNum += 1;
                if (imgNum >= 4) {
                    pushEvaAddImg.setVisibility(View.GONE);
                } else {
                    pushEvaAddImg.setVisibility(View.VISIBLE);
                }
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pushEvaImgLl.removeView(frameLayout);
                        imgNum -= 1;
                        if (imgNum >= 4) {
                            pushEvaAddImg.setVisibility(View.GONE);
                        } else {
                            pushEvaAddImg.setVisibility(View.VISIBLE);
                        }
                    }
                });

            }
        }

        /****获取系统相册*****/
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            setPic(selectedImage);

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

        while ((baos.toByteArray().length / 1024 > 500) && options > 10) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    private void setPic(Uri uri) {
        ImageView imageView = new ImageView(PublishEvaluateActivity.this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
        final FrameLayout frameLayout1 = new FrameLayout(PublishEvaluateActivity.this);
        frameLayout1.setLayoutParams(new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
        TextView textView = new TextView(PublishEvaluateActivity.this);
        textView.setText("点击取消");
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(8);
        textView.setGravity(Gravity.CENTER);




        if (PublishEvaluateActivity.this != null) {
            //获取目标控件的大小
            int targetW = dip2px(getApplicationContext(), 70);
            int targetH = dip2px(getApplicationContext(), 70);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            try {
                //inJustDecodeBounds为true，可以加载源图片的尺寸大小，decodeStream方法返回的bitmap为null
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(PublishEvaluateActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);
                // 得到源图片的尺寸
                int photoW = bmOptions.outWidth;
                Log.d("PublishEvaluateActivity", "photoW:" + photoW);
                int photoH = bmOptions.outHeight;
                Log.d("PublishEvaluateActivity", "photoH:" + photoH);
                //通过比较获取较小的缩放比列
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
                // 将inJustDecodeBounds置为false，设置bitmap的缩放比列
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;
                //再次decode获取bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(PublishEvaluateActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);
                imageView.setImageBitmap(bitmap);
                frameLayout1.addView(imageView);
                frameLayout1.addView(textView);
                pushEvaImgLl.addView(frameLayout1);
                pic64 = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bitmap);
                piclist.add(pic64);
                imgNum += 1;
                if (imgNum >= 4) {
                    pushEvaAddImg.setVisibility(View.GONE);
                } else {
                    pushEvaAddImg.setVisibility(View.VISIBLE);
                }

                frameLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pushEvaImgLl.removeView(frameLayout1);
                        imgNum -= 1;
                        if (imgNum >= 4) {
                            pushEvaAddImg.setVisibility(View.GONE);
                        } else {
                            pushEvaAddImg.setVisibility(View.VISIBLE);
                        }
                    }
                });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
