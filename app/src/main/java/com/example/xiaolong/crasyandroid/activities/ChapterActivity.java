package com.example.xiaolong.crasyandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xiaolong.crasyandroid.R;

/**
 * Created by xiaolong on 15-8-3.
 */
public class ChapterActivity extends AppCompatActivity {
    Intent mIntent;
    String mChapter;

    private ListView mListView;
    private String[] mSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chapter);

        mIntent = getIntent();
        mChapter = mIntent.getStringExtra("chapter");

        mListView = (ListView) findViewById(R.id.section_listview);

        if (mChapter.startsWith("1.")) {
            mSection = getResources().getStringArray(R.array.first_chapter);
        } else if (mChapter.startsWith("2.")) {

        }

        this.setTitle(mChapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mSection);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChapterActivity.this, SectionListActivity.class);
                //String section = mSection[position].substring(0, 2);
                intent.putExtra("section", mSection[position]);
                startActivity(intent);

            }
        });
    }
}
