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
@Getter
@Setter
public class UserProductMao {
    // 主键Id
    private Long userProductId;
    // 创建时间
    private Date createTime;
    // 消费商品所获得的积分
    private Integer point;
    // 顾客信息实体类
    private PersonInfo user;
    // 商品信息实体类
    private Product product;
    // 店铺信息实体类
    private Shop shop;
    // 操作员信息实体类
    private PersonInfo operator;
}
