package com.mini.biz;

import com.google.common.collect.Lists;
import com.mini.BaseTest;
import com.mini.dto.ProductExecution;
import com.mini.entity.Product;
import com.mini.entity.ProductCategory;
import com.mini.entity.ProductImg;
import com.mini.entity.Shop;
import com.mini.enums.ProductStateEnum;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 21:48
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductBizTest extends BaseTest {

    @Resource
    private ProductBiz productBiz;

    @Test
    public void testAAddProduct(){
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


        File file = new File("/Users/kainingxin/Desktop/WechatIMG25.jpeg");
        File file1 = new File("/Users/kainingxin/Desktop/WechatIMG25.jpeg");

        List<File> list = Lists.newArrayList(file,file1);

        ProductExecution productExecution = productBiz.addProduct(product,file,list);
        Assert.assertEquals(productExecution.getState(),ProductStateEnum.SUCCESS.getState());

    }
}
