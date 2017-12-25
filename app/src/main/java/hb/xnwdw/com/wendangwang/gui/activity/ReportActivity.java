package hb.xnwdw.com.wendangwang.gui.activity;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AdvanceType;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import okhttp3.Call;


/**
 * Created by Administrator on 2017/12/15.
 */
public class ReportActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_tv)
    TextView rightTv;
    @BindView(R.id.public_tital)
    RelativeLayout publicTital;
    @BindView(R.id.spnner)
    Spinner spnner;
    @BindView(R.id.type)
    LinearLayout type;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.sever_img_ll)
    LinearLayout severImgLl;
    @BindView(R.id.sever_addimg)
    ImageView severAddimg;
    @BindView(R.id.sever_btn)
    Button severBtn;
    @BindView(R.id.stitle1)
    TextView stitle1;
    private String pic64;
    private List<String> piclist;
    private Bitmap bitmap;
    private int num = 1, imgnum = 0;
    private String complaintType;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_advance;
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
        piclist = new ArrayList<>();
        title.setText("举报");
        stitle1.setText("举报类型");
        initSpner();

    }

    @OnClick({R.id.back, R.id.sever_addimg, R.id.sever_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

            case R.id.sever_addimg:
                if (imgnum < 3) {
                    showHeader();
                } else {
                    Toast.makeText(this, "最多上传3张图片", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sever_btn:
                if (content.getText().toString().length() != 0) {
                    commitData();
                } else {
                    Toast.makeText(this, "请输入举报的内容", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * 提交数据
     */
    private void commitData() {
        String picsStr = "";
        String str = "";
        StringBuffer strbf = new StringBuffer();
        if (piclist.size() != 0) {
            for (int i = 0; i < piclist.size(); i++) {
                strbf.append(piclist.get(i) + "|");
            }
            picsStr = strbf.toString();
            int lenth = picsStr.length();
            str = picsStr.substring(0, lenth);
        }
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("ComplaintType", complaintType);
        jsonObj.put("Describe", content.getText().toString());
        jsonObj.put("UpPics", str);
        jsonObj.put("saveType", 1);
        Log.d("AdvanceActivity", jsonObj.toJSONString());
        startProgressDialog("提交中...");
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_SubmitComSug, jsonObj.toJSONString(), new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {
                Log.d("AdvanceActivity", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                Log.d("AdvanceActivity", response);
                if(JSONObject.parseObject(response).get("dataStatus").toString().equals("1")){
                    Toast.makeText(ReportActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ReportActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    /**
     * 选图片
     */
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


                //相机权限管理
                AndPermission.with(ReportActivity.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(ReportActivity.this, rationale)
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
                                Toast.makeText(ReportActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
                                MainPagerActivity.GotoFragment(0);
                            }
                        })
                        .start();

            }
        });


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
        String url = "";
        /***相机拍照***/
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bit = (Bitmap) bundle.get("data");
                bitmap = compressImage(bit);
                pic64 = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bitmap);
                piclist.add(pic64);
                Log.d("ApplyCustviceActivity", "piclist:" + piclist);
                ImageView img = new ImageView(ReportActivity.this);
                img.setLayoutParams(new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
                img.setImageBitmap(bitmap);
                final FrameLayout frameLayout = new FrameLayout(ReportActivity.this);
                frameLayout.setLayoutParams(new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
                frameLayout.addView(img);
                TextView textView = new TextView(ReportActivity.this);
                textView.setText("点击取消");
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setTextSize(8);
                textView.setGravity(Gravity.CENTER);
                frameLayout.addView(textView);
                severImgLl.addView(frameLayout);
                imgnum += 1;
                if (imgnum >= 3) {
                    severAddimg.setVisibility(View.GONE);
                } else {
                    severAddimg.setVisibility(View.VISIBLE);
                }
                frameLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        severImgLl.removeView(frameLayout);
                        imgnum -= 1;
                        if (imgnum >= 3) {
                            severAddimg.setVisibility(View.GONE);
                        } else {
                            severAddimg.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }
        /***系统相册****/
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

        while ((baos.toByteArray().length / 1024 > 1024) && (options > 10)) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    private void setPic(Uri uri) {
        ImageView imageView = new ImageView(ReportActivity.this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));

        final FrameLayout frameLayout1 = new FrameLayout(ReportActivity.this);
        frameLayout1.setLayoutParams(new FrameLayout.LayoutParams(dip2px(getApplicationContext(), 70), dip2px(getApplicationContext(), 70)));
        TextView textView = new TextView(ReportActivity.this);
        textView.setText("点击取消");
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(8);
        textView.setGravity(Gravity.CENTER);


        if (ReportActivity.this != null) {
            //获取目标控件的大小
            int targetW = dip2px(getApplicationContext(), 70);
            int targetH = dip2px(getApplicationContext(), 70);

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            try {
                //inJustDecodeBounds为true，可以加载源图片的尺寸大小，decodeStream方法返回的bitmap为null
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(ReportActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);
                // 得到源图片的尺寸
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;
                //通过比较获取较小的缩放比列
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
                // 将inJustDecodeBounds置为false，设置bitmap的缩放比列
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;
                //再次decode获取bitmap
                Bitmap bitmap = BitmapFactory.decodeStream(ReportActivity.this.getContentResolver().openInputStream(uri), null, bmOptions);
                imageView.setImageBitmap(bitmap);
                frameLayout1.addView(imageView);
                frameLayout1.addView(textView);
                severImgLl.addView(frameLayout1);
                pic64 = "data:image/jpeg;base64," + HelpUtil.bitmapToBase64(bitmap);
                piclist.add(pic64);
                imgnum += 1;
                if (imgnum >= 3) {
                    severAddimg.setVisibility(View.GONE);
                } else {
                    severAddimg.setVisibility(View.VISIBLE);
                }
                frameLayout1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        severImgLl.removeView(frameLayout1);
                        imgnum -= 1;
                        if (imgnum >= 3) {
                            severAddimg.setVisibility(View.GONE);
                        } else {
                            severAddimg.setVisibility(View.VISIBLE);
                        }
                    }
                });

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    /***
     * 获取到举报类型
     */
    private List<String> InformTypeList;
    private List<String> CodeTypeNameList;

    private void initSpner() {

        InformTypeList = new ArrayList<>();
        CodeTypeNameList = new ArrayList<>();

        HtttpRequest.CheackIsLoginGet(ReportActivity.this, UrlApi.URL_LoadInformTypeList, null, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Toast.makeText(ReportActivity.this, "error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("AdvanceActivity", response);
                AdvanceType data = JSON.parseObject("{data:" + response + "}", AdvanceType.class);

//                JSONArray jsonArray = JSON.parseArray(response);
//
//
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    JSONObject jsonObj = (JSONObject) jsonArray.get(i);
//                    InformTypeList.add(jsonObj.get("InformType").toString());
//                    CodeTypeNameList.add(jsonObj.get("CodeTypeName").toString());
//                }
//
                if (data.getData().size() != 0) {

                    complaintType = data.getData().get(0).getCode();
                    String[] mItems = new String[data.getData().size()];

                    for (int i = 0; i < data.getData().size(); i++) {
                        mItems[i] = data.getData().get(i).getCodeName();
                        InformTypeList.add(data.getData().get(i).getCode());
                    }


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ReportActivity.this, android.R.layout.simple_spinner_item, mItems);
                    spnner.setAdapter(adapter);
                }
                spnner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        complaintType = InformTypeList.get(pos);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Another interface callback
                    }
                });
            }
        });

    }
}


