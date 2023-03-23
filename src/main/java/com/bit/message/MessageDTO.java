package com.bit.message;

import lombok.Data;

import javax.servlet.http.HttpServletRequest;

@Data
public class MessageDTO {
    private int result; //결과값 저장 용도
    private HttpServletRequest request; //context경로 또는 session가져오기 위함
    private String successMessage;
    private String failMessage;
    private String successURL;
    private String failURL;


}