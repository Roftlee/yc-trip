package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderCredit;
import com.yc.trip.api.business.query.order.OrderCreditQuery;

/**
 * 订单积分信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:12
 */
public interface OrderCreditFacade {

    /**
     * 新增订单积分信息
     * @throws PendingException 
     */
    OrderCredit add(OrderCredit orderCredit) throws PendingException;

    /**
     * 修改订单积分信息
     * @throws PendingException 
     */
    void update(OrderCredit orderCredit) throws PendingException;
    
    /**
     * 查询订单积分信息
     * @throws PendingException 
     */
    OrderCredit get(OrderCreditQuery orderCreditQuery) throws PendingException;
    
    /**
     * 查询订单积分信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderCredit mustGet(OrderCreditQuery orderCreditQuery) throws PendingException;

    /**
     * 查询订单积分信息列表
     * @throws PendingException 
     */
    List<OrderCredit> queryList(OrderCreditQuery orderCreditQuery) throws PendingException;

    /**
     * 查询订单积分信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderCredit> queryPage(OrderCreditQuery orderCreditQuery) throws PendingException;

}