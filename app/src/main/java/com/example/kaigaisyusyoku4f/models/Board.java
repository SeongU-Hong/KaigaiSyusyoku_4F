package com.example.kaigaisyusyoku4f.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public String id;
    public String title;
    public String contents;
    public Object dateTime;
    public String flag;
    public long count;
    public long replyCount;
    public String key;
    public Object reply;

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Board() {
    }

    public Board(String id, String title, String contents, Map<String,String> dateTime, String flag, long count, long replyCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
//        dateTime.put("timestamp", ServerValue.TIMESTAMP);
        this.dateTime = dateTime;
        this.flag = flag;
        this.count = count;
        this.replyCount = replyCount;
    }

    public long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(long replyCount) {
        this.replyCount = replyCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Exclude
    public Object getDateTime() {
        return dateTime;
    }

    public void setDateTime(Object dateTime) {

        this.dateTime = dateTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", dateTime=" + dateTime +
                ", flag='" + flag + '\'' +
                ", count=" + count +
                ", replyCount=" + replyCount +
                ", key='" + key + '\'' +
                '}';
    }
}
