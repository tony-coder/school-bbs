package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.BestTopicDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.BestTopic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BestTopicDaoImpl extends BaseHibernateDAO implements BestTopicDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public void save(BestTopic topic) throws Exception {
        log.debug("saving BestTopic instance");
        try {
            getSession().save(topic);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public List<BestTopic> getBestTopics(int pageIndex, int pageSize) {
        try {
            String queryString = "from BestTopic topic order by topic.createTime desc";
            Query queryObject = getSession().createQuery(queryString);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find latest bestTopic failed", re);
            return null;
        }

    }

    @Override
    public List<BestTopic> getBestTopics() {
        try {
            String queryString = "from BestTopic topic";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find bestTopic failed", re);
            return null;
        }
    }

    @Override
    public List<BestTopic> getBestTopicsAllow(int pageIndex, int pageSize) {
        try {
            String queryString = "from BestTopic topic where topic.state =2 order by topic.createTime desc";
            Query queryObject = getSession().createQuery(queryString);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find latest allow bestTopic failed", re);
            return null;
        }
    }

    @Override
    public void delete(int postId) {

    }

    @Override
    public void updateState(int topicId, int state) throws Exception {
        try {
            String queryString = "from BestTopic topic where topic.topicByTopicId.id = :id";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", topicId);
            List<BestTopic> bestTopics = queryObject.list();

            if (bestTopics != null && bestTopics.size() > 0) {
                BestTopic bestTopic = bestTopics.get(0);
                bestTopic.setState(state);
                getSession().update(bestTopics);
            }
        } catch (RuntimeException re) {
            log.error("update state failed", re);
        }
    }

    @Override
    public List<BestTopic> getTopicsByUserId(int userId, int pageIndex, int pageSize) {
        try {
            String queryString = "from BestTopic topic where topic.userByUserId.id = :id";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", userId);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("get best topic by userId error");
            return null;
        }
    }

    @Override
    public boolean isExist(int topicId) {
        try {
            String queryString = "from BestTopic topic where topic.topicByTopicId.id = :id";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", topicId);
            List<BestTopic> topics = queryObject.list();
            return topics != null && topics.size() > 0;
        } catch (RuntimeException re) {
            log.error("find bestTopic by topicId error");
            return false;
        }
    }
}
