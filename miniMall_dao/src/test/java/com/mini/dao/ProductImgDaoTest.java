package com.mini.dao;

import com.google.common.collect.Lists;
import com.mini.BaseDaoTest;
import com.mini.entity.ProductImg;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 17:30
 **/

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseDaoTest {

    @Resource
    private ProductImgDao productImgDao;

    @Test
    public void TestABatchInsertProductImg(){
        ProductImg productImg = new ProductImg();
        productImg.setCreateTime(new Date());
        productImg.setImgAddr("test1");
        productImg.setImgDesc("testDesc");
        productImg.setPriority(1);
        productImg.setProductId(1L);
        productImg.setProductImgId(1L);

        ProductImg productImg1 = new ProductImg();
        productImg1.setCreateTime(new Date());
        productImg1.setImgAddr("test1");
        productImg1.setImgDesc("testDesc");
        productImg1.setPriority(1);
        productImg1.setProductId(1L);
        productImg1.setProductImgId(1L);

        List<ProductImg> list = Lists.newArrayList(productImg, productImg1);
        int effectedNum = productImgDao.batchInsertProductImg(list);
        Assert.assertEquals(2,effectedNum);


    }
}
