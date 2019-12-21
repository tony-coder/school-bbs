package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.TopicDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.Topic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

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
//            throw re;
            return null;
        }
    }
}
