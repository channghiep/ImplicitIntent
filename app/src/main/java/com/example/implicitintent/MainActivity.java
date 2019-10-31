package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static ArrayList<listItem> urlList = new ArrayList<listItem>();
    String data;
    listItem tempItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("state",urlList.toString());

//        urlList
        tempItem = new listItem();

        Intent i = getIntent();

        data = i.getStringExtra(Intent.EXTRA_TEXT);

        if(data != null) {
//            Log.e("test", data);
            tempItem.url = data;
//            Log.e("test2", tempItem.url.toString());
            urlList.add(tempItem);

        }
        setupAdapter();
//        ListView listView = findViewById(R.id.listView);
//        ArrayAdapter<listItem> adapter = new ArrayAdapter<listItem>(
//                this, android.R.layout.simple_list_item_1, urlList);
//        listView.setAdapter(adapter);
        Log.d("state","create");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.e("test", "paused");
        Log.e("test array pause", urlList.toString());



//        urlList.add(tempItem);

        Log.d("state","paused");
        Log.d("state",urlList.toString());
    }
    @Override
    protected void onResume(){
        super.onResume();

//        setupAdapter();
//
//        Intent i = getIntent();
//
//        data = i.getStringExtra(Intent.EXTRA_TEXT);
//
//
////        urlList.add(tempItem);
//        if(data != null) {
////            Log.e("test", data);
//            tempItem.url = data;
////            Log.e("test2", tempItem.url.toString());
//            urlList.add(tempItem);
//
//        }

//        setupAdapter();

        Log.e("test", "resume");
        Log.e ("test array resume", urlList.toString());



        Log.d("state","resumed");
        Log.d("state",urlList.toString());
    }



    private void setupAdapter(){

       final customAdapter adapter = new customAdapter(this, urlList);

       ListView listView = findViewById(R.id.list);
       listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItem item = (listItem) adapter.getItem(position);

                WebView webView = findViewById(R.id.webView);


                webView.getSettings().setJavaScriptEnabled(true);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        Log.d("WEBVIEW", "Finished loading " + url);
                    }
                });

                Log.d("url", urlList.get(0).getUrl());
                webView.loadUrl(urlList.get(position).getUrl());

            }
        });
    }

}
