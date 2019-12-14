package cn.edu.zjut.action;

import cn.edu.zjut.po.User;
import cn.edu.zjut.service.UserService;
import cn.edu.zjut.util.MailUtil;
import cn.edu.zjut.util.Utils;

import java.sql.Timestamp;

/**
 * @author 鲍锋雄
 * 用户控制action
 */

public class UserAction extends BaseAction {
    //    private User user;
    private String username;
    private String password;
    private String email;
    private String sex;

    private UserService userService;
    private MailUtil mailUtil;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }

    public String register() throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setSex(sex);

        switch (userService.isExist(user)) {
            case 1:
                this.addFieldError("username", "该用户名已被注册");
                return "fail";
            case 2:
                this.addFieldError("email", "该邮箱已被注册");
                return "fail";
            default:
                break;
        }
        String code = Utils.createUUID();
        user.setActiveCode(code);   //设置用于验证的uuid
        user.setLevel(1);           //设置用户初始等级

        user.setPrivilege(0);       //设置为普通用户
        user.setCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        user.setHasActive(0);       //初始为未激活状态
        user.setUserAvatarUrl("/upload/default/head_icon.jpg"); //设置默认头像
        userService.register(user);

        mailUtil.sendEmail(user.getEmail(), code);

        //注册完成后跳转至中转页面，等待用户邮箱验证
        return "skip";
    }
}
