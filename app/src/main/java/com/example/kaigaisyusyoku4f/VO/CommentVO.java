package com.example.kaigaisyusyoku4f.VO;

public class CommentVO {
    private int cNum;
    private int number;
    private String comment;
    private String time;
    private int reFlag;

    public CommentVO() {
    }

    public CommentVO(String comment, String time, int reFlag) {

        this.comment = comment;
        this.time = time;
        this.reFlag = reFlag;
    }

    public int getcNum() {
        return cNum;
    }

    public void setcNum(int cNum) {
        this.cNum = cNum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "CommentVO{" +
                "comment='" + comment + '\'' +
                ", time='" + time + '\'' +
                ", reFlag=" + reFlag +
                '}';
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getReFlag() {
        return reFlag;
    }

    public void setReFlag(int reFlag) {
        this.reFlag = reFlag;
    }
}
