package com.bit.mileage.dto;

import lombok.Data;

import java.sql.Date;

@Data
//적립금 
public class MileageDTO {

    // 적립금 아이디
    private String mileageId;

    // 적립금 상태
    private String mileageStatus;

    // 적립금 상세
    private String mileageDetails;

    // 주문 아이디
    private String orderId;

    // 주문 생성 날짜
    private Date orderCreateDate;

    // 회원 인덱스
    private String memberIdx;

}
