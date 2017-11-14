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
import com.umeng.analytics.MobclickAgent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.EventBus.Addaress_event;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.Basic_RecAddressBean;
import hb.xnwdw.com.wendangwang.netdata.entity.MyAdrasseData;
import hb.xnwdw.com.wendangwang.netdata.mvp.UrlUtils;
import hb.xnwdw.com.wendangwang.utils.GetJsonDataUtil;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.JsonBean;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import hb.xnwdw.com.wendangwang.utils.Utils;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by Administrator on 2017/4/27.
 */
public class AddAdrassActivity extends ActivityBase {

    @BindView(R.id.addadrasse_finish)
    TextView addadrasseFinish;
    @BindView(R.id.addadrasse_name)
    EditText addadrasseName;
    @BindView(R.id.addadrasse_phone_edt)
    EditText addadrassePhoneEdt;
    @BindView(R.id.addadrasse_choosadraess)
    LinearLayout addadrasseChoosadraess;
    @BindView(R.id.addadrasse_setmoren)
    CheckBox addadrasseSetmoren;
    @BindView(R.id.addadrasse_back)
    ImageView addadrasseBack;
    @BindView(R.id.addadrasse_title)
    TextView addadrasseTitle;
    @BindView(R.id.addadrasse_provenc)
    TextView addadrasseProvenc;
    @BindView(R.id.addadrasse_city)
    TextView addadrasseCity;
    @BindView(R.id.addadrasse_county)
    TextView addadrasseCounty;
    @BindView(R.id.detail_adrassee)
    EditText detailAdrassee;


    @Override
    protected int getContentViewResId() {
        return R.layout.activit_addadrass;
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
        addadrasseTitle.setText("新增收货地址");
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);
    }

    @OnClick({R.id.addadrasse_finish, R.id.addadrasse_choosadraess, R.id.addadrasse_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addadrasse_back:
                finish();
                break;
            case R.id.addadrasse_finish:
//                if(Utils.isPhone(addadrassePhoneEdt.getText().toString())){
//
//
//                }else {
//                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
//                }
                if(addadrasseName.getText().toString().length()==0){
                    Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
                }else  if(!Utils.isPhone(addadrassePhoneEdt.getText().toString())){
                    Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                }else if(addadrasseProvenc.getText().toString().length()==0){
                    Toast.makeText(this, "请选取您的地址", Toast.LENGTH_SHORT).show();
                }else if(detailAdrassee.getText().toString().length()==0){
                    Toast.makeText(this, "请选填写您的详细地址", Toast.LENGTH_SHORT).show();
                }else {
                    commitData();
                }
                break;
            case R.id.addadrasse_choosadraess:
                if (isLoaded) {
                    hideKeyboard(view);
                    ShowPickerView();
                } else {
                    //   Toast.makeText(this, "数据暂未解析成功，请等待", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private Basic_RecAddressBean bean;

    /**
     * 修改编辑向服务器提交数据
     */
    private void commitData() {
        bean = new Basic_RecAddressBean();
        bean.setAddressDetail(detailAdrassee.getText().toString());
        bean.setCity(tx2);
        bean.setCounty(tx3);
        bean.setProvince(tx1);
        bean.setMemo(null);
        bean.setMemberID(10);
        bean.setPhoneNum(addadrassePhoneEdt.getText().toString());


        if (addadrasseSetmoren.isChecked()) {
            bean.setIsDefault(1);
        } else {
            bean.setIsDefault(0);
        }
        bean.setRecName(addadrasseName.getText().toString());
        String jsonBeanst = JSON.toJSONString(bean);

        JSONObject object = new JSONObject();
        object.put("dataSource", "APP");
        object.put("memberId", WDWApp.getMenberId());
        object.put("RecAddress", jsonBeanst);
        String paramsString = object.toJSONString();


        HtttpRequest.CheackIsLoginPOST(this,UrlApi.URL_ADDADRASS, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AddAdrassActivity", response);
                Toast.makeText(AddAdrassActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(5);
                finish();
            }
        });
    }


    /***
     * 城市选择器
     */
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private boolean isLoaded = false;
    private Thread thread;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                      //  Toast.makeText(AddAdrassActivity.this, "数据解析中。。。", Toast.LENGTH_SHORT).show();

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
                    //    Toast.makeText(AddAdrassActivity.this, "解析数据成功", Toast.LENGTH_SHORT).show();
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    //   Toast.makeText(AddAdrassActivity.this, "解析数据失败", Toast.LENGTH_SHORT).show();
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
                addadrasseProvenc.setText(tx1);
                addadrasseCity.setText(tx2);
                addadrasseCounty.setText(tx3);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(16)
                .setSelectOptions(28, 0, 0)
                .setOutSideCancelable(true)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void hideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) AddAdrassActivity.this
                .getSystemService(AddAdrassActivity.this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
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
