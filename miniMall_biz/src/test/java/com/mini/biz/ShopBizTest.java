package com.mini.biz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.BaseTest;
import com.mini.dto.ShopExecution;
import com.mini.entity.Area;
import com.mini.entity.PersonInfo;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import com.mini.enums.ShopStateEnum;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-27 12:47
 **/
public class ShopBizTest extends BaseTest {
    @Resource
    ShopBiz shopBiz;

    @Test
    public void addShopTest() {
        Shop shop = new Shop();
        Area area = new Area();
        PersonInfo user = new PersonInfo();
        ShopCategory shopCategory = new ShopCategory();

        File file = new File("/Users/kainingxin/Desktop/WechatIMG25.jpeg");
        area.setAreaId(2);
        user.setUserId(1L);
        shopCategory.setShopCategoryId(12L);
        shop.setShopName("测试1");
        shop.setShopDesc("test1");
        shop.setShopAddr("hell");
        shop.setPhone("0000dw0");
        shop.setPriority(1);
        shop.setAdvice("test");

        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setOwner(user);

        ShopExecution shopExecution = shopBiz.addShop(file, shop);
        Assert.assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }

    @Test
    public void updateShopTest() {
        Shop shop = shopBiz.getShop(1);
        shop.setShopName("测试11212");
        shop.setShopDesc("test123123");
        shop.setShopAddr("hedawd");
        shop.setPhone("0adwadw0");
        shop.setPriority(2);
        shop.setAdvice("test");
        File file = new File("/Users/kainingxin/Desktop/12.jpg");

        ShopExecution shopExecution = shopBiz.modifyShop(file, shop);
        Assert.assertEquals(ShopStateEnum.SUCCESS.getState(), shopExecution.getState());
    }

    @Test
    public void selectShop() throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        Shop shop = shopBiz.getShop(1L);
        System.out.println(o.writeValueAsString(shop));
    }


    @Test
    public void selectShopList() throws JsonProcessingException {
        ObjectMapper o = new ObjectMapper();
        Shop s = new Shop();
        s.setShopId(1L);

        ShopExecution shop = shopBiz.getShopList(s);
        System.out.println(o.writeValueAsString(shop));
    }
}
