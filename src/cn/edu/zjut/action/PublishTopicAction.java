package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.TopicService;

import java.sql.Timestamp;

/**
 * 发帖action
 */

public class PublishTopicAction extends BaseAction {

    private Topic topic;

    //Spring注入
    private TopicService topicService;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public String publish() throws Exception {
        //int userId = (Integer) getSession().get("userId"); //获取用户
        // 测试
        User user1;


        //getSession().put("user", user1);

        User user = (User) getSession().get("user");  //获取用户
        /*int level = blackListBiz.getLevel(userId);
        if (level<=3&&level>0){
            this.addFieldError("limit", "您已被管理员限制发帖");
            return "publish";
        }*/

        topic.setUserId(user.getId());
        topic.setUserByUserId(user);

        topic.setType(0);  //设置帖子类型，默认为普通帖
        topic.setReplyNum(0);
        topic.setCreateTime(new Timestamp(System.currentTimeMillis()));
        topic.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        topicService.publish(topic);

        return "success";
    }
}
