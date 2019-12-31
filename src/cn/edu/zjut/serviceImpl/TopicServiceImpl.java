package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.TopicDao;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.TopicService;

public class TopicServiceImpl implements TopicService {
    TopicDao topicDao;

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    @Override
    public Topic getTopicById(int topicId) {
        return topicDao.getTopicById(topicId);
    }

    @Override
    public void publish(Topic topic) {
        topicDao.save(topic);
    }
}
