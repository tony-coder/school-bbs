package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Objects;

public class Notice {
    private int id;
    private String title;
    private String content;
    private Timestamp createTime;
    private User userByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return id == notice.id &&
                Objects.equals(title, notice.title) &&
                Objects.equals(content, notice.content) &&
                Objects.equals(createTime, notice.createTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createTime);
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
