package com.bit.product.service;

import com.bit.cart.dto.CartDTO;
import com.bit.dto.QnaQuestionDTO;
import com.bit.mybatis.product.ProductMapper;
import com.bit.qna.dto.QnaAnswerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;


    public void productView(Model model, int num) { // 모든 상품 보기
        int allCount = productMapper.selectProductCount(); // 상품 총 개수 얻어오기
        int pageLetter = 6; // 한 페이지에 6개 출력
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectAllProductList(start, end));
    }

    public void katView(Model model, String kat, int num) { // 카테고리 보기
        int allCount = productMapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectAllCategorieList(kat, start, end));
    }

    public void recommendAllView(Model model, int num) { // 모든 상품 인기도순 보기
        int allCount = productMapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectRecommendAllProductList(start, end));
    }

    public void recommendKatView(Model model, String kat, int num) { // 카테고리 인기도순 보기
        int allCount = productMapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectRecommendAllCategorieList(kat, start, end));
    }

    public void priceAllView(Model model, int num) { // 모든 상품 가격순 보기
        int allCount = productMapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectPriceAllProductList(start, end));
    }

    public void priceKatView(Model model, String kat, int num) { // 카테고리 가격순 보기
        int allCount = productMapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectPriceAllCategorieList(kat, start, end));
    }

    public void scoreAllView(Model model, int num) {
        int allCount = productMapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", productMapper.selectScoreAllProductList(start, end));
    }

    public void productDetail(Model model, String productId) { // 상품 상세 보기
        model.addAttribute("productDetail", productMapper.selectAllProductDetail(productId));
    }

    public void selectAllReviewList(Model model, String productId, int num) { // 상품 상세 보기 창에서 평점들 출력
        System.out.println("productId : " + productId);
        String postId = productMapper.selectPostId(productId);
        System.out.println("mapper.selecetPostId : " + postId);
        int allCount = productMapper.selectScoreCount(productId);
        System.out.println("mapper.selectScoreCount : " + allCount);
        int pageLetter = 5;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }

        int end = num * pageLetter;
        int start = end + 1 - pageLetter;

        model.addAttribute("repeat", repeat);

        model.addAttribute("review", productMapper.selectAllReviewList(productId, start, end));
    }

    public void updateRecommendCounts(String productId) { // 추천 기능
        productMapper.updateRecommendCounts(productId);
    }

    public void selectAllQnaQuestionList(Model model, String productPostId) { // 문의 리스트 출력
        System.out.println("qnaQuestion_postId : " + productPostId);
        try {
            model.addAttribute("qnaQuestion", productMapper.selectAllQnaQList(productPostId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public QnaAnswerDTO selectQnaAnswerList(String productId) { // 답변 출력
        try {
            System.out.println(productId);
            System.out.println(productMapper.selectQnaAnswerList(productId));
            return productMapper.selectQnaAnswerList(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertQnaQuestion(String questionContent, HttpSession session, String productId) { // 문의하기
        QnaQuestionDTO qnaQDTO = new QnaQuestionDTO();
        long time = System.currentTimeMillis();
        String memberId = (String) session.getAttribute("loginUser");
        String memberIdx = productMapper.selectMemberIdx(memberId);
        System.out.println("ps.insertQnaQuestion_memberId : " + memberId);
        System.out.println("ps.insertQnaQuestion_productPostId : " + productId);
        qnaQDTO.setQuestionId(memberId + time);
        qnaQDTO.setQuestionWriterId(memberIdx);
        qnaQDTO.setProductPostId(productId);
        qnaQDTO.setQuestionTitle("nan");
        qnaQDTO.setQuestionContent(questionContent);
        qnaQDTO.setQuestionCreateDate(new Date(System.currentTimeMillis()));
        qnaQDTO.setQuestionViews(0);
        productMapper.insertQnaQuestion(qnaQDTO);
    }

    public void relativeProduct(Model model, String kat, String productId, int num) {
        int allCount = productMapper.selectRelProductCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("relPro", productMapper.selectRelativeProduct(productId, kat, start, end));
    }

    public void selectProductPost(Model model, String productId) { // 상품 게시글 정보 가져오기
        model.addAttribute("productPost", productMapper.selectProductPost(productId));
    }

    /*
    public void selectProductScore(Model model, String productId) {
        String postId = mapper.selectPostId(productId);
        int scoreCnt = mapper.selectScoreCount(postId);
        Map<String, Object> map = mapper.selectProductScore(postId);
        map.keySet();
    }
    */
    public String selectPostId(String productId) {
        return productMapper.selectPostId(productId);
    }

    public void insertCart(HttpSession session, String productId, int productCounts) {
        String memberId = (String) session.getAttribute("loginUser");
        String memberIdx = productMapper.selectMemberIdx(memberId);
        System.out.println("mapper.selectMemberIdx 성공");
        long time = System.currentTimeMillis();
        String productName = productMapper.selectProductName(productId);
        int productPrice = productMapper.selectPrice(productName);
        System.out.println("mapper.selectPrice : " + productPrice);
        String productFile = productMapper.selectFile(productId);
        System.out.println("mapper.selectFile : " + productFile);
        System.out.println("productId : " + productId);
        System.out.println("memberIdx : " + memberIdx);
        //System.out.println(mapper.checkCart(memberIdx, productId));
        //if(mapper.checkCart(memberIdx, productId) == 1) {
        //	System.out.println("mapper.updateCart");
        //	mapper.updateCart(productCounts, memberIdx, productId);
        //}else {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(memberId + time);
        cartDTO.setCartProductId(productId);
        cartDTO.setCartProductFile1(productName);
        cartDTO.setCartProductPrice(productPrice);
        cartDTO.setCartProductCounts(productCounts);
        cartDTO.setMemberId(memberIdx);
        System.out.println("mapper.insertCart");
        productMapper.insertCart(cartDTO);
        //}
    }
}