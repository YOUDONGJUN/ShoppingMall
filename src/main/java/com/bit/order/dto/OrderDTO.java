package com.bit.order.dto;

import lombok.Data;

import java.sql.Date;

@Data
//주문 
public class OrderDTO {

    // 주문 아이디
    private String orderId;

    // 구매자 아이디
    private String buyerIdx;

    // 주문 상품 아이디
    private String orderProductId;

    // 주문 개수
    private int orderCounts;

    // 주문 생성 날짜
    private Date orderCreateDate;

    // 총 가격
    private int totalPrice;

    // 결제수단
    private String payment;

    // 주문 상품 이름
    private String orderProductName;

}