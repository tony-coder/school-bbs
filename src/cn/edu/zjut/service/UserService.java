package cn.edu.zjut.service;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;

import java.util.List;

/**
 * @author 鲍锋雄
 * 用户服务接口
 */

public interface UserService {
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public void register(User user);

    /**
     * 用户是否已经存在，防止相同信息重复注册
     *
     * @param user
     * @return 0：用户不存在 1：用户名已存在，2：用户邮箱已存在
     */
    public int isExist(User user);

    /**
     * 激活用户
     *
     * @param code
     * @return
     */
    public int activeUser(String code);

    /**
     * 查询用户
     *
     * @param user
     * @return -3:用户不存在 -2:用户未激活 -1:用户名 密码不匹配 正数:成功登录
     */
    public int login(User user);

    /**
     * 查询用户
     *
     * @param user
     * @return -3:用户不存在 -2:用户未激活 -1:用户名 密码不匹配 正数:成功登录
     */
    public int adminLogin(User user);

    /**
     * 根据用户ID 查询用户
     *
     * @param id
     * @return
     */
    public User findById(Integer id);


}
