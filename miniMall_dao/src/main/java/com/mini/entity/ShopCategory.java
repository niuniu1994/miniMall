package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author kainingxin
 */
@Setter
@Getter
@NoArgsConstructor
public class ShopCategory {

	private Long shopCategoryId;

	private String shopCategoryName;

	private String shopCategoryDesc;

	private String shopCategoryImg;

	private Integer priority;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date lastEditTime;

	private ShopCategory parent;
}
