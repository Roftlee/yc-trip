package com.yc.trip.api.business.dao.shop;

import java.util.List;

import com.yc.trip.api.business.bo.shop.ShoppingCarDomain;

/**
 * 购物车信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:19
 *
 */
public interface ShoppingCarDao {

    /**
     * 新增购物车信息
     */
    void addShoppingCar(ShoppingCarDomain shoppingCarDomain);

    /**
     * 修改购物车信息
     */
    void updateShoppingCar(ShoppingCarDomain shoppingCarDomain);
    
    /**
     * 查询购物车信息
     */
    ShoppingCarDomain getShoppingCar(ShoppingCarDomain shoppingCarDomain);

    /**
     * 查询购物车信息列表
     */
    List<ShoppingCarDomain> queryShoppingCarList(ShoppingCarDomain shoppingCarDomain);

}