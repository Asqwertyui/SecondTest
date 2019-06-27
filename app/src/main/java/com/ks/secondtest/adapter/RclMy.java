package com.ks.secondtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ks.secondtest.R;
import com.ks.secondtest.bean.Art;
import com.ks.secondtest.bean.Bean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

/**
 * Created by F0519 on 2019/6/26.
 */

public class RclMy extends RecyclerView.Adapter<RclMy.ViewHolder> {

    private ArrayList<Bean> mBeans;

    public void setBeans(ArrayList<Bean> beans) {
        mBeans = beans;
    }

    private Context context;

    public RclMy(ArrayList<Bean> beans, Context context) {
        mBeans = beans;
        this.context = context;
    }



    @NonNull
    @Override
    public RclMy.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.art, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RclMy.ViewHolder holder, final int position) {
        final Bean bean = mBeans.get(position);
        holder.tv.setText(bean.getTitle());
        Glide.with(context).load(bean.getThumbnail()).into(holder.iv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickListener.OnClick(position,bean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
           tv= itemView.findViewById(R.id.tv);
           iv= itemView.findViewById(R.id.iv);
        }
    }
    private OnClickListener OnClickListener;

    public void setOnClickListener(RclMy.OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }
    public interface OnClickListener{
        void OnClick(int position, Bean bean);
    }
}
