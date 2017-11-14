package hb.xnwdw.com.wendangwang.gui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hb.xnwdw.com.wendangwang.R;
import hb.xnwdw.com.wendangwang.gui.adapter.GuiViewPagerAdapter;
import hb.xnwdw.com.wendangwang.gui.widget.PagerDotIndicator;

/**
 * Created by Administrator on 2017/2/20.
 */
public class GuideActivity extends AppCompatActivity {
    @BindView(R.id.gui_viewpager)
    ViewPager guiViewpager;
    private Button btn;
    private GuiViewPagerAdapter adapter;
    private View view1, view2, view3;
    private List<View> viewList;//view数组

    private LinearLayout guiindicatorContaner;
    private PagerDotIndicator pagerDotIndicator;


    SimpleDraweeView simpleDraweeView1;
    SimpleDraweeView simpleDraweeView2;
    SimpleDraweeView simpleDraweeView3;

    ImageView closebtn1;
    ImageView closebtn2;
    ImageView closebtn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ButterKnife.bind(this);
        LayoutInflater inflater = getLayoutInflater();
        getViews(inflater);
        adapter = new GuiViewPagerAdapter(viewList);
        guiViewpager.setAdapter(adapter);
        btn= (Button) view3.findViewById(R.id.gui_viewpager_btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainPager();
                finish();
            }
        });


        closebtn1= (ImageView) view1.findViewById(R.id.vip1_close1);
        closebtn2= (ImageView) view2.findViewById(R.id.vip2_close2);
        closebtn3= (ImageView) view2.findViewById(R.id.vip2_close2);
        closebtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainPager();
                finish();
            }
        });
        closebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainPager();
                finish();
            }
        });
        closebtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainPager();
                finish();
            }
        });



        simpleDraweeView1= (SimpleDraweeView)view1.findViewById(R.id.viewp1);
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
        //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.star1))//设置uri
                .build();
        //设置Controller
        simpleDraweeView1.setController(mDraweeController);


        simpleDraweeView2= (SimpleDraweeView)view2.findViewById(R.id.viewp2);
        DraweeController mDraweeController2 = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.star2))//设置uri
                .build();
        //设置Controller
        simpleDraweeView2.setController(mDraweeController2);


        simpleDraweeView3= (SimpleDraweeView)view3.findViewById(R.id.viewp3);
        DraweeController mDraweeController3 = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                //加载drawable里的一张gif图
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.star3))//设置uri
                .build();
        //设置Controller
        simpleDraweeView3.setController(mDraweeController3);

    }

   

    private void gotoMainPager() {
        startActivity(new Intent(this,MainPagerActivity.class));
    }


    private void getViews(LayoutInflater inflater) {
        view1 = inflater.inflate(R.layout.guideviewpager1, null);
        view2 = inflater.inflate(R.layout.guideviewpager2, null);
        view3 = inflater.inflate(R.layout.guideviewpafer3, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
    }



}
