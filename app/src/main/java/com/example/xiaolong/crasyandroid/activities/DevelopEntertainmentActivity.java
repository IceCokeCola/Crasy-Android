package com.example.xiaolong.crasyandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xiaolong.crasyandroid.R;
import com.example.xiaolong.crasyandroid.firstchapter.HelloWorldList;

/**
 * Created by xiaolong on 15-7-29.
 */
public class DevelopEntertainmentActivity extends AppCompatActivity {

    private ListView mListView;
    private String[] mSection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chapter);

        mListView = (ListView) findViewById(R.id.section_listview);
        mSection = getResources().getStringArray(R.array.first_chapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mSection);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mSection[position].startsWith("1.4")) {

                    Intent intent = new Intent(DevelopEntertainmentActivity.this, HelloWorldList.class);
                    startActivity(intent);
                }
            }
        });

    }
}
