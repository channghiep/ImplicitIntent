package com.example.implicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<listItem> urlList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlList = new ArrayList<listItem>();
        listItem tempItem = new listItem();

        Intent i = getIntent();

        String data = i.getStringExtra(Intent.EXTRA_TEXT);

        if(data != null) {
            Log.e("test", data);
            tempItem.url = data;
            Log.e("test2", tempItem.url.toString());
            urlList.add(tempItem);

        }
        setupAdapter();
//        ListView listView = findViewById(R.id.listView);
//        ArrayAdapter<listItem> adapter = new ArrayAdapter<listItem>(
//                this, android.R.layout.simple_list_item_1, urlList);
//        listView.setAdapter(adapter);

    }

    private void setupAdapter(){

       final customAdapter adapter = new customAdapter(this, urlList);

       ListView listView = findViewById(R.id.list);
       listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItem item = (listItem) adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, webview.class);
                intent.putExtra("url", item.getUrl());
                startActivity(intent);
            }
        });
    }

}
