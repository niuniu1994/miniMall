package com.mini.dao;

import com.mini.entity.Area;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-24 21:00
 **/
@Repository("areaDao")
public interface AreaDao {
    void insert(Area area);

    void update(Area area);

    void delete(Integer areaId);

    Area select(Integer areaId);

    List<Area> selectAll();
}
