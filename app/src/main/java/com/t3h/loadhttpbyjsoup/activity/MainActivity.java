package com.t3h.loadhttpbyjsoup.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.t3h.loadhttpbyjsoup.Apdapter.CustomAdapter;
import com.t3h.loadhttpbyjsoup.ImageVSBG;
import com.t3h.loadhttpbyjsoup.R;
import com.t3h.loadhttpbyjsoup.TestLoadImageActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String URL = "https://vsbg.info/";

    ListView listView;
    Button buttonTestLoadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        buttonTestLoadImage = (Button) findViewById(R.id.btn_test);
        new DownloadTask().execute(URL);

        buttonTestLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestLoadImageActivity.class);
                startActivity(intent);
            }
        });

    }

    class DownloadTask extends AsyncTask<String, Void, List<ImageVSBG>> {

        @Override
        protected List<ImageVSBG> doInBackground(String... strings) {
            Document document = null;
            List<ImageVSBG> imageVSBGS = new ArrayList<>();
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (document != null) {
                    Elements imagesElements = document.select("section.grid__item");


                    if (imagesElements != null && imagesElements.size() > 0) {
                        for (Element element : imagesElements) {
                            Element infoGirl = element.getElementsByClass("czr-title").first();
                            Element imageGirl = element.getElementsByTag("img").first();

                            if (infoGirl != null && imageGirl != null) {
                                String info = infoGirl.text();
                                //Log.d(TAG, "doInBackground: " + info);
                                String src = imageGirl.attr("src");

                                ImageVSBG imageVSBG = new ImageVSBG(info, src);
                                imageVSBGS.add(imageVSBG);
                            }
                        }
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return imageVSBGS;
        }

        @Override
        protected void onPostExecute(List<ImageVSBG> imageVSBGS) {
            final CustomAdapter adapter = new CustomAdapter(MainActivity.this, imageVSBGS);
            listView.setAdapter(adapter);
        }
    }
}
