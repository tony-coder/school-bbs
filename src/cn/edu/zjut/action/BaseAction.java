package cn.edu.zjut.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class BaseAction extends ActionSupport implements RequestAware, ApplicationAware, SessionAware {
    private Map request, session, application;  //作用域对象

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map getRequest() {
        return request;
    }

    public Map getSession() {
        return session;
    }

    public Map getApplication() {
        return application;
    }
}
