package com.bit.member.dto;

import lombok.Data;

import java.sql.Date;

@Data
// 회원
public class MemberDTO {
    // 회원 인덱스
    private String memberIdx;

    // 회원 이름
    private String memberName;

    // 회원 이메일
    private String memberEmail;

    // 회원 비밀번호
    private String memberPassword;

    // 회원 역할
    private String memberRole;

    // 회원 주소 //우편번호 입력칸
    private String memberAddress;

    // 회원 주소 // 주소 입력칸
    private String memberAddress2;

    // 회원 생성 날짜
    private Date memberCreateDate;

    // 회원 적립금
    private int memberMileage;

    // 회원 포인트
    private int memberPoint;

    // 회원 아이디
    private String memberId;

    // 회원 전화번호
    private String memberPhone;

    // 회원 세션아이디
    private String memberSessionId;

    // 회원 만료시간
    private Date memberLimitTime;

}