package com.example.kaigaisyusyoku4f.fireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FireBaseBasement {
    private String id = "";
    private Date today = new Date();
    private SimpleDateFormat date = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ssSS");
    private String title = "";
    private String contents = "";
    private String flag = "0";
    private DatabaseReference mDatabase;



    public void uploadBoard(String id, String title, String contents) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        String dateTime = date.format(today);
        HashMap<String, Object> board = new HashMap<String, Object>();
        board.put("title", title);
        board.put("contents", contents);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(id).child(dateTime).setValue(board);
    }
}
