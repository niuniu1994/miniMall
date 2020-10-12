package com.mini.biz;

import com.mini.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryBiz {
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);


}
