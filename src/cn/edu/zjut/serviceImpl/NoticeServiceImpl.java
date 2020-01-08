package cn.edu.zjut.serviceImpl;

import cn.edu.zjut.dao.NoticeDao;
import cn.edu.zjut.po.Notice;
import cn.edu.zjut.service.NoticeService;

import java.util.List;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    public NoticeDao getNoticeDao() {
        return noticeDao;
    }

    public void setNoticeDao(NoticeDao noticeDao) {
        this.noticeDao = noticeDao;
    }

    @Override
    public void publish(Notice notice) {
        noticeDao.publish(notice);
    }

    @Override
    public Notice getNoticeById(int noticeId) {
        return noticeDao.getNoticeById(noticeId);
    }

    @Override
    public List<Notice> getNotice(int pageIndex, int pageSize) {
        return noticeDao.getNotice(pageIndex, pageSize);
    }
}
