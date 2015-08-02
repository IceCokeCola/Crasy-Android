package com.example.xiaolong.crasyandroid.firstchapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xiaolong.crasyandroid.R;

/**
 * Created by xiaolong on 15-7-30.
 */
public class HelloWorldActivityList extends AppCompatActivity implements View.OnClickListener {

    private Button mStartProgram;
    private ExpandableListView mCodeFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_list);

        mStartProgram = (Button) findViewById(R.id.start_program);
        mCodeFile = (ExpandableListView) findViewById(R.id.code_file);

        ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            int[] filetype = new int[] {
                    R.drawable.title,
                    R.drawable.java,
                    R.drawable.xml
            };
            private String[] codeTitle = new String[] {
                    "代码文件"
            };
            private String[][] codeFile = new String[][] {
                    { "HelloWorld/src/org/crazyit/helloworld/HelloWorldActivity.java",
                    "HelloWorld/res/layout/hello_world.xml",
                    "HelloWorld/res/menu/hello_world.xml",
                    "HelloWorld/res/values/strings.xml",
                    "HelloWorld/res/values/styles.xml" }
            };
            @Override
            public int getGroupCount() {
                return codeTitle.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return codeFile[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return codeTitle[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return codeFile[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(HelloWorldActivityList.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ImageView type = new ImageView(HelloWorldActivityList.this);
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(128, 128);
                type.setLayoutParams(lp);
                type.setPadding(36, 0, 0, 0);
                type.setImageResource(filetype[0]);
                type.setVisibility(View.GONE);
                ll.addView(type);

                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);

                return ll;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                LinearLayout ll = new LinearLayout(HelloWorldActivityList.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                ll.setGravity(Gravity.CENTER_VERTICAL);
                ImageView type = new ImageView(HelloWorldActivityList.this);
                if (getChild(groupPosition,childPosition).toString().endsWith(".java")) {
                    type.setImageResource(filetype[1]);
                } else if (getChild(groupPosition,childPosition).toString().endsWith(".xml")) {
                    type.setImageResource(filetype[2]);
                }
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(64, 64);
                type.setLayoutParams(lp);

                ll.addView(type);

                TextView textView = getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());
                ll.addView(textView);
                return ll;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }

            private TextView getTextView() {
                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(HelloWorldActivityList.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(48, 0, 0, 0);
                textView.setTextSize(16);
                return textView;
            }
        };

        mCodeFile.setAdapter(adapter);

        mStartProgram.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_program:
                Intent intent = new Intent(HelloWorldActivityList.this, HelloWorldActivity.class);
                startActivity(intent);
                break;
        }
    }
}
