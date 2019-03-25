package com.yc.trip.api.business.facade.sales;

import java.util.List;

import com.yc.trip.api.business.request.common.IdRequest;
import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.sales.SpecialOfferProduct;

/**
 * 优惠活动产品信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:21
 */
public interface SpecialOfferProductFacade {

    /**
     * 新增优惠活动产品信息
     * @throws PendingException 
     */
    SpecialOfferProduct addSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException;

    /**
     * 修改优惠活动产品信息
     * @throws PendingException 
     */
    SpecialOfferProduct updateSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException;
    
    /**
     * 查询优惠活动产品信息
     * @throws PendingException 
     */
    SpecialOfferProduct getSpecialOfferProduct(SpecialOfferProduct specialOfferProduct) throws PendingException;
    
    /**
     * 查询优惠活动产品信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    SpecialOfferProduct mustGet(SpecialOfferProduct specialOfferProduct) throws PendingException;

    /**
     * 查询优惠活动产品信息列表
     * @throws PendingException 
     */
    List<SpecialOfferProduct> querySpecialOfferProductList(SpecialOfferProduct specialOfferProduct) throws PendingException;

    /**
     * 查询优惠活动产品信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<SpecialOfferProduct> querySpecialOfferProductPage(SpecialOfferProduct specialOfferProduct) throws PendingException;

    /**
     * 删除优惠活动产品
     * @throws PendingException
     */
    void deleteSpecialOfferProduct(IdRequest idRequest) throws PendingException;
}