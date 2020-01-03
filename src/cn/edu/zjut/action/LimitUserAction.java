package cn.edu.zjut.action;

import cn.edu.zjut.po.BlackList;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.BlackListService;
import cn.edu.zjut.service.UserService;

import javax.servlet.http.MappingMatch;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LimitUserAction extends BaseAction{

    private String keywords;
//    private BlackList blackList;
    private int userId;
    private int level;
    private UserService userService;
    private BlackListService blackListService;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public BlackListService getBlackListService() {
        return blackListService;
    }

    public void setBlackListService(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String execute() throws Exception {
        if (keywords != null){
            List<User> users = userService.findByKeywords(keywords);
//            Set<BlackList> temp = (Set) users.get(0).getBestTopicsById();
//            System.out.println(tempgetLevel());
            getRequest().put("users", users);
            getRequest().put("keywords", keywords);
            return SUCCESS;
        }
        return ERROR;
    }


    public String limit() throws Exception{

        if (userId >0 && level>0){
            System.out.println("limit:"+keywords);
            BlackList blackList = new BlackList();
            User user = new User();
            user.setId(userId);
            blackList.setUserByUserId(user);
            blackList.setLevel(level);
            int oldLevel = blackListService.getLevel(userId);
            System.out.println(oldLevel);
            if (oldLevel > 0){
                if (oldLevel != level){
                    blackListService.update(blackList);
                }
            }else {
                blackListService.save(blackList);
            }

        }else if (level<0){
            blackListService.delete(userId);
        }
        System.out.println("before query"+keywords);
        List<User> users = userService.findByKeywords(keywords);
//        Map<Integer,Integer> levelMap = new HashMap<Integer,Integer>();
//        for(User user : users){
//            int tempLevel = blackListService.getLevel(user.getId());
//            levelMap.put(user.getId(),tempLevel);
//        }
//        getRequest().put("levelList", levelMap.values());
        getRequest().put("users", users);
        getRequest().put("keywords", keywords);
        return SUCCESS;
    }

}
