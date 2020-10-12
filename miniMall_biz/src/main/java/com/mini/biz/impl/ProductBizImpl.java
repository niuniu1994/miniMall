package com.mini.biz.impl;

import com.mini.biz.ProductBiz;
import com.mini.dao.ProductDao;
import com.mini.dao.ProductImgDao;
import com.mini.dto.ProductExecution;
import com.mini.entity.Product;
import com.mini.entity.ProductImg;
import com.mini.enums.ProductStateEnum;
import com.mini.exception.ProductOperationException;
import com.mini.util.ImageUtil;
import com.mini.util.PathUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 18:17
 **/
@Service("productBiz")
public class ProductBizImpl implements ProductBiz {

    @Resource
    private ProductDao productDao;

    @Resource
    private ProductImgDao productImgDao;

    @Override
    public List<Product> getProductList(Product product) {
        return productDao.queryProductList(product);
    }

    @Override
    public Product getProductById(long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution addProduct(Product product, File thumbnail, List<File> productImgList) throws ProductOperationException {
        if (product == null || product.getShop() == null || product.getShop().getShopId() <= 0) {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

        try {
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);

            if (thumbnail != null) {
                try {
                    addImage(product, thumbnail);
                } catch (Exception e) {
                    throw new ProductOperationException("Add image error:" + e.getMessage());
                }
                try {
                    int effectedNum = productDao.insertProduct(product);
                    if (effectedNum <= 0) {
                        throw new ProductOperationException("Fail to insert product");
                    }
                }catch (Exception e){
                    throw new ProductOperationException("addProduct error : " + e.getMessage());
                }

                if (productImgList != null && productImgList.size()>0){
                    addProductImgList(productImgList,product);
                }

            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        } catch (Exception e) {
            throw new ProductOperationException("Add product error : " + e.getMessage());
        }

    }

    @Override
    public ProductExecution modifyProduct(Product product, File thumbnail, List<File> productImgList) throws ProductOperationException {
        if (product == null || product.getProductId() <= 0 || product.getShop().getShopId() <=0){
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

        product.setLastEditTime(new Date());
        try {
            if (thumbnail != null){
                Product tmp = productDao.queryProductById(product.getProductId());
                if (tmp.getImgAddr() != null){
                    ImageUtil.deleteFileOrPath(tmp.getImgAddr());
                }
                addImage(product,thumbnail);
            }

            if (productImgList != null && !productImgList.isEmpty()){
                List<ProductImg> list = productImgDao.queryProductImgList(product.getProductId());
                if (!list.isEmpty()){
                    deleteProductImgList(product.getProductId());
                }
                addProductImgList(productImgList,product);
            }

            int effectedNum = productDao.updateProduct(product);
            if (effectedNum <= 0){
                throw new ProductOperationException("Fail to update product");
            }else {
                product = productDao.queryProductById(product.getProductId());
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            }
        }catch (Exception e){
            throw new ProductOperationException("Update product error : " + e.getMessage());
        }

    }

    private void addImage(Product product, File file) {
        String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
        String productImgAddr = ImageUtil.generateThumbnail(file, dest);
        product.setImgAddr(productImgAddr);
    }

    private void addProductImgList(List<File> list, Product product) throws ProductOperationException{
        String dest = PathUtil.getShopImgPath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();

        try {
            //store photo
            list.forEach(a->{
                String imgAddr = ImageUtil.generateNormalImg(a, dest);
                ProductImg productImg = new ProductImg();
                productImg.setProductId(product.getProductId());
                productImg.setCreateTime(new Date());
                productImg.setImgAddr(imgAddr);
                productImgList.add(productImg);
            });

            //store productImg info in database
            if (productImgList.size() > 0) {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0){
                    throw new ProductOperationException("Fail to add productImg");
                }
            }

        }catch (Exception e){
            throw new ProductOperationException("Add productImg error : " + e.getMessage());
        }
    }

    /**
     * 删除某个商品下的所有详情图
     *
     * @param productId
     */
    private void deleteProductImgList(long productId) {
        // 根据productId获取原来的图片
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        // 干掉原来的图片
        productImgList.forEach(a -> ImageUtil.deleteFileOrPath(a.getImgAddr()));
        // 删除数据库里原有图片的信息
        productImgDao.deleteProductImgByProductId(productId);
    }
}
