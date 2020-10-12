package com.mini.exception;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-09 16:03
 **/
public class AreaOperationException extends RuntimeException{
    public AreaOperationException(String message) {
        super(message);
    }
}
