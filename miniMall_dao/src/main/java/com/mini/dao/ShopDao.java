package com.mini.dao;

import com.mini.entity.Shop;
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
    void insert(Shop shop);

    void update(Shop shop);

    void delete(Shop shop);

    Shop select(Long shopId);

    List<Shop> selectAll();
}
