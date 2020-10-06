package com.mini.dao;

import com.mini.entity.ProductImg;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 15:07
 **/
@Repository("productImgDao")
public interface ProductImgDao {
    /**
     * 列出某个商品的详情图列表
     *
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加商品详情图片
     *
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(@Param("productImgList") List<ProductImg> productImgList);

    /**
     * 删除指定商品下的所有详情图
     *
     * @param productId
     * @return
     */
    int deleteProductImgByProductId(long productId);
}
