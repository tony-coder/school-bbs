package cn.edu.zjut.service;

import cn.edu.zjut.po.Notice;

public interface NoticeService {
    void publish(Notice notice);

    Notice getNoticeById(int noticeId);
}
