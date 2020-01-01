package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.AdminDao;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.AdminService;

public class AdminServiceImpl implements AdminService {


    private AdminDao adminDAO;


    public AdminDao getAdminDAO() {
        return adminDAO;
    }

    public void setAdminDAO(AdminDao adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public User getAdminById(Integer id) {
        return adminDAO.getAdminById(id);
    }

    @Override
    public void updateAdmin(User admin) {
        adminDAO.updateAdmin(admin);
    }

    @Override
    public boolean existEmail(User admin) {
        return adminDAO.existEmail(admin);
    }

    @Override
    public boolean existUsername(User admin) {
        return adminDAO.existUsername(admin);
    }

    @Override
    public int getAdminIdByUsername(String username) {
        return adminDAO.getAdminIdByUsername(username);
    }

    @Override
    public int getAdminIdByEmail(String email) {
        return adminDAO.getAdminIdByEmail(email);
    }


}
