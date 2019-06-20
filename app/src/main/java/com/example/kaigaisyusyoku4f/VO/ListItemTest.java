package com.example.kaigaisyusyoku4f.VO;

public class ListItemTest {
    private String title;
    private int hit;
    private int comment;
    private String date;

    public ListItemTest() {
    }

    public ListItemTest(String title, int hit, int comment, String date) {
        this.title = title;
        this.hit = hit;
        this.comment = comment;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public int getHit() {
        return hit;
    }

    public int getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ListItemTest{" +
                "title='" + title + '\'' +
                ", hit=" + hit +
                ", comment=" + comment +
                ", date='" + date + '\'' +
                '}';
    }
}
