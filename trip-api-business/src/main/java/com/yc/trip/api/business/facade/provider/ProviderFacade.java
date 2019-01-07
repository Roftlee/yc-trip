package com.yc.trip.api.business.facade.provider;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.query.provider.ProviderQuery;

/**
 * 供应商信息相关接口
 * @author My-Toolkits
 * @since 2019-01-06 17:24
 */
public interface ProviderFacade {

    /**
     * 新增供应商信息
     * @throws PendingException 
     */
    Provider add(Provider provider) throws PendingException;

    /**
     * 修改供应商信息
     * @throws PendingException 
     */
    void update(Provider provider) throws PendingException;
    
    /**
     * 查询供应商信息
     * @throws PendingException 
     */
    Provider get(ProviderQuery providerQuery) throws PendingException;
    
    /**
     * 查询供应商信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Provider mustGet(ProviderQuery providerQuery) throws PendingException;

    /**
     * 查询供应商信息列表
     * @throws PendingException 
     */
    List<Provider> queryList(ProviderQuery providerQuery) throws PendingException;

    /**
     * 查询供应商信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Provider> queryPage(ProviderQuery providerQuery) throws PendingException;

}