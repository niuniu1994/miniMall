package com.mini.exception;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 19:21
 **/
public class LocalAuthOperationException extends RuntimeException{
    public LocalAuthOperationException(String message) {
        super(message);
    }
}
