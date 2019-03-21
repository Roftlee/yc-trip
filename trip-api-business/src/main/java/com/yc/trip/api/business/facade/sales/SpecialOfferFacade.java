package com.yc.trip.api.business.facade.sales;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SpecialOffer;

/**
 * 优惠活动信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:20
 */
public interface SpecialOfferFacade {

    /**
     * 新增优惠活动信息
     * @throws PendingException 
     */
    SpecialOffer addSpecialOffer(SpecialOffer specialOffer) throws PendingException;

    /**
     * 修改优惠活动信息
     * @throws PendingException 
     */
    SpecialOffer updateSpecialOffer(SpecialOffer specialOffer) throws PendingException;
    
    /**
     * 查询优惠活动信息
     * @throws PendingException 
     */
    SpecialOffer getSpecialOffer(SpecialOffer specialOffer) throws PendingException;
    
    /**
     * 查询优惠活动信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    SpecialOffer mustGet(SpecialOffer specialOffer) throws PendingException;

    /**
     * 查询优惠活动信息列表
     * @throws PendingException 
     */
    List<SpecialOffer> querySpecialOfferList(SpecialOffer specialOffer) throws PendingException;

    /**
     * 查询优惠活动信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<SpecialOffer> querySpecialOfferPage(SpecialOffer specialOffer) throws PendingException;

}