package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.BestTopicDao;
import cn.edu.zjut.po.BestTopic;
import cn.edu.zjut.service.BestTopicService;

import java.util.List;

public class BestTopivServiceImpl implements BestTopicService {
    BestTopicDao bestTopicDao;

    public void setBestTopicDao(BestTopicDao bestTopicDao) {
        this.bestTopicDao = bestTopicDao;
    }

    @Override
    public boolean add(BestTopic topic) {
        try {
            bestTopicDao.save(topic);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BestTopic> getBestTopics(int pageIndex, int pageSize) {
        return bestTopicDao.getBestTopics(pageIndex, pageSize);
    }

    @Override
    public List<BestTopic> getBestTopics() {
        return bestTopicDao.getBestTopics();
    }

    @Override
    public void delete(int postId) {

    }

    @Override
    public boolean updateState(int topicId, int state) {
        try {
            bestTopicDao.updateState(topicId, state);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<BestTopic> getTopicsByUserId(int userId, int pageIndex, int pageSize) {
        return bestTopicDao.getTopicsByUserId(userId, pageIndex, pageSize);
    }

    @Override
    public boolean isExist(int topicId) {
        return bestTopicDao.isExist(topicId);
    }
}
