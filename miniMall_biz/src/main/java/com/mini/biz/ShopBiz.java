package com.mini.biz;

import com.mini.dto.ShopExecution;
import com.mini.entity.Shop;
import com.mini.exception.ShopOperationException;

import java.io.File;
import java.util.List;

public interface ShopBiz {

    ShopExecution addShop(File img, Shop shop) throws ShopOperationException;

    Shop getShop(long shopId);

    ShopExecution modifyShop (File img,Shop shop) throws ShopOperationException;

    ShopExecution getShopList(Shop shopCondition);

    void deleteShop(long shopId);

}
