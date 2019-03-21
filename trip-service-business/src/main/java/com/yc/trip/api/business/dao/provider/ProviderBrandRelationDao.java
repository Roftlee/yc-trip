package com.yc.trip.api.business.dao.provider;

import java.util.List;

import com.yc.trip.api.business.bo.provider.ProviderBrandRelationDomain;

/**
 * 供应商品牌关联信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:10
 *
 */
public interface ProviderBrandRelationDao {

    /**
     * 新增供应商品牌关联信息
     */
    void addProviderBrandRelation(ProviderBrandRelationDomain providerBrandRelationDomain);

    /**
     * 修改供应商品牌关联信息
     */
    void updateProviderBrandRelation(ProviderBrandRelationDomain providerBrandRelationDomain);
    
    /**
     * 查询供应商品牌关联信息
     */
    ProviderBrandRelationDomain getProviderBrandRelation(ProviderBrandRelationDomain providerBrandRelationDomain);

    /**
     * 查询供应商品牌关联信息列表
     */
    List<ProviderBrandRelationDomain> queryProviderBrandRelationList(ProviderBrandRelationDomain providerBrandRelationDomain);

}