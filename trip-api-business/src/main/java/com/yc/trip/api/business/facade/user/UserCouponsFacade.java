package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserCoupons;
import com.yc.trip.api.business.query.user.UserCouponsQuery;

/**
 * 用户优惠券相关接口
 * @author My-Toolkits
 * @since 2019-01-08 23:28
 */
public interface UserCouponsFacade {

    /**
     * 新增用户优惠券
     * @throws PendingException 
     */
    UserCoupons add(UserCoupons userCoupons) throws PendingException;

    /**
     * 修改用户优惠券
     * @throws PendingException 
     */
    void update(UserCoupons userCoupons) throws PendingException;
    
    /**
     * 查询用户优惠券
     * @throws PendingException 
     */
    UserCoupons get(UserCouponsQuery userCouponsQuery) throws PendingException;
    
    /**
     * 查询用户优惠券（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserCoupons mustGet(UserCouponsQuery userCouponsQuery) throws PendingException;

    /**
     * 查询用户优惠券列表
     * @throws PendingException 
     */
    List<UserCoupons> queryList(UserCouponsQuery userCouponsQuery) throws PendingException;

    /**
     * 查询用户优惠券列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserCoupons> queryPage(UserCouponsQuery userCouponsQuery) throws PendingException;

}