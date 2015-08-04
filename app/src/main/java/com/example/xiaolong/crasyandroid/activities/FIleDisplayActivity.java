package com.example.xiaolong.crasyandroid.activities;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.xiaolong.crasyandroid.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by xiaolong on 15-8-4.
 */
public class FIleDisplayActivity extends AppCompatActivity {
    TextView mFileContent;
    String mTitle;
    String mContent ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_file_display);
        mFileContent = (TextView) findViewById(R.id.file_content);

        mTitle = getIntent().getStringExtra("filename");
        this.setTitle(mTitle);
        mFileContent.setText("加载中。。。");
        FileReadTast fileReadTast = new FileReadTast(this);
        fileReadTast.execute("test");

    }


    private class FileReadTast extends AsyncTask<String, String, String> {
        String line_str;
        String result;
        Context context;

        public FileReadTast(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.text)));

            try {
                while ((line_str = reader.readLine()) != null) {
                    mContent += line_str + "\n";
                    publishProgress(line_str);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return line_str;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            mFileContent.setText(mContent);
        }
    }
}
