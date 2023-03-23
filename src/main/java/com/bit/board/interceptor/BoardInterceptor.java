package com.bit.board.interceptor;

import com.bit.member.session_name.MemberSessionName;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;


public class BoardInterceptor extends HandlerInterceptorAdapter
        implements MemberSessionName {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute(LOGIN) == null) {
            //response.sendRedirect("login");
            response.setContentType("text/html; charset=utf-8");//클라이언트로 전달할 타입
            PrintWriter out = response.getWriter();
            out.print("<script>alert('로그인하지 않은 유저는 접근할 수 없는 페이지입니다.'); " +
//					+ "location.href='/root/com.bit.member/login';</script>");
                    "location.href='" + request.getContextPath() + "/member/login';</script>");
            return false;
        }
        return true;
    }
}