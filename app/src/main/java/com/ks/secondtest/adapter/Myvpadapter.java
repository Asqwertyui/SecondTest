package com.ks.secondtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ks.secondtest.R;

import java.util.ArrayList;

/**
 * Created by F0519 on 2019/6/26.
 */

public class Myvpadapter extends PagerAdapter {
    private ArrayList<String> mStrings;
private Context context;

    public Myvpadapter(ArrayList<String> strings, Context context) {
        mStrings = strings;
        this.context = context;
    }

    public Myvpadapter(ArrayList<String> strings) {
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.item, null);
        ImageView iv = view.findViewById(R.id.iv);
        TextView tv = view.findViewById(R.id.tv);
        String s = mStrings.get(position);
        Glide.with(context).load(s).into(iv);
        tv.setText("这是第"+position+"张");
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
