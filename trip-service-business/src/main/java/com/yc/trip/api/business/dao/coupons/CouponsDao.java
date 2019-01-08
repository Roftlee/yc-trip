package com.yc.trip.api.business.dao.coupons;

import java.util.List;

import com.yc.trip.api.business.dto.coupons.Coupons;
import com.yc.trip.api.business.query.coupons.CouponsQuery;

/**
 * 优惠券Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:25
 *
 */
public interface CouponsDao {

    /**
     * 优惠券新增
     */
    void add(Coupons coupons);

    /**
     * 优惠券修改
     */
    void update(Coupons coupons);
    
    /**
     * 优惠券查询
     */
    Coupons get(CouponsQuery couponsQuery);

    /**
     * 优惠券列表查询
     */
    List<Coupons> queryList(CouponsQuery couponsQuery);

}