package com.example.kaigaisyusyoku4f.VO;

public class FreeboardVO {
    private String id;
    private String title;
    private String contents;
    private String dateTime;
    private String flag;
    private long count;
    private long replyCount;

    public FreeboardVO(String id) {
        this.id = id;
    }

    public FreeboardVO() {
    }

    @Override
    public String toString() {
        return "FreeboardVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", flag='" + flag + '\'' +
                ", count=" + count +
                ", replyCount=" + replyCount +
                '}';
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

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(long replyCount) {
        this.replyCount = replyCount;
    }

    public FreeboardVO(String id, String title, String contents, String dateTime, String flag, long count, long replyCount) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.dateTime = dateTime;
        this.flag = flag;
        this.count = count;
        this.replyCount = replyCount;
    }
}
