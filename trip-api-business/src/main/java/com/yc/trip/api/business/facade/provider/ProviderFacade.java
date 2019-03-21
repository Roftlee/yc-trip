package com.yc.trip.api.business.facade.provider;

import java.util.List;

import org.go.framework.core.exception.PendingException;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.dto.provider.Provider;

/**
 * 供应商信息相关接口
 * @author My-Toolkits
 * @since 2019-03-21 22:09
 */
public interface ProviderFacade {

    /**
     * 新增供应商信息
     * @throws PendingException 
     */
    Provider addProvider(Provider provider) throws PendingException;

    /**
     * 修改供应商信息
     * @throws PendingException 
     */
    Provider updateProvider(Provider provider) throws PendingException;
    
    /**
     * 查询供应商信息
     * @throws PendingException 
     */
    Provider getProvider(Provider provider) throws PendingException;
    
    /**
     * 查询供应商信息（若不存在，则抛出异常）
     * 
     * @throws PendingException
     */
    Provider mustGet(Provider provider) throws PendingException;

    /**
     * 查询供应商信息列表
     * @throws PendingException 
     */
    List<Provider> queryProviderList(Provider provider) throws PendingException;

    /**
     * 查询供应商信息列表 ,分页查询
     * @throws PendingException 
     */
    PageInfo<Provider> queryProviderPage(Provider provider) throws PendingException;

}