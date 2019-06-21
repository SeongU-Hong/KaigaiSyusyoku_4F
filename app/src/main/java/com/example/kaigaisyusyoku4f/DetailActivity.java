package com.example.kaigaisyusyoku4f;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FreeListViewAdapter fla;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_board_detail);

        fla = new FreeListViewAdapter();
        Intent intent = getIntent();
        TextView detailVIew_title = findViewById(R.id.detailTile);
        TextView detailVIew_contents = findViewById(R.id.detailContent);
        TextView detailVIew_hitCount = findViewById(R.id.detailHit);
        TextView detailVIew_dateTime = findViewById(R.id.detailDate);

        detailVIew_title.setText(intent.getStringExtra("title"));
        detailVIew_contents.setText(intent.getStringExtra("contents"));
        detailVIew_dateTime.setText(intent.getStringExtra("dateTime"));
        detailVIew_hitCount.setText(intent.getStringExtra("count"));

        Button goCommentWrite = findViewById(R.id.commentWrite);
        goCommentWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteComment.class);
                startActivity(intent);
            }
        });

        //툴바
        toolbar =(Toolbar) findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("해외취업 아카데미");
        toolbar.setSubtitle("게시글");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}