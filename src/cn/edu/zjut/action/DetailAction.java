package cn.edu.zjut.action;

import cn.edu.zjut.po.Topic;
import cn.edu.zjut.service.MainSectionService;
import cn.edu.zjut.service.TopicService;

import java.util.List;
//按类型 帖子详情
public class DetailAction extends BaseAction {
    private int type;//1,2,3.4.5.6.7.
    private int page;
    private TopicService topicService;//帖子
    private MainSectionService mainSectionService;

    public String execute() throws Exception {
        System.out.println("type"+":"+type+"page:"+page);
        String typename = null;
        switch (type) {
            case -1:
                typename = "论坛新帖";
                break;
            case -2:
                typename = "精华帖";
                break;
            case -3:
                typename = "论坛热帖";
                break;
            default:
                typename = mainSectionService.getMainSectionById(type).getTitle();
                break;
        }
        if (type>-4 && page>0){
            //按类型查找帖
            List<Topic> topics = topicService.getTopicByType(type, page, 10);
            getRequest().put("topics", topics);
            getRequest().put("page", page);
            getRequest().put("type", type);
            getRequest().put("typename", typename);
            return SUCCESS;
        }
        return ERROR;
    }

    public MainSectionService getMainSectionService() {
        return mainSectionService;
    }

    public void setMainSectionService(MainSectionService mainSectionService) {
        this.mainSectionService = mainSectionService;
    }

    public TopicService getTopicService() {
        return topicService;
    }

    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType(){
        return type;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage(){
        return page;
    }
}

