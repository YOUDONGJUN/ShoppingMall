package com.bit.board.dto;

import lombok.Data;

@Data
public class BoardDTO {
    private int writeNo;
    private String title;
    private String content;
    private String saveDate;
    private int hit;
    private String imageFileName;
    private String id;

}
