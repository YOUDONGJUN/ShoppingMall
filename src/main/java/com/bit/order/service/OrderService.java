package com.bit.order.service;

import com.bit.member.dto.MemberDTO;
import com.bit.mybatis.order.OrderMapper;
import com.bit.order.dto.OrderDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class OrderService {

//    private final String NAMESPACE = "mapper.OrderMapper";
    @Autowired
    OrderMapper orderMapper;
//    private SqlSession session;

    public void getUserOrdersDeliveryStates(HttpServletRequest request,
                                            Model model,
                                            HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        ArrayList<String> deliveryStateList = orderMapper.getUserOrdersDeliveryStates(LoginUserIdx);
        model.addAttribute("deliveryStateList", deliveryStateList);
        deliveryStateList.forEach((deliveryState) -> System.out.println(deliveryState));
        System.out.println(deliveryStateList);

        int beforeDepositStateCount = Collections.frequency(deliveryStateList, "입금전");
        int readyToDeliveryStateCount = Collections.frequency(deliveryStateList, "배송준비중");
        int onDeliveryStateCount = Collections.frequency(deliveryStateList, "배송중");
        int DeliveryOverStateCount = Collections.frequency(deliveryStateList, "배송완료");

        model.addAttribute("beforeDepositStateCount", beforeDepositStateCount);
        model.addAttribute("readyToDeliveryStateCount", readyToDeliveryStateCount);
        model.addAttribute("onDeliveryStateCount", onDeliveryStateCount);
        model.addAttribute("DeliveryOverStateCount", DeliveryOverStateCount);
    }


    public void getUserOrders(Model model,
                              HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        ArrayList<OrderDTO> userOrderList = orderMapper.getUserOrders(LoginUserIdx);
        model.addAttribute("userOrderList", userOrderList);
        userOrderList.forEach((order) -> System.out.println(order.getOrderProductName()));
    }

}
