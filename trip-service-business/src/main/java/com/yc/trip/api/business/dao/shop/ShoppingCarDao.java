package com.yc.trip.api.business.dao.shop;

import java.util.List;

import com.yc.trip.api.business.dto.shop.ShoppingCar;
import com.yc.trip.api.business.query.shop.ShoppingCarQuery;

/**
 * 购物车信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:44
 *
 */
public interface ShoppingCarDao {

    /**
     * 购物车信息新增
     */
    void add(ShoppingCar shoppingCar);

    /**
     * 购物车信息修改
     */
    void update(ShoppingCar shoppingCar);
    
    /**
     * 购物车信息查询
     */
    ShoppingCar get(ShoppingCarQuery shoppingCarQuery);

    /**
     * 购物车信息列表查询
     */
    List<ShoppingCar> queryList(ShoppingCarQuery shoppingCarQuery);

}