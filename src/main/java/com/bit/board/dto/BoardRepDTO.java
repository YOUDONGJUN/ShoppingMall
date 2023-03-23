package com.bit.board.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BoardRepDTO {
    private String id;
    private String title;
    private String content;
    private int write_group;
    private Timestamp write_date;


}