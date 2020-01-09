package cn.edu.zjut.dao;

import cn.edu.zjut.po.Topic;
import org.hibernate.Session;

import java.util.List;

public interface TopicDao {

    public Topic getTopicById(int topicId);

    public List<Topic> findByHql(String hql);

    public void save(Topic topic) throws Exception;

    public void update(Topic topic) throws Exception;

    public List<Topic> getTopicByUserId(int userId, int pageIndex, int pageSize);

    public List<Topic> getLatestTopic(int pageIndex, int pageSize);

    public void delete(Topic topic) throws Exception;

//    public Topic findById(int id);


    public List<Topic> search(String keywords);

    public void autoIncreaseViewNum(Topic topic) throws Exception;

    public void autoIncreaseReply(Topic topic) throws Exception;

    //按类型寻找帖
    public List<Topic> getTopicByType(int type, int pageIndex, int pageSize);

    //最热帖
    List<Topic> getHotTopic(int pageIndex, int pageSize);

    //最新帖
    List<Topic> getBestTopic(int pageIndex, int pageSize);
}
