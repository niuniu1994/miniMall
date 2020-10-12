package com.mini.controller.shopAdmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mini.biz.AreaBiz;
import com.mini.biz.ProductCategoryBiz;
import com.mini.biz.ShopBiz;
import com.mini.biz.ShopCategoryBiz;
import com.mini.dto.ShopExecution;
import com.mini.entity.Area;
import com.mini.entity.PersonInfo;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import com.mini.enums.ShopStateEnum;
import com.mini.util.CodeUtil;
import com.mini.util.HttpServletRequestUtil;
import com.mini.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-28 17:39
 **/

@Controller("shopManagementController")
@RequestMapping("/shop_admin")
public class ShopManagementController {

    @Resource
    private ProductCategoryBiz productCategoryBiz;

    @Resource
    private ShopBiz shopBiz;

    @Resource
    private ShopCategoryBiz shopCategoryBiz;

    @Resource
    private AreaBiz areaBiz;



    @GetMapping("/shop_management_info")
    @ResponseBody
    public Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId <= 0){
            //add shop to the session preparing for procedures coming to deal with info of shop
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            if (currentShopObj == null){
                map.put("redirect", true);
                map.put("url", "/shop/shop_list");
            }else {
                Shop shop = (Shop) currentShopObj;
                map.put("redirect:", false);
                map.put("shopId", shop.getShopId());
            }
        }else {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", shop);
            map.put("redirect", false);
        }

        return map;
     }

    @GetMapping("/get_shop_list")
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();


        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        Shop shopCondition = new Shop();
        shopCondition.setOwner(user);
        try {
            int pageNum = HttpServletRequestUtil.getInt(request, "pageNum");
            PageHelper.startPage(pageNum, 10);
            List<Shop> shopList = shopBiz.getShopList(shopCondition).getShopList();
            PageInfo pageInfo = new PageInfo<>(shopList);

            request.getSession().setAttribute("shopList",shopList);

            map.put("shopList",pageInfo);
            map.put("user", user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/shop")
    @ResponseBody
    public Map<String, Object> getShop(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId >= 1) {
            try {
                map.put("shop", shopBiz.getShop(shopId));
                map.put("areaList", areaBiz.getAreaList());
                map.put("success", true);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.getMessage());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "shop not found");
        }
        return map;
    }

    @RequestMapping("shop_init_info")
    @ResponseBody
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> map = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        List<Area> areaList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryBiz.getShopCategoryList(new ShopCategory());
            areaList = areaBiz.getAreaList();
            map.put("shopCategoryList", shopCategoryList);
            map.put("areaList", areaList);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        return map;
    }

    @RequestMapping("/registration_shop")
    @ResponseBody
    public Map<String, Object> registerShop(HttpServletRequest request) {
        //receive data
        Map<String, Object> map = new HashMap<>();

        String s = HttpServletRequestUtil.getString(request, "verifyCode");
        if (!CodeUtil.checkVerifyCode(request)) {
            map.put("success", false);
            map.put("errMsg", "验证码错误");
            return map;
        }

        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        // JSON -> POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }

        //strip mutipart file from request
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            map.put("success", false);
            map.put("errMsg", "shop image can't be null");
        }

        //register shop
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            owner.setUserId(owner.getUserId());
            shop.setOwner(owner);

            ShopExecution shopExecution = shopBiz.addShop(ImageUtil.transferCommonsMultipartFileToFile(shopImg), shop);
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                map.put("success", true);

//              get list of shop of user
                List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                if (shopList == null) {
                    shopList = new ArrayList<>();
                }
                shopList.add(shop);
                request.getSession().setAttribute("shopList", shopList);


            } else {
                map.put("success", false);
                map.put("errMsg", shopExecution.getStateInfo());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "shop info can't be null");
        }
        //return result
        return map;

    }

    @RequestMapping("/modification_shop")
    @ResponseBody
    public Map<String, Object> modifyShop(HttpServletRequest request) {
        //receive data
        Map<String, Object> map = new HashMap<>();

        String s = HttpServletRequestUtil.getString(request, "verifyCode");
        if (!CodeUtil.checkVerifyCode(request)) {
            map.put("success", false);
            map.put("errMsg", "验证码错误");
            return map;
        }

        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        // JSON -> POJO
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = objectMapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }

        //strip multipart file from request
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            map.put("success", false);
            map.put("errMsg", "shop image can't be null");
        }

        //register shop
        if (shop != null && shop.getShopId() != null) {

            ShopExecution shopExecution = null;

            if (shopImg == null) {
                shopExecution = shopBiz.modifyShop(null, shop);
            } else {
                shopExecution = shopBiz.modifyShop(ImageUtil.transferCommonsMultipartFileToFile(shopImg), shop);
            }
            if (shopExecution.getState() == ShopStateEnum.SUCCESS.getState()) {
                map.put("success", true);
            } else {
                map.put("success", false);
                map.put("errMsg", shopExecution.getStateInfo());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "shop info can't be null");
        }
        //return result
        return map;

    }
}
