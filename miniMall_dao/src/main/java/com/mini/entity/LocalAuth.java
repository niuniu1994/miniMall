package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
/**
* @Parame:
* @description: Local author
* @return:
* @author: xin kaining
* @Date 2020/9/24
**/

@Getter
@Setter
@NoArgsConstructor
public class LocalAuth {

	private Long localAuthId;

	private String username;

	private String password;

	private Date createTime;

	private Date lastEditTime;

	//assocation with PersonInfo
	private PersonInfo personInfo;
}
