package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.dto.order.OrderCredit;
import com.yc.trip.api.business.query.order.OrderCreditQuery;

/**
 * 订单积分信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:12
 *
 */
public interface OrderCreditDao {

    /**
     * 订单积分信息新增
     */
    void add(OrderCredit orderCredit);

    /**
     * 订单积分信息修改
     */
    void update(OrderCredit orderCredit);
    
    /**
     * 订单积分信息查询
     */
    OrderCredit get(OrderCreditQuery orderCreditQuery);

    /**
     * 订单积分信息列表查询
     */
    List<OrderCredit> queryList(OrderCreditQuery orderCreditQuery);

}