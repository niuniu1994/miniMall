package com.mini.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.BaseDaoTest;
import com.mini.entity.Product;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 17:29
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseDaoTest {
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
        Product product = productDao.queryProductById(6);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(product);
        Assert.assertEquals("笑眯眯",product.getProductName());
    }

    @Test
    public void testCUpdateProduct(){
        Shop shop = new Shop();
        shop.setShopId(28L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1L);

        Product product = new Product();
        product.setProductId(50L);
        product.setCreateTime(new Date());
        product.setEnableStatus(1);
        product.setProductName("test2");
        product.setProductDesc("test2Desc");
        product.setImgAddr("test2");
        product.setPriority(2);
        product.setLastEditTime(new Date());
        product.setShop(shop);
        product.setProductCategory(productCategory);
        int effectedNum = productDao.updateProduct(product);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testDUpdateNullProduct(){
        Product product = new Product();

       int num =  productDao.updateProductCategoryToNull(1L);
        Assert.assertEquals(2,num);
    }

    @Test
    public void testESelectProduct(){

        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(15L);
        Shop shop = new Shop();
        shop.setShopId(20L);
        Product product = new Product();
        product.setShop(shop);
        product.setProductCategory(productCategory);

        List<Product> list = productDao.queryProductList(product);
        Assert.assertEquals(0,list.size());
    }


}
