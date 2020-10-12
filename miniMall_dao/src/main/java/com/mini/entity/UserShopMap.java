package com.mini.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-12 20:19
 **/
@Setter
@Getter
public class UserShopMap {
    // 主键Id
    private Long userShopId;
    // 创建时间
    private Date createTime;
    // 顾客在该店铺的积分
    private Integer point;
    // 顾客信息实体类
    private PersonInfo user;
    // 店铺信息实体类
    private Shop shop;
}
