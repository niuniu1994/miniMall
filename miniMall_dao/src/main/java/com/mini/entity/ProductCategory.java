package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;
}
