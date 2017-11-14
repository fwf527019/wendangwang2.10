package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.Basic_RecAddressBean;
import hb.xnwdw.com.wendangwang.netdata.entity.MyAdrasseData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.GetJsonDataUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.JsonBean;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/4/28.
 */
public class EdtAdrassActivity extends ActivityBase {
    @BindView(R.id.edtadrasse_back)
    ImageView edtadrasseBack;
    @BindView(R.id.edtadrasse_title)
    TextView edtadrasseTitle;
    @BindView(R.id.edtadrasse_finish)
    TextView edtadrasseFinish;
    @BindView(R.id.edtadrasse_name)
    EditText edtadrasseName;
    @BindView(R.id.edtadrasse_phone_edt)
    EditText edtadrassePhoneEdt;
    @BindView(R.id.edtadrasse_provenc)
    TextView edtadrasseProvenc;
    @BindView(R.id.edtadrasse_city)
    TextView edtadrasseCity;
    @BindView(R.id.edtadrasse_county)
    TextView edtadrasseCounty;
    @BindView(R.id.edtadrasse_choosadraess)
    LinearLayout edtadrasseChoosadraess;
    @BindView(R.id.detail_adrassee)
    EditText detailAdrassee;
    @BindView(R.id.edtadrasse_setmoren)
    CheckBox edtadrasseSetmoren;
    private String id;
    private int defaltPrv, defaltCity, defaltConty;

    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private ArrayList<JsonBean> options1Items = new ArrayList<>();

    private int positions1;
    private int positions2;
    private int positions3;
    private Intent intent0;
    private String provence, city, county;

    @Override
    protected int getContentViewResId() {
        return R.layout.activit_edtadrass;
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
        edtadrasseTitle.setText("编辑收货地址");
        intent0 = getIntent();
        String name = intent0.getStringExtra("name");
        edtadrasseName.setText(name);
        id = intent0.getStringExtra("id");
        loadMyAddress(id);
        edtadrassePhoneEdt.setText(intent0.getStringExtra("phone"));
        provence = intent0.getStringExtra("provence");
        edtadrasseProvenc.setText(provence);
        city = intent0.getStringExtra("city");
        edtadrasseCity.setText(city);
        county = intent0.getStringExtra("county");
        edtadrasseCounty.setText(county);
        detailAdrassee.setText(intent0.getStringExtra("detail"));
        if (intent0.getIntExtra("isdefault", 0) == 0) {
            edtadrasseSetmoren.setChecked(false);
        } else {
            edtadrasseSetmoren.setChecked(true);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @OnClick({R.id.edtadrasse_back, R.id.edtadrasse_finish, R.id.edtadrasse_choosadraess, R.id.edtadrasse_setmoren})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edtadrasse_back:
                finish();
                break;
            case R.id.edtadrasse_finish:

                if(edtadrasseName.getText().toString().length()==0){
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }else  if(!Utils.isPhone(edtadrassePhoneEdt.getText().toString())){
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }else if(edtadrasseProvenc.getText().toString().length()==0){
                    Toast.makeText(this, "请选取您的地址", Toast.LENGTH_SHORT).show();
                }else if(detailAdrassee.getText().toString().length()==0){
                    Toast.makeText(this, "请选填写您的详细地址", Toast.LENGTH_SHORT).show();
                }else {
                    commitData();
                }


                break;
            case R.id.edtadrasse_choosadraess:
                if (isLoaded) {
                    hideKeyboard(view);
                    ShowPickerView();
                } else {
                    //      Toast.makeText(this, "数据暂未解析成功，请等待", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.edtadrasse_setmoren:
                break;
        }
    }

    private Basic_RecAddressBean bean;

    //编辑收货地址
    private void commitData() {
        startProgressDialog("保存中...");
        bean = new Basic_RecAddressBean();
        bean.setAddressDetail(detailAdrassee.getText().toString());
        bean.setCity(edtadrasseCity.getText().toString());
        bean.setCounty(edtadrasseCounty.getText().toString());
        bean.setProvince(edtadrasseProvenc.getText().toString());
        bean.setMemo(null);
        bean.setMemberID(10);
        bean.setID(id);
        bean.setPhoneNum(edtadrassePhoneEdt.getText().toString());
        if (edtadrasseSetmoren.isChecked()) {
            bean.setIsDefault(1);
        } else {
            bean.setIsDefault(0);
        }
        bean.setRecName(edtadrasseName.getText().toString());
        final String jsonBeanst = JSON.toJSONString(bean);

        JSONObject object = new JSONObject();
        object.put("dataSource", "APP");
        object.put("memberId", "10");
        object.put("RecAddress", jsonBeanst);
        String paramsString = object.toJSONString();

        HtttpRequest.CheackIsLoginPOST(this,UrlApi.URL_ADDADRASS, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                stopProgressDialog();
                Toast.makeText(EdtAdrassActivity.this, "网络错误请重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
               stopProgressDialog();
                LogUtils.d("AddAdrassActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        Toast.makeText(EdtAdrassActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EdtAdrassActivity.this, MyAdressActivity.class));
                        EventBus.getDefault().post(6);
                        finish();
                    }
                    Toast.makeText(EdtAdrassActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /***
     * 城市选择器
     */

    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;

    private boolean isLoaded = false;
    private Thread thread;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        // Toast.makeText(EdtAdrassActivity.this, "数据解析中。。。", Toast.LENGTH_SHORT).show();

                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    // Toast.makeText(EdtAdrassActivity.this, "解析数据成功", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    //      Toast.makeText(EdtAdrassActivity.this, "解析数据失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体


        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）


            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        for (int i = 0; i < options1Items.size(); i++) {
            if (options1Items.get(i).getPickerViewText().equals(intent0.getStringExtra("provence"))) {
                positions1 = i;
                Log.d("EdtAdrassActivity", "positions1:" + positions1);
            }
        }
        for (int i = 0; i < options2Items.get(positions1).size(); i++) {
            if (options2Items.get(positions1).get(i).equals(intent0.getStringExtra("city"))) {
                positions2 = i;
                Log.d("EdtAdrassActivity", "positions2:" + positions2);
            }
        }
        for (int i = 0; i < options3Items.get(positions1).get(positions2).size(); i++) {
            if (options3Items.get(positions1).get(positions2).get(i).equals(intent0.getStringExtra("county"))) {
                positions3 = i;
                Log.d("EdtAdrassActivity", "positions3:" + positions3);
            }
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);


    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private String tx1, tx2, tx3;

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                tx1 = options1Items.get(options1).getPickerViewText();
                tx2 = options2Items.get(options1).get(options2);
                tx3 = options3Items.get(options1).get(options2).get(options3);
                edtadrasseProvenc.setText(tx1);
                edtadrasseCity.setText(tx2);
                edtadrasseCounty.setText(tx3);

            }
        })


                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(15)
                .setSelectOptions(positions1, positions2, positions3)
                .setOutSideCancelable(true)// default is true
                .build();



        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) EdtAdrassActivity.this
                .getSystemService(EdtAdrassActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

   private void  loadMyAddress(String id){
       Map<String,String> map=new HashMap<>();
       map.put("id",id+"");
       map.put("dataSource","APP");
       map.put("memberId","heh");

        HtttpRequest.CreatGetRequst(UrlApi.URL_GetMyAddressd, map, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                Log.d("EdtAdrassActivity", response);
                if(intent0.getStringExtra("phone")==null){
                    if(!response.contains(MConstant.HTTP404)){
                        MyAdrasseData data=JSON.parseObject(response,MyAdrasseData.class);
                        if(data.getObj()!=null&&data.getObj().size()!=0){
                            edtadrasseName.setText(data.getObj().get(0).getRecName());
                            edtadrassePhoneEdt.setText(data.getObj().get(0).getPhoneNum());
                            edtadrasseProvenc.setText(data.getObj().get(0).getProvince());
                            detailAdrassee.setText(data.getObj().get(0).getAddressDetail());
                            edtadrasseCounty.setText(data.getObj().get(0).getCounty());
                            edtadrasseCity.setText(data.getObj().get(0).getCounty());
                            if (data.getObj().get(0).getIsDefault() == 0) {
                                edtadrasseSetmoren.setChecked(false);
                            } else {
                                edtadrasseSetmoren.setChecked(true);
                            }
                        }

                    }
                }
            }
        });
    }
}
