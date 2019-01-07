package com.yc.trip.api.business.dao.provider;

import java.util.List;

import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.query.provider.ProviderBrandRelationQuery;

/**
 * 供应商品牌关联信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:25
 *
 */
public interface ProviderBrandRelationDao {

    /**
     * 供应商品牌关联信息新增
     */
    void add(ProviderBrandRelation providerBrandRelation);

    /**
     * 供应商品牌关联信息修改
     */
    void update(ProviderBrandRelation providerBrandRelation);
    
    /**
     * 供应商品牌关联信息查询
     */
    ProviderBrandRelation get(ProviderBrandRelationQuery providerBrandRelationQuery);

    /**
     * 供应商品牌关联信息列表查询
     */
    List<ProviderBrandRelation> queryList(ProviderBrandRelationQuery providerBrandRelationQuery);

}