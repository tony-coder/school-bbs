package cn.edu.zjut.action;

import cn.edu.zjut.po.User;
import cn.edu.zjut.service.AdminService;
import cn.edu.zjut.service.UserService;
import cn.edu.zjut.util.MailUtil;
import cn.edu.zjut.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import java.sql.Timestamp;
import java.io.File;
import java.io.IOException;

public class UserAction extends BaseAction {
    //注册信息
    private User user;
    //激活信息
    private String activeCode;
    //用户权限
    private int privilege;
    //头像文件
    private File photoImg;
    private String photoImgFileName;
    //Spring 注入
    private UserService userService;
    private MailUtil mailUtil;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        user.setPrivilege(privilege);
        int result = 0;
        if (user.getPrivilege() == 0) {
            result = userService.login(user);
            if (result >= 0) {//如果用户名密码都正确，登录成功
                //将用户id，和姓名写入session
//                getSession().put("username", user.getUsername());
//                getSession().put("userId", result);
                //将用户信息写入session
                user = userService.findById(result);
                getSession().put("user", user);
                return "success";
            }
        } else {
            result = userService.adminLogin(user);
            if (result >= 0) {//如果用户名密码都正确，登录成功
                user = userService.findById(result);
                getSession().put("user", user);
                return "success";
            }
        }

        switch (result) {
            case -1:
                addFieldError("password", "用户名或密码不正确");//迷惑行为，不能直接说密码不正确
                return "login";
            case -2:
                addFieldError("username", "账户未激活，请验证激活后进行登录");
                return "login";
            case -3:
                addFieldError("username", "该用户不存在");
                return "login";
            default:
                break;
        }
        return "login";
    }
    //用户登出，返回登录界面
    public String logout() throws Exception {
        if (getSession().get("user")!=null){
            getSession().put("user",null);
        }
        return "logout";
    }

    //用户信息更新
    public String update() throws IOException {
        User user = (User)getSession().get("user");
        if (user.getUsername() != null || user.getPassword() != null || user.getEmail() != null || user.getSex() != null || photoImg != null) {
            if (userService.isExist(user)== 1 ) {
                int id = userService.getUserIdByUsername(user.getUsername());
                if(id!=user.getId()){
                    System.out.println("该用户已存在");
                    addFieldError("username", "该用户名已存在");
                    return SUCCESS;
                }
            } else if (userService.isExist(user)== 2 ) {
                int id = userService.getUserIdByEmail(user.getEmail());
                if(id!=user.getId()){
                    System.out.println("该邮箱已存在");
                    addFieldError("email", "该邮箱已存在");
                    return SUCCESS;
                }
            }
            if(user.getPassword() != null){
                user.setPassword(user.getPassword());
            }
            user.setEmail(user.getEmail());
            user.setUsername(user.getUsername());
            if (photoImg != null){
                String root = ServletActionContext.getServletContext().getRealPath("/upload/headImg");
                System.out.println(root);
                String filename = photoImgFileName;
                int index = filename.indexOf("\\");
                if (index != -1){
                    filename = filename.substring(index+1);
                }
                //得到哈希码
                int code = filename.hashCode();
                //转化成16进制
                String hex = Integer.toHexString(code);
                File dstDir = new File(root,hex.charAt(0)+"/"+hex.charAt(1));
                //防止文件重名
                String saveFilename = Utils.createUUID()+filename;
                String abstractPath = "/upload/headImg/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+saveFilename;
                File dstFile = new File(dstDir,saveFilename);
                System.out.println(dstFile.toPath());
                if (!dstFile.getParentFile().exists()){
                    dstFile.getParentFile().mkdirs();
                }
                FileUtils.copyFile(photoImg,dstFile);
                user.setUserAvatarUrl(abstractPath);
                ActionContext.getContext().put("message", "上传成功");
            }
            //更新
            userService.update(user);
            return SUCCESS;
        }
        return SUCCESS;
    }
}
