package com.bit.order.dto;


import lombok.Data;

@Data
//주문 상세 
public class OrderDetailsDTO {

    // 주문 아이디
    private String orderId;

    // 배송 요구사항
    private String deliveryRequirement;

    // 배송 주소
    private String deliveryAddress;

    // 판매자 전화번호
    private int sellerPhone;

    // 배송 상태
    private String deliveryStatus;

}