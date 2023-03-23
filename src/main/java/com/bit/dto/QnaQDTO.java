package com.bit.dto;

import lombok.Data;

import java.sql.Date;

@Data
// 구매자 질문 
public class QnaQDTO extends com.bit.qna.dto.QnaQDTO {

    // 질문 아이디
    private String questionId;

    // 질문 작성자 아이디
    private String questionWriterId;

    // 상품 게시글 아이디
    private String productPostId;

    // 질문 제목
    private String questionTitle;

    // 질문 내용
    private String questionContent;

    // 질문 생성 날짜
    private Date questionCreateDate;

    // 질문 조회수
    private int questionViews;

}