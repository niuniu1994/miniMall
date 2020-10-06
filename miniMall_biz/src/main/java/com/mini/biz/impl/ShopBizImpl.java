package com.mini.biz.impl;

import com.mini.biz.ShopBiz;
import com.mini.dao.ShopDao;
import com.mini.dto.ShopExecution;
import com.mini.entity.Shop;
import com.mini.enums.ShopStateEnum;
import com.mini.exception.ShopOperationException;
import com.mini.util.ImageUtil;
import com.mini.util.PathUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-27 11:29
 **/

@Service("shopBiz")
public class ShopBizImpl implements ShopBiz {

    @Resource
    ShopDao shopDao;

    @Override
    public ShopExecution addShop(File img, Shop shop) throws ShopOperationException {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            //initialize shop
            shop.setCreateTime(new Date());
            shop.setEnableStatus(0);
            shop.setLastEditTime(new Date());

            //insert shop
            int shopId = shopDao.insert(shop);

            if (shopId <= 0) {
                throw new ShopOperationException("Fail to insert shop");
            } else {
                //save image
                if (img != null) {
                    try {
                        addImage(shop, img);
                    } catch (Exception e) {
                        throw new ShopOperationException("Add Image error : " + e.toString());
                    }
                }
                //update shop's img path
                int effectedNum = shopDao.update(shop);
                if (effectedNum <= 0) {
                    throw new ShopOperationException("Fail to update img");
                }
            }

        } catch (Exception e) {
            throw new ShopOperationException("addShop error" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

//    get 的方法一般不加异常处理 对数据库进行结果修改的需要特殊处理
    @Override
    public Shop getShop(long shopId) {
        return shopDao.select(shopId);
    }

    @Override
    public ShopExecution modifyShop(File img, Shop shop) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            //update img
            if (img != null && img.exists()){
                Shop tmp = shopDao.select(shop.getShopId());
                if (tmp.getShopImg() != null){
                    ImageUtil.deleteFileOrPath(tmp.getShopImg());
                }
                addImage(shop, img);
            }

            shop.setLastEditTime(new Date());
            //update shop
            int effectedNum = shopDao.update(shop);

            if (effectedNum <= 0) {
                throw new ShopOperationException("Fail to update shop");
            } else {
                //update shop's img path
                shop = shopDao.select(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw new ShopOperationException("updateShop error" + e.getMessage());
        }

    }

    //此处需要考虑未来实现模糊查询功能所以传入一一个 shop类

    @Override
    public ShopExecution getShopList(Shop shopCondition) {

            ShopExecution shopExecution = new ShopExecution();

            if (shopCondition == null){
                shopExecution.setState(ShopStateEnum.NULL_SHOP.getState());
                return shopExecution;
            }

            List<Shop> shopList = shopDao.selectList(shopCondition);
            if (shopList != null){
                shopExecution.setShopList(shopList);
                shopExecution.setState(ShopStateEnum.SUCCESS.getState());
                shopExecution.setCount(shopList.size());
            }else {
                shopExecution.setState(ShopStateEnum.INNER_ERROR.getState());
            }
            return shopExecution;
    }

    @Override
    public void deleteShop(long shopId) {

    }


    private void addImage(Shop shop, File file) {
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(file, dest);
        shop.setShopImg(shopImgAddr);
    }
}
