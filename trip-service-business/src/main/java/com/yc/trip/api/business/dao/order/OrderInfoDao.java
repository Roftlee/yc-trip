package com.yc.trip.api.business.dao.order;

import java.util.List;

import com.yc.trip.api.business.bo.order.OrderInfoDomain;

/**
 * 订单信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:48
 *
 */
public interface OrderInfoDao {

    /**
     * 新增订单信息
     */
    void addOrderInfo(OrderInfoDomain orderInfoDomain);

    /**
     * 修改订单信息
     */
    void updateOrderInfo(OrderInfoDomain orderInfoDomain);
    
    /**
     * 查询订单信息
     */
    OrderInfoDomain getOrderInfo(OrderInfoDomain orderInfoDomain);

    /**
     * 查询订单信息列表
     */
    List<OrderInfoDomain> queryOrderInfoList(OrderInfoDomain orderInfoDomain);

    /**
     * 后台管理系统中搜索功能
     * @param orderInfoDomain
     * @return
     */
    List<OrderInfoDomain>  queryOrderInfo(OrderInfoDomain orderInfoDomain);

}