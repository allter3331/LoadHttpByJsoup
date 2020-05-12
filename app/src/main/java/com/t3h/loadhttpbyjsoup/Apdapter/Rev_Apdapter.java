package com.t3h.loadhttpbyjsoup.Apdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.t3h.loadhttpbyjsoup.ImageVSBG;
import com.t3h.loadhttpbyjsoup.R;

import java.util.ArrayList;
import java.util.List;

public class Rev_Apdapter extends RecyclerView.Adapter<Rev_Apdapter.ViewHolder> {

    Context context;
    List<ImageVSBG> list;

    public Rev_Apdapter(Context context, List<ImageVSBG> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_chose_ativity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getUrlPath()).into(holder.imgAvatar);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgAvatar = itemView.findViewById(R.id.img_choseImg);
        }
    }
}
