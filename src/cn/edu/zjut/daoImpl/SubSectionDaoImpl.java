package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.SubSectionDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.SubSection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubSectionDaoImpl extends BaseHibernateDAO implements SubSectionDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public List<SubSection> findByHql(String hql) {
        log.debug("finding SubSection instance by hql");
        try {
            String queryString = hql;
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by hql failed", re);
            return null;
            //throw re;
        }
    }

    @Override
    public void save(SubSection subSection) {
        log.debug("saving SubSection instance");
        try {
            getSession().save(subSection);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public void delete(SubSection subSection) {
        log.debug("deleting SubSection instance");
        try {
            getSession().delete(subSection);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public void update(SubSection subSection) {
        log.debug("update SubSection instance");
        try {
            getSession().update(subSection);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

    @Override
    public SubSection findById(int id) {
        log.debug("finding SubSection instance by id");
        try {
            SubSection instance = (SubSection) getSession().get(SubSection.class, id);  //PO对应的类 主键
            return instance;
        } catch (RuntimeException re) {
            log.error("find by id failed", re);
            throw re;
        }
    }

    @Override
    public List findAll() {
        log.debug("finding all SubSection instances");
        try {
            String queryString = "from SubSection";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List findByMainId(int mid) {
        log.debug("finding SubSection instance by title");
        try {
            String queryString = "from SubSection as subSection where subSection.mainSectionId = :mid ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("mid", mid);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by title failed", re);
            return null;
        }
    }

    @Override
    public List findByTitle(String title) {
        log.debug("finding SubSection instance by title");
        try {
            String queryString = "from SubSection as subSection where subSection.title = :title ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setString("title", title);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by title failed", re);
            return null;
        }
    }
}
