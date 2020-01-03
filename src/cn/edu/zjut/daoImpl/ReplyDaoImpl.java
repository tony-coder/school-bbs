package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.ReplyDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.Reply;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ReplyDaoImpl extends BaseHibernateDAO implements ReplyDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public List<Reply> findByHql(String hql) {
        log.debug("finding Reply instance by hql");
        try {
            String queryString = hql;
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by hql failed", re);
            return null;
        }
    }

    @Override
    public void save(Reply reply) throws Exception {
        log.debug("saving Reply instance");
        try {
            getSession().save(reply);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public void update(Reply reply) throws Exception {
        log.debug("update Reply instance");
        try {
            getSession().update(reply);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

    @Override
    public void delete(Reply reply) {
        log.debug("delete Reply instance");
        try {
            getSession().delete(reply);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public Reply findById(int id) {
        try {
            String queryString = "from Reply reply where reply.id=:id";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", id);
            return (Reply) queryObject.list().get(0);
        } catch (RuntimeException re) {
            log.error("find reply by id failed");
            return null;
        }
    }

    @Override
    public List<Reply> getReplies(int topicId, int pageIndex, int pageSize) {
        try {
            String queryString = "from Reply reply where reply.topicByTopicId.id=:topicId";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("topicId", topicId);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("get replies failed");
            return null;
        }
    }
}
