package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Objects;

public class BestTopic {
    private int id;
    private Timestamp createTime;
    private Integer state;
    private Topic topicByTopicId;
    private User userByUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestTopic bestTopic = (BestTopic) o;
        return id == bestTopic.id &&
                Objects.equals(createTime, bestTopic.createTime) &&
                Objects.equals(state, bestTopic.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, state);
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
