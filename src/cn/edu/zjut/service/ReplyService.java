package cn.edu.zjut.service;

import cn.edu.zjut.po.Reply;

import java.util.List;

public interface ReplyService {
    //增加一条回复
    public boolean addRepy(Reply reply);

    //获取指定帖子的回复
    public List<Reply> getReplies(int topicId, int pageIndex, int pageSize);
}
