package com.example.kaigaisyusyoku4f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.kaigaisyusyoku4f.fireBase.FireBaseBasement;
import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class WriteComment extends Activity {
    public WriteComment() {
        super();
    }

    String key;
    String replyCount;
    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_comment);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        replyCount = intent.getStringExtra("replyCount");
//        Integer.parseInt(replyCount)+1;
//        System.out.println("key = "+ key);
        Button writeReply = findViewById(R.id.writeReply);

        writeReply.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TextView reply = findViewById(R.id.reply);
                String inputReply = reply.getText().toString();

                Reply rp = new Reply("1",inputReply,ServerValue.TIMESTAMP,"0");

                FireBaseBasement fbb = new FireBaseBasement();
                fbb.uploadReply(key,rp);
//                fbb.plusReply(key,replyCount);

                finish();
            }
        });

    }
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);

        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()== MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }



}
