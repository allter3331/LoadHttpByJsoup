package com.t3h.loadhttpbyjsoup.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.t3h.loadhttpbyjsoup.Apdapter.Rev_Apdapter;
import com.t3h.loadhttpbyjsoup.ImageVSBG;
import com.t3h.loadhttpbyjsoup.ImageVSBGDbHelper;
import com.t3h.loadhttpbyjsoup.R;

import java.util.ArrayList;
import java.util.List;

public class ChosedActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Rev_Apdapter rev_apdapter;
    List<ImageVSBG> list;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chose_img_activity);

        recyclerView = findViewById(R.id.recycler_rev);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);

//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3,RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        GetImageVSBGTask asyn = new GetImageVSBGTask();
        asyn.execute();


    }

    class GetImageVSBGTask extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressBar;
        //private boolean check = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar = new ProgressDialog(ChosedActivity.this);
            progressBar.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ImageVSBGDbHelper imageVSBGDbHelper = new ImageVSBGDbHelper(ChosedActivity.this);
            list = imageVSBGDbHelper.getAllImageVSBG();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            rev_apdapter = new Rev_Apdapter(ChosedActivity.this, list);
            recyclerView.setAdapter(rev_apdapter);
            progressBar.dismiss();
        }
    }
}
