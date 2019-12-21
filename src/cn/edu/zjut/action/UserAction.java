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
    //private User user;

    //注册信息
    private String username;
    private String password;
    private String email;
    private String sex;

    //激活信息
    private String activeCode;

    //用户权限
    private int privilege;

    //Spring 注入
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

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setMailUtil(MailUtil mailUtil) {
        this.mailUtil = mailUtil;
    }


    public int getPrivilege() {
        return privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
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

    public String active() throws Exception {
        //激活码为空，则返回首页
        if (activeCode == null) {
            return "index";
        }
        int result = userService.activeUser(activeCode);
        if (result == 0) {
            getRequest().put("message", "该激活码已失效请重新注册");
            return "error";
        } else if (result == -1) {
            getRequest().put("message", "该账户已激活，请勿重复激活");
            return "error";
        } else {
            getRequest().put("message", "恭喜您！账户激活成功，五秒钟后自动跳转，无法跳转请点击下面按钮");
            return "success";
        }
    }
    
    public String login() throws Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPrivilege(privilege);
        int result = 0;
        if(user.getPrivilege()==0){
            result = userService.login(user);
            if (result >= 0){//如果用户名密码都正确，登录成功
                //将用户id，和姓名写入session
                getSession().put("username", username);
                getSession().put("userId", result);
                return "success";
            }
        }else{
            result = userService.adminLogin(user);
            if (result >= 0){//如果用户名密码都正确，登录成功
                //将用户id，和姓名写入session
                getSession().put("username", username);
                getSession().put("userId", result);
                return "success";
            }
        }

        switch (result) {
            case -1:
                addFieldError("password", "用户名或密码不正确");//迷惑行为，不能直接说密码不正确
                return "login";
            case -2:
                addFieldError("username","账户未激活，请验证激活后进行登录");
                return "login";
            case -3:
                addFieldError("username", "该用户不存在");
                return "login";
            default:
                break;
        }
        return "login";
    }
}
