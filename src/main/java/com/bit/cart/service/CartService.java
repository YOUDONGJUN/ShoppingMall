package com.bit.cart.service;

import com.bit.cart.dto.CartDTO;
import com.bit.member.dto.MemberDTO;
import com.bit.mybatis.cart.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class CartService {
    @Autowired
    CartMapper cartMapper;

    public String getMessage(int num, HttpServletRequest request) {
        String message = null; //num = mapper.reviewSave(rDto);
        if (num == 1) {
            message = "<script>alert('새 장바구니 상품을 성공적으로 추가 했습니다');";
            message +=
                    "location.href='" + request.getContextPath() + "/myPage/reaWishes';</script>";
        } else {
            message = "<script>alert('새 장바구니 상품 등록에 실패했습니다.');";
            message +=
                    "location.href='" + request.getContextPath() + "/myPage/reaWishes'';</script>";
        }
        return message;
    }

    public String insertUserCart(HttpServletRequest request,
                                 HttpSession session,
                                 int cartProductCounts,
                                 String cartProductId,
                                 String cartProductFile1,
                                 int cartProductPrice) {

        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(UUID.randomUUID().toString().replace("-", ""));

        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");

        String LoginUserIdx = memberDTO.getMemberIdx();
        cartDTO.setMemberId(LoginUserIdx);
        cartDTO.setCartProductCounts(cartProductCounts);
        cartDTO.setCartProductFile1(cartProductFile1);
        cartDTO.setCartProductId(cartProductId);
        cartDTO.setCartProductPrice(cartProductPrice);

        return getMessage(cartMapper.insertUserCart(cartDTO), request);
    }
}
