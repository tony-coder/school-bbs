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

//    public void delete(User user);

//    public Topic findById(int id);

    List<Topic> search(String keywords);

    void autoIncreaseViewNum(Topic topic) throws Exception;

    public Session getSession();

}
