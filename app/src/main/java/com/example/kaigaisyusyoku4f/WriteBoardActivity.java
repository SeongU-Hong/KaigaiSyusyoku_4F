package com.example.kaigaisyusyoku4f;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.example.kaigaisyusyoku4f.fireBase.FireBaseBasement;
import com.example.kaigaisyusyoku4f.fragment.FreeBoard;
import com.example.kaigaisyusyoku4f.models.Board;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class WriteBoardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    static ArrayList<Board> mList = FreeBoard.mList;
    static ArrayList<String> List = FreeBoard.List;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.write_board);
        toolbar =(Toolbar) findViewById(R.id.writeToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle("해외취업 아카데미");
        toolbar.setSubtitle("글작성");

        FloatingActionButton send = findViewById(R.id.sendFab);

        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView title = findViewById(R.id.freeBoard_title);
                TextView contents = findViewById(R.id.freeBoard_contents);
                String inputTitle = title.getText().toString();
                String inputContent = contents.getText().toString();

                Board vo = new Board("1",inputTitle,inputContent,ServerValue.TIMESTAMP,"0",0,0);

                FireBaseBasement fbb = new FireBaseBasement();
                fbb.uploadBoard(vo);
//                System.out.println("vo업로드");

                finish();
        }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
