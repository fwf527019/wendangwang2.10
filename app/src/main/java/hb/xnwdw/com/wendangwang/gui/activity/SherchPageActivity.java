package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.EventBus.SerchadapterEvent;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.ShearchListAdapter;
import hb.xnwdw.com.wendangwang.gui.adapter.ShearchResutsAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.MyLinearLayoutManager;
import hb.xnwdw.com.wendangwang.utils.MessageEvent;
import hb.xnwdw.com.wendangwang.utils.RecordsDao;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SherchPageActivity extends ActivityBase {


    @BindView(R.id.back_img)
    ImageView backImg;
    @BindView(R.id.sherchpage_edt)
    EditText sherchpageEdt;
    @BindView(R.id.activityserch_ll)
    LinearLayout activityserchLl;
    @BindView(R.id.go_exit_btn)
    TextView goExitBtn;
    @BindView(R.id.shearchpage_list)
    RecyclerView shearchpageList;
    @BindView(R.id.clearallrecords_tv)
    TextView clearallrecordsTv;


    private List<String> hislist;
    private RecordsDao recordsDao;
    private List<String> tempList;
    private ShearchListAdapter adpter;
    private String keywords;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_sherchpage;
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
        final Intent intent = getIntent();
        keywords = intent.getStringExtra("keyword");
        sherchpageEdt.setHint(keywords);
        //获取搜索历史列表
        EventBus.getDefault().register(this);
        initData();
        initListener();
        bindAdapter();

        sherchpageEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            //键盘的确认键
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_SEARCH) || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (sherchpageEdt.getText() != null) {
                        // loadSheaechData(sherchpageEdt.getText().toString().trim());
                        if (sherchpageEdt.getText().toString().length() > 0) {
                            //网页收索
                            //     startActivity(new Intent(SherchPageActivity.this, ShearchResutsActivityweb.class).putExtra("keyword", "?kw=" + sherchpageEdt.getText().toString().trim()));
                            //原生
                            Intent intent1 = new Intent(SherchPageActivity.this, ShearchResutsActivity.class);
                            intent1.putExtra("keyword", sherchpageEdt.getText().toString().trim());
                            intent1.putExtra("TAG", "搜索");
                            startActivity(intent1);
                            String record = sherchpageEdt.getText().toString();
                            //判断数据库中是否存在该记录
                            if (!recordsDao.isHasRecord(record)) {
                                tempList.add(record);
                            }
                            //将搜索记录保存至数据库中
                            recordsDao.addRecords(record);
                            reversedList();
                            checkRecordsSize();
                            adpter.notifyDataSetChanged();
                            //根据关键词去搜索
                        } else {
//                            Toast.makeText(SherchPageActivity.this, "搜索内容不能为空", Toast.LENGTH_SHORT).show();
                            //为空时网页
                            //  startActivity(new Intent(SherchPageActivity.this, ShearchResutsActivityweb.class).putExtra("keyword", "?kw=" + keywords));

                            //为空时原生
                            Intent intent4 = new Intent(SherchPageActivity.this, ShearchResutsActivity.class);
                            intent4.putExtra("keyword", keywords);
                            intent4.putExtra("TAG", "搜索");
                            startActivity(intent4);

                            String record = sherchpageEdt.getText().toString();
                            //判断数据库中是否存在该记录
                            if (!recordsDao.isHasRecord(record)) {
                                tempList.add(record);
                            }
                            //将搜索记录保存至数据库中
                            recordsDao.addRecords(record);
                            reversedList();
                            checkRecordsSize();
                            adpter.notifyDataSetChanged();

                        }
                    }

                    return true;
                }
                return false;
            }
        });

    }

    @OnClick({R.id.back_img, R.id.go_exit_btn, R.id.clearallrecords_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_img:
                finish();
                break;
            case R.id.clearallrecords_tv:
                tempList.clear();
                reversedList();
                recordsDao.deleteAllRecords();
                adpter.notifyDataSetChanged();
                break;
            case R.id.go_exit_btn:
                if (sherchpageEdt.getText() != null) {
                    // loadSheaechData(sherchpageEdt.getText().toString().trim());
                    if (sherchpageEdt.getText().toString().length() > 0) {
                        //网页搜索
                        //  startActivity(new Intent(this, ShearchResutsActivityweb.class).putExtra("keyword", "?kw=" + sherchpageEdt.getText().toString().trim()));
                        //原生
                        Intent intent2 = new Intent(SherchPageActivity.this, ShearchResutsActivity.class);
                        intent2.putExtra("keyword", sherchpageEdt.getText().toString().trim());
                        intent2.putExtra("TAG", "搜索");
                        startActivity(intent2);

                        String record = sherchpageEdt.getText().toString();

                        //判断数据库中是否存在该记录
                        if (!recordsDao.isHasRecord(record)) {
                            tempList.add(record);
                        }
                        //将搜索记录保存至数据库中
                        recordsDao.addRecords(record);
                        reversedList();
                        checkRecordsSize();
                        adpter.notifyDataSetChanged();
                        //根据关键词去搜索
                    } else {
                        //网页
                        //                 startActivity(new Intent(SherchPageActivity.this, ShearchResutsActivityweb.class).putExtra("keyword", "?kw=" + keywords));

                        //原生
                        Intent intent3 = new Intent(SherchPageActivity.this, ShearchResutsActivity.class);
                        intent3.putExtra("keyword", keywords);
                        intent3.putExtra("TAG", "搜索");
                        startActivity(intent3);
                        String record = sherchpageEdt.getText().toString();
                        //判断数据库中是否存在该记录
                        if (!recordsDao.isHasRecord(record)) {
                            tempList.add(record);
                        }
                        //将搜索记录保存至数据库中
                        recordsDao.addRecords(record);
                        reversedList();
                        checkRecordsSize();
                        adpter.notifyDataSetChanged();
                    }
                }
                break;
        }
    }

    private void initData() {
        recordsDao = new RecordsDao(this);
        hislist = new ArrayList<>();
        tempList = new ArrayList<>();
        tempList.addAll(recordsDao.getRecordsList());
        reversedList();
        //第一次进入判断数据库中是否有历史记录，没有则不显示

    }

    private void bindAdapter() {
        adpter = new ShearchListAdapter(this, hislist);
        shearchpageList.setLayoutManager(new MyLinearLayoutManager(this));
        shearchpageList.setAdapter(adpter);
        //历史记录点击事件
        adpter.setOnItemClickListener(new ShearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView textView = (TextView) view.findViewById(R.id.shearch_his_content);
                String name = textView.getText().toString();
                sherchpageEdt.setText(name);
            }

        });
    }


    private void initListener() {
        sherchpageEdt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                }
                return false;
            }
        });

        //根据输入的信息去模糊搜索
        sherchpageEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String tempName = sherchpageEdt.getText().toString();
                tempList.clear();
                tempList.addAll(recordsDao.querySimlarRecord(tempName));
                reversedList();
                checkRecordsSize();
                adpter.notifyDataSetChanged();
            }
        });


    }

    /**
     * //颠倒list顺序，用户输入的信息会从上依次往下显示
     */
    private void reversedList() {

        hislist.clear();
        for (int i = tempList.size() - 1; i >= 0; i--) {
            hislist.add(tempList.get(i));

        }
    }

    //当没有匹配的搜索数据的时候不显示历史记录栏
    private void checkRecordsSize() {
        if (hislist.size() == 0) {
            shearchpageList.setVisibility(View.GONE);
        } else {
            shearchpageList.setVisibility(View.VISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void serchadapter(SerchadapterEvent event) {
        int posintion = event.getPos();
        recordsDao.removRecords();
    }
}