package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author kainingxin
 */
@Getter
@Setter
@NoArgsConstructor
public class WechatAuth {

	private Long wechatAuthId;

	private String openId;

	private Date createTime;

	private PersonInfo personInfo;
}
