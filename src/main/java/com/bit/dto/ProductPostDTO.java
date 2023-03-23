package com.bit.dto;

import lombok.Data;

import java.sql.Date;

@Data
// 상품 게시글 
public class ProductPostDTO {
    // 상품 게시글 아이디
    private String productPostId;

    // 상품 아이디
    private String productId;

    // 상품 게시글 제목
    private String productPostTitle;

    // 상품 게시글 상세설명
    private String productPostDescription;

    // 상품 게시글 생성 날짜 저자 미상 포스트 가능
    private Date productPostCreateDate;

    // 상품 게시글 수정 날짜
    private Date productPostUpdateDate;

    // 판매 개수
    private int sellCounts;

    // 상품 파일 1
    private String productFile1;

    // 상품 파일 2
    private String productFile2;

    // 상품 파일 3
    private String productFile3;

}