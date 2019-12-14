package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Objects;

public class Reply {
    private int id;
    private int topicId;
    private int userId;
    private String content;
    private Timestamp replyTime;
    private Topic topicByTopicId;
    private User userByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Timestamp replyTime) {
        this.replyTime = replyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reply reply = (Reply) o;
        return id == reply.id &&
                topicId == reply.topicId &&
                userId == reply.userId &&
                Objects.equals(content, reply.content) &&
                Objects.equals(replyTime, reply.replyTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topicId, userId, content, replyTime);
    }

    public Topic getTopicByTopicId() {
        return topicByTopicId;
    }

    public void setTopicByTopicId(Topic topicByTopicId) {
        this.topicByTopicId = topicByTopicId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
