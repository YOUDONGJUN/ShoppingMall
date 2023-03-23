package com.bit.wish.dto;

import lombok.Data;

@Data
//관심 상품 
public class WishDTO {

    // 관심상품 아이디
    private String wishId;

    // 구매자 인덱스
    private String buyerIdx;

    // 관심상품 이름
    private String wishName;

    // 관심상품 총 가격
    private int wishTotalPrice;

    // 관심상품 개수
    private int wishCounts;


}