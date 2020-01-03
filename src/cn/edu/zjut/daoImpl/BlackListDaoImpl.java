package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.BlackListDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.BlackList;
import cn.edu.zjut.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BlackListDaoImpl  extends BaseHibernateDAO implements BlackListDao {

    private Log log = LogFactory.getLog(User.class);


    @Override
    public void save(BlackList blackListItem) {
        log.debug("saving BlackList instance");
        try {
            getSession().save(blackListItem);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public void update(BlackList blackListItem) {
        String queryString = "from BlackList blackList where blackList.userByUserId.id = ?1";
        Query query = getSession().createQuery(queryString);
        query.setParameter(1,blackListItem.getUserByUserId().getId());
        BlackList newBlackList = (BlackList)query.list().get(0);
        newBlackList.setLevel(blackListItem.getLevel());
        getSession().update(newBlackList);
    }

    @Override
    public void delete(int userId) {
        int level = getLevel(userId);
        if(level>0){
            String queryString = "from BlackList blackList where blackList.userByUserId.id = ?1";
            Query query = getSession().createQuery(queryString);
            query.setParameter(1,userId);
            BlackList newBlackList = (BlackList)query.list().get(0);
            getSession().delete(newBlackList);
        }
    }

    @Override
    public int getLevel(int userId) {
        String queryString = "from BlackList blackList where blackList.userByUserId.id = ?1";
        Query query = getSession().createQuery(queryString);
        query.setParameter(1,userId);
        List list = query.list();

        if(list!=null && list.size()>0){
            BlackList blackList = (BlackList)list.get(0);
            return blackList.getLevel();
        }
        return -1;
    }
}
