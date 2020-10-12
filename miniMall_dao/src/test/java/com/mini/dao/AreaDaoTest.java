package com.mini.dao;
import com.mini.entity.ShopCategory;
import org.junit.Assert;

import com.mini.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 21:28
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dao.xml")
public class AreaDaoTest{
    @Resource
    AreaDao areaDao;

    @Test
    public void testSelectAll(){
        List<Area> list = areaDao.selectAll();
        Assert.assertEquals(2,list.size());

    }
}
