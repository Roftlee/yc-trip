package com.yc.trip.api.business.facade.provider;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.provider.ProviderBrandRelation;
import com.yc.trip.api.business.query.provider.ProviderBrandRelationQuery;

/**
 * 供应商品牌关联信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:25
 */
public interface ProviderBrandRelationFacade {

    /**
     * 新增供应商品牌关联信息
     * @throws PendingException 
     */
    ProviderBrandRelation add(ProviderBrandRelation providerBrandRelation) throws PendingException;

    /**
     * 修改供应商品牌关联信息
     * @throws PendingException 
     */
    void update(ProviderBrandRelation providerBrandRelation) throws PendingException;
    
    /**
     * 查询供应商品牌关联信息
     * @throws PendingException 
     */
    ProviderBrandRelation get(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException;
    
    /**
     * 查询供应商品牌关联信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    ProviderBrandRelation mustGet(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException;

    /**
     * 查询供应商品牌关联信息列表
     * @throws PendingException 
     */
    List<ProviderBrandRelation> queryList(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException;

    /**
     * 查询供应商品牌关联信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<ProviderBrandRelation> queryPage(ProviderBrandRelationQuery providerBrandRelationQuery) throws PendingException;

}