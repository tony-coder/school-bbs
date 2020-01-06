package cn.edu.zjut.action;

import cn.edu.zjut.po.Notice;
import cn.edu.zjut.po.User;
import cn.edu.zjut.service.NoticeService;

import java.sql.Timestamp;
import java.util.Date;

import static com.opensymphony.xwork2.Action.SUCCESS;

public class NoticeAction extends BaseAction{
    private Notice notice;

    private NoticeService noticeService;

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public NoticeService getNoticeService() {
        return noticeService;
    }

    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public String execute() throws Exception{
        String title = notice.getTitle();
        String content = notice.getContent();
        if (title != null && content != null){
            Notice notice = new Notice();
            notice.setTitle(title);
            notice.setContent(content);
            notice.setCreateTime(new Timestamp(System.currentTimeMillis()));
            User user = new User();
            User admin = (User)getSession().get("admin");
            user.setId(admin.getId());
            notice.setUserByUserId(admin);
            noticeService.publish(notice);
            this.addFieldError("notice_result","发表成功");
            return SUCCESS;
        }
        return ERROR;
    }
}
