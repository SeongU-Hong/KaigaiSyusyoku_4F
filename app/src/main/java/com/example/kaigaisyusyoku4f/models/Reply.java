package com.example.kaigaisyusyoku4f.models;

public class Reply {
    public String id;
    public String reply;
    public String dateTime;
    public String flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
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
        return "Reply{" +
                "id='" + id + '\'' +
                ", reply='" + reply + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
