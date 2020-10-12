package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
@ToString
public class LocalAuth {

	private Long localAuthId;

	private String username;

	private String password;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date lastEditTime;

	//assocation with PersonInfo
	private PersonInfo personInfo;
}
