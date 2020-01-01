package cn.edu.zjut.action;

import cn.edu.zjut.po.User;
import cn.edu.zjut.service.AdminService;
import cn.edu.zjut.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.IOException;

public class AdminAction extends BaseAction{

    private User user;
    private File photoImg;
    private String photoImgFileName;
    private AdminService adminService;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public File getPhotoImg() {
        return photoImg;
    }

    public void setPhotoImg(File photoImg) {
        this.photoImg = photoImg;
    }

    public String getPhotoImgFileName() {
        return photoImgFileName;
    }

    public void setPhotoImgFileName(String photoImgFileName) {
        this.photoImgFileName = photoImgFileName;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public String update() throws IOException {
        User admin = (User)getSession().get("user");
        if (user.getUsername() != null || user.getPassword() != null || user.getEmail() != null || user.getSex() != null || photoImg != null) {

            if (adminService.existUsername(user)) {
                int id = adminService.getAdminIdByUsername(user.getUsername());
                if(id!=admin.getId()){
                    System.out.println("该用户已存在");
                    addFieldError("username", "该用户名已存在");
                    return SUCCESS;
                }
            } else if (adminService.existEmail(user)) {
                int id = adminService.getAdminIdByEmail(user.getEmail());
                if(id!=admin.getId()){
                    System.out.println("该邮箱已存在");
                    addFieldError("email", "该邮箱已存在");
                    return SUCCESS;
                }
            }
            if(user.getPassword() != null){
                admin.setPassword(user.getPassword());
            }
            admin.setEmail(user.getEmail());
            admin.setUsername(user.getUsername());
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
                admin.setUserAvatarUrl(abstractPath);
                ActionContext.getContext().put("message", "上传成功");
            }

            adminService.updateAdmin(admin);
            return SUCCESS;
        }
        return SUCCESS;
    }

}
