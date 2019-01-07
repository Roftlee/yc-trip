package com.yc.trip.api.business.facade.sales;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.query.sales.SpecialOfferQuery;

/**
 * 优惠活动信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:46
 */
public interface SpecialOfferFacade {

    /**
     * 新增优惠活动信息
     * @throws PendingException 
     */
    SpecialOffer add(SpecialOffer specialOffer) throws PendingException;

    /**
     * 修改优惠活动信息
     * @throws PendingException 
     */
    void update(SpecialOffer specialOffer) throws PendingException;
    
    /**
     * 查询优惠活动信息
     * @throws PendingException 
     */
    SpecialOffer get(SpecialOfferQuery specialOfferQuery) throws PendingException;
    
    /**
     * 查询优惠活动信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    SpecialOffer mustGet(SpecialOfferQuery specialOfferQuery) throws PendingException;

    /**
     * 查询优惠活动信息列表
     * @throws PendingException 
     */
    List<SpecialOffer> queryList(SpecialOfferQuery specialOfferQuery) throws PendingException;

    /**
     * 查询优惠活动信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<SpecialOffer> queryPage(SpecialOfferQuery specialOfferQuery) throws PendingException;

}