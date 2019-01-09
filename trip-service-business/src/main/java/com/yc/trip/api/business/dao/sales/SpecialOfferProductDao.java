package com.yc.trip.api.business.dao.sales;

import java.util.List;

import com.yc.trip.api.business.dto.sales.SpecialOfferProduct;
import com.yc.trip.api.business.query.sales.SpecialOfferProductQuery;
import com.yc.trip.api.business.request.common.IdRequest;

/**
 * 优惠活动产品信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-08 23:31
 *
 */
public interface SpecialOfferProductDao {

    /**
     * 优惠活动产品信息新增
     */
    void add(SpecialOfferProduct specialOfferProduct);

    /**
     * 优惠活动产品信息修改
     */
    void update(SpecialOfferProduct specialOfferProduct);
    
    /**
     * 优惠活动产品信息查询
     */
    SpecialOfferProduct get(SpecialOfferProductQuery specialOfferProductQuery);

    /**
     * 优惠活动产品信息列表查询
     */
    List<SpecialOfferProduct> queryList(SpecialOfferProductQuery specialOfferProductQuery);

    /**
     * 删除优惠活动产品
     */
    void deleteSpecialOfferProduct(IdRequest idRequest);
}