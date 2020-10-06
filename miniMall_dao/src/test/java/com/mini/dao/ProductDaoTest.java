package com.mini.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.BaseTest;
import com.mini.entity.Product;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 17:29
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {
    @Resource
    private ProductDao productDao;


    @Test
    public void testAProductInsert(){
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);

        Product product = new Product();
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setProductName("test1");
        product.setProductDesc("test1Desc");
        product.setImgAddr("test1");
        product.setPriority(1);
        product.setLastEditTime(new Date());
        product.setShop(shop);
        product.setProductCategory(productCategory);
        int effectedNum = productDao.insertProduct(product);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryProduct() throws JsonProcessingException {
        Product product = productDao.queryProductById(47);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(product));

    }
}
