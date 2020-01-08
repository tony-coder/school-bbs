package cn.edu.zjut.action;

import cn.edu.zjut.po.MainSection;
import cn.edu.zjut.po.SubSection;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.BlackListService;
import cn.edu.zjut.service.MainSectionService;
import cn.edu.zjut.service.TopicService;

import java.sql.Timestamp;
import java.util.List;

/**
 * 发帖action
 */

public class PublishTopicAction extends BaseAction {
    private Topic topic;

    //Spring注入
    private TopicService topicService;
    private MainSectionService mainSectionService;
    private BlackListService blackListService;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setMainSectionService(MainSectionService mainSectionService) {
        this.mainSectionService = mainSectionService;
    }

    public void setBlackListService(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    public String publish() throws Exception {
        //int userId = (Integer) getSession().get("userId"); //获取用户
        // 测试
       /* User user1 = new User();
        user1.setId(17);
        user1.setUsername("test");
        user1.setEmail("");
        user1.setLevel(1);
        getSession().put("user", user1);*/

        User user = (User) getSession().get("user");  //获取用户

        int level = blackListService.getLevel(user.getId());
        if (level <= 3 && level > 0) {
            this.addFieldError("limit", "您已被管理员限制发帖");
            return "publish";
        }

        if (topic.getTitle().length() > 30) {
            this.addFieldError("limit", "输入标题超过30个字符");
            return "publish";
        }
        topic.setUserByUserId(user);
        topic.setType(0);  //设置帖子类型，默认为普通帖
        topic.setReplyNum(0);
        topic.setCreateTime(new Timestamp(System.currentTimeMillis()));
        topic.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        if (!topicService.publish(topic))
            return "error";  //发布失败

        return "success";
    }

    public String updateTopic() throws Exception {
        User user = (User) getSession().get("user");  //获取用户

        int level = blackListService.getLevel(user.getId());
        if (level <= 3 && level > 0) {
            this.addFieldError("limit", "您已被管理员限制发帖");
            return "publish";
        }

        if (topic.getTitle().length() > 30) {
            this.addFieldError("limit", "输入标题超过30个字符");
            return "publish";
        }
        String title = topic.getTitle();
        String content = topic.getContent();
        MainSection mainSection = topic.getSubSectionBySectionId().getMainSectionByMainSectionId();
        SubSection subSection = topic.getSubSectionBySectionId();

        topic = topicService.getTopicById(topic.getId());
        topic.setTitle(title);
        topic.setContent(content);

        topic.setSubSectionBySectionId(subSection);
        topic.getSubSectionBySectionId().setMainSectionByMainSectionId(mainSection);
        topic.setUpdateTime(new Timestamp(System.currentTimeMillis()));  //更新帖子更新时间

        if (topicService.updateTopic(topic))
            return "success";

        return "error";
    }

    public String initPublishPostPage() throws Exception {
        List<MainSection> mainSections = mainSectionService.getAllMainSection();  //获取所有主板块
        if (topic != null && topic.getId() > 0) {
            int topicId = topic.getId();
            Topic topic = topicService.getTopicById(topicId);
            getRequest().put("topic", topic);
        }
        getRequest().put("mainSections", mainSections);
        return "success";
    }
}
