package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.MainSectionDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.MainSection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author 鲍锋雄
 */
public class MainSectionDaoImpl extends BaseHibernateDAO implements MainSectionDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public List findByHql(String hql) {
        log.debug("finding MainSection instance by hql");
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
    public void save(MainSection mainSection) {
        log.debug("saving MainSection instance");
        try {
            getSession().save(mainSection);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public void delete(MainSection mainSection) {
        log.debug("deleting MainSection instance");
        try {
            getSession().delete(mainSection);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    @Override
    public void update(MainSection mainSection) {
        log.debug("update MainSection instance");
        try {
            getSession().update(mainSection);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

    @Override
    public MainSection findById(int id) {
        log.debug("finding MainSection instance by id");
        try {
            MainSection instance = (MainSection) getSession().get(cn.edu.zjut.po.MainSection.class, id);  //PO对应的类 主键
            return instance;
        } catch (RuntimeException re) {
            log.error("find by id failed", re);
            throw re;
        }
    }

    @Override
    public List findAll() {
        log.debug("finding all MainSection instances");
        try {
            String queryString = "from MainSection";
            Query queryObject = getSession().createQuery(queryString);
            return queryObject.list();

        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    @Override
    public List findByTitle(String title) {
        log.debug("finding MainSection instance by title");
        try {
            String queryString = "from MainSection as mainSection where mainSection.title = :title ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setString("title", title);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by title failed", re);
            return null;
        }
    }

    @Override
    public List findByDesc(String desc) {
        log.debug("finding MainSection instance by desc");
        try {
            String queryString = "from MainSection as mainSection where mainSection.description = :desc ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setString("desc", desc);
            return queryObject.list();
        } catch (RuntimeException re) {
            log.error("find by desc failed", re);
            return null;
        }
    }
}
