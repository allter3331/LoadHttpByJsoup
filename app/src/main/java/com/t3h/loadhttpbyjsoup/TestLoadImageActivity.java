package com.t3h.loadhttpbyjsoup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class TestLoadImageActivity extends AppCompatActivity {

    private final String link = "https://andreluu.com/wp-content/uploads/2018/06/DSC2822-Edit1.jpg";

    Button buttonNormal;
    Button buttonGlide;
    ImageView imageViewNormal;
    ImageView imageViewGlide;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_loadimg_activity);

        buttonNormal = (Button) findViewById(R.id.btn_normal);
        buttonGlide = (Button) findViewById(R.id.btn_glide);

        imageViewGlide = (ImageView) findViewById(R.id.img_Glide);
        imageViewNormal = (ImageView) findViewById(R.id.img_normal);

        buttonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DownloadImageTask downloadImageTask = new DownloadImageTask(imageViewNormal);
                downloadImageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, link);

            }
        });

        buttonGlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(TestLoadImageActivity.this)
                        .load(link)
                        .into(imageViewGlide);
            }
        });

    }
}
