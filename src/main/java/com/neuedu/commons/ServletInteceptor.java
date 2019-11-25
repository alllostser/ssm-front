package com.neuedu.commons;

import com.neuedu.pojo.Users;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletInteceptor implements HandlerInterceptor   {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Users user = (Users) request.getSession().getAttribute("user");
        if (user == null){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(" <span style=\"font-size:18px;\">未登录，请登录后操作！3秒后跳回登录页面...</span><span style=\"font-size:24px;\"><meta http-equiv=\"refresh\" content=\"3;/manage/user/islogin.do\"> </span>");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
