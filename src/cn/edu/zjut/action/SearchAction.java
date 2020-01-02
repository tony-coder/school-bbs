package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.TopicService;

import java.util.List;

/**
 * 搜索
 */

public class SearchAction extends BaseAction{

    private String keywords;
    private TopicService topicService;

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public String execute() throws Exception {
//        keywords = new String(keywords.getBytes("iso8859-1"),"utf-8");
        System.out.println(keywords);
        List<Topic> posts = topicService.searchTopics(keywords);
        getRequest().put("posts", posts);
        return SUCCESS;
    }
}
