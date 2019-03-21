package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderCredit;

/**
 * 订单积分信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:45
 */
public interface OrderCreditFacade {

    /**
     * 新增订单积分信息
     * @throws PendingException 
     */
    OrderCredit addOrderCredit(OrderCredit orderCredit) throws PendingException;

    /**
     * 修改订单积分信息
     * @throws PendingException 
     */
    OrderCredit updateOrderCredit(OrderCredit orderCredit) throws PendingException;
    
    /**
     * 查询订单积分信息
     * @throws PendingException 
     */
    OrderCredit getOrderCredit(OrderCredit orderCredit) throws PendingException;
    
    /**
     * 查询订单积分信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderCredit mustGet(OrderCredit orderCredit) throws PendingException;

    /**
     * 查询订单积分信息列表
     * @throws PendingException 
     */
    List<OrderCredit> queryOrderCreditList(OrderCredit orderCredit) throws PendingException;

    /**
     * 查询订单积分信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderCredit> queryOrderCreditPage(OrderCredit orderCredit) throws PendingException;

}