package com.ks.secondtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ks.secondtest.R;
import com.ks.secondtest.bean.Wea;

import java.util.ArrayList;

/**
 * Created by F0519 on 2019/6/26.
 */

public class RclMyweal extends RecyclerView.Adapter<RclMyweal.ViewHolder> {

    private ArrayList<Wea.ResultsBean> mResultsBeans;
    private Context context;

    public RclMyweal(ArrayList<Wea.ResultsBean> resultsBeans, Context context) {
        mResultsBeans = resultsBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public RclMyweal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.art, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
private int pos;
    @Override
    public void onBindViewHolder(@NonNull RclMyweal.ViewHolder holder, final int position) {
        final Wea.ResultsBean bean = mResultsBeans.get(position);
        holder.tv.setText(bean.getPublishedAt());
        Glide.with(context).load(bean.getUrl()).into(holder.iv);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                OnClickListener.OnClick(position,bean);
                return true;
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=position;
                OnClickListener.OnClick(position,bean);
            }
        });
    }
public void delete(){
        mResultsBeans.remove(pos);
        notifyDataSetChanged();
}

    @Override
    public int getItemCount() {
        return mResultsBeans.size();
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

    public void setOnClickListener(RclMyweal.OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }
    public interface OnClickListener{
        void OnClick(int position, Wea.ResultsBean bean);
        void OnLong(int position, Wea.ResultsBean bean);
    }
}
