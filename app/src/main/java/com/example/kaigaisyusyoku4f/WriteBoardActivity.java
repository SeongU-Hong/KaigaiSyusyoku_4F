package com.example.kaigaisyusyoku4f;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.kaigaisyusyoku4f.VO.FreeboardVO;
import com.example.kaigaisyusyoku4f.fragment.FreeBoard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WriteBoardActivity extends AppCompatActivity {

    private Toolbar toolbar;
    static ArrayList<FreeboardVO> mList = FreeBoard.mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_board);
        toolbar = (Toolbar) findViewById(R.id.writeToolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);      //뒤로가기 버튼

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

                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
                String inputDateTime = sdf.format(date);

                FreeboardVO vo = new FreeboardVO("1",inputTitle,inputContent,inputDateTime,"0",0,0);

                mList.add(vo);
                finish();
            }
        });
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
