package com.mini.interceptors;

import com.mini.entity.Shop;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 19:51
 **/
public class ShopPermissionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        @SuppressWarnings("unchecked")
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");

        if (currentShop != null && shopList != null && !shopList.isEmpty()) {
            for (Shop shop : shopList) {
                if (shop.getShopId().equals(currentShop.getShopId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
