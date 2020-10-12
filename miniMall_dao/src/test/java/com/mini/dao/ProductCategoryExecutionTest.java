package com.mini.dao;

import com.google.common.collect.Lists;
import com.mini.BaseDaoTest;

import com.mini.entity.ProductCategory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 11:02
 **/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryExecutionTest extends BaseDaoTest {
    @Resource
    ProductCategoryDao productCategoryDao;

    @Test
    public void testAbatchInsert() {
        ProductCategory productCategory = new ProductCategory();
        ProductCategory productCategory1 = new ProductCategory();

        productCategory.setCreateTime(new Date());
        productCategory.setPriority(2);
        productCategory.setProductCategoryName("摩托");
        productCategory.setShopId(1L);

        productCategory1.setCreateTime(new Date());
        productCategory1.setPriority(3);
        productCategory1.setProductCategoryName("卡车");
        productCategory1.setShopId(1L);

        List<ProductCategory> list = Lists.newArrayList(productCategory, productCategory1);
        int res = productCategoryDao.batchInsertProductCategory(list);
        Assert.assertEquals(2, res);
    }


    @Test
    public void testBselectShopCategory() {
        List<ProductCategory> list = productCategoryDao.selectByShopId(1L);
        Assert.assertEquals(2, list.size());
    }


    @Test
    public void testCdeleteProductCategoryId() {
        List<ProductCategory> list = productCategoryDao.selectByShopId(1L);
        list = list.stream().filter(a -> a.getProductCategoryName().equals("摩托") || a.getProductCategoryName().equals("卡车")).collect(Collectors.toList());
        int effectedNum = 0;
        for (ProductCategory p : list){
           effectedNum += productCategoryDao.deleteProductCategory(p.getProductCategoryId(), p.getShopId());
        }

        Assert.assertEquals(2, effectedNum);

    }
}
