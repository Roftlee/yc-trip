package com.yc.trip.api.business.facade.provider;

import com.github.pagehelper.PageInfo;
import com.yc.trip.api.business.item.provider.ProviderItem;
import com.yc.trip.api.business.request.provider.ProviderAddRequest;
import com.yc.trip.api.business.request.provider.ProviderPageRequest;
import com.yc.trip.api.business.request.provider.ProviderUpdateRequest;
import org.go.framework.core.exception.PendingException;

/**
 * 供应商信息高级服务
 *
 * @author My-Toolkits
 * @since 2019-03-21 22:09
 */
public interface ProviderProfFacade {

    /**
     * 查询供应商列表分页
     * @param request
     * @return
     * @throws PendingException
     */
    PageInfo<ProviderItem> queryProviderPage(ProviderPageRequest request) throws PendingException;

    /**
     * 新增供应商
     * @param request
     * @throws PendingException
     */
    void addProvider(ProviderAddRequest request) throws PendingException;

    /**
     * 更新供应商
     * @param request
     * @throws PendingException
     */
    void updateProvider(ProviderUpdateRequest request) throws PendingException;
}