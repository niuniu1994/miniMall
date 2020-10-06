package com.mini.dao;

import com.mini.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopCategoryDao {

    List<ShopCategory> select(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
