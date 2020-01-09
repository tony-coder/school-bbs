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
    public void save(Topic topic) throws Exception {
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
    public void update(Topic topic) throws Exception {
        log.debug("update Topic instance");
        try {
            getSession().update(topic);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

    @Override
    public List<Topic> getTopicByUserId(int userId, int pageIndex, int pageSize) {
        log.debug("finding Topic instances by userId");
        String queryString = "from Topic topic where topic.userByUserId.id= :id";
        try {
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", userId);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find topics by userId failed", re);
            return null;
        }
    }

    @Override
    public List<Topic> search(String keywords) {
        String queryString = "from Topic as topic where topic.title like '%" + keywords + "%'";
        Query queryObject = getSession().createQuery(queryString);
//        queryObject.setParameter("title",keywords);
        List list = queryObject.list();
        return list;
    }

    @Override
    public void autoIncreaseViewNum(Topic topic) throws Exception {
        topic.setClick(topic.getClick() + 1);
        try {
            getSession().update(topic);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }

    @Override
    public void autoIncreaseReply(Topic topic) throws Exception {
        topic.setReplyNum(topic.getReplyNum() + 1);
        try {
            getSession().update(topic);
            log.debug("update successful");
        } catch (RuntimeException re) {
            log.error("update failed", re);
            throw re;
        }
    }


    @Override
    public List<Topic> getLatestTopic(int pageIndex, int pageSize) {
        String queryString = "from Topic topic order by topic.updateTime desc";
        try {
            Query queryObject = getSession().createQuery(queryString);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find latest topic failed", re);
            return null;
        }
    }

    @Override
    public void delete(Topic topic) throws Exception {
        try {
            getSession().delete(topic);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public List<Topic> getTopicByType(int type, int pageIndex, int pageSize) {
        if (type > 0){//大于0寻找主板块下的所有topic
            String queryString = "from Topic topic where topic.subSectionBySectionId.mainSectionByMainSectionId.id= :id";
            try {
                Query queryObject = getSession().createQuery(queryString);
                queryObject.setInteger("id", type);
                int startIndex = (pageIndex - 1) * pageSize;
                queryObject.setFirstResult(startIndex);
                queryObject.setMaxResults(pageSize);
                return queryObject.list();
            } catch (RuntimeException re) {
                log.error("find mainSection topics failed", re);
                return null;
            }
        }else if (type==-1){//最新帖
            return getLatestTopic(pageIndex, pageSize);
        }
        else if (type==-2){//精华帖
            return getBestTopic(pageIndex, pageSize);
        }else if (type==-3){//最热帖
            return getHotTopic(pageIndex, pageSize);
        }
        return null;
    }

    //最热帖
    @Override
    public List<Topic> getHotTopic(int pageIndex, int pageSize) {
        //按点击量判断
        String queryString = "from Topic topic order by topic.click desc";
        try {
            Query queryObject = getSession().createQuery(queryString);
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find hot topic failed", re);
            return null;
        }
    }

    //精华帖
    @Override
    public List<Topic> getBestTopic(int pageIndex, int pageSize) {
        //按类型判断
        String queryString = "from Topic topic where topic.type= :type";
        try {
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("type", 1);//精华帖为1
            int startIndex = (pageIndex - 1) * pageSize;
            queryObject.setFirstResult(startIndex);
            queryObject.setMaxResults(pageSize);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find best topic failed", re);
            return null;
        }
    }
}
