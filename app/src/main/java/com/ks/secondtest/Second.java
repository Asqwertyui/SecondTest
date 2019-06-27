package com.ks.secondtest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ks.secondtest.adapter.VpMyfragment;
import com.ks.secondtest.fragment.Collection;
import com.ks.secondtest.fragment.Home;
import com.ks.secondtest.fragment.Load;
import com.ks.secondtest.fragment.Weal;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Second extends AppCompatActivity {

    /**
     * 天苍苍野茫茫
     */

    private Toolbar mTl;
    private ViewPager mVp;
    private TabLayout mTab;
    private DrawerLayout mDl;
    private ArrayList<Fragment> mFragments;
    private VpMyfragment mVpMyfragment;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNv;
    private ImageView mIv;
    private Weal mWeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        mTl = (Toolbar) findViewById(R.id.tl);
        mTl.setTitle("安安生生");
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        mDl = (DrawerLayout) findViewById(R.id.dl);
        mFragments = new ArrayList<>();
        mWeal = new Weal();

        mFragments.add(new Home());
        mFragments.add(new Load());
        mFragments.add(mWeal);
        mFragments.add(new Collection());
        setSupportActionBar(mTl);

        mToggle = new ActionBarDrawerToggle(this, mDl, mTl, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(mToggle);
        mToggle.getDrawerArrowDrawable().setColor(R.color.colorPrimary);
        mToggle.syncState();
        mVpMyfragment = new VpMyfragment(getSupportFragmentManager(), mFragments);
        mVp.setAdapter(mVpMyfragment);

        mNv = (NavigationView) findViewById(R.id.nv);
        View headerView = mNv.getHeaderView(0);

        headerView.findViewById(R.id.iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Second.this, "办事处", Toast.LENGTH_SHORT).show();
            }
        });
        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.select));
        mTab.addTab(mTab.newTab().setText("加载").setIcon(R.drawable.select1));
        mTab.addTab(mTab.newTab().setText("福利").setIcon(R.drawable.select2));
        mTab.addTab(mTab.newTab().setText("收藏").setIcon(R.drawable.select3));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));

        mIv = (ImageView) findViewById(R.id.iv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,1,1,"网格");
        menu.add(2,2,2,"线性");
        menu.add(3,3,3,"瀑布");

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==1) {
            mWeal.setManager(new GridLayoutManager(this,2));
        }else if(item.getItemId()==2){
        mWeal.setManager(new LinearLayoutManager(this));

        }else if(item.getItemId()==3){
            mWeal.setManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        }
        return super.onOptionsItemSelected(item);
    }
}
