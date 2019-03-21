package com.yc.trip.api.business.dao.user;

import java.util.List;

import com.yc.trip.api.business.bo.user.UserCouponsDomain;

/**
 * 用户优惠券Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:32
 *
 */
public interface UserCouponsDao {

    /**
     * 新增用户优惠券
     */
    void addUserCoupons(UserCouponsDomain userCouponsDomain);

    /**
     * 修改用户优惠券
     */
    void updateUserCoupons(UserCouponsDomain userCouponsDomain);
    
    /**
     * 查询用户优惠券
     */
    UserCouponsDomain getUserCoupons(UserCouponsDomain userCouponsDomain);

    /**
     * 查询用户优惠券列表
     */
    List<UserCouponsDomain> queryUserCouponsList(UserCouponsDomain userCouponsDomain);

}