package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderInfo;
import com.yc.trip.api.business.query.order.OrderInfoQuery;

/**
 * 订单信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:15
 */
public interface OrderInfoFacade {

    /**
     * 新增订单信息
     * @throws PendingException 
     */
    OrderInfo add(OrderInfo orderInfo) throws PendingException;

    /**
     * 修改订单信息
     * @throws PendingException 
     */
    void update(OrderInfo orderInfo) throws PendingException;
    
    /**
     * 查询订单信息
     * @throws PendingException 
     */
    OrderInfo get(OrderInfoQuery orderInfoQuery) throws PendingException;
    
    /**
     * 查询订单信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderInfo mustGet(OrderInfoQuery orderInfoQuery) throws PendingException;

    /**
     * 查询订单信息列表
     * @throws PendingException 
     */
    List<OrderInfo> queryList(OrderInfoQuery orderInfoQuery) throws PendingException;

    /**
     * 查询订单信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderInfo> queryPage(OrderInfoQuery orderInfoQuery) throws PendingException;

}