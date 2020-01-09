package cn.edu.zjut.po;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 用户实体类
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String sex;
    private String email;
    private String userAvatarUrl;
    private Timestamp createTime;
    private Timestamp updateTime;
    private int level;
    private String activeCode;
    private Integer hasActive;
    private String signature;
    private Integer privilege;
    private Collection<BestTopic> bestTopicsById;
    private Set<BlackList> blackListsById;
    private Collection<Notice> noticesById;
    private Collection<Reply> repliesById;
    private Collection<Topic> topicsById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }*/

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public Integer getHasActive() {
        return hasActive;
    }

    public void setHasActive(Integer hasActive) {
        this.hasActive = hasActive;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                level == user.level &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(sex, user.sex) &&
                Objects.equals(email, user.email) &&
                Objects.equals(userAvatarUrl, user.userAvatarUrl) &&
                Objects.equals(createTime, user.createTime) &&
                Objects.equals(updateTime, user.updateTime) &&
                Objects.equals(activeCode, user.activeCode) &&
                Objects.equals(hasActive, user.hasActive) &&
                Objects.equals(signature, user.signature) &&
                Objects.equals(privilege, user.privilege);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, sex, email, userAvatarUrl, createTime, updateTime, level, activeCode, hasActive, signature, privilege);
    }

    public Collection<BestTopic> getBestTopicsById() {
        return bestTopicsById;
    }

    public void setBestTopicsById(Collection<BestTopic> bestTopicsById) {
        this.bestTopicsById = bestTopicsById;
    }

//    public Collection<BlackList> getBlackListsById() {
//        return blackListsById;
//    }
//
//    public void setBlackListsById(Collection<BlackList> blackListsById) {
//        this.blackListsById = blackListsById;
//    }


    public Set<BlackList> getBlackListsById() {
        return blackListsById;
    }

    public void setBlackListsById(Set<BlackList> blackListsById) {
        this.blackListsById = blackListsById;
    }

    public Collection<Notice> getNoticesById() {
        return noticesById;
    }

    public void setNoticesById(Collection<Notice> noticesById) {
        this.noticesById = noticesById;
    }

    public Collection<Reply> getRepliesById() {
        return repliesById;
    }

    public void setRepliesById(Collection<Reply> repliesById) {
        this.repliesById = repliesById;
    }

    public Collection<Topic> getTopicsById() {
        return topicsById;
    }

    public void setTopicsById(Collection<Topic> topicsById) {
        this.topicsById = topicsById;
    }
}
