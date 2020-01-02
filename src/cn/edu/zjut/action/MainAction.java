package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.TopicService;

import java.util.List;

/**
 * 主页控制
 */
public class MainAction extends BaseAction {
    TopicService topicService;

    //Spring注入
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    //初始化首页推荐的帖子列表按照时间顺序
    public String initHomePage() throws Exception {
        List<Topic> latestPosts = topicService.getLatestTopic(1, 6);
        getRequest().put("topics", latestPosts);
        return "success";
    }
}