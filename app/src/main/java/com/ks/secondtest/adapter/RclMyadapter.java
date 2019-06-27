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

public class RclMyadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Art.RecentBean> mList;
    private Context context;

    public RclMyadapter(ArrayList<Art.RecentBean> list, Context context) {
        mList = list;
        this.context = context;
    }
//阿斯顿法国红酒
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = View.inflate(context, R.layout.ban, null);
            ViewHolder1 holder1 = new ViewHolder1(view);
            return holder1;
        } else {
            View view = View.inflate(context, R.layout.art, null);
            ViewHolder2 holder2 = new ViewHolder2(view);
            return holder2;
        }
    }

    private int pos = 0;

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int type = getItemViewType(position);
        if (type == 0) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.ban.setImages(mList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Art.RecentBean bean = (Art.RecentBean) path;
                    Glide.with(context).load(bean.getThumbnail()).into(imageView);
                }
            }).start();
        } else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            if (mList.size() > 0 && mList != null) {
                pos = position - 1;
            } else {
                pos = position;
            }
            final Art.RecentBean bean = mList.get(pos);
            holder2.tv.setText(bean.getTitle());
            if (pos % 3 == 0) {
                RoundedCorners corners = new RoundedCorners(30);
                RequestOptions options = RequestOptions.bitmapTransform(corners);
                Glide.with(context).load(bean.getThumbnail()).apply(options).into(holder2.iv);
            } else {
                RequestOptions crop = new RequestOptions().circleCrop();
                Glide.with(context).load(bean.getThumbnail()).apply(crop).into(holder2.iv);
            holder2.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnClickListener.OnClick(position,bean);
                }
            });
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mList.size() > 0 && mList != null) {
            return mList.size() + 1;
        } else {
            return mList.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    class ViewHolder1 extends RecyclerView.ViewHolder {
        private Banner ban;

        public ViewHolder1(View itemView) {
            super(itemView);
            ban = itemView.findViewById(R.id.ban);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView tv;
        private ImageView iv;

        public ViewHolder2(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }



private OnClickListener OnClickListener;

    public void setOnClickListener(RclMyadapter.OnClickListener onClickListener) {
        OnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnClick(int position, Art.RecentBean bean);
    }
}
