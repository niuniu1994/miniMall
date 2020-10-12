package com.mini.exception;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 19:11
 **/
public class ShopCategoryOperationException extends RuntimeException {
    public ShopCategoryOperationException(String message) {
        super(message);
    }
}
