package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hb.xnwdw.com.wendangwang.R;

/**
 * Created by Administrator on 2017/7/14.
 */
public class EditName extends ActivityBase {

    @BindView(R.id.editename_back)
    ImageView editenameBack;
    @BindView(R.id.editename_title)
    TextView editenameTitle;
    @BindView(R.id.editename_finish)
    TextView editenameFinish;
    @BindView(R.id.edit_name_edt)
    EditText editNameEdt;
    @BindView(R.id.edit_name_delet)
    ImageView editNameDelet;

    @Override
    protected int getContentViewResId() {
        return R.layout.edite_name_activity;
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
        editenameTitle.setText("修改昵称");
        editenameFinish.setText("确定");
        Intent intent=getIntent();
        String s=intent.getStringExtra("name");
        editNameEdt.setText(s);
        editNameEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                    if(s.length()==0){
                        editNameDelet.setVisibility(View.INVISIBLE);
                    }else {
                        editNameDelet.setVisibility(View.VISIBLE);
                    }
            }
        });
    }

    @OnClick({R.id.editename_back, R.id.editename_finish, R.id.edit_name_delet})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editename_back:
                finish();
                break;
            case R.id.editename_finish:
                Intent intent=new Intent(EditName.this,AccontinfoActivity.class);
                intent.putExtra("name",editNameEdt.getText().toString());
                setResult(5,intent);
                finish();
                break;
            case R.id.edit_name_delet:
                if(editNameEdt.getText().toString().length()!=0){
                    editNameEdt.setText("");
                }
                break;
        }
    }
}
