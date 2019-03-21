package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.bo.sales.SpecialOfferDomain;

/**
 * 优惠活动信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:20
 *
 */
public interface SpecialOfferDao {

    /**
     * 新增优惠活动信息
     */
    void addSpecialOffer(SpecialOfferDomain specialOfferDomain);

    /**
     * 修改优惠活动信息
     */
    void updateSpecialOffer(SpecialOfferDomain specialOfferDomain);
    
    /**
     * 查询优惠活动信息
     */
    SpecialOfferDomain getSpecialOffer(SpecialOfferDomain specialOfferDomain);

    /**
     * 查询优惠活动信息列表
     */
    List<SpecialOfferDomain> querySpecialOfferList(SpecialOfferDomain specialOfferDomain);

}