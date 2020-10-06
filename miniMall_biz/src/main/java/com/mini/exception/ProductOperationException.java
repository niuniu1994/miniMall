package com.mini.exception;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-04 18:00
 **/
public class ProductOperationException extends RuntimeException {
    public ProductOperationException(String message) {
        super(message);
    }
}
