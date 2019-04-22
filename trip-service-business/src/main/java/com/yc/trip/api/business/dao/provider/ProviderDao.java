package com.yc.trip.api.business.dao.provider;

import java.util.List;

import com.yc.trip.api.business.bo.provider.ProviderDomain;

/**
 * 供应商信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-03-21 22:09
 *
 */
public interface ProviderDao {

    /**
     * 新增供应商信息
     */
    void addProvider(ProviderDomain providerDomain);

    /**
     * 修改供应商信息
     */
    void updateProvider(ProviderDomain providerDomain);
    
    /**
     * 查询供应商信息
     */
    ProviderDomain getProvider(ProviderDomain providerDomain);

    /**
     * 查询供应商信息列表
     */
    List<ProviderDomain> queryProviderList(ProviderDomain providerDomain);


    List<ProviderDomain> getAllProvider();

}