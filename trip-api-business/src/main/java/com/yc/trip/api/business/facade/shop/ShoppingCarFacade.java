package com.yc.trip.api.business.facade.shop;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.shop.ShoppingCar;

/**
 * 购物车信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:19
 */
public interface ShoppingCarFacade {

    /**
     * 新增购物车信息
     * @throws PendingException 
     */
    ShoppingCar addShoppingCar(ShoppingCar shoppingCar) throws PendingException;

    /**
     * 修改购物车信息
     * @throws PendingException 
     */
    ShoppingCar updateShoppingCar(ShoppingCar shoppingCar) throws PendingException;
    
    /**
     * 查询购物车信息
     * @throws PendingException 
     */
    ShoppingCar getShoppingCar(ShoppingCar shoppingCar) throws PendingException;
    
    /**
     * 查询购物车信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ShoppingCar mustGet(ShoppingCar shoppingCar) throws PendingException;

    /**
     * 查询购物车信息列表
     * @throws PendingException 
     */
    List<ShoppingCar> queryShoppingCarList(ShoppingCar shoppingCar) throws PendingException;

    /**
     * 查询购物车信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ShoppingCar> queryShoppingCarPage(ShoppingCar shoppingCar) throws PendingException;

}