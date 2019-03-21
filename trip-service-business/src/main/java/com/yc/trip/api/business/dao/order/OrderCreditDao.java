package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.bo.order.OrderCreditDomain;

/**
 * 订单积分信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:45
 *
 */
public interface OrderCreditDao {

    /**
     * 新增订单积分信息
     */
    void addOrderCredit(OrderCreditDomain orderCreditDomain);

    /**
     * 修改订单积分信息
     */
    void updateOrderCredit(OrderCreditDomain orderCreditDomain);
    
    /**
     * 查询订单积分信息
     */
    OrderCreditDomain getOrderCredit(OrderCreditDomain orderCreditDomain);

    /**
     * 查询订单积分信息列表
     */
    List<OrderCreditDomain> queryOrderCreditList(OrderCreditDomain orderCreditDomain);

}