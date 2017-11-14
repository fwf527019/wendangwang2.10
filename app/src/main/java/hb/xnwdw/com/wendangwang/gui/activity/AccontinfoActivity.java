package hb.xnwdw.com.wendangwang.gui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.TimePickerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.AccontinInfoData;
import hb.xnwdw.com.wendangwang.utils.HelpUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/4/11.
 */
public class AccontinfoActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.accontinfo_save)
    TextView accontinfoSave;
    @BindView(R.id.accontinfo_head)
    LinearLayout accontinfoHead;
    @BindView(R.id.accontinfo_name)
    LinearLayout accontinfoName;
    @BindView(R.id.accontinfo_sexy)
    LinearLayout accontinfoSexy;
    @BindView(R.id.accontinfo_birthday)
    LinearLayout accontinfoBirthday;
    @BindView(R.id.accontinfo_birthday_tv)
    TextView accontinfoBirthdayTv;
    @BindView(R.id.accontinfo_sex_tv)
    TextView accontinfoSexTv;
    @BindView(R.id.choosehead_img)
    SimpleDraweeView chooseheadImg;
    @BindView(R.id.accontinfo_user_name)
    EditText accontinfoUserName;

    private String pic64;
    private String sex;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_accontinfo;
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
        title.setText("账户信息");
        initTimePicker();
        loadInfo();
        accontinfoUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccontinfoActivity.this, EditName.class);
                intent.putExtra("name", accontinfoUserName.getText().toString());
                startActivityForResult(intent, 5);

            }
        });
    }

    /**
     * 获取会员信息
     */
    private void loadInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("dataSource", "Mobile");
        map.put("memberId", WDWApp.getMenberId());
        HtttpRequest.CheackIsLoginGet(this,UrlApi.URL_GETMENBERINFO, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AccontinfoActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    JSONObject jsonObject = JSON.parseObject(response);
                    if (jsonObject.get("obj") != null) {
                        AccontinInfoData accontinInfoData = JSON.parseObject(response, AccontinInfoData.class);
                        accontinfoBirthdayTv.setText(accontinInfoData.getObj().getBirthday().substring(0, 10));
                        accontinfoUserName.setText(accontinInfoData.getObj().getMemberName());
                        accontinfoSexTv.setText(accontinInfoData.getObj().getSex());
                        chooseheadImg.setImageURI(UrlApi.SERVER_IP + accontinInfoData.getObj().getHeadFace());
                    }
                }
            }
        });
    }

    @OnClick({R.id.back, R.id.accontinfo_save, R.id.accontinfo_head, R.id.accontinfo_name, R.id.accontinfo_sexy, R.id.accontinfo_birthday})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.accontinfo_save:
                commitData();
                break;
            case R.id.accontinfo_head:
                showHeader();
                break;
            case R.id.accontinfo_name:
                break;
            case R.id.accontinfo_sexy:
                //showSexPopuwindow();
                show1();
                break;
            case R.id.accontinfo_birthday:
                pvTime.show();
                break;
        }
    }

    /**
     * 选性别
     */
    private void show1() {
        LinearLayout linearLayout1;
        View.OnClickListener listener;
        LinearLayout linearLayout2;
        LinearLayout linearLayout3;
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.popuwindow_choosesex, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(true);
        final View v1 = contentView.findViewById(R.id.sex_view1);
        final View v2 = contentView.findViewById(R.id.sex_view2);
        final View v3 = contentView.findViewById(R.id.sex_view3);
        linearLayout1 = ((LinearLayout) (contentView.findViewById(R.id.choosesex_man)));
        linearLayout2 = ((LinearLayout) (contentView.findViewById(R.id.choosesex_woman)));
        linearLayout3 = ((LinearLayout) (contentView.findViewById(R.id.choosesex_no)));
        final View[] views = {v1, v2, v3};
        final LinearLayout[] linerl = {linearLayout2, linearLayout1, linearLayout3};
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.choosesex_man:
                        initCorlor(2, views, linerl);
                        sex = "男";
                        break;
                    case R.id.choosesex_woman:
                        initCorlor(1, views, linerl);
                        sex = "女";
                        break;
                    case R.id.choosesex_no:
                        initCorlor(3, views, linerl);
                        sex = "保密";
                        break;
                    case R.id.choose_sex_ok:
                        accontinfoSexTv.setText(sex);
                        bottomDialog.dismiss();
                        break;
                    case R.id.choose_sex_exit:
                        bottomDialog.dismiss();
                        break;
                }
            }
        };
        linearLayout1.setOnClickListener(listener);
        linearLayout2.setOnClickListener(listener);
        linearLayout3.setOnClickListener(listener);
        ((TextView) (contentView.findViewById(R.id.choose_sex_ok))).setOnClickListener(listener);
        ((TextView) (contentView.findViewById(R.id.choose_sex_exit))).setOnClickListener(listener);
        View rootview = LayoutInflater.from(AccontinfoActivity.this).inflate(R.layout.activity_accontinfo, null);

        bottomDialog.show();

    }


    private void initCorlor(int pos, View[] views1, LinearLayout[] lilays) {
        for (int i = 0; i < 3; i++) {
            if (pos - 1 == i) {
                views1[i].setVisibility(View.VISIBLE);
                lilays[i].setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                views1[i].setVisibility(View.INVISIBLE);
                lilays[i].setBackgroundColor(getResources().getColor(R.color.f6));
            }
        }

    }

    /**
     * 选头像
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
                //相机权限管理
                AndPermission.with(AccontinfoActivity.this)
                        .requestCode(100)
                        .permission(
                                // Multiple permissions, array form.
                                Manifest.permission.CAMERA
                        )
                        .rationale(new RationaleListener() {
                            @Override
                            public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                                AndPermission.rationaleDialog(AccontinfoActivity.this, rationale)
                                        .show();
                            }
                        })
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

                                //  Intent 跳转相机
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(intent, 1);
                                bottomDialog.dismiss();
                            }
                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                                Toast.makeText(AccontinfoActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
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

    /**
     * 修改会员信息
     */
    private void commitData() {
        startProgressDialog("保存中...");
        String sex = accontinfoSexTv.getText().toString();
        String name = accontinfoUserName.getText().toString();
        String birth = accontinfoBirthdayTv.getText().toString();

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setSex(sex);
        memberInfo.setBirthday(birth);
        memberInfo.setMemberID("10");
        memberInfo.setMemberName(name);
        if (pic64 != null) {
            memberInfo.setHeadFace("data:image/jpeg;base64," + pic64);
        } else {
            Bitmap bitmap = getBimapByImagView(chooseheadImg);
            Bitmap bitmap1 = compressImage(bitmap);
            String pic = HelpUtil.bitmapToBase64(bitmap1);
            memberInfo.setHeadFace("data:image/jpeg;base64," + pic);
            bitmap.recycle();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("memberInfo", memberInfo);
        jsonObject.put("dataSource", "Mobile");
        String pamstring = jsonObject.toJSONString();
        LogUtils.d("AccontinfoActivity", pamstring);
        HtttpRequest.CreatPostRequst(UrlApi.URL_PUTMENBERINFO, pamstring, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("AccontinfoActivity", "e:" + e);
                stopProgressDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                stopProgressDialog();
                LogUtils.d("AccontinfoActivity", response);
                if (JSONObject.parseObject(response).get("Success").toString().equals("true")) {
                    Toast.makeText(AccontinfoActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AccontinfoActivity.this, JSONObject.parseObject(response).get("ErrMSG").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private TimePickerView pvTime;

    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 0, 0);

        Calendar endDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        LogUtils.d("AccontinfoActivity", str);

        int year = Integer.parseInt(str.substring(0, 4));
        int month = Integer.parseInt(str.substring(5, 7));
        int day = Integer.parseInt(str.substring(8, 10));
        endDate.set(year, month - 1, day);
        //时间选择器
        pvTime = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null

                /*btn_Time.setText(getTime(date));*/
//                Button btn = (Button) v;
//                btn.setText(getTime(date));
                accontinfoBirthdayTv.setText(getTime(date));
            }
        })
                .setType(TimePickerView.Type.YEAR_MONTH_DAY)
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .build();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        //    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }


    class MemberInfo {

        String MemberID;
        String HeadFace;
        String MemberName;
        String Sex;
        String Birthday;

        public String getMemberID() {
            return MemberID;
        }

        public void setMemberID(String memberID) {
            MemberID = memberID;
        }

        public String getHeadFace() {
            return HeadFace;
        }

        public void setHeadFace(String headFace) {
            HeadFace = headFace;
        }

        public String getMemberName() {
            return MemberName;
        }

        public void setMemberName(String memberName) {
            MemberName = memberName;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String sex) {
            Sex = sex;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String birthday) {
            Birthday = birthday;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String url = "";
        Bitmap bitmap = null;
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                Bitmap bm = (Bitmap) bundle.get("data");
                Bitmap bit = HelpUtil.makeRoundCorner(bm);
                pic64 = HelpUtil.bitmapToBase64(bit);
                chooseheadImg.setImageBitmap(bit);
            }
        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            Toast.makeText(this, imagePath, Toast.LENGTH_SHORT).show();
            //       Bitmap bm = BitmapFactory.decodeFile(imagePath);
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(cr, selectedImage);
                //bitimap 圆形化
                Bitmap bit = HelpUtil.makeRoundCorner(bm);
                //bitmap转化64编码
                pic64 = HelpUtil.bitmapToBase64(bit);
                chooseheadImg.setImageBitmap(bit);
                c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 5 && data != null) {
            Bundle b = data.getExtras(); //data为B中回传的Intent
            String str = b.getString("name");//str即为回传的值
            accontinfoUserName.setText(str);
        }
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

        while (baos.toByteArray().length / 1024 > 50) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset(); // 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
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
}
