package com.mini.dao;

import com.mini.entity.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-25 22:41
 **/
@Repository("shopDao")
public interface ShopDao {
    int insert(Shop shop);

    int update(Shop shop);

    void delete(Shop shop);

    Shop select(Long shopId);
    /**
    * @Parame: [shopCondition, rowIndex, pageSize]
    * @description: 
    * @return: java.util.List<com.mini.entity.Shop>
    * @author: xin kaining
    * @Date 2020/10/2
    **/
    List<Shop> selectList(@Param("shopCondition") Shop shopCondition);
}
