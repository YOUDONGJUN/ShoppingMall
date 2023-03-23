package com.bit.review.dto;

import lombok.Data;

@Data
// 상품평
public class ReviewDTO {

    // 상품평 아이디
    private String reviewId;

    // 상품평 작성자 아이디
    private String reviewWriterIdx;

    // 상품평 제목
    private String reviewTitle;

    // 상품평 내용
    private String reviewContent;

    // 상품 평점
    private int productScore;

    // 상품 게시글 아이디
    private String productId;

    // 상품평 등급
    private String productDegree;

    // 상품 파일 1
    private String productFile1;

    // 상품 파일 2
    private String productFile2;

    // 상품 파일 3
    private String productFile3;

    // 상품 파일 4
    private String productFile4;

    // 상품 파일 5
    private String productFile5;

    // 상품 이름
    private String productName;

}
