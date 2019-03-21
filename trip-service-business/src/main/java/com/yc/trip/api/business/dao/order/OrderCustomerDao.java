package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.bo.order.OrderCustomerDomain;

/**
 * 订单人员信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:47
 *
 */
public interface OrderCustomerDao {

    /**
     * 新增订单人员信息
     */
    void addOrderCustomer(OrderCustomerDomain orderCustomerDomain);

    /**
     * 修改订单人员信息
     */
    void updateOrderCustomer(OrderCustomerDomain orderCustomerDomain);
    
    /**
     * 查询订单人员信息
     */
    OrderCustomerDomain getOrderCustomer(OrderCustomerDomain orderCustomerDomain);

    /**
     * 查询订单人员信息列表
     */
    List<OrderCustomerDomain> queryOrderCustomerList(OrderCustomerDomain orderCustomerDomain);

}