package com.bit.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.bit.product.service.ProductService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("allView") // 모든 상품 보기
    public String allView(Model model, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.productView(model, num);
        return "product/allView";
    }

    @GetMapping("dogView/{kat}") // 강아지 카테고리 보기
    public String dogView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.katView(model, kat, num);
        return "product/dogView";
    }

    @GetMapping("catView/{kat}") // 고양이 카테고리 보기
    public String catView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.katView(model, kat, num);
        return "product/catView";
    }

    @GetMapping("recommendAllView") // 모든 상품 인기도순 보기
    public String recommendAllView(Model model, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.recommendAllView(model, num);
        return "product/recommendAllView";
    }

    @GetMapping("recommendDogView/{kat}") // 강아지 인기도순 보기
    public String recommendDogView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.recommendKatView(model, kat, num);
        return "product/recommendDogView";
    }

    @GetMapping("recommendCatView/{kat}") // 고양이 인기도순 보기
    public String recommendCatView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.recommendKatView(model, kat, num);
        return "product/recommendCatView";
    }

    @GetMapping("priceAllView") // 모든 상품 가격순 보기
    public String priceAllView(Model model, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.priceAllView(model, num);
        return "product/priceAllView";
    }

    @GetMapping("priceDogView/{kat}") // 강아지 가격순 보기
    public String priceDogView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.priceKatView(model, kat, num);
        return "product/priceDogView";
    }

    @GetMapping("priceCatView/{kat}") // 고양이 가격순 보기
    public String priceCatView(Model model, @PathVariable String kat, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.priceKatView(model, kat, num);
        return "product/priceCatView";
    }

    @GetMapping("scoreAllView")
    public String scoreAllView(Model model, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.scoreAllView(model, num);
        return "product/scoreAllView";
    }

    @GetMapping("productDetail/{productId}/{productCategory}/{productName}") // 상품 상세 보기
    public String productDetail(Model model, @PathVariable String productId, @PathVariable String productCategory, @PathVariable String productName, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.relativeProduct(model, productCategory, productName, num);
        System.out.println("ps.relativeProduct 성공");
        productService.productDetail(model, productId);
        System.out.println("ps.productDetail 성공");
        productService.selectAllReviewList(model, productId, num);
        System.out.println("ps.selectAllReviewList 성공");
        String postId = productService.selectPostId(productId);
        System.out.println("controller_postId : " + postId + ", productId : " + productId);
        productService.selectAllQnaQuestionList(model, productId);
        System.out.println("ps.selectAllQnaQuestionList 성공");
        productService.selectProductPost(model, productId);
        System.out.println("ps.selectProductPost 성공");
        return "product/productDetail";
    }

    @GetMapping("download") // 상품 이미지 가져오기
    public void download(@RequestParam("file") String fileName,
                         HttpServletResponse response)
            throws Exception {
		/*
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		File file = new File("c:\\kgitbank\\image_repo\\"+fileName);
		FileInputStream in = new FileInputStream(file);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
		*/
    }

    @GetMapping("updaterecommend/{productId}/{productCategory}/{productName}") // 추천하고 상품 상세 창 다시 띄우기
    public String updaterecommend(Model model, @PathVariable String productId, @PathVariable String productCategory, @PathVariable String productName, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.relativeProduct(model, productCategory, productName, num);
        System.out.println("ps.relativeProduct 성공");
        productService.updateRecommendCounts(productId);
        System.out.println("ps.updateRecommendCounts 성공");
        productService.productDetail(model, productId);
        System.out.println("ps.productDetail 성공");
        productService.selectAllReviewList(model, productId, num);
        System.out.println("ps.selectAllReviewList 성공");
        productService.selectAllQnaQuestionList(model, productId);
        System.out.println("ps.selectAllQnaQuestionList 성공");
        productService.selectProductPost(model, productId);
        System.out.println("ps.selectProductPost 성공");
        return "product/productDetail";
    }

    @PostMapping("addQnaQuestion/{productId}/{productCategory}/{productName}") // 문의하기
    public String addQnaQuestion(Model model, @PathVariable String productId, @RequestParam String questionContent, HttpSession session, @PathVariable String productCategory, @PathVariable String productName, @RequestParam(value = "num", required = false, defaultValue = "1") int num) {
        productService.relativeProduct(model, productCategory, productName, num);
        System.out.println("ps.relativeProduct 성공");
        productService.productDetail(model, productId);
        System.out.println("ps.productDetail 성공");
        productService.selectAllReviewList(model, productId, num);
        System.out.println("ps.selectAllReviewList 성공");
        productService.insertQnaQuestion(questionContent, session, productId);
        System.out.println("ps.insertQnaQ 성공");
        productService.selectAllQnaQuestionList(model, productId);
        System.out.println("ps.selectAllQnaQList 성공");
        productService.selectProductPost(model, productId);
        System.out.println("ps.selectProductPost 성공");
        return "product/productDetail";
    }

    @GetMapping("insertCart/{productId}/{productCnt}")
    public String insertCart(HttpSession session,
                             @PathVariable String productId,
                             @PathVariable String productCnt) {
        System.out.println("String - " + productCnt);
        int productCounts = Integer.parseInt(productCnt);
        System.out.println("int - " + productCounts);
        productService.insertCart(session, productId, productCounts);
        return "redirect:/myPage/cart";
    }
}
