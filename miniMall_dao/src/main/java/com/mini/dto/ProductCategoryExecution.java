package com.mini.dto;

import com.mini.entity.ProductCategory;
import com.mini.entity.Shop;
import com.mini.enums.ProductCategoryStateEnum;
import com.mini.enums.ShopStateEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-10-03 11:05
 **/

@Getter
@Setter
@NoArgsConstructor
public class ProductCategoryExecution {

    //state index
    private int state;

    // state value
    private String stateInfo;

    //amount of shop
    private int count;

    //for insert , update or select
    private ProductCategory productCategory;

    //for selectAll
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public ProductCategoryExecution(ProductCategoryStateEnum  stateEnum, ProductCategory productCategory){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategory = productCategory;
    }

    //constructor in case of a success manipulation of shop object (selectAll)
    public ProductCategoryExecution(ProductCategoryStateEnum  stateEnum, List<ProductCategory> list){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        productCategoryList = list;
    }
}
