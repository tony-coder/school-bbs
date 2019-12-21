package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.TopicService;

/**
 * 发帖管理action
 */

public class TopicAction extends BaseAction {
    private Integer topicId;  //帖子id

    //Spring注入
    TopicService topicService;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public String postDetail() {
        if (topicId != null) {
//            System.out.println("id"+topicId);
            Topic topic = topicService.getTopicById(topicId);
            if (topic == null)
                return ERROR;
            getRequest().put("topic", topic);
            return "success";
        }
        return "error";
    }
}
