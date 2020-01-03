package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.UserDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl extends BaseHibernateDAO implements UserDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public List findByHql(String hql) {
        log.debug("finding User instance by hql");
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
    public void save(User user) {
        log.debug("saving User instance");
        try {
            getSession().save(user);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }

    @Override
    public void update(User user) {
        log.debug("update User instance");
        try {
            getSession().update(user);
            log.debug("update successful");
        } catch (RuntimeException e) {
            log.error("update failed", e);
            throw e;
        }
    }

    public List findByProperty(String property, Object value) {
        log.debug("finding User instance with property: " + property + ", value: " + value);
        try {
            String queryString = "from User as user where user." + property + "= ?1";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(1, value);
            List list = queryObject.list();
            return list;
        } catch (RuntimeException re) {
            log.error("find by property failed", re);
            return null;
            //throw re;
        }
    }

    @Override
    public User findById(int id) {
        log.debug("finding User instance by Id");
        try {
            String queryString = "from User as user where user.id = :id ";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setInteger("id", id);
            return (User) queryObject.list().get(0);
        } catch (RuntimeException re) {
            log.error("find by id failed", re);
            return null;
            //throw re;
        }
    }

    @Override
    public User findByEmail(String email) {
        log.debug("finding User instance by email");
        List<User> users = findByProperty("email", email);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findByUsername(String username) {
        log.debug("finding User instance by username");
        List<User> users = findByProperty("username", username);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public User findByActiveCode(String code) {
        log.debug("finding User instance by activeCode");
        List<User> users = findByProperty("activeCode", code);
        if (users != null && users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<User> findByKeywords(String keywords) {
        String queryString = "from User user where user.username like :keyword or user.email like :keyword";
        Query query = getSession().createQuery(queryString);
        query.setParameter("keyword", '%'+keywords+'%');
        List list = query.list();
        return list;
    }

}
