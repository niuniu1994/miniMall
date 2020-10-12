package com.mini.controller.frontend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mini.biz.AreaBiz;
import com.mini.biz.HeadLineBiz;
import com.mini.biz.ShopBiz;
import com.mini.biz.ShopCategoryBiz;
import com.mini.dto.ShopExecution;
import com.mini.entity.Area;
import com.mini.entity.HeadLine;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import com.mini.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @create: 2020-10-07 09:32
 **/
@Controller("mainPageController")
@RequestMapping("/front_end")
public class MainPageController {

    @Resource
    private HeadLineBiz headLineBiz;

    @Resource
    private ShopCategoryBiz shopCategoryBiz;

    @Resource
    private AreaBiz areaBiz;

    @Resource
    ShopBiz shopBiz;

    @GetMapping("/list_main_page_info")
    @ResponseBody
    private Map<String, Object> listMainPageInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try {
            shopCategoryList = shopCategoryBiz.getShopCategoryList(null);
            map.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }

        List<HeadLine> headLines = new ArrayList<>();
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            headLines = headLineBiz.getHeadLineList(headLine);
            map.put("headLineList", headLines);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        map.put("success", true);
        return map;
    }

    @GetMapping("list_shop_page_info")
    @ResponseBody
    private Map<String, Object> listShopPageInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        List<ShopCategory> shopCategoryList = null;
        long parentId = HttpServletRequestUtil.getLong(request, "parentId");
        if (parentId > 0) {
            try {
                ShopCategory son = new ShopCategory();
                ShopCategory parent = new ShopCategory();
                parent.setShopCategoryId(parentId);
                son.setParent(parent);

                shopCategoryBiz.getShopCategoryList(son);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.getMessage());
            }
        } else {
            try {
                shopCategoryList = shopCategoryBiz.getShopCategoryList(null);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.getMessage());
            }
        }
        map.put("shopCategoryList", shopCategoryList);
        List<Area> areaList = null;
        try {
            areaList = areaBiz.getAreaList();
            map.put("areaList", areaList);
            map.put("success", true);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }

        return map;
    }

    @GetMapping("list_shop")
    @ResponseBody
    private Map<String, Object> listShop(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        int pageNum = HttpServletRequestUtil.getInt(request, "pageNum");

        if (pageNum > 0) {
            //获取一级类别
            long parentId = HttpServletRequestUtil.getLong(request, "parentId");
            //获取区域
            int areaId = HttpServletRequestUtil.getInt(request, "areaId");
            //获取二级类别
            long shopCategoryId = HttpServletRequestUtil.getLong(request, "shopCategoryId");
            //模糊查询商店名
            String shopName = HttpServletRequestUtil.getString(request, "shopName");

            Shop shopCondition = compactShopCondition4Search(parentId, shopCategoryId, areaId, shopName);

            PageHelper.startPage(pageNum, 3);
            ShopExecution shopExecution = shopBiz.getShopList(shopCondition);
            Page<Shop> shopPage = (Page<Shop>) shopExecution.getShopList();
            PageInfo<Shop> pageInfo = new PageInfo<>(shopPage);

            map.put("shopList", pageInfo);
            map.put("success", true);
        } else {
            map.put("success", false);
            map.put("errMsg : ", "Fail to get shop list");
        }
        return map;
    }


    private Shop compactShopCondition4Search(long parentId, long shopCategoryId, int areaId, String shopName) {
        Shop shop = new Shop();

        if (parentId > 0) {
//            查询shopCategory父类下的店
            ShopCategory son = new ShopCategory();
            ShopCategory parent = new ShopCategory();

            parent.setShopCategoryId(parentId);
            son.setParent(parent);
            shop.setShopCategory(son);
        }

        if (shopCategoryId > 0) {
            //查询某一个二级shopcategory下的店

            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setShopCategoryId(shopCategoryId);
            shop.setShopCategory(shopCategory);
        }

        if (areaId > 0) {
            //查询某一个区域下的
            Area area = new Area();
            area.setAreaId(areaId);
            shop.setArea(area);

        }

        if (shopName != null) {
            shop.setShopName(shopName);
        }
        shop.setEnableStatus(1);
        return shop;
    }
}