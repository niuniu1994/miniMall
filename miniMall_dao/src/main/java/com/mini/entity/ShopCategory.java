package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

	private Date createTime;

	private Date lastEditTime;

	private ShopCategory parent;
}
