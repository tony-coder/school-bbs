package cn.edu.zjut.service;

import cn.edu.zjut.po.User;

/**
 * @author
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
     * 用户是否已经存在
     *
     * @param user
     * @return 0：用户不存在 1：用户名已存在，2：用户邮箱已存在
     */
    public int isExist(User user);


}
