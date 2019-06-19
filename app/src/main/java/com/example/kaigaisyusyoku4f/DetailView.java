package com.example.kaigaisyusyoku4f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.VO.CommentVO;
import com.example.kaigaisyusyoku4f.VO.FreeboardVO;
import com.example.kaigaisyusyoku4f.fireBase.FireBaseBasement;
import com.example.kaigaisyusyoku4f.fragment.FreeBoard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DetailView extends Activity {
    public static ArrayList<CommentVO> cList;
    static ListView cListView;
    static ArrayAdapter mAdapter;

    private Toolbar toolbar;
      @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        Intent intent = getIntent();
        TextView detailVIew_title = findViewById(R.id.detailVIew_title);
        TextView detailVIew_contents = findViewById(R.id.detailVIew_contents);
        TextView detailVIew_hitCount = findViewById(R.id.detailVIew_hitCount);
        TextView detailVIew_dateTime = findViewById(R.id.detailVIew_dateTime);

        detailVIew_title.setText(intent.getStringExtra("title"));
        detailVIew_contents.setText(intent.getStringExtra("contents"));
        detailVIew_dateTime.setText(intent.getStringExtra("dateTime"));
        detailVIew_hitCount.setText(intent.getStringExtra("count"));

        Button goBack = findViewById(R.id.goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Button goCommentWrite = findViewById(R.id.writeComment);
        goCommentWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteComment.class);
                startActivity(intent);
            }
        });

        cList = new ArrayList();
        cListView = (ListView) findViewById(R.id.commentList);
        mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cList);
        cListView.setAdapter(mAdapter);


    }



}
