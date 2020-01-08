package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.LoginLogDao;
import cn.edu.zjut.po.LoginLog;
import cn.edu.zjut.service.LoginLogService;

public class LoginLogServiceImpl implements LoginLogService {
    private LoginLogDao loginLogDao;

    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    @Override
    public void log(LoginLog loginLog) {
        try {
            loginLogDao.save(loginLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
