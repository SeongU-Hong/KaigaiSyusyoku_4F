package com.example.kaigaisyusyoku4f.fireBase;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

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

//        String dateTime = board.getDateTime();
//        board.setDateTime(dateTime);
//        board.setFlag("0");
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = mDatabase.push().getKey();
        board.setKey(key);
        mDatabase.child("freeboard").child(key)
                .setValue(board, new DatabaseReference.CompletionListener() {

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
    public void updateBoard(Board board){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String key = board.getKey();
        mDatabase.child("freeboard").child(key).child("count")
                .setValue(board.getCount(), new DatabaseReference.CompletionListener() {

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

    public void testMethod(){
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("freeboard").child("write").exists()){
                        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");


                        Log.e(format.format(child.child("freeboard").child("write").child("dateTime").getValue()) + "","dateTime");

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
