package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.ReplyDao;
import cn.edu.zjut.po.Reply;
import cn.edu.zjut.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {

    private ReplyDao replyDao;

    public void setReplyDao(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    @Override
    public boolean addRepy(Reply reply) {
        try {
            replyDao.save(reply);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Reply> getReplies(int topicId, int pageIndex, int pageSize) {
        return replyDao.getReplies(topicId, pageIndex, pageSize);
    }

}
