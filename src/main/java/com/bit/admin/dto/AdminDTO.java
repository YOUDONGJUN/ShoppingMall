package com.bit.admin.dto;

import lombok.Data;

@Data
// 관리자 저자에 관한 정보를 저장하는 테이블
public class AdminDTO {

    // 관리자 아이디 아이디는 번호로 구성
    private String adminId;

    // 관리자 이메일 실제 이름이 아니라 닉네임 같은 이름
    private String adminEmail;

    // 관리자 비밀번호 실제 이름
    private String adminPassword;

    // 관리자 이름 실제 이름
    private String adminName;

}
