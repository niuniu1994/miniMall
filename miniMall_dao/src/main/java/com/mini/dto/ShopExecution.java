package com.mini.dto;

import com.mini.entity.Shop;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-26 15:31
 **/
public class ShopExecution {
    //state index
    private int state;

    // state value
    private String stateInfo;

    //amount of shop
    private int count;

    //for insert , update or select
    private Shop shop;

    //for selectAll
    private List<Shop> shopList;

}
