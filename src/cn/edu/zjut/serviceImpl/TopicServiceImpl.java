package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.TopicDao;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.TopicService;
import org.hibernate.Transaction;

import java.util.List;

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
    public boolean publish(Topic topic) {
        try {
            topicDao.save(topic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateTopic(Topic topic) {
        try {
            topicDao.update(topic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Topic> getTopicByUserId(int userId, int pageIndex, int pageSize) {
        return topicDao.getTopicByUserId(userId, pageIndex, pageSize);
    }

    @Override
    public List<Topic> getLatestTopic(int pageIndex, int pageSize) {
        return topicDao.getLatestTopic(pageIndex, pageSize);
    }

    @Override
    public List<Topic> searchTopics(String keywords) {
        return topicDao.search(keywords);
    }

    @Override
    public void autoIncreaseViewNum(Topic topic) {
        try {
            topicDao.autoIncreaseViewNum(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void autoIncreaseReply(Topic topic) {
        try {
            topicDao.autoIncreaseReply(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Topic> getTopicByType(int type, int pageIndex, int pageSize) {
        return topicDao.getTopicByType(type,pageIndex,pageSize);
    }
}
