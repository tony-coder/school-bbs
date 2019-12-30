package cn.edu.zjut.action;

import cn.edu.zjut.po.MainSection;
import cn.edu.zjut.po.SubSection;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.TopicService;

import java.sql.Timestamp;

/**
 * 发帖action
 */

public class PublishTopicAction extends BaseAction {
    private String title;
    private String content;
    private int mainSectionId;
    private int subSectionId;
    private int topicId;

    private Topic topic;

    //Spring注入
    private TopicService topicService;

    String publish() {
        int userId = (Integer) getSession().get("userId");
        /*int level = blackListBiz.getLevel(userId);
        if (level<=3&&level>0){
            this.addFieldError("limit", "您已被管理员限制发帖");
            return "publish";
        }*/
        Topic topic = new Topic();
        topic.setTitle(title);
        topic.setContent(content);

        SubSection subSection = new SubSection();
        subSection.setId(subSectionId);
        MainSection mainSection = new MainSection();
        mainSection.setId(mainSectionId);
        subSection.setMainSectionByMainSectionId(mainSection);

        topic.setSubSectionBySectionId(subSection);

        User user = new User();
        user.setId(userId);
        topic.setUserByUserId(user);

        topic.setType(0);  //设置帖子类型，默认为普通帖
        topic.setReplyNum(0);
        topic.setCreateTime(new Timestamp(System.currentTimeMillis()));
        topic.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        return "success";
    }
}
