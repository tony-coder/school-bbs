package cn.edu.zjut.service;

import cn.edu.zjut.po.Topic;

public interface TopicService {
    /**
     * 获取指定Id的帖子
     *
     * @param topicId
     * @return
     */
    public Topic getTopicById(int topicId);

    /**
     * 向帖子表中增加一条记录
     *
     * @param topic
     */
    public void pushlish(Topic topic);
}
