package com.mini.controller.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 19:01
 **/
@Controller
@RequestMapping("/local")
public class LocalController {

    @GetMapping("/login")
    public String toLogin(){
        return "local/login";
    }
}
