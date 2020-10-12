package com.mini.biz.impl;

import com.mini.biz.ShopCategoryBiz;
import com.mini.dao.ShopCategoryDao;
import com.mini.entity.ShopCategory;
import com.mini.exception.ShopCategoryOperationException;
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
 * @create: 2020-09-29 11:47
 **/
@Service("shopCategoryBiz")
public class ShopCategoryBizImpl implements ShopCategoryBiz {

    @Resource
    ShopCategoryDao shopCategoryDao;

    @Resource(description = "jedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    private static String SHOPCATEGORYKEYS = "shopcategorylist";

    Logger logger = LoggerFactory.getLogger(ShopCategoryBizImpl.class);

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        String key = SHOPCATEGORYKEYS;

        List<ShopCategory> shopCategoryList = null;

        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();


        if (shopCategoryCondition == null){
            //得到所有父类
            key += ("_allfirstlevel");
        }else if (shopCategoryCondition.getParent() != null && shopCategoryCondition.getParent().getShopCategoryId() != null){
            //得到所有父类的子类
            key += ("_parent" + shopCategoryCondition.getParent().getShopCategoryId());
        }else {
            //所有子类
            key += ("_allsecondlevel");
        }

        if (!redisTemplate.hasKey(key)){
            shopCategoryList = shopCategoryDao.select(shopCategoryCondition);
            try {
                valueOperations.set(key,shopCategoryList);
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }else {
            try {
                shopCategoryList = (List<ShopCategory>) valueOperations.get(key);
            }catch (Exception e){
                logger.error(e.getMessage());
                throw new ShopCategoryOperationException(e.getMessage());
            }
        }
        return shopCategoryList;
    }
}

