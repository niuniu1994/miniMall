package com.mini.dto;

import com.mini.entity.Shop;
import com.mini.enums.ShopStateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-26 15:31
 **/

@Getter
@Setter
@NoArgsConstructor
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

    //constructor in case of a failure manipulation of shop object
    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    //constructor in case of a success manipulation of shop object (select,insert,update...)
    public ShopExecution(ShopStateEnum stateEnum, Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    //constructor in case of a success manipulation of shop object (selectAll)
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> list){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        shopList = list;
    }


}
