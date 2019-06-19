package com.example.kaigaisyusyoku4f.fireBase;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FireBaseBasement {

//    private String id = "";
//    private Date today = new Date();
//    private SimpleDateFormat date = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ssSS");
//    private String title = "";
//    private String contents = "";
//
//    private String flag = "0";
    private DatabaseReference mDatabase;





    public void uploadBoard(Board board) {

        String dateTime = board.getDateTime();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.child("post").push().getKey();
        mDatabase.child("post").child(key).setValue(board, new DatabaseReference.CompletionListener() {

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });
        mDatabase.child("post_user").child(board.getId()).child(key).setValue(board);
    }


    //게시글 리스트
    List<HashMap<String, Object>> array = new ArrayList<>();
    public List listBoard(String boardName) {


        mDatabase = FirebaseDatabase.getInstance().getReference(boardName);
        mDatabase.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postData: dataSnapshot.getChildren()) {
                    HashMap<String, Object> boardData = new HashMap<>();
                    Board board = postData.getValue(Board.class);
                    boardData.put(postData.getKey(), board);
                    array.add(boardData);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return array;
    }


    public void uploadReply(Reply reply, String path) {
        String dateTime = reply.dateTime;
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(path).setValue(reply, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if (databaseError != null) {
                    System.out.println("Data could not be saved " + databaseError.getMessage());
                } else {
                    System.out.println("Data saved successfully.");
                }
            }
        });

    }
}
