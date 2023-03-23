package com.bit.wish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bit.wish.service.WishService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/wish")
public class WishController {
    @Autowired
	WishService ws;

    @GetMapping("/readWishes")
    public String createReview() {
        return "eunbin/readWishes";
    }

    @ResponseBody
    @PostMapping(value = "deleteUserWishes", produces = "application/json; charset=utf-8")
    public String deleteUserWishes(
            @RequestBody Map<String, String> map,
            HttpServletRequest request) {
        try {
            System.out.println("wishController");
            System.out.println(map.get("wishId"));
            ws.deleteUserWishes(map.get("wishId"), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "eunbin/readWishes";
    }
}
