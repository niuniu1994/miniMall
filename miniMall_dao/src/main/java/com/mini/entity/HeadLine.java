package com.mini.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* @Parame:
* @description: HeadLine
* @return:
* @author: xin kaining
* @Date 2020/9/24
**/

@Getter
@Setter
@NoArgsConstructor
public class HeadLine {

	private Long lineId;

	private String lineName;

	private String lineLink;

	private String lineImg;
	// 0 > 1
	private Integer priority;
	// 0. usable 1.unusable
	private Integer enableStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date lastEditTime;

}
