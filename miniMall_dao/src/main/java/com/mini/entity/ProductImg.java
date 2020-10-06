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
public class ProductImg {

	private Long productImgId;

	private String imgAddr;

	private String imgDesc;

	private Integer priority;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;

	private Long productId;

}
