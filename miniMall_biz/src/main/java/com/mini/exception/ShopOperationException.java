package com.mini.exception;

import java.io.Serializable;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-27 12:13
 **/
public class ShopOperationException extends RuntimeException {

    public ShopOperationException(String message) {
        super(message);
    }
}
