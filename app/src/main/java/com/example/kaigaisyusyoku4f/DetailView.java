package com.example.kaigaisyusyoku4f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.adapter.FreeListViewAdapter;
import com.example.kaigaisyusyoku4f.models.Reply;

import java.util.ArrayList;

public class DetailView extends Activity {
    public static ArrayList<Reply> cList;
    static ListView cListView;
    static ArrayAdapter mAdapter;
    public FreeListViewAdapter fla;
    private Toolbar toolbar;


    private TextView detailVIew_title;
    private TextView detailVIew_contents;
    private TextView detailVIew_hitCount;
    private TextView detailVIew_dateTime;
    private Button goCommentWrite;
    private Button goBack;


    void set(){
        detailVIew_title = findViewById(R.id.detailVIew_title);
        detailVIew_contents = findViewById(R.id.detailVIew_contents);
        detailVIew_hitCount = findViewById(R.id.detailVIew_hitCount);
        detailVIew_dateTime = findViewById(R.id.detailVIew_dateTime);
        goBack = findViewById(R.id.goBack);
        goCommentWrite = findViewById(R.id.writeComment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        set();

        fla = new FreeListViewAdapter();

        Intent intent = getIntent();

        detailVIew_title.setText(intent.getStringExtra("title"));
        detailVIew_contents.setText(intent.getStringExtra("contents"));
        detailVIew_dateTime.setText(intent.getStringExtra("dateTime"));
        detailVIew_hitCount.setText(intent.getStringExtra("count"));


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final String key = intent.getStringExtra("key");
        final String replyCount = intent.getStringExtra("replyCount");
        final String id = intent.getStringExtra("id");

        goCommentWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WriteComment.class);
                intent.putExtra("key", key);
                intent.putExtra("replyCount", replyCount);
                startActivity(intent);
            }
        });
    }
}
