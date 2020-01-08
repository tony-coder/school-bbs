package cn.edu.zjut.action;

import cn.edu.zjut.po.Reply;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.ReplyService;
import cn.edu.zjut.service.TopicService;
import cn.edu.zjut.service.UserService;

import java.util.List;

/**
 * 帖子管理action
 */

public class TopicAction extends BaseAction {
    private Integer topicId;  //帖子id
    private Integer pageNum;     //页数

    //Spring注入
    private TopicService topicService;
    private UserService userService;
    private ReplyService replyService;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setReplyService(ReplyService replyService) {
        this.replyService = replyService;
    }

    public String postDetail() throws Exception {
        if (topicId != null) {
//            System.out.println("id"+topicId);
            Topic topic = topicService.getTopicById(topicId);

            if (pageNum == null) {
                pageNum = 1;
            }

            List<Reply> replies = replyService.getReplies(topicId, pageNum, 5);

            if (topic == null)
                return "error";
            topicService.autoIncreaseViewNum(topic);  //浏览数+1
            getRequest().put("topic", topic);
            getRequest().put("replies", replies);
            getRequest().put("pageNum", pageNum);
            return "success";
        }
        return "error";
    }

    public String initTopicPage() throws Exception {
        //getSession().put("user", userService.findById(17));  //测试数据
        User user = (User) getSession().get("user");
        if (pageNum == null)
            pageNum = 1;
        List<Topic> topics = topicService.getTopicByUserId(user.getId(), pageNum, 10);

        getRequest().put("topics", topics);
        getRequest().put("pageNum", pageNum);
        return "success";
    }

}
