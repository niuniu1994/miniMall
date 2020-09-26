package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 20:12
 **/

@Getter
@Setter
@NoArgsConstructor
public class Area {

    private Integer areaId;

    private String areaName;

    //0 > 1  
    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

}
