package com.bit.member.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.member.dto.MemberDTO;
import com.bit.member.service.MemberService;
import com.bit.member.session_name.MemberSessionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;


public class AutoLoginInterceptor extends HandlerInterceptorAdapter
        implements MemberSessionName {
    @Autowired
    MemberService ms;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("test interceptor");
        Cookie cookie = WebUtils.getCookie(request, "loginCookie");
        if (cookie != null) {
            MemberDTO dto = ms.getUserSessionId(cookie.getValue());
            if (dto != null) {
                request.getSession().setAttribute(LOGIN, dto.getMemberId());
            }
        }
        return true;
    }

}





