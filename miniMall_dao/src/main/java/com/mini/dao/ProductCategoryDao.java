package com.mini.dao;

import com.mini.dto.ProductCategoryExecution;
import com.mini.entity.ProductCategory;
import com.mini.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 10:56
 **/
@Repository("productCategoryDao")
public interface ProductCategoryDao {

    List<ProductCategory> selectByShopId(Long shopId);

    int batchInsertProductCategory(List<ProductCategory> list);

    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,@Param("shopId") long shopId);
}
