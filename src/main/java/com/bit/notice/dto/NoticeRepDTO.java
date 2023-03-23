package com.bit.notice.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class NoticeRepDTO {
    private String noticeWriterId;
    private String noticeTitle;
    private String noticeContent;
    private int write_group;
    private Timestamp write_date;


}
