package com.bit.cart.dto;

import lombok.Data;

//장바구니
@Data
public class CartDTO {

    // 장바구니 아이디
    private String cartId;

    // 회원 아이디
    private String memberId;

    // 장바구니 상품 개수
    private int cartProductCounts;

    // 장바구니 상품 아이디
    private String cartProductId;

    // 장바구니 상품 파일 1
    private String cartProductFile1;

    // 장바구니 상품 가격
    private int cartProductPrice;

}