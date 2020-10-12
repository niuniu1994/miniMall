package com.mini.controller.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 10:09
 **/
@Controller("frontEndController")
@RequestMapping("/front_end")
public class FrontEndController {

    @GetMapping("/index")
    public String toMainPage(){
        return "front/index";
    }

    @GetMapping("/shop_list")
    private String toShopList(){
        return "front/shop_list";
    }

    @GetMapping("/product_detail")
    private String toProductDetail(){
        return "front/product_detail";
    }

    @GetMapping("/shop_detail")
    private String toShopDetail(){
        return "front/shop_detail";}
}
