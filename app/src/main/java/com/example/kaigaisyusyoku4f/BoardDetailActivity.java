package com.example.kaigaisyusyoku4f;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class BoardDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    public BoardDetailActivity(){

    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_board_detail);
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
