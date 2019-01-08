package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.dto.user.UserCoupons;
import com.yc.trip.api.business.query.user.UserCouponsQuery;

/**
 * 用户优惠券Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:28
 *
 */
public interface UserCouponsDao {

    /**
     * 用户优惠券新增
     */
    void add(UserCoupons userCoupons);

    /**
     * 用户优惠券修改
     */
    void update(UserCoupons userCoupons);
    
    /**
     * 用户优惠券查询
     */
    UserCoupons get(UserCouponsQuery userCouponsQuery);

    /**
     * 用户优惠券列表查询
     */
    List<UserCoupons> queryList(UserCouponsQuery userCouponsQuery);

}