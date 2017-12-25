package hb.xnwdw.com.wendangwang.gui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.WDWApp;
import hb.xnwdw.com.wendangwang.gui.adapter.MyAdrassAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.netdata.UrlApi;
import hb.xnwdw.com.wendangwang.netdata.entity.Basic_RecAddressBean;
import hb.xnwdw.com.wendangwang.netdata.entity.MyAdrasseData;
import hb.xnwdw.com.wendangwang.utils.HtttpRequest;
import hb.xnwdw.com.wendangwang.utils.LogUtils;
import hb.xnwdw.com.wendangwang.utils.MConstant;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/3/16.
 */
public class MyAdressActivity extends ActivityBase {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.addadrasse)
    LinearLayout addadrasse;
    @BindView(R.id.myadress_add_myadress_tv)
    TextView myadressAddMyadressTv;
    @BindView(R.id.no_data_page)
    LinearLayout noDataPage;
    private MyAdrasseData myAdrasseData;
    private RecyclerView recyclerView;
    private MyAdrassAdapter myAdrassAdapter;
    private String tag;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_myadrasse;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initDatas() {

    }

    private void initData() {
        JSONObject object = new JSONObject();
        object.put("memberId", WDWApp.getMenberId());
        object.put("dataSource", "APP");

        String paramsString = object.toJSONString();
        HtttpRequest.CheackIsLoginPOST(this, UrlApi.URL_GETMYADRASS, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                LogUtils.d("MyAdressActivity", "e:" + e);
            }

            @Override
            public void onResponse(String response, int id) {

                LogUtils.d("MyAdressActivity", response);
                if (response.contains(MConstant.HTTP404)) {
                    Toast.makeText(MyAdressActivity.this, "404错误", Toast.LENGTH_SHORT).show();
                } else {
                    myAdrasseData = JSON.parseObject(response, MyAdrasseData.class);
                    if (myAdrasseData.getObj() == null || myAdrasseData.getObj().size() == 0) {
                        noDataPage.setVisibility(View.VISIBLE);
                    } else {
                        noDataPage.setVisibility(View.GONE);

                        recyclerView = (RecyclerView) findViewById(R.id.my_adress_recycler);
                        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(myLinearLayoutManager);
                        myAdrassAdapter = new MyAdrassAdapter(R.layout.item_myadrasse, myAdrasseData.getObj());
                        recyclerView.setAdapter(myAdrassAdapter);
                        myAdrassAdapter.setClickLstner(new MyAdrassAdapter.ClickLstner() {
                            @Override
                            public void Oncliclstner(boolean isCheacked, int pos) {
                                if (!isCheacked) {
                                    commitData(pos, true);

                                } else {
                                    commitData(pos, false);

                                }
                            }
                        });

                        if (tag != null && tag.equals("OrderBalanceActivity")) {
                            myAdrassAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    Intent intent = new Intent();
                                    intent.putExtra("name", myAdrasseData.getObj().get(position).getRecName());
                                    intent.putExtra("phone", myAdrasseData.getObj().get(position).getPhoneNum());
                                    intent.putExtra("pos", position);
                                    intent.putExtra("adrass", myAdrasseData.getObj().get(position).getProvince() + myAdrasseData.getObj().get(position).getCity() +
                                            myAdrasseData.getObj().get(position).getCounty() + myAdrasseData.getObj().get(position).getAddressDetail()
                                    );
                                    intent.putExtra("provence", myAdrasseData.getObj().get(position).getProvince());
                                    setResult(1, intent);
                                    finish();
                                }
                            });
                        }


                        myAdrassAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public boolean onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
                                switch (view.getId()) {
                                    case R.id.item_adrass_delete:

                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MyAdressActivity.this);
                                        builder1.setMessage("确定删除地址？");
                                        builder1.setTitle("提示");
                                        builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                deleteAdrasse(position);
                                                dialog.dismiss();
                                            }
                                        });

                                        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                        builder1.create().show();

                                        break;
                                    case R.id.item_adrass_edt:
                                        //intent 传值
                                        Intent intent = new Intent(getApplicationContext(), EdtAdrassActivity.class);
                                        intent.putExtra("id", myAdrasseData.getObj().get(position).getID());
                                        intent.putExtra("name", myAdrasseData.getObj().get(position).getRecName());
                                        intent.putExtra("phone", myAdrasseData.getObj().get(position).getPhoneNum());
                                        intent.putExtra("provence", myAdrasseData.getObj().get(position).getProvince());
                                        intent.putExtra("city", myAdrasseData.getObj().get(position).getCity());
                                        intent.putExtra("county", myAdrasseData.getObj().get(position).getCounty());
                                        intent.putExtra("detail", myAdrasseData.getObj().get(position).getAddressDetail());
                                        intent.putExtra("isdefault", myAdrasseData.getObj().get(position).getIsDefault());
                                        startActivity(intent);
                                        break;
                                }

                                return false;
                            }

                            /**
                             * 删除收货地址
                             * @param pos
                             */
                            private void deleteAdrasse(final int pos) {
                                JSONObject object = new JSONObject();
                                object.put("memberId", "10");
                                object.put("id", myAdrasseData.getObj().get(pos).getID());
                                object.put("dataSource", "APP");
                                String paramsString = object.toJSONString();
                                HtttpRequest.CreatPostRequst(UrlApi.URL_DELETEADRASS, paramsString, new StringCallback() {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {

                                    }

                                    @Override
                                    public void onResponse(String response, int id) {
                                        Toast.makeText(MyAdressActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                        myAdrassAdapter.remove(pos);
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        title.setText("管理收货地址");
        Intent intent = getIntent();
        tag = intent.getStringExtra("tag");

        initData();
    }

    @OnClick({R.id.back, R.id.addadrasse, R.id.myadress_add_myadress_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            //添加地址
            case R.id.addadrasse:
                startActivity(new Intent(this, AddAdrassActivity.class));
                break;
            case R.id.myadress_add_myadress_tv:
                startActivity(new Intent(this, AddAdrassActivity.class));
                break;
        }
    }

    private Basic_RecAddressBean bean;

    //编辑收货地址
    private void commitData(int pos, boolean isdefalt) {
        bean = new Basic_RecAddressBean();
        bean.setAddressDetail(myAdrasseData.getObj().get(pos).getAddressDetail());
        bean.setCity(myAdrasseData.getObj().get(pos).getCity());
        bean.setCounty(myAdrasseData.getObj().get(pos).getCounty());
        bean.setProvince(myAdrasseData.getObj().get(pos).getProvince());
        bean.setMemo(null);
        bean.setMemberID(myAdrasseData.getObj().get(pos).getMemberID());
        bean.setID(myAdrasseData.getObj().get(pos).getID());
        bean.setPhoneNum(myAdrasseData.getObj().get(pos).getPhoneNum());
        if (isdefalt) {
            bean.setIsDefault(1);
        } else {
            bean.setIsDefault(0);
        }
        bean.setRecName(myAdrasseData.getObj().get(pos).getRecName());

        final String jsonBeanst = JSON.toJSONString(bean);

        JSONObject object = new JSONObject();
        object.put("dataSource", "APP");
        object.put("memberId", "0");
        object.put("RecAddress", bean);
        String paramsString = object.toJSONString();
        Log.d("MyAdressActivity", paramsString);
        HtttpRequest.CreatPostRequst(UrlApi.URL_ADDADRASS, paramsString, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                LogUtils.d("AddAdrassActivity", response);
                if (!response.contains(MConstant.HTTP404)) {
                    if (JSONObject.parseObject(response).get("dataStatus").toString().equals("1")) {
                        Toast.makeText(MyAdressActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }                      else {
                        Toast.makeText(MyAdressActivity.this, JSONObject.parseObject(response).get("describe").toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }
}
