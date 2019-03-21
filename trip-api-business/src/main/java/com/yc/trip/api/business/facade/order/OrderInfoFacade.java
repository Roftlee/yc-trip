package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderInfo;

/**
 * 订单信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:48
 */
public interface OrderInfoFacade {

    /**
     * 新增订单信息
     * @throws PendingException 
     */
    OrderInfo addOrderInfo(OrderInfo orderInfo) throws PendingException;

    /**
     * 修改订单信息
     * @throws PendingException 
     */
    OrderInfo updateOrderInfo(OrderInfo orderInfo) throws PendingException;
    
    /**
     * 查询订单信息
     * @throws PendingException 
     */
    OrderInfo getOrderInfo(OrderInfo orderInfo) throws PendingException;
    
    /**
     * 查询订单信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderInfo mustGet(OrderInfo orderInfo) throws PendingException;

    /**
     * 查询订单信息列表
     * @throws PendingException 
     */
    List<OrderInfo> queryOrderInfoList(OrderInfo orderInfo) throws PendingException;

    /**
     * 查询订单信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderInfo> queryOrderInfoPage(OrderInfo orderInfo) throws PendingException;

}