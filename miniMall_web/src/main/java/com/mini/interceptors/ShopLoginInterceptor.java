package com.mini.interceptors;

import com.mini.entity.PersonInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 19:44
 **/
public class ShopLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");

        if (user != null){
            if (user.getUserId() != null && user.getUserId() > 0 && user.getEnableStatus() != null &&user.getEnableStatus() ==1){
                return true;
            }
        }
        response.sendRedirect(request.getContextPath() + "/local/login?usertype=2");
        return false;
    }


}
