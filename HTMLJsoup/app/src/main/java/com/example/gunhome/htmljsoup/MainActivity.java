package com.example.gunhome.htmljsoup;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String htmlPageUrl = "https://news.naver.com/main/ranking/popularDay.nhn?mid=etc&sid1=111";
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat;

    private String pol[] = new String[10];
    private String eco[] = new String[10];
    private String soc[] = new String[10];
    private String lif[] = new String[10];
    private String wor[] = new String[10];
    private String sci[] = new String[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textviewHtmlDocument = (TextView)findViewById(R.id.text);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod());


        Button htmlTitleButton = (Button)findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
            }
        });
    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {
            String News_Url;
            int cnt = 0;
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();
                Elements links = doc.select("a[href]");

                for (Element link : links) {
                    News_Url = (link.attr("abs:href") + "(" + link.text().trim() + ")\n");
                    //htmlContentInStringFormat += News_Url;

                    if(News_Url.contains("rankingSectionId=100")){
                        pol[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) cnt = 0;
                    }else if(News_Url.contains("rankingSectionId=101")){
                        eco[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) cnt = 0;
                    }else if(News_Url.contains("rankingSectionId=102")){
                        soc[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) cnt = 0;
                    }else if(News_Url.contains("rankingSectionId=103")){
                        lif[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) cnt = 0;
                    }else if(News_Url.contains("rankingSectionId=104")){
                        wor[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) cnt = 0;
                    }else if(News_Url.contains("rankingSectionId=105")){
                        sci[cnt++] = News_Url;
                        htmlContentInStringFormat += News_Url;
                        if(cnt == 6) break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            textviewHtmlDocument.setText("");
            textviewHtmlDocument.setText(htmlContentInStringFormat);
            System.out.println(htmlContentInStringFormat);
        }
    }
}