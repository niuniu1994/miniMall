package com.mini.controller.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.biz.ProductBiz;
import com.mini.dao.ProductCategoryDao;
import com.mini.dto.ProductExecution;
import com.mini.entity.Product;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.enums.ProductStateEnum;
import com.mini.exception.ProductOperationException;
import com.mini.util.CodeUtil;
import com.mini.util.HttpServletRequestUtil;
import com.mini.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 22:12
 **/
@Controller("productManagementController")
@RequestMapping("shop_admin")
public class ProductManagementController {

    private static final int IMAGEMAXCOUNT = 6;

    @Resource
    private ProductBiz productBiz;

    @Resource
    private ProductCategoryDao productCategoryDao;

    @PostMapping("/add_product")
    @ResponseBody
    private Map<String, Object> addProduct(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        String s = HttpServletRequestUtil.getString(request, "verifyCode");
        if (!CodeUtil.checkVerifyCode(request)) {
            map.put("success", false);
            map.put("errMsg", "验证码错误");
            return map;
        }

        String productStr = HttpServletRequestUtil.getString(request, "productStr");
        // JSON -> POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(productStr, Product.class);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }

        List<File> productImgList = new ArrayList<>();

        CommonsMultipartFile thumbnail = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;

            //get thumbnail of product
            thumbnail = (CommonsMultipartFile) multipartHttpServletRequest.getFile("thumbnail");

            //get detail image of product
            for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                CommonsMultipartFile productImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg" + i);
                if (productImg != null) {
                    productImgList.add(ImageUtil.transferCommonsMultipartFileToFile(productImg));
                } else {
                    break;
                }
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "Upload image can't be null");
            return map;
        }

        if (product != null && thumbnail != null && productImgList.size() > 0) {
            try {
                // get current shop form session in order to decrease dependency to the data coming from front end
                Shop shop = (Shop) request.getSession().getAttribute("currentShop");
                product.setShop(shop);

                ProductExecution productExecution = productBiz.addProduct(product, ImageUtil.transferCommonsMultipartFileToFile(thumbnail), productImgList);
                if (productExecution.getState() == ProductStateEnum.SUCCESS.getState()) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                    map.put("errMsg", "Fail to add image");
                }
            } catch (ProductOperationException e) {
                map.put("success", false);
                map.put("errMsg", e.getMessage());
                return map;
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "Please input information of product");
        }


        return map;
    }

    @GetMapping("/get_product")
    @ResponseBody
    private Map<String, Object> getProduct(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<ProductCategory> productCategoryList = new ArrayList<>();
        Product product = null;
        long productId = HttpServletRequestUtil.getLong(request, "productId");

        if (productId > 0) {
            try {
                product = productBiz.getProductById(productId);
                productCategoryList = productCategoryDao.selectByShopId(product.getShop().getShopId());
            }catch (Exception e){
                map.put("success", false);
                map.put("errMsg", e.getMessage());
                return map;
            }
            map.put("product", product);
            map.put("productCategoryList", productCategoryList);
            map.put("success", true);
            return map;
        }else {
            map.put("success", false);
            map.put("errMsg", "ShopId can't be null");
            return map;
        }

    }


}
