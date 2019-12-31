package cn.edu.zjut.action;

import cn.edu.zjut.po.User;
import cn.edu.zjut.service.AdminService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.File;

public class AdminAction extends BaseAction{
    private String username;
    private String password;
    private String email;
    private String sex;
    private File photoImg;
    private String photoImgFileName;

    private AdminService adminService;

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

    public String update() {
        if(username!=null || password!=null || email!=null || sex!=null || photoImg!=null){
            User admin = adminService.getAdminById((Integer)getSession().get("userId"));
            if(username!=null && username.length()>0){
                admin.setUsername(username);
            }
            if(sex!=null && sex.length()>0){
                admin.setSex(sex);
            }
            if(email!=null && email.length()>0){
                admin.setEmail(email);
            }
            if (password != null&&password.length()>0)
                admin.setPassword(password);

            switch (adminBiz.isExist(admin)) {
                case 1:
                    int id = adminBiz.getAdminIdByUsername(username);
                    if (id !=-1 &&id != (Integer)getSession().get("adminid")){
                        System.out.println("该用户已存在");
                        addFieldError("username", "该用户名已存在");
                        return SUCCESS;
                    }
                case 2:
                    int id2 = adminBiz.getAdminIdByEmail(email);
                    if (id2!=2&&id2 != (Integer)getSession().get("adminid")){
                        System.out.println("该邮箱已存在");
                        addFieldError("email","该邮箱已存在");
                        return SUCCESS;
                    }

            }
            if (photoImg != null){
                String root = ServletActionContext.getServletContext().getRealPath("/upload/headImg");
                System.out.println(root);
                String filename = photoImgFileName;
                int index = filename.indexOf("\\");
                if (index != -1){
                    filename = filename.substring(index+1);
                }
                int code = filename.hashCode();//得到哈希码
                String hex = Integer.toHexString(code);//转化成16进制
                File dstDir = new File(root,hex.charAt(0)+"/"+hex.charAt(1));
                String saveFilename = Utils.createUUID()+filename;//防止文件重名
                String abstractPath = "/upload/headImg/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+saveFilename;
                File dstFile = new File(dstDir,saveFilename);
                System.out.println(dstFile.toPath());
                if (!dstFile.getParentFile().exists()){
                    dstFile.getParentFile().mkdirs();
                }
                FileUtils.copyFile(photoImg,dstFile);
                admin.setPhotoUrl(abstractPath);
                ActionContext.getContext().put("message", "上传成功");
            }
            adminBiz.updateAdmin(admin);
            this.addFieldError("update_result", "修改成功");
            return SUCCESS;
        }
    }
}
