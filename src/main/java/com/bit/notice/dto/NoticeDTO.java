package com.bit.notice.dto;

import lombok.Data;

import java.sql.Date;

@Data
// 공지사항
public class NoticeDTO {

    // 공지 아이디
    private int noticeId;

    // 공지 작성자 아이디
    private String noticeWriterId;

    // 공지 제목
    private String noticeTitle;

    // 공지 내용
    private String noticeContent;

    // 공지 조회수
    private int noticeViews;

    // 공지 생성 날짜
    private String noticeCreateDate;

    // 공지 수정 날짜
    private Date noticeUpdateDate;

    // 공지 이미지 파일
    private String noticeImageFileName;


}