package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.bo.sales.SpecialOfferProductDomain;

/**
 * 优惠活动产品信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:21
 *
 */
public interface SpecialOfferProductDao {

    /**
     * 新增优惠活动产品信息
     */
    void addSpecialOfferProduct(SpecialOfferProductDomain specialOfferProductDomain);

    /**
     * 修改优惠活动产品信息
     */
    void updateSpecialOfferProduct(SpecialOfferProductDomain specialOfferProductDomain);
    
    /**
     * 查询优惠活动产品信息
     */
    SpecialOfferProductDomain getSpecialOfferProduct(SpecialOfferProductDomain specialOfferProductDomain);

    /**
     * 查询优惠活动产品信息列表
     */
    List<SpecialOfferProductDomain> querySpecialOfferProductList(SpecialOfferProductDomain specialOfferProductDomain);

    /**
     * 删除优惠活动产品
     */
    void deleteSpecialOfferProduct(IdRequest idRequest);
}