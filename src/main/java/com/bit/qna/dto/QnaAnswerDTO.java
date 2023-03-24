package com.bit.qna.dto;

import lombok.Data;

import java.sql.Date;

@Data
//판매자 답변 
public class QnaAnswerDTO {

    // 답변 아이디
    private String answerId;

    // 질문 아이디
    private String questionId;

    // 답변 작성자 아이디
    private String answerWriterId;

    // 답변 제목
    private String answerTitle;

    // 답변 내용
    private String answerContent;

    // 답변 생성 날짜
    private Date answerCreateDate;

    // 답변 조회수
    private int answerViews;

}
