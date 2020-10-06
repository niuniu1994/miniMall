package com.mini.controller.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-28 19:44
 **/
@Controller("shopAdminController")
@RequestMapping(value = "/shop_admin")
public class ShopAdminController {

    @RequestMapping(value = "/shop_operation")
    public String shopOperation(){
        return "shop/shop_operation";
    }

    @RequestMapping(value = "/shop_list")
    public String shopList(){
        return "shop/shop_list";
    }

    @RequestMapping(value = "shop_management")
    public String shopManagement(){
        return "shop/shop_management";
    }

    @RequestMapping("/product_management")
    public String productManagement(){
        return "shop/product_management";
    }

    @RequestMapping("/product_category_management")
    public String productCategoryManagement(){
        return "shop/product_category_management";
    }

    @RequestMapping("/product_operation")
    public String productOperation(){
        return "shop/product_operation";
    }
}
