package com.mini.dao;
import com.mini.entity.ShopCategory;
import org.junit.Assert;
import com.mini.BaseTest;
import com.mini.entity.Area;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 21:28
 **/
public class AreaDaoTest extends BaseTest {
    @Resource
    AreaDao areaDao;

    @Test
    public void testSelectAll(){
        List<Area> list = areaDao.selectAll();
        Assert.assertEquals(2,list.size());

    }
}
