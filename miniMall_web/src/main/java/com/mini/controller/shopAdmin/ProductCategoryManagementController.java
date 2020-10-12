package com.mini.controller.shopAdmin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mini.biz.ProductCategoryBiz;
import com.mini.dao.ProductDao;
import com.mini.dto.ProductCategoryExecution;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.enums.ProductCategoryStateEnum;
import com.mini.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 14:18
 **/
@Controller
@RequestMapping("/shop_admin")
public class ProductCategoryManagementController {

    @Resource
    private ProductCategoryBiz productCategoryBiz;

    @Resource
    private ProductDao productDao;

    @GetMapping("/product_category_list")
    @ResponseBody
    public Map<String, Object> getProductCategoryList(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        Shop shop = new Shop();
        shop.setShopId(28L);
        request.getSession().setAttribute("currentShop", shop);

        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");

        if (currentShop != null && currentShop.getShopId()> 0){
            int pageNum = HttpServletRequestUtil.getInt(request, "pageNum");
            if (pageNum >= 1){
                PageHelper.startPage(pageNum, 4);
                List<ProductCategory> list= productCategoryBiz.getProductCategory(currentShop.getShopId());
                PageInfo pageInfo = new PageInfo(list);
                map.put("productCategoryList", pageInfo);
            }else {
                List<ProductCategory> list= productCategoryBiz.getProductCategory(currentShop.getShopId());
                map.put("productCategoryList",list);
            }

            map.put("success", true);
        }else {
            map.put("success", false);
            map.put("errMsg", "currentShop is null");
        }
        return map;
    }

    @PostMapping("add_product_categories")
    @ResponseBody
    private Map<String, Object> addProductCategories(HttpServletRequest request , @RequestBody List<ProductCategory> productCategoryList){
        Map<String, Object> map = new HashMap<>();
        Shop shop = (Shop) request.getSession().getAttribute("currentShop");
        productCategoryList.forEach(a -> a.setShopId(shop.getShopId()));

        if (productCategoryList.size()>0){
            try {
               ProductCategoryExecution productCategoryExecution =  productCategoryBiz.batchAddProductCategory(productCategoryList);
               if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                   map.put("success", true);
               }else {
                   map.put("success", false);
                   map.put("errMsg", productCategoryExecution.getStateInfo());
               }
            }catch (Exception e){
                map.put("success", false);
                map.put("errMsg", e.getMessage());
            }
            return map;
        }else {
            map.put("success", false);
            map.put("errMsg", "请至少输入一个商品类别");
        }
        return map;
    }
    @PostMapping("/remove_product_category")
    @ResponseBody
    private Map<String, Object> removeProductCategory(@RequestParam Long productCategoryId,HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();
        if (productCategoryId != null && productCategoryId > 0){
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution productCategoryExecution = productCategoryBiz.removeProductCategory(productCategoryId,currentShop.getShopId());
                if (productCategoryExecution.getState() == ProductCategoryStateEnum.SUCCESS.getState()){
                    map.put("success", true);
                }else {
                    map.put("success", false);
                    map.put("errMsg", productCategoryExecution.getStateInfo());
                }
            }catch (Exception e){
                map.put("success", true);
                map.put("errMsg", e.getMessage());
            }
            return map;
        }else {
            map.put("success", false);
            map.put("errMsg", "请至少选择一个商品种类");
        }
        return map;
    }

}
