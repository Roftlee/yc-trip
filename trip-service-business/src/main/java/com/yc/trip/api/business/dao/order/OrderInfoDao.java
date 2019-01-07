package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.dto.order.OrderInfo;
import com.yc.trip.api.business.query.order.OrderInfoQuery;

/**
 * 订单信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:15
 *
 */
public interface OrderInfoDao {

    /**
     * 订单信息新增
     */
    void add(OrderInfo orderInfo);

    /**
     * 订单信息修改
     */
    void update(OrderInfo orderInfo);
    
    /**
     * 订单信息查询
     */
    OrderInfo get(OrderInfoQuery orderInfoQuery);

    /**
     * 订单信息列表查询
     */
    List<OrderInfo> queryList(OrderInfoQuery orderInfoQuery);

}