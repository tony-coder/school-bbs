package cn.edu.zjut.dao;

import cn.edu.zjut.po.User;

import java.util.List;

/**
 * @author 鲍锋雄
 * 用户数据库接口
 */

public interface UserDao {

    public List findByHql(String hql);

    public void save(User user);
    
    public void update(User user);

//    public void delete(User user);

    public User findById(int id);

    public User findByEmail(String email);

    public User findByUsername(String username);

    public User findByActiveCode(String code);
}
