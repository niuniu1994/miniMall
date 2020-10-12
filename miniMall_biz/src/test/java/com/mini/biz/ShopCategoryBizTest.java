package com.mini.biz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.BaseTest;
import com.mini.entity.ShopCategory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 19:20
 **/

public class ShopCategoryBizTest extends BaseTest {
    @Resource
    ShopCategoryBiz shopCategoryBiz;

    @Test
    public void testAShopCategoryList() throws JsonProcessingException {
        ShopCategory shopCategory = new ShopCategory();
        List<ShopCategory> list = shopCategoryBiz.getShopCategoryList(shopCategory);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(list));
    }
}
