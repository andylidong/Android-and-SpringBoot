package com.example.dli.androiddemo.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dli.androiddemo.R;
import com.example.dli.androiddemo.bean.News;
import com.example.dli.androiddemo.common.util.ObjectUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<News> data;

    private OnItemClickListener onItemClickListener;


    public NewsAdapter(Context context, List<News> data, OnItemClickListener onItemClickListener) {
        mContext = context;
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_news, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        if (onItemClickListener != null) {
            holder.tv.setOnClickListener(v -> onItemClickListener.onTextClick(v));
            holder.iv.setOnClickListener(v -> onItemClickListener.onImageClick(v));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        News item;
        try {
            item = data.get(position);
        } catch (Exception e) {
            return;
        }
        if (item == null) return;
        ((MyViewHolder) holder).tv.setText(item.getDesc() + "\n" + item.getWho());
        if (ObjectUtils.isEmpty(item.getUrl())) return;
        Uri uri = Uri.parse(item.getUrl());
        ((MyViewHolder) holder).iv.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private SimpleDraweeView iv;

        public MyViewHolder(View view) {
            super(view);
            if (tv == null)
                tv = view.findViewById(R.id.tv_Title);
            if (iv == null)
                iv = view.findViewById(R.id.iv_Image);
        }
    }

    public interface OnItemClickListener {

        void onImageClick(View view);

        void onTextClick(View view);
    }
}
