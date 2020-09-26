package com.mini.biz.impl;

import com.mini.biz.AreaBiz;
import com.mini.dao.AreaDao;
import com.mini.entity.Area;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-25 09:07
 **/


@Service("areaBiz")
public class AreaBizImpl implements AreaBiz {

    @Resource
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.selectAll();
    }
}
