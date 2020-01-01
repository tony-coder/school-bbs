package cn.edu.zjut.dao;

import cn.edu.zjut.po.User;

import java.util.List;

public interface AdminDao {
    User getAdminById(Integer id);

    void updateAdmin(User user);

    boolean existEmail(User user);

    boolean existUsername(User user);

    int getAdminIdByUsername(String username);

    int getAdminIdByEmail(String email);

}
