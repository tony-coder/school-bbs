package cn.edu.zjut.action;

import cn.edu.zjut.po.BestTopic;
import cn.edu.zjut.po.MainSection;
import cn.edu.zjut.po.Notice;
import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.BestTopicService;
import cn.edu.zjut.service.MainSectionService;
import cn.edu.zjut.service.NoticeService;
import cn.edu.zjut.service.TopicService;

import java.util.List;

/**
 * 主页控制
 */
public class MainAction extends BaseAction {
    private TopicService topicService;
    private MainSectionService mainSectionService;
    private NoticeService noticeService;
    private BestTopicService bestTopicService;

    //Spring注入
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setMainSectionService(MainSectionService mainSectionService) {
        this.mainSectionService = mainSectionService;
    }

    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public void setBestTopicService(BestTopicService bestTopicService) {
        this.bestTopicService = bestTopicService;
    }

    //初始化首页推荐的帖子列表按照时间顺序
    public String initHomePage() throws Exception {
        List<Topic> latestPosts = topicService.getLatestTopic(1, 6);
        List<MainSection> mainSectionList = mainSectionService.getAllMainSection();
        List<Notice> notices = noticeService.getNotice(1, 5);
        List<BestTopic> bestTopics = bestTopicService.getBestTopics(1, 6);
        getSession().put("mainSections", mainSectionList);
        getRequest().put("notices", notices);
        getRequest().put("topics", latestPosts);
        getRequest().put("bestTopics", bestTopics);
        return "success";
    }
}
