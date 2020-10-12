package com.mini.biz;

import com.mini.entity.HeadLine;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 09:27
 **/
public interface HeadLineBiz {
    List<HeadLine> getHeadLineList(HeadLine headLine);
}
