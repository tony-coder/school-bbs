package cn.edu.zjut.dao;

import cn.edu.zjut.po.User;

import java.util.List;

public interface AdminDao {
    User getAdminById(Integer id);

    void updateAdmin(User user);

    int exist(User user);

    int getAdminIdByUsername(String username);

    int getAdminIdByEmail(String email);

}
