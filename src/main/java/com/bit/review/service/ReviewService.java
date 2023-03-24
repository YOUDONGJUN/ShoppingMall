package com.bit.review.service;

import com.bit.member.dto.MemberDTO;
import com.bit.mybatis.review.ReviewMapper;
import com.bit.review.dto.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    ReviewMapper reviewMapper;

    public String reviewSave(MultipartHttpServletRequest mul,
                             HttpServletRequest request,
                             @RequestParam(value = "orderProductId", defaultValue = "ProductId") String orderProductId) {
        ReviewDTO reviewDTO = new ReviewDTO();
        HttpSession session = request.getSession();
        reviewDTO.setReviewId(UUID.randomUUID().toString().replace("-", ""));
        reviewDTO.setReviewTitle(mul.getParameter("title"));
        reviewDTO.setProductScore(Integer.parseInt(mul.getParameter("score")));
        reviewDTO.setProductDegree(mul.getParameter("degree"));
        reviewDTO.setReviewContent(mul.getParameter("content"));
        reviewDTO.setProductId((String) session.getAttribute("orderProductId"));
        reviewDTO.setProductName((String) session.getAttribute("orderProductName"));

        System.out.println("------------------");
        System.out.println((String) session.getAttribute("orderProductId"));
        System.out.println((String) session.getAttribute("orderProductName"));

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        reviewDTO.setReviewWriterIdx(LoginUserIdx);

        MultipartFile file = mul.getFile("image_file_name");
        ReviewFileService rfs = new ReviewFileService();

        if (file.isEmpty()) { // 파일이 비워있으면 true
            reviewDTO.setProductFile1("nan");
        } else { //파일이 존재하는 경우
            reviewDTO.setProductFile1(rfs.saveFile(file));
        }
		/*
		int result = mapper.writeSave(dto);
		String com.bit.message = bfs.getMessage(result, request);
		return com.bit.message;
		*/
        System.out.println(reviewDTO);
        System.out.println("ReviewWriterIdx" + reviewDTO.getReviewWriterIdx());
        System.out.println(reviewDTO.getReviewTitle());
        System.out.println(reviewDTO.getReviewId());
        System.out.println(reviewDTO.getReviewContent());
        System.out.println(reviewDTO.getProductScore());
        System.out.println(reviewDTO.getProductName()); //null 들어옴
        System.out.println(reviewDTO.getProductId()); //null 들어옴
        System.out.println(reviewDTO.getProductDegree());
        System.out.println(reviewDTO.getProductFile1()); //20210703101228-흡수혁명 애견패드 소형 50매2.jpg

        return rfs.getMessage(reviewMapper.reviewSave(reviewDTO), request);
    }

}
