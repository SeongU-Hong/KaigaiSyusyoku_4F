package com.example.kaigaisyusyoku4f.fireBase;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.kaigaisyusyoku4f.models.Board;
import com.example.kaigaisyusyoku4f.models.Reply;
import com.google.firebase.database.ChildEventListener;
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
import java.util.Map;
import java.util.Set;

public class FireBaseBasement {

    private Date today = new Date();
    private SimpleDateFormat date = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ssSS");

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;



    /*public void initDatabase() {
        mDatabase = FirebaseDatabase.getInstance();

        mReference = mDatabase.getReference("log");
        mReference.child("log").setValue("check");
        mChild = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);


    }*/

    // 게시글 업로드
    public void uploadBoard(Board board, String boardName) {

        String dateTime = date.format(today);
        board.setDateTime(dateTime);
        board.setFlag("0");
        mReference = mDatabase.getInstance().getReference();
        String key = mReference.child("post").child(boardName).push().getKey();
        Map<String, Object> postValues = new HashMap<String, Object>();

        postValues.put("id", board.getId());
        postValues.put("title", board.getTitle());
        postValues.put("contents", board.getContents());
        postValues.put("dateTime", board.getDateTime());
        postValues.put("count", board.getCount());
        postValues.put("replyCount", board.getReplyCount());
        postValues.put("flag", board.getFlag());

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/post/"+ boardName + "/" + key, postValues);
        childUpdates.put("/user_post/" + board.getId() + "/" + key, postValues);

        mReference.updateChildren(childUpdates);
    }

    List<HashMap<String, Object>> array = new ArrayList<>();
    public List listBoard(String boardName) {


        mReference = mDatabase.getReference("post/"+boardName+"/");
        mReference.addValueEventListener(new ValueEventListener() {

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

    public void countBoard(String key, String boardName) {
        mReference = mDatabase.getReference("post/"+boardName+"/");

    }

    public Board findBoard(String key, String boardName) {
        Board board = new Board();
        List<HashMap> findList = listBoard(boardName);
        for (int i = 0; i < findList.size(); i++) {
            HashMap map = findList.get(i);
            Set<String> keys = map.keySet();
            for(String k:keys) {
                if(key.equals(k)) {
                    board = (Board) map.get(k);
                    return board;
                }
            }
        }

        return board;
    }

    public void deleteBoard(Board board) {
        mReference = FirebaseDatabase.getInstance().getReference();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("flag", "1");
        mReference.child(board.getId()).child(board.getDateTime()).updateChildren(childUpdates, new DatabaseReference.CompletionListener() {

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

    public void uploadReply(Reply reply, Board board) {
        String dateTime = date.format(today);
        reply.setDateTime(dateTime);
        reply.setFlag("0");
        mReference = FirebaseDatabase.getInstance().getReference();
        mReference.child(board.getId()).child(board.getDateTime()).child("reply").child(reply.getId()).child(reply.getDateTime()).setValue(reply, new DatabaseReference.CompletionListener() {
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
