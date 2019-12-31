package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.AdminDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class AdminDaoImpl extends BaseHibernateDAO implements AdminDao {
    private Log log = LogFactory.getLog(User.class);


    public List findByProperty(String propertyName, Object value){
        try{
            String queryString = "from User as admin where admin."+propertyName+"= ?";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0,value);
            List list = queryObject.list();
            return list;
        }catch(RuntimeException re){
            log.error(re);
            throw re;
        }
    }


    @Override
    public User getAdminById(Integer id) {
        log.debug("get admin by id"+id);
        try{
            User admin = (User) getSession().get(User.class,id);
            return admin;
        }catch(RuntimeException re){
            log.error("get by id failed", re);
            throw re;
        }
    }

    @Override
    public void updateAdmin(User admin) {
        try{
            log.debug("update Admin instance");
            getSession().update(admin);
            log.debug("update Admin successfully");
        }catch(RuntimeException re){
            log.error("update Admin failed",re);
        }

    }

    @Override
    public int exist(User user) {
        return 0;
    }

    @Override
    public int getAdminIdByUsername(String username) {
        List<User> adminList = findByProperty("username",username);
        return adminList.get(0).getId();
    }

    @Override
    public int getAdminIdByEmail(String email) {
        List<User> adminList = findByProperty("email",email);
        return adminList.get(0).getId();
    }
}
