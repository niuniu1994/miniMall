package com.mini.controller;

import com.mini.biz.AreaBiz;
import com.mini.entity.Area;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-25 09:28
 **/
@Controller("areaController")
@RequestMapping("/superAdmin")
public class AreaController {

    private Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Resource
    private AreaBiz areaBiz;

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getAreaList(){

        logger.info("===start===");
        StopWatch watch = new StopWatch();
        watch.start();
        Map<String, Object> map = new HashMap<>();

        List<Area> list = new ArrayList<>();
        try {
            list = areaBiz.getAreaList();
            map.put("rows", list);
            map.put("total", list.size());
        }catch (Exception e){
            map.put("success", false);
            map.put("errMsg", e.toString());
        }
        watch.stop();
        logger.debug("costTime:[{}ms]",(watch.getTotalTimeMillis()));
        logger.info("===结束===");
        return map;
    }
}
