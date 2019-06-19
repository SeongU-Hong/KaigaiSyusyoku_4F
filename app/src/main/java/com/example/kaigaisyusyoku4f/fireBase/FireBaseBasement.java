package com.example.kaigaisyusyoku4f.fireBase;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

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
//        board.setDateTime(dateTime);
//        board.setFlag("0");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(board.getId()).child(dateTime).setValue(board, new DatabaseReference.CompletionListener() {

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

    public void uploadReply(Reply reply) {
        

    }
}
