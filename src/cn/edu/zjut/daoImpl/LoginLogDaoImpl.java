package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.LoginLogDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.LoginLog;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginLogDaoImpl extends BaseHibernateDAO implements LoginLogDao {
    private Log log = LogFactory.getLog(UserDaoImpl.class);

    @Override
    public void save(LoginLog loginLog) throws Exception {
        log.debug("saving LoginLog instance");
        try {
            getSession().save(loginLog);
            log.debug("save successful");
        } catch (RuntimeException e) {
            log.error("save failed", e);
            throw e;
        }
    }
}
