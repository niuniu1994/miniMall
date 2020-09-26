package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;



/**
 * @author kainingxin
 */
@Getter
@Setter
@NoArgsConstructor
public class Product {

	private Long productId;

	private String productName;

	private String productDesc;

	private String imgAddr;

	private String normalPrice;

	private String promotionPrice;

	private Integer priority;

	private Integer point;

	private Date createTime;

	private Date lastEditTime;
	// 0.unuable 1.usable
	private Integer enableStatus;

	// one to many association with productImg
	private List<ProductImg> productImgList;

	//one to one association with product category
	private ProductCategory productCategory;

	//one to one association with sho
	private Shop shop;

}
