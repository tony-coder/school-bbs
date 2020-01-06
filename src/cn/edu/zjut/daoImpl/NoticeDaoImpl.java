package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.NoticeDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.Notice;
import cn.edu.zjut.po.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.query.Query;

import java.util.List;

public class NoticeDaoImpl extends BaseHibernateDAO implements NoticeDao {
    private Log log = LogFactory.getLog(User.class);

    @Override
    public void publish(Notice notice) {
        log.debug("saving notice instance");
        try {
            getSession().save(notice);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        String queryString = "from Notice notice where notice.id=?1";
        Query query = getSession().createQuery(queryString);
        query.setParameter(1,noticeId);
        List<Notice> list = query.list();
        if(list.size()>0){
            return list.get(0);
        }else{
            return null;
        }



    }
}
