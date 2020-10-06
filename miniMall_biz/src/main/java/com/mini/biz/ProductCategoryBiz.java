package com.mini.biz;

import com.mini.dto.ProductCategoryExecution;
import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.entity.ShopCategory;
import com.mini.exception.ProductCategoryOperationException;

import java.util.List;
import java.util.Properties;

public interface ProductCategoryBiz {
    List<ProductCategory> getProductCategory(long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;

    /**
    * @Parame: [shopCategory]
    * @description: remove product category and reset product's Id to null who was in this category
    * @return: com.mini.dto.ProductCategoryExecution
    * @author: xin kaining
    * @Date 2020/10/3
    **/
    ProductCategoryExecution removeProductCategory(long productCategoryId, long shopId);
}
