package com.bit.member.service;

import com.bit.member.dto.MemberDTO;
import com.bit.mybatis.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public int user_check(HttpServletRequest request, Model model, HttpSession session) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        MemberDTO memberDTO = memberMapper.user_check(request.getParameter("memberId"));
        if (memberDTO != null) {
            //if(request.getParameter("pw").equals(dto.getPw())) {
            if (encoder.matches(request.getParameter("memberPassword"), memberDTO.getMemberPassword()) || request.getParameter("memberPassword").equals(memberDTO.getMemberPassword())) {
                model.addAttribute("userDataOnThisPageRequest", memberMapper.user_check(request.getParameter("memberId")));
                session.setAttribute("userSessionData", memberMapper.user_check(request.getParameter("memberId")));
                return 0;
            }
        }
        return 1;

    }

    // 로그인한 회원 정보
    public void profile(Model model, String userId) {
        MemberDTO list = memberMapper.profile(userId);
        model.addAttribute("memberList", list);
        //model.addAttribute("memberList", mapper.memberInfo());
    }

    public void memberInfo(Model model) {
        ArrayList<MemberDTO> list = memberMapper.memberInfo();
        model.addAttribute("memberList", list);
        //model.addAttribute("memberList", mapper.memberInfo());
    }

    public void info(String memberId, Model model) {
        model.addAttribute("info", memberMapper.info(memberId));
    }

    public int register(MemberDTO memberDTO) {
        memberDTO.setMemberId(memberDTO.getMemberId());
		/*
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//dto.setPw(encoder.encode(dto.getPw()));
		System.out.println("비밀번호 변경 전 : "+dto.getMemberPassword());
		String pw = encoder.encode(dto.getMemberPassword());
		System.out.println("암호화 후 : "+pw);
		*/
        memberDTO.setMemberIdx(UUID.randomUUID().toString().replace("-", ""));

        memberDTO.setMemberPassword(memberDTO.getMemberPassword());
        memberDTO.setMemberName(memberDTO.getMemberName());
        memberDTO.setMemberPhone(memberDTO.getMemberPhone());
        memberDTO.setMemberAddress(memberDTO.getMemberAddress());
        memberDTO.setMemberAddress2(memberDTO.getMemberAddress2());
        memberDTO.setMemberEmail(memberDTO.getMemberEmail());

        memberDTO.setMemberLimitTime(new Date(System.currentTimeMillis()));
        memberDTO.setMemberSessionId("nan");

        memberDTO.setMemberRole("user");
        Date createDate = new Date(System.currentTimeMillis());
        memberDTO.setMemberCreateDate(createDate);

        try {
            return memberMapper.register(memberDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    //로그인 한 회원정보 수정
    public void modify(HttpServletRequest request) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId((String) request.getParameter("id"));
        memberDTO.setMemberPassword((String) request.getParameter("pw"));
        memberDTO.setMemberPhone((String) request.getParameter("phone"));
        memberDTO.setMemberEmail((String) request.getParameter("email"));
        memberDTO.setMemberAddress((String) request.getParameter("memberAddress"));
        memberDTO.setMemberAddress2((String) request.getParameter("memberAddress2"));
        memberMapper.modify(memberDTO);
    }

    //로그인 한 회원 탈퇴
    public void delete(String userId) {
        memberMapper.delete(userId);
        /* model.addAttribute("delete", list); */
    }

    public void keepLogin(String memberSessionId, Date memberLimitTime, String memberId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberSessionId", memberSessionId);
        map.put("memberLimitTime", memberLimitTime);
        map.put("memberId", memberId);
        memberMapper.keepLogin(map);
    }

    public MemberDTO getUserSessionId(String memberSessionId) {
        return memberMapper.getUserSessionId(memberSessionId);
    }

    public int idCheck(String memberId) {
        return memberMapper.idCheck(memberId);
    }

    //아이디 찾기
    public String find_id(HttpServletResponse response, String email) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = memberMapper.find_id(email);

        if (id == null) {
            out.println("<script>");
            out.println("alert('가입된 이메일이 없습니다');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else {
            return id;
        }

    }

    // 비밀번호 찾기
    public String find_pw(HttpServletResponse response, String id) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String pw = memberMapper.find_pw(id);

        if (id == null) {
            out.println("<script>");
            out.println("alert('가입된 이메일이 없음');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        } else {
            return pw;
        }

    }
}
