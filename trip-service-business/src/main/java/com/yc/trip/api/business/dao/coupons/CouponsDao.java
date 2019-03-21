package com.yc.trip.api.business.dao.coupons;

import java.util.List;

import com.yc.trip.api.business.bo.coupons.CouponsDomain;

/**
 * 优惠券Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 21:34
 *
 */
public interface CouponsDao {

    /**
     * 新增优惠券
     */
    void addCoupons(CouponsDomain couponsDomain);

    /**
     * 修改优惠券
     */
    void updateCoupons(CouponsDomain couponsDomain);
    
    /**
     * 查询优惠券
     */
    CouponsDomain getCoupons(CouponsDomain couponsDomain);

    /**
     * 查询优惠券列表
     */
    List<CouponsDomain> queryCouponsList(CouponsDomain couponsDomain);

}