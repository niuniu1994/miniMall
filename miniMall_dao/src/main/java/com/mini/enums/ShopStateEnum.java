package com.mini.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-26 15:21
 **/

@Getter
public enum ShopStateEnum {
    CHECK(0, "reviewing"), OFFLINE(-1, "Illgel shop"), SUCCESS(1, "Success operation"), PASS(2, "Review passed"),
    NULL_SHOPID(-1002,"ShopId is null")
    ;

    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state){
        return Arrays.stream(values()).filter(a -> a.getState() == state).findFirst().orElse(null);
    }
}
