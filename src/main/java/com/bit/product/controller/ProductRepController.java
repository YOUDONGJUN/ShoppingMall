package com.bit.product.controller;

import com.bit.product.service.ProductService;
import com.bit.qna.dto.QnaADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProductRepController {
    @Autowired
    ProductService ps;

    @PostMapping(value = "qnaaview",
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public QnaADTO qnaAView(@RequestBody Map<String, Object> questionId) { // 문의 글 누르면 답변 가져오기
        System.out.println(questionId.get("questionId"));
        return ps.selectQnaAList((String) questionId.get("questionId"));
    }
}
