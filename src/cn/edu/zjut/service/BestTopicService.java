package cn.edu.zjut.service;

import cn.edu.zjut.po.BestTopic;

import java.util.List;

public interface BestTopicService {
    public boolean add(BestTopic topic);

    public List<BestTopic> getBestTopics(int pageIndex, int pageSize);

    public List<BestTopic> getBestTopics();

    public void delete(int topicId);

    public boolean updateState(int topicId, int state);

    public List<BestTopic> getTopicsByUserId(int userId, int pageIndex, int pageSize);

    public boolean isExist(int topicId);
}
