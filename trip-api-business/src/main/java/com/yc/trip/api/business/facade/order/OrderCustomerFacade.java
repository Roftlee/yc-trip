package com.yc.trip.api.business.facade.order;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.order.OrderCustomer;

/**
 * 订单人员信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:47
 */
public interface OrderCustomerFacade {

    /**
     * 新增订单人员信息
     * @throws PendingException 
     */
    OrderCustomer addOrderCustomer(OrderCustomer orderCustomer) throws PendingException;

    /**
     * 修改订单人员信息
     * @throws PendingException 
     */
    OrderCustomer updateOrderCustomer(OrderCustomer orderCustomer) throws PendingException;
    
    /**
     * 查询订单人员信息
     * @throws PendingException 
     */
    OrderCustomer getOrderCustomer(OrderCustomer orderCustomer) throws PendingException;
    
    /**
     * 查询订单人员信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    OrderCustomer mustGet(OrderCustomer orderCustomer) throws PendingException;

    /**
     * 查询订单人员信息列表
     * @throws PendingException 
     */
    List<OrderCustomer> queryOrderCustomerList(OrderCustomer orderCustomer) throws PendingException;

    /**
     * 查询订单人员信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<OrderCustomer> queryOrderCustomerPage(OrderCustomer orderCustomer) throws PendingException;

}