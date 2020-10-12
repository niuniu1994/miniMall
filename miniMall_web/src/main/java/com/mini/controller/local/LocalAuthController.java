package com.mini.controller.local;

import com.mini.biz.LocalAuthBiz;
import com.mini.dto.LocalAuthExecution;
import com.mini.entity.LocalAuth;
import com.mini.entity.PersonInfo;
import com.mini.enums.LocalAuthStateEnum;
import com.mini.util.CodeUtil;
import com.mini.util.HttpServletRequestUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 19:04
 **/
@RestController
@RequestMapping("/local")
public class LocalAuthController {
    @Resource
    private LocalAuthBiz localAuthBiz;

    @RequestMapping(value = "/login_check", method = RequestMethod.POST)
    @ResponseBody
    /**
     * loginCheck
     * @param request
     * @return
     */
    private Map<String, Object> loginCheck(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 验证码校验
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            map.put("success", false);
            map.put("errMsg", "输入了错误的验证码");
            return map;
        }
        // 获取输入的帐号
        String userName = HttpServletRequestUtil.getString(request, "userName");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取用户状态码
//        Integer status = HttpServletRequestUtil.getInt(request, "usertype");
        // 从session中获取当前用户信息(用户一旦通过微信登录之后，便能获取到用户的信息)
        if (userName != null && password != null){
            LocalAuth localAuth = localAuthBiz.getLocalAuthByUsernameAndPwd(userName, password);
            if (localAuth != null && localAuth.getPersonInfo() != null){
                map.put("userType", localAuth.getPersonInfo().getUserType());
                map.put("success", true);
                request.getSession().setAttribute("user", localAuth.getPersonInfo());
            }else {
                map.put("success", false);
                map.put("errMsg", "用户名或密码错误");
            }
        }else {
            map.put("success", false);
            map.put("errMsg", "用户名和密码均不能为空");
        }

        return map;
    }
}
