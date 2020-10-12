package com.mini.biz.impl;

import com.mini.biz.ProductCategoryBiz;
import com.mini.dao.ProductCategoryDao;
import com.mini.dao.ProductDao;
import com.mini.dto.ProductCategoryExecution;
import com.mini.entity.ProductCategory;
import com.mini.enums.ProductCategoryStateEnum;
import com.mini.exception.ProductCategoryOperationException;
import com.mini.exception.ProductOperationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 11:09
 **/

@Service("productCategoryBiz")
public class ProductCategoryBizImpl implements ProductCategoryBiz {

    @Resource
    ProductCategoryDao productCategoryDao;

    @Resource
    ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategory(long shopId) {
        return productCategoryDao.selectByShopId(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
        if (productCategoryList != null && productCategoryList.size() > 0) {
            try {
                productCategoryList.forEach(a -> a.setCreateTime(new Date()));
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("店铺类别创建失败");
                } else {
                    return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
                }

            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
            }
        } else {
            return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
        }
    }

    @Override
    public ProductCategoryExecution removeProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
        try {
            int effectedNum1 = productDao.updateProductCategoryToNull(productCategoryId);
        } catch (Exception e) {
            throw new ProductOperationException("deleteProductCategory error" + e.getMessage());
        }

        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategory error" + e.getMessage());
        }
    }
}
