package com.example.kaigaisyusyoku4f.models;

public class Board {
    public String id;
    public String title;
    public String contents;
    public String dateTime;
    public String flag;
    public long count;
    public long replyCount;

    public Board() {
    }

    public Board(String id, String title, String contents, String dateTime, String flag, long count, long replyCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
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
                ", count=" + count +
                ", dateTime='" + dateTime + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}