package com.mini;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 21:19
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-biz.xml")
public class BaseTest {

    @Test
    public void test(){
        String s = "Hello world!";
        System.out.println(s.substring(5,12));

    }

}
