package com.mini.biz;

import com.mini.dto.ImageHolder;
import com.mini.dto.ProductExecution;
import com.mini.entity.Product;
import com.mini.exception.ProductOperationException;

import java.io.File;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 17:55
 **/
public interface ProductBiz {
    /**
     * 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
     *
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 通过商品Id查询唯一的商品信息
     *
     * @param productId
     * @return
     */
    Product getProductById(long productId);

    /**
     * 添加商品信息以及图片处理
     *
     * @param product
     * @param thumbnail
     * @param productImgList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution addProduct(Product product, File thumbnail, List<File> productImgList)
            throws ProductOperationException;

    /**
     * 修改商品信息以及图片处理
     *
     * @param product
     * @param thumbnail
     * @param productImgHolderList
     * @return
     * @throws ProductOperationException
     */
    ProductExecution modifyProduct(Product product, File thumbnail, List<ImageHolder> productImgHolderList)
            throws ProductOperationException;
}
