package com.mini.dao;

import com.mini.BaseDaoTest;

import com.mini.entity.HeadLine;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 09:08
 **/
public class HeadLineTest extends BaseDaoTest {
    @Resource
    HeadLineDao headLineDao;

    @Test
    public void testASelect(){
        HeadLine headLine = new HeadLine();
        headLine.setEnableStatus(1);
        List<HeadLine> lines = headLineDao.selectHeadList(headLine);
        Assert.assertEquals(6,lines.size());
    }
}
