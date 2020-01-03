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

    //Spring注入
    TopicService topicService;
    UserService userService;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
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

        String pageNumStr = (String) getRequest().get("page");  //获取第几页
        int pageNum = 1;
        if (pageNumStr != null)
            pageNum = Integer.parseInt(pageNumStr);

        List<Topic> topics = topicService.getTopicByUserId(user.getId(), pageNum, 10);

        getRequest().put("topics", topics);
        return "success";
    }
}
