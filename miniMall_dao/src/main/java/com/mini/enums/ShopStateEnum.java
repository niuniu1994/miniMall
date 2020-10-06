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
    CHECK(0, "reviewing"), OFFLINE(-1, "Illegel shop"), SUCCESS(1, "Success operation"), PASS(2, "Review passed"),
    NULL_SHOPID(-1002,"ShopId  null"),NULL_SHOP(-1003,"shop info null"),INNER_ERROR(-1001,"内部系统错误")
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
