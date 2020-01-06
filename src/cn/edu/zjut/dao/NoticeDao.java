package cn.edu.zjut.dao;

import cn.edu.zjut.po.Notice;

import java.util.List;

public interface NoticeDao {
    void publish(Notice notice);

    Notice getNoticeById(int noticeId);


}
