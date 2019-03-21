package com.yc.trip.api.business.facade.user;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.user.UserCoupons;

/**
 * 用户优惠券相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:32
 */
public interface UserCouponsFacade {

    /**
     * 新增用户优惠券
     * @throws PendingException 
     */
    UserCoupons addUserCoupons(UserCoupons userCoupons) throws PendingException;

    /**
     * 修改用户优惠券
     * @throws PendingException 
     */
    UserCoupons updateUserCoupons(UserCoupons userCoupons) throws PendingException;
    
    /**
     * 查询用户优惠券
     * @throws PendingException 
     */
    UserCoupons getUserCoupons(UserCoupons userCoupons) throws PendingException;
    
    /**
     * 查询用户优惠券（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    UserCoupons mustGet(UserCoupons userCoupons) throws PendingException;

    /**
     * 查询用户优惠券列表
     * @throws PendingException 
     */
    List<UserCoupons> queryUserCouponsList(UserCoupons userCoupons) throws PendingException;

    /**
     * 查询用户优惠券列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<UserCoupons> queryUserCouponsPage(UserCoupons userCoupons) throws PendingException;

}