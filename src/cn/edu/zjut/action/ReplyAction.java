package cn.edu.zjut.action;

import cn.edu.zjut.po.User;
import cn.edu.zjut.service.BlackListService;

import java.sql.Timestamp;

public class ReplyAction extends BaseAction {
    private int topicId;
    private int pageNum;
    private String content;

    //Spring注入
    private BlackListService blackListService;

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getContent() {
        return content;
    }

    public void setBlackListService(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String reply() {
        if (content == null) {
            return "error";
        }
//        int userId = (Integer) getSession().get("userId");
        User user = (User) getSession().get("user");
        int level = blackListService.getLevel(user.getId());
        if (level == 4 || level == 2 || level == 1) {
            this.addFieldError("limit", "你已被管理员限制发表回复");
            return "post";
        }
       /* Followcard followcard = new Followcard();
        followcard.setFollowContent(content);
        Post post = new Post();
        post.setId(postId);
        followcard.setPost(post);
        followcard.setFollowDate(new Timestamp(System.currentTimeMillis()));
        User user = new User();
        user.setId(userId);
        System.out.println("user id:" + userId + user.getUsername());
        user.setUsername((String) getSession().get("username"));
        followcard.setUser(user);
        followcardBiz.addReply(followcard);
        postBiz.autoIncreaseReply(postId);
        getRequest().put("postId", postId);
        getRequest().put("page", pageNum);*/
        return SUCCESS;
    }
}
