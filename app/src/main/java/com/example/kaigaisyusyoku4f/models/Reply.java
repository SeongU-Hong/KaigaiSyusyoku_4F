package com.example.kaigaisyusyoku4f.models;

public class Reply {
    public String id;
    public String reply;
    public Object dateTime;
    public String flag;

    public Reply() {
    }

    public Reply(String id, String reply, Object dateTime, String flag) {
        this.id = id;
        this.reply = reply;
        this.dateTime = dateTime;
        this.flag = flag;
    }

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
        return "Reply{" +
                "id='" + id + '\'' +
                ", reply='" + reply + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
