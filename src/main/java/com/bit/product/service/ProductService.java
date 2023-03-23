package com.bit.product.service;

import com.bit.cart.dto.CartDTO;
import com.bit.dto.QnaQDTO;
import com.bit.mybatis.product.ProductMapper;
import com.bit.qna.dto.QnaADTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Service
public class ProductService {

    @Autowired
    ProductMapper mapper;


    public void productView(Model model, int num) { // 모든 상품 보기
        int allCount = mapper.selectProductCount(); // 상품 총 개수 얻어오기
        int pageLetter = 6; // 한 페이지에 6개 출력
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectAllProductList(start, end));
    }

    public void katView(Model model, String kat, int num) { // 카테고리 보기
        int allCount = mapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectAllCategorieList(kat, start, end));
    }

    public void recommendAllView(Model model, int num) { // 모든 상품 인기도순 보기
        int allCount = mapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectRecommendAllProductList(start, end));
    }

    public void recommendKatView(Model model, String kat, int num) { // 카테고리 인기도순 보기
        int allCount = mapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectRecommendAllCategorieList(kat, start, end));
    }

    public void priceAllView(Model model, int num) { // 모든 상품 가격순 보기
        int allCount = mapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectPriceAllProductList(start, end));
    }

    public void priceKatView(Model model, String kat, int num) { // 카테고리 가격순 보기
        int allCount = mapper.selectCategorieCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectPriceAllCategorieList(kat, start, end));
    }

    public void scoreAllView(Model model, int num) {
        int allCount = mapper.selectProductCount();
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("product", mapper.selectScoreAllProductList(start, end));
    }

    public void productDetail(Model model, String productId) { // 상품 상세 보기
        model.addAttribute("productDetail", mapper.selectAllProductDetail(productId));
    }

    public void selectAllReviewList(Model model, String productId, int num) { // 상품 상세 보기 창에서 평점들 출력
        System.out.println("productId : " + productId);
        String postId = mapper.selectPostId(productId);
        System.out.println("mapper.selecetPostId : " + postId);
        int allCount = mapper.selectScoreCount(productId);
        System.out.println("mapper.selectScoreCount : " + allCount);
        int pageLetter = 5;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }

        int end = num * pageLetter;
        int start = end + 1 - pageLetter;

        model.addAttribute("repeat", repeat);

        model.addAttribute("review", mapper.selectAllReviewList(productId, start, end));
    }

    public void updateRecommendCounts(String productId) { // 추천 기능
        mapper.updateRecommendCounts(productId);
    }

    public void selectAllQnaQList(Model model, String productPostId) { // 문의 리스트 출력
        System.out.println("qnaQ_postId : " + productPostId);
        try {
            model.addAttribute("qnaq", mapper.selectAllQnaQList(productPostId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public QnaADTO selectQnaAList(String productId) { // 답변 출력
        try {
            System.out.println(productId);
            System.out.println(mapper.selectQnaAList(productId));
            return mapper.selectQnaAList(productId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertQnaQ(String questionContent, HttpSession session, String productId) { // 문의하기
        QnaQDTO qnaQDTO = new QnaQDTO();
        long time = System.currentTimeMillis();
        String memberId = (String) session.getAttribute("loginUser");
        String memberIdx = mapper.selectMemberIdx(memberId);
        System.out.println("ps.insertQnaQ_memberId : " + memberId);
        System.out.println("ps.insertQnaQ_productPostId : " + productId);
        qnaQDTO.setQuestionId(memberId + time);
        qnaQDTO.setQuestionWriterId(memberIdx);
        qnaQDTO.setProductPostId(productId);
        qnaQDTO.setQuestionTitle("nan");
        qnaQDTO.setQuestionContent(questionContent);
        qnaQDTO.setQuestionCreateDate(new Date(System.currentTimeMillis()));
        qnaQDTO.setQuestionViews(0);
        mapper.insertQnaQ(qnaQDTO);
    }

    public void relativeProduct(Model model, String kat, String productId, int num) {
        int allCount = mapper.selectRelProductCount(kat);
        int pageLetter = 6;
        int repeat = allCount / pageLetter;
        if (allCount % pageLetter != 0) {
            repeat += 1;
        }
        int end = num * pageLetter;
        int start = end + 1 - pageLetter;
        model.addAttribute("repeat", repeat);
        model.addAttribute("relPro", mapper.selectRelativeProduct(productId, kat, start, end));
    }

    public void selectProductPost(Model model, String productId) { // 상품 게시글 정보 가져오기
        model.addAttribute("productPost", mapper.selectProductPost(productId));
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
        return mapper.selectPostId(productId);
    }

    public void insertCart(HttpSession session, String productId, int productCounts) {
        String memberId = (String) session.getAttribute("loginUser");
        String memberIdx = mapper.selectMemberIdx(memberId);
        System.out.println("mapper.selectMemberIdx 성공");
        long time = System.currentTimeMillis();
        String productName = mapper.selectProductName(productId);
        int productPrice = mapper.selectPrice(productName);
        System.out.println("mapper.selectPrice : " + productPrice);
        String productFile = mapper.selectFile(productId);
        System.out.println("mapper.selectFile : " + productFile);
        System.out.println("productId : " + productId);
        System.out.println("memberIdx : " + memberIdx);
        //System.out.println(mapper.checkCart(memberIdx, productId));
        //if(mapper.checkCart(memberIdx, productId) == 1) {
        //	System.out.println("mapper.updateCart");
        //	mapper.updateCart(productCounts, memberIdx, productId);
        //}else {
        CartDTO dto = new CartDTO();
        dto.setCartId(memberId + time);
        dto.setCartProductId(productId);
        dto.setCartProductFile1(productName);
        dto.setCartProductPrice(productPrice);
        dto.setCartProductCounts(productCounts);
        dto.setMemberId(memberIdx);
        System.out.println("mapper.insertCart");
        mapper.insertCart(dto);
        //}
    }
}