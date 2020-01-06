package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

public class Topic {
    private int id;
    private String title;
    private String content;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int click;
    private Integer replyNum;
    private Integer type;
    private Collection<BestTopic> bestTopicsById;
    private Collection<Reply> repliesById;
    private SubSection subSectionBySectionId;
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

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return id == topic.id &&
                click == topic.click &&
                Objects.equals(title, topic.title) &&
                Objects.equals(content, topic.content) &&
                Objects.equals(createTime, topic.createTime) &&
                Objects.equals(updateTime, topic.updateTime) &&
                Objects.equals(replyNum, topic.replyNum) &&
                Objects.equals(type, topic.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createTime, updateTime, click, replyNum, type);
    }

    public Collection<BestTopic> getBestTopicsById() {
        return bestTopicsById;
    }

    public void setBestTopicsById(Collection<BestTopic> bestTopicsById) {
        this.bestTopicsById = bestTopicsById;
    }

    public Collection<Reply> getRepliesById() {
        return repliesById;
    }

    public void setRepliesById(Collection<Reply> repliesById) {
        this.repliesById = repliesById;
    }

    public SubSection getSubSectionBySectionId() {
        return subSectionBySectionId;
    }

    public void setSubSectionBySectionId(SubSection subSectionBySectionId) {
        this.subSectionBySectionId = subSectionBySectionId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
