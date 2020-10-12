package com.mini.controller.frontend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mini.biz.ProductBiz;
import com.mini.biz.ProductCategoryBiz;
import com.mini.biz.ShopBiz;
import com.mini.biz.ShopCategoryBiz;
import com.mini.entity.Product;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 22:54
 **/

@Controller("shopDetailController")
@RequestMapping("/front_end")
public class ShopDetailController {
    @Resource
    private ShopBiz shopBiz;

    @Resource
    private ShopCategoryBiz shopCategoryBiz;

    @Resource
    private ProductBiz productBiz;

    @Resource
    private ProductCategoryBiz productCategoryBiz;

    @GetMapping("/shop_detail_page_info")
    @ResponseBody
    private Map<String, Object> getShopDetail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        if (shopId > 0) {
            Shop shop = shopBiz.getShop(shopId);
            List<ProductCategory> list = productCategoryBiz.getProductCategory(shopId);
            map.put("success", true);
            map.put("shopInfo", shop);
            map.put("productCategoryList", list);
        } else {
            map.put("success", false);
            map.put("errMsg", "ShopId can't be null");
        }
        return map;
    }

    @GetMapping("/product_list")
    @ResponseBody
    private Map<String, Object> getProductList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int pageNum = HttpServletRequestUtil.getInt(request, "pageNum");
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Product product = null;

        if (pageNum > 0 && shopId > 0) {

            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");

            String productName = HttpServletRequestUtil.getString(request, "productName");

            product = compactProductCondition4Search(shopId, productCategoryId, productName);

            PageHelper.startPage(pageNum, 3);
            Page<Product> productPage = (Page<Product>) productBiz.getProductList(product);
            PageInfo pageInfo = new PageInfo<>(productPage);

            map.put("productList", pageInfo);
            map.put("success", true);
        } else {
            map.put("success", false);
            map.put("errMsg:", "Page number can't inferior 0");
        }

        return map;
    }


    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product product = new Product();

        if (shopId > 0) {
//            查询shopCategory父类下的店
            Shop shop = new Shop();
            shop.setShopId(shopId);
            product.setShop(shop);
        }

        if (productCategoryId > 0) {
            //查询某一个二级shopcategory下的店
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
        }

        if (productName != null) {
            product.setProductName(productName);
        }
        product.setEnableStatus(1);

        return product;
    }
}
