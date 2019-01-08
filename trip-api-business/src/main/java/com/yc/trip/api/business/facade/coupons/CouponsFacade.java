package com.yc.trip.api.business.facade.coupons;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.coupons.Coupons;
import com.yc.trip.api.business.query.coupons.CouponsQuery;

/**
 * 优惠券相关接口
 * @author My-Toolkits
 * @since 2019-01-08 23:25
 */
public interface CouponsFacade {

    /**
     * 新增优惠券
     * @throws PendingException 
     */
    Coupons add(Coupons coupons) throws PendingException;

    /**
     * 修改优惠券
     * @throws PendingException 
     */
    void update(Coupons coupons) throws PendingException;
    
    /**
     * 查询优惠券
     * @throws PendingException 
     */
    Coupons get(CouponsQuery couponsQuery) throws PendingException;
    
    /**
     * 查询优惠券（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Coupons mustGet(CouponsQuery couponsQuery) throws PendingException;

    /**
     * 查询优惠券列表
     * @throws PendingException 
     */
    List<Coupons> queryList(CouponsQuery couponsQuery) throws PendingException;

    /**
     * 查询优惠券列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Coupons> queryPage(CouponsQuery couponsQuery) throws PendingException;

}