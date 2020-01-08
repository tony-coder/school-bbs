package cn.edu.zjut.action;

import cn.edu.zjut.po.BestTopic;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.BestTopicService;
import cn.edu.zjut.service.TopicService;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author 鲍锋雄
 */

public class BestTopicAction extends BaseAction {
    private BestTopic bestTopic;
    private Integer pageNum;

    //Spring注入
    private BestTopicService bestTopicService;
    private TopicService topicService;

    public BestTopic getBestTopic() {
        return bestTopic;
    }

    public void setBestTopic(BestTopic bestTopic) {
        this.bestTopic = bestTopic;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public void setBestTopicService(BestTopicService bestTopicService) {
        this.bestTopicService = bestTopicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    //申请精华帖
    public String applyBestTopic() {
        User user = (User) getSession().get("user");
        if (bestTopic != null && user != null && user.getPrivilege() == 0) {
            Topic topic = topicService.getTopicById(bestTopic.getTopicByTopicId().getId());
            bestTopic.setState(1);  //1表示正在等待处理
            bestTopic.setTopicByTopicId(topic);
            bestTopic.setUserByUserId(user);
            bestTopic.setCreateTime(new Timestamp(System.currentTimeMillis()));

            if (!bestTopicService.add(bestTopic)) {
                return "error";
            }
        }
        return "success";
    }

    //申请精华帖记录
    public String applyRecords() {
        if (pageNum == null)
            pageNum = 1;
        User user = (User) getSession().get("user");
        if (user == null)
            return "login";
        List<BestTopic> bestTopics = bestTopicService.getTopicsByUserId(user.getId(), pageNum, 10);

        getRequest().put("bestTopics", bestTopics);

        return "success";
    }

    //管理员回复精华帖
    public String replyapply() {
        User user = (User) getSession().get("user");

        if (user != null && user.getPrivilege() == 1) {
            bestTopicService.updateState(bestTopic.getTopicByTopicId().getId(), bestTopic.getState());  //更新帖子状态
            if (bestTopic.getState() == 2) {  //同意
                Topic topic = topicService.getTopicById(bestTopic.getTopicByTopicId().getId());
                topic.setType(1);
                topicService.updateTopic(topic);  //更新帖子状态
            }
            return "success";
        }
        return "error";
    }

    //初始化bestTopic.jsp页面
    public String initBestTopicPage() {
        if (pageNum == null)
            pageNum = 1;
        List<BestTopic> bestTopics = bestTopicService.getBestTopics(pageNum, 10);

        getRequest().put("bestTopics", bestTopics);
        getRequest().put("pageNum", pageNum);
        return "success";
    }
}
