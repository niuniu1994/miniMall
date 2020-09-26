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
public class ProductCategory {

	private Long productCategoryId;

	private Long shopId;

	private String productCategoryName;

	private Integer priority;

	private Date createTime;
}
