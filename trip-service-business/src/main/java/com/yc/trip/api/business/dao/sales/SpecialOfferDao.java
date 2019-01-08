package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.dto.sales.SpecialOffer;
import com.yc.trip.api.business.query.sales.SpecialOfferQuery;

/**
 * 优惠活动信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:30
 *
 */
public interface SpecialOfferDao {

    /**
     * 优惠活动信息新增
     */
    void add(SpecialOffer specialOffer);

    /**
     * 优惠活动信息修改
     */
    void update(SpecialOffer specialOffer);
    
    /**
     * 优惠活动信息查询
     */
    SpecialOffer get(SpecialOfferQuery specialOfferQuery);

    /**
     * 优惠活动信息列表查询
     */
    List<SpecialOffer> queryList(SpecialOfferQuery specialOfferQuery);

}