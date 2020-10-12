package com.mini.biz;

import com.mini.BaseTest;
import com.mini.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-25 09:10
 **/
public class AreaBizTest extends BaseTest {

    @Resource
    AreaBiz areaBiz;

    @Resource(description = "jedisTemplate")
    RedisTemplate redisTemplate;
    @Test
    public void getAreaListTest(){
        List<Area> list = areaBiz.getAreaList();
        Assert.assertEquals("西苑", list.get(0).getAreaName());
    }

    @Test
    public void testRedisTemplate(){

        List<Area> list = areaBiz.getAreaList();

        list.forEach(System.out::println);
    }
}
