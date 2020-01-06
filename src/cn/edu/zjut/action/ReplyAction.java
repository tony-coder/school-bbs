package cn.edu.zjut.action;

import cn.edu.zjut.po.Reply;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.BlackListService;
import cn.edu.zjut.service.ReplyService;
import cn.edu.zjut.service.TopicService;

import java.sql.Timestamp;
import java.util.List;

public class ReplyAction extends BaseAction {
    private int pageNum;
    private Reply reply;

    //Spring注入
    private BlackListService blackListService;
    private TopicService topicService;
    private ReplyService replyService;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    public void setBlackListService(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    public String reply() {
        if (reply.getContent() == null) {
            return "error";
        }
//        int userId = (Integer) getSession().get("userId");
        User user = (User) getSession().get("user");
        int level = blackListService.getLevel(user.getId());
        if (level == 4 || level == 2 || level == 1) {
            this.addFieldError("limit", "你已被管理员限制发表回复");
            return "post";
        }
        Topic topic = topicService.getTopicById(reply.getTopicByTopicId().getId());
        reply.setTopicByTopicId(topic);
        reply.setReplyTime(new Timestamp(System.currentTimeMillis()));

        reply.setUserByUserId(user);

        if (!replyService.addReply(reply))
            return "error";
        topicService.autoIncreaseReply(topic);
        pageNum = topic.getReplyNum() % 5 == 0 ? topic.getReplyNum() / 5 : topic.getReplyNum() / 5 + 1;
        List<Reply> replies = replyService.getReplies(topic.getId(), pageNum, 5);

        getRequest().put("topic", topic);
        getRequest().put("replies", replies);
        getRequest().put("pageNum", pageNum);

        return "success";
    }
}
