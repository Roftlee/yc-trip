package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.dto.order.OrderCustomer;
import com.yc.trip.api.business.query.order.OrderCustomerQuery;

/**
 * 订单人员信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:14
 *
 */
public interface OrderCustomerDao {

    /**
     * 订单人员信息新增
     */
    void add(OrderCustomer orderCustomer);

    /**
     * 订单人员信息修改
     */
    void update(OrderCustomer orderCustomer);
    
    /**
     * 订单人员信息查询
     */
    OrderCustomer get(OrderCustomerQuery orderCustomerQuery);

    /**
     * 订单人员信息列表查询
     */
    List<OrderCustomer> queryList(OrderCustomerQuery orderCustomerQuery);

}