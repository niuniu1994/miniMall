package com.mini.biz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.BaseTest;
import com.mini.entity.HeadLine;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 19:24
 **/
public class HeadLineBizTest extends BaseTest {
    @Resource
    HeadLineBiz headLineBiz;

    @Test
    public void testAHeadLineList() throws JsonProcessingException {
        List<HeadLine> lines = headLineBiz.getHeadLineList(null);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(lines));
        Assert.assertEquals(4,lines.size());
    }
}
