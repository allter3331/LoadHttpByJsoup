package com.t3h.loadhttpbyjsoup.Apdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.loadhttpbyjsoup.DownloadImageTask;
import com.t3h.loadhttpbyjsoup.ImageVSBG;
import com.t3h.loadhttpbyjsoup.ImageVSBGDbHelper;
import com.t3h.loadhttpbyjsoup.R;
import com.t3h.loadhttpbyjsoup.activity.ChosedActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<ImageVSBG> list;

    public CustomAdapter(Context context, List<ImageVSBG> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgv_itemGirl);
        TextView textViewName = (TextView) convertView.findViewById(R.id.tv_name);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageVSBGDbHelper imageVSBGDbHelper = new ImageVSBGDbHelper(context);
                imageVSBGDbHelper.insertImageVSBG(list.get(position));

                Intent intent = new Intent((Activity) context, ChosedActivity.class);
                context.startActivity(intent);
            }
        });
        textViewName.setText(list.get(position).getName());

        DownloadImageTask downloadImageTask = new DownloadImageTask(imageView);
        downloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, list.get(position).getUrlPath());

        return convertView;
    }
}
