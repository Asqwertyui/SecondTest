package com.ks.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ks.secondtest.adapter.Myvpadapter;

import java.util.ArrayList;

public class Vp extends AppCompatActivity {

    private ViewPager mVp;
    private ArrayList<String> mStrings;
    private Myvpadapter mMyvpadapter;
    /**
     * 跳
     */
    private Button mBtdream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp);
        initView();
    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mStrings = new ArrayList<>();
        mStrings.add("https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AGF0DPGmAFmM0AAOyqabQiEI126.png");
        mStrings.add("https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AGF0DV4-Adx-LAANGNJXIQcg259.png");
        mStrings.add("https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AdV0DbNCAJceCAAWgMzdrXWM856.png");
        mStrings.add("https://img1.dqdgame.com/fastdfs/M00/04/87/640x400/-/-/rB8AdV0DOEWACUlAAAQFJXr1oeI280.png");
        mStrings.add("https://img1.dqdgame.com/fastdfs/M00/04/88/640x400/-/-/rB8AdV0DZhuALu8IAAThJKyYang116.png");
        mMyvpadapter = new Myvpadapter(mStrings, this);
        mVp.setAdapter(mMyvpadapter);
        mBtdream = (Button) findViewById(R.id.btdream);
        mBtdream.setVisibility(View.GONE);
        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==4){
                    mBtdream.setVisibility(View.VISIBLE);
                    mBtdream.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Vp.this,Second.class));
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
