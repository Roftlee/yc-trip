package com.yc.trip.api.business.dao.provider;

import java.util.List;

import com.yc.trip.api.business.dto.provider.Provider;
import com.yc.trip.api.business.query.provider.ProviderQuery;

/**
 * 供应商信息Dao类
 * 
 * @author My-Toolkits
 * @since 2019-01-06 17:24
 *
 */
public interface ProviderDao {

    /**
     * 供应商信息新增
     */
    void add(Provider provider);

    /**
     * 供应商信息修改
     */
    void update(Provider provider);
    
    /**
     * 供应商信息查询
     */
    Provider get(ProviderQuery providerQuery);

    /**
     * 供应商信息列表查询
     */
    List<Provider> queryList(ProviderQuery providerQuery);

}