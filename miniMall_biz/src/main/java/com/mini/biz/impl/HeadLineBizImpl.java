package com.mini.biz.impl;

import com.mini.biz.HeadLineBiz;
import com.mini.dao.HeadLineDao;
import com.mini.entity.HeadLine;
import com.mini.exception.HeadLineOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-07 09:29
 **/
@Service("headLineBiz")
public class HeadLineBizImpl implements HeadLineBiz {

    @Resource
    private HeadLineDao headLineDao;

    @Resource(description = "jedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static String HEADLINEKEYS = "headlinelist";

    Logger logger = LoggerFactory.getLogger(HeadLineBizImpl.class);

    @Override
    public List<HeadLine> getHeadLineList(HeadLine headLine) {
        String key = HEADLINEKEYS;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List<HeadLine> headLineList = null;
        if (headLine != null && headLine.getEnableStatus() != null){
            key += ("_" + headLine.getEnableStatus());
        }

        if (!redisTemplate.hasKey(key)){
            headLineList = headLineDao.selectHeadList(headLine);
            try {
                valueOperations.set(key,headLineList);
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }else {
            try {
                headLineList = (List<HeadLine>) valueOperations.get(key);
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new HeadLineOperationException(e.getMessage());
            }
        }

        return headLineList;
        
    }
}
