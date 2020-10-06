package com.mini.exception;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 13:48
 **/
public class ProductCategoryOperationException extends RuntimeException{
    public ProductCategoryOperationException(String message) {
        super(message);
    }
}
