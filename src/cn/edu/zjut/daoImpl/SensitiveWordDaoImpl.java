package cn.edu.zjut.daoImpl;

import cn.edu.zjut.dao.SensitiveWordDao;
import cn.edu.zjut.db.BaseHibernateDAO;
import cn.edu.zjut.po.SensitiveWord;
import org.hibernate.query.Query;

import java.util.List;

public class SensitiveWordDaoImpl extends BaseHibernateDAO implements SensitiveWordDao {


    @Override
    public void save(SensitiveWord sensetiveWord) {
        try{
            getSession().save(sensetiveWord);
        }catch (RuntimeException re){
            throw re;
        }
    }

    @Override
    public void delete(SensitiveWord sensitiveWord) {
        try{
            getSession().delete(sensitiveWord);
        }catch (RuntimeException re){
            throw re;
        }
    }

    @Override
    public void update(SensitiveWord sensitiveWord) {
        try{
            getSession().update(sensitiveWord);
        }catch (RuntimeException re){
            throw re;
        }
    }

    @Override
    public List findById(int id) {
        String queryString = "from SensitiveWord as Sword where Sword.id = ?1";
        try{
            Query query = getSession().createQuery(queryString);
            query.setParameter(1,id);
            List list = query.list();
            return list;
        }catch (RuntimeException re){
            throw re;
        }
    }

    @Override
    public List findAll() {
        String queryString = "from SensitiveWord";
        try{
            Query query = getSession().createQuery(queryString);
            List list = query.list();
            return list;
        }catch (RuntimeException re){
            throw re;
        }
    }
}
