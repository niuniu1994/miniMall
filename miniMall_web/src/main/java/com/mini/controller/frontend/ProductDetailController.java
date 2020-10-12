package com.mini.controller.frontend;

import com.mini.biz.ProductBiz;
import com.mini.entity.Product;
import com.mini.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 18:28
 **/
@Controller("productDetailController")
@RequestMapping("/front_end")
public class ProductDetailController {

    @Resource
    private ProductBiz productBiz;


    @GetMapping("/product_info")
    @ResponseBody
    private Map<String, Object> getProductInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        long productId = HttpServletRequestUtil.getLong(request, "productId");

        if (productId > 0){
            Product product = productBiz.getProductById(productId);
            map.put("product", product);
            map.put("success", true);
        }else {
            map.put("success", false);
            map.put("errMsg:", "ProductId can't be null");
        }
        return map;
    }
}
