package com.mini.dao;

import com.mini.BaseTest;
import com.mini.entity.ShopCategory;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-29 11:29
 **/
public class ShopCategoryDaoTest extends BaseTest {
    @Resource
    ShopCategoryDao shopCategoryDao;

    @Test
    public void selectShopCategory() {
        ShopCategory parent = new ShopCategory();
        ShopCategory son = new ShopCategory();

        parent.setShopCategoryId(10L);
        son.setParent(parent);

        List<ShopCategory> list = shopCategoryDao.select(son);
            Assert.assertEquals(2, list.size());
    }



}
