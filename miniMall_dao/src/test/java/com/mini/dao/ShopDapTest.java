package com.mini.dao;

import com.mini.BaseTest;
import com.mini.entity.Area;
import com.mini.entity.PersonInfo;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-25 23:19
 **/
public class ShopDapTest extends BaseTest {
    @Resource
    ShopDao shopDao;

    @Test
    public void insertShopTest(){
        Shop shop = new Shop();
        Area area = new Area();
        PersonInfo user = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();
        area.setAreaId(2);
        user.setUserId(1L);
        shopCategory.setShopCategoryId(12L);
        shop.setShopName("测试");
        shop.setShopDesc("test");
        shop.setShopAddr("heaven");
        shop.setPhone("0000000");
        shop.setShopImg("user/dwad");
        shop.setPriority(1);
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setAdvice("test");
        shop.setEnableStatus(1);

        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setOwner(user);

        shopDao.insert(shop);
    }

    @Test
    public void updateShopTest(){
        Shop shop = new Shop();


        ShopCategory shopCategory = new ShopCategory();

        shopCategory.setShopCategoryId(12L);
        shop.setShopName("测试update");
        shop.setShopDesc("testupdate");

        shop.setLastEditTime(new Date());
        shop.setAdvice("test");
        shop.setEnableStatus(1);
        shop.setShopId(42L);

        shop.setShopCategory(shopCategory);


        shopDao.update(shop);
    }
}
