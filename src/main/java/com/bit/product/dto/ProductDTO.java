package com.bit.product.dto;


import lombok.Data;

@Data
// 상품 
public class ProductDTO {
    // 상품 아이디
    private String productId;

    // 상품 판매자 아이디
    private String productSellerId;

    // 상품 카테고리
    private String productCategory;

    // 상품 브랜드
    private String productBrand;

    // 상품 이름
    private String productName;

    // 상품 할인율
    private int productDiscountRate;

    // 상품 가격
    private int productNormalPrice;

    // 상품 가격
    private int productSalePrice;

    // 상품 파일 1
    private String productFile1;

    // 상품 추천 개수
    private int productRecommendCounts;

    // 상품 재고 개수
    private int productStockCounts;

    // 상품 평점
    private int productScore;

}