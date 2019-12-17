package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.UserDao;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.UserService;

import java.util.List;

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

    @Override
    public int login(User user) {
        //根据用户名查找用户
        String hql = "from user where username='"+ user.getUsername() +"'";
        List<User> users = userDao.findByHql(hql);
        if (users != null && users.size() > 0){     //判断查询是否有返回值
            if (users.get(0).getHasActive() ==0)
                return -2;                      //如果用户未激活，返回 -2
            if (users.get(0).getPassword().equals(user.getPassword()))
                return users.get(0).getId();    //用户名 密码匹配，返回 用户ID
            return -1;          //用户名 密码不匹配，返回 -1
        }else
            return -3;       //无返回值说明用户不存在，返回 -3
    }

    @Override
    public User findById(Integer id) {
        User user = userDao.findById(id);
        return user;
    }
}
