package com.example.kaigaisyusyoku4f;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.fragment.FreeBoard;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FreeListViewAdapter fla;
    private CommentListViewAdapter commentListViewAdapter;
    private ListView listView;
    private String key;
    private String replyCount;
    private String id;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.free_board_detail);

        fla = FreeBoard.fla;
        Intent intent = getIntent();
        TextView detailVIew_title = findViewById(R.id.detailTile);
        TextView detailVIew_contents = findViewById(R.id.detailContent);
        TextView detailVIew_hitCount = findViewById(R.id.detailHit);
        TextView detailVIew_dateTime = findViewById(R.id.detailDate);

        detailVIew_title.setText(intent.getStringExtra("title"));
        detailVIew_contents.setText(intent.getStringExtra("contents"));
        detailVIew_dateTime.setText(intent.getStringExtra("dateTime"));
        detailVIew_hitCount.setText(intent.getStringExtra("count"));



        //댓글
        key = intent.getStringExtra("key");
        replyCount = intent.getStringExtra("replyCount");
        id = intent.getStringExtra("id"); //글작성자 id


        Button goCommentWrite = findViewById(R.id.commentWrite);
        goCommentWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteComment.class);
                intent.putExtra("key",key);
                intent.putExtra("replyCount",replyCount);
                intent.putExtra("id",id);
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
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("freeboard").child(key).child("replyList");
        //아이쳄 추가
//        commentListViewAdapter.addItem("정보", "20190303", "아아!");
//        commentListViewAdapter.addItem("좋은정보", "20190513", "아오");
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                commentListViewAdapter.clear();

                for(DataSnapshot reply : dataSnapshot.getChildren()){
                    Reply comment = reply.getValue(Reply.class);
                    SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd aaa HH:mm:ss");
                    String dateTime = format.format(comment.dateTime);
                    String cId = "";
                    //댓글의 작성자가 게시글의 작성자일 경우 "작성자", 아닐 경우 "anonymous"
                    if(comment.getId().equals(id)){
                        cId = "작성자";
                    }else{
                        cId = "anonymous";
                    }
                    commentListViewAdapter.addItem(cId,dateTime,comment.getReply());
                }
                commentListViewAdapter.notifyDataSetInvalidated();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
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
                fla.notifyDataSetChanged();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}