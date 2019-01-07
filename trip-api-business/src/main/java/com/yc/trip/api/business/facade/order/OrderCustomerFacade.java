package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderCustomer;
import com.yc.trip.api.business.query.order.OrderCustomerQuery;

/**
 * 订单人员信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:14
 */
public interface OrderCustomerFacade {

    /**
     * 新增订单人员信息
     * @throws PendingException 
     */
    OrderCustomer add(OrderCustomer orderCustomer) throws PendingException;

    /**
     * 修改订单人员信息
     * @throws PendingException 
     */
    void update(OrderCustomer orderCustomer) throws PendingException;
    
    /**
     * 查询订单人员信息
     * @throws PendingException 
     */
    OrderCustomer get(OrderCustomerQuery orderCustomerQuery) throws PendingException;
    
    /**
     * 查询订单人员信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderCustomer mustGet(OrderCustomerQuery orderCustomerQuery) throws PendingException;

    /**
     * 查询订单人员信息列表
     * @throws PendingException 
     */
    List<OrderCustomer> queryList(OrderCustomerQuery orderCustomerQuery) throws PendingException;

    /**
     * 查询订单人员信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderCustomer> queryPage(OrderCustomerQuery orderCustomerQuery) throws PendingException;

}