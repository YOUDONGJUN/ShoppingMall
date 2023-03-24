package com.bit.mileage.service;

import com.bit.member.dto.MemberDTO;
import com.bit.mileage.dto.MileageDTO;
import com.bit.mybatis.mileage.MileageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class MileageService {

    @Autowired
    MileageMapper mileageMapper;

    public void getUserMileageStateList(HttpServletRequest request, Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();

        ArrayList<String> userMileageStateList = mileageMapper.getUserMileageStateList(LoginUserIdx);
        model.addAttribute("userMileageStateList", userMileageStateList);
        System.out.println(userMileageStateList);

        userMileageStateList.forEach((mileageState) -> System.out.println(mileageState));

        int usableMileageCount = Collections.frequency(userMileageStateList, "사용가능");
        int usedMileageCount = Collections.frequency(userMileageStateList, "사용됨");
        int unusedMileageCount = Collections.frequency(userMileageStateList, "미가용");
        int refundExpectedMileageCount = Collections.frequency(userMileageStateList, "환불예정");

        model.addAttribute("usableMileageCount", usableMileageCount);
        model.addAttribute("usedMileageCount", usedMileageCount);
        model.addAttribute("unusedMileageCount", unusedMileageCount);
        model.addAttribute("refundExpectedMileageCount", refundExpectedMileageCount);
    }


    public void getUserMileages(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        ArrayList<MileageDTO> userMileageList = mileageMapper.getUserMileages(LoginUserIdx);

        model.addAttribute("userMileageList", userMileageList);
        System.out.println(userMileageList);
        userMileageList.forEach((mileage) -> System.out.println(mileage.getMileageStatus()));
    }


    public void getUserTotalMileage(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        int UserTotalMileageCount = mileageMapper.getUserTotalMileage(LoginUserIdx);
        model.addAttribute("UserTotalMileageCount", UserTotalMileageCount);
        System.out.println(UserTotalMileageCount);

    }


    public void getUnusedUserMileages(Model model, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("userSessionData");
        String LoginUserIdx = memberDTO.getMemberIdx();
        
        ArrayList<MileageDTO> userMileageList = mileageMapper.getUnusedUserMileages(LoginUserIdx);
        model.addAttribute("userMileageList", userMileageList);
        System.out.println(userMileageList);
        userMileageList.forEach((mileage) -> System.out.println(mileage.getMileageStatus()));

    }
}
