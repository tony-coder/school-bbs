package cn.edu.zjut.interceptors;

import cn.edu.zjut.po.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import java.util.Map;

public class AuthorityInterceptor extends AbstractInterceptor {


    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("Authority Interceptor executed!");
        ActionContext ctx = actionInvocation.getInvocationContext();
        Map session = ctx.getSession();
        User user = (User)session.get("user");
        if(user != null){
            return actionInvocation.invoke();
        }
        else{
            ctx.put("tip", "您还没有登录，请输入用户名和密码登录系统");
            return Action.LOGIN;
        }
    }
}
