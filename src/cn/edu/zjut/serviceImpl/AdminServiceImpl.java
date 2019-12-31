package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.AdminDao;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.AdminService;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao;

    public void setAdminDao(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Override
    public User getAdminById(Integer id) {
        return adminDao.getAdminById(id);
    }

    @Override
    public void updateAdmin(User admin) {
        adminDao.updateAdmin(admin);
    }


}
