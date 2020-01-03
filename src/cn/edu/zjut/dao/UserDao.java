package cn.edu.zjut.dao;

import cn.edu.zjut.po.User;

import java.util.List;


public interface UserDao {

    public List<User> findByHql(String hql);

    public void save(User user);
    
    public void update(User user);

//    public void delete(User user);

    public User findById(int id);

    public User findByEmail(String email);

    public User findByUsername(String username);

    public User findByActiveCode(String code);

    public List<User> findByKeywords(String keywords);
}
