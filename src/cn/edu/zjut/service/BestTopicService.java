package cn.edu.zjut.service;

import cn.edu.zjut.po.BestTopic;

import java.util.List;

public interface BestTopicService {
    public boolean add(BestTopic topic);

    public List<BestTopic> getBestTopics(int pageIndex, int pageSize);

    public List<BestTopic> getBestTopics();

    //已经同意的精华帖
    public List<BestTopic> getBestTopicsAllow(int pageIndex, int pageSize);

    public void delete(int topicId);

    public boolean updateState(int topicId, int state);

    public List<BestTopic> getTopicsByUserId(int userId, int pageIndex, int pageSize);

    public boolean isExist(int topicId);
}
