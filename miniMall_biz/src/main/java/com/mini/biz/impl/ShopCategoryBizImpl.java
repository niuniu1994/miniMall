package com.mini.biz.impl;

import com.mini.biz.ShopCategoryBiz;
import com.mini.dao.ShopCategoryDao;
import com.mini.entity.ShopCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-29 11:47
 **/
@Service("shopCategoryBiz")
public class ShopCategoryBizImpl implements ShopCategoryBiz {

    @Resource
    ShopCategoryDao shopCategoryDao;


    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.select(shopCategoryCondition);
    }
}
