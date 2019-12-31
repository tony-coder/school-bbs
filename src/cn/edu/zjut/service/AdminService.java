package cn.edu.zjut.service;

import cn.edu.zjut.po.User;

public interface AdminService {
    User getAdminById(Integer id);

    void updateAdmin(User admin);
}
