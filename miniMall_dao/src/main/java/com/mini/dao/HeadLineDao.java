package com.mini.dao;

import com.mini.entity.HeadLine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 09:02
 **/
@Repository("headLineDao")
public interface HeadLineDao {
    List<HeadLine> selectHeadList(HeadLine headLine);
}
