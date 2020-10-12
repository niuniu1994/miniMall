package com.mini.biz.impl;

import com.mini.biz.AreaBiz;
import com.mini.dao.AreaDao;
import com.mini.entity.Area;
import com.mini.exception.AreaOperationException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    @Resource(description = "jedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static String AREALISTKEY = "arealist";

    @Override
    public List<Area> getAreaList() throws AreaOperationException{

        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<Area> areaList = null;

        if (!redisTemplate.hasKey(AREALISTKEY)){
            areaList = areaDao.selectAll();
            try {
                valueOperations.set(AREALISTKEY,areaList);
            }catch (Exception e){
                throw new AreaOperationException(e.getMessage());
            }
        }else {
            try {
                areaList = (List<Area>) valueOperations.get(AREALISTKEY);
            }catch (Exception e){
                throw new AreaOperationException(e.getMessage());
            }
        }

        return areaList;


    }
}
