package com.yc.trip.api.business.facade.shop;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.shop.ShoppingCar;
import com.yc.trip.api.business.query.shop.ShoppingCarQuery;

/**
 * 购物车信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:44
 */
public interface ShoppingCarFacade {

    /**
     * 新增购物车信息
     * @throws PendingException 
     */
    ShoppingCar add(ShoppingCar shoppingCar) throws PendingException;

    /**
     * 修改购物车信息
     * @throws PendingException 
     */
    void update(ShoppingCar shoppingCar) throws PendingException;
    
    /**
     * 查询购物车信息
     * @throws PendingException 
     */
    ShoppingCar get(ShoppingCarQuery shoppingCarQuery) throws PendingException;
    
    /**
     * 查询购物车信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ShoppingCar mustGet(ShoppingCarQuery shoppingCarQuery) throws PendingException;

    /**
     * 查询购物车信息列表
     * @throws PendingException 
     */
    List<ShoppingCar> queryList(ShoppingCarQuery shoppingCarQuery) throws PendingException;

    /**
     * 查询购物车信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ShoppingCar> queryPage(ShoppingCarQuery shoppingCarQuery) throws PendingException;

}