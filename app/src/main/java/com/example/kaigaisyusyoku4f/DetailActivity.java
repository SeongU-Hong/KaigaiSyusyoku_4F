package com.example.kaigaisyusyoku4f;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FreeListViewAdapter fla;
    private CommentListViewAdapter commentListViewAdapter;
    private ListView listView;
    private String key;
    private String replyCount;
    private String id;

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
        detailVIew_hitCount.setText(String.valueOf(intent.getLongExtra("count",0)));

        //댓글
        key = intent.getStringExtra("key");
        replyCount = intent.getStringExtra("replyCount");
        id = intent.getStringExtra("id");

        Button goCommentWrite = findViewById(R.id.commentWrite);
        goCommentWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteComment.class);
                intent.putExtra("key",key);
                intent.putExtra("replyCount",replyCount);
                startActivity(intent);
            }
        });

        //댓글 리스트
        //리스트뷰
        // Adapter 생성
        commentListViewAdapter = new CommentListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listView = (ListView)findViewById(R.id.commentListView);
        listView.setAdapter(commentListViewAdapter);

        //아이쳄 추가
        commentListViewAdapter.addItem("정보", "20190303", "아아!");
        commentListViewAdapter.addItem("좋은정보", "20190513", "아오");

        //리스트뷰 클릭이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Intent intent=new Intent(DetailActivity.this, DetailActivity.class);
                //여기에 코드 작성
                // TODO : use item data.

                //게시글 상세내용 페이지 이동

                //해당 인텐트 실행
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