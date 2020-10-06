package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author kainingxin
 */
@Getter
@Setter
@NoArgsConstructor

public class Shop {

	private Long shopId;

	private String shopName;

	private String shopDesc;

	private String shopAddr;

	private String phone;

	private String shopImg;

	private Integer priority;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date lastEditTime;

	private Integer enableStatus;

	private String advice;

	//one to one association with area
	private Area area;

	//one to one association with owner
	private PersonInfo owner;

	// one to one association with shopCategory
	private ShopCategory shopCategory;
}
