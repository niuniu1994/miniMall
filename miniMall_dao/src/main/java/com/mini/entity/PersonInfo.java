package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 20:26
 **/
@Getter
@Setter
@NoArgsConstructor
public class PersonInfo {
    private Long userId;

    private String name;

    private String profileImg;

    private String email;

    private String gender;

    private Integer enableStatus;
    //1.customer 2.owner 3.admin
    private Integer userType;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
    private Date lastEditTime;

}
