package cn.edu.zjut.service;

import cn.edu.zjut.po.User;

public interface AdminService {


    User getAdminById(Integer id);

    void updateAdmin(User admin);

    boolean existEmail(User admin);

    boolean existUsername(User admin);

    int getAdminIdByUsername(String username);

    int getAdminIdByEmail(String email);

}
