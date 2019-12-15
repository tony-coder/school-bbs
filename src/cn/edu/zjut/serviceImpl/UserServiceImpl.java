package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.UserDao;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.UserService;

/**
 * @author 鲍锋雄
 * UserService接口实现类
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //注册用户，验证控制工作在控制层完成
    @Override
    public void register(User user) {
        userDao.save(user);
    }

    @Override
    public int isExist(User user) {
        if (userDao.findByUsername(user.getUsername()) != null)
            return 1;
        else if (userDao.findByEmail(user.getEmail()) != null)
            return 2;
        else
            return 0;
    }

    @Override
    public int activeUser(String code) {
        User user = userDao.findByActiveCode(code);
        if (user == null) {
            return 0;  //无效激活码
        }
        if (user.getHasActive() == 0) {  //用户未激活
            user.setHasActive(1);
            userDao.update(user);  //更新用户状态为激活
            return 1;  //激活成功
        } else {
            return -1;  //用户已经激活
        }
    }
}
