package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.TopicDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.Topic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class TopicDaoImpl extends BaseHibernateDAO implements TopicDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public Topic getTopicById(int topicId) {
        log.debug("finding Topic instance by id");
        try {
            String queryString = "from Topic as topic where topic.id = :id ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", topicId);
            return (Topic) queryObject.list().get(0);
        } catch (RuntimeException re) {
            log.error("find topic by id failed", re);
            return null;
            //throw re;
        }
    }

    @Override
    public List<Topic> findByHql(String hql) {
        return null;
    }

    @Override
    public void save(Topic topic) {
        log.debug("saving Topic instance");
        try {
            getSession().save(topic);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public void update(Topic topic) {
        log.debug("update Topic instance");
        try {
            getSession().update(topic);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

}
