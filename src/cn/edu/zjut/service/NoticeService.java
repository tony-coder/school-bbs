package cn.edu.zjut.service;

import cn.edu.zjut.po.Notice;

import java.util.List;

public interface NoticeService {
    public void publish(Notice notice);

    public Notice getNoticeById(int noticeId);

    public List<Notice> getNotice(int pageIndex, int pageSize);
}
