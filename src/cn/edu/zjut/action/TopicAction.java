package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.TopicService;
import cn.edu.zjut.service.UserService;

import java.util.List;

/**
 * 帖子管理action
 */

public class TopicAction extends BaseAction {
    private Integer topicId;  //帖子id
    private Integer page;     //页数

    //Spring注入
    TopicService topicService;
    UserService userService;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String postDetail() throws Exception {
        if (topicId != null) {
//            System.out.println("id"+topicId);
            Topic topic = topicService.getTopicById(topicId);
            if (topic == null)
                return "error";
            topicService.autoIncreaseViewNum(topic);  //浏览数+1
            getRequest().put("topic", topic);
            return "success";
        }
        return "error";
    }

    public String initTopicPage() throws Exception {
        getSession().put("user", userService.findById(17));  //测试数据
        User user = (User) getSession().get("user");

        if (page == null)
            page = 1;

        List<Topic> topics = topicService.getTopicByUserId(user.getId(), page, 10);

        getRequest().put("topics", topics);
        getRequest().put("pageNum", page);
        return "success";
    }
}
