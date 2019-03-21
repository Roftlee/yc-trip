package com.yc.trip.api.business.facade.coupons;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.coupons.Coupons;

/**
 * 优惠券相关接口
 * @author My-Toolkits
 * @since 2019-03-21 21:34
 */
public interface CouponsFacade {

    /**
     * 新增优惠券
     * @throws PendingException 
     */
    Coupons addCoupons(Coupons coupons) throws PendingException;

    /**
     * 修改优惠券
     * @throws PendingException 
     */
    Coupons updateCoupons(Coupons coupons) throws PendingException;
    
    /**
     * 查询优惠券
     * @throws PendingException 
     */
    Coupons getCoupons(Coupons coupons) throws PendingException;
    
    /**
     * 查询优惠券（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Coupons mustGet(Coupons coupons) throws PendingException;

    /**
     * 查询优惠券列表
     * @throws PendingException 
     */
    List<Coupons> queryCouponsList(Coupons coupons) throws PendingException;

    /**
     * 查询优惠券列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Coupons> queryCouponsPage(Coupons coupons) throws PendingException;

}